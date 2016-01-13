package com.jpmtech.stockexchange;

import com.jpmtech.services.TradingStocksService;

public class CustomStockExchange {

    private final TradingStocksService tradingStocksService;

    public CustomStockExchange(TradingStocksService tradingStocksService) {
        this.tradingStocksService = tradingStocksService;
    }


}
