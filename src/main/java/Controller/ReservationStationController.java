package Controller;

import java.util.Arrays;
import java.util.HashMap;

import Constants.Instructions;
import Constants.ReservationStation.TypeEnum;
import Model.ReservationStation;

public class ReservationStationController {
    public static HashMap<TypeEnum, ReservationStation> allReservationsArea;

    public static void startReservationStation() {
        ReservationStationController.allReservationsArea = new HashMap<TypeEnum, ReservationStation>();
        for (TypeEnum typeReservationStation : TypeEnum.values()) {
            ReservationStationController.allReservationsArea.put(typeReservationStation, new ReservationStation(typeReservationStation));
        }
    }
    
    public static TypeEnum findTypeEnumBaseInstruction(String instruction) throws Exception {
        if (Arrays.stream(Instructions.LOGIC).anyMatch(item -> item.equals(instruction))) {
            return TypeEnum.TYPE_LOGIC;
        } else if (Arrays.stream(Instructions.MULT).anyMatch(item -> item.equals(instruction))) {
            return TypeEnum.TYPE_MULT;
        } else if (Arrays.stream(Instructions.LOAD_STORE).anyMatch(item -> item.equals(instruction))) {
            return TypeEnum.TYPE_MEMORY;
        } else if (Arrays.stream(Instructions.DETOUR).anyMatch(item -> item.equals(instruction))) {
            return TypeEnum.TYPE_BEQ;
        } else if (Arrays.stream(Instructions.ARITIMETIC).anyMatch(item -> item.equals(instruction))) {
            return TypeEnum.TYPE_ARITMETIC;
        } else {
            throw new Exception("Instruction type nor found");
        }
    }
}
