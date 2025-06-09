package com.team4.metro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private double balance;
    private String phoneNo;
    public void updateBalance(double amount) {
        this.balance += amount;
    }
    
}