package co.edu.unbosque.persistence;

import co.edu.unbosque.model.base.ListaEnlazada;

public interface IDAO<T, ID> {

    boolean create(T obj);

    T read(ID id);

    boolean update(T obj);

    boolean delete(ID id);

    ListaEnlazada<T> getAll();

    void loadFromFile();

    void saveToFile();
}