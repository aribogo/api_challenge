package api.gft.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_party")
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@NotNull(message = "Can not be null")
	@Column(name = "id")
	private Long partyId;
	
	@Column(name = "party_name")
	@NotEmpty(message = "Can not be blank")
	private String partyName;
	
	@Column(name = "party_initials")
	@NotEmpty(message = "Can not be blank")
	private String partyInitials;
	
	@OneToMany(mappedBy = "party")
	private Set<Politician> politician;
	
	
	public Party(Long partyid, String partyName, String partyInitials) {
		partyId = partyid;
		this.partyName = partyName;
		this.partyInitials = partyInitials;
	}

	public Party() {
	}
	
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyInitials() {
		return partyInitials;
	}

	public void setPartyInitials(String partyInitials) {
		this.partyInitials = partyInitials;
	}

	public Set<Politician> getPolitician() {
		return politician;
	}

	public void setPolitician(Set<Politician> politician) {
		this.politician = politician;
	}
	
		
}
