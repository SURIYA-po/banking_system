package com.bank.transaction_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "account-service")
public interface AccountClient {

    // debit account
    @PostMapping("/accounts/{accId}/debit")
    Map<String, Object> debit(@PathVariable("accId") UUID accId, @RequestParam("amount") Double amount);

    // credit account
    @PostMapping("/accounts/{accId}/credit")
    Map<String, Object> credit(@PathVariable("accId") UUID accId, @RequestParam("amount") Double amount);

    // get balance (optional)
    @GetMapping("/accounts/{accId}/balance")
    Map<String, Object> getBalance(@PathVariable("accId") UUID accId);
}
