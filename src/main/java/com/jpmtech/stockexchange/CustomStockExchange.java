package com.jpmtech.stockexchange;

import com.jpmtech.entities.StockInterface;
import com.jpmtech.services.TradingStocksService;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;

public class CustomStockExchange {

    public static final String currency = "GBP";
    public static final long minutesToCalculateTrades = 10;

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
        p("Trading stoppped");
    }

    private void deliverReport() {

    }

    public void startTrading(){
        Set<StockInterface> stocks = tradingStocksService.getStockRepository().getAllStocks();

        stocks.stream().forEach(stock -> {
            BigDecimal priceVariation = getRandomPrice();
            int quantiy = getRandomQuantity();
            Money tradePrice = stock.parValue().add(Money.of(currency, priceVariation));
            tradingStocksService.buyOrder(stock, quantiy, tradePrice);
        } );

        stocks.stream().forEach(stock -> {
            BigDecimal priceVariation = getRandomPrice();
            int quantiy = getRandomQuantity();
            Money tradePrice = stock.parValue().subtract(Money.of(currency, priceVariation));
            tradingStocksService.sellOrder(stock, quantiy, tradePrice);
        } );
    }

    private int getRandomQuantity() {
        return (int) Math.round(Math.random()*5000 +1 );
    }

    private BigDecimal getRandomPrice() {
        return new BigDecimal(10 + Math.random()).divide(new BigDecimal(1)).round(MathContext.DECIMAL32);
    }

    public static void p(String s) {
        System.out.println(s);
    }
}
