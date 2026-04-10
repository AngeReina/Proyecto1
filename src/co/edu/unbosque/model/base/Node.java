package co.edu.unbosque.model.base;

public class Node<T> {

	private T data;
	private Node<T> next;
	
	public Node(T data) {
		this.data = data;
		this.next = null;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public Node<T> getNext() {
		return next;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
}
