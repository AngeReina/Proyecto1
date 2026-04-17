package co.edu.unbosque.model.unidad;

import java.util.UUID;

import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;

public class Unidad {

    private UUID id;
    private TIPO_VEHICULO tipo;
    private ESTADO_UNIDAD estado;
    private String zona;
    private boolean disponible;

    public Unidad(UUID id, TIPO_VEHICULO tipo, ESTADO_UNIDAD estado, String zona, boolean disponible) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.zona = zona;
        this.disponible = disponible;
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

    public void setEstado(ESTADO_UNIDAD estado) {
        this.estado = estado;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}