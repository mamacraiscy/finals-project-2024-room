package com.ctu.roommanagementportal.dbservices;

import com.ctu.roommanagementportal.abstraction.Room;
import com.ctu.roommanagementportal.model.RoomType;

import java.sql.*;

public class InsertRecords {

    private static final String INSERT_ROOMS_SQL = "INSERT INTO roomrecord (roomName, capacity, roomStatus, buildingLocation, maintenanceNotes, roomType, hasProjector, numOfChairs, whiteBoard, numOfComputers, numOfDesks, hasTV, hasInternetAccess) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public InsertRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JDBC MySQL Driver", e);
        }
    }

    public boolean insertRoomRecord(Room roomRecord) {
        System.out.println("Inserting room data to DB");

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "admin123$");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROOMS_SQL)) {

            // Set common attributes
            preparedStatement.setString(1, roomRecord.getRoomName());
            preparedStatement.setInt(2, roomRecord.getCapacity());
            preparedStatement.setBoolean(3, roomRecord.getRoomStatus());
            preparedStatement.setString(4, roomRecord.getBuildingLocation());
            preparedStatement.setString(5, roomRecord.getMaintenanceNotes());

            // Set room type-specific attributes
            setRoomTypeAttributes(preparedStatement, roomRecord);

            // Execute the statement
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            // Log or handle the exception
//            e.printStackTrace();
            return false;
        }
    }

    private void setRoomTypeAttributes(PreparedStatement preparedStatement, Room roomRecord) throws SQLException {
        if (roomRecord instanceof RoomType.Classroom) {
            RoomType.Classroom classroom = (RoomType.Classroom) roomRecord;
            preparedStatement.setString(6, "Classroom");
            preparedStatement.setBoolean(7, classroom.isHasProjector());
            preparedStatement.setInt(8, classroom.getNumOfChairs());
            preparedStatement.setBoolean(9, classroom.isWhiteboard());
            preparedStatement.setNull(10, Types.INTEGER);
            preparedStatement.setNull(11, Types.INTEGER);
            preparedStatement.setNull(12, Types.BOOLEAN);
            preparedStatement.setNull(13, Types.BOOLEAN);
        } else if (roomRecord instanceof RoomType.CompLaboratory) {
            RoomType.CompLaboratory laboratory = (RoomType.CompLaboratory) roomRecord;
            preparedStatement.setString(6, "CompLaboratory");
            preparedStatement.setBoolean(7, laboratory.isHasProjector());
            preparedStatement.setInt(8, laboratory.getNumOfChairs());
            preparedStatement.setNull(9, Types.BOOLEAN);
            preparedStatement.setInt(10, laboratory.getNumOfComputers());
            preparedStatement.setNull(11, Types.INTEGER);
            preparedStatement.setNull(12, Types.BOOLEAN);
            preparedStatement.setNull(13, Types.BOOLEAN);
        } else if (roomRecord instanceof RoomType.Library) {
            RoomType.Library library = (RoomType.Library) roomRecord;
            preparedStatement.setString(6, "Library");
            preparedStatement.setBoolean(7, library.isHasProjector());
            preparedStatement.setInt(8, library.getNumOfChairs());
            preparedStatement.setNull(9, Types.BOOLEAN);
            preparedStatement.setNull(10, Types.INTEGER);
            preparedStatement.setInt(11, library.getNumOfDesks());
            preparedStatement.setNull(12, Types.BOOLEAN);
            preparedStatement.setNull(13, Types.BOOLEAN);
        } else if (roomRecord instanceof RoomType.Smartroom) {
            RoomType.Smartroom smartroom = (RoomType.Smartroom) roomRecord;
            preparedStatement.setString(6, "Smart room");
            preparedStatement.setBoolean(7, smartroom.isHasProjector());
            preparedStatement.setInt(8, smartroom.getNumOfChairs());
            preparedStatement.setNull(9, Types.BOOLEAN);
            preparedStatement.setNull(10, Types.INTEGER);
            preparedStatement.setNull(11, Types.INTEGER);
            preparedStatement.setBoolean(12, smartroom.isTv());
            preparedStatement.setBoolean(13, smartroom.isInternetAccess());
        } else {
            throw new IllegalArgumentException("Invalid room type.");
        }
    }
}
