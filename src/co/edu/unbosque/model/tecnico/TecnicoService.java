package co.edu.unbosque.model.tecnico;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.persistence.DataMapper;
import co.edu.unbosque.persistence.TecnicoDAO;

public class TecnicoService {

	private TecnicoDAO tecnicoDAO;
	private DataMapper mapper;

	public TecnicoService() {
		tecnicoDAO = new TecnicoDAO();
		mapper = new DataMapper();
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

	public Tecnico asignarTecnicoLibre(String especialidad) {
		ListaEnlazada<Tecnico> lista = tecnicoDAO.buscarPorEspecialidad(especialidad);

		for (int i = 0; i < lista.count(); i++) {
			Tecnico t = lista.getValueByPos(i);

			if (t != null && t.getEstado() == EstadoTecnico.DISPONIBLE) {
				t.setEstado(EstadoTecnico.ASIGNADO);
				tecnicoDAO.update(t);
				return t;
			}
		}

		return null;
	}

	public boolean validarDisponibilidad(int id) {
		Tecnico tecnico = tecnicoDAO.read(id);

		if (tecnico == null) {
			return false;
		}

		return tecnico.getEstado() == EstadoTecnico.DISPONIBLE;
	}


	public boolean registrarTecnicoDTO(TecnicoDTO dto) {
		if (!validarDTO(dto)) return false;

		try {
			Tecnico tecnico = mapper.toTecnico(dto);
			return registrarTecnico(tecnico);
		} catch (Exception e) {
			return false;
		}
	}

	public TecnicoDTO buscarTecnicoDTO(int id) {
		if (id <= 0) return null;

		return mapper.toTecnicoDTO(buscarTecnico(id));
	}

	public ListaEnlazada<TecnicoDTO> listarTecnicosDTO() {
		return mapper.toTecnicoDTOList(listarTecnicos());
	}

	public ListaEnlazada<TecnicoDTO> buscarTecnicosPorZonaDTO(String zona) {
		if (zona == null || zona.trim().isEmpty()) return null;

		return mapper.toTecnicoDTOList(buscarPorZona(zona));
	}

	public ListaEnlazada<TecnicoDTO> buscarTecnicosPorEspecialidadDTO(String especialidad) {
		if (especialidad == null || especialidad.trim().isEmpty()) return null;

		return mapper.toTecnicoDTOList(buscarPorEspecialidad(especialidad));
	}

	public TecnicoDTO buscarTecnicoDisponibleDTO(String zona) {
		if (zona == null || zona.trim().isEmpty()) return null;

		return mapper.toTecnicoDTO(buscarDisponible(zona));
	}

	public boolean actualizarTecnicoDTO(TecnicoDTO dto) {
		if (!validarDTO(dto)) return false;

		try {
			Tecnico tecnico = mapper.toTecnico(dto);
			return actualizarTecnico(tecnico);
		} catch (Exception e) {
			return false;
		}
	}


	private boolean validarDTO(TecnicoDTO dto) {
		return !(dto == null || dto.getId() <= 0 ||
				dto.getNombre() == null || dto.getNombre().trim().isEmpty() ||
				dto.getEspecialidad() == null || dto.getEspecialidad().trim().isEmpty() ||
				dto.getEstado() == null || dto.getEstado().trim().isEmpty() ||
				dto.getZona() == null || dto.getZona().trim().isEmpty());
	}
}