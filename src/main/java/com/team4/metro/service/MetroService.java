package com.team4.metro.service;

import com.team4.metro.entity.User;
import com.team4.metro.exception.InsufficientBalanceException;

import java.util.List;

public interface MetroService {
    void swipeIn(int userId, String sourceStationName) throws InsufficientBalanceException;
    void swipeOut(int userId, String destinationStationName);
    User getUserById(int userId);
    User addUser(String name, String phoneNo, double balance);
    void rechargeBalance(int userId, double amount);

    List<String> getAllStations(); // Added function
}
