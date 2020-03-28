package com.bridgelabz.parkinglot;

public class AirportSecurity implements ParkingObserver{
    private boolean isFullCapacity;

    @Override
    public void capacityIsFull()
    {
        this.isFullCapacity=true;
    }

    public boolean isCapacityFull() {
        return isFullCapacity;
    }
}
