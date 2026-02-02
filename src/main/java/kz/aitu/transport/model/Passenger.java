package kz.aitu.transport.model;

import kz.aitu.transport.database.DatabaseConnection;

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
        return "Passenger";
    }

    @Override
    public String toString() {
        return "kz.aitu.transport.model.Passenger{" +
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
