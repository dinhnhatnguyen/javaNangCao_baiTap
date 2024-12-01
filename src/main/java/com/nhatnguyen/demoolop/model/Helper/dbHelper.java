package com.nhatnguyen.demoolop.model.Helper;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public class dbHelper {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QlSach;trustServerCertificate=true";
    private static final String USER = "SA";
    private static final String PASSWORD = "YourStrong@Passw0rd";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Kết nối thất bại.");
            throw e;
        }
    }
}
