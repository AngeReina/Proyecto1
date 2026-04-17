package co.edu.unbosque.model.solicitud;

import co.edu.unbosque.model.base.ColaPrioridadPropia;
import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.enums.TipoSolicitud;

public class SolicitudServicio {
	
	private static int INDEX_PRIORITARIO = 0;
	private static int INDEX_NORMAL = 1;

    private ColaPrioridadPropia<Solicitud> colaPrioridadPropia;
    private ListaEnlazada<Solicitud> historicoAtendidas;
    private ListaEnlazada<Solicitud> todasLasSolicitudes;

    public SolicitudServicio() {
    	colaPrioridadPropia = new ColaPrioridadPropia<>(2);
    	colaPrioridadPropia.createPriority(INDEX_PRIORITARIO);
    	colaPrioridadPropia.createPriority(INDEX_NORMAL);
    	
        historicoAtendidas = new ListaEnlazada<>();
        todasLasSolicitudes = new ListaEnlazada<>();
    }

    public void registrarSolicitud(Solicitud solicitud) {
        todasLasSolicitudes.add(solicitud);
        int prioridad = INDEX_NORMAL;
        
        if (solicitud.getTipo() == TipoSolicitud.CRITICA) {
            prioridad = INDEX_PRIORITARIO;
        }
        
    }

    public Solicitud obtenerProximaAtencion() {
    	int prioridad = INDEX_NORMAL;
    	
    	if (colaPrioridadPropia.isEmptyByPriority(INDEX_PRIORITARIO)) {
    		prioridad = INDEX_PRIORITARIO;
    	}
    	
    	Solicitud res = colaPrioridadPropia.getBeginByPriority(prioridad);
        colaPrioridadPropia.dequeueByPriority(prioridad);

        return res;
    }

    public void marcarComoAtendida(Solicitud solicitud) {
        solicitud.marcarAtendida();
        historicoAtendidas.add(solicitud);
    }

    // Reporte

    public SolicitudDTO[] listarHistorialAtendidas() {
    	int tamanio = historicoAtendidas.count();
    	SolicitudDTO[] res = new SolicitudDTO[tamanio];
    	
    	for (int i = 0; i < tamanio; i++) {
    		Solicitud s = historicoAtendidas.getValueByPos(i);
    		SolicitudDTO dto = new SolicitudDTO();
    		dto.setClienteid(s.getCliente().getId());
    		dto.setCriterioCriticidad(s.getCriterioCriticidad().name());
    		dto.setDescripcionIncidente(s.getDescripcionIncidente());
    		dto.setEstado(s.getEstado().name());
    		dto.setFechaAsignacion(s.getFechaAsignacionStr());
    		dto.setFechaAtencion(s.getFechaAtencionStr());
    		dto.setFechaCreacion(s.getFechaCreacionStr());
    		res[i] = dto;
    	}
    	
    	return res;
    }


}
