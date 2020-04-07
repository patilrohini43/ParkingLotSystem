package com.bridgelabz.parkinglot.exception;

public class ParkingLotException extends Exception {

    enum ExceptionType {
        Not_Found
    }

    ExceptionType type;

    public ParkingLotException(String message) {
        super(message);
    }
}
