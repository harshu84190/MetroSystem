package com.team4.metro.persistence;

import com.team4.metro.entity.Station;
import java.util.List;

public interface StationDAO {
    Station getStationByName(String name);
    int getStationIdByName(String name);
    List<String> getAllStations();  
}


