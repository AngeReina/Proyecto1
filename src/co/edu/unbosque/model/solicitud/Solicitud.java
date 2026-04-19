package co.edu.unbosque.model.solicitud;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.edu.unbosque.model.cliente.ClienteDTO;
import co.edu.unbosque.model.enums.CriterioCriticidad;
import co.edu.unbosque.model.enums.EstadoSolicitud;
import co.edu.unbosque.model.enums.TipoSolicitud;
import co.edu.unbosque.model.tecnico.TecnicoDTO;
import co.edu.unbosque.model.unidad.UnidadDTO;

public class Solicitud {
	
    private String id;
    private ClienteDTO cliente;
    private String descripcionIncidente;
    private String ubicacion;
    private TipoSolicitud tipo;
    private EstadoSolicitud estado;
    private CriterioCriticidad criterioCriticidad;
    private long fechaCreacion;
    private long fechaAsignacion;
    private long fechaAtencion;
    
    // Recursos asignados (inicialmente null)
    private UnidadDTO unidadAsignada;
    private TecnicoDTO tecnicoAsignado;

    public Solicitud(String id, ClienteDTO cliente, String descripcionIncidente, 
                     String ubicacion, TipoSolicitud tipo, CriterioCriticidad criterioCriticidad) {
        this.id = id;
        this.cliente = cliente;
        this.descripcionIncidente = descripcionIncidente;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.criterioCriticidad = criterioCriticidad;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.fechaCreacion = System.currentTimeMillis();
    }

    // Métodos de negocio
    public void asignarRecursos(UnidadDTO unidad, TecnicoDTO tecnico) {
        //this.unidadAsignada = unidad;
        this.tecnicoAsignado = tecnico;
        this.estado = EstadoSolicitud.ASIGNADA;
        this.fechaAsignacion = System.currentTimeMillis();
    }

    public void marcarAtendida() {
        this.estado = EstadoSolicitud.ATENDIDA;
        this.fechaAtencion = System.currentTimeMillis();
    }

    // Getters y Setters
    public String getId() { return id; }
    public ClienteDTO getCliente() { return cliente; }
    public String getDescripcionIncidente() { return descripcionIncidente; }
    public String getUbicacion() { return ubicacion; }
    public TipoSolicitud getTipo() { return tipo; }
    public EstadoSolicitud getEstado() { return estado; }
    public CriterioCriticidad getCriterioCriticidad() { return criterioCriticidad; }
    public long getFechaCreacion() { return fechaCreacion; }
    public long getFechaAsignacion() { return fechaAsignacion; }
    public long getFechaAtencion() { return fechaAtencion; }
    
    public String getFechaCreacionStr() { return formatearFecha(fechaCreacion); }
    public String getFechaAsignacionStr() { return formatearFecha(fechaAsignacion); }
    public String getFechaAtencionStr() { return formatearFecha(fechaAtencion); }
    
    public UnidadDTO getUnidadAsignada() { return unidadAsignada; }
    public TecnicoDTO getTecnicoAsignado() { return tecnicoAsignado; }
    
    public void setEstado(EstadoSolicitud estado) { this.estado = estado; }
    public void setUnidadAsignada(UnidadDTO unidad) { this.unidadAsignada = unidad; }
    public void setTecnicoAsignado(TecnicoDTO tecnico) { this.tecnicoAsignado = tecnico; }

    private String formatearFecha(long timestamp) {
        Date fecha = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(fecha);
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Cliente: %s, Estado: %s)", 
                id, tipo, descripcionIncidente, cliente.getNombre(), estado);
    }
}
