package ent;

/**
 * Clase Game
 */
public class Game {
    /**
     * Atributo que almacena el id del juego
     */
    private int idGame;

    /**
     * Atributo que almacena el nombre del juego
     */
    private String nombre;

    /**
     * Atributo que almacena el tiempo jugado
     */
    private String tiempoJugado;

    /**
     * Constructor de la clase Game
     * @param idGame id
     * @param nombre nombre
     * @param tiempoJugado tiempo
     */
    public Game(int idGame, String nombre, String tiempoJugado) {
        if (idGame > 0) {
            this.idGame = idGame;
        }

        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        }

        if (tiempoJugado != null && !tiempoJugado.isEmpty()) {
            this.tiempoJugado = tiempoJugado;
        }
    }

    /**
     * Devuelve el id del juego
     * @return id
     */
    public int getIdGame() {
        return idGame;
    }

    /**
     * Establece el id del juego
     * @param idGame id
     */
    public void setIdGame(int idGame) {
        if (idGame > 0) {
            this.idGame = idGame;
        }
    }

    /**
     * Devuelve el nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre
     * @param nombre nombre
     */
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        }
    }

    /**
     * Devuelve el tiempo jugado
     * @return tiempo
     */
    public String getTiempoJugado() {
        return tiempoJugado;
    }

    /**
     * Establece el tiempo jugado
     * @param tiempoJugado tiempo
     */
    public void setTiempoJugado(String tiempoJugado) {
        if (tiempoJugado != null && !tiempoJugado.isEmpty()) {
            this.tiempoJugado = tiempoJugado;
        }
    }
}
