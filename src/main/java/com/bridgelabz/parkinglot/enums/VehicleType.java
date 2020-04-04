package com.bridgelabz.parkinglot.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum VehicleType {
    SmallVehicle{
        public ArrayList<Integer> checkSlot(ArrayList<Integer> emptyList, DriverType type) {
            ArrayList<Integer> emptySlot= (ArrayList<Integer>) type.getSlotNumber(emptyList);
            return emptySlot;
        }
    },
    LargeVehicle{
        public ArrayList<Integer> checkSlot(ArrayList<Integer> emptyList, DriverType type) {
            ArrayList<Integer> emptySlot= (ArrayList<Integer>) type.getSlotNumber(emptyList);
            Collections.sort(emptySlot);
            ArrayList<Integer> slot = new ArrayList<>();
           for(int i=0;i<=emptySlot.size()-1;i++){
               if(emptySlot.get(i)%2==0){
                   slot.add(emptySlot.get(i));
               }
           }
            return slot;
        }
    };

    public ArrayList<Integer> checkSlot(ArrayList<Integer> emptyList, DriverType type) {
        return null;
    }
}
