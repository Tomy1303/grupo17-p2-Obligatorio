package TADs.Heap;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.Heap.HeapIMPL;
import uy.edu.um.prog2.adt.TADs.Heap.MyHeap;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapIMPLTest {

    @Test
    void agregar() throws EmptyLinkedListException {
        MyHeap heap = new HeapIMPL(false);
        heap.agregar(1);
        heap.agregar(3);
        heap.agregar(2);
        assertTrue(heap.size() == 3);
    }

    @Test
    void obtenerYEliminar() throws EmptyLinkedListException, EmptyHeapException {
        MyHeap heap = new HeapIMPL(false);
        heap.agregar(1);
        heap.agregar(3);
        heap.agregar(2);
        assertTrue(heap.obtenerYEliminar() == 1);
        assertTrue(heap.size() == 2);



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