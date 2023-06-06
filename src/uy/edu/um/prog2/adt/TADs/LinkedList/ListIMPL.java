package uy.edu.um.prog2.adt.TADs.LinkedList;

import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;
import uy.edu.um.prog2.adt.TADs.Queue.MyPriorityQueue;
import uy.edu.um.prog2.adt.TADs.Queue.MyQueue;
import uy.edu.um.prog2.adt.TADs.Stack.EmptyStackException;
import uy.edu.um.prog2.adt.TADs.Stack.MyStack;

public class ListIMPL<T> implements MyLinkedList<T>,MyStack <T>, MyQueue<T>, MyPriorityQueue<T>, MySimpleLinkedList<T> {

    private Nodo<T> head;
    private Nodo<T> last;
    private int size;

    public ListIMPL() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void addS(T value) {
        Nodo node = new Nodo(value);
        if (this.head == null) {
            this.head = node;
        } else {
            Nodo temp;
            for (temp = this.head; temp.getSiguiente() != null; temp = temp.getSiguiente()) {
            }

            temp.setSiguiente(node);

        }
        size++;
    }

    @Override
    public void removeS(T value) throws EmptyLinkedListException {
        if (head == null) {
            throw new EmptyLinkedListException();
        }
        if (head.getValue().equals(value)) {
            head = head.getSiguiente();
        } else {
            Nodo temp = head;
            while (temp.getSiguiente() != null) {
                if (temp.getSiguiente().getValue().equals(value)) {
                    temp.setSiguiente(temp.getSiguiente().getSiguiente());
                    break;
                }
                temp = temp.getSiguiente();
            }
            size--;
        }
    }



    @Override
    public boolean containsS(T value) {
        boolean contains = false;
        Nodo<T> temp = this.head;

        while (temp != null && !temp.getValue().equals(value)) {

            temp = temp.getSiguiente();

        }

        if (temp != null) {

            contains = true;

        }

        return contains;
    }

    @Override
    public void add(T value) { 
        if (value != null) {                          // si el elemento es vacio se ignora
            Nodo<T> elementToAdd = new Nodo<>(value);// se crea un nodo con el valor a agregar
            if (this.head == null) {                  // si la lista es vacia se agrega al comienzo
                this.head = elementToAdd;
                this.last = elementToAdd;
            } else {                                  // en caso de no ser vacia se agrega al final
                this.last.setSiguiente(elementToAdd);
                Nodo<T> aux = this.last;
                this.last = elementToAdd;
                this.last.setAnterior(aux);           // se actualiza el ultimo elemento
            }
            this.size++;
        }
    }

    @Override
    public void remove(int pocicion) throws EmptyLinkedListException {
        if (pocicion >= 0 && pocicion < this.size) { // verifica si la pocicion es valida
            if (pocicion == 0) {                     // si la pocicion es 0 se elimina el primer elemento
                this.head = null;
            } else if (pocicion == this.size - 1) {
                this.last=this.last.getAnterior();
                this.last.setSiguiente(null);        // si la pocicion es la ultima se elimina el ultimo elemento
            }else{
                Nodo<T> aux = this.head;            // se crea un nodo auxiliar para recorrer la lista
                int i = 0;
                while(i<pocicion-1){
                    aux = aux.getSiguiente();
                    i++;
                }                                    // se recorre la lista hasta llegar al elemento anterior al que se quiere eliminar
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
                aux.getSiguiente().setAnterior(aux); // se actualiza el elemento siguiente al eliminado
            }
            this.size--;
        }else {
           throw new EmptyLinkedListException();
        }
    }

    @Override
    public T get(int pocicion) throws EmptyLinkedListException {
        if (pocicion >= 0 && pocicion < this.size) { // verifica si la pocicion es valida
            Nodo<T> aux = this.head;                 // se crea un nodo auxiliar para recorrer la lista
            int i = 0;
            while(i<pocicion){
                aux = aux.getSiguiente();
                i++;
            }                                       // se recorre la lista hasta llegar al elemento que se quiere obtener
            return aux.getValue();
        }
        throw new EmptyLinkedListException();
    }

    @Override
    public void enqueue(T value) {
        if (value != null) {                         // si el elemento es vacio se ignora
            Nodo<T> elementToAdd = new Nodo<>(value);// se crea un nodo con el valor a agregar
            if (this.head == null) {                 // si la lista es vacia se agrega al comienzo
                this.head = elementToAdd;
                this.last = elementToAdd;
            } else {                                 // en caso de no ser vacia se agrega al final
                this.head.setAnterior(elementToAdd);
                Nodo<T> aux = this.head;
                this.head = elementToAdd;
                this.head.setSiguiente(aux);   // se actualiza el primer elemento
            }
            this.size++;
        }
    }

