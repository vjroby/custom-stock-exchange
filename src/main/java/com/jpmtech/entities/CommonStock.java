package com.jpmtech.entities;

import org.javamoney.moneta.Money;

public class CommonStock extends AbstractStock {

    public CommonStock(String symbol, Money parValue, Money lastDividend) {
        super(symbol, parValue, lastDividend);
    }

    @Override
    public StockType type() {
        return StockType.COMMON;
    }

    @Override
    public double calculateDividendYield(Money tickerPrice) {
        return lastDividend().divide(tickerPrice.getNumber()).getNumber().doubleValueExact();
    }

}
