//package com.team4.metro.persistence;
//
//
//import com.team4.metro.entity.User;
//
//import java.sql.*;
//
//public class UserDAOImpl implements UserDAO {
//    private Connection connection;
//
//    public UserDAOImpl(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public User getUserById(int userId){
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
//            statement.setInt(1, userId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return new User(
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getDouble("balance"),
//                        resultSet.getString("phone_no")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    
//    
//    @Override
//    public void updateUserBalance(int userId, double newBalance) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
//            statement.setDouble(1, newBalance);
//            statement.setInt(2, userId);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    @Override
//    public void addUser(User user) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, balance, phone_no) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, user.getName());
//            statement.setDouble(2, user.getBalance());
//            statement.setString(3, user.getPhoneNo());
//            statement.executeUpdate();
//            ResultSet keys = statement.getGeneratedKeys();
//            if (keys.next()) {
//                user.setId(keys.getInt(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
////    @Override
////    public void addBalance(int userId, double amount) {
////        User user = user.getUserById(userId);
////        if (user != null) {
////            user.updateBalance(amount);
////        }
////    }
//    
//}



//Giving the error

//package com.team4.metro.persistence;
//
//import com.team4.metro.entity.User;
//import com.team4.metro.util.DatabaseConnection;
//
//import java.sql.*;
//
//public class UserDAOImpl implements UserDAO {
//
//    @Override
//    public User getUserById(int userId) {
//        String query = "SELECT * FROM users WHERE id = ?";
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//             
//            statement.setInt(1, userId);
//            ResultSet resultSet = statement.executeQuery();
//            
//            if (resultSet.next()) {
//                return new User(
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getDouble("balance"),
//                        resultSet.getString("phone_no")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null; // User not found
//    }
//
//    @Override
//    public void updateUserBalance(int userId, double newBalance) {
//        String query = "UPDATE users SET balance = ? WHERE id = ?";
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//             
//            statement.setDouble(1, newBalance);
//            statement.setInt(2, userId);
//            statement.executeUpdate();
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void addUser(User user) {
//        String query = "INSERT INTO users (name, balance, phone_no) VALUES (?, ?, ?)";
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//             
//            statement.setString(1, user.getName());
//            statement.setDouble(2, user.getBalance());
//            statement.setString(3, user.getPhoneNo());
//            statement.executeUpdate();
//            
//            ResultSet keys = statement.getGeneratedKeys();
//            if (keys.next()) {
//                user.setId(keys.getInt(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//	@Override
//	public void addBalance(int userId, double amount) {
//		// TODO Auto-generated method stub
//		
//	}
//}



package com.team4.metro.persistence;

import com.team4.metro.entity.User;
import com.team4.metro.util.DatabaseConnection;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    
    @Override
    public User getUserById(int userId) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("phone_no")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUserBalance(int userId, double newBalance) {
        String query = "UPDATE users SET balance = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setDouble(1, newBalance);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (name, balance, phone_no) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
             
            statement.setString(1, user.getName());
            statement.setDouble(2, user.getBalance());
            statement.setString(3, user.getPhoneNo());
            statement.executeUpdate();
            
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
