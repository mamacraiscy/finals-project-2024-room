package com.ctu.roommanagementportal.infrastracture;

import com.ctu.roommanagementportal.abstraction.Room;
import com.ctu.roommanagementportal.dbservices.SelectFromDB;
import com.ctu.roommanagementportal.model.RoomType;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SearchRoomRecord {

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean exit = false;

    public static void main(String[] args) {
        while (!exit) {
            System.out.println("---------------------");
            System.out.println("Search for rooms by:");
            System.out.println("---------------------");
            System.out.println("1. Room Name");
            System.out.println("2. Room Type");
            System.out.println("3. Equipments");
            System.out.print("\n Enter your choice: ");

            int searchType = InputValidator.validateIntegerInput(scanner);

            try {
                List<? extends Room> foundRooms;

                if (searchType == 1) {
                    foundRooms = searchByRoomName(scanner);
                } else if (searchType == 2) {
                    foundRooms = searchByRoomType(scanner);
                } else if (searchType == 3) {
                    foundRooms = searchByEquipment(scanner);
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                    continue; // Prompt user again for input
                }

                if (!foundRooms.isEmpty()) {
                    System.out.println("\nSearching room records...");


                    System.out.println("\n---------------------------------------------------" +
                            "-------------------------------------------------------------------" +
                            "----------------------------------------------------------------------" +
                            "-----------------------------------");
                    System.out.printf("| %-20s | %-20s | %-10s | %-10s | %-20s | %-20s | %-10s | %-18s | %-10s | %-18s | %-15s | %-2s | %-15s |%n",
                            "Room Name", "Room Type", "Capacity", "Room Status", "Building Location", "Maintenance Notes", "Projector", "Number of Chairs", "White Board", "Number of Computers",
                            "Number of Desks", "TV", "Internet Access");
                    System.out.println("------------------------------------------------------" +
                            "--------------------------------------------------------------------------" +
                            "--------------------------------------------------------------------------" +
                            "-----------------------");

                    for (Room room : foundRooms) {
                        if (room instanceof RoomType.CompLaboratory) {
                            RoomType.CompLaboratory compLabRoom = (RoomType.CompLaboratory) room;
                            System.out.printf("| %-20s | %-20s | %-10d | %-10s | %-20s | %-20s | %-10s | %-18d | %-10s | %-18d | %-15s | %-2s | %-15s |%n",
                                    room.getRoomName(), room.getRoomType(), room.getCapacity(), room.getRoomStatus(),
                                    room.getBuildingLocation(), room.getMaintenanceNotes(), room.isHasProjector() ? "Yes" : "No",
                                    room.getNumOfChairs(), "N/A", compLabRoom.getNumOfComputers(),
                                    "N/A", "N/A", "N/A");
                        } else if (room instanceof RoomType.Library) {
                            RoomType.Library libraryRoom = (RoomType.Library) room;
                            System.out.printf("| %-20s | %-20s | %-10d | %-10s | %-20s | %-20s | %-10s | %-18d | %-10s | %-18s | %-15d | %-2s | %-15s |%n",
                                    room.getRoomName(), room.getRoomType(), room.getCapacity(), room.getRoomStatus(),
                                    room.getBuildingLocation(), room.getMaintenanceNotes(), room.isHasProjector() ? "Yes" : "No",
                                    room.getNumOfChairs(), "N/A", "N/A",
                                    libraryRoom.getNumOfDesks(), "N/A", "N/A");
                        } else if (room instanceof RoomType.Classroom) {
                            RoomType.Classroom classroomRoom = (RoomType.Classroom) room;
                            System.out.printf("| %-20s | %-20s | %-10d | %-10s | %-20s | %-20s | %-10s | %-18d | %-10s | %-18s | %-15s | %-2s | %-15s |%n",
                                    room.getRoomName(), room.getRoomType(), room.getCapacity(), room.getRoomStatus(),
                                    room.getBuildingLocation(), room.getMaintenanceNotes(), room.isHasProjector() ? "Yes" : "No",
                                    room.getNumOfChairs(), classroomRoom.isWhiteboard() ? "Yes" : "No", "N/A",
                                    "N/A", "N/A", "N/A");
                        } else if (room instanceof RoomType.Smartroom) {
                            RoomType.Smartroom smartRoom = (RoomType.Smartroom) room;
                            System.out.printf("| %-20s | %-20s | %-10d | %-10s | %-20s | %-20s | %-10s | %-18d | %-10s | %-18s | %-15s | %-2s | %-15s |%n",
                                    room.getRoomName(), room.getRoomType(), room.getCapacity(), room.getRoomStatus(),
                                    room.getBuildingLocation(), room.getMaintenanceNotes(), room.isHasProjector() ? "Yes" : "No",
                                    room.getNumOfChairs(), "N/A", "N/A",
                                    "N/A", smartRoom.isTv() ? "Yes" : "No", smartRoom.isInternetAccess()? "Yes" : "No");
                        } else {
                            // Handle other room types if needed
                            System.out.println("No rooms found.");
                            exit = true;
                        }

                    }

                    System.out.println("---------------------------------------------------------------" +
                            "---------------------------------------------------------------------------" +
                            "-------------------------------------------------------------------------" +
                            "--------------");
                } else {
                    System.out.println("No rooms found matching the criteria.");
                }


            } catch (SQLException e) {
                System.err.println("Error during room search: " + e.getMessage());

            }

            promptForAnotherSearch(scanner);
        }
    }
    private static void promptForAnotherSearch(Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {
            System.out.print("\nDo you want to search for another room? (yes/no): ");
            String choice = scanner.nextLine().toLowerCase().trim();
            if (choice.equals("yes")) {
                validInput = true;
            } else if (choice.equals("no")) {
                System.out.println("\nThank you for using the room record creation system. Goodbye!");
                validInput = true;
                exit = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    private static List<? extends Room> searchByRoomName(Scanner scanner) throws SQLException {
        System.out.print("\nEnter room name:");
        String roomName = scanner.nextLine();
        return SelectFromDB.getRoomsByName(roomName);
    }

    private static List<? extends Room> searchByRoomType(Scanner scanner) throws SQLException {
        List<? extends Room> foundRooms = null;
        boolean exit = false;
        while (!exit) {
            System.out.println("--------------------");
            System.out.println("Room types to search:");
            System.out.println("--------------------");
            System.out.println("C - Classroom");
            System.out.println("CL- CompLab");
            System.out.println("L- Library");
            System.out.println("S- Smart room");
            System.out.print("\n Enter your choice:");
            String roomType = scanner.nextLine();

            switch (roomType.toUpperCase()) {
                case "C":
                    foundRooms = SelectFromDB.getRoomsByType("Classroom");
                    exit = true;
                    break;
                case "CL":
                    foundRooms = SelectFromDB.getCompLaboratoriesByType("CompLaboratory");
                    exit = true;
                    break;
                case "L":
                    foundRooms = SelectFromDB.getLibrariesByType("Library");
                    exit = true;
                    break;
                case "S":
                    foundRooms = SelectFromDB.getSmartroomsByType("Smart Room");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid room type. Please try again.");
            }
        }
        return foundRooms;
    }

    private static List<? extends Room> searchByEquipment(Scanner scanner) throws SQLException {
        List<? extends Room> foundRooms = null;
        boolean exit = false;
        while (!exit) {
            System.out.println("----------------------");
            System.out.println("Equipments to search: ");
            System.out.println("----------------------");
            System.out.println("P - Projector");
            System.out.println("C - Chairs");
            System.out.println("W - Whiteboard");
            System.out.println("M - Computers");
            System.out.println("D - Desks");
            System.out.println("T - TV");
            System.out.println("I - Internet");
            System.out.print("\n Enter your choice: ");
            String equipment = scanner.nextLine().toLowerCase().trim();

            switch (equipment) {
                case "p":
                    foundRooms = SelectFromDB.getRoomsWithProjector(true);
                    exit = true;
                    break;
                case "c":
                    foundRooms = SelectFromDB.getRoomsWithChairs();
                    exit = true;
                    break;
                case "w":
                    foundRooms = SelectFromDB.getRoomsByWhiteboard(true);
                    exit = true;
                    break;
                case "m":
                    foundRooms = SelectFromDB.getRoomsByComputers();
                    exit = true;
                    break;
                case "d":
                    foundRooms = SelectFromDB.getRoomsByDesks();
                    exit = true;
                    break;
                case "t":
                    foundRooms = SelectFromDB.getRoomsByTV(true);
                    exit = true;
                    break;
                case "i":
                    foundRooms = SelectFromDB.getRoomsByInternetAccess(true);
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid equipment choice. Please try again.");
            }
        }
        return foundRooms;
    }
}


