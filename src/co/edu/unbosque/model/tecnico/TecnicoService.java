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

	    if (tecnicoDAO.read(tecnico.getId()) != null) {
	        return false; 
	    }

	    if (tecnico.getEstado() == null) {
	        tecnico.setEstado(EstadoTecnico.DISPONIBLE);
	    }

	    return tecnicoDAO.create(tecnico);
	}

	public Tecnico buscarTecnico(int id) {
		if (id <= 0) return null;
		return tecnicoDAO.read(id);
	}

	public ListaEnlazada<Tecnico> listarTecnicos() {
		return tecnicoDAO.getAll();
	}

	public ListaEnlazada<Tecnico> buscarPorZona(String zona) {
		if (zona == null || zona.trim().isEmpty()) return null;

		ListaEnlazada<Tecnico> lista = tecnicoDAO.buscarPorZona(zona);
		return (lista != null && lista.count() > 0) ? lista : null;
	}

	public ListaEnlazada<Tecnico> buscarPorEspecialidad(String especialidad) {
		if (especialidad == null || especialidad.trim().isEmpty()) return null;

		ListaEnlazada<Tecnico> lista = tecnicoDAO.buscarPorEspecialidad(especialidad);
		return (lista != null && lista.count() > 0) ? lista : null;
	}

	public Tecnico buscarDisponible(String zona) {
		if (zona == null || zona.trim().isEmpty()) return null;

		Tecnico t = tecnicoDAO.buscarDisponiblePorZona(zona);
		return t;
	}

	public boolean cambiarEstado(int id, String estado) {
		if (id <= 0 || estado == null || estado.trim().isEmpty()) return false;

		try {
			EstadoTecnico nuevoEstado = EstadoTecnico.valueOf(estado.toUpperCase());
			return tecnicoDAO.cambiarEstado(id, nuevoEstado);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public boolean actualizarTecnico(Tecnico tecnico) {
		if (tecnico == null) return false;
		return tecnicoDAO.update(tecnico);
	}

	public Tecnico asignarTecnicoLibre(String especialidad) {
		if (especialidad == null || especialidad.trim().isEmpty()) return null;

		ListaEnlazada<Tecnico> lista = tecnicoDAO.buscarPorEspecialidad(especialidad);

		if (lista == null || lista.count() == 0) return null;

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
		if (id <= 0) return false;

		Tecnico tecnico = tecnicoDAO.read(id);
		return tecnico != null && tecnico.getEstado() == EstadoTecnico.DISPONIBLE;
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

		Tecnico t = buscarTecnico(id);
		return mapper.toTecnicoDTO(t);
	}

	public ListaEnlazada<TecnicoDTO> listarTecnicosDTO() {
		ListaEnlazada<Tecnico> lista = listarTecnicos();
		return (lista != null) ? mapper.toTecnicoDTOList(lista) : null;
	}

	public ListaEnlazada<TecnicoDTO> buscarTecnicosPorZonaDTO(String zona) {
		ListaEnlazada<Tecnico> lista = buscarPorZona(zona);
		return (lista != null) ? mapper.toTecnicoDTOList(lista) : null;
	}

	public ListaEnlazada<TecnicoDTO> buscarTecnicosPorEspecialidadDTO(String especialidad) {
		ListaEnlazada<Tecnico> lista = buscarPorEspecialidad(especialidad);
		return (lista != null) ? mapper.toTecnicoDTOList(lista) : null;
	}

	public TecnicoDTO buscarTecnicoDisponibleDTO(String zona) {
		Tecnico t = buscarDisponible(zona);
		return mapper.toTecnicoDTO(t);
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
		return !(dto == null ||
				dto.getId() <= 0 ||
				dto.getNombre() == null || dto.getNombre().trim().isEmpty() ||
				dto.getEspecialidad() == null || dto.getEspecialidad().trim().isEmpty() ||
				dto.getEstado() == null || dto.getEstado().trim().isEmpty() ||
				dto.getZona() == null || dto.getZona().trim().isEmpty());
	}
}