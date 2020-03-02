package com.formos.fruitdrinktest.drink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.recipe.BlendedFruitDrinkRecipe;
import com.formos.fruitdrinktest.stock.IngredientsStock;
import com.formos.fruitdrinktest.util.Constant;
import com.formos.fruitdrinktest.util.NumberUtil;

public class Drink {

	private float drinkSize;
	private List<Ingredients> selectedFlavors;
	private float drinkCost;
	private float profit;
	private float drinkValue;

	public Drink() {
	}

	public float getDrinkSize() {
		return drinkSize;
	}

	public void setDrinkSize(float drinkSize) {
		this.drinkSize = drinkSize;
	}

	public float getDrinkCost() {
		return drinkCost;
	}

	public void setDrinkCost(float drinkCost) {
		this.drinkCost = drinkCost;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public void setDrinkValue(float drinkValue) {
		this.drinkValue = drinkValue;
	}

	public float getDrinkValue() {
		return drinkValue;
	}

	public List<Ingredients> getSelectedFlavors() {
		return selectedFlavors;
	}

	public void setSelectedFlavors(List<Ingredients> selectedFlavors) {
		this.selectedFlavors = selectedFlavors;
	}

	public List<Ingredients> getRequiredIngredients() {
		return BlendedFruitDrinkRecipe.getRequiredIngredients(drinkSize, selectedFlavors);
	}

	public Map<Ingredients, Float> checkMissingIngredients() {

		Map<Ingredients, Float> missingIngredients = new HashMap<>();

		List<Ingredients> requiredIngredients = BlendedFruitDrinkRecipe.getRequiredIngredients(drinkSize,
				selectedFlavors);

		List<Ingredients> remainingIngredients = IngredientsStock.getIngredients();

		for (Ingredients necesaryIngredient : requiredIngredients) {
			Float necesaryStock = necesaryIngredient.getStock();
			for (Ingredients remainingingredient : remainingIngredients) {
				Float currentStock = remainingingredient.getStock();
				if (necesaryIngredient.equals(remainingingredient) && (currentStock < necesaryStock)) {
					missingIngredients.put(remainingingredient, necesaryStock);
				}
			}
		}

		return missingIngredients;
	}

	public Float calculateDrinkValue() {
		List<Ingredients> requiredIngredients = getRequiredIngredients();
		Float cost = 0f;

		for (Ingredients required : requiredIngredients) {
			cost += required.getStock() * required.getCostByUnit();
		}
		drinkCost = cost;
		profit = (cost * Constant.PROFIT_PERCENTAJE) / 100;
		drinkValue = NumberUtil.getRoundedCurrencyValue(cost + profit);

		return drinkValue;
	}

}
