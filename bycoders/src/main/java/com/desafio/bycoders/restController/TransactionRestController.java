package com.desafio.bycoders.restController;

import com.desafio.bycoders.service.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {
    private final ITransactionService transactionService;

    public TransactionRestController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) {
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty file");
        }
        transactionService.save(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
