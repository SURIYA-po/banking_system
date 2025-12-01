package com.bank.account_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;        // Owner of the account

    private String accountNumber;

    private Double balance;

    private String accountType;   // Savings, Current, etc.
}
