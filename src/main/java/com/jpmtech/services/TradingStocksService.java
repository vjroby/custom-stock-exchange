package com.jpmtech.services;

import com.jpmtech.datasource.StockRepositoryInterface;
import com.jpmtech.datasource.TradesRepositoryInterface;
import com.jpmtech.entities.StockInterface;
import com.jpmtech.entities.Trade;
import com.jpmtech.entities.TradeInfo;
import com.jpmtech.entities.TradeType;
import com.jpmtech.exceptions.StockNotFoundException;
import org.javamoney.moneta.Money;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class TradingStocksService {

    private StockRepositoryInterface stockRepository;
    private TradesRepositoryInterface tradesRepository;
    private Set<TradeInfo> tradeInfoPerStock = new HashSet<>();

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

    public void buyOrder(StockInterface stock, int quantity, Money price) {
       addNewTrade(stock, quantity, price, TradeType.BUY);
    }


    public void sellOrder(StockInterface stock, int quantity, Money price) {
        addNewTrade(stock, quantity, price, TradeType.SELL);
    }

    private void addNewTrade(StockInterface stock, int quantity, Money price, TradeType tradeType){
        Trade trade = new Trade(Instant.now(), tradeType, stock, quantity, price);
        tradesRepository.addTrade(trade);
        printTrade(trade);
    }

    private void printTrade(Trade trade){
        System.out.println(trade);
    }

    public void printStocks() {
        Set<StockInterface> stocks = stockRepository.getAllStocks();
        for (StockInterface stock :
                stocks) {
            System.out.println(stock);
        }
    }
}
