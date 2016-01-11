package com.jpmtech.entities;

import org.javamoney.moneta.Money;

public interface StockInterface {

    String symbol();

    StockType type();

    Money parValue();

    double calculateDividendYield(Money money);

    double calculatePERatio(Money money);
}
