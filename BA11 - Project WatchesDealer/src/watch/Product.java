package watch;

import brand.Brand;
import brand.CheckBrand;

public class Product {
	private Integer watchID;
	private Integer brandID;
	private String watchName;
	private Integer watchPrice;
	private Integer watchStock;

	public Product(Integer watchID, Integer brandID, String watchName, Integer watchPrice, Integer watchStock) {
		super();
		this.watchID = watchID;
		this.brandID = brandID;
		this.watchName = watchName;
		this.watchPrice = watchPrice;
		this.watchStock = watchStock;
	}

	public Integer getWatchID() {
		return watchID;
	}

	public void setWatchID(Integer watchID) {
		this.watchID = watchID;
	}

	public Integer getBrandID() {
		return brandID;
	}

	public void setBrandID(Integer brandID) {
		this.brandID = brandID;
	}

	public String getWatchName() {
		return watchName;
	}

	public void setWatchName(String watchName) {
		this.watchName = watchName;
	}

	public Integer getWatchPrice() {
		return watchPrice;
	}

	public void setWatchPrice(Integer watchPrice) {
		this.watchPrice = watchPrice;
	}

	public Integer getWatchStock() {
		return watchStock;
	}

	public void setWatchStock(Integer watchStock) {
		this.watchStock = watchStock;
	}
	
	public Brand getBrand() {
		return CheckBrand.getBrand(brandID);
	}

	public String getBrandName() {
		return CheckBrand.getBrand(brandID).getBrandName();
	}

}