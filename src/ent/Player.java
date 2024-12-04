package ent;

/**
 * Clase Player
 */
public class Player {
    /**
     * Atributo que almacena el id del jugador
     */
    private int idPlayer;

    /**
     * Atributo que almacena el nick del jugador
     */
    private String nick;

    /**
     * Atributo que almacena la contraseña del jugador
     */
    private String password;

    /**
     * Atributo que almacena el email del jugador
     */
    private String email;

    /**
     * Constructor de la clase Player
     *
     * @param idPlayer
     * @param nick
     * @param password
     * @param email
     */
    public Player(int idPlayer, String nick, String password, String email) {
        // Comprueba que idPlayer es mayor que 0
        if (idPlayer > 0) {
            this.idPlayer = idPlayer;
        }

        // Comprueba que nick no es vacio
        if (nick != null && !nick.isEmpty()) {
            this.nick = nick;
        }

        // Comprueba que password no es vacio
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }

        // Comprueba que email no es vacio
        if (email != null && !email.isEmpty()) {
            this.email = email;
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
     * Devuelve el nick del jugador
     * @return nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * Establece el nick del jugador
     * @param nick nick
     */
    public void setNick(String nick) {
        if (nick != null && !nick.isEmpty()) {
            this.nick = nick;
        }
    }

    /**
     * Devuelve la contraseña del jugador
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del jugador
     * @param password password
     */
    public void setPassword(String password) {
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
    }

    /**
     * Devuelve el email del jugador
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del jugador
     * @param email email
     */
    public void setEmail(String email) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
    }
}
