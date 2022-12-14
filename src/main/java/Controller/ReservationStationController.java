package Controller;

import java.util.Arrays;
import java.util.HashMap;

import Constants.Definitions;
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
            return TypeEnum.logic;
        } else if (Arrays.stream(Instructions.MULT).anyMatch(item -> item.equals(instruction))) {
            return TypeEnum.mult;
        } else if (Arrays.stream(Instructions.LOAD_STORE).anyMatch(item -> item.equals(instruction))) {
            return TypeEnum.memory;
        } else if (Arrays.stream(Instructions.DETOUR).anyMatch(item -> item.equals(instruction))) {
            return TypeEnum.beq;
        } else if (Arrays.stream(Instructions.ARITIMETIC).anyMatch(item -> item.equals(instruction))) {
            return TypeEnum.aritmetic;
        } else {
            throw new Exception("Instruction type nor found");
        }
    }

    public static int getCiclesBasedType(TypeEnum type) {
        if (type == TypeEnum.aritmetic) {
            return Definitions.INSTRUCTION_CICLE_ARITIMETIC;
        } else if (type == TypeEnum.logic) {
            return Definitions.INSTRUCTION_CICLE_LOGIC;
        } else if (type == TypeEnum.mult) {
            return Definitions.INSTRUCTION_CICLE_MULT;
        } else if (type == TypeEnum.beq) {
            return Definitions.INSTRUCTION_CICLE_BEQ;
        } else if (type == TypeEnum.memory) {
            return Definitions.INSTRUCTION_CICLE_LOAD_STORE;
        }
        return -1;
    }
}
