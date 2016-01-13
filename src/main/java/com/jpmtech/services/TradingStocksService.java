package com.jpmtech.services;

import com.jpmtech.datasource.StockRepositoryInterface;
import com.jpmtech.datasource.TradesRepositoryInterface;
import com.jpmtech.entities.StockInterface;
import com.jpmtech.exceptions.StockNotFoundException;

import java.util.Set;

public class TradingStocksService {

    private StockRepositoryInterface stockRepository;
    private TradesRepositoryInterface tradesRepository;

    public StockRepositoryInterface getStockRepository() {
        return stockRepository;
    }

    public TradingStocksService setStockRepository(StockRepositoryInterface stockRepository) {
        this.stockRepository = stockRepository;
        return this;
    }

    public TradesRepositoryInterface getTradesRepository() {
        return tradesRepository;
    }

    public TradingStocksService setTradesRepository(TradesRepositoryInterface tradesRepository) {
        this.tradesRepository = tradesRepository;
        return this;
    }

    public Double calculateDividendYield(String stockSymbol) throws StockNotFoundException{
        return null;
    }

    public Double calculatePERation(String stockSymbol) throws StockNotFoundException{
        return null;
    }

    public void printStocks() {
        Set<StockInterface> stocks = stockRepository.getAllStocks();
        for (StockInterface stock :
                stocks) {
            System.out.println(stock);
        }
    }
}
