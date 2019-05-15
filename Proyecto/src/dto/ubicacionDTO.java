/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
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
		this(u.id, u.nombre, u.descripcion, u.edificio, u.planta);
	}

	public ubicacionDTO clonar() {
		return new ubicacionDTO(this);
	}

	@Override
	public String toString() {
		return "ubicacionDTO [id=" + this.id + ", nombre=" + this.nombre + ", descripcion=" + this.descripcion
				+ ", edificio=" + this.edificio + ", planta=" + this.planta + "]";
	}

	public void visualizar() {
		System.out.println(this.toString());
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the edificio
	 */
	public String getEdificio() {
		return this.edificio;
	}

	/**
	 * @param edificio the edificio to set
	 */
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	/**
	 * @return the planta
	 */
	public String getPlanta() {
		return this.planta;
	}

	/**
	 * @param planta the planta to set
	 */
	public void setPlanta(String planta) {
		this.planta = planta;
	}



}
