package api.gft.DTO.lawSuit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LawSuitRegistrationDTO {

	@NotEmpty(message = "Can not be blank")
	private String description;

	@NotEmpty(message = "Can not be blank")
	private String status;

	@NotNull(message = "Can not be null")
	private Long politicianId;

	public LawSuitRegistrationDTO(String description, String status, Long politicianId) {
		this.description = description;
		this.status = status;
		this.politicianId = politicianId;
	}

	public LawSuitRegistrationDTO() {
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

	public Long getPoliticianId() {
		return politicianId;
	}

	public void setPoliticianId(Long politicianId) {
		this.politicianId = politicianId;
	}
	
	
}
