package co.edu.unbosque.model.cliente;

public class ClienteDTO {
    private String id;
    private String nombre;
    private String telefono;
    private String tipo; // "PARTICULAR", "EMPRESA", "ASEGURADORA"

    public ClienteDTO(String id, String nombre, String telefono, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getTipo() { return tipo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
