package model;

public class Registrant {
	private Integer ID;
	private String name;
	private String address;
	private Integer age;
	private Double ipk;
	
	public Registrant(Integer ID, String name, String address, Integer age, Double ipk) {
		this.ID = ID;
		this.name = name;
		this.address = address;
		this.age = age;
		this.ipk = ipk;
	}

	public Integer getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public Integer getAge() {
		return age;
	}

	public Double getIpk() {
		return ipk;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setIpk(Double ipk) {
		this.ipk = ipk;
	}
	
	
	
	

}
