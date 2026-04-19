package co.edu.unbosque.model.base;

public class PilaPropia<T> {
	
	private Nodo<T> top;
	
	public PilaPropia() {}
	
	public T peek() {
		if (top != null) {
			return top.getData();	
		} 
		
		return null;
	}
	
	public void push(T data) {
		Nodo<T> n = new Nodo<T>(data);
		n.setNext(top);
		top = n;
	}
	
	public void pop() {
		Nodo<T> aux = top;
		top = top.getNext();
		aux.setNext(null);
	}
	
	public boolean isEmpty() {
		return top == null;
	}

}
