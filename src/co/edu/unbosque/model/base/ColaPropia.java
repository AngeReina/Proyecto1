package co.edu.unbosque.model.base;

public class ColaPropia<T> {
	
	private Nodo<T> frente;
	private Nodo<T> ultimo;
	private int size;
	
	public ColaPropia() {
	}
	
	public void enqueue(T data) {
		Nodo<T> aux = new Nodo<T>(data);
		if (ultimo != null) {
			ultimo.setNext(aux);
		}
		
		ultimo = aux;
		
		if (frente == null) {
			frente = ultimo;
		}
		
		size++;
	}
	
	public void dequeue() {
		if (!isEmpty()) {
			frente = frente.getNext();
			
			if (frente == null) {
				ultimo = frente;
			}
			
			size--;
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public T getBegin() {
		return frente.getData();
	}
	
	public T getEnd() {
		return ultimo.getData();
	}
	
	public int getSize() {
		return size;
	}
}
