package api.gft.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import api.gft.entity.Politician;
import api.gft.exception.PoliticianErrorCode;
import api.gft.exception.PoliticianException;
import api.gft.entity.LawSuit;
import api.gft.repository.LawSuitRepository;

@Service
public class LawSuitService {

	private final LawSuitRepository processRepository;
	
	private final PoliticianService politicianService;
	
	private static final Logger logger = LogManager.getLogger(LawSuitService.class);

	public LawSuitService(LawSuitRepository processRepository, PoliticianService politicianService) {
		this.processRepository = processRepository;
		this.politicianService = politicianService;
	}
	
	/**
	 * Saves law suit to database.
	 * @param process
	 * @return
	 * @throws Exception 
	 */

	public LawSuit saveLawSuit(LawSuit process) throws Exception {
		
		Politician p = politicianService.getPoliticianById(process.getPolitician().getId());
		
		process.setPolitician(p);
		try {
			return processRepository.save(process);
		} catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new PoliticianException(PoliticianErrorCode.OPERATIONNOTPERFORMED, "It was not possible to complete this request.", null);
		}
		
	}
	
	/**
	 * Gets all database law suit.
	 * @return
	 */
	public List<LawSuit> getAllLawSuit(){
		
		return processRepository.findAll();
	}
	
	/**
	 * Gets database law suit by id value.
	 * @param id
	 * @return
	 * @throws PoliticianException 
	 */
	public LawSuit getLawSuitById(Long id) throws PoliticianException {
		try {
			Optional<LawSuit> ls = processRepository.findById(id);
			return ls.get();
		} catch(NoSuchElementException | EntityNotFoundException | EmptyResultDataAccessException e) {
			logger.debug("Not found " + id);
			throw new PoliticianException(PoliticianErrorCode.NOTFOUND, "Not Found!", null);
		}
		
	}
	
	/**
	 * Updates law suit in the database.
	 * @param updatedProcess
	 * @param id
	 * @return
	 * @throws PoliticianException 
	 */
	public LawSuit updateLawSuit(LawSuit updatedLawSuit, Long id) throws PoliticianException {
		
		LawSuit Process = processRepository.getById(id);
		updatedLawSuit.setId(Process.getId());
		try {
			return processRepository.save(updatedLawSuit);
		} catch(Exception e) {
			logger.error(e);
			throw new PoliticianException(PoliticianErrorCode.OPERATIONNOTPERFORMED, "It was not possible to complete this request.", null);
		}
	}
	
	/**
	 * Deletes database law suit by id.
	 * @param id
	 * @throws PoliticianException 
	 */
	public void deleteLawSuit(Long id) throws PoliticianException {
		try {
			processRepository.deleteById(id);
		} catch(EntityNotFoundException | EmptyResultDataAccessException e){
			logger.debug("Failed to delete " + id);
			throw new PoliticianException(PoliticianErrorCode.FAILEDTODELETE, "Failed to delete. Verify if the information is correct.", null);
		}
		
	}
	
	
	
}
