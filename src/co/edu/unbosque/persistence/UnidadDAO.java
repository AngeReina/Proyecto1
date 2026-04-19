package co.edu.unbosque.persistence;

import java.util.UUID;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.enums.Zona;
import co.edu.unbosque.model.unidad.Unidad;

public class UnidadDAO extends AbstractFileDAO<Unidad, UUID> {

    private DataMapper mapper;

    public UnidadDAO() {
        super("data/unidades.txt");
        mapper = new DataMapper();
        loadFromFile();
    }

    @Override
    protected String objectToLine(Unidad u) {
        return mapper.unidadToLine(u);
    }

    @Override
    protected Unidad lineToObject(String line) {
        return mapper.lineToUnidad(line);
    }

    @Override
    protected UUID getId(Unidad u) {
        return u.getId();
    }

    @Override
    protected boolean compareId(Unidad u, UUID id) {
        return u.getId().equals(id);
    }

    // Método propio del negocio
    public Unidad buscarDisponible(String zona) {

        if (zona == null || zona.trim().isEmpty()) {
            return null;
        }

        Zona zonaEnum = Zona.valueOf(zona.trim().toUpperCase());

        for (int i = 0; i < lista.count(); i++) {

            Unidad u = lista.getValueByPos(i);

            if (u != null &&
                u.isDisponible() &&
                u.getZona() == zonaEnum) {

                return u;
            }
        }

        return null;
    }

    public ListaEnlazada<Unidad> buscarDisponibles(String zona) {

        ListaEnlazada<Unidad> resultado = new ListaEnlazada<>();

        if (zona == null || zona.trim().isEmpty()) {
            return resultado;
        }

        Zona zonaEnum = Zona.valueOf(zona.trim().toUpperCase());

        for (int i = 0; i < lista.count(); i++) {

            Unidad u = lista.getValueByPos(i);

            if (u != null &&
                u.getZona() == zonaEnum) {

                resultado.add(u);
            }
        }

        return resultado;
    }
    
}