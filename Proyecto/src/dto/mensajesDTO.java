/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package dto;

import java.sql.Date;

public class mensajesDTO {

	private int id;
	private String asunto;
	private String cuerpo;
	private Date fecha;

	public mensajesDTO() {
		this.id = 0;
		this.asunto = "";
		this.cuerpo = "";
		this.fecha = new Date(0);
	}

	public mensajesDTO(String asunto, String cuerpo, Date fecha) {
		this.id = 0;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.fecha = fecha;
	}

	public mensajesDTO(int id, String asunto, String cuerpo, Date fecha) {
		this.id = id;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.fecha = fecha;
	}

	public mensajesDTO(mensajesDTO m) {
		this(m.id, m.asunto, m.cuerpo, m.fecha);
	}

	public mensajesDTO clonar() {
		return new mensajesDTO(this);
	}

	@Override
	public String toString() {
		return "mensajesDTO [id=" + this.id + ", asunto=" + this.asunto + ", cuerpo=" + this.cuerpo + ", fecha="
				+ this.fecha + "]";
	}

	public void visualizar() {
		System.out.println(this.toString());
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return this.cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
