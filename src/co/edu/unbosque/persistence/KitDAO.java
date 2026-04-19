package co.edu.unbosque.persistence;

import java.util.UUID;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.kit.Kit;
import co.edu.unbosque.model.unidad.Unidad;

public class KitDAO extends AbstractFileDAO<Kit, UUID> {

    private DataMapper mapper;

    public KitDAO() {
        super("data/kit.txt");
        mapper = new DataMapper();
        loadFromFile();
    }

    @Override
    protected String objectToLine(Kit u) {
        return mapper.kitToLine(u);
    }

    @Override
    protected Kit lineToObject(String line) {
        return mapper.lineToKit(line);
    }

    @Override
    protected UUID getId(Kit u) {
        return u.getId();
    }

    @Override
    protected boolean compareId(Kit u, UUID id) {
        return u.getId().equals(id);
    }

}