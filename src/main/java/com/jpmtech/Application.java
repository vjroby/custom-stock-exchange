package com.jpmtech;

import com.jpmtech.csv.Parser;
import com.jpmtech.entities.StockInterface;
import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

public class Application {

    public final static String csvFileName = "/jpm.csv";

    public final Set<StockInterface> STOCKS;

    static {
        InputStream inputStream = Application.class.getResourceAsStream(csvFileName);

        STOCKS = Collections.unmodifiableSet(Parser.parseCSVStocks(inputStream));
    }

    public static void main(String[] args){
        FastMoney fastMoney = FastMoney.of("GBP",23);
        Money money = Money.of("GBP", 1);
//        MonetaryAmount monetaryAmount = FastMoney.of()

    }


    private static void getDataFromCSV(){

    }
}
