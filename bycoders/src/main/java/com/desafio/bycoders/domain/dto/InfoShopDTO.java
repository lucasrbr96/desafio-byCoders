package com.desafio.bycoders.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoShopDTO {
    private BigDecimal amountTotal;
    private List<TransactionInfoDTO> transactions;
}
