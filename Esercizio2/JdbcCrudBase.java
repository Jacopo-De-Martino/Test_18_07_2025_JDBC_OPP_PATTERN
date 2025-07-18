package Esercizio2;

import java.sql.*;

public class JdbcCrudBase {

    static final String URL = "jdbc:mysql://localhost:3306/smile";
    static final String USER = "";
    static final String PASSWORD = "";

    static class DbManager { // DbManager classe annidata Singleton Instanza univoca che fornisce la
                             // connessione al db
        private static DbManager instance = null;

        private DbManager() {
            // Class.forName("com.mysql.cj.jdbc.Driver");
        }

        public static DbManager getInstance() {
            if (instance == null) {
                instance = new DbManager();
            }
            return instance;
        }

        public Connection getConnection() throws SQLException { // lancio automatico eccezzioni / se non ci sono
                                                                // eccezzioni restituisce la connession
            System.out.println("Tentativo di connessione al database...");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione stabilita con successo!");
            return conn;
        }
    }

    public static void main(String[] args) {
        DbManager dbManager = DbManager.getInstance();// Creazione istanza dbManager

        createTable(dbManager); // Metodo statico per creare la tabella utenti passo la connessione al db
                                // tramite istanza dbManager

        System.out.println("\n--- Inserimento Utenti ---");
        insertUtente(dbManager, "Alice", "alice@example.com");// Metodo inserimento utenti
        insertUtente(dbManager, "Bob", "bob@example.com");

        System.out.println("\n--- Lettura Utenti ---");
        readUtenti(dbManager); // metodo lettura utenti
    }

    public static void createTable(DbManager dbManager) {
        String sql = "CREATE TABLE IF NOT EXISTS utenti (" + // query per creare la tabella utenti
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(100) ," +
                "email VARCHAR(100) " +
                ")";
        try (Connection conn = dbManager.getConnection(); // tentativo di connessione con dbManager (Passato nel metodo
                                                          // come parametro reale)
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);// Esecuzione della query di creazio
            System.out.println("Tabella 'utenti' creata o già esistente.");
        } catch (SQLException e) {
            System.err.println("Errore durante la creazione della tabella: " + e.getMessage()); // Stampa messaggio
                                                                                                // errore
            e.printStackTrace();
        }
    }

    public static void insertUtente(DbManager dbManager, String nome, String email) {
        String sql = "INSERT INTO utenti (nome, email) VALUES (?, ?)"; // Query "Dinamica"
        try (Connection conn = dbManager.getConnection(); // try-whit-resources
                PreparedStatement pstmt = conn.prepareStatement(sql)) { // utilizzo PreparedStatment per evitare SQL
                                                                        // injection

            pstmt.setString(1, nome);
            pstmt.setString(2, email);

            int rowsAffected = pstmt.executeUpdate(); // restituizione numero righe inserite, esecuzione query
            System.out.println(rowsAffected + " riga/e inserita/e per l'utente '" + nome + "'.");

        } catch (SQLException e) {
            if (e.getSQLState().startsWith("23")) {
                System.err.println("Attenzione: L'email '" + email + "' esiste già nel database. " + e.getMessage());
            } else {
                System.err.println("Errore durante l'inserimento dell'utente: " + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    // NO TEMPOOOOO
    public static void readUtenti(DbManager dbManager) {
        String sql = "SELECT id, nome, email FROM utenti";
        try (Connection conn = dbManager.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Nessun utente trovato nella tabella 'utenti'.");
                return;
            }

            System.out.println("--- Lista Completa Utenti ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la lettura degli utenti: " + e.getMessage());
            e.printStackTrace();
        }
    }
}