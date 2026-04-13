package co.edu.unbosque.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import co.edu.unbosque.model.base.ListaEnlazada;

public abstract class AbstractFileDAO<T, ID> implements IDAO<T, ID> {

	protected ListaEnlazada<T> lista;
	protected String filePath;

	public AbstractFileDAO(String filePath) {
		this.filePath = filePath;
		this.lista = new ListaEnlazada<T>();
	}

	protected abstract String objectToLine(T obj);

	protected abstract T lineToObject(String line);

	protected abstract ID getId(T obj);

	protected abstract boolean compareId(T obj, ID id);

	@Override
	public boolean create(T obj) {
		if (obj == null || read(getId(obj)) != null) {
			return false;
		}

		lista.add(obj);
		saveToFile();
		return true;
	}

	@Override
	public T read(ID id) {
		for (int i = 0; i < lista.count(); i++) {
			T obj = lista.getValueByPos(i);
			if (obj != null && compareId(obj, id)) {
				return obj;
			}
		}
		return null;
	}

	@Override
	public boolean update(T obj) {
		if (obj == null) {
			return false;
		}

		for (int i = 0; i < lista.count(); i++) {
			T actual = lista.getValueByPos(i);
			if (actual != null && compareId(actual, getId(obj))) {
				lista.updateDataByPos(i, obj);
				saveToFile();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(ID id) {
		for (int i = 0; i < lista.count(); i++) {
			T actual = lista.getValueByPos(i);
			if (actual != null && compareId(actual, id)) {
				lista.remove(i);
				saveToFile();
				return true;
			}
		}
		return false;
	}

	@Override
	public ListaEnlazada<T> getAll() {
		return lista;
	}

	@Override
	public void loadFromFile() {
		File file = new File(filePath);

		if (!file.exists()) {
			return;
		}

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					T obj = lineToObject(line);
					if (obj != null) {
						lista.add(obj);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error al cargar archivo: " + e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar lector: " + e.getMessage());
			}
		}
	}

	@Override
	public void saveToFile() {
		File file = new File(filePath);

		if (file.getParentFile() != null && !file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(file, false));

			for (int i = 0; i < lista.count(); i++) {
				T obj = lista.getValueByPos(i);
				bw.write(objectToLine(obj));
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error al guardar archivo: " + e.getMessage());
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar escritor: " + e.getMessage());
			}
		}
	}
}