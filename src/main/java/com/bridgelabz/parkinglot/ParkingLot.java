package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.enums.CarName;
import com.bridgelabz.parkinglot.enums.DriverType;
import com.bridgelabz.parkinglot.enums.VehicleType;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.model.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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


    public void registerParkingObserver(ParkingLotObserver observer) {
        this.observerList.add(observer);
    }

    public void setCapacity(int capacity) {
        this.actualCapacity=capacity;
    }

    public void parkInSlot(Object vehicle) throws ParkingLotException {
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

    public boolean unParkInSlot(Object vehicle) {
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

    public int parkingSlotCapacity() {
        IntStream.range(0, actualCapacity)
                .forEach(slotNumber -> slotList.add(null));
        return slotList.size();
    }

    public boolean parkInSlot(int slot, Vehicle vehicle, DriverType driverType, VehicleType vehicleType) throws ParkingLotException {
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
        this.slotList.set(slot,new ParkingSlot(vehicle,LocalDateTime.now(),driverType,vehicleType));
        return true;
    }

    public boolean unParkInSlot(int slot, Object object)  {
        if(idExists(object))
        {
            this.slotList.set(slot,new ParkingSlot(null));
            for (ParkingLotObserver observer:observerList)
            {
                observer.spaceAvailablity();
            }
            return true;
        }
        return false;
    }

    private boolean idExists(Object vehicle) {
        boolean idExists = slotList.stream()
                .anyMatch(t -> t.getVehicle().equals(vehicle));
        System.out.println(idExists);
        return idExists;
    }

    public ArrayList<Integer> getDrivertypeWiselist(DriverType type, VehicleType vehicleType) {
        return vehicleType.checkSlot(this.getEmptyAvailableSlot(),type);
    }

    public  ArrayList<Integer> getEmptyAvailableSlot() {
        ArrayList<Integer> emptyListSlot=new ArrayList<>();
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


    public LocalDateTime getVehicleParkTime(int slot, Object vehicle) throws ParkingLotException {
        for(ParkingSlot slottime:slotList)
        {
            if(slottime.getVehicle() == vehicle && slottime.getSlotnumber() == slot)
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

    public List<Integer> getLocationofCarsInSlot(String white) {
        List<Integer> colorCarList=new ArrayList<>();
        IntStream.range(0, actualCapacity)
                .filter(slot -> slotList.get(slot) != null)
                .filter(slot -> slotList.get(slot).getVehicle().getColor().equals(white))
                .forEach(slot -> colorCarList.add(slot));
        return colorCarList;
    }

    public  Map<Integer,Integer> getLocationofCarsInSlotByNameAndColor(String color, CarName name) {
        Map<Integer,Integer> carList=new HashMap<>();
        IntStream.range(0, actualCapacity)
                .filter(slot -> slotList.get(slot) != null)
                .filter(slot -> slotList.get(slot).getVehicle().getColor().equals(color) && slotList.get(slot).getVehicle().getCarName().equals(name))
                .forEach(slot -> carList.put(slot,slotList.get(slot).getVehicle().getNumberPlate()));
        return carList;
    }

    public List<Integer> getVehicleByName(CarName carName) {
        List<Integer> carList=new ArrayList<>();
        IntStream.range(0, actualCapacity)
                .filter(slot -> slotList.get(slot) != null)
                .filter(slot -> slotList.get(slot).getVehicle().getCarName().equals(carName))
                .forEach(carList::add);
        return carList;
    }

    public List<Integer> getVehicleByTime() {
        List<Integer> carList=new ArrayList<>();
        IntStream.range(0, actualCapacity)
                .filter(slot -> slotList.get(slot) != null)
                .filter(slot -> slotList.get(slot).getLocalDateTime().getMinute() < 30)
                .forEach(carList::add);
        return carList;
    }
}
