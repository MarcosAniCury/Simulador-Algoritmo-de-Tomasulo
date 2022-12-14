package Utils;

import Model.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Arquive {
    private List<Instruction> instructions;

    public Arquive() throws Exception {
        this.instructions = new ArrayList<Instruction>();
    }

    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    public Instruction get() throws Exception {
        return instructions.remove(0);
    }

    public Instruction[] getAllInstructions() {
        return instructions.toArray(new Instruction[0]);
    }

    public int size() {
        return instructions.size();
    }
}
