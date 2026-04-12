package co.edu.unbosque.model.base;

public class ColaPrioridadPropia<T> {
	
	private ColaPropia<T>[] arrQueueByPriorties;
	
	public ColaPrioridadPropia(int size) {
		this.arrQueueByPriorties = (ColaPropia<T>[]) new ColaPropia[size];
	}
	
	public void createPriority(int index) {
		arrQueueByPriorties[index] = new ColaPropia<T>();
	}
	
	public void queueByPriority(int index, T data) {
		arrQueueByPriorties[index].enqueue(data);
	}
	
	public void dequeueByPriority(int index) {
		arrQueueByPriorties[index].dequeue();
	}
	
	public boolean isEmptyByPriority(int index) {
		return arrQueueByPriorties[index].isEmpty();
	}

	public T getBeginByPriority(int index) {
		return arrQueueByPriorties[index].getBegin();
	}
	
	public T getEndByPriority(int index) {
		return arrQueueByPriorties[index].getEnd();
	}
}
