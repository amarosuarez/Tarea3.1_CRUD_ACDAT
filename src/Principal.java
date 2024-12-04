import java.util.List;

import dal.AccesoDatos;
import ent.Compra;

public class Principal {
    public static void main(String[] args) throws Exception {
        // Creamos la base de datos Players
        // AccesoDatos.crearTablaPlayers();

        // Insertamos los jugadores
        // AccesoDatos.insertarPlayers();

        // AccesoDatos.crearTablaGames();

        // AccesoDatos.insertarGames();

        List<Compra> compras = AccesoDatos.getCompras();

        for (Compra compra : compras) {
            System.out.println(compra.getCosa());
        }
    }
}
