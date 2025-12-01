package com.bank.account_service.service;

import com.bank.account_service.entity.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account getAccountByNumber(String accNum);
    List<Account> getAllAccounts();
    Account deposit(String accNum, Double amount);
    Account withdraw(String accNum, Double amount);
}
