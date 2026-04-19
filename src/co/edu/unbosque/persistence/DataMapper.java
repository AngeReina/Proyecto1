package co.edu.unbosque.persistence;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;
import co.edu.unbosque.model.tecnico.Tecnico;
import co.edu.unbosque.model.tecnico.TecnicoDTO;
import co.edu.unbosque.model.unidad.Unidad;

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

		

	public String unidadToLine(Unidad unidad) {
		if (unidad == null) {
			return null;
		}

		return unidad.getId() + ";" +
			unidad.getTipo().name() + ";" +
			unidad.getEstado().name() + ";" +
			unidad.getZona();
	}

	public Unidad lineToUnidad(String line) {
		if (line == null || line.trim().isEmpty()) {
			return null;
		}

		String[] parts = line.split(";");

		if (parts.length != 4) {
			return null;
		}

		try {
			java.util.UUID id = java.util.UUID.fromString(parts[0]);
			TIPO_VEHICULO tipo = TIPO_VEHICULO.valueOf(parts[1]);
			ESTADO_UNIDAD estado = ESTADO_UNIDAD.valueOf(parts[2]);
			String zona = parts[3];

			return new Unidad(id, tipo, estado, zona);
		} catch (Exception e) {
			return null;
		}
	}

}