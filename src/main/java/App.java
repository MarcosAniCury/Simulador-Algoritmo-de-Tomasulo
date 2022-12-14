import java.util.Arrays;
import java.util.Scanner;


import Controller.RegisterController;
import Controller.ReorderBufferController;
import Controller.TomasuloController;
import Model.BufferInstruction;

import Model.Register;
import Constants.Definitions;
import Constants.Instructions;
import Model.ReservationStation;
import Model.ReservationStationInstruction;
import Controller.ReservationStationController;

public final class App {

    public static void main(String[] args) {
        TomasuloController.setupTomasulo("comands.txt");
        Scanner scanner;

        while (ReorderBufferController.reorderBuffer.size() > 0) {
            scanner = new Scanner(System.in);

            System.out.println("\n");
            System.out.println(
                    "------------------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t Reorder Buffer");
            System.out.println(
                    "------------------------------------------------------------------------------------------");
            System.out.println("Entry \t Busy \t Instruction \t   State \t Destination \t Value");
            System.out.println(
                    "------------------------------------------------------------------------------------------");

            BufferInstruction[] inst = ReorderBufferController.reorderBuffer.getBufferInstructions();

            for (int i = 0; i < inst.length; i++) {

                System.out.print(i + 1);
                System.out.print("\t yes");
                System.out.print("\t" + inst[i].getInstruction().getInstructionFormated());
                System.out.print("\t    " + inst[i].getState());
                System.out.print("\t  " + inst[i].getRegisterDestination().getName());
                System.out.print("\t" + inst[i].getValue());
                System.out.print("\n");
            }

            for (int i = inst.length; i < Definitions.TAM_BUFFER_INSTRUCTION; i++) {
                System.out.print(i + 1);
                System.out.print("\t no");
                System.out.print("\t");
                System.out.print("\t");
                System.out.print("\t");
                System.out.print("\t");
                System.out.print("\n");
            }

            System.out.println(
                    "------------------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t ReservationStations");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t Busy \t Op \t Vj \t\t Vk \t Qj \t Qk \t Dest");
            System.out.println("------------------------------------------------------------------------------------------");

            for (ReservationStation reservationStation : ReservationStationController.allReservationsArea.values()) {
                ReservationStationInstruction[] reservationStationInstructions = reservationStation
                        .getReservationStationInstructions();
                for (int i = 0; i < reservationStationInstructions.length; i++) {
                    String name = reservationStation.getType().toString() + i;
                    String instructionType = reservationStationInstructions[i].getInstructionType().toString();
                    String addressFirstRegister = "";
                    String valueFirstRegister = "";
                    if (reservationStationInstructions[i].getRegisterOne().getBufferInstruction() != null) {
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
                    }
                    String addressSecondRegister = "";
                    String valueSecondRegister = "";
                    if (reservationStationInstructions[i].getRegisterTwo().getBufferInstruction() != null) {
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
                    }
                    String registerDestination = "#" + reservationStationInstructions[i].getRegisterTarget().getName();
                    System.out.println(name + "\tYes\t" + instructionType + "\t" + addressFirstRegister + "\t"
                            + addressSecondRegister + "\t" + valueFirstRegister + "\t" + valueSecondRegister + "\t"
                            + registerDestination + "\t");
                }
            }

            System.out.println(
                    "------------------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t FP register status");
            System.out.println(
                    "------------------------------------------------------------------------------------------");

            System.out.println("Field \t  F0 \t F1 \t F2 \t F3 \t F4 \t F5 \t F6 \t F7 \t F8 \t F9");
            System.out.print("Reorder#");
            
            Register [] r = RegisterController.registers;        

            for (int i = 0; i < 10; i++) {
                if(r[i].bufferInstruction == null) System.out.print("");
                else
                    try {
                        System.out.print(" #" + ReorderBufferController.reorderBuffer.findIndexBasedInInstruction(r[i].bufferInstruction.getInstruction()));
                    } catch (Exception e) {                        
                        e.printStackTrace();
                    }                
                System.out.print("\t");
            }

            System.out.print("\nBusy\t  ");

            for (int i = 0; i < 10; i++) {
                if(r[i].bufferInstruction == null) System.out.print("No");
                else System.out.print("yes");                
                System.out.print("\t");
            }            
            System.out.println("\n------------------------------------------------------------------------------------------");   
            System.out.println("\n");

            String next = scanner.nextLine();
            TomasuloController.nextStep();
        }
    }
}