    @Override
    public void enqueueWithPriority(T value, int prioridad) {
        if (value != null) {                         // si el elemento es vacio se ignora
            Nodo<T> elementToAdd = new Nodo<>(value, prioridad);// se crea un nodo con el valor a agregar
            Nodo<T> aux = this.head;                 // se crea un nodo auxiliar para recorrer la lista
            if (this.head == null) {                 // si la lista es vacia se agrega al comienzo
                this.head = elementToAdd;
                this.last = elementToAdd;
            } else {                                 // en caso de no ser vacia se agrega al final
                if (prioridad > this.head.getPrioridad()) {
                    this.head.setAnterior(elementToAdd);
                    this.head = elementToAdd;
                    this.head.setSiguiente(aux);     // se actualiza el primer elemento
                } else {
                    while (aux.getSiguiente() != null && prioridad <= aux.getSiguiente().getPrioridad()) {
                        aux = aux.getSiguiente();
                    }                                // se recorre la lista hasta encontrar el elemento con menor prioridad
                    if (aux.getSiguiente() == null) {
                        aux.setSiguiente(elementToAdd);
                        this.last = elementToAdd;    // si la prioridad es mayor a todos los elementos se agrega al final
                    } else {
                        elementToAdd.setSiguiente(aux.getSiguiente());
                        aux.getSiguiente().setAnterior(elementToAdd);
                        aux.setSiguiente(elementToAdd);
                    }
                }
            }
            this.size++;
        }
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (this.size > 0) {                         // verifica si la lista no esta vacia
            Nodo<T> aux = this.head;
            if (this.size == 1) {                    // si la lista tiene un elemento se elimina
                this.head = null;
                this.last = null;
            } else {                                 // en caso de tener mas de un elemento se elimina el primero
                this.head = this.head.getSiguiente();
                this.head.setAnterior(null);         // se actualiza el primer elemento
            }
            this.size--;
            return aux.getValue();
        }
        throw new EmptyQueueException();
    }

    @Override
    public boolean existe(T value) {
        Nodo<T> aux = this.head;                     // se crea un nodo auxiliar para recorrer la lista
        while (aux != null) {
            if (aux.getValue().equals(value)) {      // se recorre la lista hasta encontrar el elemento
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    @Override
    public void addFirst(T value) {
        if (value != null) {                         // si el elemento es vacio se ignora
            Nodo<T> elementToAdd = new Nodo<>(value);// se crea un nodo con el valor a agregar
            if (this.head == null) {                 // si la lista es vacia se agrega al comienzo
                this.head = elementToAdd;
                this.last = elementToAdd;
            } else {                                 // en caso de no ser vacia se agrega al comienzo
                elementToAdd.setSiguiente(this.head);
                this.head.setAnterior(elementToAdd);
                this.head = elementToAdd;
            }
            this.size++;
        }
    }

    @Override
    public void pop() throws EmptyStackException {
        if (this.size > 0) {                         // verifica si la lista no esta vacia
            if (this.size == 1) {                    // si la lista tiene un elemento se elimina
                this.head = null;
                this.last = null;
            } else {                                 // en caso de tener mas de un elemento se elimina el ultimo
                this.last.getAnterior().setSiguiente(null);
                this.last= this.last.getAnterior();
            }
            this.size--;
        } else {
            throw new EmptyStackException();         // si la lista esta vacia se lanza una excepcion
        }
    }

    @Override
    public T top() throws EmptyStackException{
        if(this.size == 0){
            throw new EmptyStackException();         // si la lista esta vacia se lanza una excepcion
        }
        return this.last.getValue(); //Devuelve el ultimo elemento de la lista
    }

    @Override
    public void push(T element) {
        this.add(element); //Agrega un elemento al final de la lista
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;//Verifica si la lista esta vacia
    }

    @Override
    public void makeEmpty() {
        this.head = null; //Vacia la lista
        this.last = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size; //
    }

    @Override
    public void change(int pocicion1, int pocicion2) throws EmptyLinkedListException {
        if(this.size<pocicion1 || this.size<pocicion2){
            throw new EmptyLinkedListException(); //Si la pocicion es mayor al tamaño de la lista se lanza una excepcion
        }else{
            Nodo<T> aux = this.head;
            Nodo<T> aux2 = this.head;
            for(int i = 0; i<pocicion1; i++){
                aux = aux.getSiguiente(); //Se recorre la lista hasta llegar a la pocicion 1
            }
            for(int i = 0; i<pocicion2; i++){
                aux2 = aux2.getSiguiente(); //Se recorre la lista hasta llegar a la pocicion 2
            }
            T value = aux.getValue();
            aux.setValue(aux2.getValue()); //Se cambian los valores de las pociciones
            aux2.setValue(value);
        }
    }
    public void selectionSort() {
        Nodo<T> current = head;

        while (current != null) {
            Nodo<T> smallest = current;
            Nodo<T> temp = current.getSiguiente();

            while (temp != null) {
                if (temp.getValue().hashCode() < smallest.getValue().hashCode()) {
                    smallest = temp;
                }
                temp = temp.getSiguiente();
            }

            swap(current, smallest);
            current = current.getSiguiente();
        }
    }

    @Override
    public void swap(Nodo<T> node1, Nodo<T> node2) {
        T temp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp);
    }

}
