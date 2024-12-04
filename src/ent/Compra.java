package ent;

import java.util.Date;

/**
 * Clase Compra
 */
public class Compra {
    /**
     * Atributo que almacena el id de la compra
     */
    private int idCompra;

    /**
     * Atributo que almacena el id del jugador
     */
    private int idPlayer;

    /**
     * Atributo que almacena el id del juego
     */
    private int idGame;
    
    /**
     * Atributo que almacena la cosa
     */
    private String cosa;
    
    /**
     * Atributo que almacena el precio
     */
    private double precio;

    /**
     * Atributo que almacena la fecha
     */
    private Date fecha;

    /**
     * Constructor de la clase Compra
     * @param idCompra id
     * @param idPlayer id
     * @param idGame id
     * @param cosa cosa
     * @param precio precio
     * @param fecha fecha
     */
    public Compra(int idCompra, int idPlayer, int idGame, String cosa, double precio, Date fecha) {
        if (idCompra > 0) {
            this.idCompra = idCompra;
        }

        if (idPlayer > 0) {
            this.idPlayer = idPlayer;
        }

        if (idGame > 0) {
            this.idGame = idGame;
        }

        if (cosa != null && !cosa.isEmpty()) {
            this.cosa = cosa;
        }

        if (precio > 0) {
            this.precio = precio;
        }

        if (fecha != null) {
            this.fecha = fecha;
        }
    }

    /**
     * Devuelve el id de la compra
     * @return id
     */
    public int getIdCompra() {
        return idCompra;
    }

    /**
     * Establece el id de la compra
     * @param idCompra id
     */
    public void setIdCompra(int idCompra) {
        if (idCompra > 0) {
            this.idCompra = idCompra;
        }
    }

    /**
     * Devuelve el id del jugador
     * @return id
     */
    public int getIdPlayer() {
        return idPlayer;
    }

    /**
     * Establece el id del jugador
     * @param idPlayer id
     */
    public void setIdPlayer(int idPlayer) {
        if (idPlayer > 0) {
            this.idPlayer = idPlayer;
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
     * Devuelve la cosa
     * @return cosa
     */
    public String getCosa() {
        return cosa;
    }

    /**
     * Establece la cosa
     * @param cosa cosa
     */
    public void setCosa(String cosa) {
        if (cosa != null && !cosa.isEmpty()) {
            this.cosa = cosa;
        }
    }

    /**
     * Devuelve el precio
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio
     * @param precio precio
     */
    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        }
    }

    /**
     * Devuelve la fecha
     * @return fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha
     * @param fecha fecha
     */
    public void setFecha(Date fecha) {
        if (fecha != null) {
            this.fecha = fecha;
        }
    }
}
