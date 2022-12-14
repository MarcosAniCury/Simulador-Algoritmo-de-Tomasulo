package Model;

import java.util.ArrayList;
import java.util.List;

import Constants.Definitions;
import Constants.Instructions.InstructionsEnum;
import Constants.ReservationStation.TypeEnum;

public class ReservationStation {
    private List<ReservationStationInstruction> reservationStation;
    private TypeEnum type;

    public ReservationStation(TypeEnum type) {
        this.type = type;
        this.reservationStation = new ArrayList<ReservationStationInstruction>();
    }

    public ReservationStationInstruction[] getReservationStationInstructions() {
        return this.reservationStation.toArray(new ReservationStationInstruction[0]);
    }

    public TypeEnum getType() {
        return this.type;
    }

    public void add(InstructionsEnum instructionType, Register registerTarget, Register registerOne, Register registerTwo, Instruction intruction) throws Exception {
        if (this.reservationStation.size() > Definitions.TAM_RESERVATION_STATION) {
            throw new Exception("Cannot insert new values in reservation station. Max size: "+Definitions.TAM_RESERVATION_STATION);
        }
        this.reservationStation.add(new ReservationStationInstruction(instructionType, registerTarget, registerOne, registerTwo, intruction));
    }

    public ReservationStationInstruction remove() {
        return reservationStation.remove(0);
    }

    public ReservationStationInstruction remove(int i) {
        return reservationStation.remove(i);
    }

    public int findIndexBasedInIntruction(Instruction instruction) throws Exception {
        for (int i = 0; i < reservationStation.size(); i++) {
            Instruction instructionFromReservation = reservationStation.get(i).getInstruction();
            if (
                instruction.getInstruction().equals(instructionFromReservation.getInstruction()) && 
                instruction.getOption1().equals(instructionFromReservation.getOption1()) && 
                instruction.getOption2().equals(instructionFromReservation.getOption2()) &&
                instruction.getOption3().equals(instructionFromReservation.getOption3())
            ) {
                return i;
            }
        }
        return -1;
    }
}
