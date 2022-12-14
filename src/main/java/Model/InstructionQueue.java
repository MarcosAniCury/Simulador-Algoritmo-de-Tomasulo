package Model;

import java.util.ArrayList;
import java.util.List;

import Constants.Definitions;

public class InstructionQueue {
    private List<QueueInstruction> instructions;

    public InstructionQueue() {
        this.instructions = new ArrayList<QueueInstruction>();
    }

    public int add(QueueInstruction instruction) throws Exception {
        if (this.instructions.size() > Definitions.TAM_INSTRUCTION_QUEUE) {
            throw new Exception("Cannot insert new values in instruction queue. Max size: "+Definitions.TAM_INSTRUCTION_QUEUE);
        }
        this.instructions.add(instruction);  
        return this.instructions.size() - 1;
    }

    public QueueInstruction remove() {
        return this.instructions.remove(0);
    }

    public QueueInstruction remove(int index) {
        return this.instructions.remove(index);
    }

    public QueueInstruction[] getAllQueueInstructions() {
        return this.instructions.toArray(new QueueInstruction[0]);
    }

    public int getInstructionQueueSize() {
        return this.instructions.size();
    }

    public int findIndexBasedInIntruction(Instruction instruction) {
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instructionFromQueue = instructions.get(i).getInstruction();
            if (
                instruction.getInstruction().equals(instructionFromQueue.getInstruction()) && 
                instruction.getOption1().equals(instructionFromQueue.getOption1()) && 
                instruction.getOption2().equals(instructionFromQueue.getOption2()) &&
                instruction.getOption3().equals(instructionFromQueue.getOption3())
            ) {
                return i;
            }
        }
        return -1;
    }
}
