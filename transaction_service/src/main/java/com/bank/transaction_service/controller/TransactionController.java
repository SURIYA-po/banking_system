package com.bank.transaction_service.controller;

import com.bank.transaction_service.dto.TransferRequest;
import com.bank.transaction_service.entity.Transaction;
import com.bank.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService txnService;

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<Transaction> deposit(@PathVariable UUID accountId,
                                               @RequestParam Double amount,
                                               @RequestParam(required = false) String remarks) {
        Transaction t = txnService.deposit(accountId, amount, remarks);
        return ResponseEntity.ok(t);
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<Transaction> withdraw(@PathVariable UUID accountId,
                                                @RequestParam Double amount,
                                                @RequestParam(required = false) String remarks) {
        Transaction t = txnService.withdraw(accountId, amount, remarks);
        return ResponseEntity.ok(t);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@Validated @RequestBody TransferRequest request) {
        Transaction t = txnService.transfer(request);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getForAccount(@PathVariable UUID accountId) {
        return ResponseEntity.ok(txnService.getTransactionsForAccount(accountId));
    }
}
