package com.bridgelabz.parkinglot;
import com.bridgelabz.parkinglot.model.Car;


public class ParkingSystem {

    private Object vehicle;

    public boolean addParkSystem(Car car) {
        this.vehicle=car;
       return true;
    }

    public boolean unParkSystem(Car car) {
        if(this.vehicle.equals(car))
        {
            this.vehicle=null;
            return true;
        }
        return false;
    }
}
