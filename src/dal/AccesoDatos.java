package dal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ent.Compra;
import ent.Game;
import ent.Player;

public class AccesoDatos {
    /**
     * Variable para usar la base de datos
     */
    private static String use = "USE ad2425_asuarez;";

    /**
     * Metodo para crear la tabla players
     */
    public static void crearTablaPlayers() {
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
     * 
     * @return List de jugadores
     */
    private static List<Player> getPlayers() {
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
        List<Player> players = getPlayers();
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
                if (ps != null) {
                    ps.close();
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
     * Metodo para crear la tabla games
     */
    public static void crearTablaGames() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(use);
            String sql = "CREATE TABLE Games(" +
                    "idGame int Primary Key auto_increment," +
                    "nombre varchar(45)," +
                    "tiempoJugado TIME);";
            stmt.execute(sql);
            System.out.println("Tabla GAMES  creada");
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
     * Metodo para obtener los juegos del fichero
     * 
     * @return List de juegos
     */
    private static List<Game> getGames() {
        List<Game> games = new ArrayList<>();

        // Leemos el fichero data_games.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("src/files/data_games.txt"))) {
            String linea;

            // Cogemos la linea
            while ((linea = reader.readLine()) != null) {
                // Dividimos la linea
                String[] data = linea.split(",");

                // Cogemos los datos
                int id = Integer.parseInt(data[0]);
                String nombre = data[1];
                String tiempoJugado = data[2];

                // Creamos el game
                Game game = new Game(id, nombre, tiempoJugado);

                // Lo anadimos a la lista
                games.add(game);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return games;
    }

    /**
     * Metodo para insertar los juegos en la base de datos
     */
    public static void insertarGames() {
        List<Game> games = getGames();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(use);

            // Preparamos la consulta
            String sql = "INSERT INTO Games (nombre, tiempoJugado) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);

            // Recorremos los juegos
            for (Game game : games) {
                // Insertamos los juegos
                ps.setString(1, game.getNombre());

                String tiempoJugado = game.getTiempoJugado();

                // Comprobamos que el formato de tiempo jugado sea HH:MM:SS
                if (!tiempoJugado.matches("\\d{2}:\\d{2}:\\d{2}")) {
                    // Si no es así, le añadimos segundos
                    tiempoJugado += ":00";
                }

                Time time = Time.valueOf(tiempoJugado);

                ps.setTime(2, time);
                ps.executeUpdate();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

                if (ps != null) {
                    ps.close();
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
     * Metodo para crear la tabla compras
     */
    public static void crearTablaCompras() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(use);
            String sql = "CREATE TABLE Compras(" +
                    "idCompra int Primary Key auto_increment," +
                    "idPlayer int," +
                    "idGame int," +
                    "cosa Varchar(25)," +
                    "precio decimal(6,2)," +
                    "fechaCompra DATE," +
                    "FOREIGN KEY (idPlayer) REFERENCES Players(idPlayer), " +
                    "FOREIGN KEY (idGame) REFERENCES Games(idGame)" +
                    ");";
            stmt.execute(sql);
            System.out.println("Tabla Compras creada");
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
     * Metodo para obtener las compras del fichero
     * 
     * @return List de compras
     */
    public static List<Compra> getCompras() {
        List<Compra> compras = new ArrayList<>();

        // Leemos el fichero data_games.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("src/files/data_compras.txt"))) {
            String linea;

            // Cogemos la linea
            while ((linea = reader.readLine()) != null) {
                // Dividimos la linea
                String[] data = linea.split(",");

                // Cogemos los datos
                int id = Integer.parseInt(data[0]);
                int idPlayer = Integer.parseInt(data[1]);
                int idGame = Integer.parseInt(data[2]);
                String cosa = data[3];
                String precio = data[4];
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                java.util.Date fecha = new Date();

                try {
                    // Convertir el string a un objeto Date
                    fecha = sdf.parse(data[5]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Creamos las compra
                Compra compra = new Compra(id, idPlayer, idGame, cosa, Double.parseDouble(precio), fecha);

                // Lo anadimos a la lista
                compras.add(compra);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return compras;
    }

}
