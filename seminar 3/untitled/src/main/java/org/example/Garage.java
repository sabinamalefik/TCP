package org.example;

public class Garage {
    private Vehicle[] fleet;
    private int size;

    public Garage(int capacity) {
        fleet = new Vehicle[capacity];
        size = 0;
    }

    public void add(Vehicle v) {
        if (size == fleet.length) {
            throw new RuntimeException("Garage is full.");
        }

        if (findById(v.getId()) != null) {
            throw new RuntimeException("Vehicle with ID " + v.getId() + " already exists.");
        }

        fleet[size] = v;
        size++;

        System.out.println("Vehicle added: " + v.getId());
    }

    public Vehicle findById(String id) {
        for (int i = 0; i < size; i++) {
            if (fleet[i].getId().equals(id)) {
                return fleet[i];
            }
        }

        return null;
    }

    public void rentById(String id) {
        Vehicle v = findById(id);

        if (v == null) {
            throw new RuntimeException("Vehicle with ID " + id + " was not found.");
        }

        v.rent();
    }

    public void returnById(String id, int drivenKm) {
        Vehicle v = findById(id);

        if (v == null) {
            throw new RuntimeException("Vehicle with ID " + id + " was not found.");
        }

        v.returnVehicle(drivenKm);
    }

    public void printAvailable() {
        System.out.println("\nAvailable vehicles:");

        for (int i = 0; i < size; i++) {
            if (!fleet[i].isRented()) {
                System.out.println(fleet[i]);
            }
        }
    }

    public void printNeedsService() {
        System.out.println("\nVehicles that need service:");

        for (int i = 0; i < size; i++) {
            if (fleet[i].needsService()) {
                System.out.println(fleet[i]);
            }
        }
    }

    public void printRentalEstimate(String id, int days) {
        Vehicle v = findById(id);

        if (v == null) {
            throw new RuntimeException("Vehicle with ID " + id + " was not found.");
        }

        if (days <= 0) {
            throw new RuntimeException("Days must be greater than 0.");
        }

        System.out.println("Rental estimate for " + id + " for " + days + " days: " + v.rentalPrice(days));
    }
}