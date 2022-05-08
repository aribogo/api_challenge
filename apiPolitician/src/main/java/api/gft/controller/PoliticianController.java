package api.gft.controller;

import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.gft.DTO.politician.PoliticianMapper;
import api.gft.DTO.politician.PoliticianRegistrationDTO;
import api.gft.exception.PoliticianErrorCode;
import api.gft.exception.PoliticianException;
import api.gft.service.PoliticianService;

@RestController
@RequestMapping("v1/politician")
public class PoliticianController {

	private final PoliticianService politicianService;

	public PoliticianController(PoliticianService politicianService) {
		this.politicianService = politicianService;
	}

	/**
	 * Gets DTO with JSON information given in the API and saves it's content in the
	 * database. Also, transforms the base64 string in byte to save it in local
	 * storage. Or throws an exception.
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping(produces = "application/json")
	public ResponseEntity<?> savePolitician(@Validated @RequestBody PoliticianRegistrationDTO dto) throws Exception  {

		try {
			byte[] imageByte = Base64.getMimeDecoder().decode(dto.getBase64Image());
			politicianService.savePolitician(imageByte, PoliticianMapper.fromDTO(dto));
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}

		return null;

	}

	/**
	 * Displays all database politicians in the Rest API sorted in descending order
	 * by name or throws an exception.
	 * 
	 * @return
	 */
	@GetMapping(path = "/desc")
	public ResponseEntity<?> getAllPoliticiansDesc() {
		try {
			return ResponseEntity.ok(politicianService.getAllPoliticiansByNameDesc());
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}

	}

	/**
	 * Displays all database politicians in the Rest API sorted in ascending order
	 * by name or throws an exception.
	 * 
	 * @return
	 */
	@GetMapping(path = "/asc")
	public ResponseEntity<?> getAllPoliticiansAsc() {
		try {
			return ResponseEntity.ok(politicianService.getAllPoliticiansByNameAsc());
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}

	}

	/**
	 * Gets politician by id and displays it in the Rest API or throws an exception.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "{id}")
	public ResponseEntity<?> getPoliticiansById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(politicianService.getPoliticianDTOById(id));
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}

	}

	/**
	 * Gets all politician with the same amount or more of bills chosen by user and
	 * displays in the Rest API or throws an exception.
	 * 
	 * @param amount
	 * @return
	 */
	@GetMapping(path = "/project/{amount}")
	public ResponseEntity<?> getPoliticiansByProjectAmount(@PathVariable Integer amount) {
		try {
			return ResponseEntity.ok(politicianService.getPoliticianByProjectAmount(amount));
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}

	}

	/**
	 * Updates politician content in the database or throws an exception.
	 * 
	 * @param id
	 * @param dto
	 * @return
	 */
	@PutMapping(path = "{id}", produces = "application/json")
	public ResponseEntity<?> updatePolitician(@PathVariable Long id, @RequestBody PoliticianRegistrationDTO dto) {

		try {
			byte[] imageByte = Base64.getMimeDecoder().decode(dto.getBase64Image());
			politicianService.updatePolitician(imageByte, PoliticianMapper.fromDTO(dto), id);

		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}

		return null;
	}

	/**
	 * Deletes politician from the database or throws an exception.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletePolitician(@PathVariable Long id) {

		try {
			politicianService.deletePolitician(id);

			return ResponseEntity.ok("Deleted with success");

		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError()).body("It was not possible to complete this request.");
		}
	}
}
