package com.formos.fruitdrinktest.ingredients;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.interfaces.IFlavor;
import com.formos.fruitdrinktest.stock.HardCodedStock;

public class BananaFlavor extends Ingredients implements IFlavor {

	private static final Float FACTOR_GRAMS_MILLILITERS = 1.2f;

	public BananaFlavor() {
		setName("Banana");
		setUnits("g");
		setCostByUnit(HardCodedStock.BANANA_COST_BY_UNIT);
	}

	@Override
	public Float getRequiredAmountForDrink(Float millilitersSize) {
		return millilitersSize * FACTOR_GRAMS_MILLILITERS;
	}

	@Override
	public Ingredients clone() {
		BananaFlavor bananaFlavor = new BananaFlavor();
		bananaFlavor.setName(this.getName());
		bananaFlavor.setStock(this.getStock());
		bananaFlavor.setUnits(this.getUnits());
		return bananaFlavor;
	}

}
