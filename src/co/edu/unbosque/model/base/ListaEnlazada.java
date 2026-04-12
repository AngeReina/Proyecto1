package co.edu.unbosque.model.base;

public class ListaEnlazada<T> {
	
	private Nodo<T> cabeza;
	
	//add ----------------------------------------------------------------------
	
	public void add(T data) {
		if (cabeza == null) {
			cabeza = new Nodo<T>(data);
		} else if (!cabeza.hasNext())  {
			cabeza.setNext(new Nodo<T>(data));
		} else {
			addElement(cabeza, data);
		}
	}
	
	private void addElement(Nodo<T> node, T data) {
		if (!node.hasNext()) {
			node.setNext(new Nodo<T>(data));
		} else {
			addElement(node.getNext(), data);
		}
	}
	
	public void addAtFirst(T data) {
		if (cabeza == null) {
			cabeza = new Nodo<T>(data);
		} else  {
			Nodo<T> aux = new Nodo<T>(data);
			aux.setNext(cabeza);
			cabeza = aux;
		}
	}
	
	public void addByPos(T data, int pos) {
		if (pos == 0) {
			addAtFirst(data);
		} else if (pos > 0) {
			if (cabeza != null) {
				addElementByPos(cabeza, data, pos, 0);	
			}
		}
	}
	
	private void addElementByPos(Nodo<T> node, T data, int posToInsert, int currentPos) {
		if (currentPos == (posToInsert - 1) || !node.hasNext()) {
			Nodo<T> created = new Nodo<T>(data);
			created.setNext(node.getNext());
			node.setNext(created);
		} else if (node.hasNext()) {
			addElementByPos(node.getNext(), data, posToInsert, currentPos + 1);
		}
	}
	
	//get value ----------------------------------------------------------------
	
	public T getValueByPos(int pos) {
		if (pos >= 0) {
			Nodo<T> node = getElementByPos(cabeza, pos, 0);
			if (node != null) {
				return node.getData();
			}
		}
		
		return null;
	}
	
	private Nodo<T> getElementByPos(Nodo<T> node, int posToSearch, int currentPos) {
		if (node == null) {
			return null;
		} else if (currentPos == (posToSearch)) {
			return node;
		} else {
			return getElementByPos(node.getNext(), posToSearch, currentPos+1);
		}
	}
	
	//update value 
	
	public void updateDataByPos(int pos, T data) {
		if (pos >= 0) {
			Nodo<T> node = getElementByPos(cabeza, pos, 0);
			if (node != null) {
				node.setData(data);
			}
		}
	}
	
	///remove  --------------------------------------------------------------------
	
	public void remove(int posToDelete) {
		if (posToDelete == 0) {
			if (cabeza != null) {
				cabeza = cabeza.getNext();
			}
		} else if (cabeza != null && cabeza.hasNext()) {
			removeElement(posToDelete, 1, cabeza);
		}
	}
	
	private void removeElement(int posToDelete, int currentPos, Nodo<T> node) {
		if (currentPos == posToDelete) {
			if (node.hasNext()) {
				 if (node.getNext().hasNext()) {
						Nodo<T> aux = node.getNext().getNext();
						node.getNext().setNext(null);
						node.setNext(aux);
						
					} else if (node.hasNext()) {
						node.setNext(null);
					}	
			}
		} else if (node.hasNext()) {
			removeElement(posToDelete, currentPos + 1, node.getNext());
		}
	}
	
	//count elements ---------------------------------------------------------------
	
	public int count() {
		return countImpl(cabeza);
	}
	
	public int countImpl(Nodo<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + countImpl(node.getNext());
		}
	}
	
}
