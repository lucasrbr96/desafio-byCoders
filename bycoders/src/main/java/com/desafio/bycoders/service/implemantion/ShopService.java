package com.desafio.bycoders.service.implemantion;

import com.desafio.bycoders.domain.model.Shop;
import com.desafio.bycoders.repository.ShopRepository;
import com.desafio.bycoders.service.IShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShopService implements IShopService {


    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop findOrCreateShop(String shopName) throws Exception {
        validateShopName(shopName);
        if(existsShop(shopName)){
            return findByName(shopName);
        }
        return new Shop(null, shopName, BigDecimal.ZERO);
    }

    private void validateShopName(String shopName) throws Exception {
        if (StringUtils.isBlank(shopName)){
            throw new Exception("Empty Shop name");
        }
    }

    public void save(Shop shop){
        shopRepository.save(shop);
    }

    public List<Shop> getAll(){
        return shopRepository.findAll();
    }

    public BigDecimal amountTotalByShopName(String shopName){
        return findByName(shopName).getAmountTotal();
    }

    private Shop findByName(String shopName){
        return shopRepository.findByShopName(shopName).orElseThrow(() -> new RuntimeException("Sem record"));
    }

    private boolean existsShop(String shopName){
        return shopRepository.existsShopByShopName(shopName);
    }
}
