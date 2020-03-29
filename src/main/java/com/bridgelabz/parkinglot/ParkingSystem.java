package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class ParkingSystem {

    private int currentCapacity;
    private int actualCapacity;
    List vehicle;
    List<ParkingSlot> slotList;
    private List<ParkingObserver> observerList;


    public ParkingSystem(int capacity){
        this.slotList=new ArrayList<>();
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

    public void listOfSlot() {
        IntStream.range(0, actualCapacity)
                .forEach(slotNumber -> slotList.add(new ParkingSlot(slotNumber)));
    }

    public List<ParkingSlot> addParkSystem(int slot, Object vehicle) {
        this.slotList.get(slot).setVehicle(vehicle);
        return slotList;
    }


    public List<Integer> getEmptyAvailableSlot() {
        System.out.println(slotList.size()+"size");
        List<Integer> emptyListSlot=new ArrayList<>();
        for(ParkingSlot slot1:slotList)
        {
            if(slot1.getVehicle() == null)
            {
                emptyListSlot.add(slot1.getSoltNumber());
            }
        }
        return emptyListSlot;
    }
}
