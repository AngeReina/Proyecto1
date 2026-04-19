package co.edu.unbosque.model.unidad;

import java.util.UUID;

import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;
import co.edu.unbosque.model.enums.Zona;
import co.edu.unbosque.persistence.UnidadDAO;
import co.edu.unbosque.model.base.ListaEnlazada;



public class UnidadServicio {

    private UnidadDAO unidadDAO;

    public UnidadServicio() {
        this.unidadDAO = new UnidadDAO();
    }

    // Registrar una nueva unidad
    public void registrarUnidad(TIPO_VEHICULO tipo, ESTADO_UNIDAD estado, Zona zona) {

        if (zona == null) {
            return;
        }

        Unidad unidad = new Unidad(UUID.randomUUID(), tipo, estado, zona);
        unidadDAO.create(unidad);
    }

    // Buscar una unidad disponible por zona
    public UnidadDTO buscarDisponible(String zona) {

        Unidad unidad = unidadDAO.buscarDisponible(zona);

        if (unidad != null) {
            return new UnidadDTO(unidad);
        }

        return null;
    }

    // Cambiar el estado de una unidad
    public boolean cambiarEstado(UUID id, ESTADO_UNIDAD estado) {

        Unidad u = unidadDAO.read(id);

        if (u != null) {

            if (u.getEstado() == estado) {
                return false;
            }

            u.cambiarEstado(estado);
            unidadDAO.update(u);
            return true;
        }

        return false;
    }

    public ListaEnlazada<UnidadDTO> buscarDisponibles(String zona) {

        ListaEnlazada<Unidad> lista = unidadDAO.buscarDisponibles(zona);
        ListaEnlazada<UnidadDTO> resultado = new ListaEnlazada<>();

        for (int i = 0; i < lista.count(); i++) {

            Unidad u = lista.getValueByPos(i);

            if (u != null) {
                resultado.add(new UnidadDTO(u));
            }
        }

        return resultado;
    }

}