package gft.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import gft.api.DTO.lawProject.BillDTO;
import gft.api.DTO.lawSuit.LawSuitDTO;
import gft.api.DTO.party.PartyDTO;
import gft.api.DTO.politician.PoliticianDTO;
import reactor.core.publisher.Mono;

@Service
public class ConsumerService {

	@Autowired
	private WebClient webClientPolitician;

	/**
	 * Gets all parties from politician API.
	 * @return
	 */
	public List<PartyDTO> getAllParties() {

		Mono<List<PartyDTO>> monoParty = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/party").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<PartyDTO>>() {
				});

		List<PartyDTO> listDTO = monoParty.block();

		return listDTO;

	}

	/**
	 * Gets party by id from politician API.
	 * @return
	 */
	public PartyDTO getPartyById(Long id) {

		Mono<PartyDTO> monoParty = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/party/{id}", id).retrieve()
				.bodyToMono(new ParameterizedTypeReference<PartyDTO>() {
				});
		
		PartyDTO dto = monoParty.block();
		
		return dto;
	}
	
	/**
	 * Gets all politician from politician API in descending order.
	 * @return
	 */
	public List<PoliticianDTO> getAllPoliticiansDesc() {

		Mono<List<PoliticianDTO>> monoPolitician = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/politician/desc").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<PoliticianDTO>>() {
				});

		List<PoliticianDTO> listDTO = monoPolitician.block();

		return listDTO;

	}

	/**
	 * Gets all politician from politician API in ascending order.
	 * @return
	 */
	public List<PoliticianDTO> getAllPoliticiansAsc() {

		Mono<List<PoliticianDTO>> monoPolitician = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/politician/asc").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<PoliticianDTO>>() {
				});

		List<PoliticianDTO> listDTO = monoPolitician.block();

		return listDTO;

	}
	
	/**
	 * Gets politician by id from politician API.
	 * @return
	 */
	public PoliticianDTO getPoliticianById(Long id) {

		Mono<PoliticianDTO> monoPolitician = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/politician/{id}", id).retrieve()
				.bodyToMono(new ParameterizedTypeReference<PoliticianDTO>() {
				});
		
		PoliticianDTO dto = monoPolitician.block();
		
		return dto;
	}
	
	/**
	 * Gets all politician that have the given number of projects or more.
	 * @return
	 */
	public List<PoliticianDTO> getPoliticiansByProjectAmount(Integer amount) {

		Mono<List<PoliticianDTO>> monoPolitician = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/politician/project/{amount}", amount).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<PoliticianDTO>>() {
				});

		List<PoliticianDTO> listDTO = monoPolitician.block();

		return listDTO;

	}

	/**
	 * Gets all law suits from politician API.
	 * @return
	 */
	public List<LawSuitDTO> getAllLawSuit() {
		Mono<List<LawSuitDTO>> monoLawSuit = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/lawsuit").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<LawSuitDTO>>() {
				});

		List<LawSuitDTO> listDTO = monoLawSuit.block();

		return listDTO;
	}

	/**
	 * Gets law suit by id from politician API.
	 * @return
	 */
	public LawSuitDTO getLawSuitById(Long id) {
		Mono<LawSuitDTO> monoLawSuit = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/lawsuit/{id}", id).retrieve()
				.bodyToMono(new ParameterizedTypeReference<LawSuitDTO>() {
				});

		LawSuitDTO dto = monoLawSuit.block();

		return dto;
	}

	/**
	 * Gets bills from politician API.
	 * @return
	 */
	public List<BillDTO> getAllBills() {
		Mono<List<BillDTO>> monoLawProject = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/bill").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<BillDTO>>() {
				});

		List<BillDTO> listDTO = monoLawProject.block();

		return listDTO;
	}

	/**
	 * Gets bill by id from politician API.
	 * @return
	 */
	public BillDTO getBillById(Long id) {
		Mono<BillDTO> monoBill = this.webClientPolitician
				.method(HttpMethod.GET)
				.uri("/v1/bill/{id}", id).retrieve()
				.bodyToMono(new ParameterizedTypeReference<BillDTO>() {
				});

		BillDTO dto = monoBill.block();

		return dto;
	}
	
	

}
