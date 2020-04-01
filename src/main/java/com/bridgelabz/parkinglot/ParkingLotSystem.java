package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.exception.ParkingLotException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class ParkingLotSystem {

    private int currentCapacity;
    private int actualCapacity;
    List vehicle;
    List<ParkingSlot> slotList;
    private List<ParkingLotObserver> observerList;
    ParkingSlot slottime;
    int minfee = 2;


    public ParkingLotSystem(int capacity){
        this.slotList=new ArrayList<>();
        this.vehicle=new ArrayList();
        this.currentCapacity =0;
        this.actualCapacity=capacity;
        this.observerList=new ArrayList<>();
        this.slottime=new ParkingSlot();
    }

    public void addParkSystem(Object vehicle) throws ParkingLotException {
        if(vehiclePark(vehicle))
        {
            throw new ParkingLotException("Vehicle already park");
        }
        System.out.println(this.vehicle.size());
        if(this.vehicle.size() == this.actualCapacity)
        {
            for(ParkingLotObserver observer:observerList)
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
            for (ParkingLotObserver observer:observerList)
            {
                observer.spaceAvailablity();
            }
            return true;
        }
        return false;
    }

    public void registerParkingObserver(ParkingLotObserver observer) {
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
        System.out.println(slotList.size());
        this.slotList.get(slot).setVehicle(vehicle);
        this.slotList.get(slot).setLocalDateTime(LocalDateTime.now());
        return slotList;
    }


    public List<Integer> getEmptyAvailableSlot() {
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

    public boolean findvehicle(Object vehicle) {
        for(ParkingSlot slot1:slotList)
        {
            if(slot1.getVehicle().equals(vehicle))
            {
                return true;
            }
        }
        return false;
    }

    public void unParkSystem(int slot, Object object) throws ParkingLotException {
        if(vehiclePark(object) == true)
        {
            this.slotList.remove(slot);
        }
        throw new ParkingLotException("Vehicle not Found");
    }

    public LocalDateTime getVehicleParkTime(int slot, Object vehicle) throws ParkingLotException {
        if(this.slotList.get(slot).getVehicle().equals(vehicle)){
            return this.slotList.get(slot).getLocalDateTime();
        }
        throw new ParkingLotException("Vehicle Not Park");
    }

    public boolean vehiclePark(Object vehicle) {
        if(this.vehicle.contains(vehicle))
            return true;
        else
            return false;
    }
}
