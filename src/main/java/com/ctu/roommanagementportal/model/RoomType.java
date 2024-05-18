//Package declaration specifying the location of this code within the project structure

package com.ctu.roommanagementportal.model;

import com.ctu.roommanagementportal.abstraction.Room;

public class RoomType {

    /**
     * Class representing a classroom inheriting from Room
     * This class extends the functionality of the Room class to specifically represent a classroom.
     * It adds a property for the number of chairs in the classroom.
     */
   public static class Classroom extends Room {

        /**
         *  **Number of chairs in the classroom**
         */
       private int chairs;

        /**
         * Constructor for the Classroom class
         * @param roomNumber String representing the unique identifier for the classroom
         * @param capacity int representing the maximum number of occupants in the classroom
         * @param roomStatus boolean indicating if the classroom is available (true) or occupied (false)
         * @param buildingLocation String representing the building where the classroom is located
         * @param maintenanceNotes String containing any notes about maintenance required for the classroom
         * @param hasProjector boolean indicating if the classroom has a projector
         * @param numOfChairs int representing the number of chairs in the classroom
         */

        public Classroom(String roomNumber, int capacity, boolean roomStatus, String buildingLocation, String maintenanceNotes, boolean hasProjector, int numOfChairs) {
            super(roomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, "Classroom", hasProjector); // Inheritance
            this.chairs = numOfChairs;
        }

        /**
         * Getter method to retrieve the number of chairs in the classroom
         * @return int representing the number of chairs
         * This getter might be useful in the future but isn't used currently
         */
        public int getChairs() {
            return chairs;
        }

        /**
         * Setter method to update the number of chairs in  the classroom
         * @param chairs int representing the new number of chairs
         * This setter might be useful in the future but isn't used currently
         */
        public void setChairs(int chairs) {
            this.chairs = chairs;
        }

        /**
         * Override of the displayRoomInfo method inherited from the Room class
         * This method provides specific information about the classroom, including the number of chairs
         */
        @Override
        public void displayRoomInfo() {
            System.out.println("Room Type: " + roomType);
            System.out.println("Classroom " + roomNumber);
            System.out.println("Building: " + buildingLocation);
            System.out.println("Capacity: " + capacity);
            System.out.println("Status (Available/Occupied): " + roomStatus);
            System.out.println("Number of chairs: " + chairs);
            System.out.println("Has projector: " + (hasProjector ? "Yes" : "No"));
            System.out.println("Maintenance Notes: " + maintenanceNotes);
        }
    }

    /**Class representing a laboratory inheriting from Room
     * Similar structure and comments applied to the CompLaboratory, Library, and Smart room classes
     */
    public static class CompLaboratory extends Room {

        /**
         * Stores the number of desk present in the library
         */
        private int numOfComputers;

        /**
         * Stores the number of desks present in the computer laboratory
         *
         * @param roomNumber The room number of the  computer laboratory
         * @param capacity The maximum capacity of the  computer laboratory (number of people).
         * @param roomStatus True if the  computer laboratory is available, false otherwise
         * @param buildingLocation The building where the  computer laboratory is located with the library.
         * @param maintenanceNotes Any maintenance notes associated with the  computer laboratory.
         * @param hasProjector True if the  computer laboratory has projector, False otherwise
         * @param numOfComputers The number of computer in the  computer laboratory.
         */
        public CompLaboratory(String roomNumber, int capacity, boolean roomStatus, String buildingLocation, String maintenanceNotes, boolean hasProjector, int numOfComputers) {
            super(roomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, "CompLaboratory", hasProjector);
            this.numOfComputers = numOfComputers;
        }

        /**
         * Getter method to retrieve the number of computer in the laboratory
         * This getter might be useful in the future but isn't used currently
         * @return The number of computer in the laboratory
         */
        public int getNumOfComputers() {
            return numOfComputers;
        }

        /**
         * Setter method to set the number of computer in the laboratory
         * This setter might be useful in the future but isn't used currently
         * @param numOfComputers The new number of computer for the laboratory .
         */
        public void setNumOfComputers(int numOfComputers) {
            this.numOfComputers = numOfComputers;
        }

        /**
         * Overrides the displayRoomInfo method from the Room class
         * This method displays information specific to computer laboratories, including the number of computers.
         */
        @Override
        public void displayRoomInfo() {
            System.out.println("Room Type: " + roomType);
            System.out.println("Classroom " + roomNumber);
            System.out.println("Building: " + buildingLocation);
            System.out.println("Capacity: " + capacity);
            System.out.println("Status (Available/Occupied): " + roomStatus);
            System.out.println("Number of computers: " + numOfComputers);
            System.out.println("Has projector: " + (hasProjector ? "Yes" : "No"));
            System.out.println("Maintenance Notes: " + maintenanceNotes);
        }
    }

    //Class representing a laboratory inheriting from Room
    public static class Library extends Room {

        /**
         * Stores the number of desks present in the library.
         */
        private int numOfDesks;

