package com.jay.accounts.accountservice;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;
    @Column
    private double balance;
    @Column(name = "customer_id")
    private long customerId;


}
