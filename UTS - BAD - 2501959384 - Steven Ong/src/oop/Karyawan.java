package oop;

public abstract class Karyawan {
	private Integer employeeCode;
	private String employeeName;
	private Integer employeeSalary;
	private Integer soldItem;
	
	public Karyawan(Integer employeeCode, String employeeName, Integer employeeSalary, Integer soldItem) {
		super();
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.soldItem = soldItem;
	}

	public Integer getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(Integer employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Integer employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public Integer getSoldItem() {
		return soldItem;
	}

	public void setSoldItem(Integer soldItem) {
		this.soldItem = soldItem;
	}
	
	public abstract void calculateSalary();
	
}
