package api.gft.DTO.politician;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import api.gft.DTO.address.AddressDTO;
import api.gft.DTO.image.ImageDTO;

public class PoliticianRegistrationDTO {

	@NotEmpty(message = "Can not be blank")
	private String name;

	@NotEmpty(message = "Can not be blank")
	@CPF(message = "Invalida number.")
	private String cpf;

	@NotNull(message = "Can not be null")
	private String phoneNumber;

	@NotEmpty(message = "Can not be blank")
	private String position;

	private AddressDTO address;

	@NotNull(message = "Can not be null")
	private Long partyId;

	
	private ImageDTO image;

	@NotNull(message = "Can not be null")
	private Boolean isLeader;
	
	private String base64Image;

	public PoliticianRegistrationDTO(String name, String cpf, String phoneNumber, String position, AddressDTO address,
			Long partyDTO, ImageDTO image, Boolean isLeader) {
		this.name = name;
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.position = position;
		this.address = address;
		this.partyId = partyDTO;
		this.image = image;
		this.isLeader = isLeader;
	}
	
	
	public PoliticianRegistrationDTO(String name, String cpf, String phoneNumber, String position, AddressDTO address, Long partyId,
			Long partyDTO, ImageDTO image, Boolean isLeader) {
		this.name = name;
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.position = position;
		this.address = address;
		this.partyId = partyDTO;
		this.image = image;
		this.isLeader = isLeader;
		this.partyId = partyId;
	}
	

	public PoliticianRegistrationDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	public Boolean getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Boolean isLeader) {
		this.isLeader = isLeader;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}


	public Long getPartyId() {
		return partyId;
	}


	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	
	

}
