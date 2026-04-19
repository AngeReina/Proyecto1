package co.edu.unbosque.model.solicitud;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import co.edu.unbosque.model.enums.CriterioCriticidad;
import co.edu.unbosque.model.enums.EstadoSolicitud;
import co.edu.unbosque.model.enums.TIPO_CLIENTE;
import co.edu.unbosque.model.enums.TipoSolicitud;

public class Solicitud {
	
    private int id;
    private int clienteId;
    private TIPO_CLIENTE clienteTipo;
    private String descripcionIncidente;
    private String ubicacion;
    private TipoSolicitud tipo;
    private EstadoSolicitud estado;
    private CriterioCriticidad criterioCriticidad;
    private long fechaCreacion;
    private long fechaAsignacion;
    private long fechaAtencion;
    
    
    // Recursos asignados (inicialmente null)
    private UUID unidadId;
    private int tecnicoId;

    public Solicitud(int id, int clienteId, String descripcionIncidente, 
                     String ubicacion, TipoSolicitud tipo, CriterioCriticidad criterioCriticidad) {
        this.id = id;
        this.clienteId = clienteId;
        this.descripcionIncidente = descripcionIncidente;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.criterioCriticidad = criterioCriticidad;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.fechaCreacion = System.currentTimeMillis();
    }

    // Métodos de negocio
    public void asignarRecursos(UUID unidadId, int tecnico) {
        this.unidadId = unidadId;
        this.tecnicoId = tecnico;
        this.estado = EstadoSolicitud.ASIGNADA;
        this.fechaAsignacion = System.currentTimeMillis();
    }

    public void marcarAtendida() {
        this.estado = EstadoSolicitud.ATENDIDA;
        this.fechaAtencion = System.currentTimeMillis();
    }
    

    // Getters y Setters

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

	public TIPO_CLIENTE getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(TIPO_CLIENTE clienteTipo) {
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

	public TipoSolicitud getTipo() {
		return tipo;
	}

	public void setTipo(TipoSolicitud tipo) {
		this.tipo = tipo;
	}

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}

	public CriterioCriticidad getCriterioCriticidad() {
		return criterioCriticidad;
	}

	public void setCriterioCriticidad(CriterioCriticidad criterioCriticidad) {
		this.criterioCriticidad = criterioCriticidad;
	}

	public long getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(long fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public long getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(long fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public long getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(long fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public UUID getUnidadId() {
		return unidadId;
	}

	public void setUnidadId(UUID unidadID) {
		this.unidadId = unidadID;
	}

	public int getTecnicoId() {
		return tecnicoId;
	}

	public void setTecnicoId(int tecnicoId) {
		this.tecnicoId = tecnicoId;
	}
    
}
