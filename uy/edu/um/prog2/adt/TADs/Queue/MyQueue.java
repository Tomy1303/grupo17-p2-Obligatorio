package uy.edu.um.prog2.adt.TADs.Queue;

import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;

public interface MyQueue<T> {

	void enqueue(T value);

	T dequeue()  throws EmptyQueueException;
	
	boolean existe(T value);

	T get(int i) throws EmptyQueueException, EmptyLinkedListException;
	
	int size();

	boolean isEmpty();
	
}
