package com.bank.transaction_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class TransferRequest {
    @NotNull private UUID fromAccount;
    @NotNull private UUID toAccount;
    @NotNull private Double amount;
    private String remarks;
}
