package com.bridgelabz.parkinglot;
import java.sql.Time;
import java.util.Objects;

public class ParkingSlot {
    int soltNumber;
    Object vehicle;
    long startTime;
    long endTime;
    Time time;

    public ParkingSlot() {

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

    public long calculateCharge() {
        long totalTime = this.endTime - this.startTime;
        return totalTime;
    }
}
