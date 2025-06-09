package com.team4.metro.persistence;

import com.team4.metro.entity.Transaction;

public interface TransactionDAO {
    void addTransaction(Transaction transaction);
}