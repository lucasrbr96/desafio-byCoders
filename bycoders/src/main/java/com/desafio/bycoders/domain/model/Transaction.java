package com.desafio.bycoders.domain.model;

import com.desafio.bycoders.domain.enumn.TypeOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private TypeOperationEnum type;

    private LocalDate date;

    private BigDecimal value;

    private String cpf;

    private String card;

    private LocalTime hour;

    private String ownerName;

    @ManyToOne
    private Shop shop;

    public Transaction(String line, TypeOperationEnum typeOperationEnum, Shop shop) {
        this.type = typeOperationEnum;
        this.date = LocalDate.parse(line.substring(1, 9), DateTimeFormatter.BASIC_ISO_DATE);
        this.value = new BigDecimal(line.substring(9, 19));
        this.cpf = line.substring(19, 30);
        this.card = line.substring(30, 42);
        this.hour = LocalTime.parse(line.substring(42, 48), DateTimeFormatter.ofPattern("HHmmss"));
        this.ownerName = line.substring(48, 62).trim();
        this.shop = shop;
    }
}
