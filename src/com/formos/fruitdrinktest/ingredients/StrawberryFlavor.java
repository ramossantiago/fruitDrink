package com.formos.fruitdrinktest.ingredients;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.interfaces.IFlavor;
import com.formos.fruitdrinktest.stock.HardCodedStock;

public class StrawberryFlavor extends Ingredients implements IFlavor {

	private static final Float FACTOR_GRAMS_MILLILITERS = 1f;

	public StrawberryFlavor() {
		setName("Strawberry");
		setUnits("g");
		setCostByUnit(HardCodedStock.STRAWBERRY_COST_BY_UNIT);
	}

	@Override
	public Float getRequiredAmountForDrink(Float millilitersSize) {
		return millilitersSize * FACTOR_GRAMS_MILLILITERS;
	}

	@Override
	public Ingredients clone() {
		StrawberryFlavor strawberryFlavor = new StrawberryFlavor();
		strawberryFlavor.setName(this.getName());
		strawberryFlavor.setStock(this.getStock());
		strawberryFlavor.setUnits(this.getUnits());
		return strawberryFlavor;
	}

}
