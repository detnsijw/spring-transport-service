package service;

import model.Bus;
import model.Lrt;
import model.Passenger;

import java.sql.*;

public class TransportService {
    private final BusService busService = new BusService();
    private final LrtService lrtService = new LrtService();
    private final PassengerService passengerService = new PassengerService();

    public boolean rideBus(int busId, int passengerId, double fare)
            throws SQLException {

        Bus bus = busService.getBusById(busId);
        Passenger passenger = passengerService.getPassengerById(passengerId);

        if (bus == null || passenger == null) return false;
        if (!passenger.payFare(fare)) return false;
        if (!bus.boardPassenger()) return false;

        busService.updatePassengers(bus);
        passengerService.updateBalance(passenger);

        return true;
    }

    public boolean rideLrt(int lrtId, int passengerId, double fare)
            throws SQLException {

        Lrt lrt = lrtService.getLrtById(lrtId);
        Passenger passenger = passengerService.getPassengerById(passengerId);

        if (lrt == null || passenger == null) {
            return false;
        }
        if (!passenger.payFare(fare)) {
            return false;
        }
        if (!lrt.boardPassenger()) {
            return false;
        }

        lrtService.updatePassengers(lrt);
        passengerService.updateBalance(passenger);

        return true;
    }
}
