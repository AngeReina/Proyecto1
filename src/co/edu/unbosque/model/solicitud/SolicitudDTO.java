package co.edu.unbosque.model.solicitud;

import java.util.UUID;

public class SolicitudDTO {
	
    private int id;
    private int clienteId;
    private String clienteTipo;
    private String descripcionIncidente;
    private String ubicacion;
    private String tipo;
    private String estado;
    private String criterioCriticidad;
    private String fechaCreacion;
    private String fechaAsignacion;
    private String fechaAtencion;
    private int tecnicoAsignado;
    private UUID unidadId;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	public String getClienteTipo() {
		return clienteTipo;
	}
	public void setClienteTipo(String clienteTipo) {
		this.clienteTipo = clienteTipo;
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
	public int getTecnicoAsignado() {
		return tecnicoAsignado;
	}
	public void setTecnicoAsignado(int tecnicoAsignado) {
		this.tecnicoAsignado = tecnicoAsignado;
	}
	
	public void setUnidadId(UUID unidadId) {
		this.unidadId = unidadId;
	}
	
	public UUID getUnidadId() {
		return unidadId;
	}

}
