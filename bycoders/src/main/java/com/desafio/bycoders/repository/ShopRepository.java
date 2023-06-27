package com.desafio.bycoders.repository;

import com.desafio.bycoders.domain.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    boolean existsShopByShopName(String shopName);
    Optional<Shop> findByShopName(String shopName);

}
