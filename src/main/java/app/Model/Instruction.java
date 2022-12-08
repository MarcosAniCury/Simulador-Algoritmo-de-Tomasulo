package main.java.app.Model;
import main.java.app.Constants.Instructions;

public class Instruction {
    private String instruction;
    private String option1;
    private String option2;
    private String option3;

    public Instruction(String instruction, String option1, String option2, String option3) {
        setInstruction(instruction);
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;    
    }

    public void setInstruction(String instruction) {
        if (Instructions.ALL_INSTRUCTIONS.contains(instruction.toLowerCase().trim())) {
            this.instruction = instruction.toLowerCase().trim();
        }
    }
}
