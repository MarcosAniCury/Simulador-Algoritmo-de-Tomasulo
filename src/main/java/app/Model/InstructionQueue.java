package app.Model;

import java.util.ArrayList;
import java.util.List;

import app.Constants.Definitions;

public class InstructionQueue {
    private List<Instruction> instructions;

    public InstructionQueue() {
        this.instructions = new ArrayList<Instruction>();
    }

    public int add(Instruction instruction) throws Exception {
        if (this.instructions.size() > Definitions.TAM_INSTRUCTION_QUEUE) {
            throw new Exception("Cannot insert new values in instruction queue. Max size: "+Definitions.TAM_INSTRUCTION_QUEUE);
        }
        this.instructions.add(instruction);  
        return this.instructions.size() - 1;
    }

    public Instruction remove() {
        return this.instructions.remove(0);
    }
}
