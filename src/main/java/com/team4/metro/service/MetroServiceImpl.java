//package com.team4.metro.service;
//
//import com.team4.metro.entity.User;
//import com.team4.metro.entity.Station;
//import com.team4.metro.entity.Transaction;
//import com.team4.metro.exception.InsufficientBalanceException;
//import com.team4.metro.exception.InvalidStationException;
//import com.team4.metro.exception.UserNotFoundException;
//import com.team4.metro.persistence.*;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class MetroServiceImpl implements MetroService {
//    private UserDAO userDAO;
//    private StationDAO stationDAO;
//    private TransactionDAO transactionDAO;
//
//    public MetroServiceImpl() {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", "root", "YOUrmi123@");
//            this.userDAO = new UserDAOImpl(connection);
//            this.stationDAO = new StationDAOImpl(connection);
//            this.transactionDAO = new TransactionDAOImpl(connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
////    @Override
////    public void swipeIn(int userId, String sourceStationName) throws InsufficientBalanceException {
////        User user = userDAO.getUserById(userId);
////        if (user.getBalance() < 20) {
////            throw new InsufficientBalanceException("Insufficient balance to swipe in.");
////        }
////        System.out.println("You have successfully swiped in at the station " + sourceStationName);
////    }
//    
//    @Override
//    public void swipeIn(int userId, String sourceStationName) throws InsufficientBalanceException {
//        User user = getUserById(userId); // Ensures user exists or throws exception
//        if (user.getBalance() < 20) {
//            throw new InsufficientBalanceException("Insufficient balance to swipe in.");
//        }
//        
//     // Validate if station exists
//        Station station = stationDAO.getStationByName(sourceStationName);
//        if (station == null) {
//            throw new InvalidStationException("Invalid station: " + sourceStationName + " does not exist.");
//        }
//        
//        System.out.println("You have successfully swiped in at the station " + sourceStationName);
//    }
//
//    
//
////    @Override
////    public void swipeOut(int userId, String destinationStationName) {
////        User user = userDAO.getUserById(userId);
////        int sourceStationId = 1; // This should be dynamically set during the swipe-in
////        int destinationStationId = stationDAO.getStationIdByName(destinationStationName);
////
////        int fare = Math.abs(destinationStationId - sourceStationId) * 5;
////
////        double newBalance = user.getBalance() - fare;
////        userDAO.updateUserBalance(userId, newBalance);
////
////        Transaction transaction = new Transaction(0, userId, sourceStationId, destinationStationId, LocalDateTime.now(), LocalDateTime.now());
////        transactionDAO.addTransaction(transaction);
////
////        System.out.println("You have successfully swiped out with card balance as " + newBalance);
////    }
//
//    @Override
//    public void swipeOut(int userId, String destinationStationName) {
//        User user = getUserById(userId); // Ensures user exists or throws exception
//        
//        int sourceStationId = 1; // Should be dynamically set
//        int destinationStationId = stationDAO.getStationIdByName(destinationStationName);
//        if (destinationStationId == -1) {
//            throw new InvalidStationException("Invalid station: " + destinationStationName + " does not exist.");
//        }
//
//        int fare = Math.abs(destinationStationId - sourceStationId) * 5;
//
//        double newBalance = user.getBalance() - fare;
//        userDAO.updateUserBalance(userId, newBalance);
//
//        Transaction transaction = new Transaction(0, userId, sourceStationId, destinationStationId, LocalDateTime.now(), LocalDateTime.now());
//        transactionDAO.addTransaction(transaction);
//        
//        System.out.println("Fare :" +fare);
//        System.out.println("You have successfully swiped out with card balance as " + newBalance);
//    }
//
//    
//
////    @Override
////    public User getUserById(int userId) {
////        return userDAO.getUserById(userId);
////    }
//    
//    @Override
//    public User getUserById(int userId) {
//        User user = userDAO.getUserById(userId);
//        if (user == null) {
//            throw new UserNotFoundException("User with ID " + userId + " not found.");
//        }
//        return user;
//    }
//
//    @Override
//    public User addUser(String name, String phoneNo, double balance) {
//        User user = new User(0, name, balance, phoneNo);
//        userDAO.addUser(user);
//        return user;
//    }
//
//    @Override
//    public List<String> getAllStations() { // Implementing getAllStations()
//        return stationDAO.getAllStations();
//    }
//    
//    
//    @Override
//    public void rechargeBalance(int userId, double amount) {
//        User user = userDAO.getUserById(userId);
//        if (amount <= 0) {
//            System.out.println("Invalid recharge amount. Please enter a valid amount.");
//            return;
//        }
//        double newBalance = user.getBalance() + amount;
//        userDAO.updateUserBalance(userId, newBalance);
//        System.out.println("Recharge successful! New balance: " + newBalance);
//    }
//
//}



