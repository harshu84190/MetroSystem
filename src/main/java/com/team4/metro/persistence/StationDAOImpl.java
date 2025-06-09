//package com.team4.metro.persistence;
//
//import com.team4.metro.entity.Station;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class StationDAOImpl implements StationDAO {
//    private Connection connection;
//
//    public StationDAOImpl(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public Station getStationByName(String name) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM stations WHERE name = ?");
//            statement.setString(1, name);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return new Station(
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getInt("station_index")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public int getStationIdByName(String name) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT id FROM stations WHERE name = ?");
//            statement.setString(1, name);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getInt("id");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    @Override
//    public List<String> getAllStations() {
//        List<String> stations = new ArrayList<>();
//        try {
//            String query = "SELECT name FROM stations";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                stations.add(resultSet.getString("name"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return stations;
//    }
//}


package com.team4.metro.persistence;

import com.team4.metro.entity.Station;
import com.team4.metro.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationDAOImpl implements StationDAO {

    @Override
    public List<String> getAllStations() {
        List<String> stations = new ArrayList<>();
        String query = "SELECT name FROM stations";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                stations.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return stations;
    }

    @Override
    public Station getStationByName(String stationName) {
        String query = "SELECT * FROM stations WHERE name = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, stationName);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return new Station(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public int getStationIdByName(String stationName) {
        String query = "SELECT id FROM stations WHERE name = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, stationName);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1; // Return -1 if station not found
    }
}

