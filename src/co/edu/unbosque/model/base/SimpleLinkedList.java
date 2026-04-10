package co.edu.unbosque.model.base;

public class SimpleLinkedList<T> {
	
	private Node<T> head;
	
	//add ----------------------------------------------------------------------
	
	public void add(T data) {
		if (head == null) {
			head = new Node<T>(data);
		} else if (!head.hasNext())  {
			head.setNext(new Node<T>(data));
		} else {
			addElement(head, data);
		}
	}
	
	private void addElement(Node<T> node, T data) {
		if (!node.hasNext()) {
			node.setNext(new Node<T>(data));
		} else {
			addElement(node.getNext(), data);
		}
	}
	
	public void addAtFirst(T data) {
		if (head == null) {
			head = new Node<T>(data);
		} else  {
			Node<T> aux = new Node<T>(data);
			aux.setNext(head);
			head = aux;
		}
	}
	
	public void addByPos(T data, int pos) {
		if (pos == 0) {
			addAtFirst(data);
		} else if (pos > 0) {
			if (head != null) {
				addElementByPos(head, data, pos, 0);	
			}
		}
	}
	
	private void addElementByPos(Node<T> node, T data, int posToInsert, int currentPos) {
		if (currentPos == (posToInsert - 1) || !node.hasNext()) {
			Node<T> created = new Node<T>(data);
			created.setNext(node.getNext());
			node.setNext(created);
		} else if (node.hasNext()) {
			addElementByPos(node.getNext(), data, posToInsert, currentPos + 1);
		}
	}
	
	//get value ----------------------------------------------------------------
	
	public T getValueByPos(int pos) {
		if (pos >= 0) {
			Node<T> node = getElementByPos(head, pos, 0);
			if (node != null) {
				return node.getData();
			}
		}
		
		return null;
	}
	
	private Node<T> getElementByPos(Node<T> node, int posToSearch, int currentPos) {
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
			Node<T> node = getElementByPos(head, pos, 0);
			if (node != null) {
				node.setData(data);
			}
		}
	}
	
	///remove  --------------------------------------------------------------------
	
	public void remove(int posToDelete) {
		if (posToDelete == 0) {
			if (head != null) {
				head = head.getNext();
			}
		} else if (head != null && head.hasNext()) {
			removeElement(posToDelete, 1, head);
		}
	}
	
	private void removeElement(int posToDelete, int currentPos, Node<T> node) {
		if (currentPos == posToDelete) {
			if (node.hasNext()) {
				 if (node.getNext().hasNext()) {
						Node<T> aux = node.getNext().getNext();
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
		return countImpl(head);
	}
	
	public int countImpl(Node<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + countImpl(node.getNext());
		}
	}
	
}
