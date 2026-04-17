package co.edu.unbosque.model.solicitud;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.edu.unbosque.model.cliente.Cliente;
import co.edu.unbosque.model.enums.CriterioCriticidad;
import co.edu.unbosque.model.enums.EstadoSolicitud;
import co.edu.unbosque.model.enums.TipoSolicitud;
import co.edu.unbosque.model.tecnico.Tecnico;

public class Solicitud {
	
    private String id;
    private Cliente cliente;
    private String descripcionIncidente;
    private String ubicacion;
    private TipoSolicitud tipo;
    private EstadoSolicitud estado;
    private CriterioCriticidad criterioCriticidad;
    private long fechaCreacion;
    private long fechaAsignacion;
    private long fechaAtencion;
    
    // Recursos asignados (inicialmente null)
    //private UnidadServicio unidadAsignada;
    private Tecnico tecnicoAsignado;

    public Solicitud(String id, Cliente cliente, String descripcionIncidente, 
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
    public void asignarRecursos(/**UnidadServicio unidad, **/Tecnico tecnico) {
        //this.unidadAsignada = unidad;
        this.tecnicoAsignado = tecnico;
        this.estado = EstadoSolicitud.ASIGNADA;
        this.fechaAsignacion = System.currentTimeMillis();
        // Marcar unidad y técnico como ocupados (se hará externamente)
    }

    public void marcarAtendida() {
        this.estado = EstadoSolicitud.ATENDIDA;
        this.fechaAtencion = System.currentTimeMillis();
    }

    // Getters y Setters
    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
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
    
    //public UnidadServicio getUnidadAsignada() { return unidadAsignada; }
    public Tecnico getTecnicoAsignado() { return tecnicoAsignado; }
    
    public void setEstado(EstadoSolicitud estado) { this.estado = estado; }
    //public void setUnidadAsignada(UnidadServicio unidad) { this.unidadAsignada = unidad; }
    public void setTecnicoAsignado(Tecnico tecnico) { this.tecnicoAsignado = tecnico; }

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
