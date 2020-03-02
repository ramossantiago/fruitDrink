package com.formos.fruitdrinktest.abstracts;

import com.formos.fruitdrinktest.interfaces.IIngredient;

public abstract class Ingredients implements IIngredient {

	private Float stock = 0f;
	private String name;
	private String units;
	private Float costByUnit = 0f;

	@Override
	public void addStock(Float amount) {
		stock += amount;
	}

	@Override
	public void reduceStock(Float amount) {
		stock -= amount;
	}

	@Override
	public String getCompleteStock() {
		return this.stock + this.units;
	}

	public Float getStock() {
		return stock;
	}

	public void setStock(Float stock) {
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Float getCostByUnit() {
		return costByUnit;
	}

	public void setCostByUnit(Float costByUnit) {
		this.costByUnit = costByUnit;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Ingredients)) {
			return false;
		}

		Ingredients ingredient = (Ingredients) obj;

		return this.name.equals(ingredient.getName());
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

	public abstract Ingredients clone();

}