        /**
         * Constructor for Library.
         * @param roomNumber The room number of the library
         * @param capacity The maximum capacity of the  library (number of people).
         * @param roomStatus True if the  library is available, false otherwise
         * @param buildingLocation The building where the  library is located with the library.
         * @param maintenanceNotes Any maintenance notes associated with the  library.
         * @param hasProjector True if the library has projector, False otherwise
         * @param numOfDesks The number of computer in the  library.
         */
        public Library(String roomNumber, int capacity, boolean roomStatus, String buildingLocation, String maintenanceNotes, boolean hasProjector, int numOfDesks) {
            super(roomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, "Library", hasProjector); // Corrected parameter
            this.numOfDesks = numOfDesks;
        }

        /**
         * Getter method to retrieve the number of desks in the library
         * <br>
         * **Note:** The method name clearly indicates its purpose, so a detailed Javadoc comment might be unnecessary.
         * @return The number of desks in the library.
         * This getter might be useful in the future but isn't used currently
         */
        public int getNumOfComputers() {
            return numOfDesks;
        }

        /**
         * Setter method to set the number of desks in the library.
         * <br>
         * **Note: ** The method name clearly indicates its purpose, so a detailed Javadoc comment might be unnecessary.
         * @param numOfDesks The number of desks for the library.
         * This setter might be useful in the future but isn't used currently
         */
        public void setNumOfDesks(int numOfDesks) {
            this.numOfDesks = numOfDesks;
        }

        /**
         * Override the displayRoomInfo method from the Room class
         * This method display information specific to libraries , including the number of desks.
         */
        @Override
        public void displayRoomInfo() {
            System.out.println("Room Type: " + roomType);
            System.out.println("Classroom " + roomNumber);
            System.out.println("Building: " + buildingLocation);
            System.out.println("Capacity: " + capacity);
            System.out.println("Status (Available/Occupied): " + roomStatus);
            System.out.println("Number of desks: " + numOfDesks);
            System.out.println("Has projector: " + (hasProjector ? "Yes" : "No"));
            System.out.println("Maintenance Notes: " + maintenanceNotes);
        }
    }

    /**Class representing a laboratory inheriting from Room
     * Smart room typically have additional features like TVs and internet access.
     */
    public static class Smartroom extends Room {

        /**
         * Indicates whether the Smart room has a TV.
         */
        private boolean tv;

        /**
         * Indicates whether the Smart room has Internet access
         */
        private boolean internetAccess;

        /**
         * Constructor for Smart room.
         * @param roomNumber The room number of the  Smart room
         * @param capacity The maximum capacity of the Smart room (number of people).
         * @param roomStatus True if the  Smart room is available, false otherwise
         * @param buildingLocation The building where the Smart room is located with the located.
         * @param maintenanceNotes Any maintenance notes associated with the  Smart room.
         * @param hasProjector True if the Smart room has projector, False otherwise
         * @param tv True if the Smart room has TV , False otherwise
         * @param internetAccess True if the Smart room has internet access, False otherwise
         */
        public Smartroom(String roomNumber, int capacity, boolean roomStatus, String buildingLocation, String maintenanceNotes, boolean hasProjector, boolean tv, boolean internetAccess) {
            super(roomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, "Smart room", hasProjector); // Inheritance
            this.tv = tv;
            this.internetAccess = internetAccess;
        }


        /**
         * Getter method to check if the smart room has a TV.
         * This getter might be useful in the future but isn't used currently
         * @return True if the smart room has a TV , False otherwise.
         */
        public boolean isTv() {
            return tv;
        }

        /**
         * Setter method to set the Tv availability for the smart room.
         * This setter might be useful in the future but isn't used currently
         * @param tv True to indicate the smart room has TV, False otherwise
         */
        public void setTv(boolean tv) {
            this.tv = tv;
        }

        /**
         * Getter method to check if the smart room has  Internet access.
         * This getter might be useful in the future but isn't used currently
         * @return True if the smart room has Internet access, False otherwise.
         */
        public boolean isInternetAccess() {
            return internetAccess;
        }

        /**
         * Setter method to set the Internet access availability for the smart room.
         * This setter might be useful in the future but isn't used currently
         * @param internetAccess True to indicate the smart room has Internet access, False otherwise
         */
        public void setInternetAccess(boolean internetAccess) {
            this.internetAccess = internetAccess;
        }

        /**
         * Overrides the displayRoom method from the Room class.
         * This method displays information specific to smart room
         */
        @Override
        public void displayRoomInfo() {
            System.out.println("Room Type: " + roomType);
            System.out.println("Classroom " + roomNumber);
            System.out.println("Building: " + buildingLocation);
            System.out.println("Capacity: " + capacity);
            System.out.println("Status (Available/Occupied): " + roomStatus);
            System.out.println("Has tv: " + (tv ? "Yes" : "No"));
            System.out.println("Has Internet access: " + (internetAccess ? "Yes" : "No"));
            System.out.println("Has projector: " + (hasProjector ? "Yes" : "No"));
            System.out.println("Maintenance Notes: " + maintenanceNotes);
        }
    }
}