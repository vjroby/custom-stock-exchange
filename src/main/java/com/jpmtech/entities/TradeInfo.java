package com.jpmtech.entities;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public class TradeInfo {

    private Money price;
    private int quantity = 0;
    private Money totalStockVolume = Money.of("GBP", BigDecimal.ZERO);
    private Money totalLiquidity = Money.of("GBP", BigDecimal.ZERO);


    public TradeInfo(Money price, Money totalStockVolume, Money totalLiquidity) {
        this.price = price;
        this.totalStockVolume = totalStockVolume;
        this.totalLiquidity = totalLiquidity;
    }

    public void registerTrade(Trade trade) {
        quantity += trade.getQuantity();
        Money tradeValue = trade.getTradePrice().multiply(trade.getQuantity());
        totalLiquidity.add(tradeValue);
        totalStockVolume.add(tradeValue);
        price = totalLiquidity.divide(quantity);
    }

    //todo method to look for expired trades!?!!?
}
