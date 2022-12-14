package Model;

import Constants.Instructions.InstructionsEnum;

public class ReservationStationInstruction {
    private InstructionsEnum instructionType;
    private Register registerTarget;
    private Register registerOne;
    private Register registerTwo;

    public ReservationStationInstruction(InstructionsEnum instructionType,Register registerTarget,Register registerOne,Register registerTwo) {
        this.instructionType = instructionType;
        this.registerTarget = registerTarget;
        this.registerOne = registerOne;
        this.registerTwo = registerTwo;
    }

    public InstructionsEnum getInstructionType() {
        return this.instructionType;
    }

    public Register getRegisterTarget() {
        return this.registerTarget;
    }

    public Register getRegisterOne() {
        return this.registerOne;
    }

    public Register getRegisterTwo() {
        return this.registerTwo;
    }
}
