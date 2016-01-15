package com.jpmtech.entities;

import com.jpmtech.stockexchange.CustomStockExchange;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public class TradeInfo {

    private Money price;
    private int quantity = 0;
    private Money totalStockVolume = Money.of(CustomStockExchange.currency, BigDecimal.ZERO);
    private Money totalLiquidity = Money.of(CustomStockExchange.currency, BigDecimal.ZERO);


    public TradeInfo() {

    }

    public void registerTrade(Trade trade) {
        quantity += trade.getQuantity();
        Money tradeValue = trade.getTradePrice().multiply(trade.getQuantity());
        totalLiquidity.add(tradeValue);
        totalStockVolume.add(tradeValue);
        price = totalLiquidity.divide(quantity);
    }
    public static TradeInfo createEmptyObject(){
        return new TradeInfo();
    }
    //todo method to look for expired trades!?!!?
}
