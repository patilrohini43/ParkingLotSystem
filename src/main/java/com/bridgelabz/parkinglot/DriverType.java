package com.bridgelabz.parkinglot;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum DriverType{

    Handicap{
        public int getSlotNumber(List<Integer> emptyList) {
            int value=emptyList.stream().sorted().collect(Collectors.toList()).get(0);
            return value;
        }
    },Normal{
        public int getSlotNumber(List<Integer> emptyList) {
            int value=emptyList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()).get(0);
            return value;
        }
    };

        public int getSlotNumber(List<Integer> emptyList) {
           return 0;
        }
}
