package com.jpmtech.datasource;

import com.jpmtech.entities.StockInterface;

import java.util.Set;

public class MemoryStockRepository implements StockRepositoryInterface{

    private final Set<StockInterface> stocks;

    public MemoryStockRepository(Set<StockInterface> stocks) {
        this.stocks = stocks;
    }

    @Override
    public void setStocks(Set<StockInterface> stockInterfaceSet) {

    }

    @Override
    public Set<StockInterface> getAllStocks() {
        return stocks;
    }

    @Override
    public StockInterface getRandomStock() {
        return null;
    }
}
