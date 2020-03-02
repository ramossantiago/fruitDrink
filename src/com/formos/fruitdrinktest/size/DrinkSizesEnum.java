package com.formos.fruitdrinktest.size;

public enum DrinkSizesEnum {

	SMALL(1, "Small", 100f), MEDIUM(2, "Medium", 200f), LARGE(3, "Large", 300f);

	private final Integer index;
	private final String sizeName;
	private final Float size;

	private DrinkSizesEnum(Integer index, String sizeName, Float size) {
		this.index = index;
		this.sizeName = sizeName;
		this.size = size;
	}

	public Float getSize() {
		return size;
	}

	public String getSizeName() {
		return sizeName;
	}

	public String getFullSizeName() {
		return sizeName + ": " + size;
	}

	public Integer getIndex() {
		return index;
	}

}
