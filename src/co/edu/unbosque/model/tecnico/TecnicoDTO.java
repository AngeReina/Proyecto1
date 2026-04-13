package co.edu.unbosque.model.tecnico;

public class TecnicoDTO {

    private int id;
    private String nombre;
    private String especialidad;
    private String estado;
    private String zona;

    public TecnicoDTO(int id, String nombre, String especialidad, String estado, String zona) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.estado = estado;
        this.zona = zona;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getEstado() {
        return estado;
    }

    public String getZona() {
        return zona;
    }

    @Override
    public String toString() {
        return "TecnicoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", estado='" + estado + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }
}
