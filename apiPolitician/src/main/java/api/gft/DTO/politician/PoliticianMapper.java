package api.gft.DTO.politician;

import api.gft.DTO.address.AddressMapper;
import api.gft.DTO.image.ImageMapper;
import api.gft.entity.Party;
import api.gft.entity.Politician;
import api.gft.entity.Position;

public class PoliticianMapper {

	public static Politician fromDTO(PoliticianRegistrationDTO dto) {

		Party party = new Party();
		party.setPartyId(dto.getPartyId());

		return new Politician(null, dto.getName(), dto.getCpf(), dto.getPhoneNumber(),
				Position.getEnum(dto.getPosition()), AddressMapper.fromDTO(dto.getAddress()), party,
				ImageMapper.fromDTO(dto.getImage()), dto.getIsLeader());

	}

	public static PoliticianAdminUserDTO fromEntity(Politician politician) {

		return new PoliticianAdminUserDTO(politician.getName(), politician.getPosition().getDisplayValue(),
				politician.getParty().getPartyName(), politician.getTotalBill(), politician.getTotalLawSuit() ,
				ImageMapper.fromEntity(politician.getImage()), politician.getIsLeader(), politician.getCpf(), politician.getPhoneNumber(),
				AddressMapper.fromEntity(politician.getAddress()));
	}

	public static PoliticianDTO fromEntitygGeneralUsers(Politician politician) {

		return new PoliticianDTO(politician.getName(), politician.getPosition().getDisplayValue(),
				politician.getParty().getPartyName(), politician.getLawproject().size(), politician.getProcess().size(),
				ImageMapper.fromEntity(politician.getImage()), politician.getIsLeader());
	}

}
