package uy.edu.um.prog2.adt.TADs.LinkedList;

public interface MyLinkedList<T> {

     void add(T value);

     void remove(int pocicion) throws EmptyLinkedListException;

     T get(int pocicion) throws EmptyLinkedListException;

     boolean existe(T value);

     void addFirst(T value);

     int size();

     void change(int pocicion1,int pocicion2) throws EmptyLinkedListException;

}
