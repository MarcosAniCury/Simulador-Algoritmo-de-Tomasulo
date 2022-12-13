package Model;

import Constants.Instructions.InstructionsEnum;

public class ReservationStationInstruction {
    private String name;
    private InstructionsEnum instructionType;
    private Register registerTarget;
    private Register registerOne;
    private Register registerTwo;

    public ReservationStationInstruction(String name,InstructionsEnum instructionType,Register registerTarget,Register registerOne,Register registerTwo) {
        this.name = name;
        this.instructionType = instructionType;
        this.registerTarget = registerTarget;
        this.registerOne = registerOne;
        this.registerTwo = registerTwo;
    }

    public Register getRegisterTarget() {
        return this.registerTarget;
    }
}
