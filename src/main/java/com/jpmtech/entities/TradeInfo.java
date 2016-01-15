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

    synchronized public void registerTrade(Trade trade) {
        quantity += trade.getQuantity();
        Money tradeValue = trade.getTradePrice().multiply(trade.getQuantity());
        totalStockVolume = totalStockVolume.add(tradeValue);
        if(trade.getTradeType().equals(TradeType.SELL)){
            totalLiquidity = totalLiquidity.add(tradeValue);
        }else{
            totalLiquidity = totalLiquidity.subtract(tradeValue);
        }
        price = totalStockVolume.divide(quantity);
    }

    public Money getPrice() {
        return price;
    }
    //todo method to look for expired trades!?!!?
}
