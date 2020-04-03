package com.bridgelabz.parkinglot;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

public class ParkingSlot {

    Object vehicle;
    int slotnumber;
    LocalDateTime localDateTime;
    DriverType type;
    VehicleType vehicleType;

    public ParkingSlot( Object vehicle, LocalDateTime now, DriverType type,VehicleType vehicleType) {
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

    public Object getVehicle() {
        return vehicle;
    }

    public void setVehicle(Object vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

}
