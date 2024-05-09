/**
 * This class allows users to create a room records and save them to a database.
 * It provides methods for gathering information about different types of rooms such  as classrooms,computer laboratories,
 * libraries, and smart rooms.
 */
package com.ctu.roommanagementportal.infrastracture;

import com.ctu.roommanagementportal.abstraction.Room;
import com.ctu.roommanagementportal.dbservices.CreateTables;
import com.ctu.roommanagementportal.model.RoomType.Classroom;
import com.ctu.roommanagementportal.model.RoomType.CompLaboratory;
import com.ctu.roommanagementportal.model.RoomType.Library;
import com.ctu.roommanagementportal.model.RoomType.Smartroom;
import java.sql.SQLException;

import java.util.Scanner;


/**
 * The main method of the program.
 * It prompts the user to select a room type, gathers information about the room,
 * creates a room object based on the user's input, and saves the room record to the database.
 *
 */
public class CreateRoomRecord {
    public static void main(String[] args) {

//        CreateTables createTables = new CreateTables();

        Scanner scanner = new Scanner(System.in);

        // Prompt use to select a room type
        System.out.println("Select a room type:");
        System.out.println("1. Classroom");
        System.out.println("2. Computer Laboratory");
        System.out.println("3. Library");
        System.out.println("4. Smart room");

        // Validate user input for room type
        String roomType = InputValidator.validateRoomTypeInput(scanner);

        // Create a room record to the database
        Room newRoom = createRoom(scanner, roomType); // Use createRoom to construct the room based on roomType
        RoomDao roomDao = new RoomDao(); // Create a DAO instance

//        try {
//            RoomDao roomDao = new RoomDao(); // Create a DAO instance
//            roomDao.insertRoom(newRoom);
//            System.out.println("Room record inserted successfully");
//        } catch (SQLException e) {
//            System.out.println("Error inserting room record: " + e.getMessage());
//        }
//    }
        try {
            roomDao.saveRoom(newRoom); // Save the room record to the database
            System.out.println("Room record saved successfully.");

            // Display the details of what the user entered
            System.out.println("Here are the details of the room you created:");
            newRoom.displayRoomInfo(); // Display the user input

        } catch (SQLException e) {
            System.err.println("Error saving room record: " + e.getMessage());
            e.printStackTrace();
        }

       scanner.close(); // Close scanner when finished
    }

    /**
     * Create a room object based on user input
     *
     * @param scanner  The scanner object for user input.
     * @param roomType The type of room selected by the user.
     * @return The created room object
     */
    private static Room createRoom(Scanner scanner, String roomType) {

        System.out.println("Enter room number: ");
        String roomNumber = InputValidator.validateNonEmptyStringInput(scanner); // Ensures non-empty input

        System.out.println("Enter room capacity: ");
        int capacity = InputValidator.validateIntegerInput(scanner);

        System.out.println("Is the room available?(yes/no): ");
        boolean roomStatus = InputValidator.validateBooleanInput(scanner);

        System.out.println("Does it have a projector?(yes/no): ");
        boolean hasProjector =InputValidator.validateBooleanInput(scanner);

        System.out.println("Enter building location. Available Buildings are..");
        System.out.println("'Admin' for Admin Building");
        System.out.println("'COT' for College of Technology.");
        System.out.println("'COE' for College of Engineering.");
        String buildingLocation = InputValidator.validateRoomBuilding(scanner).trim();

        System.out.println("Provide here the things that needs maintenance: ");
        String maintenanceNotes = InputValidator.validateNonEmptyStringInput(scanner).trim();

        switch (roomType) {
            case "1":
                return createClassroom(scanner, roomNumber, capacity, roomStatus, hasProjector, buildingLocation, maintenanceNotes);
            case "2":
                return createLaboratory(scanner, roomNumber, capacity, roomStatus, hasProjector, buildingLocation, maintenanceNotes);
            case "3":
                return createLibrary(scanner, roomNumber, capacity, roomStatus, hasProjector, buildingLocation, maintenanceNotes);
            case "4":
                return createSmartroom(scanner, roomNumber, capacity, roomStatus, hasProjector, buildingLocation, maintenanceNotes);
            default:
                throw new IllegalArgumentException("Invalid room type.");
        }
    }

