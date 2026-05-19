package org.example;

public class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String id, String brand, double speed, int mileage, boolean hasSidecar) {
        super(id, brand, speed, mileage);
        this.hasSidecar = hasSidecar;
    }

    public boolean isHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }

    @Override
    public void move() {
        System.out.println("The motorcycle " + getBrand() + " is moving fast.");
    }

    @Override
    public boolean needsService() {
        return getMileage() >= 6000;
    }

    @Override
    public double rentalPrice(int days) {
        double price = 30 * days;

        if (hasSidecar) {
            price = price + 15 * days;
        }

        return price;
    }

    @Override
    public String toString() {
        return "Motorcycle { " + super.toString() + ", Has sidecar: " + hasSidecar + " }";
    }
}
