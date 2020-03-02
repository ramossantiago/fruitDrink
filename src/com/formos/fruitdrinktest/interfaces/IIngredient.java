package com.formos.fruitdrinktest.interfaces;

import com.formos.fruitdrinktest.abstracts.Ingredients;

public interface IIngredient {

	void addStock(Float amount);

	void reduceStock(Float amount);

	String getCompleteStock();

	Ingredients clone();
}
