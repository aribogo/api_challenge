package api.gft.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.gft.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Gets user by username.
	 * @param username
	 * @return
	 */
	Optional<User> findByUsername(String email);
	
}
