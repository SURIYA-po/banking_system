package com.bank.account_service.service.impl;

import com.bank.account_service.entity.Account;
import com.bank.account_service.repository.AccountRepository;
import com.bank.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;

    @Override
    public Account createAccount(Account account) {
        account.setBalance(0.0);
        return repo.save(account);
    }

    @Override
    public Account getAccountByNumber(String accNum) {
        return repo.findByAccountNumber(accNum)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public List<Account> getAllAccounts() {
        return repo.findAll();
    }

    @Override
    public Account deposit(String accNum, Double amount) {
        Account account = getAccountByNumber(accNum);
        account.setBalance(account.getBalance() + amount);
        return repo.save(account);
    }

    @Override
    public Account withdraw(String accNum, Double amount) {
        Account account = getAccountByNumber(accNum);

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        return repo.save(account);
    }
}
