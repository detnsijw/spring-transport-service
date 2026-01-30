package model;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class Passenger implements Payable {
    private String name;
    private int cardId;
    private double balance;

    public Passenger(String name, int cardId, double balance) {
        this.name = name;
        this.cardId = cardId;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getCardId() {
        return cardId;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void turnUp(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println(name + " tried to turn up his balance with zero or negative amount!");
        }
    }

    @Override
    public boolean payFare(double baseFare) {
        if (baseFare <= balance) {
            balance -= baseFare;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Passenger)) {
            return false;
        }
        Passenger passenger = (Passenger) obj;
        return this.cardId == passenger.cardId;
    }

    public String getType() {
        return "model.Passenger";
    }

    public static void insertPassenger(int cardId, String name, double balance) {
        String sql = "INSERT INTO passenger(card_id, name, balance) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cardId);
            ps.setString(2, name);
            ps.setDouble(3, balance);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAllPassengers() {
        String sql = "SELECT * FROM passenger";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("card_id") + " " +
                                rs.getString("name") + " " +
                                rs.getDouble("balance")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBalance(int cardId, double newBalance) {
        String sql = "UPDATE passenger SET balance = ? WHERE card_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, cardId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "model.Passenger{" +
                "name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", balance=" + balance +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(cardId);
    }
}
