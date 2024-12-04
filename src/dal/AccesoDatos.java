package dal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ent.Player;

public class AccesoDatos {
    /**
     * Variable para usar la base de datos
     */
    private static String use = "USE ad2425_asuarez;";

    /**
     * Metodo para crear la tabla players
     */
    public static void crearTablaPlayer() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(use);
            String sql = "CREATE TABLE Players(" +
                "idPlayer int Primary Key auto_increment," +
                "nick varchar(45)," +
                "password varchar(128)," +
                "email varchar(100));";
            stmt.execute(sql);
            System.out.println("Tabla players creada");
        } catch (SQLException se) {
            // Gestionamos los posibles errores que puedan surgir durante la ejecución de la
            // inserción
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
    }

    /**
     * Metodo para obtener los jugadores del fichero
     * @return List de jugadores
     */
    private List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        
        // Leemos el fichero data_players.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("src/files/data_players.txt"))) {
            String linea;

            // Cogemos la linea
            while ((linea = reader.readLine()) != null) {
                // Dividimos la linea
                String[] data = linea.split(",");

                // Cogemos los datos
                int id = Integer.parseInt(data[0]);
                String nick = data[1];
                String password = data[2];
                String email = data[3];

                // Creamos el jugador
                Player player = new Player(id, nick, password, email);
                
                // Lo anadimos a la lista
                players.add(player);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return players;
    }

    /**
     * Metodo para insertar los jugadores en la base de datos
     */
    public static void insertarPlayers() {
        List<Player> players = new AccesoDatos().getPlayers();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(use);

            // Preparamos la consulta
            String sql = "INSERT INTO Players (nick, password, email) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);

            // Recorremos los jugadores
            for (Player player : players) {
                // Insertamos los jugadores
                ps.setString(1, player.getNick());
                ps.setString(2, player.getPassword());
                ps.setString(3, player.getEmail());
                ps.executeUpdate();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
    }

}
