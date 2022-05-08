package api.gft.DTO.politician;

import java.io.Serializable;

import api.gft.DTO.image.ImageDTO;

public class PoliticianDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;

	private String position;

	private String partyDTO;

	private Integer bill;

	private Integer process;

	private ImageDTO image;

	private Boolean isLeader;
	
	private String base64Image;

	public PoliticianDTO(String name, String position, String partyDTO, Integer bill, Integer process,
			ImageDTO image, Boolean isLeader) {
		this.name = name;
		this.position = position;
		this.partyDTO = partyDTO;
		this.bill = bill;
		this.process = process;
		this.image = image;
		this.isLeader = isLeader;
	}
	

	public PoliticianDTO() {
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPartyDTO() {
		return partyDTO;
	}

	public void setPartyDTO(String partyDTO) {
		this.partyDTO = partyDTO;
	}

	public Integer getBill() {
		return bill;
	}


	public void setBill(Integer bill) {
		this.bill = bill;
	}


	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	public Boolean getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Boolean isLeader) {
		this.isLeader = isLeader;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	

	


}
