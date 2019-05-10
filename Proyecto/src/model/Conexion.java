/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Conexion instance;
	private Connection connect;

	private Conexion() {
		this.connect = this.conectar();
	}

	public static Conexion getInstance() {
		if (instance == null) {
			instance = new Conexion();
		}
		return instance;
	}

	public void cerrarConexion() throws SQLException {
		this.connect.close();
	}

	public Connection conectar() {
		Connection Conexion = null;
		try {
			Conexion = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/m_proyectodaw?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "1234");
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return Conexion;
	}

}
