import java.util.Scanner;

import Controller.TomasuloController;
import Controller.ReorderBufferController;

public final class App {

    public static void main(String[] args) {
        TomasuloController.setupTomasulo("comands.txt");        
    
        while (ReorderBufferController.reorderBuffer.size() > 0) {
            Scanner scanner = new Scanner(System.in);

            String next = scanner.nextLine();
            TomasuloController.nextStep();
        }
    }   
}
