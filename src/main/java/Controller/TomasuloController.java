package Controller;

import Constants.Definitions;
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
            for (Instruction instruction : InstructionQueueController.instructionQueue.getAllInstructions()) {
                for (ReservationStation reservationStation : ReservationStationController.allReservationsArea.values()) {
                    for (ReservationStationInstruction reservationStationInstruction : reservationStation.reservationStationInstructions()) {
                        //Dependencie check logic
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
