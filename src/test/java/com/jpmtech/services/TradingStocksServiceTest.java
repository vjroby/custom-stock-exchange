package com.jpmtech.services;

import com.jpmtech.datasource.MemoryStockRepository;
import com.jpmtech.datasource.MemoryTradesRepository;
import com.jpmtech.datasource.StockRepositoryInterface;
import com.jpmtech.datasource.TradesRepositoryInterface;
import com.jpmtech.entities.CommonStock;
import com.jpmtech.entities.PreferredStock;
import com.jpmtech.entities.StockInterface;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TradingStocksServiceTest {
    private StockRepositoryInterface stockRepository;
    private TradesRepositoryInterface tradesRepository;
    private TradingStocksService tradingStocksService = new TradingStocksService();

    @Before
    public void setUp() throws Exception {
        StockInterface stock1 = new PreferredStock(
                "TEST1",
                Money.of("GBP", new BigDecimal(10)),
                Money.of("GBP", new BigDecimal(0.5)),
                new BigDecimal(0.2));
        StockInterface stock2 = new CommonStock(
                "TEST2",
                Money.of("GBP", new BigDecimal(10)),
                Money.of("GBP", new BigDecimal(2)));
        Set<StockInterface> stockSet = new HashSet<>();
        stockSet.add(stock1);
        stockSet.add(stock2);
        stockRepository = new MemoryStockRepository(stockSet);
        tradesRepository = new MemoryTradesRepository();
        tradingStocksService.setStockRepository(stockRepository);
        tradingStocksService.setTradesRepository(tradesRepository);

    }

    @Test
    public void testCalculateDividendYield() throws Exception {
        Double dividendYield = 0.08;
        Double dividendYieldCommon = tradingStocksService.calculateDividendYield("TEST2", Money.of("GBP", new BigDecimal(25)));
        assertEquals(dividendYield, dividendYieldCommon);
        Double dividendYieldPreferred = tradingStocksService.calculateDividendYield("TEST1", Money.of("GBP", new BigDecimal(35)));
        dividendYield = 0.05714285714285715;
        assertEquals(dividendYield, dividendYieldPreferred);
    }

    @Test
    public void testCalculatePERation() throws Exception {
        Double PERationTest1 = tradingStocksService.calculatePERation("TEST1", Money.of("GBP", new BigDecimal(15)));
        assertEquals(new Double(30.00), PERationTest1);

        Double PERationTest2 = tradingStocksService.calculatePERation("TEST2", Money.of("GBP", new BigDecimal(20)));
        assertEquals(new Double(10.00), PERationTest2);
    }

    @Test
    public void testCalculateGEMean() throws Exception {
        tradingStocksService.buyOrder(
                stockRepository.getStockBySymbol("TEST1"), 10, Money.of("GBP", new BigDecimal(9))
        );
        Double expectedIndex = 9.0;
        assertEquals(expectedIndex, tradingStocksService.calculateGEMean());
        tradingStocksService.buyOrder(
                stockRepository.getStockBySymbol("TEST1"), 100, Money.of("GBP", new BigDecimal(7))
        );
        expectedIndex = 7.181818181818182;
        assertEquals(expectedIndex, tradingStocksService.calculateGEMean());
        tradingStocksService.sellOrder(
                stockRepository.getStockBySymbol("TEST1"), 10, Money.of("GBP", new BigDecimal(11))
        );
        expectedIndex = 7.5;
        assertEquals(expectedIndex, tradingStocksService.calculateGEMean());
    }
}