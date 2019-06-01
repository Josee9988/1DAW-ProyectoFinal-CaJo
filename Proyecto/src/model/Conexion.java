/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Conexion instance;
	private Connection connect;

	/**
	 * Conexion método que llama al método conectar el cual devuelve un objeto
	 * Connection el cual se le iguala a nuestra variable private Connection
	 * connect.
	 */
	private Conexion() {
		this.connect = this.conectar();
	}

	/**
	 * getInstance devuelve la conexión en forma de instancia de la base de datos
	 *
	 * @return instance devuelve la instancia de la conexión Connection
	 */
	public static Conexion getInstance() {
		if (Conexion.instance == null) {
			Conexion.instance = new Conexion();
		}
		return Conexion.instance;
	}

	/**
	 * cerrarConexion método que cierra la conexión con la base de datos
	 *
	 * @throws SQLException
	 */
	public void cerrarConexion() throws SQLException {
		this.connect.close();
	}

	/**
	 * conectar método en el cual se llama a la base de datos con el usuario
	 * contraseña y la dirección
	 *
	 * @return Conexion devuelve la conexión de la base de datos
	 */
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
