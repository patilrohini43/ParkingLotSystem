package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotSystem {

    List<ParkingLot> parkingLotList;

    public ParkingLotSystem() {
        this.parkingLotList = new ArrayList<>();
    }

    public void add(ParkingLot parkingLot) {
        this.parkingLotList.add(parkingLot);
    }

    public ParkingLot getParkingLotEmptyList(){
    ParkingLot parkingLot= this.parkingLotList.stream().sorted(Comparator.comparing(list -> list.getEmptyAvailableSlot().size(), Comparator.reverseOrder())).collect(Collectors.toList()).get(0);
    return parkingLot;
    }

    public void parkVehicle(int i, Object object, DriverType driverType) throws ParkingLotException {
        ParkingLot parkingLot= getParkingLotEmptyList();
        parkingLot.addParkSystem(i,object,driverType);
    }

    public void unparkVehicle(int i, Object object){
        for (ParkingLot parkingLot:parkingLotList) {
            parkingLot.unParkSystem(i, object);
            return;
        }
    }

    public boolean findVehicleInparkingLot(Object vehicle) throws ParkingLotException {
        for(ParkingLot parkingLot:parkingLotList)
        {
            return parkingLot.findvehicle(vehicle);
        }
        return false;
    }

    public List<Integer> getParkingLotAvailableSlotList() throws ParkingLotException {
        for (ParkingLot parkingLot:parkingLotList)
          return parkingLot.getEmptyAvailableSlot();
        throw new ParkingLotException("ParkingLot full");
    }
}
