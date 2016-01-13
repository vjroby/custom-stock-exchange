package com.jpmtech.datasource;

import com.jpmtech.entities.Trade;

import java.util.HashSet;
import java.util.Set;

public class MemoryTradesRepository implements TradesRepositoryInterface{

    private Set<Trade> trades = new HashSet<Trade>();

    @Override
    public void addTrade(Trade trade) {
        trades.add(trade);
    }
}
