package Controller;

import java.util.HashMap;

import Constants.ReservationStation.TypeEnum;
import Model.ReservationStation;

public class ReservationStationController {
    private static HashMap<TypeEnum, ReservationStation> allReservationsArea;

    public static void startReservationStation() {
        for (TypeEnum typeReservationStation : TypeEnum.values()) {
            ReservationStationController.allReservationsArea.put(typeReservationStation, new ReservationStation(typeReservationStation));
        }
    }
}
