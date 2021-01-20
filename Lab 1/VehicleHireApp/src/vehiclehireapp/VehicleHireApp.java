package vehiclehireapp;

import au.edu.swin.vehicle.Vehicle;
import au.edu.swin.vehicle.VehicleType;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kevinnguyen2208
 */
public class VehicleHireApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

// Create the vehicle types
        VehicleType sedan = new VehicleType("SEDAN", "A standard sedan", 4);
        VehicleType limo6 = new VehicleType("LIMO6", "A six seater limo", 6);
        VehicleType limo8 = new VehicleType("LIMO8", "An eight seater limo", 8);

// Create the vehicles
        ArrayList<Vehicle> vehicles = new ArrayList();
        vehicles.add(new Vehicle("Ed's Holden Caprice", "Silver", sedan, 2002));
        vehicles.add(new Vehicle("John's Mercedes C200", "Black", sedan, 2005));
        vehicles.add(new Vehicle("Guy's Volvo 244 DL", "Blue", sedan, 1976));
        vehicles.add(new Vehicle("Sasco'sFord Limo", "White", limo6, 2014));
        vehicles.add(new Vehicle("Peter's Ford Limo", "White", limo6, 2004));
        vehicles.add(new Vehicle("Robert's Ford Limo", "White", limo8, 2003));

        System.out.println("\n\nList of vehicles in system:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

        String typeCode = args[0];

        System.out.println("\n\nList of vehicle of type " + typeCode);
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType().getCode().equals(typeCode)) {
                System.out.println(vehicle);
            }
        }

        System.out.println("It will display a list of vehicle type you choose");
        System.out.println("1: SEDAN");
        System.out.println("2: LIMO6");
        System.out.println("3: LIMO8");
        System.out.println("4: Exit");

        while (true) {
            Scanner options = new Scanner(System.in);
            System.out.println("Please select an option (1-4):");
            int choice = Integer.parseInt(options.nextLine());
            if (choice == 1) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getType().getCode() == "SEDAN") {
                        System.out.println(vehicle.getName() + " " + vehicle.getColour() + " (" + vehicle.getType().getDescription() + ") " + vehicle.getYear());
                    }
                }
            } else if (choice == 2) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getType().getCode() == "LIMO6") {
                        System.out.println(vehicle.getName() + " " + vehicle.getColour() + " (" + vehicle.getType().getDescription() + ") " + vehicle.getYear());
                    }
                }
            } else if (choice == 3) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getType().getCode() == "LIMO8") {
                        System.out.println(vehicle.getName() + " " + vehicle.getColour() + " (" + vehicle.getType().getDescription() + ") " + vehicle.getYear());
                    }
                }
            } else if (choice == 4) 
            {
                System.exit(0);
            }
        }
    }
}
