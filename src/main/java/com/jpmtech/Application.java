package com.jpmtech;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

public class Application {

    public static void main(String[] args){
        FastMoney fastMoney = FastMoney.of("GBP",23);
        Money money = Money.of("GBP", 1);
//        MonetaryAmount monetaryAmount = FastMoney.of()

    }


    private static void getDataFromCSV(){

    }
}
