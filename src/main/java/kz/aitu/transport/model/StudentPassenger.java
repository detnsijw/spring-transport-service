package model;

public class StudentPassenger extends Passenger {
    private double discount;

    public StudentPassenger(String name, int cardId, double balance, double discount) {
        super(name, cardId, balance);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount; }

    public void setDiscount(double discount) {
        this.discount = discount; }

    @Override
    public boolean payFare(double baseFare) {
        double finalFare = baseFare * (1.0 - discount);
        return super.payFare(finalFare);
    }

    @Override
    public String getType() {
        return "StudentPassenger";
    }

    @Override
    public String toString() {
        return super.toString() + " (discount=" + discount + ")";
    }
}