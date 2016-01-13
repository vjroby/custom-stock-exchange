package com.jpmtech;

import com.jpmtech.csv.Parser;
import com.jpmtech.datasource.MemoryStockRepository;
import com.jpmtech.datasource.MemoryTradesRepository;
import com.jpmtech.datasource.StockRepositoryInterface;
import com.jpmtech.datasource.TradesRepositoryInterface;
import com.jpmtech.entities.StockInterface;
import com.jpmtech.services.TradingStocksService;
import com.jpmtech.stockexchange.CustomStockExchange;

import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

public class Application {

    public final static String csvFileName = "/jpm.csv";

    public static final Set<StockInterface> STOCKS;

    static {
        InputStream inputStream = Application.class.getResourceAsStream(csvFileName);

        STOCKS = Collections.unmodifiableSet(Parser.parseCSVStocks(inputStream));
    }

    public static void main(String[] args){

        StockRepositoryInterface stockRepository = new MemoryStockRepository(STOCKS);
        TradesRepositoryInterface tradesRepository = new MemoryTradesRepository();
        TradingStocksService tradingStocksService = new TradingStocksService()
                .setStockRepository(stockRepository)
                .setTradesRepository(tradesRepository);
        CustomStockExchange exchange = new CustomStockExchange(tradingStocksService);
        exchange.startExchange();

    }
}
