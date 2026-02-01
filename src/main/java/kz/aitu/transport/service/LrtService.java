package kz.aitu.transport.service;

import kz.aitu.transport.database.DatabaseConnection;
import kz.aitu.transport.model.Lrt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LrtService {
    public void addLrt(Lrt lrt) throws SQLException {
        String sql = """
                INSERT INTO lrt(id, route_name, capacity, current_passengers)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, lrt.getId());
            ps.setString(2, lrt.getRouteName());
            ps.setInt(3, lrt.getCapacity());
            ps.setInt(4, lrt.getCurrentPassengers());

            ps.executeUpdate();
        }
    }

    public Lrt getLrtById(int id) throws SQLException {
        String sql = "SELECT * FROM lrt WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Lrt lrt = new Lrt(
                        rs.getInt("id"),
                        rs.getString("route_name"),
                        rs.getInt("capacity")
                );

                int passengers = rs.getInt("current_passengers");
                for (int i = 0; i < passengers; i++) {
                    lrt.boardPassenger();
                }

                return lrt;
            }
            return null;
        }
    }

    public List<Lrt> getAllLrts() throws SQLException {
        List<Lrt> lrts = new ArrayList<>();
        String sql = "SELECT * FROM lrt";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Lrt lrt = new Lrt(
                        rs.getInt("id"),
                        rs.getString("route_name"),
                        rs.getInt("capacity")
                );

                int passengers = rs.getInt("current_passengers");
                for (int i = 0; i < passengers; i++) {
                    lrt.boardPassenger();
                }

                lrts.add(lrt);
            }
        }
        return lrts;
    }

    public void updatePassengers(Lrt lrt) throws SQLException {
        String sql = """
            UPDATE lrt
            SET current_passengers = ?
            WHERE id = ?
            """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, lrt.getCurrentPassengers());
            ps.setInt(2, lrt.getId());
            ps.executeUpdate();
        }
    }

    public void deleteLrt(int lrtId) throws SQLException {
        String sql = "DELETE FROM lrt WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, lrtId);
            ps.executeUpdate();
        }
    }
}
