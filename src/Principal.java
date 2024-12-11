import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dal.AccesoDatos;
import dal.ConexionDB;
import ent.Compra;
import ent.Game;
import ent.Player;

public class Principal {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Creamos la base de datos Players
        // AccesoDatos.crearTablaPlayers();

        // Insertamos los jugadores
        // AccesoDatos.insertarPlayers();

        // AccesoDatos.crearTablaGames();

        // AccesoDatos.crearTablaCompras();

        // AccesoDatos.insertarGames();

        // AccesoDatos.insertarCompras();

        int opcion = 0;

        do {
            opcion = menu();
            opcionElegida(opcion);
        } while (opcion != 8);
    }

    // Método que muestra el menú
    private static int menu() {
        int opcion = 0;
        System.out.println();
        System.out.println("Menu");
        System.out.println("1. Conectar");
        System.out.println("2. Crear tablas");
        System.out.println("3. Insertar");
        System.out.println("4. Listar");
        System.out.println("5. Modificar");
        System.out.println("6. Borrar");
        System.out.println("7. Eliminar tablas");
        System.out.println("8. Salir");

        do {
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
                scanner.nextLine();
            }
        } while (opcion < 1 || opcion > 8);
        return opcion;
    }

    // Método que ejecuta la opción elegida
    private static void opcionElegida(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("Conectando a la base de datos...");
                Connection conn = ConexionDB.connect();

                try {
                    if (!conn.isClosed()) {
                        System.out.println("Conectado!");
                    } else {
                        System.out.println("No se pudo conectar");
                    }
                } catch (SQLException e) {
                    System.out.println("Ha ocurrido un error...");
                }
                break;
            case 2:
                crearTablas();
                break;
            case 3:
                insertar();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                System.out.println("Gracias por usar el programa!");
                break;
        }
    }

    // Método que muestra el menú para crear todas las tablas o una tabla
    private static void crearTablas() {
        int opcion = 0;

        do {
            System.out.println();
            System.out.println("¿Qué deseas hacer?");
            System.out.println("1. Crear todas las tablas");
            System.out.println("2. Crear una tabla");
            System.out.println("3. Volver al menú principal");

            do {
                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                    scanner.nextLine();
                }
            } while (opcion < 1 || opcion > 3);

            switch (opcion) {
                case 1:
                    System.out.println("Creando tablas...");
                    AccesoDatos.crearTablaPlayers();
                    AccesoDatos.crearTablaGames();
                    AccesoDatos.crearTablaCompras();
                    break;
                case 2:
                    menuTablas();
                    break;
                case 3:
                    // Volvemos al menú principal
                    System.out.println("Volviendo...");
                    break;
            }
        } while (opcion != 3);

    }

    // Metodo para crear una tabla específica
    private static void menuTablas() {
        int opcionTabla = 0;
        do {
            System.out.println();
            System.out.println("Que tabla deseas crear?");
            System.out.println("1. Players");
            System.out.println("2. Games");
            System.out.println("3. Compras");
            System.out.println("4. Volver al menú");

            do {
                try {
                    opcionTabla = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                    scanner.nextLine();
                }
            } while (opcionTabla < 1 || opcionTabla > 4);

            switch (opcionTabla) {
                case 1:
                    System.out.println("Creando la tabla PLAYERS...");
                    AccesoDatos.crearTablaPlayers();
                    break;
                case 2:
                    System.out.println("Creando la tabla GAMES...");
                    AccesoDatos.crearTablaGames();
                    break;
                case 3:
                    AccesoDatos.crearTablaCompras();
                    break;
                case 4:
                    // Volvemos al menú
                    System.out.println("Volviendo...");
                    break;
            }
        } while (opcionTabla != 4);

    }

    private static void insertar() {
        int opcion = 0;

        do {
            System.out.println();
            System.out.println("¿En cuál tabla deseas insertar?");
            System.out.println("1. Players");
            System.out.println("2. Games");
            System.out.println("3. Compras");
            System.out.println("4. Volver al menú principal");

            do {
                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                    scanner.nextLine();
                }
            } while (opcion < 1 || opcion > 4);

            switch (opcion) {
                case 1:
                    Player jugador = (Player) pideDatos(1);
                    System.out.println(jugador.getNick());
                    break;
                case 2:
                    Game game = (Game) pideDatos(2);
                    System.out.println(game.getTiempoJugado());
                    break;
                case 3:
                    Compra compra = (Compra) pideDatos(3);
                    break;

            }

        } while (opcion != 4);
    }

    private static Object pideDatos(int opcion) {
        Object object = null;

        switch (opcion) {
            case 1:
                scanner.nextLine();
                String nombre = "";
                String password = "";
                String email = "";

                System.out.println();
                do {
                    System.out.println("Introduce el nombre del jugador: ");
                    nombre = scanner.nextLine();
                } while (nombre.isEmpty());

                System.out.println();
                do {
                    System.out.println("Introduce la contraseña del jugador: ");
                    password = scanner.nextLine();
                } while (password.isEmpty());

                System.out.println();
                do {
                    System.out.println("Introduce el email del jugador: ");
                    email = scanner.nextLine();
                } while (email.isEmpty());

                object = new Player(0, nombre, password, email);
                break;

            case 2:
                scanner.nextLine();

                String nombreJuego = "";
                String horaJuego = "";
                int horas = -1;
                int minutos = -1;
                int segundos = -1;
                System.out.println();

                do {
                    System.out.println("Introduce el nombre del juego: ");
                    nombreJuego = scanner.nextLine();
                } while (nombreJuego.isEmpty());

                System.out.println();
                do {
                    try {
                        System.out.println("Introduce las horas jugadas: ");
                        horas = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }
                } while (horas < 0);

                System.out.println();
                do {
                    try {
                        System.out.println("Introduce los minutos jugados: ");
                        minutos = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }
                } while (minutos < 0 || minutos > 59);

                System.out.println();
                do {
                    try {
                        System.out.println("Introduce los segundos jugados: ");
                        segundos = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }
                } while (segundos < 0 || segundos > 59);

                horaJuego = (horas > 9 ? horas : "0" + horas) + ":" + (minutos > 9 ? minutos : "0" + minutos) + ":"
                        + (segundos > 9 ? segundos : "0" + segundos);

                object = new Game(0, nombreJuego, horaJuego);
                break;

            case 3:
                scanner.nextLine();

                int idPlayer = -1;
                String nombreJugador = "";
                boolean jugadorEncontrado = false;

                do {
                    System.out.println("Nombre del jugador?");
                    nombreJugador = scanner.nextLine();

                    List<Player> players = AccesoDatos.buscarPlayer(nombreJugador);

                    jugadorEncontrado = players.isEmpty();

                    if (players.isEmpty()) {
                        System.out.println("No se ha encontrado ningú jugador con ese nombre");
                    } else {
                        if (players.size() > 1) {
                            System.out.println("Se han encontrado los siguientes jugadores");
                            System.out.printf("| %-10s | %-25s | %-15s %n", "Número", "Nombre", "Email");
                            System.out.println("-".repeat(80));
                            for (int i = 0; i < players.size(); i++) {
                                Player player = players.get(i);
                                System.out.printf("| %-10s | %-25s | %-15s %n",  i, player.getNick(), player.getEmail());
                                System.out.println("-".repeat(80));
                            }
                            System.out.println();
                            System.out.println("Elige un jugador de los mostrados arriba");
                        } else {
                            idPlayer = players.get(0).getIdPlayer();
                        }
                    }
                } while (jugadorEncontrado);

                break;
        }

        return object;
    }
}
