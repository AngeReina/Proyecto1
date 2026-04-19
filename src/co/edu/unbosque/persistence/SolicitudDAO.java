package co.edu.unbosque.persistence;

import co.edu.unbosque.model.solicitud.Solicitud;

public class SolicitudDAO extends AbstractFileDAO<Solicitud, Integer> {

	private DataMapper dataMapper;

	public SolicitudDAO() {
		super("data/solicittud.txt");
		dataMapper = new DataMapper();
	}

	@Override
	protected String objectToLine(Solicitud obj) {
		return dataMapper.solicitudToLine(obj);
	}

	@Override
	protected Solicitud lineToObject(String line) {
		return dataMapper.lineToSolicitud(line);
	}

	@Override
	protected Integer getId(Solicitud obj) {
		return obj.getId();
	}

	@Override
	protected boolean compareId(Solicitud obj, Integer id) {
		return obj.getId() == id;
	}
}
