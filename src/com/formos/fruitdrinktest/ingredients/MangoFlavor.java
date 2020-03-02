package com.formos.fruitdrinktest.ingredients;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.interfaces.IFlavor;
import com.formos.fruitdrinktest.stock.HardCodedStock;

public class MangoFlavor extends Ingredients implements IFlavor {

	private static final Float FACTOR_GRAMS_MILLILITERS = 1.4f;

	public MangoFlavor() {
		setName("Mango");
		setUnits("g");
		setCostByUnit(HardCodedStock.MANGO_COST_BY_UNIT);
	}

	@Override
	public Float getRequiredAmountForDrink(Float millilitersSize) {
		return millilitersSize * FACTOR_GRAMS_MILLILITERS;
	}

	@Override
	public Ingredients clone() {
		MangoFlavor mangoFlavor = new MangoFlavor();
		mangoFlavor.setName(this.getName());
		mangoFlavor.setStock(this.getStock());
		mangoFlavor.setUnits(this.getUnits());
		return mangoFlavor;
	}

}
