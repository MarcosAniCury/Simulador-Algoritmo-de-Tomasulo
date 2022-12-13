package Controller;

import Model.Instruction;

public class TomasuloController {
    public static void startTomasulo(String path) {
        try {
            RegisterController.defineRegisters();
            ArquiveController.readArquive(path);
            InstructionQueueController.startInstructionQueue();
            ReorderBufferController.startReorderBuffer();

            for (int i = 0; i < 1; i++) {
                Instruction instructionArquive = ArquiveController.getFirstIntruction();
                int indexInstructionQueue = InstructionQueueController.instructionQueue.add(instructionArquive);
                ReorderBufferController.reorderBuffer.add(instructionArquive, indexInstructionQueue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
