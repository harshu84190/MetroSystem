package com.team4.metro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private int id;
    private int userId;
    private int sourceStationId;
    private int destinationStationId;
    private LocalDateTime swipeInTime;
    private LocalDateTime swipeOutTime;
}