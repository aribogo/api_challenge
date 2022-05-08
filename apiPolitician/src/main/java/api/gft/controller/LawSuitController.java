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

import api.gft.DTO.lawSuit.LawSuitDTO;
import api.gft.DTO.lawSuit.LawSuitMapper;
import api.gft.DTO.lawSuit.LawSuitRegistrationDTO;
import api.gft.entity.LawSuit;
import api.gft.exception.PoliticianErrorCode;
import api.gft.exception.PoliticianException;
import api.gft.service.LawSuitService;

@Controller
@RequestMapping("v1/lawsuit")
public class LawSuitController {

	private final LawSuitService lawSuitService;

	public LawSuitController(LawSuitService lawSuitService) {
		this.lawSuitService = lawSuitService;
	}

	/**
	 * Gets DTO with JSON information given in the API and saves it's content in the
	 * database or throws an exception.
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveLawSuit(@RequestBody @Validated LawSuitRegistrationDTO dto) {

		try {
			LawSuit LawSuit = lawSuitService.saveLawSuit(LawSuitMapper.fromDTO(dto));
			return ResponseEntity.ok(LawSuitMapper.fromEntity(LawSuit));
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError())
					.body("It was not possible to complete this request.");
		}

	}

	/**
	 * Displays all database law suits in the Rest API or throws an exception.
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllLawSuits() {

		try {
			return ResponseEntity.ok(LawSuitMapper.fromEntityList(lawSuitService.getAllLawSuit()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError())
					.body("It was not possible to complete this request.");
		}

	}

	/**
	 * Gets law suit by id and displays it in the Rest API or throws an exception.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> getLawSuitById(@PathVariable Long id) {

		try {
			LawSuitDTO dto = LawSuitMapper.fromEntity(lawSuitService.getLawSuitById(id));
			return ResponseEntity.ok(dto);
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError())
					.body("It was not possible to complete this request.");
		}

	}

	/**
	 * Updates law suit in the database or throws an exception.
	 * 
	 * @param id
	 * @param dto
	 * @return
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> updateLawSuit(@PathVariable Long id, @RequestBody LawSuitRegistrationDTO dto) {

		try {
			LawSuit LawSuit = LawSuitMapper.fromDTO(dto);
			LawSuit updatedLawSuit = lawSuitService.updateLawSuit(LawSuit, id);
			LawSuitDTO LawSuitDto = LawSuitMapper.fromEntity(updatedLawSuit);

			return ResponseEntity.ok(LawSuitDto);

		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError())
					.body("It was not possible to complete this request.");
		}
	}

	/**
	 * Deletes law suit from the database or throws an exception.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteLawSuit(@PathVariable Long id) {

		try {

			lawSuitService.deleteLawSuit(id);
			return ResponseEntity.ok().build();

		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError())
					.body("It was not possible to complete this request.");
		}
	}

}
