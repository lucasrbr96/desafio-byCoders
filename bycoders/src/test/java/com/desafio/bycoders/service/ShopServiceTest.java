package com.desafio.bycoders.service;

import com.desafio.bycoders.domain.model.Shop;
import com.desafio.bycoders.repository.ShopRepository;
import com.desafio.bycoders.service.implemantion.ShopService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

    @InjectMocks
    private ShopService shopService;
    @Mock
    private ShopRepository repository;

    @DisplayName("Extract and create a new shop")
    @Test
    void findOrCreateSuccessCase1() throws Exception {
        String shopName = "Shop";

        Shop shop = shopService.findOrCreateShop(shopName);

        Assertions.assertEquals(shop.getShopName(), shopName);
    }

    @DisplayName("Extract and create an existing shop")
    @Test
    void findOrCreateSuccessCase2() throws Exception {
        Shop shopTest = new Shop(1L, "shop test", BigDecimal.ZERO);

        Mockito.when(repository.existsShopByShopName(shopTest.getShopName())).thenReturn(true);
        Mockito.when(repository.findByShopName(shopTest.getShopName())).thenReturn(Optional.of(shopTest));
        Shop shop = shopService.findOrCreateShop(shopTest.getShopName());

        Assertions.assertEquals(shop.getShopName(), shopTest.getShopName());
    }

    @DisplayName("Extract shop name empty")
    @Test
    void findOrCreateErrorCase(){
        Assertions.assertThrows(Exception.class, () -> shopService.findOrCreateShop(""));
    }

    @DisplayName("Save or update a shop")
    @Test
    void save(){
        Shop shop = new Shop();

        shopService.save(shop);

        Mockito.verify(repository, Mockito.times(1)).save(shop);
    }

    @DisplayName("get all saved names")
    @Test
    void getAll(){

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(new Shop(1L, "shop1", BigDecimal.ZERO),
                new Shop(2L, "shop2", BigDecimal.ZERO)));

        List<Shop> shops = shopService.getAll();


        Assertions.assertEquals(2, shops.size());
    }

    @DisplayName("Total value of a store")
    @Test
    void getAmountTotal(){
        String shopName = "shop1";
        Mockito.when(repository.findByShopName(shopName)).thenReturn(Optional.of(new Shop(1L, shopName, new BigDecimal("500.00"))));

        BigDecimal amount = shopService.amountTotalByShopName(shopName);

        Assertions.assertEquals(new BigDecimal("500.00"), amount);
    }
}
