package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.enums.DriverType;
import com.bridgelabz.parkinglot.enums.VehicleType;
import com.bridgelabz.parkinglot.model.Vehicle;

import java.time.LocalDateTime;

public class ParkingSlot {

    Vehicle vehicle;
    int slotnumber;
    LocalDateTime localDateTime;
    DriverType driverType;
    VehicleType vehicleType;

    public ParkingSlot(Vehicle vehicle, LocalDateTime now, DriverType type, VehicleType vehicleType) {
        this.vehicle=vehicle;
        this.localDateTime=now;
        this.driverType=type;
        this.vehicleType=vehicleType;
    }

    public ParkingSlot(Object o) {
    }
    public int getSlotnumber() {
        return slotnumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
