package com.formos.fruitdrinktest.run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import com.formos.fruitdrinktest.abstracts.Ingredients;
import com.formos.fruitdrinktest.drink.Drink;
import com.formos.fruitdrinktest.ingredients.BananaFlavor;
import com.formos.fruitdrinktest.ingredients.CondensedMilk;
import com.formos.fruitdrinktest.ingredients.Ice;
import com.formos.fruitdrinktest.ingredients.MangoFlavor;
import com.formos.fruitdrinktest.ingredients.StrawberryFlavor;
import com.formos.fruitdrinktest.ingredients.Sugar;
import com.formos.fruitdrinktest.recipe.BlendedFruitDrinkRecipe;
import com.formos.fruitdrinktest.sales.FruitDrinkSales;
import com.formos.fruitdrinktest.size.DrinkSizesEnum;
import com.formos.fruitdrinktest.stock.HardCodedStock;
import com.formos.fruitdrinktest.stock.IngredientsStock;
import com.formos.fruitdrinktest.util.Constant;

public class Main {

	private static Drink drink;
	private static Map<String, Ingredients> availableFlavors;
	private static List<Ingredients> selectedFlavors;
	private static Boolean checkout;

	public static void main(String[] args) {

		initIngredientsStock();

		boolean showMainMenu = true;
		int opctionSelected;

		while (showMainMenu) {

			checkFutureStock();

			System.out.println("|   FRUITS DRINKS MENU       |");
			System.out.println("|   Options:                 |");
			System.out.println("|    1. Sell drink           |");
			System.out.println("|    2. Check current Stock  |");
			System.out.println("|    3. Daily Sales report   |");
			System.out.println("|    4. Exit                 |");
			System.out.println();
			System.out.println("Enter you option number... ");
			Scanner scanner = new Scanner(System.in);
			opctionSelected = scanner.nextInt();

			switch (opctionSelected) {
			case 1:
				clearConsole();
				createNewDrink();
				showSellMenu();
				sellDrink();
				break;
			case 2:
				clearConsole();
				IngredientsStock.printRemainingStock();
				waitForEnter();
				break;
			case 3:
				clearConsole();
				FruitDrinkSales.printDailySalesReport();
				waitForEnter();
				break;
			case 4:
				System.out.println("Bye bye");
				showMainMenu = false;
				break;
			default:
				System.out.println("Invalid selection try again");
				break;
			}

		}
	}

	private static void initIngredientsStock() {
		List<Ingredients> listIngredients = new ArrayList<>();

		Ingredients strawberryFlavor = new StrawberryFlavor();
		strawberryFlavor.addStock(HardCodedStock.STRAWBERRY_INITIAL_STOCK);
		listIngredients.add(strawberryFlavor);

		Ingredients bananaFlavor = new BananaFlavor();
		bananaFlavor.addStock(HardCodedStock.BANANA_INITIAL_STOCK);
		listIngredients.add(bananaFlavor);

		Ingredients mangoFlavor = new MangoFlavor();
		mangoFlavor.addStock(HardCodedStock.MANGO_INITIAL_STOCK);
		listIngredients.add(mangoFlavor);

		Ingredients condensedMilk = new CondensedMilk();
		condensedMilk.addStock(HardCodedStock.CONDENSED_MILK_INITIAL_STOCK);
		listIngredients.add(condensedMilk);

		Ingredients ice = new Ice();
		ice.addStock(HardCodedStock.ICE_INITIAL_STOCK);
		listIngredients.add(ice);

		Ingredients sugar = new Sugar();
		sugar.addStock(HardCodedStock.SUGAR_INITIAL_STOCK);
		listIngredients.add(sugar);

		IngredientsStock.setIngredients(listIngredients);
	}

	public static void createNewDrink() {
		drink = new Drink();
	}

	public static void showSellMenu() {

		showDrinkSizeMenu();

		if (drink.getDrinkSize() > 0) {
			showFlavorsMenu();
		}

		if (!Objects.isNull(drink.getSelectedFlavors()) && !drink.getSelectedFlavors().isEmpty()) {
			checkout = true;
		} else {
			checkout = false;
		}
	}

	private static void showDrinkSizeMenu() {

		boolean showMenu = true;
		String userInput;
		Integer selectedOption;

		while (showMenu) {
			System.out.println();
			System.out.println("Choise your drink size... or press R to return ");

			for (DrinkSizesEnum size : DrinkSizesEnum.values()) {
				System.out.println(size.getIndex() + ". " + size.getFullSizeName());
			}
			Scanner scanner = new Scanner(System.in);
			userInput = scanner.next();

			if ("R".equals(userInput.trim())) {
				showMenu = false;
				clearConsole();
				break;
			}

			try {
				selectedOption = Integer.valueOf(userInput);
				boolean optionIsCorrect = false;

				for (DrinkSizesEnum size : DrinkSizesEnum.values()) {
					if (size.getIndex().equals(selectedOption)) {
						drink.setDrinkSize(size.getSize());
						optionIsCorrect = true;
						break;
					}
				}

				if (optionIsCorrect) {
					showMenu = false;
					System.out.println("Selected size: " + drink.getDrinkSize());
				} else {
					System.out.println("The selected option is not correct... please try again");
				}

			} catch (NumberFormatException e) {
				System.out.println("The selected option is not correct... please try again");
			}
		}

	}

