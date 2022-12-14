
import Controller.TomasuloController;







public final class App {

    public static void main(String[] args) {

        TomasuloController.setupTomasulo("comands.txt");        

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
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t FP register status");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Field \t F0 \t F1 \t F2 \t F3 \t F4 \t F5 \t F6 \t F7 \t F8 \t F9 \t F10");
        System.out.println("Reorder #");
        System.out.println("Busy");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------");        
        System.out.println("\n");


    }
}
