package com.jpmtech.csv;

import com.jpmtech.entities.CommonStock;
import com.jpmtech.entities.PreferredStock;
import com.jpmtech.entities.StockInterface;
import com.jpmtech.entities.StockType;
import org.javamoney.moneta.Money;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Parser {

    public static Set<StockInterface> parseCSVStocks(InputStream inputStream) {
        if (inputStream == null) return Collections.<StockInterface>emptySet();
        Set<StockInterface> stocks = new HashSet<StockInterface>();
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line = bufferedReader.readLine();// skip first line
            String cvsSplitBy = ",";
            while ((line = bufferedReader.readLine()) != null){
                String[] stockString = line.split(cvsSplitBy);
                StockInterface stock = convertStringToStock(stockString);
                stocks.add(stock);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("CSV parse error");
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return stocks;
    }

    private static StockInterface convertStringToStock(String[] stockStringInfo) throws IllegalArgumentException {
        if (stockStringInfo.length < 5){
            throw new IllegalArgumentException("CSV stocks file is not properly formatted, every line must have 5 fields" +
                    "separated by a coma. THe fields are: symbol,type,last_dividend,fixed_dividend,par_value");
        }
        try {
            String symbol = stockStringInfo[0];
            String type = stockStringInfo[1];
            Money lastDividend = Money.of("GBP", new BigDecimal(stockStringInfo[2]));
            BigDecimal fixedDividend = new BigDecimal("0");
            Money parValue = Money.of("GBP", new BigDecimal(stockStringInfo[4]));
            if (type.equalsIgnoreCase(StockType.COMMON.toString())) return new CommonStock(symbol, parValue, lastDividend);
            else if(type.equalsIgnoreCase(StockType.PREFERRED.toString())) return new PreferredStock(symbol, parValue, lastDividend, fixedDividend);
            else throw new IllegalArgumentException("Stock type unknown: " + type);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("There was an error when parsing stock, line: " + Arrays.toString(stockStringInfo));
        }
    }

//    private static BigDecimal getDecimalFromString();
}
