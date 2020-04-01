package com.bridgelabz.parkinglot;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

public class ParkingSlot {
    int soltNumber;
    Object vehicle;
    LocalDateTime localDateTime;

    public ParkingSlot() {

    }

    public ParkingSlot(Object vehicle, LocalDateTime localDateTime) {
        this.vehicle = vehicle;
        this.localDateTime = localDateTime;
    }

    public ParkingSlot(int slotNumber) {
        this.soltNumber=slotNumber;
    }

    public int getSoltNumber() {
        return soltNumber;
    }

    public void setSoltNumber(int soltNumber) {
        this.soltNumber = soltNumber;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Object getVehicle() {
        return vehicle;
    }

    public void setVehicle(Object vehicle) {
        this.vehicle = vehicle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return soltNumber == that.soltNumber &&
                Objects.equals(vehicle, that.vehicle);
    }
}
