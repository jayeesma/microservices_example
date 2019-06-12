package com.jay.customerservice;

import lombok.Data;

@Data
public class Account {
    private long id;
    private String type;
    private double balance;
}
