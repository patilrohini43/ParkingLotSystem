package com.bridgelabz.parkinglot;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                   System.out.println(emptySlot.get(i));
                   slot.add(emptySlot.get(i));
               }
           }
            return slot;
        }
    };

    public List checkSlot(ArrayList<Integer> emptyList, DriverType type) {
        return null;
    }
}
