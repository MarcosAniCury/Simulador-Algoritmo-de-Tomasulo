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

    public BufferInstruction[] getBufferInstructions() {
        return this.reorderBuffer.toArray(new BufferInstruction[0]);
    }

    public BufferInstruction getIndex(int index) {
        return reorderBuffer.get(index);
    }

    public int size() {
        return this.reorderBuffer.size();
    }
}
