package com.jpmtech.entities;

import com.jpmtech.stockexchange.CustomStockExchange;
import org.javamoney.moneta.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class TradeInfoTest {

    StockInterface stockTest = new PreferredStock(
            "ALE",
            Money.of("GBP", new BigDecimal(344)),
            Money.of("GBP", new BigDecimal(20)),
            new BigDecimal(0.5));

    @Test
    public void testRegisterTrade() throws Exception {
        Trade firstTrade = new Trade(
                Instant.now(),
                TradeType.BUY,
                stockTest,
                10,
                Money.of("GBP", new BigDecimal(250))
        );
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.registerTrade(firstTrade);

        Money price = tradeInfo.getPrice();

        long expiredMinutes = CustomStockExchange.minutesToCalculateTrades + 1;
        Instant tradeInstant = Instant.now().minus(Duration.ofMinutes(expiredMinutes));
        Trade expiredTrade = new Trade(
                tradeInstant,
                TradeType.BUY,
                stockTest,
                10,
                Money.of("GBP", new BigDecimal(400))
        );
        tradeInfo.registerTrade(expiredTrade);
        assertEquals(price, tradeInfo.getPrice());
    }

    @Test
    public void testGetPrice() throws Exception {

    }
}