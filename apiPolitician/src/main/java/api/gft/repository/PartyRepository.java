package api.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.gft.entity.Party;
import api.gft.entity.Politician;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long>{

	/**
	 * Get database politician who is a party leader.
	 * @param partyId
	 * @return Politician
	 */
	@Query(value = "SELECT p FROM Politician p WHERE p.party.partyId = :partyId AND p.isLeader = true")
	Politician getLeaderByParty(Long partyId);

}
