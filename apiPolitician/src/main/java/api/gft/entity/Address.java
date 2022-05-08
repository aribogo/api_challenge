package api.gft.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Address {

	@Column(name= "street_name")
	@NotEmpty(message = "Can not be blank")
	private String streetName;
	
	@Column(name= "number")
	@NotEmpty(message = "Can not be blank")
	private String number;
	
	@Column(name= "complement")
	private String complement;
	
	@Column(name= "zipcode")
	@NotEmpty(message = "Can not be blank")
	private String zipCode;
	
	
	public Address(String streetName, String number, String complement, String zipCode) {
		this.streetName = streetName;
		this.number = number;
		this.complement = complement;
		this.zipCode = zipCode;
	}

	public Address() {
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
