/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package dto;

import java.sql.Date;

public class incidenciaDTO {

	private int id;
	private String usuario;
	private String descripcion;
	private String elemento;
	private String ubicacion;
	private Date fecha;
	private String urgencia;
	private String categoria;
	private String materiales;

	public incidenciaDTO() {
		this.id = 0;
		this.usuario = "";
		this.descripcion = "";
		this.elemento = "";
		this.ubicacion = "";
		this.fecha = new Date(0);
		this.urgencia = "";
		this.categoria = "";
		this.materiales = "";
	}

	public incidenciaDTO(int id) {
		this.id = id;
		this.usuario = "";
		this.descripcion = "";
		this.elemento = "";
		this.ubicacion = "";
		this.fecha = new Date(0);
		this.urgencia = "";
		this.categoria = "";
		this.materiales = "";
	}

	public incidenciaDTO(int id, String urgencia) {
		this.id = id;
		this.usuario = "";
		this.descripcion = "";
		this.elemento = "";
		this.ubicacion = "";
		this.fecha = new Date(0);
		this.urgencia = urgencia;
		this.categoria = "";
		this.materiales = "";
	}

	public incidenciaDTO(int id, String usuario, String descripcion, String elemento, String ubicacion) {
		this.id = id;
		this.usuario = usuario;
		this.descripcion = descripcion;
		this.elemento = elemento;
		this.ubicacion = ubicacion;
		this.fecha = new Date(0);
		this.urgencia = "";
		this.categoria = "";
		this.materiales = "";
	}

	public incidenciaDTO(String usuario, String descripcion, String elemento, String ubicacion, Date fecha,
			String urgencia, String categoria, String materiales) {
		this.id = 0;
		this.usuario = usuario;
		this.descripcion = descripcion;
		this.elemento = elemento;
		this.ubicacion = ubicacion;
		this.fecha = fecha;
		this.urgencia = urgencia;
		this.categoria = categoria;
		this.materiales = materiales;
	}

	public incidenciaDTO(int id, String usuario, String descripcion, String elemento, String ubicacion, Date fecha,
			String urgencia, String categoria, String materiales) {
		this.id = id;
		this.usuario = usuario;
		this.descripcion = descripcion;
		this.elemento = elemento;
		this.ubicacion = ubicacion;
		this.fecha = fecha;
		this.urgencia = urgencia;
		this.categoria = categoria;
		this.materiales = materiales;
	}

	public incidenciaDTO(incidenciaDTO i) {
		this(i.id, i.usuario, i.descripcion, i.elemento, i.ubicacion, i.fecha, i.urgencia, i.categoria, i.materiales);
	}

	public incidenciaDTO clonar() {
		return new incidenciaDTO(this);
	}

	@Override
	public String toString() {
		return "incidenciaDTO [id=" + this.id + ",usuario=" + this.usuario + ", descripcion=" + this.descripcion
				+ ", elemento=" + this.elemento + ", ubicacion=" + this.ubicacion + ", fecha=" + this.fecha
				+ ", urgencia=" + this.urgencia + ", categoria=" + this.categoria + ", materiales=" + this.materiales
				+ "]";
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * @return the elemento
	 */
	public String getElemento() {
		return this.elemento;
	}

	/**
	 * @param elemento the elemento to set
	 */
	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return this.ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return this.fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the urgencia
	 */
	public String getUrgencia() {
		return this.urgencia;
	}

	/**
	 * @param urgencia the urgencia to set
	 */
	public void setUrgencia(String urgencia) {
		this.urgencia = urgencia;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return this.categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the materiales
	 */
	public String getMateriales() {
		return this.materiales;
	}

	/**
	 * @param materiales the materiales to set
	 */
	public void setMateriales(String materiales) {
		this.materiales = materiales;
	}

}
