package com.formos.fruitdrinktest.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.interfaces.IFlavor;
import com.formos.fruitdrinktest.util.Constant;
import com.formos.fruitdrinktest.util.NumberUtil;

public class IngredientsStock {

	private static List<Ingredients> stockIngredients = new ArrayList<>();

	public static void printRemainingStock() {
		StringBuilder currentStock = new StringBuilder("REMAINING STOCK LIST:" + Constant.NEW_LINE);

		stockIngredients.stream().forEach(ingredient -> {
			currentStock.append(ingredient.getName() + ": " + ingredient.getCompleteStock() + Constant.NEW_LINE);
		});

		System.out.println(currentStock.toString());
	}

	public static List<Ingredients> getIngredients() {

		List<Ingredients> remainingIngredients = new ArrayList<>();

		stockIngredients.stream().forEach(ingredient -> {
			remainingIngredients.add(ingredient.clone());
		});

		return remainingIngredients;
	}

	public static void setIngredients(List<Ingredients> ingredients) {
		ingredients.stream().forEach(ingredient -> stockIngredients.add(ingredient.clone()));
	}

	public static void removeStock(List<Ingredients> removeIngredientsStock) {

		stockIngredients.stream().forEach(stockIngredient -> {
			removeIngredientsStock.stream().forEach(removeIngredient -> {
				if (stockIngredient.equals(removeIngredient)) {
					stockIngredient.reduceStock(removeIngredient.getStock());
				}
			});
		});
	}

	public static Map<String, Ingredients> getAvailableFlavors() {
		Map<String, Ingredients> availableFlavors = new HashMap<>();
		int cont = 1;

		for (Ingredients ingredients : IngredientsStock.getIngredients()) {
			if ((ingredients instanceof IFlavor) && NumberUtil.getRoundedStockValue(ingredients.getStock()) > 0.0) {
				availableFlavors.put(String.valueOf(cont), ingredients);
				cont++;
			}
		}
		return availableFlavors;
	}

	public static List<Ingredients> getAllFlavors() {
		List<Ingredients> availableFlavors = new ArrayList<>();

		IngredientsStock.getIngredients().stream().forEach((ingredients) -> {
			if (ingredients instanceof IFlavor) {
				availableFlavors.add(ingredients);
			}
		});

		return availableFlavors;
	}

}
