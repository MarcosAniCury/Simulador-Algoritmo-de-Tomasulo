package Controller;

import Constants.Definitions;
import Constants.Instructions.InstructionsEnum;
import Constants.ReservationStation.TypeEnum;
import Model.Instruction;
import Model.ReservationStation;
import Model.ReservationStationInstruction;

public class TomasuloController {
    public static void setupTomasulo(String path) {
        try {
            //Create global variables for arquiteture
            RegisterController.defineRegisters();
            ArquiveController.readArquive(path);
            InstructionQueueController.startInstructionQueue();
            ReorderBufferController.startReorderBuffer();
            ReservationStationController.startReservationStation();

            //Full the instructionQueue and ReorderBuffer
            for (int i = 0; i < Definitions.TAM_INSTRUCTION_QUEUE; i++) {
                Instruction instructionArquive = ArquiveController.getFirstIntruction();
                int indexInstructionQueue = InstructionQueueController.instructionQueue.add(instructionArquive);
                ReorderBufferController.reorderBuffer.add(instructionArquive, indexInstructionQueue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void nextStep() {
        try {
            addInstructionsAndReservationStation();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private static void addInstructionsAndReservationStation() throws Exception {
        for (Instruction instruction : InstructionQueueController.instructionQueue.getAllInstructions()) {
            String registerReadOneName = instruction.getOption2();
            String registerReadTwoName = instruction.getOption3();

            if (!verifyIdInstructionHasDependencie(instruction, registerReadOneName, registerReadTwoName)) {
                addInReservationStation(instruction, registerReadOneName, registerReadTwoName);
            }
        }
    }

    private static boolean verifyIdInstructionHasDependencie(Instruction instruction, String registerReadOneName, String registerReadTwoName) {
        ReservationStation[] reservationStations = (ReservationStation[]) ReservationStationController.allReservationsArea.values().toArray();
        for (ReservationStation reservationStation : reservationStations) {
            ReservationStationInstruction[] reservationStationInstructions = reservationStation.getReservationStationInstructions();
            for (ReservationStationInstruction reservationStationInstruction : reservationStationInstructions) {
                String registerLookingWriteName = reservationStationInstruction.getRegisterTarget().getName();
                if (registerLookingWriteName.equals(registerReadOneName) || (registerReadTwoName != null && registerLookingWriteName.equals(registerReadTwoName))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void addInReservationStation(Instruction instruction, String registerReadOneName, String registerReadTwoName) throws Exception {
        String registerWriteName = instruction.getOption1();
        TypeEnum instructionReservationStationType = ReservationStationController.findTypeEnumBaseInstruction(instruction.getInstruction());
        InstructionsEnum instructionType = InstructionController.findInstructionEnumBasedInInstructionName(instruction.getInstruction());
        ReservationStation selectReservationStation = ReservationStationController.allReservationsArea.get(instructionReservationStationType);
        selectReservationStation.add(
            instruction.getInstruction(), 
            instructionType, 
            RegisterController.findRegisterBasedInName(registerWriteName), 
            RegisterController.findRegisterBasedInName(registerReadOneName), 
            RegisterController.findRegisterBasedInName(registerReadTwoName)
        );
    }
}
