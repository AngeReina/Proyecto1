package co.edu.unbosque.model.reports;

import co.edu.unbosque.model.solicitud.SolicitudDTO;

public class ReporteDTO {
	
	private SolicitudDTO[] solicitudes;
	
	public void setSolicitudes(SolicitudDTO[] solicitudes) {
		this.solicitudes = solicitudes;
	}
	
	public SolicitudDTO[] getSolicitudes() {
		return solicitudes;
	}
}
