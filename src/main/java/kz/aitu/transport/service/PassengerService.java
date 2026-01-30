package service;

import java.sql.*;

import database.DatabaseConnection;
import model.Passenger;

public class PassengerService {
    public void registerPassenger(Passenger passenger) throws SQLException {
        String sql = "INSERT INTO passenger(card_id, name, balance) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, passenger.getCardId());
            ps.setString(2, passenger.getName());
            ps.setDouble(3, passenger.getBalance());
            ps.executeUpdate();
        }
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
}
