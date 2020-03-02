package com.formos.fruitdrinktest.util;

import java.math.BigDecimal;

public class NumberUtil {

	public static float getRoundedStockValue(float amount) {
		BigDecimal decimal = new BigDecimal(amount);
		decimal = decimal.setScale(Constant.STOCK_DECIMAL_PLACES, Constant.ROUNDING_MODE);
		return decimal.floatValue();
	}

	public static float getRoundedCurrencyValue(float amount) {
		BigDecimal decimal = new BigDecimal(amount);
		decimal = decimal.setScale(Constant.CURRENCY_DECIMAL_PLACES, Constant.ROUNDING_MODE);
		return decimal.floatValue();
	}

}
