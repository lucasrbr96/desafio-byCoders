package com.desafio.bycoders.repository;

import com.desafio.bycoders.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t join t.shop s where s.shopName = :shopName")
    List<Transaction> findByShop(@Param("shopName") String shopName);
}
