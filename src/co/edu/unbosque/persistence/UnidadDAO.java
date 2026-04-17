package co.edu.unbosque.persistence;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.unidad.Unidad;

public class UnidadDAO {

    private ListaEnlazada<Unidad> lista;

    public UnidadDAO() {
        lista = new ListaEnlazada<>();
    }

    // ✔ guardar
    public void guardar(Unidad u) {
        lista.add(u);
    }

    // ✔ buscar por id
    public Unidad buscarPorId(Object id) {

        for (int i = 0; i < lista.count(); i++) {

            Unidad u = lista.getValueByPos(i);

            if (u != null && u.getId().equals(id)) {
                return u;
            }
        }

        return null;
    }

    // 🔥 MÉTODO CLAVE DEL TALLER
    public Unidad buscarDisponible(String zona) {

        for (int i = 0; i < lista.count(); i++) {

            Unidad u = lista.getValueByPos(i);

            if (u != null && u.isDisponible() &&
                u.getZona().equalsIgnoreCase(zona)) {
                return u;
            }
        }

        return null;
    }
}