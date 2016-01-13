package com.jpmtech.stockexchange;

import com.jpmtech.services.TradingStocksService;

public class CustomStockExchange {

    private final TradingStocksService tradingStocksService;

    public CustomStockExchange(TradingStocksService tradingStocksService) {
        this.tradingStocksService = tradingStocksService;
    }

    public void startExchange() {
        p("Custom Stock Exchange started.");
        tradingStocksService.printStocks();
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
