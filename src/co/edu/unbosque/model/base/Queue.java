package co.edu.unbosque.model.base;

public class Queue<T> {
	
	private Node<T> begin;
	private Node<T> end;
	private int size;
	
	public Queue() {
	}
	
	public void queue(T data) {
		Node<T> aux = new Node<T>(data);
		if (end != null) {
			end.setNext(aux);
		}
		
		end = aux;
		
		if (begin == null) {
			begin = end;
		}
		
		size++;
	}
	
	public void dequeue() {
		if (!isEmpty()) {
			begin = begin.getNext();
			
			if (begin == null) {
				end = begin;
			}
			
			size--;
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public T getBegin() {
		return begin.getData();
	}
	
	public T getEnd() {
		return end.getData();
	}
	
	public int getSize() {
		return size;
	}
}
