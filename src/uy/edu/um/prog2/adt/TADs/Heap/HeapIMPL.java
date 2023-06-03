package uy.edu.um.prog2.adt.TADs.Heap;

import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import uy.edu.um.prog2.adt.TADs.Queue.MyQueue;
import uy.edu.um.prog2.adt.TADs.Stack.MyStack;

public class HeapIMPL<T> implements MyHeap<T>{
    private MyLinkedList<T> heap;
    private int size;
    private boolean max;

    public HeapIMPL(boolean max) {
        this.heap = new ListIMPL<>();
        this.size = 0;
        this.max = max;
    }

    @Override
    public void agregar(T lista) throws EmptyLinkedListException {
        this.heap.add(lista); // Agrego el valor al final de la lista
        this.size++;
        int i = this.size - 1;
        if (lista instanceof MyLinkedList<?>) { // Verifica si T es instancia de MyLinkedList
            int listaSize = size2(lista); // Obtiene el tamaño de la lista particular utilizando size2()
            if (max) { // max heap
                while (i > 0 && size2(this.heap.get(i)) > size2(this.heap.get((i - 1) / 2))) {
                    this.heap.change(i, (i - 1) / 2);
                    i = (i - 1) / 2;
                }
            } else { // min heap lo mismo que max heap pero con el signo cambiado
                while (i > 0 && size2(this.heap.get(i)) < size2(this.heap.get((i - 1) / 2))) {
                    this.heap.change(i, (i - 1) / 2);
                    i = (i - 1) / 2;
                }
            }
        }
    }

    public int size2(T lista) throws EmptyLinkedListException {
        int listaSize = 0;
        if (lista instanceof MyLinkedList<?>) { // Verifica si T es instancia de MyLinkedList
            MyLinkedList<?> linkedList = (MyLinkedList<?>) lista; // Hacemos un cast seguro a MyLinkedList
            listaSize = linkedList.size();
        }
        if (lista instanceof MyQueue<?>){
            MyQueue<?> queue = (MyQueue<?>) lista;
            listaSize = queue.size();
        }
        if (lista instanceof MyStack<?>){
            MyStack<?> stack = (MyStack<?>) lista;
            listaSize = stack.size();
        }
        return listaSize;
    }

    @Override
    public T obtenerYEliminar() throws EmptyHeapException, EmptyLinkedListException {
        if (this.size == 0) {
            throw new EmptyHeapException(); // Si el heap está vacío, lanzo una excepción
        } else {
            T valor = this.heap.get(0); // Guardo el valor máximo o mínimo
            if (max) { // Max heap
                this.heap.change(0, this.size - 1); // Cambio el valor máximo con el último valor del heap
                this.heap.remove(this.size - 1); // Elimino el último valor del heap
                this.size--;
                int i = 0;
                while (i < this.size) {
                    if (2 * i + 1 < size && size2(this.heap.get(i)) < size2(this.heap.get(2 * i + 1)) && 2 * i + 2 < size && size2(this.heap.get(i)) < size2(this.heap.get(2 * i + 2))) {
                        if (size2(this.heap.get(2 * i + 1)) > size2(this.heap.get(2 * i + 2))) {
                            this.heap.change(i, 2 * i + 1);
                            i = 2 * i + 1;
                        } else {
                            this.heap.change(i, 2 * i + 2);
                            i = 2 * i + 2;
                        }
                    } else if (2 * i + 1 < size && size2(this.heap.get(i)) < size2(this.heap.get(2 * i + 1))) {
                        this.heap.change(i, 2 * i + 1);
                        i = 2 * i + 1;
                    } else if (2 * i + 2 < size && size2(this.heap.get(i)) < size2(this.heap.get(2 * i + 2))) {
                        this.heap.change(i, 2 * i + 2);
                        i = 2 * i + 2;
                    } else {
                        break;
                    }
                }
            } else { // Min heap lo mismo que max heap pero con el signo cambiado
                this.heap.change(0, this.size - 1);
                this.heap.remove(this.size - 1);
                this.size--;
                int i = 0;
                while (i < this.size) {
                    if (2 * i + 1 < size && size2(this.heap.get(i)) > size2(this.heap.get(2 * i + 1)) && 2 * i + 2 < size && size2(this.heap.get(i)) > size2(this.heap.get(2 * i + 2))) {
                        if (size2(this.heap.get(2 * i + 1)) < size2(this.heap.get(2 * i + 2))) {
                            this.heap.change(i, 2 * i + 1);
                            i = 2 * i + 1;
                        } else {
                            this.heap.change(i, 2 * i + 2);
                            i = 2 * i + 2;
                        }
                    } else if (2 * i + 1 < size && size2(this.heap.get(i)) > size2(this.heap.get(2 * i + 1))) {
                        this.heap.change(i, 2 * i + 1);
                        i = 2 * i + 1;
                    } else if (2 * i + 2 < size && size2(this.heap.get(i)) > size2(this.heap.get(2 * i + 2))) {
                        this.heap.change(i, 2 * i + 2);
                        i = 2 * i + 2;
                    } else {
                        break;
                    }
                }
            }
            return valor;
        }
    }


    @Override
    public int size() {
        return this.size;
    }
}
