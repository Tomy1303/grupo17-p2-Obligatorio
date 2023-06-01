package uy.edu.um.prog2.adt.TADs.LinkedList;

public interface MySimpleLinkedList<T> {

        void addS(T value);

        T get(int position) throws EmptyLinkedListException;

        boolean containsS(T value);

        void removeS(T value) throws EmptyLinkedListException;

        int size();
    }

