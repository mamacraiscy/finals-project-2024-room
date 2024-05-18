package com.ctu.roommanagementportal.dbservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create Statement JDBC Example
 * @author Ramesh Fadatare
 *
 */
public class CreateTables {

    private static final String createUserInfoTbl = "CREATE TABLE userinfo (" +
            "firstName VARCHAR(255)," +
            "middleName VARCHAR(255)," +
            "lastName VARCHAR(255)," +
            "birthDate DATE," +
            "homeAddress VARCHAR(255)," +
            "nationality VARCHAR(255)," +
            "gender ENUM('Male', 'Female', 'Other')," +
            "roleAtSchool VARCHAR(255)" +
            ");";

    private static final String createRoomRecordTbl = "CREATE TABLE RoomRecord (\n" +
            "    roomNumber VARCHAR(255),\n" +
            "    capacity INT,\n" +
            "    roomStatus BOOLEAN,\n" +
            "    buildingLocation VARCHAR(255),\n" +
            "    maintenanceNotes TEXT,\n" +
            "    roomType VARCHAR(255),\n" +
            "    hasProjector BOOLEAN\n" +
            ");";

    private static final String createBookingDetailsTbl = "CREATE TABLE BookingDetails (\n" +
            "    userName VARCHAR(255),\n" +
            "    email VARCHAR(255),\n" +
            "    date DATE,\n" +
            "    time TIME,\n" +
            "    room VARCHAR(255),\n" +
            "    bookingID INT\n" +
            ");";

    private static final String createAdminInfoTbl = "CREATE TABLE AdminInfo (\n" +
            "    firstName VARCHAR(255),\n" +
            "    middleName VARCHAR(255),\n" +
            "    lastName VARCHAR(255),\n" +
            "    birthDate DATE,\n" +
            "    street VARCHAR(255),\n" +
            "    barangay VARCHAR(255),\n" +
            "    municipality VARCHAR(255),\n" +
            "    city VARCHAR(255),\n" +
            "    zipCode INT,\n" +
            "    nationality VARCHAR(255),\n" +
            "    gender VARCHAR(255),\n" +
            "    roleAtSchool VARCHAR(255)\n" +
            ");";

    public CreateTables(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            createTable();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JBCD MySQL Driver", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable() throws SQLException {

        System.out.println("Establishing connection to DB");

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "admin123$");

             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            System.out.println("Creating UserInfo Table in DB");
            statement.execute(createUserInfoTbl);

            System.out.println("Creating RoomRecord Table in DB");
            statement.execute(createRoomRecordTbl);

            System.out.println("Creating BookingDetails Table in DB");
            statement.execute(createBookingDetailsTbl);

            System.out.println("Creating AdminInfo Table in DB");
            statement.execute(createAdminInfoTbl);

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