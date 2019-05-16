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
	private String incidenciaS;
	private String emisorS;
	private String receptorS;

	/**
	 * mensajesDTO constructor default inicializa todas las variables
	 */
	public mensajesDTO() {
		this.id = 0;
		this.asunto = "";
		this.cuerpo = "";
		this.incidencia = 0;
		this.fecha = new Date(0);
		this.emisor = 0;
		this.receptor = 0;
		this.incidenciaS = "";
		this.emisorS = "";
		this.receptorS = "";
	}

	/**
	 * mensajesDTO constructor parametrizado que recibe todos los atributos
	 * originales
	 * 
	 * @param id         entero id
	 * @param asunto     String asunto del mensaje
	 * @param cuerpo     String cuerpo del mensajer
	 * @param incidencia int id de la incidencia referida
	 * @param fecha      Date fecha del mensaje que será la de creación
	 * @param emisor     int id del emisor
	 * @param receptor   int id del receptor
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

	/**
	 * mensajesDTO constructor parametrizado que recibe todos los atributos
	 * 
	 * @param id          entero id
	 * @param asunto      String asunto del mensaje
	 * @param cuerpo      String cuerpo del mensajer
	 * @param incidencia  int id de la incidencia referida
	 * @param fecha       Date fecha del mensaje que será la de creación
	 * @param emisor      int id del emisor
	 * @param receptor    int id del receptor
	 * @param incidenciaS String nombre de la incidencia
	 * @param emisorS     String nombre y apellidos del emisor
	 * @param receptorS   String nombre y apellidos del receptor
	 */
	public mensajesDTO(int id, String asunto, String cuerpo, int incidencia, Date fecha, int emisor, int receptor,
			String incidenciaS, String emisorS, String receptorS) {
		this.id = id;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.incidencia = incidencia;
		this.fecha = fecha;
		this.emisor = emisor;
		this.receptor = receptor;
		this.incidenciaS = incidenciaS;
		this.emisorS = emisorS;
		this.receptorS = receptorS;
	}

	/**
	 * mensajesDTO constructor copia
	 * 
	 * @param m objeto mensajesDTO
	 */
	public mensajesDTO(mensajesDTO m) {
		this(m.id, m.asunto, m.cuerpo, m.incidencia, m.fecha, m.emisor, m.receptor, m.incidenciaS, m.emisorS,
				m.receptorS);
	}

	/**
	 * mensajesDTO constructor clonar
	 * 
	 * @return mensajesDTO devuelve el propio mensajesDTO clonado
	 */
	public mensajesDTO clonar() {
		return new mensajesDTO(this);
	}

	@Override
	public String toString() {
		return "mensajesDTO [id=" + this.id + ", asunto=" + this.asunto + ", cuerpo=" + this.cuerpo + ", incidencia="
				+ this.incidencia + ", fecha=" + this.fecha + ", emisor=" + this.emisor + ", receptor=" + this.receptor
				+ ", incidenciaS=" + this.incidenciaS + ", emisorS=" + this.emisorS + ", receptorS=" + this.receptorS
				+ "]";
	}

	/**
	 * visualizar método que saca por pantalla el toString
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

	/**
	 * @return the incidenciaS
	 */
	public String getIncidenciaS() {
		return this.incidenciaS;
	}

	/**
	 * @param incidenciaS the incidenciaS to set
	 */
	public void setIncidenciaS(String incidenciaS) {
		this.incidenciaS = incidenciaS;
	}

	/**
	 * @return the emisorS
	 */
	public String getEmisorS() {
		return this.emisorS;
	}

	/**
	 * @param emisorS the emisorS to set
	 */
	public void setEmisorS(String emisorS) {
		this.emisorS = emisorS;
	}

	/**
	 * @return the receptorS
	 */
	public String getReceptorS() {
		return this.receptorS;
	}

	/**
	 * @param receptorS the receptorS to set
	 */
	public void setReceptorS(String receptorS) {
		this.receptorS = receptorS;
	}

}
