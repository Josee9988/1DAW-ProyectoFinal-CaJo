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
	private String rolS;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String direccion;

	/**
	 * usuarioDTO constructor default
	 */
	public usuarioDTO() {
		this.id = 0;
		this.user = "";
		this.password = "";
		this.rol = 0;
		this.rolS = "";
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	/**
	 * usuarioDTO constructor parametrizado utilizado en mensajes, que contiene la
	 * id y el rol
	 * 
	 * @param id  entero de la id recibida
	 * @param rol rol del rol recibido
	 */
	public usuarioDTO(int id, int rol) {
		this.id = id;
		this.rol = rol;
	}

	/**
	 * usuarioDTO constructor parametrizado con el usuario y el rol
	 *
	 * @param user nombre de usuario del usuario
	 * @param rol  rol del usuario
	 */
	public usuarioDTO(String user, int rol) {
		this.id = 0;
		this.user = user;
		this.password = "";
		this.rol = rol;
		this.rolS = "";
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	/**
	 * usuarioDTO constructor parametrizado con el usuario y la contraseña
	 *
	 * @param user     nombre de usuario del usuario
	 * @param password contraseña del usuario
	 */
	public usuarioDTO(String user, String password) {
		this.id = 0;
		this.user = user;
		this.password = password;
		this.rol = 0;
		this.rolS = "";
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.direccion = "";
	}

	/**
	 * usuarioDTO constructor parametrizado con el String del rol traducido
	 *
	 * @param user      nombre de usuario del usuario
	 * @param password  contraseña del usuario
	 * @param rol       rol del usuario
	 * @param rolS      traducción del rol, lo que significa esa id del rol
	 * @param nombre    nombre del usuario
	 * @param apellidos apellidos del usuario
	 * @param telefono  telefono del usuario
	 * @param direccion dirección del usuario
	 */
	public usuarioDTO(String user, String password, int rol, String rolS, String nombre, String apellidos,
			String telefono, String direccion) {
		this.id = 0;
		this.user = user;
		this.password = password;
		this.rol = rol;
		this.rolS = rolS;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	/**
	 * usuarioDTO constructor parametrizado
	 *
	 * @param id        id del usuario
	 * @param user      nombre de usuario del usuario
	 * @param password  contraseña del usuario
	 * @param rol       rol del usuario
	 * @param nombre    nombre del usuario
	 * @param apellidos apellidos del usuario
	 * @param telefono  telefono del usuario
	 * @param direccion dirección del usuario
	 */
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

	/**
	 * usuarioDTO constructor clonar
	 *
	 * @param u usuarioDTO a clonar
	 */
	public usuarioDTO(usuarioDTO u) {
		this(u.user, u.password, u.rol, u.rolS, u.nombre, u.apellidos, u.telefono, u.direccion);
	}

	/**
	 * clonar constructor clonar
	 *
	 * @return usuarioDTO clonado
	 */
	public usuarioDTO clonar() {
		return new usuarioDTO(this);
	}

	/**
	 * visualizar método que imprime por pantalla el toString
	 */
	public void visualizar() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "usuarioDTO [id=" + this.id + ", user=" + this.user + ", password=" + this.password + ", rol=" + this.rol
				+ ", rolS=" + this.rolS + ", nombre=" + this.nombre + ", apellidos=" + this.apellidos + ", telefono="
				+ this.telefono + ", direccion=" + this.direccion + "]";
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

	/**
	 * @return the rolS
	 */
	public String getRolS() {
		return this.rolS;
	}

	/**
	 * @param rolS the rolS to set
	 */
	public void setRolS(String rolS) {
		this.rolS = rolS;
	}

}
