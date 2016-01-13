package com.jpmtech.stockexchange;

import com.jpmtech.services.TradingStocksService;

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

    private static void p(String s) {
        System.out.println(s);
    }
}
