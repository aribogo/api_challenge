package api.gft.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import api.gft.DTO.Bill.BillDTO;
import api.gft.DTO.Bill.BillMapper;
import api.gft.DTO.Bill.BillRegistrationDTO;
import api.gft.entity.Bill;
import api.gft.exception.PoliticianErrorCode;
import api.gft.exception.PoliticianException;
import api.gft.service.BillService;

@Controller
@RequestMapping("v1/bill")
public class BillController {
	
	private final BillService billService;

	public BillController(BillService BillService) {
		this.billService = BillService;
	}
	
	/**
	 * Gets DTO with JSON information given in the API and saves it's content in the database or throws an exception.
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveBill(@RequestBody @Validated BillRegistrationDTO dto) {

		try {
			Bill Bill = billService.saveBill(BillMapper.fromDTO(dto));
			return ResponseEntity.ok(BillMapper.fromEntity(Bill));
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}

	}

	/**
	 * Displays all database law projects in the Rest API or throws an exception.
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllBills() {
		
		try {
		return ResponseEntity.ok(BillMapper.fromEntityList(billService.getAllBills()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}

	}

	/**
	 * Gets law project by id and displays it in the Rest API or throws an exception.
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> getBillById(@PathVariable Long id) {

		try {
			BillDTO dto = BillMapper.fromEntity(billService.getBillById(id));
			return ResponseEntity.ok(dto);
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}

	}

	/**
	 * Updates law project in the database or throws an exception.
	 * @param id
	 * @param dto
	 * @return
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> updateBill(@PathVariable Long id, @RequestBody BillRegistrationDTO dto) {
		
		try {
			Bill bill = BillMapper.fromDTO(dto);
			Bill updatedBill = billService.updateBill(bill, id);
			BillDTO BillDto = BillMapper.fromEntity(updatedBill);

			return ResponseEntity.ok(BillDto);

		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}
	}

	/**
	 * Deletes law project from the database or throws an exception.
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteBill(@PathVariable Long id) {

		try {

			billService.deleteBill(id);
			return ResponseEntity.ok().build();

		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}
	}	

}
