package Controller;

import Constants.Instructions.InstructionsEnum;
import Model.ReservationStationInstruction;

public class InstructionController {
    public static InstructionsEnum findInstructionEnumBasedInInstructionName(String name) throws Exception {
        InstructionsEnum[] instructionsEnum = InstructionsEnum.values();
        for (InstructionsEnum instructionEnum : instructionsEnum) {
            if (instructionEnum.toString().equals(name)) {
                return instructionEnum;
            }
        }
        throw new Exception("InstructionEnum not founded");
    }

    public static int LogicInstructionExecute(ReservationStationInstruction reservationStationInstruction) {
        boolean registerOne = reservationStationInstruction.getRegisterOne().getValue() > 0;
        boolean registerTwo = reservationStationInstruction.getRegisterTwo().getValue() > 0;
        boolean result = false;
        if(reservationStationInstruction.getInstructionType() == InstructionsEnum.XOR) {
            result = registerOne ^ registerTwo;
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.OR) {
            result = registerOne || registerTwo;
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.AND) {
            result = registerOne && registerTwo;
        }
        return result ? 1 : 0;
    }

    public static int AritimeticInstructionExecute(ReservationStationInstruction reservationStationInstruction) {
        int registerOne = reservationStationInstruction.getRegisterOne().getValue();
        int registerTwo = reservationStationInstruction.getRegisterTwo().getValue();
        int result = -1;
        if(reservationStationInstruction.getInstructionType() == InstructionsEnum.ADD) {
            result = registerOne + registerTwo;
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.SUB) {
            result = registerOne - registerTwo;
        }
        return result;
    }

    public static int MultInstructionExecute(ReservationStationInstruction reservationStationInstruction) {
        int registerOne = reservationStationInstruction.getRegisterOne().getValue();
        int registerTwo = reservationStationInstruction.getRegisterTwo().getValue();
        int result = -1;
        if(reservationStationInstruction.getInstructionType() == InstructionsEnum.MUL) {
            result = registerOne * registerTwo;
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.DIV) {
            result = registerOne / registerTwo;
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.REM) {
        }
        return result;
    }
}
