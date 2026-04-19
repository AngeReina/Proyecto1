package co.edu.unbosque.persistence;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.model.solicitud.SolicitudDTO;
import co.edu.unbosque.model.tecnico.Tecnico;
import co.edu.unbosque.model.tecnico.TecnicoDTO;

public class DataMapper {

	public String tecnicoToLine(Tecnico tecnico) {
		if (tecnico == null) {
			return null;
		}

		return tecnico.getId() + ";" +
			   tecnico.getNombre() + ";" +
			   tecnico.getEspecialidad() + ";" +
			   tecnico.getEstado().name() + ";" +
			   tecnico.getZona();
	}

	public Tecnico lineToTecnico(String line) {
		if (line == null || line.trim().isEmpty()) {
			return null;
		}

		String[] parts = line.split(";");

		if (parts.length != 5) {
			return null;
		}

		try {
			int id = Integer.parseInt(parts[0]);
			String nombre = parts[1];
			String especialidad = parts[2];
			EstadoTecnico estado = EstadoTecnico.valueOf(parts[3]);
			String zona = parts[4];

			return new Tecnico(id, nombre, especialidad, estado, zona);
		} catch (Exception e) {
			return null;
		}
	}
	public Tecnico toTecnico(TecnicoDTO dto) {
        if (dto == null) return null;

        return new Tecnico(
                dto.getId(),
                dto.getNombre(),
                dto.getEspecialidad(),
                EstadoTecnico.valueOf(dto.getEstado().toUpperCase()),
                dto.getZona()
        );
    }

    public TecnicoDTO toTecnicoDTO(Tecnico t) {
        if (t == null) return null;

        return new TecnicoDTO(
                t.getId(),
                t.getNombre(),
                t.getEspecialidad(),
                t.getEstado().name(),
                t.getZona()
        );
    }

    public ListaEnlazada<TecnicoDTO> toTecnicoDTOList(ListaEnlazada<Tecnico> lista) {
        ListaEnlazada<TecnicoDTO> dtoList = new ListaEnlazada<>();

        for (int i = 0; i < lista.count(); i++) {
            Tecnico t = lista.getValueByPos(i);
            if (t != null) {
                dtoList.add(toTecnicoDTO(t));
            }
        }

        return dtoList;
    }
    
	public String solicitudDtoToLine(SolicitudDTO dto) {
		if (dto == null) {
			return null;
		}

		return dto.getClienteid() + ";" +
		       dto.getId() + ";" +
		       dto.getTipo() + ";" +
		       dto.getUbicacion() + ";" +
		       dto.getTecnicoAsignado() + ";" +
		       dto.getCriterioCriticidad() + ";" +
			   dto.getDescripcionIncidente() + ";" +
			   dto.getEstado() + ";" +
			   dto.getFechaCreacion() + ";" +
			   dto.getFechaAsignacion() + ";" +
			   dto.getFechaAtencion();
	}
}