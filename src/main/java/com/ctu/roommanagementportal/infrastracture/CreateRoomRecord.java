/**
 * This class allows users to create a room records and save them to a database.
 * It provides methods for gathering information about different types of rooms such  as classrooms,computer laboratories,
 * libraries, and smart rooms.
 */
package com.ctu.roommanagementportal.infrastracture;

import com.ctu.roommanagementportal.abstraction.Room;
import com.ctu.roommanagementportal.model.RoomType;
import com.ctu.roommanagementportal.dbservices.InsertRecords;
import java.sql.SQLException;
import java.util.Scanner;


/**
 * The main method of the program.
 * It prompts the user to select a room type, gathers information about the room,
 * creates a room object based on the user's input, and saves the room record to the database.
 *
 */
public class CreateRoomRecord {
    public static void main(String[] args) throws SQLException {

//        CreateTables createTables = new CreateTables();

        Scanner scanner = new Scanner(System.in);

        boolean roomInserted = false;

        while (!roomInserted) {

            System.out.println("------------------");
            System.out.println("Enter building location:");
            System.out.println("------------------");
            System.out.println("A - Admin Building");
            System.out.println("T - College of Technology");
            System.out.println("E - College of Engineering");
            System.out.print("\nEnter your choice: ");
            String buildingLocation = InputValidator.validateRoomBuilding(scanner);

            System.out.println("------------------");
            System.out.println("Select a room type");
            System.out.println("------------------");
            System.out.println("1. Classroom");
            System.out.println("2. Computer Laboratory");
            System.out.println("3. Library");
            System.out.println("4. Smart room");
            System.out.print("\nEnter your choice: ");

            String roomType = InputValidator.validateRoomTypeInput(scanner);

            Room newRoom = createRoom(scanner, roomType, buildingLocation);

            InsertRecords insertRecords = new InsertRecords();
            boolean success = insertRecords.insertRoomRecord(newRoom);
            if (success) {
                System.out.println("Room record successfully inserted.");
            } else {
                System.out.println("A room with the same name and location already exists. Please try again with different details.");
            }

            System.out.print("\nDo you want to create another room record? (yes/no): ");
            String createAnother = scanner.nextLine().trim().toLowerCase();

            if (createAnother.equals("no")) {
                roomInserted = true;
            }
        }

        System.out.println("\nThank you for using the room record creation system. Goodbye!");
        scanner.close();
    }

    private static Room createRoom(Scanner scanner, String roomType, String buildingLocation) {
        System.out.print("\nEnter room name: ");
        String roomName = InputValidator.validateNonEmptyStringInput(scanner);

        System.out.print("\nEnter room capacity: ");
        int capacity = InputValidator.validateIntegerInput(scanner);

        System.out.print("\nEnter number of chairs: ");
        int numOfChairs = InputValidator.validateIntegerInput(scanner);

        System.out.print("\nIs the room available? (yes/no): ");
        boolean roomStatus = InputValidator.validateBooleanInput(scanner);

        System.out.print("\nDoes it have a projector? (yes/no): ");
        boolean hasProjector = InputValidator.validateBooleanInput(scanner);

        System.out.print("\nProvide maintenance notes: ");
        String maintenanceNotes = InputValidator.validateNonEmptyStringInput(scanner).trim();


        switch (roomType) {
            case "1":
                return createClassroom(scanner, roomName, capacity, roomStatus, hasProjector, buildingLocation, maintenanceNotes, numOfChairs);
            case "2":
                return createLaboratory(scanner, roomName, capacity, roomStatus, hasProjector, buildingLocation, maintenanceNotes,numOfChairs);
            case "3":
                return createLibrary(scanner, roomName, capacity, roomStatus, hasProjector, buildingLocation, maintenanceNotes, numOfChairs);
            case "4":
                return createSmartroom(scanner, roomName, capacity, roomStatus, hasProjector, buildingLocation, maintenanceNotes, numOfChairs);
            default:
                throw new IllegalArgumentException("Invalid room type.");
        }
    }

    private static Room createClassroom(Scanner scanner, String roomName, int capacity, boolean roomStatus, boolean hasProjector,
                                        String buildingLocation, String maintenanceNotes, int numOfChairs) {
        System.out.print("\nDoes it have a whiteboard? (yes/no): ");
        boolean hasWhiteboard = InputValidator.validateBooleanInput(scanner);

        return new RoomType.Classroom(roomName, capacity, roomStatus, buildingLocation, maintenanceNotes, hasProjector, numOfChairs,hasWhiteboard);
    }

    private static Room createLaboratory(Scanner scanner, String roomName, int capacity, boolean roomStatus, boolean hasProjector,
                                         String buildingLocation, String maintenanceNotes, int numOfChairs) {
        System.out.print("\nEnter number of computers: ");
        int numOfComputers = InputValidator.validateIntegerInput(scanner);

        return new RoomType.CompLaboratory(roomName, capacity, roomStatus, buildingLocation, maintenanceNotes, hasProjector, numOfChairs, numOfComputers);
    }

    private static Room createLibrary(Scanner scanner,String roomName, int capacity, boolean roomStatus, boolean hasProjector,
                                      String buildingLocation, String maintenanceNotes, int numOfChairs) {
        System.out.print("\nEnter number of desks: ");
        int numOfDesks = InputValidator.validateIntegerInput(scanner);

        return new RoomType.Library(roomName, capacity, roomStatus, buildingLocation, maintenanceNotes, hasProjector, numOfChairs, numOfDesks);
    }

    private static Room createSmartroom(Scanner scanner, String roomName, int capacity, boolean roomStatus, boolean hasProjector,
                                        String buildingLocation, String maintenanceNotes, int numOfChairs) {
        System.out.print("\nDoes it have a TV? (yes/no): ");
        boolean hasTV = InputValidator.validateBooleanInput(scanner);

        System.out.print("\nDoes it have internet connection? (yes/no): ");
        boolean hasInternetAccess = InputValidator.validateBooleanInput(scanner);

        return new RoomType.Smartroom(roomName, capacity, roomStatus, buildingLocation, maintenanceNotes, hasProjector,hasTV, hasInternetAccess, numOfChairs);
    }
}