package api.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.gft.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
