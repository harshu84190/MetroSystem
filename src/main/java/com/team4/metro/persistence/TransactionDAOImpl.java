//package com.team4.metro.persistence;
//
//import com.team4.metro.entity.Transaction;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class TransactionDAOImpl implements TransactionDAO {
//    private Connection connection;
//
//    public TransactionDAOImpl(Connection connection) {
//        this.connection = connection;
//    }
//
//    public TransactionDAOImpl() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//    public void addTransaction(Transaction transaction) {
//        String sql = "INSERT INTO transactions (user_id, source_station_id, destination_station_id, swipe_in_time, swipe_out_time) VALUES (?, ?, ?, ?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, transaction.getUserId());
//            statement.setInt(2, transaction.getSourceStationId());
//            statement.setInt(3, transaction.getDestinationStationId());
//            // Use setObject to directly use LocalDateTime
//            statement.setObject(4, transaction.getSwipeInTime());
//            statement.setObject(5, transaction.getSwipeOutTime());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}


//package com.team4.metro.persistence;
//
//import com.team4.metro.entity.Transaction;
//import com.team4.metro.util.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class TransactionDAOImpl implements TransactionDAO {
//    private Connection connection;
//
//    // Constructor that initializes connection
//    public TransactionDAOImpl() {
//        this.connection = DatabaseConnection.getConnection();
//    }
//
//    // Optional constructor if you want to pass a connection manually
//    public TransactionDAOImpl(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public void addTransaction(Transaction transaction) {
//        String sql = "INSERT INTO transactions (user_id, source_station_id, destination_station_id, swipe_in_time, swipe_out_time) VALUES (?, ?, ?, ?, ?)";
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, transaction.getUserId());
//            statement.setInt(2, transaction.getSourceStationId());
//            statement.setInt(3, transaction.getDestinationStationId());
//            statement.setObject(4, transaction.getSwipeInTime()); // Correct way to insert LocalDateTime
//            statement.setObject(5, transaction.getSwipeOutTime());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}


package com.team4.metro.persistence;

import com.team4.metro.entity.Transaction;
import com.team4.metro.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (user_id, source_station_id, destination_station_id, swipe_in_time, swipe_out_time) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); // ✅ Always get a fresh connection
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, transaction.getUserId());
            statement.setInt(2, transaction.getSourceStationId());
            statement.setInt(3, transaction.getDestinationStationId());
            statement.setObject(4, transaction.getSwipeInTime());
            statement.setObject(5, transaction.getSwipeOutTime());

            statement.executeUpdate();
//            System.out.println("Transaction added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
//            System.err.println("Failed to insert transaction.");
        }
    }
}

