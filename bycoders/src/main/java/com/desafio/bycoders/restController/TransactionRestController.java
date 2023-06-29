package com.desafio.bycoders.restController;

import com.desafio.bycoders.service.ITransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {
    private final ITransactionService transactionService;

    public TransactionRestController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/upload")
    @ApiOperation(value = "transaction upload")
    public ResponseEntity<String> upload(@RequestBody MultipartFile file) {
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty file");
        }
        transactionService.save(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
