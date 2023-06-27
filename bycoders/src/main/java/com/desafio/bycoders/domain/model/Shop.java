package com.desafio.bycoders.domain.model;

import com.desafio.bycoders.domain.enumn.TypeOperationEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Shop {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String shopName;

    private BigDecimal amountTotal;

    public Shop(String shopName, BigDecimal zero) {
        this.shopName = shopName;
        this.amountTotal = zero;
    }

    public void operation(TypeOperationEnum typeOperationEnum, BigDecimal amount){
        this.amountTotal = typeOperationEnum.operation(this.amountTotal, amount);
    }
}
