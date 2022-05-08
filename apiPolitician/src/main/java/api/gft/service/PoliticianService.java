package api.gft.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import api.gft.DTO.politician.PoliticianDTO;
import api.gft.DTO.politician.PoliticianMapper;
import api.gft.entity.Party;
import api.gft.entity.Politician;
import api.gft.exception.PoliticianErrorCode;
import api.gft.exception.PoliticianException;
import api.gft.repository.PoliticianRepository;
import api.gft.security.entity.User;
import api.gft.utility.FileUtility;

@Service
public class PoliticianService {

	private final PartyService partyService;
	private final PoliticianRepository politicianRepository;
	private final FileUtility fileU;

	private static final Logger logger = LogManager.getLogger(PoliticianService.class);
	
	public PoliticianService(PoliticianRepository politicianRepository, FileUtility fileU, PartyService partyService) {
		this.politicianRepository = politicianRepository;
		this.fileU = fileU;
		this.partyService = partyService;
	}


	/**
	 * Save image in the folder and the politician in the database with file's path.
	 */
	public Politician savePolitician(byte[] file, Politician politician) throws PoliticianException{

		isLeaderDuplicated(politician);
		Politician p = null;
		try {
			Party party = partyService.getPartyById(politician.getParty().getPartyId());

			politician.getImage().setFileFolder(party.getPartyInitials());
			p = politicianRepository.save(politician);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new PoliticianException(PoliticianErrorCode.OPERATIONNOTPERFORMED, "It was not possible to complete this request.", null);
		}

		String path = politician.getImage().getFileFolder() + "/" + politician.getId();
		fileU.saveFile(file, path, politician.getImage().getFileName());

		return p;
	}

	/**
	 * Gets politicians sorted in descending order by name.
	 * 
	 * @return
	 * @throws PoliticianException 
	 */
	public List<PoliticianDTO> getAllPoliticiansByNameDesc() throws PoliticianException {

		List<Politician> politicians = politicianRepository.findAllDesc();
		List<PoliticianDTO> listDto = new ArrayList<>();

		for (Politician p : politicians) {

			String path = p.getImage().getFileFolder() + "/" + p.getId();
			byte[] file = fileU.readFile(path, p.getImage().getFileName());

			PoliticianDTO pDTO = null;

			if (isManager()) {
				pDTO = PoliticianMapper.fromEntity(p);
			} else {
				pDTO = PoliticianMapper.fromEntitygGeneralUsers(p);
			}
			pDTO.setBase64Image(Base64.getMimeEncoder().encodeToString(file));

			listDto.add(pDTO);
		}

		return listDto;
	}

	/**
	 * Gets politicians sorted in ascending order by name.
	 * 
	 * @return
	 * @throws PoliticianException 
	 */
	public List<PoliticianDTO> getAllPoliticiansByNameAsc() throws PoliticianException {

		List<Politician> politicians = politicianRepository.findAllAsc();
		List<PoliticianDTO> listDto = new ArrayList<>();

		for (Politician p : politicians) {

			String path = p.getImage().getFileFolder() + "/" + p.getId();
			byte[] file = fileU.readFile(path, p.getImage().getFileName());

			PoliticianDTO pDTO = null;

			if (isManager()) {
				pDTO = PoliticianMapper.fromEntity(p);
			} else {
				pDTO = PoliticianMapper.fromEntitygGeneralUsers(p);
			}
			pDTO.setBase64Image(Base64.getMimeEncoder().encodeToString(file));

			listDto.add(pDTO);
		}

		return listDto;
	}

	/**
	 * Gets politician by ID and return's the content in DTO.
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public PoliticianDTO getPoliticianDTOById(Long id) throws PoliticianException  {
		
		Politician p = null;
		
			p = this.getPoliticianById(id);
		
		PoliticianDTO dto = null;
		if (isManager()) {
			dto = PoliticianMapper.fromEntity(p);
		} else {
			dto = PoliticianMapper.fromEntitygGeneralUsers(p);
		}

		String path = p.getImage().getFileFolder() + "/" + p.getId();
		byte[] file = fileU.readFile(path, p.getImage().getFileName());

		dto.setBase64Image(Base64.getMimeEncoder().encodeToString(file));

		return dto;
	}

	/**
	 * Gets politician by id.
	 * 
	 * @param id
	 * @return
	 * @throws PoliticianException 
	 * @throws Exception 
	 */
	public Politician getPoliticianById(Long id) throws PoliticianException  {

		try {
			Optional<Politician> p = politicianRepository.findById(id);
			return p.get();
		} catch(NoSuchElementException e) {
			logger.debug("Not found " + id);
			throw new PoliticianException(PoliticianErrorCode.NOTFOUND, "Not Found!", null);
		}
			
	}

