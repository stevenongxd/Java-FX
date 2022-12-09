package oop;

public class Item {
	private Integer itemQuantity;
	private String itemName;
	private Integer itemPrice;
	
	public Item(Integer itemQuantity, String itemName, Integer itemPrice) {
		super();
		this.itemQuantity = itemQuantity;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
