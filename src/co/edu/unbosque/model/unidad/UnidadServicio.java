package co.edu.unbosque.model.unidad;

import java.util.UUID;

import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;
import co.edu.unbosque.persistence.UnidadDAO;

public class UnidadServicio {

    private UnidadDAO unidadDAO;

    public UnidadServicio() {
        unidadDAO = new UnidadDAO();
    }

    // registrar
    public void registrarUnidad(TIPO_VEHICULO tipo, ESTADO_UNIDAD estado, String zona) {

        boolean disponible = (estado == ESTADO_UNIDAD.DISPONIBLE);

        Unidad u = new Unidad(UUID.randomUUID(), tipo, estado, zona, disponible);

        unidadDAO.guardar(u);
    }

    // buscar
    public UnidadDTO buscarDisponible(String zona) {

        Unidad u = unidadDAO.buscarDisponible(zona);

        if (u != null) {
            return new UnidadDTO(u);
        }

        return null;
    }

    // cambiar estado
    public void cambiarEstado(UUID id, ESTADO_UNIDAD estado) {

        Unidad u = unidadDAO.buscarPorId(id);

        if (u != null) {

            u.setEstado(estado);

            if (estado == ESTADO_UNIDAD.DISPONIBLE) {
                u.setDisponible(true);
            } else {
                u.setDisponible(false);
            }
        }
    }
}