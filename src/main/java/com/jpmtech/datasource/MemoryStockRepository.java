package com.jpmtech.datasource;

import com.jpmtech.entities.StockInterface;
import com.jpmtech.exceptions.StockNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public StockInterface getStockBySymbol(String symbol) {
        List<StockInterface> stockInterface = stocks.stream().filter(stock -> stock.hashCode() == symbol.hashCode()).collect(Collectors.toList());
        if(stockInterface.size() == 1){
            return stockInterface.get(0);
        }
        throw new StockNotFoundException("Stock with symbol" + symbol + " not found");
    }
}
