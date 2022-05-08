package api.gft.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import api.gft.entity.Party;
import api.gft.entity.Politician;
import api.gft.exception.PoliticianErrorCode;
import api.gft.exception.PoliticianException;
import api.gft.repository.PartyRepository;

@Service
public class PartyService {

	private final PartyRepository partyRepository;
	
	private static final Logger logger = LogManager.getLogger(PartyService.class);

	public PartyService(PartyRepository partyRepository) {
		this.partyRepository = partyRepository;
	}

	/**
	 * Saves party to database.
	 * 
	 * @param party
	 * @return
	 * @throws PoliticianException
	 */

	public Party saveParty(Party party) throws PoliticianException {

		try {
			return partyRepository.save(party);
		} catch (Exception e) {
			logger.error(e);
			throw new PoliticianException(PoliticianErrorCode.OPERATIONNOTPERFORMED, "It was not possible to complete this request.", null);
		}
	}

	/**
	 * Gets all database parties.
	 * 
	 * @return
	 */
	public List<Party> getAllParties() {

		return partyRepository.findAll();
	}

	/**
	 * Gets database party by id value.
	 * 
	 * @param id
	 * @return
	 * @throws PoliticianException
	 */
	public Party getPartyById(Long id) throws PoliticianException {

		try {
			Optional<Party> p = partyRepository.findById(id);
			return p.get();
		} catch (NoSuchElementException | EntityNotFoundException | EmptyResultDataAccessException e) {
			logger.debug("Not found " + id);
			throw new PoliticianException(PoliticianErrorCode.NOTFOUND, "Not Found!", null);
		}

	}

	/**
	 * Updates party in the database.
	 * 
	 * @param updatedParty
	 * @param id
	 * @return
	 * @throws PoliticianException
	 */
	public Party updateParty(Party updatedParty, Long id) throws PoliticianException {

		Party party = partyRepository.getById(id);
		updatedParty.setPartyId(party.getPartyId());
		try {
			return partyRepository.save(updatedParty);
		} catch (Exception e) {
			logger.error(e);
			throw new PoliticianException(PoliticianErrorCode.OPERATIONNOTPERFORMED, "It was not possible to complete this request.", null);
		}
	}

	/**
	 * Deletes database party by id.
	 * 
	 * @param id
	 * @throws PoliticianException
	 */
	public void deleteParty(Long id) throws PoliticianException {

		try {
			partyRepository.deleteById(id);
		} catch (EntityNotFoundException | EmptyResultDataAccessException e) {
			logger.debug("Failed to delete " + id);
			throw new PoliticianException(PoliticianErrorCode.FAILEDTODELETE,
					"Failed to delete. Verify if the information is correct.", null);
		}

	}

	public Politician getLeaderByParty(Long partyId) throws PoliticianException {

		try {
			return partyRepository.getLeaderByParty(partyId);
		} catch (Exception e) {
			logger.debug("Not found ");
			e.printStackTrace();
			throw new PoliticianException(PoliticianErrorCode.NOTFOUND, "Not found!",
					null);

		}
	}

}
