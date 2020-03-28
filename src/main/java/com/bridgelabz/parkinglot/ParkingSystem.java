package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.model.Car;

import java.util.ArrayList;
import java.util.List;


public class ParkingSystem {

    private int currentCapacity;
    private int actualCapacity;
    List vehicle;
    private ParkingOwner owner;


    public ParkingSystem(int capacity){
        this.vehicle=new ArrayList();
        this.currentCapacity =0;
        this.actualCapacity=capacity;
    }

    public void addParkSystem(Object vehicle) throws ParkingLotException {
        System.out.println(this.vehicle.size());
        if(this.vehicle.size() == this.actualCapacity)
        {
            owner.capacityIsFull();
           throw new ParkingLotException("Lot already full");
        }
        this.currentCapacity++;
        System.out.println(currentCapacity+"capacity");
        this.vehicle.add(vehicle);
    }

    public boolean unParkSystem(Object vehicle) {
        if(vehicle == null)
        {
            return false;
        }
        if(this.vehicle.contains(vehicle))
        {
            this.vehicle.remove(vehicle);
            return true;
        }
        return false;
    }

    public boolean vehiclePark(Object vehicle) {
        if(this.vehicle.contains(vehicle))
            return true;
        else
            return false;
    }

    public void registerOwner(ParkingOwner owner) {
        this.owner=owner;
    }

    public void setCapacity(int capacity) {
        this.actualCapacity=capacity;
    }
}
