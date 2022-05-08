package api.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import api.gft.entity.LawSuit;

@Repository
public interface LawSuitRepository extends JpaRepository<LawSuit, Long> {

	
}
