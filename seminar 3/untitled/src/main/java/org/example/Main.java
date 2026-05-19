package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Garage garage = new Garage(10);

        Car car1 = new Car("C1", "BMW", 220, 9500, 4);
        Car car2 = new Car("C2", "Audi", 210, 12000, 2);

        Motorcycle motorcycle1 = new Motorcycle("M1", "Yamaha", 180, 6500, false);
        Motorcycle motorcycle2 = new Motorcycle("M2", "Harley", 160, 3000, true);

        Truck truck1 = new Truck("T1", "Volvo", 130, 16000, 5000);

        garage.add(car1);
        garage.add(car2);
        garage.add(motorcycle1);
        garage.add(motorcycle2);
        garage.add(truck1);

        System.out.println("\n--- Polymorphism demonstration ---");

        Vehicle[] vehicles = new Vehicle[5];

        vehicles[0] = car1;
        vehicles[1] = car2;
        vehicles[2] = motorcycle1;
        vehicles[3] = motorcycle2;
        vehicles[4] = truck1;

        for (int i = 0; i < vehicles.length; i++) {
            vehicles[i].move();
        }

        System.out.println("\n--- Comparing 2 cars using equals() ---");

        Car car3 = new Car("C1", "BMW", 220, 9500, 4);

        if (car1.equals(car3)) {
            System.out.println("car1 and car3 are equal because they have the same ID.");
        } else {
            System.out.println("car1 and car3 are different.");
        }

        System.out.println("\n--- Renting 2 vehicles ---");

        garage.rentById("C1");
        garage.rentById("M2");

        garage.printAvailable();

        garage.printNeedsService();

        System.out.println("\n--- Rental estimates ---");

        garage.printRentalEstimate("C2", 3);
        garage.printRentalEstimate("M2", 4);
        garage.printRentalEstimate("T1", 2);

        System.out.println("\n--- Returning one vehicle ---");

        garage.returnById("C1", 700);

        garage.printAvailable();
    }
}