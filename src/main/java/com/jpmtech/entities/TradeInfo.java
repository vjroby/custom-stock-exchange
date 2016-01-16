package com.jpmtech.entities;

import com.jpmtech.stockexchange.CustomStockExchange;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TradeInfo {

    private Money price;
    private int quantity = 0;
    private Money totalStockVolume = Money.of(CustomStockExchange.currency, BigDecimal.ZERO);
    private Money totalQuantity = Money.of(CustomStockExchange.currency, BigDecimal.ZERO);
    private Set<Trade> allTrades = new HashSet<>();

    public TradeInfo() {

    }

    synchronized public void registerTrade(Trade trade) {
        quantity += trade.getQuantity();
        Money tradeValue = trade.getTradePrice().multiply(trade.getQuantity());
        totalStockVolume = totalStockVolume.add(tradeValue);
        totalQuantity = totalQuantity.add(tradeValue);
        allTrades.add(trade);
        removeExpiredTrades();
        calculatePrice();
    }

    public Money getPrice() {
        return price;
    }

    synchronized private void removeExpiredTrades(){
        if(allTrades.size() != 0){
            List<Trade> expiredTrades = allTrades.stream().filter(trade -> {
                Instant systemInstant = Instant.now();
                systemInstant.minus(Duration.ofMinutes(CustomStockExchange.minutesToCalculateTrades));
                return systemInstant.isAfter(trade.getInstant());
            }).collect(Collectors.toList());
            if(expiredTrades.size() !=0){
                for (Trade trade :
                        expiredTrades) {
                    totalQuantity = totalQuantity.subtract(trade.getTradePrice().multiply(trade.getQuantity()));
                    allTrades.remove(trade);
                }
            }
        }

        calculatePrice();
    }

    synchronized void calculatePrice(){
        price = totalStockVolume.divide(totalQuantity.getNumber());

    }
    //todo method to look for expired trades!?!!?
}
