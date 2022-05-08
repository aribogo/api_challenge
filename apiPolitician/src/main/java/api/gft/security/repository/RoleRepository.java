package api.gft.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.gft.security.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	/**
	 * Gets role by name.
	 * @param name
	 * @return
	 */
	List<Role> findByName(String name);
	
}
