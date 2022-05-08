package gft.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import gft.api.DTO.politician.PoliticianDTO;
import gft.api.service.ConsumerService;

@RestController
@RequestMapping("v1")
public class ConsumerController {

	private final ConsumerService consumerService;

	public ConsumerController(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}

	/**
	 * Displays all parties from politician API.
	 * 
	 * @return
	 */
	@GetMapping("/party")
	public ResponseEntity<?> getAllParties() {
		try {
			return ResponseEntity.ok(consumerService.getAllParties());
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	/**
	 * Displays party by id from politician API.
	 * 
	 * @return
	 */
	@GetMapping("/party/{id}")
	public ResponseEntity<?> getPartyById(@PathVariable Long id) {

		try {
			return ResponseEntity.ok(consumerService.getPartyById(id));
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	/**
	 * Displays all politician from politician API in descending order.
	 * 
	 * @return
	 */
	@GetMapping(path = "/politician/desc")
	public ResponseEntity<?> getAllPoliticiansDesc() {
		try {
			return ResponseEntity.ok(consumerService.getAllPoliticiansDesc());
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	/**
	 * Displays all politician from politician API in ascending order.
	 * 
	 * @return
	 */
	@GetMapping(path = "/politician/asc")
	public ResponseEntity<?> getAllPoliticiansAsc() {
		try {
			return ResponseEntity.ok(consumerService.getAllPoliticiansAsc());
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	/**
	 * Displays politician by id from politician API.
	 * 
	 * @return
	 */
	@GetMapping(path = "/politician/{id}")
	public ResponseEntity<?> getPoliticiansById(@PathVariable Long id) {
		try {
			PoliticianDTO dto = consumerService.getPoliticianById(id);
			return ResponseEntity.ok(dto);
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	/**
	 * Displays all politician that have the given number of projects or more.
	 * 
	 * @return
	 */
	@GetMapping(path = "/politician/project/{amount}")
	public ResponseEntity<?> getPoliticiansByProjectAmount(@PathVariable Integer amount) {
		try {
			return ResponseEntity.ok(consumerService.getPoliticiansByProjectAmount(amount));
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	/**
	 * Displays all law suits from politician API.
	 * 
	 * @return
	 */
	@GetMapping("/lawsuit")
	public ResponseEntity<?> getAllLawSuits() {

		try {
			return ResponseEntity.ok(consumerService.getAllLawSuit());
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	/**
	 * Displays law suit by id from politician API.
	 * 
	 * @return
	 */
	@GetMapping("/lawsuit/{id}")
	public ResponseEntity<?> getLawSuitById(@PathVariable Long id) {

		try {
			return ResponseEntity.ok(consumerService.getLawSuitById(id));
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	/**
	 * Displays bills from politician API.
	 * 
	 * @return
	 */
	@GetMapping("/bills")
	public ResponseEntity<?> getAllBills() {
		try {
			return ResponseEntity.ok(consumerService.getAllBills());
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	/**
	 * Displays bill by id from politician API.
	 * 
	 * @return
	 */
	@GetMapping("/bills/{id}")
	public ResponseEntity<?> getBillsById(@PathVariable Long id) {

		try {
			return ResponseEntity.ok(consumerService.getBillById(id));
		} catch (WebClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).body(getCustomMessageError(e.getRawStatusCode()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("It was not possible to get this information.");
		}

	}

	private String getCustomMessageError(int codeError) {
		String message = "Error";

		switch (codeError) {
		case 254:
			message = "Not found!";
			break;
		case 249:
			message = "Uncaught error!";
			break;
		case 250:
			message = "This person can not be a leader because the party already has a leader.";
			break;
		case 251:
			message = "Invalid CPF.";
			break;
		case 252:
			message = "There is a problem with the file.";
			break;
		case 253:
			message = "It was not possible to complete this request.";
		case 255:
			message = "Failed to delete. Verify if the information is correct.";
		default:
			break;
		}

		return message;
	}
}
