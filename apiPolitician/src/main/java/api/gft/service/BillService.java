package api.gft.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import api.gft.entity.Bill;
import api.gft.entity.Politician;
import api.gft.exception.PoliticianErrorCode;
import api.gft.exception.PoliticianException;
import api.gft.repository.BillRepository;

@Service
public class BillService {

	private final BillRepository billRepository;
	private final PoliticianService politicianService;
	
	private static final Logger logger = LogManager.getLogger(BillService.class);

	public BillService(BillRepository BilltRepository, PoliticianService politicianService) {
		this.billRepository = BilltRepository;
		this.politicianService = politicianService;
	}
	
	/**
	 * Saves bill to database.
	 * @param Bill
	 * @return
	 * @throws Exception 
	 */

	public Bill saveBill(Bill bill) throws Exception {

		Politician p = politicianService.getPoliticianById(bill.getPolitician().getId());
		
		bill.setPolitician(p);
		
		try {
		return billRepository.save(bill);
		} catch(Exception e) {
			logger.error(e);
			throw new PoliticianException(PoliticianErrorCode.OPERATIONNOTPERFORMED, "It was not possible to complete this request.", null);
		}
		
	}
	
	/**
	 * Gets all database projects.
	 * @return
	 */
	public List<Bill> getAllBills(){
		
		return billRepository.findAll();
	}
	
	/**
	 * Gets database bill by id value.
	 * @param id
	 * @return
	 * @throws PoliticianException 
	 */
	public Bill getBillById(Long id) throws PoliticianException {
		

		try {
			Optional<Bill> b = billRepository.findById(id);
			return b.get();
		} catch(NoSuchElementException | EntityNotFoundException | EmptyResultDataAccessException e) {
			logger.debug("Not found " + id);
			throw new PoliticianException(PoliticianErrorCode.NOTFOUND, "Not Found!", null);
		}
		
	}
	
	/**
	 * Updates bill in the database.
	 * @param updatedBillt
	 * @param id
	 * @return
	 */
	public Bill updateBill(Bill updatedBillt, Long id) throws PoliticianException  {
		
		Bill billt = getBillById(id);
	
		updatedBillt.setId(billt.getId());
		
		try {
		return billRepository.save(updatedBillt); 
		} catch(Exception e) {
			logger.error(e);
			throw new PoliticianException(PoliticianErrorCode.OPERATIONNOTPERFORMED, "It was not possible to complete this request.", null);
		}
	}
	
	/**
	 * Deletes database bill by id.
	 * @param id
	 * @throws PoliticianException 
	 */
	public void deleteBill(Long id) throws PoliticianException {
		
		try {
			billRepository.deleteById(id);
		} catch(EntityNotFoundException | EmptyResultDataAccessException e){
			logger.debug("Failed to delete " + id);
			throw new PoliticianException(PoliticianErrorCode.FAILEDTODELETE, "Failed to delete. Verify if the information is correct.", null);
		}
		
	}
	
	
	
	
	
}
