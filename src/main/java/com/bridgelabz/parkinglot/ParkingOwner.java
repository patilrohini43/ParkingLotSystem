package com.bridgelabz.parkinglot;

public class ParkingOwner implements ParkingObserver{
    private boolean isFullCapacity;
    private boolean isSpaceAvaible;

    @Override
    public void capacityIsFull()
    {
        this.isFullCapacity=true;
    }
    public boolean isCapacityFull() {
        return isFullCapacity;
    }
    @Override
    public void spaceAvailablity() {
        this.isSpaceAvaible = false;
    }

    public boolean isSpaceAvaible() {
        return isSpaceAvaible;
    }
}
