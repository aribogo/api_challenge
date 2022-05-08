package api.gft.DTO.lawSuit;

import java.util.ArrayList;
import java.util.List;

import api.gft.entity.LawSuit;
import api.gft.entity.Politician;
import api.gft.entity.StatusProcess;

public class LawSuitMapper {

	public static LawSuit fromDTO(LawSuitRegistrationDTO dto) {

		Politician p = new Politician(dto.getPoliticianId());

		return new LawSuit(null,  dto.getDescription(), StatusProcess.getEnum(dto.getStatus()), p);
	}

	public static LawSuitDTO fromEntity(LawSuit lp) {

		return new LawSuitDTO(lp.getId(), lp.getDescription(), lp.getStatus().getDisplayValue(),
				lp.getPolitician().getName(), lp.getPolitician().getParty().getPartyName());

	}

	public static List<LawSuitDTO> fromEntityList(List<LawSuit> listProcess) {

		List<LawSuitDTO> listDto = new ArrayList<>();

		listProcess.forEach(l -> listDto.add(LawSuitMapper.fromEntity(l)));

		return listDto;
	}

}
