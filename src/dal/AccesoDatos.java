package dal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import ent.Compra;
import ent.Game;
import ent.Player;

/**
 * Clase AccesoDatos
 */
public class AccesoDatos {
    /**
     * Variable para usar la base de datos
     */
    private static String useDB = "USE ad2425_asuarez;";

    /**
     * Metodo para crear la tabla players
     */
    public static void crearTablaPlayers() {
        if (!AccesoDatos.tablaExiste("Players")) {
            Connection conn = null;
            Statement stmt = null;
            try {
                conn = ConexionDB.connect();
                stmt = conn.createStatement();
                stmt.executeUpdate(useDB);
                String sql = "CREATE TABLE Players(" +
                        "idPlayer int Primary Key auto_increment," +
                        "nick varchar(45)," +
                        "password varchar(128)," +
                        "email varchar(100));";
                stmt.execute(sql);
                System.out.println("Tabla PLAYERS creada");
            } catch (SQLException se) {
                // Gestionamos los posibles errores que puedan surgir durante la ejecución de la
                // inserción
                se.printStackTrace();
            } catch (Exception e) {
                System.out.println("Se ha producido un error creando la tabla Players.");
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
        } else {
            System.err.println("La tabla Players ya existe.");
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
            stmt.executeUpdate(useDB);

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
        if (!AccesoDatos.tablaExiste("Games")) {
            Connection conn = null;
            Statement stmt = null;
            try {
                conn = ConexionDB.connect();
                stmt = conn.createStatement();
                stmt.executeUpdate(useDB);
                String sql = "CREATE TABLE Games(" +
                        "idGame int Primary Key auto_increment," +
                        "nombre varchar(45)," +
                        "tiempoJugado TIME);";
                stmt.execute(sql);
                System.out.println("Tabla GAMES creada");
            } catch (SQLException se) {
                // Gestionamos los posibles errores que puedan surgir durante la ejecución de la
                // inserción
                se.printStackTrace();
            } catch (Exception e) {
                System.out.println("Se ha producido un error creando la tabla Games.");
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
        } else {
            System.err.println("La tabla Games ya existe.");
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
            stmt.executeUpdate(useDB);

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
        // Comprobamos que no exista ya la tabla
        if (!AccesoDatos.tablaExiste("Compras")) {
            // Comprobamos que existan las tablas Players y Games, ya que depende de ellas
            if (AccesoDatos.tablaExiste("Players") && AccesoDatos.tablaExiste("Games")) {
                Connection conn = null;
                Statement stmt = null;
                try {
                    conn = ConexionDB.connect();
                    stmt = conn.createStatement();
                    stmt.executeUpdate(useDB);
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
                    System.out.println("Tabla COMPRAS creada");
                } catch (SQLException se) {
                    // Gestionamos los posibles errores que puedan surgir durante la ejecución de la
                    // inserción
                    se.printStackTrace();
                } catch (Exception e) {
                    System.out.println("Se ha producido un error creando la tabla Compras.");
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
            } else {
                System.err.println("No se pudo crear la tabla porque necesita que la tabla Players y Games existan.");
            }
        } else {
            System.err.println("La tabla Compras ya existe.");
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
                String fecha = data[5];

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

    /**
     * Metodo para insertar las compras en la base de datos
     */
    public static void insertarCompras() {
        List<Compra> compras = getCompras();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);

            // Preparamos la consulta
            String sql = "INSERT INTO Compras (idPlayer, idGame, cosa, precio, fechaCompra) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);

            // Recorremos las compras
            for (Compra compra : compras) {
                // Insertamos las compras
                ps.setInt(1, compra.getIdPlayer());
                ps.setInt(2, compra.getIdGame());
                ps.setString(3, compra.getCosa());
                ps.setDouble(4, compra.getPrecio());
                ps.setString(5, compra.getFecha());
                ps.executeUpdate();
            }

            System.out.println("Compras insertadas");
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
     * Verifica si existe una tabla en la base de datos
     * 
     * @param nombreTabla nombre de la tabla a verificar
     * @return true si la tabla existe, false en caso contrario
     */
    public static boolean tablaExiste(String nombreTabla) {
        boolean existe = false;

        Connection conn;
        try {
            conn = ConexionDB.connect();
            if (conn != null) {
                DatabaseMetaData dbm = conn.getMetaData();

                ResultSet tables;
                try {
                    tables = dbm.getTables(null, null, nombreTabla, null);
                    existe = tables.next(); // Verifica si hay al menos una coincidencia.
                } catch (SQLException e) {
                    System.err.println("Ha ocurrido un error al verificar la tabla: " + e.getMessage());
                }
            } else {
                System.out.println("Conexión a la base de datos fallida.");
            }
        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error al verificar la tabla: " + e.getMessage());
            e.printStackTrace();
        }

        return existe;
    }

    /**
     * Elimina una tabla de la base de datos
     * 
     * @param nombreTabla nombre de la tabla a eliminar
     */
    public static void eliminarTabla(String nombreTabla) {
        // TODO
        if (AccesoDatos.tablaExiste(nombreTabla)) {
            Connection conn = null;
            Statement stmt = null;
            try {
                conn = ConexionDB.connect();
                stmt = conn.createStatement();
                stmt.executeUpdate(useDB);
                String sql = "DROP TABLE " + nombreTabla;
                stmt.execute(sql);
                System.out.println("Tabla " + nombreTabla + " eliminada.");
            } catch (SQLException se) {
                // Gestionamos los posibles errores que puedan surgir durante la ejecución de la
                // inserción
                se.printStackTrace();
            } catch (Exception e) {
                System.out.println("Se ha producido un error eliminando la tabla " + nombreTabla + ".");
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
        } else {
            System.err.println("La tabla " + nombreTabla + " no existe.");
        }
    }

    /**
     * Busca un jugador en la base de datos
     * 
     * @param nombre nombre del jugador a buscar
     * @return Lista de jugadores
     */
    public static List<Player> buscarPlayer(String nombre) {
        List<Player> players = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "SELECT * FROM Players WHERE nick LIKE '" + nombre + "%'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String nick = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);

                Player player = new Player(id, nick, password, email);
                players.add(player);
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return players;
    }

    /**
     * Busca un juego en la base de datos
     * 
     * @param nombre nombre del juego a buscar
     * @return Lista de juegos
     */
    public static List<Game> buscarGame(String nombre) {
        List<Game> games = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "SELECT * FROM Games WHERE nombre LIKE '" + nombre + "%'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombreJuego = rs.getString(2);
                String tiempoJugado = rs.getString(3);

                Game game = new Game(id, nombreJuego, tiempoJugado);
                games.add(game);
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return games;
    }

    /**
     * Inserta un jugador en la base de datos
     * 
     * @param player jugador a insertar
     * @return boolean que determina si el jugador se ha insertado o no
     */
    public static boolean insertarPlayer(Player player) {
        boolean insertado = false;
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        
        // Comprobamos que no exista ya un player con esos datos
        if (!existePlayer(player.getEmail())) {
            try {
                conn = ConexionDB.connect();
                stmt = conn.createStatement();
                stmt.executeUpdate(useDB);
                String sql = "INSERT INTO Players (nick, password, email) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, player.getNick());
                ps.setString(2, player.getPassword());
                ps.setString(3, player.getEmail());
                ps.executeUpdate();
                insertado = true;
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

        return insertado;
    }

    /**
     * Comprueba si un jugador existe en la base de datos
     * 
     * @param mail email del jugador a buscar
     * @return boolean que determina si el jugador existe o no
     */
    private static boolean existePlayer(String mail) {
        boolean existe = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "SELECT * FROM Players WHERE email = '" + mail + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                existe = true;
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return existe;
    }

    /**
     * Inserta un juego en la base de datos.
     * 
     * @param game el juego a insertar, con nombre y tiempo jugado especificados.
     * @return boolean que indica si el juego fue insertado correctamente.
     */
    public static boolean insertarGame(Game game) {
        boolean insertado = false;
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "INSERT INTO Games (nombre, tiempoJugado) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, game.getNombre());
            ps.setString(2, game.getTiempoJugado());
            ps.executeUpdate();
            insertado = true;
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

        return insertado;
    }

    /**
     * Inserta una compra en la base de datos.
     * 
     * @param compra la compra a insertar, con idPlayer, idGame, cosa, precio y
     *               fechaCompra
     *               especificados.
     * @return boolean que indica si la compra fue insertada correctamente.
     */
    public static boolean insertarCompra(Compra compra) {
        boolean insertado = false;
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "INSERT INTO Compras (idPlayer, idGame, cosa, precio, fechaCompra) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, compra.getIdPlayer());
            ps.setInt(2, compra.getIdGame());
            ps.setString(3, compra.getCosa());
            ps.setDouble(4, compra.getPrecio());
            ps.setString(5, compra.getFecha() + " 00:00:00");
            ps.executeUpdate();
            insertado = true;
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

        return insertado;
    }
}
