package uy.edu.um.prog2.adt.TADs.Queue;

import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;

public interface MyPriorityQueue<T> extends MyQueue<T> {

    void enqueueWithPriority(T value, int prioridad);

    T dequeue() throws EmptyQueueException;

    boolean existe(T value);

    T get(int i) throws EmptyLinkedListException;

    int size();

    boolean isEmpty();

}
