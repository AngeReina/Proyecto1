package co.edu.unbosque.model.solicitud;


import java.util.UUID;

import co.edu.unbosque.model.base.ColaPrioridadPropia;
import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.enums.CriterioCriticidad;
import co.edu.unbosque.model.enums.EstadoSolicitud;
import co.edu.unbosque.model.enums.TIPO_CLIENTE;
import co.edu.unbosque.model.enums.TipoSolicitud;
import co.edu.unbosque.model.kit.Kit;
import co.edu.unbosque.persistence.DataMapper;
import co.edu.unbosque.persistence.SolicitudDAO;

public class SolicitudServicio {
	
	private static int INDEX_PRIORITARIO = 0;
	private static int INDEX_NORMAL = 1;

	private DataMapper mapper;
    private ColaPrioridadPropia<Solicitud> colaPrioridadPropia;
    private ListaEnlazada<Solicitud> historicoAtendidas;
    private ListaEnlazada<Solicitud> todasLasSolicitudes;
    
    private SolicitudDAO dao;

    public SolicitudServicio() {
    	this.dao = new SolicitudDAO();
    	this.mapper = new DataMapper();
    	colaPrioridadPropia = new ColaPrioridadPropia<>(2);
    	colaPrioridadPropia.createPriority(INDEX_PRIORITARIO);
    	colaPrioridadPropia.createPriority(INDEX_NORMAL);
    	
        historicoAtendidas = new ListaEnlazada<>();
    }
    
    public void init() {
    	ListaEnlazada<Solicitud> list = dao.getAll();
    	int count = list.count();
    	for (int i = count-1; i >= 0; i--) {
    		Solicitud s = list.getValueByPos(i);
    		if (s.getTipo().equals(TipoSolicitud.CRITICA)) {
    			colaPrioridadPropia.queueByPriority(INDEX_PRIORITARIO, s);
    		} else {
    			colaPrioridadPropia.queueByPriority(INDEX_NORMAL, s);
    		}
    	}
    }

    public void registrarSolicitud(SolicitudDTO dto) {
        int prioridad = INDEX_NORMAL;
        
        Solicitud solicitud = mapper.toSolicitud(dto);
        
        
        if (solicitud.getClienteTipo() == TIPO_CLIENTE.PREMIUN) {
        	solicitud.setTipo(TipoSolicitud.CRITICA);
            prioridad = INDEX_PRIORITARIO;
        }
        
        colaPrioridadPropia.queueByPriority(prioridad, solicitud);
        
        dao.create(solicitud);
        
    }

    public SolicitudDTO obtenerProximaAtencion() {
    	int prioridad = INDEX_NORMAL;
    	
    	if (!colaPrioridadPropia.isEmptyByPriority(INDEX_PRIORITARIO)) {
    		prioridad = INDEX_PRIORITARIO;
    	}
    	
    	Solicitud res = colaPrioridadPropia.getBeginByPriority(prioridad);
        colaPrioridadPropia.dequeueByPriority(prioridad);
        
        if (res == null) {
        	return null;
        } else {
        	 return mapper.toSolicitudDTO(res);
        }
    }
    
    public SolicitudDTO asignarProximaSolicitud(UUID unidadID, int tecnicoCode) {
    	int prioridad = INDEX_NORMAL;
    	
    	if (!colaPrioridadPropia.isEmptyByPriority(INDEX_PRIORITARIO)) {
    		prioridad = INDEX_PRIORITARIO;
    	}
    	
    	Solicitud res = colaPrioridadPropia.getBeginByPriority(prioridad);
        colaPrioridadPropia.dequeueByPriority(prioridad);
        
        if (res == null) {
        	return null;
        } else {
            res.asignarRecursos(unidadID, tecnicoCode);
            dao.update(res);
            
            return mapper.toSolicitudDTO(res);
        }
    }

    public void marcarComoAtendida(SolicitudDTO dto) {
    	Solicitud solicitud = mapper.toSolicitud(dto);
        solicitud.marcarAtendida();
        historicoAtendidas.add(solicitud);
        dao.update(solicitud);
    }

    // Reporte

    public SolicitudDTO[] listarHistorialAtendidas() {
    	int tamanio = historicoAtendidas.count();
    	SolicitudDTO[] res = new SolicitudDTO[tamanio];
    	
    	for (int i = 0; i < tamanio; i++) {
    		Solicitud s = historicoAtendidas.getValueByPos(i);
    		res[i] = mapper.toSolicitudDTO(s);
    	}
    	
    	return res;
    }

}
