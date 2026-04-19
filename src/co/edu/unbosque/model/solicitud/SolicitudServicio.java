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
	
	private static final int INDEX_PRIORITARIO_CRITICO = 0;
	private static final int INDEX_PRIORITARIO = 1;
	private static final int INDEX_NORMAL_CRITICO = 2;
	private static final int INDEX_NORMAL = 3;
	private static final UUID UUID_DEFAULT = UUID.randomUUID();

	private DataMapper mapper;
    private ColaPrioridadPropia<Solicitud> colaPrioridadPropia;
    private ListaEnlazada<Solicitud> historicoAtendidas;
    private ListaEnlazada<Solicitud> todasLasSolicitudes;
    
    private SolicitudDAO dao;

    public SolicitudServicio() {
    	this.dao = new SolicitudDAO();
    	this.mapper = new DataMapper();
    	colaPrioridadPropia = new ColaPrioridadPropia<>(4);
    	colaPrioridadPropia.createPriority(INDEX_PRIORITARIO_CRITICO);
    	colaPrioridadPropia.createPriority(INDEX_PRIORITARIO);
    	colaPrioridadPropia.createPriority(INDEX_NORMAL_CRITICO);
    	colaPrioridadPropia.createPriority(INDEX_NORMAL);
    	
        historicoAtendidas = new ListaEnlazada<>();
    }
    
    public void init() {
    	ListaEnlazada<Solicitud> list = dao.getAll();
    	int count = list.count();
    	for (int i = count-1; i >= 0; i--) {
    		Solicitud s = list.getValueByPos(i);
    		if (s.getEstado().equals(EstadoSolicitud.PENDIENTE)) {
    			colaPrioridadPropia.queueByPriority(getPrioridad(s), s);
    		} else if (s.getEstado().equals(EstadoSolicitud.ATENDIDA)) {
    			historicoAtendidas.add(s);
    		}
    	}
    }

    public void registrarSolicitud(SolicitudDTO dto) {
        int prioridad = INDEX_NORMAL;
        
        dto.setId((int)(Math.random() * 100000));
        dto.setTipo(TipoSolicitud.ORDINARIA.name());
        dto.setEstado(EstadoSolicitud.PENDIENTE.name());
        dto.setUnidadId(UUID_DEFAULT);
        
        Solicitud solicitud = mapper.toSolicitud(dto);
        
        
        if (solicitud.getClienteTipo() == TIPO_CLIENTE.PREMIUN || solicitud.getCriterioCriticidad() != CriterioCriticidad.NORMAL) {
        	solicitud.setTipo(TipoSolicitud.CRITICA);
            prioridad = getPrioridad(solicitud);
        }
        
        colaPrioridadPropia.queueByPriority(prioridad, solicitud);
        
        dao.create(solicitud);
        
    }

    public SolicitudDTO obtenerProximaAtencion() {
    	int prioridad = checkPrioridadDisponible();
    	
    	if (!colaPrioridadPropia.isEmptyByPriority(prioridad)) {
        	Solicitud res = colaPrioridadPropia.getBeginByPriority(prioridad);
            
            if (res == null) {
            	return null;
            } else {
            	 return mapper.toSolicitudDTO(res);
            }	
    	} else {
    		return null;
    	}
    }
    
    public SolicitudDTO asignarProximaSolicitud(UUID unidadID, int tecnicoCode) {
    	int prioridad = checkPrioridadDisponible();
    
    	
    	if (!colaPrioridadPropia.isEmptyByPriority(prioridad)) {
        	Solicitud res = colaPrioridadPropia.getBeginByPriority(prioridad);
            colaPrioridadPropia.dequeueByPriority(prioridad);
            
            if (res == null) {
            	return null;
            } else {
                res.asignarRecursos(unidadID, tecnicoCode);
                dao.update(res);
                
                return mapper.toSolicitudDTO(res);
            }
    	} else {
    		return null;
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
    
    private int getPrioridad(Solicitud s) {
    	if (s.getClienteTipo() == null || s.getCriterioCriticidad() == null) {
    		return INDEX_NORMAL;
    	}
		if (s.getClienteTipo().equals(TIPO_CLIENTE.PREMIUN)) {
			if (!s.getCriterioCriticidad().equals(CriterioCriticidad.NORMAL)) {
				return INDEX_PRIORITARIO_CRITICO;
			} else {
				return INDEX_PRIORITARIO;
			}
		} else {
			if (!s.getCriterioCriticidad().equals(CriterioCriticidad.NORMAL)) {
				return INDEX_NORMAL_CRITICO;
			} else {
				return INDEX_NORMAL;
			}
		}
    }
    
    private int checkPrioridadDisponible() {
    	int prioridad = INDEX_NORMAL;
    	
    	if (!colaPrioridadPropia.isEmptyByPriority(INDEX_PRIORITARIO_CRITICO)) {
    		prioridad = INDEX_PRIORITARIO_CRITICO;
    	} else if (!colaPrioridadPropia.isEmptyByPriority(INDEX_PRIORITARIO)) {
    		prioridad = INDEX_PRIORITARIO;
    	} else if (!colaPrioridadPropia.isEmptyByPriority(INDEX_NORMAL_CRITICO)) {
    		prioridad = INDEX_NORMAL_CRITICO;
    	}
    	
    	return prioridad;
    }

}
