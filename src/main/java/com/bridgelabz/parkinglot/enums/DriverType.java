package com.bridgelabz.parkinglot.enums;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum DriverType {

    Handicap{
        public ArrayList<Integer> getSlotNumber(List<Integer> emptyList) {
            List<Integer> value=emptyList.stream().sorted().collect(Collectors.toList());
            return (ArrayList<Integer>) value;
        }
    },Normal{
        public  ArrayList<Integer> getSlotNumber(List<Integer> emptyList) {
            List<Integer> value=emptyList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
            return (ArrayList<Integer>) value;
        }
    };

        public ArrayList<Integer> getSlotNumber(List<Integer> emptyList) {
           return null;
        }
}
