package co.edu.unbosque.model.tecnico;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.persistence.TecnicoDAO;

public class TecnicoService {

	private TecnicoDAO tecnicoDAO;

	public TecnicoService() {
		tecnicoDAO = new TecnicoDAO();
	}

	public boolean registrarTecnico(Tecnico tecnico) {
		if (tecnico == null) {
			return false;
		}

		if (tecnico.getEstado() == null) {
			tecnico.setEstado(EstadoTecnico.DISPONIBLE);
		}

		return tecnicoDAO.create(tecnico);
	}

	public Tecnico buscarTecnico(int id) {
		return tecnicoDAO.read(id);
	}

	public ListaEnlazada<Tecnico> listarTecnicos() {
		return tecnicoDAO.getAll();
	}

	public ListaEnlazada<Tecnico> buscarPorZona(String zona) {
		return tecnicoDAO.buscarPorZona(zona);
	}

	public ListaEnlazada<Tecnico> buscarPorEspecialidad(String especialidad) {
		return tecnicoDAO.buscarPorEspecialidad(especialidad);
	}

	public Tecnico buscarDisponible(String zona) {
		return tecnicoDAO.buscarDisponiblePorZona(zona);
	}

	public boolean cambiarEstado(int id, String estado) {
		try {
			EstadoTecnico nuevoEstado = EstadoTecnico.valueOf(estado.toUpperCase());
			return tecnicoDAO.cambiarEstado(id, nuevoEstado);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public boolean actualizarTecnico(Tecnico tecnico) {
		return tecnicoDAO.update(tecnico);
	}

	public boolean eliminarTecnico(int id) {
		return tecnicoDAO.delete(id);
	}
}