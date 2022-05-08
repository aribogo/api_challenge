package api.gft.DTO.Bill;

public class BillDTO {

	private Long id;

	private String description;

	private String status;

	private String namePolitician;
	
	private String nameParty;

	public BillDTO(Long id, String description, String status, String namePolitician, String nameParty) {
		this.id = id;
		this.description = description;
		this.status = status;
		this.namePolitician = namePolitician;
		this.nameParty = nameParty;
	}

	public BillDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNamePolitician() {
		return namePolitician;
	}

	public void setNamePolitician(String namePolitician) {
		this.namePolitician = namePolitician;
	}

	public String getNameParty() {
		return nameParty;
	}

	public void setNameParty(String nameParty) {
		this.nameParty = nameParty;
	}

}
