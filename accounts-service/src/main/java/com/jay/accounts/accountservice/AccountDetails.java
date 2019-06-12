package com.jay.accounts.accountservice;

import lombok.Data;

@Data
public class AccountDetails {
    private long id;
    private String type;
    private double balance;
    private Customer customer;
}
