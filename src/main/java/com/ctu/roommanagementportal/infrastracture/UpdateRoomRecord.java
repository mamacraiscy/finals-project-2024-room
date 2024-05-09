/**
 * Package declaration for the UpdateRoomRecord class.
 */

package com.ctu.roommanagementportal.infrastracture;

//Import statements
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a class for updating room records.
 */
public class UpdateRoomRecord {

    /**
     * Constant variables that store the admin password
     */
    private static final String ADMIN_PASSWORD = "ctu@123";

    /**
     * method to get user role (admin or student).
     * @param scanner The Scanner object to read user input.
     * @return The user's role as a String
     */
    public static String getUserRole(Scanner scanner) {
        System.out.println("Are you an admin or a student?");//Prompt user for role
        String role = scanner.next().toLowerCase();//Read user input for role and convert to lowercase

        //Check user role and return appropriate message
        if (role.equals("admin")) {
            while (true) {
                System.out.println("Enter password: ");
                String password = scanner.next();
                if (password.equals(ADMIN_PASSWORD)) {
                    System.out.println("Admin login successful. You can update the room records.");
                    return role;
                } else {
                    System.out.println("Incorrect password. Please try again.");
                }
            }
        } else if (role.equals("student")) {
            return role;
        } else {
            System.out.println("Invalid role. Please enter 'admin' or 'student'.");
            return getUserRole(scanner);//Recursively call to prompt user for correct role
        }
    }

    /**
     * Main method, entry point of the program
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//Scanner object for user input

        String userRole = UpdateRoomRecord.getUserRole(scanner);//Get user role

        //Check if user is admin, if not, exit
        if (!userRole.equals("admin")) {
            System.out.println("Access denied. Only admins can update room records.");
            return; // Exit if user is not an admin
        }


        RoomDao roomDAO = new RoomDao(); // Data Access Object for updating record

        //Prompt user to select room type
        System.out.println("Update Room Record");
        System.out.println("Select room type:");
        System.out.println("1. Classroom");
        System.out.println("2. Computer Laboratory");
        System.out.println("3. Library");
        System.out.println("4. Smart room");

        int roomType = InputValidator.validateIntegerInput(scanner); // Validate integer input for room type

        Map<String, Object> updates = new HashMap<>(); // Store updates in a map

        System.out.println("Enter room number to update:");
        String roomNumber = scanner.next(); // Identify the room to be updated

        // Update room based on type
        switch (roomType) {
            case 1 -> { // Classroom
                System.out.println("Update number of chairs?");
                if (InputValidator.validateBooleanInput(scanner)) {
                    System.out.println("Enter number of chairs:");
                    updates.put("numOfChairs", InputValidator.validateIntegerInput(scanner)); // Validate input for chairs
                }
            }
            case 2 -> { // Computer Laboratory
                System.out.println("Update number of computers?");
                if (InputValidator.validateBooleanInput(scanner)) {
                    System.out.println("Enter number of computers:");
                    updates.put("numOfComputers", InputValidator.validateIntegerInput(scanner)); // Validate input for computers
                }
            }
            case 3 -> { // Library
                System.out.println("Update number of desks?");
                if (InputValidator.validateBooleanInput(scanner)) {
                    System.out.println("Enter number of desks:");
                    updates.put("numOfDesks", InputValidator.validateIntegerInput(scanner)); // Validate input for desks
                }
            }
            case 4 -> { // Smart room
                System.out.println("Update if it has a projector?");
                if (InputValidator.validateBooleanInput(scanner)) {
                    System.out.println("Does it have a projector?");
                    updates.put("hasProjector", InputValidator.validateBooleanInput(scanner)); // Validate boolean input
                }
            }
            default -> {
                System.out.println("Invalid room type.");
                return;
            }
        }

        try {
            roomDAO.updateRoom(roomNumber, updates); // Update room with collected data
            System.out.println("Room record updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating room record: " + e.getMessage());
        }
    }
}
