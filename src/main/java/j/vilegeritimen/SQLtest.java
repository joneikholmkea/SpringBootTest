package j.vilegeritimen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLtest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String password = "rootroot";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Deaktiver autocommit, så vi manuelt kan styre transaktionen
            connection.setAutoCommit(false);

            try {
                // Træk penge fra konto 1
                try (PreparedStatement withdrawStmt = connection.prepareStatement("UPDATE kunder SET saldo = saldo - ? WHERE konto_id = ?")) {
                    withdrawStmt.setDouble(1, 100);
                    withdrawStmt.setInt(2, 1);
                    withdrawStmt.executeUpdate();
                }

                // Overfør penge til konto 2
                try (PreparedStatement depositStmt = connection.prepareStatement("UPDATE kunder SET saldo = saldo + ? WHERE konto_id = ?")) {
                    depositStmt.setDouble(1, 100);
                    depositStmt.setInt(2, 2);
                    depositStmt.executeUpdate();
                }

                // Registrer transaktionen i transaktioner-tabellen
                try (PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO transaktioner (fra_konto, til_konto, beloeb, transaktionstid) VALUES (?, ?, ?, NOW())")) {
                    insertStmt.setInt(1, 1);
                    insertStmt.setInt(2, 2);
                    insertStmt.setDouble(3, 100);
                    insertStmt.executeUpdate();
                }

                // Hvis alt går godt, så fuldfør transaktionen
                connection.commit();
                System.out.println("Transaktionen blev gennemført med succes.");

            } catch (SQLException e) {
                // Hvis der opstår en fejl, så rul transaktionen tilbage
                connection.rollback();
                System.err.println("Der opstod en fejl under transaktionen, og den blev rullet tilbage: " + e.getMessage());
            } finally {
                // Aktiver autocommit igen
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.err.println("Kunne ikke oprette forbindelse til databasen: " + e.getMessage());
        }
    }
}