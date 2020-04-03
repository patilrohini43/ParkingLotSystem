package com.bridgelabz.parkinglot;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum DriverType{

    Handicap{
        public List getSlotNumber(List<Integer> emptyList) {
            List value=emptyList.stream().sorted().collect(Collectors.toList());
            return value;
        }
    },Normal{
        public List getSlotNumber(List<Integer> emptyList) {
            List value=emptyList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
            return value;
        }
    };

        public List getSlotNumber(List<Integer> emptyList) {
           return Collections.singletonList(0);
        }
}
