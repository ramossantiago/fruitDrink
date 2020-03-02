package com.formos.fruitdrinktest.ingredients;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.stock.HardCodedStock;

public class CondensedMilk extends Ingredients {

	public CondensedMilk() {
		setName("Condensed Milk");
		setUnits("ml");
		setCostByUnit(HardCodedStock.CONDENSED_MILK_COST_BY_UNIT);
	}

	@Override
	public Ingredients clone() {
		CondensedMilk condensedMilk = new CondensedMilk();
		condensedMilk.setName(this.getName());
		condensedMilk.setStock(this.getStock());
		condensedMilk.setUnits(this.getUnits());
		return condensedMilk;
	}

}
