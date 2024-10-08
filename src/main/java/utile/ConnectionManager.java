package utile;

import exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String USERNAME_KEY ="db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String URL_KEY = "db.url";

    static {
        loadDriver();
    }

    private ConnectionManager(){}

    public static Connection open() throws DatabaseConnectionException {
        try {
            return DriverManager.getConnection(
                    Util.get(URL_KEY),
                    Util.get(USERNAME_KEY),
                    Util.get(PASSWORD_KEY)
            );
        } catch (SQLException e){
            throw new DatabaseConnectionException("Не удалось подключиться к базе данных!", e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

}
