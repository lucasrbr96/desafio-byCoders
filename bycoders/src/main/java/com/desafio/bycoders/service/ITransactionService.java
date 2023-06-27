package com.desafio.bycoders.service;

import com.desafio.bycoders.domain.model.Transaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITransactionService {
    void save(MultipartFile file);
    List<Transaction> transactions(String shopName);
}