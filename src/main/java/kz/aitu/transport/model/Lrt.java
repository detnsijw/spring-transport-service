package model;

public class Lrt extends Vehicle {
    private int capacity;
    private int currentPassengers;

    public Lrt(int id, String routeName, int capacity) {
        super(id, routeName);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean boardPassenger() {
        if (currentPassengers < capacity) {
            currentPassengers++;
            return true;
        }
        return false;
    }

    @Override
    public boolean exitPassenger() {
        if (currentPassengers > 0) {
            currentPassengers--;
            return true;
        }
        return false;
    }

    @Override
    public int getCurrentPassengers() {
        return currentPassengers;
    }

    @Override
    public String getType() {
        return "model.Lrt";
    }
}
