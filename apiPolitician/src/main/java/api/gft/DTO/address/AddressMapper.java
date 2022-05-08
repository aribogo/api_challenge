package api.gft.DTO.address;

import api.gft.entity.Address;

public class AddressMapper {

	public static Address fromDTO(AddressDTO dto) {
		
		return new Address(dto.getStreetName(), dto.getNumber(), dto.getComplement(), dto.getZipCode());
	}
	
	public static AddressDTO fromEntity(Address address) {
		
		return new AddressDTO(address.getStreetName(), address.getNumber(), address.getComplement(), address.getZipCode());
	}
	
}
