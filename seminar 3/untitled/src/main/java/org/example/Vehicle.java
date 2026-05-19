package org.example;

public abstract class Vehicle {
    private String id;
    private String brand;
    private double speed;
    private int mileage;
    private boolean rented;

    public Vehicle(String id, String brand, double speed, int mileage) {
        this.id = id;
        this.brand = brand;
        this.speed = speed;
        this.mileage = mileage;
        this.rented = false;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public double getSpeed() {
        return speed;
    }

    public int getMileage() {
        return mileage;
    }

    public boolean isRented() {
        return rented;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public void rent() {
        if (rented) {
            throw new RuntimeException("Vehicle is already rented.");
        }

        rented = true;
        System.out.println(brand + " with ID " + id + " was rented.");
    }

    public void returnVehicle(int drivenKm) {
        if (!rented) {
            throw new RuntimeException("Vehicle is not rented.");
        }

        if (drivenKm <= 0) {
            throw new RuntimeException("Driven kilometers must be greater than 0.");
        }

        mileage = mileage + drivenKm;
        rented = false;

        System.out.println(brand + " with ID " + id + " was returned.");
    }

    public abstract void move();

    public abstract boolean needsService();

    public abstract double rentalPrice(int days);

    @Override
    public String toString() {
        return "ID: " + id +
                ", Brand: " + brand +
                ", Speed: " + speed +
                ", Mileage: " + mileage +
                ", Rented: " + rented;
    }
}