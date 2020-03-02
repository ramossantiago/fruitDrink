package com.formos.fruitdrinktest.recipe;

import static com.formos.fruitdrinktest.util.NumberUtil.getRoundedStockValue;

import java.util.ArrayList;
import java.util.List;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.ingredients.CondensedMilk;
import com.formos.fruitdrinktest.ingredients.Ice;
import com.formos.fruitdrinktest.ingredients.Sugar;
import com.formos.fruitdrinktest.interfaces.IFlavor;

public class BlendedFruitDrinkRecipe {

	private static final float FACTOR_DRINKSIZE_FRUIT = 0.5f;
	private static final float FACTOR_DRINKSIZE_ICE = 0.3f;
	private static final float FACTOR_DRINKSIZE_CONDENSEDMILK = 0.2f;
	private static final float FACTOR_DRINKSIZE_SUGAR = 0.08f;

	public static List<Ingredients> getRequiredIngredients(float drinkSize, List<Ingredients> flavors) {

		List<Ingredients> requiredIngredientsList = new ArrayList<>();

		float requeridBlendedFruit = drinkSize * FACTOR_DRINKSIZE_FRUIT;
		float requeridBlendedFruitForEachFruit = requeridBlendedFruit / flavors.size();

		for (Ingredients flavor : flavors) {
			flavor.setStock(
					getRoundedStockValue(((IFlavor) flavor).getRequiredAmountForDrink(requeridBlendedFruitForEachFruit)));
			requiredIngredientsList.add(flavor);
		}

		Ingredients ice = new Ice();
		ice.setStock(getRoundedStockValue(drinkSize * FACTOR_DRINKSIZE_ICE));
		requiredIngredientsList.add(ice);

		Ingredients condensedMilk = new CondensedMilk();
		condensedMilk.setStock(getRoundedStockValue(drinkSize * FACTOR_DRINKSIZE_CONDENSEDMILK));
		requiredIngredientsList.add(condensedMilk);

		Ingredients sugar = new Sugar();
		sugar.setStock(getRoundedStockValue(drinkSize * FACTOR_DRINKSIZE_SUGAR));
		requiredIngredientsList.add(sugar);

		return requiredIngredientsList;
	}

}
