package Model;

public class Register {
    private String name;
    private int value;
    private BufferInstruction bufferInstruction;

    public Register(String name) {
        this.name = name;
        this.value = -1;
        this.bufferInstruction = null;
    }

    public String getName(){
        return this.name;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public BufferInstruction getBufferInstruction() {
        return this.bufferInstruction;
    }

    public void setBufferInstruction(BufferInstruction bufferInstruction){
        this.bufferInstruction = bufferInstruction;
    }
}
