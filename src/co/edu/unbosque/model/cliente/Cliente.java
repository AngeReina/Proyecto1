package co.edu.unbosque.model.cliente;

import co.edu.unbosque.model.enums.TIPO_CLIENTE;

public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private TIPO_CLIENTE tipo; // "PARTICULAR", "EMPRESA", "ASEGURADORA"

    public Cliente(int id, String nombre, String telefono, TIPO_CLIENTE tipo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public TIPO_CLIENTE getTipo() { return tipo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setTipo(TIPO_CLIENTE tipo) { this.tipo = tipo; }
}
