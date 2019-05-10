package dto;

import java.sql.Date;

public class mensajesDTO {
	
	private int id;
	private String asunto;
	private String cuerpo;
	private int incidencia;
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
	
	public mensajesDTO(int id, String asunto, String cuerpo, int incidencia, Date fecha) {
		this.id = id;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.incidencia = incidencia;
		this.fecha = fecha;
	}

	public mensajesDTO(mensajesDTO m) {
		this(m.id,m.asunto,m.cuerpo,m.fecha);
	}
	
	public mensajesDTO clonar() {
		return new mensajesDTO(this);
	}

	@Override
	public String toString() {
		return "mensajesDTO [id=" + id + ", asunto=" + asunto + ", cuerpo=" + cuerpo + ", incidencia=" + incidencia + " fecha=" + fecha + "]";
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

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public int getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(int incidencia) {
		this.incidencia = incidencia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
