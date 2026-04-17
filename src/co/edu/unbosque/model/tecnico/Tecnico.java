package co.edu.unbosque.model.tecnico;

import co.edu.unbosque.model.enums.EstadoTecnico;

public class Tecnico {

    private int id;
    private String nombre;
    private String especialidad;
    private EstadoTecnico estado;
    private String zona;

    public Tecnico() {
    }

    public Tecnico(int id, String nombre, String especialidad, EstadoTecnico estado, String zona) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.estado = estado;
        this.zona = zona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public EstadoTecnico getEstado() {
        return estado;
    }

    public void setEstado(EstadoTecnico estado) {
        this.estado = estado;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", estado=" + estado +
                ", zona='" + zona + '\'' +
                '}';
    }
}
