package api.gft.DTO.party;

import java.util.ArrayList;
import java.util.List;

import api.gft.entity.Party;

public class PartyMapperDTO {

	public static Party fromDTO(PartyRegistrationDTO dto) {

		return new Party(null, dto.getPartyName(), dto.getPartyInitials());
	}
	
	public static Party fromPartyDTO(PartyDTO dto) {

		return new Party(dto.getId(), dto.getPartyName(), dto.getPartyInitials());
	}

	public static PartyDTO fromEntity(Party party) {

		return new PartyDTO(party.getPartyId(), party.getPartyName(), party.getPartyInitials());

	}

	public static List<PartyDTO> fromEntityList(List<Party> listParty) {

		List<PartyDTO> listDto = new ArrayList<>();

		listParty.forEach(l -> listDto.add(PartyMapperDTO.fromEntity(l)));

		return listDto;
	}

}
