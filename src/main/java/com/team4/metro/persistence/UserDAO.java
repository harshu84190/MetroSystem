package com.team4.metro.persistence;

import com.team4.metro.entity.User;


public interface UserDAO {
    User getUserById(int userId);
    void updateUserBalance(int userId, double newBalance);
    void addUser(User user);
    
}
