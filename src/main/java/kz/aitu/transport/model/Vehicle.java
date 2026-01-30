package model;

import java.util.Objects;

public abstract class Vehicle {
    private int id;
    private String routeName;

    public Vehicle(int id, String routeName) {
        this.id = id;
        this.routeName = routeName;
    }

    public int getId() {
        return id;
    }

    public String getRouteName() {
        return routeName;
    }

    public abstract String getType();

    public abstract boolean boardPassenger();
    public abstract boolean exitPassenger();
    public abstract int getCurrentPassengers();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)){
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

