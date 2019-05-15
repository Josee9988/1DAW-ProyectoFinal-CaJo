/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package dto;

public class usuarioDTO {

	private int id;
	private String user;
	private String password;
	private int rol;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String direccion;

	public usuarioDTO() {
		this.id = 0;
		this.user = "";
		this.password = "";
		this.rol = 0;
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	public usuarioDTO(String user) {
		this.id = 0;
		this.user = user;
		this.password = "";
		this.rol = 0;
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	public usuarioDTO(String user, int rol) {
		this.id = 0;
		this.user = user;
		this.password = "";
		this.rol = rol;
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	public usuarioDTO(String user, String password) {
		this.id = 0;
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
		this.id = 0;
		this.user = user;
		this.password = password;
		this.rol = rol;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public usuarioDTO(int id, String user, String password, int rol, String nombre, String apellidos, String telefono,
			String direccion) {
		this.id = id;
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
		return "usuarioDTO [id=" + this.id + ",user=" + this.user + ", password=" + this.password + ", rol=" + this.rol
				+ ", nombre=" + this.nombre + ", apellidos=" + this.apellidos + ", telefono=" + this.telefono
				+ ", direccion=" + this.direccion + "]";
	}

	// --- GETTERS && SETTERS --- //


	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return this.user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the rol
	 */
	public int getRol() {
		return this.rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(int rol) {
		this.rol = rol;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return this.apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return this.telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return this.direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



}
