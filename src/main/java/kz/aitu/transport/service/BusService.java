package service;

import database.DatabaseConnection;
import model.Bus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusService {
    public void addBus(Bus bus) throws SQLException {
        String sql = """
                INSERT INTO bus(id, route_name, capacity, current_passengers)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bus.getId());
            ps.setString(2, bus.getRouteName());
            ps.setInt(3, bus.getCapacity());
            ps.setInt(4, bus.getCurrentPassengers());

            ps.executeUpdate();
        }
    }

    public Bus getBusById(int id) throws SQLException {
        String sql = "SELECT * FROM bus WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Bus bus = new Bus(
                        rs.getInt("id"),
                        rs.getString("route_name"),
                        rs.getInt("capacity")
                );

                int passengers = rs.getInt("current_passengers");
                for (int i = 0; i < passengers; i++) {
                    bus.boardPassenger();
                }

                return bus;
            }
            return null;
        }
    }

    public List<Bus> getAllBuses() throws SQLException {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT * FROM bus";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Bus bus = new Bus(
                        rs.getInt("id"),
                        rs.getString("route_name"),
                        rs.getInt("capacity")
                );

                int passengers = rs.getInt("current_passengers");
                for (int i = 0; i < passengers; i++) {
                    bus.boardPassenger();
                }

                buses.add(bus);
            }
        }
        return buses;
    }

    public void updatePassengers(Bus bus) throws SQLException {
        String sql = """
            UPDATE bus
            SET current_passengers = ?
            WHERE id = ?
            """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bus.getCurrentPassengers());
            ps.setInt(2, bus.getId());
            ps.executeUpdate();
        }
    }

    public void deleteBus(int busId) throws SQLException {
        String sql = "DELETE FROM bus WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, busId);
            ps.executeUpdate();
        }
    }
}
