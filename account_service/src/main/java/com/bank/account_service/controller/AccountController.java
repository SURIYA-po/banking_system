package com.bank.account_service.controller;

import com.bank.account_service.entity.Account;
import com.bank.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public Account create(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{accNum}")
    public Account get(@PathVariable String accNum) {
        return accountService.getAccountByNumber(accNum);
    }

    @GetMapping
    public List<Account> all() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/deposit/{accNum}/{amount}")
    public Account deposit(@PathVariable String accNum, @PathVariable Double amount) {
        return accountService.deposit(accNum, amount);
    }

    @PutMapping("/withdraw/{accNum}/{amount}")
    public Account withdraw(@PathVariable String accNum, @PathVariable Double amount) {
        return accountService.withdraw(accNum, amount);
    }
}
