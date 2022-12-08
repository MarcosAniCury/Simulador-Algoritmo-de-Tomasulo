package app.Model;

import app.Constants.BufferInstruction.StateEnum;

public class BufferInstruction {
    private boolean busy;
    private Instruction instruction;
    private StateEnum state;
    private String value;
    private int indexInstructionQueue;

    public BufferInstruction(Instruction instruction, int indexInstructionQueue) {
        this.busy = false;
        this.instruction = instruction;
        this.state = StateEnum.STATE_ISSUE;
        this.value = "";
        this.indexInstructionQueue = indexInstructionQueue;
    }

    public Instruction getInstruction() {
        return this.instruction;
    }
}
