package com.bank.transaction_service.service;

import com.bank.transaction_service.dto.TransferRequest;
import com.bank.transaction_service.entity.Transaction;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction deposit(UUID accountId, Double amount, String remarks);
    Transaction withdraw(UUID accountId, Double amount, String remarks);
    Transaction transfer(TransferRequest request);
    List<Transaction> getTransactionsForAccount(UUID accountId);
}
