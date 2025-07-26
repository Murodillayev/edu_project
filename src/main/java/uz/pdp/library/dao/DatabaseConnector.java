package uz.pdp.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String url = "jdbc:postgresql://195.201.128.214:5432/library";
    private static final String user = "postgres";
    private static final String password = "8E2rh3N91wuR";

    public static Connection getConnection() {
        try {

            DriverManager.registerDriver(new org.postgresql.Driver());

            return DriverManager.getConnection(
                    url,
                    user,
                    password
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
