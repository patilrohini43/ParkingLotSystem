package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.enums.DriverType;
import com.bridgelabz.parkinglot.enums.VehicleType;
import com.bridgelabz.parkinglot.model.Vehicle;

import java.time.LocalDateTime;

public class ParkingSlot {

    Vehicle vehicle;
    int slotnumber;
    LocalDateTime localDateTime;
    DriverType type;
    VehicleType vehicleType;

    public ParkingSlot(Vehicle vehicle, LocalDateTime now, DriverType type, VehicleType vehicleType) {
        this.vehicle=vehicle;
        this.localDateTime=now;
        this.type=type;
        this.vehicleType=vehicleType;
    }

    public ParkingSlot(Object o) {
    }

    public ParkingSlot(int slotNumber) {
        this.slotnumber=slotNumber;
    }

    public int getSlotnumber() {
        return slotnumber;
    }

    public void setSlotnumber(int slotnumber) {
        this.slotnumber = slotnumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

}
