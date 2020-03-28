package com.bridgelabz.parkinglot.exception;

public class ParkingLotException extends Exception {

    enum ExceptionType {
        CENSUS_FILE_PROBLEM,TYPE_NOTFOUND,HEADERNOTFOUND
    }

    ExceptionType type;

    public ParkingLotException(String message) {
        super(message);
    }
}
