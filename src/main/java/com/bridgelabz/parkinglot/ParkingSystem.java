package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.model.Car;


public class ParkingSystem {

    private int currentCapacity;
    private final int actualCapacity;
    private Object vehicle;
    private ParkingOwner owner;

    public ParkingSystem(int capacity){
        this.currentCapacity =0;
        this.actualCapacity=capacity;
    }

    public void addParkSystem(Object vehicle) throws ParkingLotException {
        if(this.currentCapacity == this.actualCapacity)
        {
            owner.capacityIsFull();
           throw new ParkingLotException("Lot already full");
        }
        this.currentCapacity++;
        System.out.println(currentCapacity+"capacity");
        this.vehicle=vehicle;
    }

    public boolean unParkSystem(Car car) {
        if(car == null)
        {
            return false;
        }
        if(this.vehicle.equals(car))
        {
            this.vehicle=null;
            return true;
        }
        return false;
    }

    public boolean vehiclePark(Object vehicle) {
        if(this.vehicle.equals(vehicle))
            return true;
        else
            return false;
    }

    public void registerOwner(ParkingOwner owner) {
        this.owner=owner;
    }
}
