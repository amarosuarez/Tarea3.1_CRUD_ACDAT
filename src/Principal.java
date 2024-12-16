import java.sql.Connection;
import java.sql.SQLException;
import java.time.Year;
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
                listar();
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                System.out.println();
                System.out.println("Gracias por usar el programa!");
                break;
        }
    }

    // Método que muestra el menú para crear todas las tablas o una tabla
    private static void crearTablas() {
        int opcion = 0;

        do {
            do {
                System.out.println();
                System.out.println("¿Qué deseas hacer?");
                System.out.println("1. Crear todas las tablas");
                System.out.println("2. Crear una tabla");
                System.out.println("3. Volver al menú principal");
                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                    scanner.nextLine();
                }
            } while (opcion < 1 || opcion > 3);

            switch (opcion) {
                case 1:
                    // Creamos todas las tablas
                    System.out.println("Creando tablas...");
                    AccesoDatos.crearTablaPlayers();
                    AccesoDatos.crearTablaGames();
                    AccesoDatos.crearTablaCompras();
                    break;
                case 2:
                    // Creamos una tabla
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
            do {
                System.out.println();
                System.out.println("Que tabla deseas crear?");
                System.out.println("1. Players");
                System.out.println("2. Games");
                System.out.println("3. Compras");
                System.out.println("4. Volver al menú");
                try {
                    opcionTabla = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                } finally {
                    scanner.nextLine();
                }
            } while (opcionTabla < 1 || opcionTabla > 4);

            switch (opcionTabla) {
                case 1:
                    // Creamos la tabla Players
                    System.out.println("Creando la tabla PLAYERS...");
                    AccesoDatos.crearTablaPlayers();
                    break;
                case 2:
                    // Creamos la tabla Games
                    System.out.println("Creando la tabla GAMES...");
                    AccesoDatos.crearTablaGames();
                    break;
                case 3:
                    // Creamos la tabla Compras
                    AccesoDatos.crearTablaCompras();
                    break;
                case 4:
                    // Volvemos al menú
                    System.out.println("Volviendo...");
                    break;
            }
        } while (opcionTabla != 4);

    }

    // Metodo para insertar datos
    private static void insertar() {
        int opcion = 0;

        do {
            do {

                // Mostramos las opciones
                System.out.println();
                System.out.println("¿En cuál tabla deseas insertar?");
                System.out.println("1. Players");
                System.out.println("2. Games");
                System.out.println("3. Compras");
                System.out.println("4. Volver al menú principal");

                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                } finally {
                    scanner.nextLine();
                }
            } while (opcion < 1 || opcion > 4);

            // Pedimos los datos según la opción
            switch (opcion) {
                case 1:
                    // Pedimos los datos del Player y lo insertamos
                    Player jugador = (Player) pideDatos(1);

                    // Mostramos el resultado
                    if (AccesoDatos.insertarPlayer(jugador)) {
                        System.out.println("Jugador insertado");
                    } else {
                        System.out.println("No se pudo insertar al jugador");
                    }
                    break;
                case 2:
                    // Pedimos los datos del Game y lo insertamos
                    Game game = (Game) pideDatos(2);

                    // Mostramos el resultado
                    if (AccesoDatos.insertarGame(game)) {
                        System.out.println("Game insertado");
                    } else {
                        System.out.println("No se pudo insertar el game");
                    }
                    break;
                case 3:
                    // Pedimos los datos de la Compra y lo insertamos
                    Compra compra = (Compra) pideDatos(3);

                    // Mostramos el resultado
                    if (AccesoDatos.insertarCompra(compra)) {
                        System.out.println("Compra insertada");
                    } else {
                        System.out.println("No se pudo insertar la compra");
                    }
                    break;
                case 4:
                    // Volvemos al menú principal
                    System.out.println("Volviendo...");
                    break;
            }

        } while (opcion != 4);
    }

    // Metodo que pide los datos del objeto especificado
    // 1. Player
    // 2. Game
    // 3. Compra
    private static Object pideDatos(int opcion) {
        Object object = null;

        // Según la opción, pedimos los datos correspondientes
        switch (opcion) {
            case 1:
                // Pedimos los datos de un Player
                object = pideDatosPlayer();
                break;
            case 2:
                // Pedimos los datos de un Game
                object = pideDatosGame();
                break;
            case 3:
                // Pedimos los datos de una Compra
                object = pideDatosCompra();
                break;
        }

        return object;
    }

    // Pide los datos de un jugador
    private static Player pideDatosPlayer() {
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

        Player player = new Player(0, nombre, password, email);

        return player;
    }

    // Pide los datos de un juego
    private static Game pideDatosGame() {
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

        Game game = new Game(0, nombreJuego, horaJuego);

        return game;
    }

    // Pide los datos de una Compra
    private static Compra pideDatosCompra() {
        scanner.nextLine();

        boolean jugadorEncontrado = false;
        boolean juegoEncontrado = false;
        int idGame = -1;
        int idPlayer = -1;
        String nombreJugador = "";
        String nombreJuego = "";
        String cosa = "";
        double precio = -1;
        int dia = -1;
        int mes = -1;
        int anyo = -1;
        String date = "";
        boolean diaCorrecto = false;

        do {
            System.out.println();
            System.out.println("Nombre del jugador?");
            nombreJugador = scanner.nextLine();

            List<Player> players = AccesoDatos.buscarPlayer(nombreJugador);

            jugadorEncontrado = players.isEmpty();

            if (players.isEmpty()) {
                System.out.println("No se ha encontrado ningún jugador con ese nombre");
            } else {
                if (players.size() > 1) {
                    System.out.println("Se han encontrado los siguientes jugadores:");
                    System.out.printf("| %-10s | %-25s | %-15s %n", "Número", "Nombre", "Email");
                    System.out.println("-".repeat(80));
                    for (int i = 0; i < players.size(); i++) {
                        Player player = players.get(i);
                        System.out.printf("| %-10s | %-25s | %-15s %n", i, player.getNick(), player.getEmail());
                        System.out.println("-".repeat(80));
                    }
                    int jugadorElegido = -1;
                    do {
                        System.out.println();
                        System.out.println("Elige un jugador de los mostrados arriba");
                        try {
                            jugadorElegido = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.err.println("Introduzca un número");
                            scanner.nextLine();
                        }
                    } while (jugadorElegido < 0 || jugadorElegido >= players.size());
                    idPlayer = players.get(jugadorElegido).getIdPlayer();
                } else {
                    idPlayer = players.get(0).getIdPlayer();
                }
            }
        } while (jugadorEncontrado);

        do {
            System.out.println();
            System.out.println("Introduce el nombre del juego: ");
            nombreJuego = scanner.nextLine();

            List<Game> games = AccesoDatos.buscarGame(nombreJuego);

            juegoEncontrado = games.isEmpty();

            if (!games.isEmpty()) {
                if (games.size() > 1) {
                    System.out.println("Se han encontrado los siguientes juegos:");
                    System.out.printf("| %-10s | %-40s %n", "Número", "Nombre");
                    System.out.println("-".repeat(80));
                    for (int i = 0; i < games.size(); i++) {
                        Game game = games.get(i);
                        System.out.printf("| %-10s | %-40s %n", i, game.getNombre());
                        System.out.println("-".repeat(80));
                    }

                    int juegoElegido = -1;
                    do {
                        System.out.println();
                        System.out.println("Elige un juego de los mostrados arriba");
                        try {
                            juegoElegido = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.err.println("Introduzca un número");
                            scanner.nextLine();
                        }
                    } while (juegoElegido < 0 || juegoElegido >= games.size());
                    idGame = games.get(juegoElegido).getIdGame();
                    cosa = games.get(juegoElegido).getNombre();
                } else {
                    idGame = games.get(0).getIdGame();
                    cosa = games.get(0).getNombre();
                }
            } else {
                System.out.println("No se ha encontrado ningú juego con ese nombre");
            }

        } while (juegoEncontrado);

        do {
            System.out.println();
            System.out.println("Introduce el precio de la compra: ");
            try {
                precio = scanner.nextDouble();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
                scanner.nextLine();
            }
        } while (precio < 0);

        do {
            System.out.println();
            System.out.println("Introduce el anyo de la compra: ");
            try {
                anyo = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
                scanner.nextLine();
            }
        } while (anyo < 0 || anyo > Year.now().getValue());

        do {
            System.out.println();
            System.out.println("Introduce el mes de la compra: ");
            try {
                mes = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
                scanner.nextLine();
            }
        } while (mes < 0 || mes > 12);

        do {
            System.out.println();
            System.out.println("Introduce el dia de la compra: ");
            try {
                dia = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
                scanner.nextLine();
            }

            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                if (dia > 0 && dia <= 31) {
                    diaCorrecto = true;
                }
            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                if (dia > 0 && dia <= 30) {
                    diaCorrecto = true;
                }
            } else if (mes == 2) {
                if (Year.isLeap(anyo)) {
                    if (dia > 0 && dia <= 29) {
                        diaCorrecto = true;
                    }
                } else {
                    if (dia > 0 && dia <= 28) {
                        diaCorrecto = true;
                    }
                }
            }
        } while (!diaCorrecto);

        date = anyo + "-" + mes + "-" + dia;

        Compra compra = new Compra(0, idPlayer, idGame, cosa, precio, date);

        return compra;
    }

    // Método que pregunta que quiere listar
    private static void listar() {
        int opcion = 0;

        do {
            do {
                System.out.println();
                System.out.println("¿Qué quieres listar?");
                System.out.println("1. Players");
                System.out.println("2. Games");
                System.out.println("3. Compras");
                System.out.println("4. Volver al menú principal");
                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                } finally {
                    scanner.nextLine();
                }
            } while (opcion < 1 || opcion > 4);

            switch (opcion) {
                case 1:
                    listarPlayers();
                    break;
                case 2:
                    listarGames();
                    break;
                case 3:
                    listarCompras();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
            }
        } while (opcion != 4);
    }

    // Método que lista los players
    private static void listarPlayers() {
        int opcion = -1;
        String nombre = "";
        String email = "";

        System.out.println();
        System.out.println("¿Por qué quieres filtrar?");
        System.out.println("1. Por nombre");
        System.out.println("2. Por email");
        System.out.println("3. No filtrar, mostrar todos");

        do {
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
                scanner.nextLine();
            }
        } while (opcion < 1 || opcion > 3);

        switch (opcion) {
            case 1:
                System.out.println();
                System.out.println("Introduce el nombre: ");
                nombre = scanner.nextLine();

                AccesoDatos.listarPlayers(1, nombre);
                break;
            case 2:
                System.out.println();
                System.out.println("Introduce el email: ");
                email = scanner.nextLine();

                AccesoDatos.listarPlayers(2, email);
                break;
            case 3:
                AccesoDatos.listarPlayers(0, email);
                break;
        }
    }

    // Método que lista los juegos
    private static void listarGames() {
        int opcion = -1;
        String nombre = "";
        int horas = -1;
        int minutos = -1;
        int segundos = -1;
        String horasJugadas = "";

        System.out.println();
        System.out.println("¿Por qué quieres filtrar?");
        System.out.println("1. Por nombre");
        System.out.println("2. Por tiempo jugado");
        System.out.println("3. No filtrar, mostrar todos");

        do {
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
                scanner.nextLine();
            }
        } while (opcion < 1 || opcion > 3);

        switch (opcion) {
            case 1:
                System.out.println();
                System.out.println("Introduce el nombre: ");
                nombre = scanner.nextLine();

                AccesoDatos.listarGames(1, nombre);
                break;
            case 2:
                System.out.println();
                System.out.println("Introduce las horas: ");

                do {
                    try {
                        horas = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }
                } while (horas < 0);

                System.out.println();
                System.out.println("Introduce los minutos: ");

                do {
                    try {
                        minutos = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }
                } while (minutos < 0 && minutos > 59);

                System.out.println();
                System.out.println("Introduce los segundos: ");

                do {
                    try {
                        segundos = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }
                } while (segundos < 0 && segundos > 59);

                horasJugadas = horas + ":" + minutos + ":" + segundos;

                AccesoDatos.listarGames(2, horasJugadas);
                break;
            case 3:
                AccesoDatos.listarGames(0, "");
                break;
        }
    }

    // Método que lista las compras
    private static void listarCompras() {
        int opcion = -1;
        String nombreJuego = "";
        String nombrePlayer = "";
        double precio = -1;
        int dia = -1;
        int mes = -1;
        int anyo = -1;
        String date = "";

        System.out.println();
        System.out.println("¿Por qué quieres filtrar?");
        System.out.println("1. Por nombre de juego");
        System.out.println("2. Por nombre de jugador");
        System.out.println("3. Por precio");
        System.out.println("4. Por fecha");
        System.out.println("5. No filtrar, mostrar todos");

        do {
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
                scanner.nextLine();
            }
        } while (opcion < 1 || opcion > 5);

        switch (opcion) {
            case 1:
                System.out.println();
                System.out.println("Introduce el nombre del juego: ");
                nombreJuego = scanner.nextLine();
                AccesoDatos.listarCompras(1, nombreJuego);
                break;
            case 2:
                int idPlayer = -1;
                boolean jugadorEncontrado = false;
                do {
                    System.out.println();
                    System.out.println("Nombre del jugador?");
                    nombrePlayer = scanner.nextLine();

                    List<Player> players = AccesoDatos.buscarPlayer(nombrePlayer);

                    jugadorEncontrado = players.isEmpty();

                    if (players.isEmpty()) {
                        System.out.println("No se ha encontrado ningún jugador con ese nombre");
                    } else {
                        if (players.size() > 1) {
                            System.out.println("Se han encontrado los siguientes jugadores:");
                            System.out.printf("| %-10s | %-25s | %-15s %n", "Número", "Nombre", "Email");
                            System.out.println("-".repeat(80));
                            for (int i = 0; i < players.size(); i++) {
                                Player player = players.get(i);
                                System.out.printf("| %-10s | %-25s | %-15s %n", i, player.getNick(), player.getEmail());
                                System.out.println("-".repeat(80));
                            }
                            int jugadorElegido = -1;
                            do {
                                System.out.println();
                                System.out.println("Elige un jugador de los mostrados arriba");
                                try {
                                    jugadorElegido = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    System.err.println("Introduzca un número");
                                    scanner.nextLine();
                                }
                            } while (jugadorElegido < 0 || jugadorElegido >= players.size());
                            idPlayer = players.get(jugadorElegido).getIdPlayer();
                        } else {
                            idPlayer = players.get(0).getIdPlayer();
                        }
                    }
                } while (jugadorEncontrado);

                AccesoDatos.listarCompras(2, String.valueOf(idPlayer));

                break;
            case 3:
                do {
                    System.out.println();
                    System.out.println("Introduce el precio: ");
                    try {
                        precio = scanner.nextDouble();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }
                } while (precio < 0);

                AccesoDatos.listarCompras(3, Double.toString(precio));
                break;
            case 4:
                do {
                    System.out.println();
                    System.out.println("Introduce el año: ");
                    try {
                        anyo = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }
                } while (anyo < 0 || anyo > Year.now().getValue());

                do {
                    System.out.println();
                    System.out.println("Introduce el mes: ");
                    try {
                        mes = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }
                } while (mes < 1 || mes > 12);

                boolean diaCorrecto = false;
                do {
                    System.out.println();
                    System.out.println("Introduce el dia: ");
                    try {
                        dia = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                        scanner.nextLine();
                    }

                    if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                        if (dia > 0 && dia <= 31) {
                            diaCorrecto = true;
                        }
                    } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                        if (dia > 0 && dia <= 30) {
                            diaCorrecto = true;
                        }
                    } else {
                        if (Year.isLeap(anyo)) {
                            if (dia > 0 && dia <= 29) {
                                diaCorrecto = true;
                            }
                        } else {
                            if (dia > 0 && dia <= 28) {
                                diaCorrecto = true;
                            }
                        }
                    }
                } while (!diaCorrecto);

                date = anyo + "-" + mes + "-" + dia;
                AccesoDatos.listarCompras(4, date);

                break;
            case 5:
                AccesoDatos.listarCompras(0, "");
                break;
        }
    }
}