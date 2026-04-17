package co.edu.unbosque.model.solicitud;

public class SolicitudDTO {
	
    private String id;
    private String clienteid;
    private String descripcionIncidente;
    private String ubicacion;
    private String tipo;
    private String estado;
    private String criterioCriticidad;
    private String fechaCreacion;
    private String fechaAsignacion;
    private String fechaAtencion;
    private String tecnicoAsignado;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClienteid() {
		return clienteid;
	}
	public void setClienteid(String clienteid) {
		this.clienteid = clienteid;
	}
	public String getDescripcionIncidente() {
		return descripcionIncidente;
	}
	public void setDescripcionIncidente(String descripcionIncidente) {
		this.descripcionIncidente = descripcionIncidente;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCriterioCriticidad() {
		return criterioCriticidad;
	}
	public void setCriterioCriticidad(String criterioCriticidad) {
		this.criterioCriticidad = criterioCriticidad;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaAsignacion() {
		return fechaAsignacion;
	}
	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	public String getFechaAtencion() {
		return fechaAtencion;
	}
	public void setFechaAtencion(String fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
	public String getTecnicoAsignado() {
		return tecnicoAsignado;
	}
	public void setTecnicoAsignado(String tecnicoAsignado) {
		this.tecnicoAsignado = tecnicoAsignado;
	}
    
    

}
