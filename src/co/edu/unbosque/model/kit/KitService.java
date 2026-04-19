package co.edu.unbosque.model.kit;

import java.util.UUID;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.base.PilaPropia;
import co.edu.unbosque.persistence.DataMapper;
import co.edu.unbosque.persistence.KitDAO;


public class KitService {

    private PilaPropia<Kit> pilaKitsDisponibles;
    private PilaPropia<Kit> pilaKitsMantenimiento;
    private KitDAO dao;
    private DataMapper mapper;

    public KitService() {
        this.dao = new KitDAO();
        this.mapper = new DataMapper();
        this.pilaKitsDisponibles = new PilaPropia<>();
        this.pilaKitsMantenimiento = new PilaPropia<>();
    }
    
    public void init() {
    	ListaEnlazada<Kit> list = dao.getAll();
    	int count = list.count();
    	for (int i = count-1; i >= 0; i--) {
    		Kit kit = list.getValueByPos(i);
    		if (kit.isNecesitaRevision()) {
    			pilaKitsMantenimiento.push(kit);
    		} else {
    			pilaKitsDisponibles.push(kit);
    		}
    	}
    }
    
    public boolean agregarKit(KitDTO kit) {
    	kit.setId(UUID.randomUUID());
    	kit.setNecesitaRevision(true);
    	pilaKitsMantenimiento.push(mapper.toKit(kit));
    	
    	return dao.create(mapper.toKit(kit));
    }
    
    public boolean devolverKit(KitDTO kit) {
    	kit.setNecesitaRevision(true);
    	pilaKitsMantenimiento.push(mapper.toKit(kit));
    	
    	return dao.update(mapper.toKit(kit));
    }
    
    public boolean revisarKit() {
        Kit k = pilaKitsMantenimiento.peek();
        if (k != null) {
        	pilaKitsMantenimiento.pop();
        	k.setNecesitaRevision(false);
        	dao.update(k);
        	pilaKitsDisponibles.push(k);
        	
        	return true;
        } 
    	
    	return false;
    }

    public KitDTO retirarKit() {
        Kit k = pilaKitsDisponibles.peek();
        if (k != null) {
        	pilaKitsDisponibles.pop();
        	dao.delete(k.getId());
        	
        	return mapper.toKitDTO(k);
        } 
    	
    	return null;
    }
    
    public boolean existeKitDisponible() {
    	return !pilaKitsDisponibles.isEmpty();
    }

}