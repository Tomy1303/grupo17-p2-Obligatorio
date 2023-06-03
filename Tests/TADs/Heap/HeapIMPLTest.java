package TADs.Heap;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.Heap.HeapIMPL;
import uy.edu.um.prog2.adt.TADs.Heap.MyHeap;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapIMPLTest {

    @Test
    void agregar() throws EmptyLinkedListException {
        MyHeap heap = new HeapIMPL(true);
        MyLinkedList<Integer> lista = new ListIMPL<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        heap.agregar(lista);
        lista.remove(1);
        heap.agregar(lista);
        lista.add(4);
        lista.add(5);
        heap.agregar(lista);
        assertTrue(heap.size() == 3);

    }

    @Test
    void obtenerYEliminar() throws EmptyLinkedListException, EmptyHeapException {
        MyHeap heap = new HeapIMPL(true);
        MyLinkedList<Integer> lista = new ListIMPL<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        heap.agregar(lista);
        MyLinkedList<Integer> lista2 = new ListIMPL<>();
        lista2 = lista;
        lista2.remove(1);
        heap.agregar(lista2);
        MyLinkedList<Integer> lista3 = new ListIMPL<>();
        lista3 = lista2;
        lista3.add(4);
        lista3.add(5);
        heap.agregar(lista3);
        assertTrue(heap.obtenerYEliminar().equals(lista3));
    }

    @Test
    void size() throws EmptyLinkedListException {
        MyHeap heap = new HeapIMPL(true);
        heap.agregar(1);
        heap.agregar(2);
        heap.agregar(3);
        assertTrue(heap.size() == 3);
    }
}