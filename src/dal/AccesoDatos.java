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
                        "tiempoJugado varchar(50));";
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

    /**
     * Muestra una lista de jugadores que coinciden con los criterios de filtrado
     * dados.
     * 
     * @param opcionFiltrar 1 si se quiere filtrar por nick, 2 si se quiere filtrar
     *                      por email.
     *                      Si no se especifica ninguno, se muestran todos los
     *                      jugadores.
     * @param valorFiltrar  el valor que se va a buscar en el campo correspondiente.
     */
    public static void listarPlayers(int opcionFiltrar, String valorFiltrar) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "SELECT * FROM Players";

            // Construir la consulta con filtros si es necesario
            if (opcionFiltrar == 1) {
                sql += " WHERE nick LIKE ?";
            } else if (opcionFiltrar == 2) {
                sql += " WHERE email LIKE ?";
            }

            ps = conn.prepareStatement(sql);

            if (opcionFiltrar == 1 || opcionFiltrar == 2) {
                ps.setString(1, "%" + valorFiltrar + "%");
            }

            ResultSet rs = ps.executeQuery();

            // Mostrar encabezado si hay resultados
            boolean hayResultados = false;
            while (rs.next()) {
                if (!hayResultados) {
                    System.out.printf("| %-10s | %-25s | %-15s %n", "ID", "Nombre", "Email");
                    System.out.println("-".repeat(80));
                    hayResultados = true;
                }

                int id = rs.getInt("idPlayer");
                String nick = rs.getString("nick");
                String email = rs.getString("email");
                System.out.printf("| %-10s | %-25s | %-15s %n", id, nick, email);
                System.out.println("-".repeat(80));
            }

            if (!hayResultados) {
                System.out.println("No se encontraron resultados.");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
    }

    /**
     * Muestra una lista de juegos que coinciden con los criterios de filtrado
     * dados.
     * 
     * @param opcionFiltrar 1 si se quiere filtrar por nombre, 2 si se quiere
     *                      filtrar
     *                      por tiempo jugado. Si no se especifica ninguno, se
     *                      muestran
     *                      todos los juegos.
     * @param valorFiltrar  el valor que se va a buscar en el campo correspondiente.
     */

    public static void listarGames(int opcionFiltrar, String valorFiltrar) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "SELECT * FROM Games";

            // Construir la consulta con filtros si es necesario
            if (opcionFiltrar == 1) {
                sql += " WHERE nombre LIKE ?";
            } else if (opcionFiltrar == 2) {
                sql += " WHERE tiempoJugado LIKE ?";
            }

            ps = conn.prepareStatement(sql);

            if (opcionFiltrar == 1 || opcionFiltrar == 2) {
                ps.setString(1, "%" + valorFiltrar + "%");
            }

            ResultSet rs = ps.executeQuery();

            // Mostrar encabezado si hay resultados
            boolean hayResultados = false;
            while (rs.next()) {
                if (!hayResultados) {
                    System.out.printf("| %-10s | %-40s | %-15s %n", "ID", "Nombre", "Tiempo Jugado");
                    System.out.println("-".repeat(80));
                    hayResultados = true;
                }

                int id = rs.getInt("idGame");
                String nombre = rs.getString("nombre");
                String tiempoJugado = rs.getString("tiempoJugado");
                System.out.printf("| %-10s | %-40s | %-15s %n", id, nombre, tiempoJugado);
                System.out.println("-".repeat(80));
            }

            if (!hayResultados) {
                System.out.println("No se encontraron resultados.");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
    }

    /**
     * Muestra una lista de compras que coinciden con los criterios de filtrado
     * dados.
     *
     * @param opcionFiltrar 1 para filtrar por id de jugador, 2 para filtrar por id
     *                      de juego,
     *                      3 para filtrar por precio, 4 para filtrar por fecha de
     *                      compra.
     *                      Si no se especifica ninguno, se muestran todas las
     *                      compras.
     * @param valorFiltrar  el valor que se va a buscar en el campo correspondiente.
     */

    public static void listarCompras(int opcionFiltrar, String valorFiltrar) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "SELECT * FROM Compras";

            // Construir la consulta con filtros si es necesario
            if (opcionFiltrar == 1) {
                sql += " WHERE cosa LIKE ?";
            } else if (opcionFiltrar == 2) {
                sql += " WHERE idPlayer = ?";
            } else if (opcionFiltrar == 3) {
                sql += " WHERE precio LIKE ?";
            } else if (opcionFiltrar == 4) {
                sql += " WHERE fechaCompra LIKE ?";
            }

            ps = conn.prepareStatement(sql);

            if (opcionFiltrar == 1 || opcionFiltrar == 3 || opcionFiltrar == 4) {
                ps.setString(1, "%" + valorFiltrar + "%");
            } else if (opcionFiltrar == 2) {
                ps.setString(1, valorFiltrar);
            }

            ResultSet rs = ps.executeQuery();

            // Mostrar encabezado si hay resultados
            boolean hayResultados = false;
            while (rs.next()) {
                if (!hayResultados) {
                    System.out.printf("| %-10s | %-10s | %-40s | %-10s | %-10s %n", "ID", "Jugador", "Juego",
                            "Precio", "Fecha de compra");
                    System.out.println("-".repeat(100));
                    hayResultados = true;
                }

                int id = rs.getInt("idCompra");
                int idPlayer = rs.getInt("idPlayer");
                String nombreJugador = buscarPlayerId(idPlayer).getNick();

                double precio = rs.getDouble("precio");
                String cosa = rs.getString("cosa");
                String fecha = rs.getString("fechaCompra");
                System.out.printf("| %-10s | %-10s | %-40s | %-10s | %-10s %n", id, nombreJugador, cosa, precio, fecha);
                System.out.println("-".repeat(100));
            }

            if (!hayResultados) {
                System.out.println("No se encontraron resultados.");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
    }

    /**
     * Busca un jugador por su id en la base de datos
     * 
     * @param id id del jugador a buscar
     * @return el jugador encontrado, o null si no se encontró
     */
    private static Player buscarPlayerId(int id) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        Player player = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            ps = conn.prepareStatement("SELECT * FROM Players where idPlayer = " + id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Si se encuentra un resultado, crea y retorna el objeto Player
                int idPlayer = rs.getInt("idPlayer");
                String nick = rs.getString("nick");
                String password = rs.getString("password");
                String email = rs.getString("email");
                player = new Player(idPlayer, nick, password, email);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return player;
    }

    /**
     * Modifica un jugador en la base de datos
     * 
     * @param opcion   1 si se quiere modificar el nick, 2 si se quiere modificar el
     *                 email, 3 si se
     *                 quiere modificar la contraseña
     * @param valor    el nuevo valor para el campo especificado
     * @param idPlayer id del jugador a modificar
     * @return boolean que determina si se ha modificado correctamente o no
     */
    public static boolean modificarPlayer(int opcion, String valor, int idPlayer) {
        boolean modificado = false;
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "UPDATE Players SET ";

            if (opcion == 1) {
                sql += "nick = ?";
            } else if (opcion == 2) {
                sql += "email = ?";
            } else if (opcion == 3) {
                sql += "password = ?";
            }

            sql += " WHERE idPlayer = " + idPlayer;

            ps = conn.prepareStatement(sql);
            ps.setString(1, valor);
            ps.executeUpdate();
            modificado = true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return modificado;
    }

    /**
     * Modifica un juego en la base de datos.
     * 
     * @param opcion 1 si se quiere modificar el nombre, 2 si se quiere modificar
     *               el tiempo jugado.
     * @param valor  el nuevo valor para el campo especificado.
     * @param idGame id del juego a modificar.
     * @return boolean que determina si se ha modificado correctamente o no.
     */
    public static boolean modificarGame(int opcion, String valor, int idGame) {
        boolean modificado = false;
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "UPDATE Games SET ";

            if (opcion == 1) {
                sql += "nombre = ?";
            } else if (opcion == 2) {
                sql += "tiempoJugado = ?";
            }

            sql += " WHERE idGame = " + idGame;

            ps = conn.prepareStatement(sql);
            ps.setString(1, valor);
            ps.executeUpdate();
            modificado = true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return modificado;
    }

    /**
     * Modifica una compra en la base de datos
     * 
     * @param opcion   1 si se quiere modificar el id del jugador, 2 si se
     *                 quiere modificar el id del juego, 3 si se
     *                 quiere modificar la cosa, 4 si se
     *                 quiere modificar el precio, 5 si se
     *                 quiere modificar la fecha de la compra
     * @param valor    el nuevo valor para el campo especificado
     * @param idCompra id de la compra a modificar
     * @return boolean que determina si se ha modificado correctamente o no
     */
    public static boolean modificarCompra(int opcion, String valor, int idCompra) {
        boolean modificado = false;
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "UPDATE Compras SET ";

            if (opcion == 1) {
                sql += "idPlayer = ?";
            } else if (opcion == 2) {
                sql += "idGame = ?, cosa = ?";
            } else if (opcion == 3) {
                sql += "precio = ?";
            } else if (opcion == 4) {
                sql += "fechaCompra = ?";
            }

            sql += " WHERE idCompra = " + idCompra;

            ps = conn.prepareStatement(sql);

            if (opcion == 1) {
                ps.setInt(1, Integer.parseInt(valor));
            } else if (opcion == 2) {
                String[] values = valor.split(":");
                ps.setInt(1, Integer.parseInt(values[1]));
                ps.setString(2, values[0]);
            } else if (opcion == 3) {
                ps.setDouble(1, Double.parseDouble(valor));
            } else if (opcion == 4) {
                ps.setString(1, valor);
            }

            ps.executeUpdate();
            modificado = true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return modificado;
    }

    /**
     * Borra un jugador de la base de datos.
     * 
     * @param idPlayer id del jugador a borrar.
     * @return boolean que determina si se ha borrado correctamente o no.
     */
    public static boolean borrarPlayer(int idPlayer) {
        boolean borrado = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "DELETE FROM Players WHERE idPlayer = " + idPlayer;
            stmt.executeUpdate(sql);
            borrado = true;
        } catch (SQLException se) {
            // se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return borrado;
    }

    /**
     * Borra un juego de la base de datos.
     * 
     * @param idGame id del juego a borrar.
     * @return boolean que determina si se ha borrado correctamente o no.
     */
    public static boolean borrarGame(int idGame) {
        boolean borrado = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "DELETE FROM Games WHERE idGame = " + idGame;
            stmt.executeUpdate(sql);
            borrado = true;
        } catch (SQLException se) {
            // se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return borrado;
    }

    /**
     * Borra una compra de la base de datos.
     * 
     * @param idCompra id de la compra a borrar.
     * @return boolean que determina si se ha borrado correctamente o no.
     */
    public static boolean borrarCompra(int idCompra) {
        boolean borrado = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConexionDB.connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(useDB);
            String sql = "DELETE FROM Compras WHERE idCompra = " + idCompra;
            stmt.executeUpdate(sql);
            borrado = true;
        } catch (SQLException se) {
            // se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("No se ha podido cerrar la conexión.");
            }
        }
        return borrado;
    }
}