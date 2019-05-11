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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getElemento() {
		return this.elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUrgencia() {
		return this.urgencia;
	}

	public void setUrgencia(String urgencia) {
		this.urgencia = urgencia;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMateriales() {
		return this.materiales;
	}

	public void setMateriales(String materiales) {
		this.materiales = materiales;
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

}
