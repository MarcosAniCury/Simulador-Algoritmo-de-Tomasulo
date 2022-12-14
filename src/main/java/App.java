import java.util.Arrays;
import java.util.Scanner;

import Constants.Instructions;
import Controller.TomasuloController;
import Model.ReservationStation;
import Model.ReservationStationInstruction;
import Controller.ReorderBufferController;
import Controller.ReservationStationController;

public final class App {

    public static void main(String[] args) {
        TomasuloController.setupTomasulo("comands.txt");        
    
        while (ReorderBufferController.reorderBuffer.size() > 0) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n");
            System.out.println("------------------------------------------------------------------------------------------");        
            System.out.println("\t\t\t\t Reorder Buffer");
            System.out.println("------------------------------------------------------------------------------------------");        
            System.out.println("Entry \t Busy \t Instruction \t State \t Destination \t Value");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t ReservationStations");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("Name \t Busy \t Op \t Vj \t\t Vk \t\t Qj \t Qk \t Dest \t A");
            System.out.println("------------------------------------------------------------------------------------------");

            for (ReservationStation reservationStation : ReservationStationController.allReservationsArea.values()) {
                ReservationStationInstruction[] reservationStationInstructions = reservationStation.getReservationStationInstructions();
                for (int i = 0; i < reservationStationInstructions.length; i++) {
                    String name = reservationStation.getType().toString()+i;
                    String instructionType = reservationStationInstructions[i].getInstructionType().toString();
                    String addressFirstRegister = "";
                    String valueFirstRegister = "";
                    if (reservationStationInstructions[i].getRegisterOne().getValue() != -1) {
                        String firstRegisterInstructionName = reservationStationInstructions[i].getRegisterOne().getBufferInstruction().getInstruction().getInstruction();
                        if (Arrays.stream(Instructions.LOAD_STORE).anyMatch(item -> item.equals(firstRegisterInstructionName))) {
                            addressFirstRegister = "Mem[Regs["+reservationStationInstructions[i].getRegisterOne().getName()+"]]";
                        } else {
                            addressFirstRegister = "Regs["+reservationStationInstructions[i].getRegisterOne().getName()+']';
                        }
                    } else {
                        valueFirstRegister = "#"+reservationStationInstructions[i].getRegisterOne().getBufferInstruction().getRegisterDestination().getName();
                    }
                    String addressSecondRegister = "";
                    String valueSecondRegister = "";
                    if (reservationStationInstructions[i].getRegisterTwo().getValue() != -1) {
                        String secondRegisterInstructionName = reservationStationInstructions[i].getRegisterTwo().getBufferInstruction().getInstruction().getInstruction();
                        if (Arrays.stream(Instructions.LOAD_STORE).anyMatch(item -> item.equals(secondRegisterInstructionName))) {
                            addressSecondRegister = "Mem[Regs["+reservationStationInstructions[i].getRegisterTwo().getName()+"]]";
                        } else {
                            addressSecondRegister = "Regs["+reservationStationInstructions[i].getRegisterTwo().getName()+']';
                        }
                    } else {
                        valueSecondRegister = "#"+reservationStationInstructions[i].getRegisterTwo().getBufferInstruction().getRegisterDestination().getName();
                    }
                    System.out.println(name+"\tYes\t"+instructionType+"\t"+addressFirstRegister+"\t"+addressSecondRegister+"\t"+valueFirstRegister+"\t"+valueSecondRegister+"\t");
                }
            }

            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t FP register status");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("Field \t F0 \t F1 \t F2 \t F3 \t F4 \t F5 \t F6 \t F7 \t F8 \t F9 \t F10");
            System.out.println("Reorder #");
            System.out.println("Busy");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------------------------------");        
            System.out.println("\n");

            String next = scanner.nextLine();
            TomasuloController.nextStep();
        }
    }
}
