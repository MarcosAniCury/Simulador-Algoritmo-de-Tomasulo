package Model;

import Constants.BufferInstruction.StateEnum;

public class BufferInstruction {
    private Instruction instruction;
    private StateEnum state;
    private String value;
    private int indexInstructionQueue;
    private Register registerDestination;

    public BufferInstruction(Instruction instruction, int indexInstructionQueue) {
        this.instruction = instruction;
        this.state = StateEnum.STATE_ISSUE;
        this.value = "";
        this.indexInstructionQueue = indexInstructionQueue;
    }

    public Instruction getInstruction() {
        return this.instruction;
    }
}
