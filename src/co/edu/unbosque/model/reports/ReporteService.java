package co.edu.unbosque.model.reports;

import co.edu.unbosque.persistence.ReporteDAO;

public class ReporteService {
	
	private ReporteDAO dao;
	
	public ReporteService() {
		this.dao = new ReporteDAO();
	}
	
	public void guardarReporte(ReporteDTO dto) {
		dao.create(new Reporte());
	}
}