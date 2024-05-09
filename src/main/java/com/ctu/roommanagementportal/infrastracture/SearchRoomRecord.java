package com.ctu.roommanagementportal.infrastracture;

import com.ctu.roommanagementportal.abstraction.Room;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * A class to search for room records based on various criteria.
 */
public class SearchRoomRecord {

    /**
     * The main method to execute room search based on user input.
     * @param args The command line arguments passed to the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Initialize scanner for user input
        RoomDao roomDAO = new RoomDao(); // Data Access Object for database operations

        // Prompt user to select search criteria
        System.out.println("Search for rooms by:");
        System.out.println("1. Room Name");
        System.out.println("2. Room Type");
        System.out.println("3. Equipments");

        int searchType = InputValidator.validateIntegerInput(scanner); // Validate integer input

        try {
            List<Room> foundRooms;
            if (searchType == 1) { // Search by room name
                System.out.println("Enter room name:");
                String roomName = scanner.next();
                foundRooms = roomDAO.searchRoomsByName(roomName);
            } else if (searchType == 2) { // Search by room type
                System.out.println("Enter room type:");
                String roomType = scanner.next();
                foundRooms = roomDAO.searchRoomsByType(roomType);
            } else if (searchType == 3) { // Retrieve list of room records by equipment
                System.out.println("Enter equipment:");
                String equipment = scanner.next();
                foundRooms = roomDAO.searchRoomsByEquipment(equipment);
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
                scanner.close(); // Exit if invalid option
                return;
            }

            if (foundRooms != null && !foundRooms.isEmpty()) { // Check if rooms are found
                System.out.println("Found rooms:");
                for (Room room : foundRooms) {
                    room.displayRoomInfo(); // Display room details
                }
            } else {
                System.out.println("No rooms found matching the criteria.");
            }
        } catch (SQLException e) { // Handle SQL exceptions
            System.err.println("Error during room search: " + e.getMessage());
        } finally {
            scanner.close(); // Close scanner
        }
    }
}