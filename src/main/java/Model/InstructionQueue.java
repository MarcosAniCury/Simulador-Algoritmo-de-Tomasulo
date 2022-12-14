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

    public QueueInstruction[] getAllQueueInstructions() {
        return this.instructions.toArray(new QueueInstruction[0]);
    }

    public int getInstructionQueueSize() {
        return this.instructions.size();
    }
}
