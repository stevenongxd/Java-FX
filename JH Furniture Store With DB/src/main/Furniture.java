package main;

public class Furniture {
	
	private String furID, furName, furType;
	private Integer furPrice;
	
	public Furniture(String furID, String furName, String furType, Integer furPrice) {
		super();
		this.furID = furID;
		this.furName = furName;
		this.furType = furType;
		this.furPrice = furPrice;
	}

	public String getFurID() {
		return furID;
	}

	public void setFurID(String furID) {
		this.furID = furID;
	}

	public String getFurName() {
		return furName;
	}

	public void setFurName(String furName) {
		this.furName = furName;
	}

	public String getFurType() {
		return furType;
	}

	public void setFurType(String furType) {
		this.furType = furType;
	}

	public Integer getFurPrice() {
		return furPrice;
	}

	public void setFurPrice(Integer furPrice) {
		this.furPrice = furPrice;
	}
	
}
