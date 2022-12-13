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

    public void add(String name, InstructionsEnum instructionType, Register registerTarget, Register registerOne, Register registerTwo) throws Exception {
        if (this.reservationStation.size() > Definitions.TAM_RESERVATION_STATION) {
            throw new Exception("Cannot insert new values in reservation station. Max size: "+Definitions.TAM_RESERVATION_STATION);
        }
        this.reservationStation.add(new ReservationStationInstruction(name, instructionType, registerTarget, registerOne, registerTwo));
    }

    public void remove() {
        //return reservationStation.remove(0).getInstruction();
    }

    public ReservationStationInstruction[] reservationStationInstructions() {
        return (ReservationStationInstruction[]) this.reservationStation.toArray();
    }
}
