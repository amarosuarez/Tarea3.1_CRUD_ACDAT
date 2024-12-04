import dal.AccesoDatos;

public class Principal {
    public static void main(String[] args) throws Exception {
        // Creamos la base de datos Players
        AccesoDatos.crearTablaPlayer();

        AccesoDatos.insertarPlayers();
    }
}
