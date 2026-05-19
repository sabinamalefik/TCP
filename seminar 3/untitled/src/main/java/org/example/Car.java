package org.example;

public class Car extends Vehicle {
    private int doors;

    public Car(String id, String brand, double speed, int mileage, int doors) {
        super(id, brand, speed, mileage);
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    @Override
    public void move() {
        System.out.println("The car " + getBrand() + " is moving on the road.");
    }

    @Override
    public boolean needsService() {
        return getMileage() >= 10000;
    }

    @Override
    public double rentalPrice(int days) {
        double price = 50 * days;

        if (doors >= 4) {
            price = price + price * 0.10;
        }

        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Car)) {
            return false;
        }

        Car other = (Car) obj;

        return this.getId().equals(other.getId());
    }

    @Override
    public String toString() {
        return "Car { " + super.toString() + ", Doors: " + doors + " }";
    }
}