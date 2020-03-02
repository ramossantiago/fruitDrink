package com.formos.fruitdrinktest.ingredients;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.stock.HardCodedStock;

public class Ice extends Ingredients {

	public Ice() {
		setName("Ice");
		setUnits("ml");
		setCostByUnit(HardCodedStock.ICE_COST_BY_UNIT);
	}

	@Override
	public Ingredients clone() {
		Ice ice = new Ice();
		ice.setName(this.getName());
		ice.setStock(this.getStock());
		ice.setUnits(this.getUnits());
		return ice;
	}

}
