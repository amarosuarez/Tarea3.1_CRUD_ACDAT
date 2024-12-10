import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Creamos la base de datos Players
        // AccesoDatos.crearTablaPlayers();

        // Insertamos los jugadores
        // AccesoDatos.insertarPlayers();

        // AccesoDatos.crearTablaGames();

        // AccesoDatos.insertarGames();

        // AccesoDatos.insertarCompras();

        int opcion = 0;

        opcion = menu();

        System.out.println("Opcion elegida: " + opcion);
    }

    private static int menu() {
        int opcion = 0;
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
}
