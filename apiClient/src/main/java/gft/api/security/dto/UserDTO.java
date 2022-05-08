package gft.api.security.dto;

public class UserDTO {

	private String username;
	
	public UserDTO(String username) {
		this.username = username;
	}

	public UserDTO() {
		super();
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
