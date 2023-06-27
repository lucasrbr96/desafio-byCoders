package com.desafio.bycoders.util;

import java.math.BigDecimal;

public class OperationsUtil {

    private OperationsUtil(){

    }
    public static BigDecimal sum(BigDecimal value1, BigDecimal value2){
        if(value1 == null || value2 == null){
            throw new RuntimeException("valores nulos para operação matematica");
        }
        return value1.add(value2);
    }

    public static BigDecimal subtract(BigDecimal value1, BigDecimal value2){
        if(value1 == null || value2 == null){
            throw new RuntimeException("valores nulos para operação matematica");
        }
        return value1.subtract(value2);
    }
}
