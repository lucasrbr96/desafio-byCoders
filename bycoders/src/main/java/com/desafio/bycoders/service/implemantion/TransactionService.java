package com.desafio.bycoders.service.implemantion;

import com.desafio.bycoders.domain.enumn.TypeOperationEnum;
import com.desafio.bycoders.domain.model.Shop;
import com.desafio.bycoders.domain.model.Transaction;
import com.desafio.bycoders.repository.TransactionRepository;
import com.desafio.bycoders.service.IShopService;
import com.desafio.bycoders.service.ITransactionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    private final IShopService shopService;

    public TransactionService(TransactionRepository transactionRepository, IShopService shopService) {
        this.transactionRepository = transactionRepository;
        this.shopService = shopService;
    }

    @Transactional(rollbackOn = Exception.class)
    public void save(MultipartFile file) {
       try(InputStream inputStream = file.getInputStream();
           Scanner scanner = new Scanner(inputStream)) {
           while (scanner.hasNext()) {
               String line = scanner.nextLine();

               TypeOperationEnum typeOperationEnum = extractTypeOperation(line);
               Shop shop = extractShop(line);

               Transaction transaction = new Transaction(line, typeOperationEnum, shop);

               shop.operation(typeOperationEnum, transaction.getValue());

               transactionRepository.save(transaction);
               shopService.save(shop);
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    private Shop extractShop(String line) {
        String shopName = line.substring(62, 80).trim();
        return findOrCreateShop(shopName);
    }

    private static TypeOperationEnum extractTypeOperation(String line) throws Exception {
        TypeOperationEnum typeOperationEnum = TypeOperationEnum.findByCode(Integer.parseInt(line.substring(0, 1)));
        if(typeOperationEnum == null) throw new Exception("No operation");
        return typeOperationEnum;
    }

    private Shop findOrCreateShop(String shopName){
       return shopService.findOrCreateShop(shopName);
    }

    public List<Transaction> transactions(String shopName){
        return transactionRepository.findByShop(shopName);
    }

}
