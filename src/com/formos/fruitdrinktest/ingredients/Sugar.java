package com.formos.fruitdrinktest.ingredients;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.stock.HardCodedStock;

public class Sugar extends Ingredients {

	public Sugar() {
		setName("Sugar");
		setUnits("g");
		setCostByUnit(HardCodedStock.SUGAR_COST_BY_UNIT);
	}

	@Override
	public Ingredients clone() {
		Sugar sugar = new Sugar();
		sugar.setName(this.getName());
		sugar.setStock(this.getStock());
		sugar.setUnits(this.getUnits());
		return sugar;
	}

}