	/**
	 * Gets politician by number of projects in their names.
	 * 
	 * @param amount
	 * @return
	 * @throws Exception 
	 */
	public List<PoliticianDTO> getPoliticianByProjectAmount(Integer amount) throws Exception {

		List<Politician> listP = politicianRepository.findAll();
		List<PoliticianDTO> listDTO = new ArrayList<>();
		for (Politician p : listP) {
			if (p.getLawproject().size() >= amount) {

				PoliticianDTO pDTO = null;
				if (isManager()) {
					pDTO = PoliticianMapper.fromEntity(p);
				} else {
					pDTO = PoliticianMapper.fromEntitygGeneralUsers(p);
				}

				String path = p.getImage().getFileFolder() + "/" + p.getId();
				byte[] file = fileU.readFile(path, p.getImage().getFileName());
				pDTO.setBase64Image(Base64.getMimeEncoder().encodeToString(file));

				listDTO.add(pDTO);

			}
		}

		try {
			return listDTO;
		} catch (Exception e) {
			logger.debug("Not found ");
			throw new Exception("Not found!");
		}
	}

	/**
	 * Updates politician in the database. If the party or image from the politician
	 * changes, sets new file path and saves image in it.
	 * 
	 * @param file
	 * @param politician
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PoliticianDTO updatePolitician(byte[] file, Politician politician, Long id) throws Exception {

		Politician originalPolitician = this.getPoliticianById(id);
		String filePath = originalPolitician.getImage().getFileFolder() + "/"
				+ originalPolitician.getId() + "/" + originalPolitician.getImage().getFileName();
		
		politician.setId(originalPolitician.getId());

		String orgianlPath = originalPolitician.getImage().getFileFolder() + "/" + originalPolitician.getId();
		byte[] originalFile = fileU.readFile(orgianlPath, originalPolitician.getImage().getFileName());

		if (originalPolitician.getParty().getPartyId() != politician.getParty().getPartyId()) {
			isLeaderDuplicated(politician);

			Party party = partyService.getPartyById(politician.getParty().getPartyId());
			politician.getImage().setFileFolder(party.getPartyInitials());
			String path = politician.getImage().getFileFolder() + "/" + politician.getId();
			
			
			fileU.deleteFile(filePath);

			fileU.saveFile(file, path, politician.getImage().getFileName());

			try {

				politicianRepository.save(politician);

				return getPoliticianDTOById(id);

			} catch (Exception e) {
				logger.error(e);
				throw new Exception(e.getMessage());

			}
		} else if (originalFile != file) {
			isLeaderDuplicated(politician);


			Party party = partyService.getPartyById(politician.getParty().getPartyId());
			politician.getImage().setFileFolder(party.getPartyInitials());
			
			String path = politician.getImage().getFileFolder() + "/" + politician.getId();
			
			fileU.deleteFile(filePath);
			
			fileU.saveFile(file, path, politician.getImage().getFileName());
			
			

			try {

				politicianRepository.save(politician);

				return getPoliticianDTOById(id);
				


			} catch (Exception e) {
				logger.error(e);
				throw new Exception(e.getMessage());

			}
		} else {
			try {
				isLeaderDuplicated(politician);

				politicianRepository.save(politician);

				return getPoliticianDTOById(id);

			} catch (Exception e) {
				logger.error(e);
				throw new Exception(e.getMessage());

			}
		}

	}

	/**
	 * Deletes politician from database and file related to it.
	 * 
	 * @param id
	 * @throws PoliticianException 
	 * @throws Exception 
	 */
	public void deletePolitician(Long id) throws PoliticianException  {


		try {
			Politician originalPolitician = getPoliticianById(id);
			String filePath = originalPolitician.getImage().getFileFolder() + "/"
				+ originalPolitician.getId() + "/" + originalPolitician.getImage().getFileName();
			
			politicianRepository.deleteById(id);
			
			fileU.deleteFile(filePath);
			
		} catch(EntityNotFoundException | EmptyResultDataAccessException e){
			logger.debug("Failed to delete " + id);
			throw new PoliticianException(PoliticianErrorCode.FAILEDTODELETE, "Failed to delete. Verify if the information is correct.", null);
		}
	}

	/**
	 * If given politician is leader, verify if party from given politician has a
	 * leader and if it does, throws an exception.
	 * 
	 * @param politician
	 * @throws Exception
	 */
	private void isLeaderDuplicated(Politician politician) throws PoliticianException {
		if (politician.getIsLeader()) {
			Politician leader = partyService.getLeaderByParty(politician.getParty().getPartyId());
			if (leader != null) {
				logger.debug("Duplicate leader for party " + politician.getParty().getPartyName());
				throw new PoliticianException(PoliticianErrorCode.DUPLICATEDLEADER, "This person can not be a leader because the party already has a leader.", null);
			}
		}
	}
	
	/**
	 * Verify if the given user is a manager.
	 * @return Boolean
	 * @throws PoliticianException 
	 */
	private Boolean isManager() throws PoliticianException {
		Boolean isManager = false;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		try {
		if(authentication != null) {
			String userName = authentication.getName();
			if(userName.equalsIgnoreCase("anonymousUser")) {
				isManager = false;
			} else {
				User principal = (User) authentication.getPrincipal();
				if(principal.getRole().getName().equals("MANAGER")) {
					isManager = true;
					}
				}
			}
		}catch(Exception e) {
			throw new PoliticianException(PoliticianErrorCode.OPERATIONNOTPERFORMED, "It was not possible to complete this request.", null);
		}
		return isManager;
	}
	

}
