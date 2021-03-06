package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.enums.CarName;
import com.bridgelabz.parkinglot.enums.DriverType;
import com.bridgelabz.parkinglot.enums.VehicleType;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.model.Vehicle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
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

    public void parkVehicle(int i, Vehicle vehicle, DriverType driverType, VehicleType vehicleType) throws ParkingLotException {
        ParkingLot parkingLot= getParkingLotEmptyList();
        parkingLot.parkInSlot(i,vehicle,driverType,vehicleType);
    }

    public void unparkVehicle(int i, Object object){
        for (ParkingLot parkingLot:parkingLotList) {
            parkingLot.unParkInSlot(i, object);
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

    public List<Vehicle> getAllCarsParkedInParkingLot() throws ParkingLotException {
        for(ParkingLot parkingLot:parkingLotList){
            return parkingLot.getAllCarsParkedInParkingLot();
        }
        throw new ParkingLotException("ParkingLot full");
    }

    public  Map<Integer,Vehicle> filterByPredicate(IntPredicate predicate) throws ParkingLotException {
        for(ParkingLot parkingLot:parkingLotList){
            return parkingLot.filterByPredicate(predicate);
        }
        throw new ParkingLotException("Vehicle Not Found");
    }
}
