package com.desafio.bycoders.service;

import com.desafio.bycoders.domain.model.Shop;

import java.math.BigDecimal;
import java.util.List;

public interface IShopService {
    Shop findOrCreateShop(String shopName) throws Exception;
    void save(Shop shop);
    BigDecimal amountTotalByShopName(String shopName);
    List<Shop> getAll();
}
