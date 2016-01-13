package com.jpmtech.stockexchange;

import com.jpmtech.entities.StockInterface;
import com.jpmtech.services.TradingStocksService;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;

public class CustomStockExchange {

    private final TradingStocksService tradingStocksService;

    public CustomStockExchange(TradingStocksService tradingStocksService) {
        this.tradingStocksService = tradingStocksService;
    }

    public void startExchange() {
        p("Custom Stock Exchange started.");
        p("The following stocks will be used for trading:");
        // TODO fixed dividend for preferred stock
        p("TYPE, SYMBOL, PRICE, LAST DIVIDEND");
        tradingStocksService.printStocks();
        p("The trading starts");
    }

    public void stopExchange() {

    }

    private void deliverReport() {

    }

    private void calculateGeoMedian() {

    }

    public void startTrading(){
        Set<StockInterface> stocks = tradingStocksService.getStockRepository().getAllStocks();

        stocks.stream().forEach(stock -> {
            BigDecimal priceVariation = new BigDecimal(1+Math.random()).divide(new BigDecimal(1)).round(MathContext.DECIMAL32);
            int quantiy = (int) Math.round(Math.random()*5000 +1 );
            Money tradePrice = stock.parValue().add(Money.of("GBP", priceVariation));
            tradingStocksService.buyOrder(stock, quantiy, tradePrice);
        } );
    }

    private static void p(String s) {
        System.out.println(s);
    }
}
