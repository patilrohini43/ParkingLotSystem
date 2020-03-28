package com.bridgelabz.parkinglot;

public class ParkingOwner implements ParkingObserver{
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
