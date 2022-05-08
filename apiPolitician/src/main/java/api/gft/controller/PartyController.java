package api.gft.controller;

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

import api.gft.DTO.party.PartyDTO;
import api.gft.DTO.party.PartyMapperDTO;
import api.gft.DTO.party.PartyRegistrationDTO;
import api.gft.entity.Party;
import api.gft.exception.PoliticianErrorCode;
import api.gft.exception.PoliticianException;
import api.gft.service.PartyService;

@RestController
@RequestMapping("v1/party")
public class PartyController {

	private final PartyService partyService;

	public PartyController(PartyService partyService) {
		this.partyService = partyService;
	}

	/**
	 * Gets DTO with JSON information given in the API and saves it's content in the
	 * database or throws an exception.
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveParty(@RequestBody @Validated PartyRegistrationDTO dto) {

		try {
			Party party = partyService.saveParty(PartyMapperDTO.fromDTO(dto));
			return ResponseEntity.ok(PartyMapperDTO.fromEntity(party));
		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError())
					.body("It was not possible to complete this request.");
		}

	}

	/**
	 * Displays all database parties in the Rest API or throws an exception.
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllParties() {

		try {
			return ResponseEntity.ok(PartyMapperDTO.fromEntityList(partyService.getAllParties()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError())
					.body("It was not possible to complete this request.");
		}

	}

	/**
	 * Gets party by id and displays it in the Rest API or throws an exception.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> getPartyById(@PathVariable Long id) {

		try {
			PartyDTO dto = PartyMapperDTO.fromEntity(partyService.getPartyById(id));
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
	 * Updates party in the database or throws an exception.
	 * 
	 * @param id
	 * @param dto
	 * @return
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> updateParty(@PathVariable Long id, @RequestBody PartyRegistrationDTO dto) {

		try {
			Party party = PartyMapperDTO.fromDTO(dto);
			partyService.updateParty(party, id);
			PartyDTO partyDto = PartyMapperDTO.fromEntity(party);

			return ResponseEntity.ok(partyDto);

		} catch (PoliticianException e) {
			return ResponseEntity.status(e.getPoliticianErrorCode().getCodeError()).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(PoliticianErrorCode.UNCAUGHTERROR.getCodeError())
					.body("It was not possible to complete this request.");
		}
	}

	/**
	 * Deletes party by id from the database or throws an exception.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteParty(@PathVariable Long id) {

		try {

			partyService.deleteParty(id);
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
