package com.jay.customerservice;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;


}
