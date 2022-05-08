package api.gft.DTO.Bill;

import java.util.ArrayList;
import java.util.List;

import api.gft.entity.Bill;
import api.gft.entity.Politician;
import api.gft.entity.StatusBill;
import api.gft.service.PoliticianService;

public class BillMapper {
	

	public static Bill fromDTO(BillRegistrationDTO dto) {
		
		Politician p = new Politician(dto.getPoliticianId());

		return new Bill(null, StatusBill.getEnum(dto.getStatus()), dto.getDescription(), p);
	}

	public static BillDTO fromEntity(Bill lp) {
		
		return new BillDTO(lp.getId(), lp.getDescription(), lp.getStatus().getDisplayValue(), lp.getPolitician().getName(), lp.getPolitician().getParty().getPartyName());

		
	}

	public static List<BillDTO> fromEntityList(List<Bill> listLawProject) {

		List<BillDTO> listDto = new ArrayList<>();

		listLawProject.forEach(l -> listDto.add(BillMapper.fromEntity(l)));

		return listDto;
	}
	
	
}
