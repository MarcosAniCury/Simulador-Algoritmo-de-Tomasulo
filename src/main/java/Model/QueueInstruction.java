package Model;

public class QueueInstruction {
    private Instruction instruction;
    private int indexInQueue;

    public QueueInstruction(Instruction instruction, int indexInQueue) throws Exception {
        this.instruction = instruction;
        this.indexInQueue = indexInQueue;
    }

    public Instruction getInstruction() {
        return this.instruction;
    }

    public int getIndexInQueue() {
        return this.indexInQueue;
    }
}