package com.team4.metro.service;

import com.team4.metro.entity.User;
import com.team4.metro.entity.Station;
import com.team4.metro.entity.Transaction;
import com.team4.metro.exception.InsufficientBalanceException;
import com.team4.metro.exception.InvalidStationException;
import com.team4.metro.exception.UserNotFoundException;
import com.team4.metro.persistence.*;
import com.team4.metro.util.DatabaseConnection;


import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;


public class MetroServiceImpl implements MetroService {
	private UserDAO userDAO;
    private StationDAO stationDAO;
    private TransactionDAO transactionDAO;

    public MetroServiceImpl() {
        this.userDAO = new UserDAOImpl();
        this.stationDAO = new StationDAOImpl();
        this.transactionDAO = new TransactionDAOImpl();
    }
    @Override
    public void swipeIn(int userId, String sourceStationName) throws InsufficientBalanceException {
        User user = getUserById(userId); // Ensures user exists or throws exception
        if (user.getBalance() < 20) {
            throw new InsufficientBalanceException("Insufficient balance to swipe in.");
        }

        // Validate if station exists
        Station station = stationDAO.getStationByName(sourceStationName);
        if (station == null) {
            throw new InvalidStationException("Invalid station: " + sourceStationName + " does not exist.");
        }

        System.out.println("You have successfully swiped in at the station " + sourceStationName);
    }

    
    @Override
    public void swipeOut(int userId, String destinationStationName) {
        User user = getUserById(userId);

        int sourceStationId = 1; // Should be dynamically set
        int destinationStationId = stationDAO.getStationIdByName(destinationStationName);
        if (destinationStationId == -1) {
            throw new InvalidStationException("Invalid station: " + destinationStationName + " does not exist.");
        }

        int fare = Math.abs(destinationStationId - sourceStationId) * 5;
        double newBalance = user.getBalance() - fare;
        userDAO.updateUserBalance(userId, newBalance);

        Transaction transaction = new Transaction(0, userId, sourceStationId, destinationStationId, LocalDateTime.now(), LocalDateTime.now());
        transactionDAO.addTransaction(transaction); // ✅ No more NullPointerException

        System.out.println("Fare: " + fare);
        
//        System.out.println("You have successfully swiped out with card balance as " + newBalance);
        System.out.println("✅ You have successfully swiped in at "+newBalance);
        System.out.println("Swipe In Time:"+transaction.getSwipeInTime());
        System.out.println("Swipe Out Time:"+transaction.getSwipeOutTime());
    }


    @Override
    public User getUserById(int userId) {
        User user = userDAO.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        return user;
    }

    @Override
    public User addUser(String name, String phoneNo, double balance) {
        User user = new User(0, name, balance, phoneNo);
        userDAO.addUser(user);
        return user;
    }

    @Override
    public List<String> getAllStations() {
        return stationDAO.getAllStations();
    }

    @Override
    public void rechargeBalance(int userId, double amount) {
        User user = userDAO.getUserById(userId);
        if (amount <= 0) {
            System.out.println("Invalid recharge amount. Please enter a valid amount.");
            return;
        }
        double newBalance = user.getBalance() + amount;
        userDAO.updateUserBalance(userId, newBalance);
        System.out.println("Recharge successful! New balance: " + newBalance);
    }
}



