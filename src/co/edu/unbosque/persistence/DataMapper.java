package co.edu.unbosque.persistence;

import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.model.tecnico.Tecnico;

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
}