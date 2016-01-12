package com.jpmtech.entities;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public class PreferredStock extends AbstractStock {

    private final BigDecimal fixedDividend;

    public PreferredStock(String symbol, Money parValue, Money lastDividend, BigDecimal fixedDividend) {
        super(symbol, parValue, lastDividend);
        this.fixedDividend = fixedDividend;
    }

    @Override
    public StockType type() {
        return StockType.PREFERRED;
    }

    @Override
    public double calculateDividendYield(Money tickerPrice) {
        return parValue().multiply(fixedDividend)
                .divide(tickerPrice.getNumber())
                .getNumber()
                .doubleValueExact();
    }
}
