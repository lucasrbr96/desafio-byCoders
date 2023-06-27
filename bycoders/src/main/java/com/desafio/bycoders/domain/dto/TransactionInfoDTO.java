package com.desafio.bycoders.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionInfoDTO {
    private int typeOperation;
    private BigDecimal value;
    private LocalTime hour;
    private LocalDate date;
}
