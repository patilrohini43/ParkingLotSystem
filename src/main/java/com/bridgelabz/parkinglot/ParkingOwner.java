package com.bridgelabz.parkinglot;

public class ParkingOwner {
    private boolean isFullCapacity;

    public void capacityIsFull()
    {
        this.isFullCapacity=true;
    }
    public boolean isCapacityFull() {
        return isFullCapacity;
    }
}
