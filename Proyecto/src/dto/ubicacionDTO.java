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

	/**
	 * ubicacionDTO constructor default
	 */
	public ubicacionDTO() {
		this.id = 0;
		this.nombre = "";
		this.descripcion = "";
		this.edificio = "";
		this.planta = "";
	}

	/**
	 * ubicacionDTO constructor parametrizado con String nombre
	 * @param nombre nombre de la ubicación
	 */
	public ubicacionDTO(String nombre) {
		this.id = 0;
		this.nombre = nombre;
		this.descripcion = "";
		this.edificio = "";
		this.planta = "";
	}

	/**
	 * ubicacionDTO constructor parametrizado con todos los valores
	 * @param id id de la ubicacion
	 * @param nombre de la ubicación
	 * @param descripcion de la ubicación
	 * @param edificio de la ubicación
	 * @param planta de la ubicación
	 */
	public ubicacionDTO(int id, String nombre, String descripcion, String edificio, String planta) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.edificio = edificio;
		this.planta = planta;
	}

	/**
	 * ubicacionDTO constructor copia
	 * @param u ubicacionDTO
	 */
	public ubicacionDTO(ubicacionDTO u) {
		this(u.id, u.nombre, u.descripcion, u.edificio, u.planta);
	}

	/**
	 * ubicacionDTO constructor clonar
	 * @return ubicacionDTO clonado
	 */
	public ubicacionDTO clonar() {
		return new ubicacionDTO(this);
	}

	@Override
	public String toString() {
		return "ubicacionDTO [id=" + this.id + ", nombre=" + this.nombre + ", descripcion=" + this.descripcion
				+ ", edificio=" + this.edificio + ", planta=" + this.planta + "]";
	}

	/**
	 * visualizar método que imprime por pantalla el toString
	 */
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
