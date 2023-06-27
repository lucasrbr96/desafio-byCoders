package com.desafio.bycoders.domain.enumn;


import com.desafio.bycoders.util.OperationsUtil;

import java.math.BigDecimal;

public enum TypeOperationEnum {
    DEBIT(1){
        @Override
        public BigDecimal operation(BigDecimal amount, BigDecimal amountTransaction) {
            return OperationsUtil.sum(amount, amountTransaction);
        }
    },
    BOLETO(2){
        @Override
        public BigDecimal operation(BigDecimal amount, BigDecimal amountTransaction) {
            return OperationsUtil.subtract(amount, amountTransaction);
        }
    },
    FINANCING(3){
        @Override
        public BigDecimal operation(BigDecimal amount, BigDecimal amountTransaction) {
            return OperationsUtil.subtract(amount, amountTransaction);
        }
    },
    CREDIT(4){
        @Override
        public BigDecimal operation(BigDecimal amount, BigDecimal amountTransaction) {
            return OperationsUtil.sum(amount, amountTransaction);
        }
    },
    LOAN(5){
        @Override
        public BigDecimal operation(BigDecimal amount, BigDecimal amountTransaction) {
            return OperationsUtil.sum(amount, amountTransaction);
        }
    },
    SALES(6){
        @Override
        public BigDecimal operation(BigDecimal amount, BigDecimal amountTransaction) {
            return OperationsUtil.sum(amount, amountTransaction);
        }
    },
    TED(7){
        @Override
        public BigDecimal operation(BigDecimal amount, BigDecimal amountTransaction) {
            return OperationsUtil.sum(amount, amountTransaction);
        }
    },
    DOC(8){
        @Override
        public BigDecimal operation(BigDecimal amount, BigDecimal amountTransaction) {
            return OperationsUtil.sum(amount, amountTransaction);
        }
    },
    RENT(9){
        @Override
        public BigDecimal operation(BigDecimal amount, BigDecimal amountTransaction) {
            return OperationsUtil.subtract(amount, amountTransaction);
        }
    };

    private int code;

    public int getCode(){
        return code;
    }
    TypeOperationEnum(int code){
        this.code = code;
    }

    public static TypeOperationEnum findByCode(int code) {
        for (TypeOperationEnum type : TypeOperationEnum.values()) {
            if (type.code == code) {
                return type;
            }
        }

        return null;
    }
    private BigDecimal operation(){
        return null;
    }

    public abstract BigDecimal operation(BigDecimal amountTotal, BigDecimal amount);
}
