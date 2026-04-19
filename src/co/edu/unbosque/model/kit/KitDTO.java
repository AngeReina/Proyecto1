package co.edu.unbosque.model.kit;

import java.util.UUID;

import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;

public class KitDTO {
	
    private UUID id;
    private String tipo; // ej: "LLANTA", "BATERIA", "HERRAMIENTAS"
    private boolean necesitaRevision;

    public KitDTO() {
    }
    
    public UUID getId() {
		return id;
	}
    
    public void setId(UUID id) {
		this.id = id;
	}
    
    public String getTipo() {
		return tipo;
	}
    
    public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
    public boolean isNecesitaRevision() {
		return necesitaRevision;
	}
    
    public void setNecesitaRevision(boolean necesitaRevision) {
		this.necesitaRevision = necesitaRevision;
	}
}