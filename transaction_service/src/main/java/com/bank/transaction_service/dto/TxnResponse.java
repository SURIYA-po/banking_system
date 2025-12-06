package com.bank.transaction_service.dto;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;
import java.time.Instant;

@Data
@Builder
public class TxnResponse {
    private UUID transactionId;
    private UUID accountId;
    private String type;
    private Double amount;
    private Instant createdAt;
}
