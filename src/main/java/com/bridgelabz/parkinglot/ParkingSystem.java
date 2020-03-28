package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.model.Car;

import java.util.ArrayList;
import java.util.List;


public class ParkingSystem {

    private int currentCapacity;
    private int actualCapacity;
    List vehicle;
    private List<ParkingObserver> observerList;


    public ParkingSystem(int capacity){
        this.vehicle=new ArrayList();
        this.currentCapacity =0;
        this.actualCapacity=capacity;
        this.observerList=new ArrayList<>();
    }

    public void addParkSystem(Object vehicle) throws ParkingLotException {
        if(vehiclePark(vehicle))
        {
            throw new ParkingLotException("Vehicle already park");
        }
        System.out.println(this.vehicle.size());
        if(this.vehicle.size() == this.actualCapacity)
        {
            for(ParkingObserver observer:observerList)
            {
                observer.capacityIsFull();
            }
           throw new ParkingLotException("Lot already full");
        }
        this.vehicle.add(vehicle);
    }

    public boolean unParkSystem(Object vehicle) {
        if(vehicle == null)
        {
            return false;
        }
        System.out.println(this.vehicle.size()+"size");
        if(this.vehicle.contains(vehicle))
        {
            this.vehicle.remove(vehicle);
            for (ParkingObserver observer:observerList)
            {
                observer.spaceAvailablity();
            }
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

    public void registerParkingObserver(ParkingObserver observer) {
       this.observerList.add(observer);
    }

    public void setCapacity(int capacity) {
        this.actualCapacity=capacity;
    }
}
