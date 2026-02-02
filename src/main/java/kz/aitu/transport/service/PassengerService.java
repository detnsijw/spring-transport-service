package kz.aitu.transport.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import kz.aitu.transport.database.DatabaseConnection;
import kz.aitu.transport.model.Passenger;

public class PassengerService {
    public void addPassenger(Passenger passenger) {
        String sql = """
            INSERT INTO passenger(card_id, name, balance)
            VALUES (?, ?, ?)
            """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, passenger.getCardId());
            ps.setString(2, passenger.getName());
            ps.setDouble(3, passenger.getBalance());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add passenger");
        }
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        String sql = "SELECT * FROM passenger";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Passenger passenger = new Passenger(
                        rs.getString("name"),
                        rs.getInt("card_id"),
                        rs.getDouble("balance")
                );

                passengers.add(passenger);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch passengers");
        }

        return passengers;
    }

    public Passenger getPassengerById(int cardId) throws SQLException {
        String sql = "SELECT * FROM passenger WHERE card_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cardId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Passenger(
                        rs.getString("name"),
                        rs.getInt("card_id"),
                        rs.getDouble("balance")
                );
            }
            return null;
        }
    }

    public void updateBalance(Passenger passenger) throws SQLException {
        String sql = "UPDATE passenger SET balance=? WHERE card_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, passenger.getBalance());
            ps.setInt(2, passenger.getCardId());
            ps.executeUpdate();
        }
    }

    public void deletePassengerById(int cardId) {
        String sql = "DELETE FROM passenger WHERE card_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cardId);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException(
                        "Passenger with cardId=" + cardId + " not found"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete passenger");
        }
    }
}
