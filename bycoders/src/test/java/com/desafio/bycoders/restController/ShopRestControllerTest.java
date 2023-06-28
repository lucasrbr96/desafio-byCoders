package com.desafio.bycoders.restController;

import com.desafio.bycoders.domain.enumn.TypeOperationEnum;
import com.desafio.bycoders.domain.model.Shop;
import com.desafio.bycoders.domain.model.Transaction;
import com.desafio.bycoders.service.implemantion.ShopService;
import com.desafio.bycoders.service.implemantion.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = ShopRestController.class)
class ShopRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService shopService;

    @MockBean
    private TransactionService transactionService;
    @Test
    @DisplayName("Get the total amount")
    void getAmount() throws Exception {

        BDDMockito.given(shopService.amountTotalByShopName(Mockito.anyString()))
                .willReturn(BigDecimal.ZERO);

        mockMvc.perform(MockMvcRequestBuilders.get("/shop/{shopName}/amount", "tech"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(BigDecimal.ZERO));

    }

    @Test
    @DisplayName("Get all the information")
    void getInfo() throws Exception {
        BigDecimal amountTotal = new BigDecimal("10");
        BDDMockito.given(shopService.amountTotalByShopName(Mockito.anyString()))
                .willReturn(amountTotal);

        BDDMockito.given(transactionService.transactions(Mockito.anyString()))
                .willReturn(Arrays.asList(
                        new Transaction(1L, TypeOperationEnum.DEBIT, LocalDate.now(), new BigDecimal("5.00"), "", "", LocalTime.now(), "", new Shop()),
                        new Transaction(2L,TypeOperationEnum.DEBIT, LocalDate.now(), new BigDecimal("5.00"), "", "", LocalTime.now(), "", new Shop())));

        mockMvc.perform(MockMvcRequestBuilders.get("/shop/{shopName}/info", "tech"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amountTotal").value(amountTotal))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactions").isArray());
    }

    @Test
    @DisplayName("Get all shop")
    void get() throws Exception {

        BDDMockito.given(shopService.getAll())
                .willReturn(Arrays.asList(
                        new Shop(1L, "Shop1", BigDecimal.ZERO),
                        new Shop(2L, "Shop2", new BigDecimal("10"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/shop"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

    }

}
