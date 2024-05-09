package com.ctu.roommanagementportal.infrastracture;

import com.ctu.roommanagementportal.abstraction.Room;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object (DAO) class for managing room information in a database.
 * This class provides  methods, for interacting with the "rooms table".
 */

public class RoomDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/room_management"; // Replace with your database URL
    private static final String DB_USER = "room"; // Replace with your database user
    private static final String DB_PASS = "9703"; // Replace with your database password


    /**
     * Establishes a connection to the database.
     *
     * @return A Connection object representing the connection to the database.
     * @throws SQLException if an error occurs while establishing the connection
     */

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    /**
     * Verifies the structure of the 'rooms' table exists in the database.
     * @throws SQLException if an error occurs while accessing database metadata or the table is not found.
     */

    public void checkRoomTableStructure() throws SQLException {
        try (Connection conn = getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "rooms", new String[]{"TABLE"});

            if (!resultSet.next()) {
                throw new SQLException("Table 'rooms' does not exist.");
            } else {
                System.out.println("Table 'rooms' exists.");
            }
        }
    }

    /**
     * Inserts a new room record into the database.
     *
     * @param room The Room containing the information for the new room.
     * @throws SQLException if an error occurs while executing the insert statement.
     */

    public void saveRoom(Room room) throws SQLException {
        String sql = "INSERT INTO rooms (room_number, capacity, room_status, building_location, maintenance_notes, room_type, has_projector) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomNumber());
            stmt.setInt(2, room.getCapacity());
            stmt.setBoolean(3, room.getRoomStatus());
            stmt.setString(4, room.getBuildingLocation());
            stmt.setString(5, room.getMaintenanceNotes());
            stmt.setString(6, room.getRoomType());
            stmt.setBoolean(7, room.isHasProjector());

            stmt.executeUpdate(); // Executes the SQL statement
        } catch (SQLException e) {
            throw new SQLException("Failed to save room record.", e);
        }
    }

    /**
     *  Searches for rooms based on room type and/or room number (optional criteria).
     *
     * @param roomType The type opf room search for (can be empty string for broader search).
     * @param  roomNumber The room number to search for(can be empty string for broader search).
     * @return A list  of Room object containing the matching rooms.
     * @throws SQLException if an error occurs while executing the search statement.
     */

    public List<Room> searchRooms(String roomType, String roomNumber) throws SQLException {
        List<Room> roomList = new ArrayList<>();

        String sql = "SELECT * FROM rooms WHERE room_type = ? OR room_number = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomType);
            stmt.setString(2, roomNumber);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Retrieve room information from the result set
                String retrievedRoomNumber = rs.getString("room_number");
                int capacity = rs.getInt("capacity");
                boolean roomStatus = rs.getBoolean("room_status");
                String buildingLocation = rs.getString("building_location");
                String maintenanceNotes = rs.getString("maintenance_notes");
                String retrievedRoomType = rs.getString("room_type");
                boolean hasProjector = rs.getBoolean("has_projector");

                Room room = new Room(retrievedRoomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, retrievedRoomType, hasProjector) {
                    @Override
                    public void displayRoomInfo() {

                    }
                };
                roomList.add(room);
            }
        } catch (SQLException e) {
            throw new SQLException("Error searching for rooms.", e);
        }

        return roomList;
    }

    // Updates room records based on a map of updates
    public void updateRoom(String roomNumber, Map<String, Object> updates) throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE rooms SET ");
        boolean first = true;

        // Build the dynamic SQL based on the updates map
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            if (!first) {
                sqlBuilder.append(", ");
            }
            sqlBuilder.append(entry.getKey()).append(" = ?");
            first = false;
        }

        sqlBuilder.append(" WHERE room_number = ?"); // Update condition

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlBuilder.toString())) {
            int index = 1;

            // Set the update values in the PreparedStatement
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                stmt.setObject(index++, entry.getValue());
            }

            stmt.setString(index, roomNumber); // Room number for the WHERE clause
            int rowsUpdated = stmt.executeUpdate(); // Execute the update

            if (rowsUpdated == 0) {
                throw new SQLException("Room record with room number " + roomNumber + " not found.");
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to update room record.", e);
        }
    }
}



