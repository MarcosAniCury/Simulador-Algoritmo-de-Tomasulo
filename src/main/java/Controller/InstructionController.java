package Controller;

import Constants.Instructions.InstructionsEnum;
import Model.Instruction;
import Model.ReservationStation;
import Model.ReservationStationInstruction;

public class InstructionController {
    public static InstructionsEnum findInstructionEnumBasedInInstructionName(String name) throws Exception {
        InstructionsEnum[] instructionsEnum = InstructionsEnum.values();
        for (InstructionsEnum instructionEnum : instructionsEnum) {
            if (instructionEnum.toString().equals(name.toUpperCase())) {
                return instructionEnum;
            }
        }
        throw new Exception("InstructionEnum not founded");
    }

    public static int LogicInstructionExecute(ReservationStationInstruction reservationStationInstruction) {
        boolean registerOne = reservationStationInstruction.getRegisterOne().getValue() > 0;
        boolean registerTwo = reservationStationInstruction.getRegisterTwo().getValue() > 0;
        boolean result = false;
        if (reservationStationInstruction.getInstructionType() == InstructionsEnum.XOR) {
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
        if (reservationStationInstruction.getInstructionType() == InstructionsEnum.ADD) {
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
        if (reservationStationInstruction.getInstructionType() == InstructionsEnum.MUL) {
            result = registerOne * registerTwo;
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.DIV) {
            result = registerOne / registerTwo;
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.REM) {
            result = registerOne % registerTwo;
        }
        return result;
    }

    public static int BeqInstructionExecute(ReservationStationInstruction reservationStationInstruction) throws Exception {
        int registerOne = reservationStationInstruction.getRegisterOne().getValue();
        int registerTwo = reservationStationInstruction.getRegisterTwo().getValue();
        if (reservationStationInstruction.getInstructionType() == InstructionsEnum.BEQ) {
            if (registerOne == registerTwo) {
                discartInstructions(reservationStationInstruction);
            }
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.BNE) {
            if (registerOne != registerTwo) {
                discartInstructions(reservationStationInstruction);
            }
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.BGE) {
            if (registerOne >= registerTwo) {
                discartInstructions(reservationStationInstruction);
            }
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.BLT) {
            if (registerOne < registerTwo) {
                discartInstructions(reservationStationInstruction);
            }
        }
        return reservationStationInstruction.getRegisterTarget().getValue();
    }

    public static int LoadStoreInstructionExecute(ReservationStationInstruction reservationStationInstruction) {
        int registerOne = reservationStationInstruction.getRegisterOne().getValue();
        int result = -1;
        if (reservationStationInstruction.getInstructionType() == InstructionsEnum.LW) {
            result = registerOne;
        } else if (reservationStationInstruction.getInstructionType() == InstructionsEnum.SW) {
            result = registerOne;
        }
        return result;
    }

    private static void discartInstructions(ReservationStationInstruction reservationStationInstruction) throws Exception {
        String jumpTag = reservationStationInstruction.getInstruction().getJumpTag();
        int reorderBufferIndex = ReorderBufferController.reorderBuffer.findIndexBasedInInstruction(reservationStationInstruction.getInstruction());
        for (int i = reorderBufferIndex + 1; i < ReorderBufferController.reorderBuffer.size();i++) {
            Instruction reorderInstruction = ReorderBufferController.reorderBuffer.getIndex(i).getInstruction();
            if (reorderInstruction.getJumpTag().equals(jumpTag)) {
                break;
            }
            int instructionQueueIndexInstruction = InstructionQueueController.instructionQueue.findIndexBasedInInstruction(reorderInstruction);
            if (instructionQueueIndexInstruction != -1) {
                InstructionQueueController.instructionQueue.remove(instructionQueueIndexInstruction);
            }
            ReorderBufferController.reorderBuffer.remove(i);
            for (ReservationStation reservationStation : ReservationStationController.allReservationsArea.values()) {
                int reservationStationIndexInstruction = reservationStation.findIndexBasedInInstruction(reorderInstruction);
                if (reservationStationIndexInstruction != -1) {
                    reservationStation.remove(reservationStationIndexInstruction);
                }
            }
        }
    }
}
