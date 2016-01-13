package com.jpmtech.datasource;

import com.jpmtech.entities.StockInterface;

import java.util.Set;

public interface StockRepositoryInterface {

    void SetStocks(Set<StockInterface> stockInterfaceSet);

    Set<StockInterface> getAllStocks();

    StockInterface getRandomStock();

}
