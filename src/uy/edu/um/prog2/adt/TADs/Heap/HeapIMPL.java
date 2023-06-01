package uy.edu.um.prog2.adt.TADs.Heap;

import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

public class HeapIMPL implements MyHeap{
    private MyLinkedList<Integer> heap;
    private int size;
    private boolean max;

    public HeapIMPL(boolean max) {
        this.heap = new ListIMPL<>();
        this.size = 0;
        this.max = max;
    }

    @Override
    public void agregar(int valor) throws EmptyLinkedListException {
        this.heap.add(valor);//agrego el valor al final de la lista
        this.size++;
        int i = this.size - 1;
        if(max) {                                                               //max heap
            while (i > 0 && this.heap.get(i) > this.heap.get((i - 1) / 2)) {    //mientras el valor agregado sea mayor que su padre
                this.heap.change(i, (i - 1) / 2);                               //cambio el valor agregado con su padre
                i = (i - 1) / 2;                                                //actualizo la posicion del valor agregado
            }
        }else {                                                                  // min heap lo mismo que max heap pero con el signo cambiado
            while (i > 0 && this.heap.get(i) < this.heap.get((i - 1) / 2)) {
                this.heap.change(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

    }

    @Override
    public int obtenerYEliminar() throws EmptyHeapException, EmptyLinkedListException {
        if(this.size==0){
            throw new EmptyHeapException();                                             //si el heap esta vacio lanzo una excepcion
        }else {
            int valor = this.heap.get(0);                                               //guardo el valor maximo o minimo
            if (max) {                                                                  //max heap
                this.heap.change(0, this.size-1);                                         //cambio el valor maximo con el ultimo valor del heap
                this.heap.remove(this.size-1);                                            //elimino el ultimo valor del heap
                this.size--;
                int i = 0;
                while (i < this.size) {
                    if (2*i+1<size && this.heap.get(i) < this.heap.get(2 * i + 1) && 2*i+2<size &&this.heap.get(i) < this.heap.get(2 * i + 2)) {   //mientras el valor sea menor que sus hijos
                        if (this.heap.get(2 * i + 1) > this.heap.get(2 * i + 2)) {      //si el hijo izquierdo es mayor que el derecho
                            this.heap.change(i, 2 * i + 1);                             //cambio el valor con el hijo izquierdo para que sea mayor que el
                            i = 2 * i + 1;
                        } else {
                            this.heap.change(i, 2 * i + 2);                             //cambio el valor con el hijo derecho para que sea mayor que el
                            i = 2 * i + 2;
                        }
                    } else if (2*i+1<size && this.heap.get(i) < this.heap.get(2 * i + 1)) {           //si el valor es menor que el hijo izquierdo
                        this.heap.change(i, 2 * i + 1);                                 //cambio el valor con el hijo izquierdo para que sea mayor que el
                        i = 2 * i + 1;                                                  //actualizo la posicion del valor
                    } else if (2*i+2<size && this.heap.get(i) < this.heap.get(2 * i + 2)) {           //si el valor es menor que el hijo derecho
                        this.heap.change(i, 2 * i + 2);                                 //cambio el valor con el hijo derecho para que sea mayor que el
                        i = 2 * i + 2;
                    } else {
                        break;
                    }
                }
            } else {                                                                   //min heap lo mismo que max heap pero con el signo cambiado
                this.heap.change(0, this.size-1);
                this.heap.remove(this.size-1);
                this.size--;
                int i = 0;
                while (i < this.size) {
                    if (2*i+1<size && this.heap.get(i) > this.heap.get(2 * i + 1) && 2*i+2<size && this.heap.get(i) > this.heap.get(2 * i + 2)) {
                        if (this.heap.get(2 * i + 1) < this.heap.get(2 * i + 2)) {
                            this.heap.change(i, 2 * i + 1);
                            i = 2 * i + 1;
                        } else {
                            this.heap.change(i, 2 * i + 2);
                            i = 2 * i + 2;
                        }
                    } else if (2*i+1<size && this.heap.get(i) > this.heap.get(2 * i + 1)) {
                        this.heap.change(i, 2 * i + 1);
                        i = 2 * i + 1;
                    } else if (2*i+2<size && this.heap.get(i) > this.heap.get(2 * i + 2)) {
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
