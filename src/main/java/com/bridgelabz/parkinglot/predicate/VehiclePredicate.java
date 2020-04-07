package com.bridgelabz.parkinglot.predicate;

import com.bridgelabz.parkinglot.ParkingSlot;
import com.bridgelabz.parkinglot.enums.CarName;
import com.bridgelabz.parkinglot.enums.DriverType;
import com.bridgelabz.parkinglot.enums.VehicleType;
import com.bridgelabz.parkinglot.model.Vehicle;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class VehiclePredicate {

    public static List<ParkingSlot> parkingSlotList;

    public static void listOfSlot(List<ParkingSlot> slotList) {
        parkingSlotList=slotList;
    }

    public static IntPredicate filterBycolor(String color){
        return slot -> parkingSlotList.get(slot).getVehicle().getColor().equals(color);
    }

    public static IntPredicate filterBycolorAndName(String color, CarName carName){
        return slot -> parkingSlotList.get(slot).getVehicle().getColor().equals(color) && parkingSlotList.get(slot).getVehicle().getCarName().equals(carName);
    }

    public static IntPredicate filterByName(CarName carName){
        return slot -> parkingSlotList.get(slot).getVehicle().getCarName().equals(carName);
    }

    public static IntPredicate filterByTime(){
        return slot -> parkingSlotList.get(slot).getLocalDateTime().getMinute() < 30;
    }

    public static IntPredicate filterDriverTypeInfo(DriverType driverType, VehicleType vehicleType){
        return slot -> parkingSlotList.get(slot).getDriverType().equals(driverType) && parkingSlotList.get(slot).getVehicleType().equals(vehicleType);
    }

    public static IntPredicate filterCarByNumberPlate(int numberPlate){
        return slot -> parkingSlotList.get(slot).getVehicle().getNumberPlate() == numberPlate;
    }
}
