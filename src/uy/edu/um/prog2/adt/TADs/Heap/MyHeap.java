package uy.edu.um.prog2.adt.TADs.Heap;

import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;

public interface MyHeap <T>{

    void agregar(T lista) throws EmptyLinkedListException;

    T obtenerYEliminar() throws EmptyHeapException, EmptyLinkedListException;

    int size();


}
