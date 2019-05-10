/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package dto;

public class usuarioDTO {
	private String user;
	private String password;
	private int rol;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String direccion;

	public usuarioDTO() {
		this.user = "";
		this.password = "";
		this.rol = 0;
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	public usuarioDTO(String user) {
		this.user = user;
		this.password = "";
		this.rol = 0;
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	public usuarioDTO(String user, int rol) {
		this.user = user;
		this.password = "";
		this.rol = rol;
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	public usuarioDTO(String user, String password) {
		this.user = user;
		this.password = password;
		this.rol = 0;
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	public usuarioDTO(String user, String password, int rol, String nombre, String apellidos, String telefono,
			String direccion) {
		this.user = user;
		this.password = password;
		this.rol = rol;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public usuarioDTO(usuarioDTO u) {
		this(u.user, u.password, u.rol, u.nombre, u.apellidos, u.telefono, u.direccion);
	}

	public usuarioDTO clonar() {
		return new usuarioDTO(this);
	}

	public void visualizar() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "usuarioDTO [user=" + this.user + ", password=" + this.password + ", rol=" + this.rol + ", nombre="
				+ this.nombre + ", apellidos=" + this.apellidos + ", telefono=" + this.telefono + ", direccion="
				+ this.direccion + "]";
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRol() {
		return this.rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
