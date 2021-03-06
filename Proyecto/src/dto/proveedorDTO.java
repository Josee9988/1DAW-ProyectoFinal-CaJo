/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package dto;

public class proveedorDTO {

	private int id;
	private String nombre;
	private String contacto;
	private String direccion;
	private int valoracion;

	/**
	 * proveedorDTO constructor default
	 */
	public proveedorDTO() {
		this.id = 0;
		this.nombre = "";
		this.contacto = "";
		this.direccion = "";
		this.valoracion = 0;
	}

	/**
	 * proveedorDTO constructor parametrizado con todos los valores
	 * 
	 * @param id         id del proveedorDTO
	 * @param nombre     nombre del proveedor
	 * @param contacto   contacto del proveedor
	 * @param direccion  dirección del proveedor
	 * @param valoracion del proveedor
	 */
	public proveedorDTO(int id, String nombre, String contacto, String direccion, int valoracion) {
		this.id = id;
		this.nombre = nombre;
		this.contacto = contacto;
		this.direccion = direccion;
		this.valoracion = valoracion;
	}

	/**
	 * proveedorDTO constructor copia
	 * 
	 * @param p proveedorDTO
	 */
	public proveedorDTO(proveedorDTO p) {
		this(p.id, p.nombre, p.contacto, p.direccion, p.valoracion);
	}

	/**
	 * proveedorDTO constructor clonar
	 * 
	 * @return proveedorDTO clonado
	 */
	public proveedorDTO clonar() {
		return new proveedorDTO(this);
	}

	@Override
	public String toString() {
		return "proveedorDTO [id=" + this.id + ", nombre=" + this.nombre + ", contacto=" + this.contacto
				+ ", direccion=" + this.direccion + ", valoracion=" + this.valoracion + "]";
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
	 * @return the contacto
	 */
	public String getContacto() {
		return this.contacto;
	}

	/**
	 * @param contacto the contacto to set
	 */
	public void setContacto(String contacto) {
		this.contacto = contacto;
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
	 * @return the valoracion
	 */
	public int getValoracion() {
		return this.valoracion;
	}

	/**
	 * @param valoracion the valoracion to set
	 */
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

}
