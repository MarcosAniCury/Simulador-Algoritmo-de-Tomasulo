package Controller;

import Model.Instruction;
import Model.InstructionQueue;
import Model.ReorderBuffer;
import Utils.Arquive;

public class TomasuloController {
    private static Arquive arquive;
    private static InstructionQueue instructionQueue;
    private static ReorderBuffer reorderBuffer;

    public void runTomasulo(String path) {
        try {
            RegisterController.defineRegisters();
            TomasuloController.arquive = new Arquive("comands.txt");
            TomasuloController.instructionQueue = new InstructionQueue();
            TomasuloController.reorderBuffer = new ReorderBuffer();

            for (int i = 0; i < 1; i++) {
                Instruction instructionArquive = arquive.get();
                int indexInstructionQueue = instructionQueue.add(instructionArquive);
                reorderBuffer.add(instructionArquive, indexInstructionQueue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
