package oop;

public class Atasan extends Karyawan{

	private Integer jumlahBawahan;
	
	public Atasan(Integer employeeCode, String employeeName, Integer employeeSalary, Integer soldItem, Integer jumlahBawahan) {
		super(employeeCode, employeeName, employeeSalary, soldItem);
		this.jumlahBawahan = jumlahBawahan;
	}

	public Integer getJumlahBawahan() {
		return jumlahBawahan;
	}

	public void setJumlahBawahan(Integer jumlahBawahan) {
		this.jumlahBawahan = jumlahBawahan;
	}

	@Override
	public void calculateSalary() {
		Integer salary = (getEmployeeSalary() + (getJumlahBawahan() * getSoldItem()) * 20000);
		System.out.println("My Salary: " + salary);
	}



}
