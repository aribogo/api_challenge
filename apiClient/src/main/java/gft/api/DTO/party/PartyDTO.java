package gft.api.DTO.party;

public class PartyDTO {

	private Long id;
	
	private String partyName;
	
	private String partyInitials;
	

	public PartyDTO(Long id, String partyName, String partyInitials) {
		this.id = id;
		this.partyName = partyName;
		this.partyInitials = partyInitials;
	}

	public PartyDTO() {
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
