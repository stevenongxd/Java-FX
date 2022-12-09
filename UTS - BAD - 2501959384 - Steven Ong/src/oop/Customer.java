package oop;

public class Customer {

	private Integer customerCode;
	private String customerName;
	private Integer moneySpent;
	private String memberType;
	
	public Customer(Integer customerCode, String customerName, Integer moneySpent, String memberType) {
		super();
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.moneySpent = moneySpent;
		this.memberType = memberType;
	}

	public Integer getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(Integer customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getMoneySpent() {
		return moneySpent;
	}

	public void setMoneySpent(Integer moneySpent) {
		this.moneySpent = moneySpent;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
}
