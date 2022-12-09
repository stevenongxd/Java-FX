package oop;

public class Bawahan extends Karyawan{
	
	private Integer superiorCode;
	
	public Bawahan(Integer employeeCode, String employeeName, Integer employeeSalary, Integer soldItem, Integer superiorCode) {
		super(employeeCode, employeeName, employeeSalary, soldItem);
		this.superiorCode = superiorCode;
	}

	public Integer getSuperiorCode() {
		return superiorCode;
	}

	public void setSuperiorCode(Integer superiorCode) {
		this.superiorCode = superiorCode;
	}

	@Override
	public void calculateSalary() {
		Integer salary = (getEmployeeSalary() + (getSoldItem() * 10000));
		System.out.println("My Salary: " + salary);
	}


}
