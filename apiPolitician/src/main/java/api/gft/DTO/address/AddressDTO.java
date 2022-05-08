package api.gft.DTO.address;

import javax.validation.constraints.NotEmpty;

public class AddressDTO {

	@NotEmpty(message = "Can not be blank")
	private String streetName;

	@NotEmpty(message = "Can not be blank")
	private String number;

	private String complement;

	@NotEmpty(message = "Can not be blank")
	private String zipCode;

	public AddressDTO(String streetName, String number, String complement, String zipCode) {
		this.streetName = streetName;
		this.number = number;
		this.complement = complement;
		this.zipCode = zipCode;
	}
	
	public AddressDTO() {
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
