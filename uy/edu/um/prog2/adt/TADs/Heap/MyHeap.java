package uy.edu.um.prog2.adt.TADs.Heap;

import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;

public interface MyHeap {

    void agregar(int valor) throws EmptyLinkedListException;

    int obtenerYEliminar() throws EmptyHeapException, EmptyLinkedListException;

    int size();


}
