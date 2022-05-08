package api.gft.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import api.gft.entity.validator.IsLeaderValidation;

@Entity
@Table(name = "tb_politician")
@IsLeaderValidation(message = "This person is not allowed to be a party leader.")
public class Politician {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	@NotNull(message = "Can not be null")
	private Long id;
	
	@Column(name = "name")
	@NotEmpty(message = "Can not be blank")
	private String name;
	
	@CPF
	@Column(name = "cpf")
	@NotEmpty
	private String cpf;
	
	@Column(name = "phone_number")
	@NotNull(message = "Can not be null")
	private String phoneNumber;
	
	@Column(name = "position")
	@NotNull(message = "Can not be null")
	private Position position;
	
	@Embedded
	private Address address;
	
	@ManyToOne
	@JoinColumn(name="id_party", nullable=false)
	@NotNull(message = "Can not be null")
	private Party party;
	
	@OneToMany(mappedBy = "politician")
	private Set<Bill> lawproject;
	
	@OneToMany(mappedBy = "politician")
	private Set<LawSuit> process;
	
	@Embedded
	private Image image;
	
	@Column(name = "is_leader")
	@NotNull(message = "Can not be null")
	private Boolean isLeader;

	public Politician(Long id, String name, String cpf, String phoneNumber, Position position, Address address,
			Set<Bill> lawproject, Set<LawSuit> process, Image image, Boolean isLeader) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.position = position;
		this.address = address;
		this.lawproject = lawproject;
		this.process = process;
		this.image = image;
		this.isLeader = isLeader;
	}

	public Politician(Long id, String name, @CPF String cpf, String phoneNumber, Position position, Address address,
			Party party, Image image, Boolean isLeader) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.position = position;
		this.address = address;
		this.party = party;
		this.image = image;
		this.isLeader = isLeader;
	}

	public Politician(Long id) {
		this.id = id;
	}

	public Politician() {

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Boolean getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Boolean isLeader) {
		this.isLeader = isLeader;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<Bill> getLawproject() {
		return lawproject;
	}


	public void setLawproject(Set<Bill> lawproject) {
		this.lawproject = lawproject;
	}


	public Set<LawSuit> getProcess() {
		return process;
	}


	public void setProcess(Set<LawSuit> process) {
		this.process = process;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@Transient
	public int getTotalBill() {
		if(getLawproject() != null) {
			return lawproject.size();
		} else {
			return 0;
		}
	}
	
	@Transient
	public int getTotalLawSuit() {
		if(getProcess() != null) {
			return lawproject.size();
		} else {
			return 0;
		}
	}

	
	
		
}
