package Controller;

import Constants.Instructions.InstructionsEnum;

public class InstructionController {
    public static InstructionsEnum findInstructionEnumBasedInInstructionName(String name) throws Exception {
        InstructionsEnum[] instructionsEnum = InstructionsEnum.values();
        for (InstructionsEnum instructionEnum : instructionsEnum) {
            if (instructionEnum.equals(name)) {
                return instructionEnum;
            }
        }
        throw new Exception("InstructionEnum not founded");
    }
}
