package co.edu.unbosque.model.unidad;

import java.util.UUID;

import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;

public class UnidadDTO {

    private UUID id;
    private TIPO_VEHICULO tipo;
    private ESTADO_UNIDAD estado;
    private String zona;
    private boolean disponible;

    public UnidadDTO(Unidad u) {
        this.id = u.getId();
        this.tipo = u.getTipo();
        this.estado = u.getEstado();
        this.zona = u.getZona();
        this.disponible = u.isDisponible();
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

    public String getZona() {
        return zona;
    }

    public boolean isDisponible() {
        return disponible;
    }
}