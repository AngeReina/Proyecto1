package co.edu.unbosque.model.unidad;

import java.util.UUID;

import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;
import co.edu.unbosque.model.enums.Zona;

public class UnidadDTO {

    private UUID id;
    private TIPO_VEHICULO tipo;
    private ESTADO_UNIDAD estado;
    private Zona zona;

    public UnidadDTO(Unidad u) {
        this.id = u.getId();
        this.tipo = u.getTipo();
        this.estado = u.getEstado();
        this.zona = u.getZona();
    }

    public UUID getId() {
        return id;
    }

    public TIPO_VEHICULO getTipo() {
        return tipo;
    }

    public ESTADO_UNIDAD getEstado() {
        return estado;
    }

    public Zona getZona() {
        return zona;
    }

    public boolean isDisponible() {
        return estado == ESTADO_UNIDAD.DISPONIBLE;
    }
}