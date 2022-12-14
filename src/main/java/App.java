import Controller.InstructionQueueController;
import Controller.TomasuloController;
import Model.Instruction;
import Model.Tomasulo;


import javax.swing.SwingUtilities;


public final class App {

    public static void main(String[] args) {

        TomasuloController.setupTomasulo("comands.txt");        

        for (Instruction i : InstructionQueueController.getAllInstructions()) {
            
            System.out.println(i);
            
        }


        SwingUtilities.invokeLater(() -> {
            Tomasulo t = new Tomasulo();
            t.setVisible(true);
        });

    }
}
