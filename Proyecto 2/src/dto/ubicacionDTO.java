package dto;

public class ubicacionDTO {
	
	private int id;
	private String nombre;
	private String descripcion;
	private String edificio;
	private String planta;
	
	public ubicacionDTO() {
		this.id = 0;
		this.nombre = "";
		this.descripcion = "";
		this.edificio = "";
		this.planta = "";
	}
	
	public ubicacionDTO(String nombre) {
		this.id = 0;
		this.nombre = nombre;
		this.descripcion = "";
		this.edificio = "";
		this.planta = "";
	}
	
	public ubicacionDTO(int id) {
		this.id = id;
		this.nombre = "";
		this.descripcion = "";
		this.edificio = "";
		this.planta = "";
	}
	
	public ubicacionDTO(String nombre, String descripcion, String edificio, String planta) {
		this.id = 0;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.edificio = edificio;
		this.planta = planta;
	}
	
	public ubicacionDTO(int id, String nombre, String descripcion, String edificio, String planta) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.edificio = edificio;
		this.planta = planta;
	}
	
	public ubicacionDTO(ubicacionDTO u) {
		this(u.id,u.nombre,u.descripcion,u.edificio,u.planta);
	}
	
	public ubicacionDTO clonar() {
		return new ubicacionDTO(this);
	}

	@Override
	public String toString() {
		return "ubicacionDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", edificio="
				+ edificio + ", planta=" + planta + "]";
	}
	
	public void visualizar() {
		System.out.println(this.toString());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

}
