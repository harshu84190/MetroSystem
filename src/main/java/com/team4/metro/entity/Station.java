package com.team4.metro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    public Station(int int1, String string) {
		// TODO Auto-generated constructor stub
	}
	private int id;
    private String name;
    private int stationIndex;
}