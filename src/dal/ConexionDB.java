package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {

    /**
     * Metodo para conectar con la base de datos
     *
     * @return Connection
     */
    public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection ("jdbc:mysql://dns11036.phdns11.es:3306?",
				"asuarez", "12345" );
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
    
    /**
     * Metodo para usar la base de datos
     */
    public static void useDB() {
        Connection conector = null;
        Statement stmt = null;
        
        try {
            conector = connect();
            stmt = conector.createStatement();
            stmt.executeUpdate("USE ad2425_asuarez;");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // try {
            //     stmt.close();
            //     conector.close();
            // } catch (SQLException e) {
            //     e.printStackTrace();
            // }
        }
    }
    
}
