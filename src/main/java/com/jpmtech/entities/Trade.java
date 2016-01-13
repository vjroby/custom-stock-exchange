package com.jpmtech.entities;

import org.javamoney.moneta.Money;

import java.time.Instant;

public class Trade {

    private final Instant instant;
    private final TradeType tradeType;
    private final StockInterface stock;
    private final int quantity;
    private final Money tradePrice;

    public Trade(Instant instant, TradeType tradeType, StockInterface stock, int quantity, Money tradePrice) {
        this.instant = instant;
        this.tradeType = tradeType;
        this.stock = stock;
        this.quantity = quantity;
        this.tradePrice = tradePrice;
    }


    public Instant getInstant() {
        return instant;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public StockInterface getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getTradePrice() {
        return tradePrice;
    }

    @Override
    public String toString() {
        return tradeType.toString()+ " (time="+ instant+", stock=" + stock +", quantity=" + quantity + ", tradePrice=" + tradePrice + ")";
    }
}
