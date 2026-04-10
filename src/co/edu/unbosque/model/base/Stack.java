package co.edu.unbosque.model.base;

public class Stack<T> {
	
	private Node<T> top;
	
	public Stack() {}
	
	public T peek() {
		return top.getData();
	}
	
	public void push(T data) {
		Node<T> n = new Node<T>(data);
		n.setNext(top);
		top = n;
	}
	
	public void pop() {
		Node<T> aux = top;
		top = top.getNext();
		aux.setNext(null);
	}
	
	public boolean isEmpty() {
		return top == null;
	}

}
