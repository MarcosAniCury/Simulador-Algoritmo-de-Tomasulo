package Controller;

import java.util.Arrays;

import Constants.Definitions;
import Constants.Instructions;
import Constants.BufferInstruction.StateEnum;
import Constants.Instructions.InstructionsEnum;
import Constants.ReservationStation.TypeEnum;
import Model.BufferInstruction;
import Model.Instruction;
import Model.QueueInstruction;
import Model.Register;
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
            for (int i = 0; i < Definitions.TAM_INSTRUCTION_QUEUE && i < ArquiveController.getArquiveSize(); i++) {
                Instruction instructionArquive = ArquiveController.getFirstIntruction();
                int indexQueueInstruction = InstructionQueueController.instructionQueue.getInstructionQueueSize();
                InstructionQueueController.instructionQueue.add(new QueueInstruction(instructionArquive, indexQueueInstruction));
                ReorderBufferController.reorderBuffer.add(instructionArquive, indexQueueInstruction);
            }
            //Set register reorder buffer destination
            for (int i = 0; i < Definitions.TAM_INSTRUCTION_QUEUE && i < ReorderBufferController.reorderBuffer.size(); i++) {
                ReorderBufferController.reorderBuffer.getIndex(i).getRegisterDestination().setBufferInstruction(ReorderBufferController.reorderBuffer.getIndex(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void nextStep() {
        try {
            addInstructionsAndReservationStation();
            dispatchFromReservationStationInstructions();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private static void addInstructionsAndReservationStation() throws Exception {
        Instruction[] instructions = InstructionQueueController.getAllInstructions();
        for (int i = 0; i < instructions.length;i++) {
            String registerReadOneName = instructions[i].getOption2();
            String registerReadTwoName = instructions[i].getOption3();

            if (!verifyIfInstructionHasDependencie(registerReadOneName, registerReadTwoName)) {
                addInReservationStation(instructions[i], registerReadOneName, registerReadTwoName, i);
            }
        }
    }

    private static boolean verifyIfInstructionHasDependencie(String registerReadOneName, String registerReadTwoName) {
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

    private static void addInReservationStation(Instruction instruction, String registerReadOneName, String registerReadTwoName, int reorderBufferIndex) throws Exception {
        String registerWriteName = instruction.getOption1();
        TypeEnum instructionReservationStationType = ReservationStationController.findTypeEnumBaseInstruction(instruction.getInstruction());
        InstructionsEnum instructionType = InstructionController.findInstructionEnumBasedInInstructionName(instruction.getInstruction());
        ReservationStation selectReservationStation = ReservationStationController.allReservationsArea.get(instructionReservationStationType);
        selectReservationStation.add(
            instructionType, 
            RegisterController.findRegisterBasedInName(registerWriteName), 
            RegisterController.findRegisterBasedInName(registerReadOneName), 
            RegisterController.findRegisterBasedInName(registerReadTwoName)
        );
        ReorderBufferController.reorderBuffer.getIndex(reorderBufferIndex).setState(StateEnum.STATE_EXECUTE);
    }

    private static void dispatchFromReservationStationInstructions() {
        ReservationStation[] reservationStations = (ReservationStation[]) ReservationStationController.allReservationsArea.values().toArray();
        for (ReservationStation reservationStation : reservationStations) {
            ReservationStationInstruction[] reservationStationInstructions = reservationStation.getReservationStationInstructions();
            for (int i = 0;i < reservationStationInstructions.length; i++) {
                String reservationStationInstructionRegisterOneName = reservationStationInstructions[i].getRegisterOne().getName();
                String reservationStationInstructionRegisterTwoName = reservationStationInstructions[i].getRegisterTwo().getName();
                BufferInstruction[] bufferInstructions = ReorderBufferController.reorderBuffer.getBufferInstructions();
                int reservationStationInstructionPositionInReorderBuffer = reservationStationInstructions[i].
                                                                            getRegisterTarget().
                                                                            getBufferInstruction().
                                                                            getIndexInstructionQueue();
                for (int j = 0; j < reservationStationInstructionPositionInReorderBuffer; j++) {
                    String bufferInstructionRegisterTargetName = bufferInstructions[j].getInstruction().getOption1();
                    if (bufferInstructionRegisterTargetName.equals(reservationStationInstructionRegisterOneName) ) {
                        makeFowarding(bufferInstructions[j], reservationStationInstructions[i], reservationStationInstructions[i].getRegisterOne());
                    }
                    if (bufferInstructionRegisterTargetName.equals(reservationStationInstructionRegisterTwoName)) {
                        makeFowarding(bufferInstructions[j], reservationStationInstructions[i], reservationStationInstructions[i].getRegisterTwo());
                    }

                    if (reservationStationInstructions[i].getRegisterOne() != null && reservationStationInstructions[i].getRegisterTwo() != null) {
                        reservationStationInstructions[i].getRegisterTarget().setValue(executeInstruction(reservationStationInstructions[i]));
                        ReservationStationController.allReservationsArea.get(reservationStation.getType()).remove(i);
                        reservationStationInstructions[i].getRegisterTarget().getBufferInstruction().setState(StateEnum.STATE_WRITE_RESULT);
                    }
                }
            }
        }
    }

    private static boolean makeFowarding(BufferInstruction bufferInstruction, ReservationStationInstruction reservationStationInstruction, Register registerToFill) {
        int bufferInstructionRegistervalue = bufferInstruction.getRegisterDestination().getValue();
        if (bufferInstructionRegistervalue != -1) {
            registerToFill.setValue(bufferInstructionRegistervalue);
            return true;
        }
        return false;
    }

    private static int executeInstruction(ReservationStationInstruction reservationStationInstruction) {
        InstructionsEnum instructionType = reservationStationInstruction.getInstructionType();
        if (Arrays.stream(Instructions.LOGIC).anyMatch(item -> item.equals(instructionType.toString()))) {
            return InstructionController.LogicInstructionExecute(reservationStationInstruction);
        } else if (Arrays.stream(Instructions.ARITIMETIC).anyMatch(item -> item.equals(instructionType.toString()))) {
            return InstructionController.AritimeticInstructionExecute(reservationStationInstruction);
        } else if (Arrays.stream(Instructions.MULT).anyMatch(item -> item.equals(instructionType.toString()))) {
            return InstructionController.MultInstructionExecute(reservationStationInstruction);
        }
        return -1;
    }
}
