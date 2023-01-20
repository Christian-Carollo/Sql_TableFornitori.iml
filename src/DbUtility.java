import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Fornitore {
        public static void main(String[] args)throws SQLException {

            Connection connection = null;

            try {
                // database parametri

                String url = "jdbc:mysql://localhost:3306/newdb";
                String user = "root";
                String password = "Fumettistica11!";

                // crea connessione nel database

                connection = DriverManager.getConnection(url, user, password); // per creare una connessione al database

                Statement statement = connection.createStatement();

            }
        }
