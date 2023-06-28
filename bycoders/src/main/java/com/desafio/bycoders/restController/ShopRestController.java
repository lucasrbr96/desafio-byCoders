package com.desafio.bycoders.restController;

import com.desafio.bycoders.domain.dto.InfoShopDTO;
import com.desafio.bycoders.domain.dto.TransactionInfoDTO;
import com.desafio.bycoders.domain.model.Shop;
import com.desafio.bycoders.domain.model.Transaction;
import com.desafio.bycoders.service.IShopService;
import com.desafio.bycoders.service.implemantion.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
public class ShopRestController {
    private final IShopService shopService;
    private final TransactionService transactionService;

    public ShopRestController(IShopService shopService, TransactionService transactionService) {
        this.shopService = shopService;
        this.transactionService = transactionService;
    }

    @GetMapping("/{shopName}/amount")
    public ResponseEntity<BigDecimal> getAmount(@PathVariable("shopName") String shopName) {
        BigDecimal amountTotal = shopService.amountTotalByShopName(shopName);
        return ResponseEntity.ok(amountTotal);
    }

    @GetMapping("/{shopName}/info")
    public ResponseEntity<InfoShopDTO> getInfo(@PathVariable("shopName") String shopName) {
        BigDecimal amountTotal = shopService.amountTotalByShopName(shopName);
        List<Transaction> transactions = transactionService.transactions(shopName);
        List<TransactionInfoDTO> transactionsInfo = transactions.stream()
                .map(transaction -> new TransactionInfoDTO(transaction.getType().getCode(), transaction.getValue(), transaction.getHour(), transaction.getDate()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new InfoShopDTO(amountTotal, transactionsInfo));
    }

    @GetMapping("")
    public ResponseEntity<List<Shop>> getAll() {
        return ResponseEntity.ok(shopService.getAll());
    }

}
