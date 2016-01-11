package com.jpmtech.entities;

import org.javamoney.moneta.Money;

public class PreferredStock extends AbstractStock {

    public PreferredStock(String symbol, Money parValue, Money lastDividend) {
        super(symbol, parValue, lastDividend);
    }

    @Override
    public StockType type() {
        return StockType.PREFERRED;
    }

    @Override
    public double calculateDividendYield(Money tickerPrice) {
        return lastDividend().multiply(parValue().getNumber())
                .divide(tickerPrice.getNumber())
                .getNumber()
                .doubleValueExact();
    }
}
