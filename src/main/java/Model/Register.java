package Model;

public class Register {
    private String name;
    private String value;
    private BufferInstruction bufferInstruction;

    public Register(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public void setBufferInstruction(BufferInstruction bufferInstruction){
        this.bufferInstruction = bufferInstruction;
    }
}
