package api.gft.DTO.politician;

import api.gft.DTO.address.AddressDTO;
import api.gft.DTO.image.ImageDTO;

public class PoliticianAdminUserDTO extends PoliticianDTO {


	private static final long serialVersionUID = 1L;

	private String cpf;

	private String phoneNumber;

	private AddressDTO address;

	public PoliticianAdminUserDTO(String name, String position, String partyDTO, Integer bill, Integer process,
			ImageDTO image, Boolean isLeader, String cpf, String phoneNumber, AddressDTO address) {
		super(name, position, partyDTO, bill, process, image, isLeader);
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	

	public PoliticianAdminUserDTO() {
	}



	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	
	}
