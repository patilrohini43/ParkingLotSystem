package com.bridgelabz.parkinglot.model;

import com.bridgelabz.parkinglot.enums.CarName;

public class Vehicle {

    CarName carName;
    String color;
    int numberPlate;

    public Vehicle(){

    }

    public Vehicle(CarName carName,String color, int numberPlate) {
        this.carName=carName;
        this.color = color;
        this.numberPlate = numberPlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(int numberPlate) {
        this.numberPlate = numberPlate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "color='" + color + '\'' +
                ", carName='" + carName + '\'' +
                ", numberPlate=" + numberPlate +
                '}';
    }
}
