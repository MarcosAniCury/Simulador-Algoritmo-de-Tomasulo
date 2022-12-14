package Model;

import java.util.List;

import Constants.Definitions;

import java.util.ArrayList;

public class ReorderBuffer {
    private List<BufferInstruction> reorderBuffer;

    public ReorderBuffer() {
        this.reorderBuffer = new ArrayList<BufferInstruction>();
    }

    public void add(Instruction instruction, int indexInstructionQueue) throws Exception {
        if (this.reorderBuffer.size() > Definitions.TAM_BUFFER_INSTRUCTION) {
            throw new Exception("Cannot insert new values in reorder buffer. Max size: "+Definitions.TAM_BUFFER_INSTRUCTION);
        }
        this.reorderBuffer.add(new BufferInstruction(instruction, indexInstructionQueue));
    }

    public Instruction remove() {
        return reorderBuffer.remove(0).getInstruction();
    }

    public BufferInstruction remove(int index) {
        return reorderBuffer.remove(index);
    }

    public BufferInstruction[] getBufferInstructions() {
        return this.reorderBuffer.toArray(new BufferInstruction[0]);
    }

    public BufferInstruction getIndex(int index) {
        return reorderBuffer.get(index);
    }

    public int size() {
        return this.reorderBuffer.size();
    }

    public int findIndexBasedInIntruction(Instruction instruction) throws Exception {
        for (int i = 0; i < reorderBuffer.size(); i++) {
            Instruction instructionFromBuffer = reorderBuffer.get(i).getInstruction();
            if (
                instruction.getInstruction().equals(instructionFromBuffer.getInstruction()) && 
                instruction.getOption1().equals(instructionFromBuffer.getOption1()) && 
                instruction.getOption2().equals(instructionFromBuffer.getOption2()) &&
                instruction.getOption3().equals(instructionFromBuffer.getOption3())
            ) {
                return i;
            }
        }
        throw new Exception("Instruction not found");
    }
}
