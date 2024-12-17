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
                modificar();
                break;
            case 6:
                borrar();
                break;
            case 7:
                borrarTablas();
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

        // Pide el nombre del jugador
        do {
            System.out.println();
            System.out.println("Nombre del jugador?");
            nombreJugador = scanner.nextLine();

            // Busca al jugador en la BD
            List<Player> players = AccesoDatos.buscarPlayer(nombreJugador);

            // Si no se ha encontrado al jugador
            jugadorEncontrado = players.isEmpty();

            // Si no se ha encontrado al jugador, se muestra un mensaje y se vuelve a pedir
            // el nombre del jugador
            if (players.isEmpty()) {
                System.out.println("No se ha encontrado ningún jugador con ese nombre");
            } else {
                // Si se ha encontrado al jugador, se muestra un mensaje y se elige al jugador
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
                    System.out.println("Has seleccionado a " + players.get(jugadorElegido).getNick());
                } else {
                    idPlayer = players.get(0).getIdPlayer();
                    System.out.println("Se ha seleccionado a " + players.get(0).getNick());
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
                    System.out.println("Has seleccionado el juego " + games.get(juegoElegido).getNombre());
                } else {
                    idGame = games.get(0).getIdGame();
                    cosa = games.get(0).getNombre();
                    System.out.println("Se ha seleccionado " + games.get(0).getNombre());
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
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
            } finally {
                scanner.nextLine();
            }
        } while (opcion < 1 || opcion > 3);

        switch (opcion) {
            case 1:
                System.out.println();
                System.out.println("Introduce el nombre: ");
                nombre = scanner.nextLine();

                System.out.println();
                AccesoDatos.listarPlayers(1, nombre);
                break;
            case 2:
                System.out.println();
                System.out.println("Introduce el email: ");
                email = scanner.nextLine();

                System.out.println();
                AccesoDatos.listarPlayers(2, email);
                break;
            case 3:
                System.out.println();
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
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
            } finally {
                scanner.nextLine();
            }
        } while (opcion < 1 || opcion > 3);

        switch (opcion) {
            case 1:
                System.out.println();
                System.out.println("Introduce el nombre: ");
                nombre = scanner.nextLine();

                System.out.println();
                AccesoDatos.listarGames(1, nombre);
                break;
            case 2:
                System.out.println();
                System.out.println("Introduce las horas: ");

                do {
                    try {
                        horas = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                    } finally {
                        scanner.nextLine();
                    }
                } while (horas < 0);

                System.out.println();
                System.out.println("Introduce los minutos: ");

                do {
                    try {
                        minutos = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                    } finally {
                        scanner.nextLine();
                    }
                } while (minutos < 0 && minutos > 59);

                System.out.println();
                System.out.println("Introduce los segundos: ");

                do {
                    try {
                        segundos = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                    } finally {
                        scanner.nextLine();
                    }
                } while (segundos < 0 && segundos > 59);

                horasJugadas = (horas < 10 ? "0" + horas : horas) + ":" + (minutos < 10 ? "0" + minutos : minutos) + ":"
                        + (segundos < 10 ? "0" + segundos : segundos);

                System.out.println();
                AccesoDatos.listarGames(2, horasJugadas);
                break;
            case 3:
                System.out.println();
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
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
            } finally {
                scanner.nextLine();
            }
        } while (opcion < 1 || opcion > 5);

        switch (opcion) {
            case 1:
                System.out.println();
                System.out.println("Introduce el nombre del juego: ");
                nombreJuego = scanner.nextLine();
                System.out.println();
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
                                } catch (InputMismatchException e) {
                                    System.err.println("Introduzca un número");
                                } finally {
                                    scanner.nextLine();
                                }
                            } while (jugadorElegido < 0 || jugadorElegido >= players.size());
                            idPlayer = players.get(jugadorElegido).getIdPlayer();
                        } else {
                            idPlayer = players.get(0).getIdPlayer();
                        }
                    }
                } while (jugadorEncontrado);

                System.out.println();
                AccesoDatos.listarCompras(2, String.valueOf(idPlayer));

                break;
            case 3:
                do {
                    System.out.println();
                    System.out.println("Introduce el precio: ");
                    try {
                        precio = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                    } finally {
                        scanner.nextLine();
                    }
                } while (precio < 0);

                System.out.println();
                AccesoDatos.listarCompras(3, Double.toString(precio));
                break;
            case 4:
                do {
                    System.out.println();
                    System.out.println("Introduce el año: ");
                    try {
                        anyo = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                    } finally {
                        scanner.nextLine();
                    }
                } while (anyo < 0 || anyo > Year.now().getValue());

                do {
                    System.out.println();
                    System.out.println("Introduce el mes: ");
                    try {
                        mes = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                    } finally {
                        scanner.nextLine();
                    }
                } while (mes < 1 || mes > 12);

                boolean diaCorrecto = false;
                do {
                    System.out.println();
                    System.out.println("Introduce el dia: ");
                    try {
                        dia = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Introduzca un número");
                    } finally {
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
                System.out.println();
                AccesoDatos.listarCompras(4, date);

                break;
            case 5:
                System.out.println();
                AccesoDatos.listarCompras(0, "");
                break;
        }
    }

    // Método para modificar datos
    private static void modificar() {
        int opcion = -1;

        do {
            do {
                System.out.println();
                System.out.println("¿Qué quieres modificar?");
                System.out.println("1. Player");
                System.out.println("2. Game");
                System.out.println("3. Compra");
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
                    modificarPlayer();
                    break;
                case 2:
                    modificarGame();
                    break;
                case 3:
                    modificarCompra();
                    break;
                case 4:
                    System.out.println("Volviendo...");
                    break;
            }
        } while (opcion != 4);
    }

    // Método que modifica un player
    private static void modificarPlayer() {
        int opcion = -1;
        int idPlayer = -1;
        List<Player> players;
        String nombre = "";
        String password = "";
        String email = "";

        do {
            System.out.println();
            System.out.println("Introduce el nombre del jugador que quieres modificar:");
            nombre = scanner.nextLine();

            players = AccesoDatos.buscarPlayer(nombre);

            if (players.size() > 0) {
                // Si se ha encontrado al jugador, se muestra un mensaje y se elige al jugador
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
                    System.out.println("Has seleccionado a " + players.get(jugadorElegido).getNick());
                } else {
                    idPlayer = players.get(0).getIdPlayer();
                    System.out.println("Se ha seleccionado a " + players.get(0).getNick());
                }
            } else {
                System.out.println("No se ha encontrado ningún jugador con ese nombre.");
            }
        } while (idPlayer < 0);

        do {
            System.out.println();
            System.out.println("¿Qué quieres modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Email");
            System.out.println("3. Contraseña");
            System.out.println("4. Volver al menú");

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
            } finally {
                scanner.nextLine();
            }
        } while (opcion < 1 || opcion > 4);

        System.out.println();
        switch (opcion) {
            case 1:
                System.out.println("Introduce el nuevo nombre: ");
                nombre = scanner.nextLine();

                if (AccesoDatos.modificarPlayer(1, nombre, idPlayer)) {
                    System.out.println("Nombre modificado");
                } else {
                    System.out.println("No se ha podido modificar el nombre");
                }
                break;
            case 2:
                System.out.println("Introduce el nuevo email: ");
                email = scanner.nextLine();

                if (AccesoDatos.modificarPlayer(2, email, idPlayer)) {
                    System.out.println("Email modificado");
                } else {
                    System.out.println("No se ha podido modificar el email");
                }
                break;
            case 3:
                System.out.println("Introduce la nueva contraseña: ");
                password = scanner.nextLine();

                if (AccesoDatos.modificarPlayer(3, password, idPlayer)) {
                    System.out.println("Contraseña modificada");
                } else {
                    System.out.println("No se ha podido modificar la contraseña");
                }
                break;
            case 4:
                System.out.println("Volviendo...");
                break;
        }
    }

    // Método que modifica un game
    private static void modificarGame() {
        int opcion = -1;
        String nombre = "";
        int horas = -1;
        int minutos = -1;
        int segundos = -1;
        int idGame = -1;
        String horasJugadas = "";

        do {
            System.out.println();
            System.out.println("Introduce el nombre del juego que quieres modificar:");
            nombre = scanner.nextLine();

            List<Game> games = AccesoDatos.buscarGame(nombre);

            if (games.size() > 0) {
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
                } else {
                    idGame = games.get(0).getIdGame();
                    System.out.println("Se ha seleccionado " + games.get(0).getNombre());
                }
            } else {
                System.out.println("No se ha encontrado ningun juego con ese nombre.");
            }
        } while (idGame < 0);

        do {
            do {
                System.out.println();
                System.out.println("¿Qué quieres modificar?");
                System.out.println("1. Nombre");
                System.out.println("2. Tiempo Jugado");
                System.out.println("3. Volver al menú");

                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                } finally {
                    scanner.nextLine();
                }
            } while (opcion < 1 || opcion > 3);

            System.out.println();
            switch (opcion) {
                case 1:
                    System.out.println("Introduce el nuevo nombre:");
                    nombre = scanner.nextLine();

                    if (AccesoDatos.modificarGame(1, nombre, idGame)) {
                        System.out.println("Nombre modificado");
                    } else {
                        System.out.println("No se ha podido modificar el nombre");
                    }
                    break;
                case 2:
                    do {
                        System.out.println("Introduce las horas:");
                        try {
                            horas = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.err.println("Introduzca un número");
                        } finally {
                            scanner.nextLine();
                        }
                    } while (horas < 0);

                    System.out.println();
                    do {
                        System.out.println("Introduce los minutos:");
                        try {
                            minutos = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.err.println("Introduzca un número");
                        } finally {
                            scanner.nextLine();
                        }
                    } while (minutos < 0 || minutos > 59);

                    System.out.println();
                    do {
                        System.out.println("Introduce los segundos:");
                        try {
                            segundos = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.err.println("Introduzca un número");
                        } finally {
                            scanner.nextLine();
                        }
                    } while (segundos < 0 || segundos > 59);

                    horasJugadas = (horas > 10 ? horas : "0" + horas) + ":" + (minutos > 10 ? minutos : "0" + minutos)
                            + ":" + (segundos > 10 ? segundos : "0" + segundos);

                    if (AccesoDatos.modificarGame(2, horasJugadas, idGame)) {
                        System.out.println("Tiempo jugado modificado");
                    } else {
                        System.out.println("No se ha podido modificar el tiempo jugado");
                    }
                    break;
                case 3:
                    System.out.println("Volviendo...");
                    break;
            }
        } while (opcion != 3);
    }

    // Método que modifica una compra
    private static void modificarCompra() {
        int opcion = -1;
        int idCompra = -1;
        int idPlayer = -1;
        int idGame = -1;
        String cosa = "";
        double precio = -1;
        int dia = -1;
        int mes = -1;
        int anyo = -1;
        String date = "";

        do {
            System.out.println();
            System.out.println("Introduce el id de la compra:");
            try {
                idCompra = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
            } finally {
                scanner.nextLine();
            }
        } while (idCompra < 0);

        do {
            do {
                System.out.println();
                System.out.println("¿Qué quieres modificar?");
                System.out.println("1. Jugador");
                System.out.println("2. Juego");
                System.out.println("3. Precio");
                System.out.println("4. Fecha");
                System.out.println("5. Volver al menú");

                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                } finally {
                    scanner.nextLine();
                }
            } while (opcion < 1 || opcion > 5);

            System.out.println();
            switch (opcion) {
                case 1:
                    System.out.println("Introduce el nombre del jugador:");
                    String nombre = "";
                    nombre = scanner.nextLine();

                    List<Player> players = AccesoDatos.buscarPlayer(nombre);

                    if (players.isEmpty()) {
                        System.out.println("No se ha encontrado ningun jugador con ese nombre.");
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
                            System.out.println("Se ha seleccionado a " + players.get(0).getNick());
                        }
                    }

                    if (AccesoDatos.modificarCompra(1, String.valueOf(idPlayer), idCompra)) {
                        System.out.println("Jugador modificado");
                    } else {
                        System.out.println("No se ha podido modificar el jugador");
                    }
                    break;
                case 2:
                    System.out.println("Introduce el nombre del juego:");
                    String nombreJuego = "";
                    nombreJuego = scanner.nextLine();

                    List<Game> juegos = AccesoDatos.buscarGame(nombreJuego);

                    if (juegos.isEmpty()) {
                        System.out.println("No se ha encontrado ningun juego con ese nombre.");
                    } else {
                        if (juegos.size() > 1) {
                            System.out.println("Se han encontrado los siguientes juegos:");
                            System.out.printf("| %-10s | %-25s | %-15s %n", "Número", "Nombre", "Tiempo jugado");
                            System.out.println("-".repeat(80));
                            for (int i = 0; i < juegos.size(); i++) {
                                Game game = juegos.get(i);
                                System.out.printf("| %-10s | %-25s | %-15s %n", i, game.getNombre(),
                                        game.getTiempoJugado());
                                System.out.println("-".repeat(80));
                            }
                            int juegoElegido = -1;
                            do {
                                System.out.println();
                                System.out.println("Elige un juego de los mostrados arriba");
                                try {
                                    juegoElegido = scanner.nextInt();
                                } catch (InputMismatchException e) {
                                    System.err.println("Introduzca un número");
                                } finally {
                                    scanner.nextLine();
                                }
                            } while (juegoElegido < 0 || juegoElegido >= juegos.size());
                            idGame = juegos.get(juegoElegido).getIdGame();
                            cosa = juegos.get(juegoElegido).getNombre();
                        } else {
                            idGame = juegos.get(0).getIdGame();
                            cosa = juegos.get(0).getNombre();
                            System.out.println("Se ha seleccionado el juego " + juegos.get(0).getNombre());
                        }
                    }

                    if (AccesoDatos.modificarCompra(2, cosa + ":" + String.valueOf(idGame), idCompra)) {
                        System.out.println("Juego modificado");
                    } else {
                        System.out.println("No se ha podido modificar el juego");
                    }
                    break;
                case 3:
                    do {
                        System.out.println("Introduce el nuevo precio:");
                        try {
                            precio = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.err.println("Introduzca un número");
                        } finally {
                            scanner.nextLine();
                        }
                    } while (precio < 0);

                    if (AccesoDatos.modificarCompra(3, String.valueOf(precio), idCompra)) {
                        System.out.println("Precio modificado");
                    } else {
                        System.out.println("No se ha podido modificar el precio");
                    }
                    break;

                case 4:
                    do {
                        System.out.println();
                        System.out.println("Introduce el año: ");
                        try {
                            anyo = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.err.println("Introduzca un número");
                        } finally {
                            scanner.nextLine();
                        }
                    } while (anyo < 0 || anyo > Year.now().getValue());

                    do {
                        System.out.println();
                        System.out.println("Introduce el mes: ");
                        try {
                            mes = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.err.println("Introduzca un número");
                        } finally {
                            scanner.nextLine();
                        }
                    } while (mes < 1 || mes > 12);

                    boolean diaCorrecto = false;
                    do {
                        System.out.println();
                        System.out.println("Introduce el dia: ");
                        try {
                            dia = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.err.println("Introduzca un número");
                        } finally {
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

                    if (AccesoDatos.modificarCompra(4, date, idCompra)) {
                        System.out.println("Fecha modificada");
                    } else {
                        System.out.println("No se ha podido modificar la fecha");
                    }
                    break;

                case 5:
                    System.out.println("Volviendo...");
                    break;
            }
        } while (opcion != 5);
    }

    // Método que pregunta que quiere borrar
    private static void borrar() {
        int opcion = -1;

        do {
            do {
                System.out.println();
                System.out.println("¿Que quieres borrar?");
                System.out.println("1. Jugador");
                System.out.println("2. Juego");
                System.out.println("3. Compra");
                System.out.println("4. Volver al menú principal");

                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                } finally {
                    scanner.nextLine();
                }
            } while (opcion < 0 || opcion > 4);

            switch (opcion) {
                case 1:
                    borrarPlayer();
                    break;
                case 2:
                    borrarGame();
                    break;
                case 3:
                    borrarCompra();
                    break;
                case 4:
                    System.out.println("Volviendo...");
                    break;
            }
        } while (opcion != 4);
    }

    // Método que borrar un player
    private static void borrarPlayer() {
        int idPlayer = -1;
        String nombre = "";

        System.out.println();
        System.out.println("Escribe el nombre del player que quieras borrar: ");
        nombre = scanner.nextLine();

        List<Player> players = AccesoDatos.buscarPlayer(nombre);

        if (players.isEmpty()) {
            System.out.println("No se ha encontrado ningun player con ese nombre");
        } else {
            // Si se ha encontrado al jugador, se muestra un mensaje y se elige al jugador
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
                System.out.println("Has seleccionado a " + players.get(jugadorElegido).getNick());
            } else {
                idPlayer = players.get(0).getIdPlayer();
                System.out.println("Se ha seleccionado a " + players.get(0).getNick());
            }

            if (AccesoDatos.borrarPlayer(idPlayer)) {
                System.out.println("Player borrado");
            } else {
                System.out.println("No se ha podido borrar el player");
            }
        }
    }

    // Método que borra un game
    private static void borrarGame() {
        int idGame = -1;
        String nombre = "";

        System.out.println();
        System.out.println("Escribe el nombre del juego que quieras borrar: ");
        nombre = scanner.nextLine();

        List<Game> games = AccesoDatos.buscarGame(nombre);

        if (games.isEmpty()) {
            System.out.println("No se ha encontrado ningun juego con ese nombre");
        } else {
            // Si se ha encontrado el juego, se muestra un mensaje y se elige el juego
            if (games.size() > 1) {
                System.out.println("Se han encontrado los siguientes juegos:");
                System.out.printf("| %-10s | %-25s | %-15s %n", "Número", "Nombre", "Tiempo jugado");
                System.out.println("-".repeat(80));
                for (int i = 0; i < games.size(); i++) {
                    Game game = games.get(i);
                    System.out.printf("| %-10s | %-25s | %-15s %n", i, game.getNombre(), game.getTiempoJugado());
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
            } else {
                idGame = games.get(0).getIdGame();
                System.out.println("Se ha seleccionado a " + games.get(0).getNombre());
            }

            if (AccesoDatos.borrarGame(idGame)) {
                System.out.println("Game borrado");
            } else {
                System.out.println("No se ha podido borrar el game");
            }
        }
    }

    // Método que borra una compra
    private static void borrarCompra() {
        int idCompra = -1;

        do {
            System.out.println();
            System.out.println("Escribe el id de la compra que quieras borrar: ");
            try {
                idCompra = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un número");
            } finally {
                scanner.nextLine();
            }
        } while (idCompra < 0);

        if (AccesoDatos.borrarCompra(idCompra)) {
            System.out.println("Compra borrada");
        } else {
            System.out.println("No se ha podido borrar la compra");
        }
    }

    // Método que pregunta que tabla quiere borrar
    private static void borrarTablas() {
        int opcion = -1;

        do {
            do {
                System.out.println();
                System.out.println("¿Que quieres borrar?");
                System.out.println("1. Players");
                System.out.println("2. Games");
                System.out.println("3. Compras");
                System.out.println("4. Todas");
                System.out.println("5. Volver al menú principal");
                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Introduzca un número");
                } finally {
                    scanner.nextLine();
                }
            } while (opcion < 0 || opcion > 5);

            switch (opcion) {
                case 1:
                    if (AccesoDatos.borrarTabla("Player")) {
                        System.out.println("Tabla Players borrada");
                    } else {
                        System.out.println("No se ha podido borrar la tabla Players");
                    }
                    break;
                case 2:
                    if (AccesoDatos.borrarTabla("Game")) {
                        System.out.println("Tabla Games borrada");
                    } else {
                        System.out.println("No se ha podido borrar la tabla Games");
                    }
                    break;
                case 3:
                    if (AccesoDatos.borrarTabla("Compra")) {
                        System.out.println("Tabla Compras borrada");
                    } else {
                        System.out.println("No se ha podido borrar la tabla Compras");
                    }

                    break;
                case 4:
                    if (AccesoDatos.borrarTablas()) {
                        System.out.println("Tablas borradas");
                    } else {
                        System.out.println("No se han podido borrar las tablas");
                    }
                    break;
                case 5:
                    System.out.println("Volviendo...");
                    break;
            }
        } while (opcion != 5);
    }
}