package co.edu.unbosque.persistence;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.tecnico.Tecnico;
import co.edu.unbosque.model.enums.EstadoTecnico;

public class TecnicoDAO extends AbstractFileDAO<Tecnico, Integer> {

	private DataMapper dataMapper;

	public TecnicoDAO() {
		super("data/tecnicos.txt");
		dataMapper = new DataMapper();
		loadFromFile();
	}

	@Override
	protected String objectToLine(Tecnico obj) {
		return dataMapper.tecnicoToLine(obj);
	}

	@Override
	protected Tecnico lineToObject(String line) {
		return dataMapper.lineToTecnico(line);
	}

	@Override
	protected Integer getId(Tecnico obj) {
		return obj.getId();
	}

	@Override
	protected boolean compareId(Tecnico obj, Integer id) {
		return obj.getId() == id;
	}

	public ListaEnlazada<Tecnico> buscarPorZona(String zona) {
		ListaEnlazada<Tecnico> encontrados = new ListaEnlazada<Tecnico>();

		for (int i = 0; i < lista.count(); i++) {
			Tecnico tecnico = lista.getValueByPos(i);
			if (tecnico != null && tecnico.getZona().equalsIgnoreCase(zona)) {
				encontrados.add(tecnico);
			}
		}

		return encontrados;
	}

	public ListaEnlazada<Tecnico> buscarPorEspecialidad(String especialidad) {
		ListaEnlazada<Tecnico> encontrados = new ListaEnlazada<Tecnico>();

		for (int i = 0; i < lista.count(); i++) {
			Tecnico tecnico = lista.getValueByPos(i);
			if (tecnico != null && tecnico.getEspecialidad().equalsIgnoreCase(especialidad)) {
				encontrados.add(tecnico);
			}
		}

		return encontrados;
	}

	public Tecnico buscarDisponiblePorZona(String zona) {
		for (int i = 0; i < lista.count(); i++) {
			Tecnico tecnico = lista.getValueByPos(i);
			if (tecnico != null
					&& tecnico.getZona().equalsIgnoreCase(zona)
					&& tecnico.getEstado() == EstadoTecnico.DISPONIBLE) {
				return tecnico;
			}
		}
		return null;
	}

	public boolean cambiarEstado(int id, EstadoTecnico nuevoEstado) {
		Tecnico tecnico = read(id);

		if (tecnico == null) {
			return false;
		}

		tecnico.setEstado(nuevoEstado);
		return update(tecnico);
	}
}