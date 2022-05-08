package api.gft.DTO.party;

import javax.validation.constraints.NotEmpty;

public class PartyRegistrationDTO {

	@NotEmpty(message = "Can not be blank")
	private String partyName;

	@NotEmpty(message = "Can not be blank")
	private String partyInitials;
	

	public PartyRegistrationDTO(String partyName, String partyInitials) {
		this.partyName = partyName;
		this.partyInitials = partyInitials;
	}

	public PartyRegistrationDTO() {
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyInitials() {
		return partyInitials;
	}

	public void setPartyInitials(String partyInitials) {
		this.partyInitials = partyInitials;
	}

}
