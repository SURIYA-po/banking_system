package com.bank.transaction_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue
    private UUID transactionId;

    private UUID accountId;        // account where transaction applies
    private UUID relatedAccount;   // other account in case of transfer (nullable)
    private String type;           // DEPOSIT / WITHDRAW / TRANSFER
    private Double amount;
    private String currency;
    private String remarks;
    private Instant createdAt;
}