	private static void showFlavorsMenu() {
		boolean showMenu = true;
		String userInput;
		availableFlavors = IngredientsStock.getAvailableFlavors();

		while (showMenu) {

			System.out.println();
			System.out.println(
					"Enter the flavors selected (enter [1] for flavor1, [1,2] for flavor1+flavor2 )... or press R to return");

			availableFlavors.keySet().stream().forEach((id) -> {
				System.out.println(id + ". " + availableFlavors.get(id).getName());
			});

			Scanner scanner = new Scanner(System.in);
			userInput = scanner.next();

			if ("R".equals(userInput.trim())) {
				showMenu = false;
				clearConsole();
				break;
			}

			List<String> keySelectedFlavors = Arrays.asList(userInput.split(","));
			selectedFlavors = new ArrayList<Ingredients>();

			keySelectedFlavors.stream().forEach((key) -> {
				if (availableFlavors.containsKey(key)) {
					selectedFlavors.add(availableFlavors.get(key));
				} else {
					System.out.println("The selected flavors is not correct... please try again");
				}
			});

			if (selectedFlavors.size() == keySelectedFlavors.size()) {
				showMenu = false;
				drink.setSelectedFlavors(selectedFlavors);
			}
		}

	}

	public static void sellDrink() {

		Float drinkValue = drink.calculateDrinkValue();
		System.out.println("Your drink cost is:  " + drinkValue + " " + Constant.CURRENCY);

		boolean agreeMenu = true;
		String userInput;
		while (agreeMenu) {

			System.out.println("Are you agree y/n... ");
			Scanner scanner = new Scanner(System.in);
			userInput = scanner.next();

			if ("y".equals(userInput.trim())) {
				FruitDrinkSales.addSaleAmount(drinkValue);
				checkout = true;
				agreeMenu = false;
			} else if ("n".equals(userInput.trim())) {
				checkout = false;
				agreeMenu = false;
			}
			clearConsole();
		}

		if (checkout) {
			Map<Ingredients, Float> missingIngredients = drink.checkMissingIngredients();

			if (missingIngredients.isEmpty()) {
				IngredientsStock.removeStock(drink.getRequiredIngredients());
				System.out.println("**** Congratulations you made a SALE **** ");
				waitForEnter();
			} else {
				System.out.println("ERROR You dont have enough stock, please check");
				for (Ingredients missingIngredient : missingIngredients.keySet()) {
					System.out.println(missingIngredient.getName() + " you only have " + missingIngredient.getStock()
							+ missingIngredient.getUnits() + " you need " + missingIngredients.get(missingIngredient));
				}
				waitForEnter();
			}
		}
	}

	public final static void checkFutureStock() {
		List<Ingredients> flavors = IngredientsStock.getAllFlavors();
		List<Ingredients> allMissingIngredients = new ArrayList<>();

		flavors.stream().forEach((flavor) -> {
			allMissingIngredients.addAll(checkLowStock(flavor));
		});

		if (!allMissingIngredients.isEmpty()) {
			System.out.println("WARNING you have low stock to made " + Constant.DRINKS_IN_WARNING_STOCK + " "
					+ DrinkSizesEnum.LARGE.getSizeName() + " drinks");
			System.out.println();
		}
	}

	private final static List<Ingredients> checkLowStock(Ingredients flavor) {

		List<Ingredients> missingIngredients = new ArrayList<>();
		Float drinkSize = DrinkSizesEnum.LARGE.getSize() * Constant.DRINKS_IN_WARNING_STOCK;

		List<Ingredients> requiredStock = BlendedFruitDrinkRecipe.getRequiredIngredients(drinkSize,
				Arrays.asList(flavor));
		List<Ingredients> remainingStock = IngredientsStock.getIngredients();

		requiredStock.stream().forEach((necesaryIngredient) -> {
			Float necesaryStock = necesaryIngredient.getStock();
			remainingStock.stream().forEach((remainingingredient) -> {
				Float currentStock = remainingingredient.getStock();
				if (necesaryIngredient.equals(remainingingredient) && (currentStock < necesaryStock)) {
					missingIngredients.add(remainingingredient);
				}
			});

		});

		return missingIngredients;
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}

	public static void waitForEnter() {
		System.out.println("Press ENTER ... ");
		try {
			System.in.read();
			clearConsole();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
