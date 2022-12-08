package app.Model;

import java.util.ArrayList;
import java.util.List;

import app.Constants.Definitions;

public class InstructionQueue {
    List<Instruction> listOfInstructions;

    public InstructionQueue() {
        this.listOfInstructions = new ArrayList<Instruction>();
    }

    public void addInstructionQueue(Instruction newInstruction) throws Exception {
        if (this.listOfInstructions.size() > Definitions.TAM_INSTRUCTION_QUEUE) {
            throw new Exception("Cannot insert new values in instruction queue. Max size: "+Definitions.TAM_INSTRUCTION_QUEUE);
        }
        this.listOfInstructions.add(newInstruction);    
    }

    public Instruction removeValueFromInstructionQueue() {
        return this.listOfInstructions.remove(0);
    }
}
