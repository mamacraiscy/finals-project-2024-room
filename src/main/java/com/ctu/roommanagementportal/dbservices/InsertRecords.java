package com.ctu.roommanagementportal.dbservices;

import dao.models.AdminInfo;
import dao.models.UserInfo;
import services.reservation.CreateObjects;
import com.ctu.roommanagementportal.abstraction.Room;

import java.sql.*;

/**
 * Insert PrepareStatement JDBC Example
 *
 * @author Ramesh Fadatare
 *
 */
public class InsertRecords {
    private static final String INSERT_USERS_SQL = "INSERT INTO USERINFO (firstName, middleName, lastName, birthDate, homeAddress, nationality, gender, roleAtSchool) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ROOMS_SQL = "INSERT INTO ROOMRECORD (roomNumber, capacity, roomStatus, buildingLocation, maintenanceNotes, roomType, hasProjector) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_BOOKING_SQL = "INSERT INTO BookingDetails (userName, email, date, time, room, bookingID)" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String INSERT_ADMIN_SQL = "INSERT INTO AdminInfo (firstName, middleName, lastName, birthDate, street, barangay, municipality, city, zipCode, nationality, gender, roleAtSchool) " +
            "VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?)";


    public InsertRecords(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JBCD MySQL Driver", e);
        }
    }

    public void insertUserRecord(UserInfo userInfo) throws SQLException {
        System.out.println("Inserting user data to DB");


        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "admin123$");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//            // Set the values
            preparedStatement.setString(1, userInfo.getFirstName());
            preparedStatement.setString(2, userInfo.getMiddleName());
            preparedStatement.setString(3, userInfo.getLastName());
            preparedStatement.setDate(4, Date.valueOf(userInfo.getBirthDate()));
            preparedStatement.setString(5, userInfo.getHomeAddress());
            preparedStatement.setString(6, userInfo.getNationality());
            preparedStatement.setString(7, userInfo.getGender());
            preparedStatement.setString(8, userInfo.getRoleAtSchool());

            // Execute the statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void insertRoomRecord(Room roomRecord) throws SQLException {
        System.out.println("Inserting room data to DB");

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "admin123$");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROOMS_SQL)) {

//            // Set the values
            preparedStatement.setString(1, roomRecord.getRoomNumber());
            preparedStatement.setInt(2, roomRecord.getCapacity());
            preparedStatement.setBoolean(3, roomRecord.getRoomStatus());
            preparedStatement.setString(4, roomRecord.getBuildingLocation());
            preparedStatement.setString(5, roomRecord.getMaintenanceNotes());
            preparedStatement.setString(6, roomRecord.getRoomType());
            preparedStatement.setBoolean(7, roomRecord.isHasProjector());

            // Execute the statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new room record was inserted successfully!");
            }

        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void insertBookingDetails(CreateObjects bookingRequest) throws SQLException {

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "admin123$");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING_SQL)) {

            preparedStatement.setString(1, bookingRequest.getUserName());
            preparedStatement.setString(2, bookingRequest.getEmail());
            preparedStatement.setDate(3, bookingRequest.getDate());
            preparedStatement.setTime(4, bookingRequest.getTime());
            preparedStatement.setString(5, bookingRequest.getRoom());
            preparedStatement.setInt(6, bookingRequest.getBookingID());

            // Execute the statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new room record was inserted successfully!");
            }

        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void insertAdminRecord(AdminInfo adminInfo) throws SQLException {
        System.out.println("Inserting admin data to DB");

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "admin123$");


             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
//            // Set the values
            preparedStatement.setString(1, adminInfo.getFirstName());
            preparedStatement.setString(2, adminInfo.getMiddleName());
            preparedStatement.setString(3, adminInfo.getLastName());
            preparedStatement.setDate(4, Date.valueOf(adminInfo.getBirthDate()));
            preparedStatement.setString(5, adminInfo.getStreet());
            preparedStatement.setString(6, adminInfo.getBarangay());
            preparedStatement.setString(7, adminInfo.getMunicipality());
            preparedStatement.setString(8, adminInfo.getCity());
            preparedStatement.setInt(9, adminInfo.getZIPcode());
            preparedStatement.setString(10, adminInfo.getNationality());
            preparedStatement.setString(11, adminInfo.getGender());
            preparedStatement.setString(12, adminInfo.getroleAtSchool());

            // Execute the statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
