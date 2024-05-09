/**
 * Input necessary packages.
 */

package com.ctu.roommanagementportal.infrastracture;

import com.ctu.roommanagementportal.abstraction.Room;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * A class to search for room records based on user input.
 */
public class SearchRoomRecord {

    /**
     * The main method to execute room search based on user input.
     * @param args The command line arguments passed to the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Initialize scanner for user input
        RoomDao roomDAO = new RoomDao(); // Data Access Object for database operations

        System.out.println("Search for rooms by:"); //Prompt user to select search criteria
        System.out.println("1. Room Type");
        System.out.println("2. Room Number");

        int searchType = InputValidator.validateIntegerInput(scanner); // Validate integer input

         //Initialize list to store found rooms

        try {
            List<Room> foundRooms;
            if (searchType == 1) { // Search by room type
                System.out.println("Enter room type:");
                String roomType = scanner.next();
                foundRooms = roomDAO.searchRooms(roomType, null);
            } else if (searchType == 2) { // Search by room number
                System.out.println("Enter room number:");
                String roomNumber = scanner.next();
                foundRooms = roomDAO.searchRooms(null, roomNumber);
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
                return; // Exit if invalid option
            }

            if (foundRooms != null && !foundRooms.isEmpty()) { //Check  if the rooms are found
                System.out.println("Found rooms:");
                for (Room room : foundRooms) {
                    room.displayRoomInfo(); // Display room details
                }
            } else {
                System.out.println("No rooms found matching the criteria.");
            }
        } catch (SQLException e) { //Handle SQL exceptions
            System.err.println("Error during room search: " + e.getMessage());
        }
    }
}
