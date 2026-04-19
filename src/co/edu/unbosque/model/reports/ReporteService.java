package co.edu.unbosque.model.reports;

import co.edu.unbosque.model.solicitud.SolicitudDTO;
import co.edu.unbosque.persistence.DataMapper;
import co.edu.unbosque.persistence.ReporteDAO;

public class ReporteService {
	
	private ReporteDAO dao;
	private DataMapper dataMapper;
	
	public ReporteService() {
		this.dao = new ReporteDAO();
		this.dataMapper = new DataMapper();
	}
	
	public void generarReporteSolicitudes(ReporteDTO dto) {
		dao.deleteFile();
		
		for (SolicitudDTO solicitudDTO : dto.getSolicitudes()) {
			String field = dataMapper.solicitudDtoToLine(solicitudDTO);
			dao.create(new Reporte(field));
		}
	}
}