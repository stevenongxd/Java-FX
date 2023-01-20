package main;

public class Mahasiswa {
	
	private String nim;
	private String name;
	private Integer age;
	
	public Mahasiswa(String nim, String name, Integer age) {
		super();
		this.nim = nim;
		this.name = name;
		this.age = age;
	}

	public String getNim() {
		return nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
