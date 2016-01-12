package conccurency.epxlame5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by evgen on 12.01.16.
 */
public class JdbcMain {

    private final static String SQL = "CREATE TABLE user(id_user INTEGER(10) NOT NULL, first_name VARCHAR(10) NOT NULL, PRIMARY KEY (id_user));";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        /**
         *
         */
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
