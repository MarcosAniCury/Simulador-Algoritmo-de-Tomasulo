package Controller;

import Model.InstructionQueue;

public class InstructionQueueController {
    public static InstructionQueue instructionQueue;

    public static void startInstructionQueue() {
        InstructionQueueController.instructionQueue = new InstructionQueue();
    }
}
