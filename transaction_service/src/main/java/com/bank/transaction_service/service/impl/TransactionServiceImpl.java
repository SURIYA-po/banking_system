
package com.bank.transaction_service.service.impl;

import com.bank.transaction_service.client.AccountClient;
import com.bank.transaction_service.dto.TransferRequest;
import com.bank.transaction_service.entity.Transaction;
import com.bank.transaction_service.repository.TransactionRepository;
import com.bank.transaction_service.service.TransactionService;
import com.bank.transaction_service.exception.InsufficientBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repo;
    private final AccountClient accountClient;

    @Override
    @Transactional
    public Transaction deposit(UUID accountId, Double amount, String remarks) {
        // call account service to credit
        Map<String, Object> resp = accountClient.credit(accountId, amount);
        // you may inspect resp for success/failure
        Transaction t = Transaction.builder()
                .accountId(accountId)
                .type("DEPOSIT")
                .amount(amount)
                .remarks(remarks)
                .currency("USD")
                .createdAt(Instant.now())
                .build();
        return repo.save(t);
    }

    @Override
    @Transactional
    public Transaction withdraw(UUID accountId, Double amount, String remarks) {
        // optionally check balance
        Map<String, Object> bal = accountClient.getBalance(accountId);
        Double current = Double.valueOf(bal.getOrDefault("balance", 0).toString());
        if (current < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        accountClient.debit(accountId, amount);
        Transaction t = Transaction.builder()
                .accountId(accountId)
                .type("WITHDRAW")
                .amount(amount)
                .remarks(remarks)
                .currency("USD")
                .createdAt(Instant.now())
                .build();
        return repo.save(t);
    }

    @Override
    @Transactional
    public Transaction transfer(TransferRequest request) {
        // 1. withdraw from source (account service will validate)
        Map<String, Object> balanceResp = accountClient.getBalance(request.getFromAccount());
        Double current = Double.valueOf(balanceResp.getOrDefault("balance", 0).toString());
        if (current < request.getAmount()) {
            throw new InsufficientBalanceException("Insufficient funds");
        }

        // debit source
        accountClient.debit(request.getFromAccount(), request.getAmount());
        // credit destination
        accountClient.credit(request.getToAccount(), request.getAmount());

        // create transaction record for debit
        Transaction debitTxn = Transaction.builder()
                .accountId(request.getFromAccount())
                .relatedAccount(request.getToAccount())
                .type("TRANSFER_DEBIT")
                .amount(request.getAmount())
                .remarks(request.getRemarks())
                .currency("USD")
                .createdAt(Instant.now())
                .build();
        repo.save(debitTxn);

        // create transaction record for credit
        Transaction creditTxn = Transaction.builder()
                .accountId(request.getToAccount())
                .relatedAccount(request.getFromAccount())
                .type("TRANSFER_CREDIT")
                .amount(request.getAmount())
                .remarks(request.getRemarks())
                .currency("USD")
                .createdAt(Instant.now())
                .build();
        repo.save(creditTxn);

        return debitTxn;
    }

    @Override
    public List<Transaction> getTransactionsForAccount(UUID accountId) {
        return repo.findByAccountIdOrderByCreatedAtDesc(accountId);
    }
}
