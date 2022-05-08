package gft.api.security.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gft.api.security.entity.User;

public class UserMapper {

	/**
	 * Sets user based on user's DTO content.
	 * @param dto
	 * @return
	 */
	public static User fromDTO(UserRegistrationDTO dto) {


		return new User(null, dto.getUsername(), new BCryptPasswordEncoder().encode(dto.getPassword()));
	}

	/**
	 * Sets user's DTO based on user's content.
	 * @param dto
	 * @return
	 */
	public static UserDTO fromEntity(User user) {

		return new UserDTO(user.getUsername());

	}
	
}