    /**
     * Creates a classroom object based on user input.
     *
     * @param scanner          The scanner object for user input.
     * @param roomNumber       The room number.
     * @param capacity         The capacity of the room.
     * @param roomStatus       The availability status of the room.
     * @param hasProjector     Indicates whether the room has a projector.
     * @param buildingLocation The location of the building.
     * @param maintenanceNotes Maintenance notes for the room.
     * @return The created classroom object.
     */
    private static Room createClassroom(Scanner scanner, String roomNumber, int capacity, boolean roomStatus, boolean hasProjector,
                                        String buildingLocation, String maintenanceNotes) {
        System.out.println("Enter number of chairs:");
        int numOfChairs = InputValidator.validateIntegerInput(scanner); // Correct initialization

        return new Classroom(roomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, hasProjector, numOfChairs); // Correct constructor call
    }

    /**
     * Create as a computer laboratory object based on user input.
     *
     * @param scanner          The scanner object for user input.
     * @param roomNumber       The room number.
     * @param capacity         The capacity of the room.
     * @param roomStatus       The availability status of the room.
     * @param hasProjector     Indicates whether the room has a projector.
     * @param buildingLocation The location of the building.
     * @param maintenanceNotes Maintenance notes for the room.
     * @return The created computer laboratory.
     */
    private static Room createLaboratory(Scanner scanner, String roomNumber, int capacity, boolean roomStatus, boolean hasProjector,
                                         String buildingLocation, String maintenanceNotes) {
        System.out.println("Enter number of computers:");
        int numOfComputers = InputValidator.validateIntegerInput(scanner);

        return new CompLaboratory(roomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, hasProjector, numOfComputers); // Correct constructor call
    }

    /**
     * Creates a library objects based on user input.
     *
     * @param scanner          The scanner object for user input.
     * @param roomNumber       The room number.
     * @param capacity         The capacity of the room.
     * @param roomStatus       The availability status of the room.
     * @param hasProjector     Indicates whether the room has a projector.
     * @param buildingLocation The location of the building.
     * @param maintenanceNotes Maintenance notes for the room.
     * @return The created library object.
     */
    private static Room createLibrary(Scanner scanner, String roomNumber, int capacity, boolean roomStatus, boolean hasProjector,
                                      String buildingLocation, String maintenanceNotes) {
        System.out.println("Enter number of desks:");
        int numOfDesks = InputValidator.validateIntegerInput(scanner); // Ensure correct initialization

        return new Library(roomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, hasProjector, numOfDesks); // Correct constructor call
    }

    /**
     * Creates a smart room object based on user input.
     *
     * @param scanner          The scanner object for user input.
     * @param roomNumber       The room number.
     * @param capacity         The capacity of the room.
     * @param roomStatus       The availability status of the room.
     * @param hasProjector     Indicates whether the room has a projector.
     * @param buildingLocation The location of the building.
     * @param maintenanceNotes Maintenance notes for the room.
     * @return The created smart room object.
     */
    private static Room createSmartroom(Scanner scanner, String roomNumber, int capacity, boolean roomStatus, boolean hasProjector,
                                        String buildingLocation, String maintenanceNotes) {
        System.out.println("Does it have a TV? (yes/no):");
        boolean hasTV = InputValidator.validateBooleanInput(scanner); // Correct variable initialization

        System.out.println("Does it have internet connection? (yes/no):");
        boolean hasInternetAccess = InputValidator.validateBooleanInput(scanner); // Correct variable initialization

        return new Smartroom(roomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, hasProjector, hasTV, hasInternetAccess); // Correct constructor call

    }

}


