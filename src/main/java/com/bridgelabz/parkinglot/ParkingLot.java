package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.exception.ParkingLotException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ParkingLot {

    private int currentCapacity;
    private int actualCapacity;
    List vehicle;
    List<ParkingSlot> slotList;
    private List<ParkingLotObserver> observerList;

    public ParkingLot(int capacity){
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

    public int listOfSlot() {
        IntStream.range(0, actualCapacity)
                .forEach(slotNumber -> slotList.add(null));
        return slotList.size();
    }

    public boolean addParkSystem(int slot, Object vehicle) throws ParkingLotException {
        if(slotList.contains(vehicle)){
            throw new ParkingLotException("already park");
        }
        if (slotList.size() == actualCapacity && !slotList.contains(null))
        {
            for(ParkingLotObserver observer:observerList)
            {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Lot full");
        }
        this.slotList.set(slot,new ParkingSlot(vehicle,LocalDateTime.now()));
        return true;
    }

    private boolean idExists(Object vehicle) {
        boolean idExists = slotList.stream()
                .anyMatch(t -> t.getVehicle().equals(vehicle));
        return idExists;
    }

    public List<Integer> getEmptyAvailableSlot() {

        List<Integer> emptyListSlot=new ArrayList<>();
        IntStream.range(0, actualCapacity)
                .filter(slot -> slotList.get(slot) == null)
                .forEach(slot -> emptyListSlot.add(slot));
        return emptyListSlot;
    }

    public boolean findvehicle(Object vehicle) throws ParkingLotException {
        for (ParkingSlot slot1:slotList){

            if (slot1.getVehicle() == vehicle){
                return true;
            }
        }
        throw new ParkingLotException("Vehicle Not Found");
    }

    public void unParkSystem(int slot, Object object) throws ParkingLotException {

        if(this.slotList.contains(object))
        {
            this.slotList.set(slot,null);
            for (ParkingLotObserver observer:observerList)
            {
                observer.spaceAvailablity();
            }
        }
        throw new ParkingLotException("Vehicle not Found");
    }

    public LocalDateTime getVehicleParkTime(int slot, Object vehicle) throws ParkingLotException {
        for(ParkingSlot slottime:slotList)
        {
            if(slottime.getVehicle() == vehicle)
                return slottime.localDateTime;
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
