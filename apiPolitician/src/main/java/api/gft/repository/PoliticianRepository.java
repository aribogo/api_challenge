package api.gft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.gft.entity.Politician;


@Repository
public interface PoliticianRepository extends JpaRepository<Politician, Long>{

	/**
	 * Gets all database politician and sorts them in descending order by name.
	 * @return Politician
	 */
	@Query(value = "Select p from Politician as p order by p.name desc NULLS LAST")
	public List<Politician> findAllDesc();
	
	/**
	 * Gets all database politician and sorts them ascending by name.
	 * @return Politician
	 */
	@Query(value = "Select p from Politician as p order by p.name NULLS LAST")
	public List<Politician> findAllAsc();
	
}
