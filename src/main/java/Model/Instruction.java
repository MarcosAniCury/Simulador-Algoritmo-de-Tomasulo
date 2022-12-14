package Model;

import java.util.Arrays;

import Constants.Instructions;

public class Instruction {
    private String instruction;
    private String option1;
    private String option2;
    private String option3;

    public Instruction(String instruction, String option1, String option2, String option3) throws Exception {
        setInstruction(instruction);
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;    
    }

    public Instruction(String instruction, String option1, String option2) throws Exception {
        setInstruction(instruction);
        this.option1 = option1;
        this.option2 = option2;   
    }

    public void setInstruction(String instruction) throws Exception{
        if (!Arrays.stream(Instructions.ALL_INSTRUCTIONS).anyMatch(item -> item.equals(instruction.toLowerCase().trim()))) {
            throw new Exception("Instruction not exist");
        }
        this.instruction = instruction.toLowerCase().trim();
    }

    public String getInstruction() {
        return this.instruction;
    }

    public String getOption1() {
        return this.option1;
    }

    public String getOption2() {
        return this.option2;
    }

    public String getOption3() {
        return this.option3;
    }

    public String getInstructionFormated(){
        return instruction + " " + option1 + ", " + option2 + ", " + option3;
    }

    @Override
    public String toString() {
        return "Instruction [instruction=" + instruction + ", option1=" + option1 + ", option2=" + option2
                + ", option3=" + option3 + "]";
    }
}
