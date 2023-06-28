package com.desafio.bycoders.service;

import com.desafio.bycoders.domain.model.Shop;
import com.desafio.bycoders.domain.model.Transaction;
import com.desafio.bycoders.repository.TransactionRepository;
import com.desafio.bycoders.service.implemantion.ShopService;
import com.desafio.bycoders.service.implemantion.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @InjectMocks
    private TransactionService service;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private ShopService shopService;
    @Test
    @DisplayName("Successfully save a transaction")
    void saveSuccess() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("file.txt", new FileInputStream("src/test/java/com/desafio/bycoders/resource/DataOneTransaction.txt"));

        when(shopService.findOrCreateShop(anyString())).thenReturn(new Shop(1L, returnsFirstArg().toString(), BigDecimal.ZERO));

        Assertions.assertDoesNotThrow(()-> service.save(multipartFile));
    }

    @Test
    @DisplayName("Transaction with error")
    void saveError() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("file.txt", new FileInputStream("src/test/java/com/desafio/bycoders/resource/DataError.txt"));
        Assertions.assertThrows(Exception.class ,()-> service.save(multipartFile));
    }

    @Test
    @DisplayName("Fetch a transaction list from a store")
    void transaction(){
        String shopName = "shopName";
        when(transactionRepository.findByShop(shopName)).thenReturn(Arrays.asList(
                new Transaction(1L,null, LocalDate.now(), BigDecimal.ZERO, "", "", LocalTime.now(), "", new Shop())));
        List<Transaction> list = service.transactions(shopName);

        Assertions.assertEquals(1, list.size());
    }
}
