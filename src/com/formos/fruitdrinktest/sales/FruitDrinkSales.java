package com.formos.fruitdrinktest.sales;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.formos.fruitdrinktest.util.Constant;
import com.formos.fruitdrinktest.util.NumberUtil;

public class FruitDrinkSales {

	private static Map<String, Float> dailySales = new HashMap<>();

	public static void addSaleAmount(Float saleAmount) {

		DateFormat df = new SimpleDateFormat(Constant.DATE_PATTERN);
		String dateKey = df.format(new Date());

		Float totalSale = saleAmount;

		if (dailySales.containsKey(dateKey)) {
			totalSale += dailySales.get(dateKey);
		}

		dailySales.put(dateKey, NumberUtil.getRoundedCurrencyValue(totalSale));
	}

	public static void printDailySalesReport() {

		if (dailySales.isEmpty()) {
			System.out.println("Sorry, no sales yet ..");
		} else {
			System.out.println("Daily sales report:");
		}

		for (Map.Entry<String, Float> entry : dailySales.entrySet()) {
			System.out.println("Date " + entry.getKey() + " : " + entry.getValue() + " " + Constant.CURRENCY);
		}
	}

}
