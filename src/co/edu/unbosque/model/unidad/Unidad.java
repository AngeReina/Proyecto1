package co.edu.unbosque.model.unidad;

import java.util.UUID;

import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;
import co.edu.unbosque.model.enums.Zona;

public class Unidad {

    private UUID id;
    private TIPO_VEHICULO tipo;
    private ESTADO_UNIDAD estado;
    private Zona zona;

    public Unidad(UUID id, TIPO_VEHICULO tipo, ESTADO_UNIDAD estado, Zona zona) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.zona = zona;
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

    // La disponibilidad depende del estado
    public boolean isDisponible() {
        return estado == ESTADO_UNIDAD.DISPONIBLE;
    }

    // Método controlado para cambiar estado
    public void cambiarEstado(ESTADO_UNIDAD nuevoEstado) {

        if (nuevoEstado == null) {
            return;
        }

        // Regla: si está en mantenimiento, solo puede pasar a disponible
        if (this.estado == ESTADO_UNIDAD.MANTENIMIENTO &&
            nuevoEstado != ESTADO_UNIDAD.DISPONIBLE) {
            return;
        }

        this.estado = nuevoEstado;
    }
}