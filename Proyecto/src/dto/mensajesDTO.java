/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package dto;

import java.sql.Date;

public class mensajesDTO {

	private int id;
	private String asunto;
	private String cuerpo;
	private int incidencia;
	private Date fecha;
	private int emisor;
	private int receptor;

	public mensajesDTO() {
		this.id = 0;
		this.asunto = "";
		this.cuerpo = "";
		this.incidencia = 0;
		this.fecha = new Date(0);
		this.emisor = 0;
		this.receptor = 0;
	}

	/*
	 * public mensajesDTO(String asunto, String cuerpo, int incidencia, Date fecha,
	 * int emisor, int receptor) { this.id = 0; this.asunto = asunto; this.cuerpo =
	 * cuerpo; this.fecha = fecha; this.emisor = emisor; this.receptor = receptor; }
	 */

	/*
	 * public mensajesDTO(int id, String asunto, String cuerpo, int incidencia, Date
	 * fecha, int emisor, int receptor) { this.id = id; this.asunto = asunto;
	 * this.cuerpo = cuerpo; this.fecha = fecha; this.emisor = emisor; this.receptor
	 * = receptor; }
	 */

	public mensajesDTO(int id, String asunto, String cuerpo, int incidencia, Date fecha, int emisor, int receptor) {
		this.id = id;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.incidencia = incidencia;
		this.fecha = fecha;
		this.emisor = emisor;
		this.receptor = receptor;
	}

	public mensajesDTO(mensajesDTO m) {
		this(m.id, m.asunto, m.cuerpo, m.incidencia, m.fecha, m.emisor, m.receptor);
	}

	public mensajesDTO clonar() {
		return new mensajesDTO(this);
	}

	@Override
	public String toString() {
		return "mensajesDTO [id=" + this.id + ", asunto=" + this.asunto + ", cuerpo=" + this.cuerpo + ", incidencia="
				+ this.incidencia + ", fecha=" + this.fecha + ", emisor=" + this.emisor + ", receptor=" + this.receptor
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
	 * @return the asunto
	 */
	public String getAsunto() {
		return this.asunto;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the cuerpo
	 */
	public String getCuerpo() {
		return this.cuerpo;
	}

	/**
	 * @param cuerpo the cuerpo to set
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	/**
	 * @return the incidencia
	 */
	public int getIncidencia() {
		return this.incidencia;
	}

	/**
	 * @param incidencia the incidencia to set
	 */
	public void setIncidencia(int incidencia) {
		this.incidencia = incidencia;
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
	 * @return the emisor
	 */
	public int getEmisor() {
		return this.emisor;
	}

	/**
	 * @param emisor the emisor to set
	 */
	public void setEmisor(int emisor) {
		this.emisor = emisor;
	}

	/**
	 * @return the receptor
	 */
	public int getReceptor() {
		return this.receptor;
	}

	/**
	 * @param receptor the receptor to set
	 */
	public void setReceptor(int receptor) {
		this.receptor = receptor;
	}






}
