package Controller;

import Model.Instruction;
import Model.InstructionQueue;
import Model.QueueInstruction;

public class InstructionQueueController {
    public static InstructionQueue instructionQueue;

    public static void startInstructionQueue() {
        InstructionQueueController.instructionQueue = new InstructionQueue();
    }

    public static Instruction[] getAllInstructions() {
        int instructionQueueSize = instructionQueue.getInstructionQueueSize();
        Instruction[] instructions = new Instruction[instructionQueueSize];
        QueueInstruction[] instructionsQueue = instructionQueue.getAllQueueInstructions();
        for (int i = 0; i < instructionQueueSize; i++) {
            instructions[i] = instructionsQueue[i].getInstruction();
        }
        return instructions;
    }
}
