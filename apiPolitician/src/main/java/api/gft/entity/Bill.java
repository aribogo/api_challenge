package api.gft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	@NotNull(message = "Can not be null")
	private Long id;
	
	@Column(name = "description")
	@NotEmpty(message = "Can not be blank")
	private String description;
	
	@Column(name = "status")
	@NotNull(message = "Can not be null")
	private StatusBill status;
	
	@ManyToOne
	@JoinColumn(name="id_politician", nullable=false)
	@NotNull(message = "Can not be null")
	private Politician politician;
	
	
	public Bill(Long id, StatusBill status, String description, Politician politician) {
		this.id = id;
		this.status = status;
		this.description = description;
		this.politician = politician;
	}

	public Bill() {
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusBill getStatus() {
		return status;
	}

	public void setStatus(StatusBill status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Politician getPolitician() {
		return politician;
	}

	public void setPolitician(Politician politician) {
		this.politician = politician;
	}
	

}
