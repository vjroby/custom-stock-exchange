package com.jpmtech.entities;

import org.javamoney.moneta.Money;

public abstract class AbstractStock implements StockInterface {

    private final String symbol;
    private final Money parValue;
    private final Money lastDividend;

    public AbstractStock(String symbol, Money parValue, Money lastDividend) {
        this.symbol = symbol;
        this.parValue = parValue;
        this.lastDividend = lastDividend;
    }

    @Override
    public String symbol() {
        return symbol;
    }

    @Override
    public Money parValue() {
        return parValue;
    }

    @Override
    public double calculatePERatio(Money tickerPrice) {
        return tickerPrice.divide(lastDividend().getNumber()).getNumber().doubleValueExact();
    }

    protected Money lastDividend() {
        return lastDividend;
    }

    @Override
    public String toString(){
        return type().toString()+ "[ " + symbol() +  "," + parValue() + "," + lastDividend() + "]";
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }
}
