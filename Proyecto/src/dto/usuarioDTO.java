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
		this(u.user,u.password,u.rol,u.nombre,u.apellidos,u.telefono,u.direccion);
	}
	
	public usuarioDTO clonar() {
		return new usuarioDTO(this);
	}
	
	public void visualizar() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		return "usuarioDTO [id=" + id + ",user=" + user + ", password=" + password + ", rol=" + rol + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", direccion=" + direccion + "]";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
