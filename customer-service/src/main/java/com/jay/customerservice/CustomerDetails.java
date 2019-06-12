package com.jay.customerservice;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDetails extends Customer {
    private List<Account> accountList;
}
