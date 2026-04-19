package co.edu.unbosque.persistence;

import co.edu.unbosque.model.reports.Reporte;

public class ReporteDAO extends AbstractFileDAO<Reporte, Integer> {

	public ReporteDAO() {
		super("report/reporte.csv");
		loadFromFile();
	}

	@Override
	protected String objectToLine(Reporte obj) {
		// TODO Auto-generated method stub
		return obj.getData();
	}

	@Override
	protected Reporte lineToObject(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer getId(Reporte obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean compareId(Reporte obj, Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
