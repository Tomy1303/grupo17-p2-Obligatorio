package uy.edu.um.prog2.adt.TADs.LinkedList;

import java.util.Objects;

public class Nodo<T> {
    private T value;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;
    private int prioridad;


    public Nodo(T value) {                      //Para la linked list, stack y queue
        this.value = value;
        this.siguiente = null;
        this.anterior = null;
    }

    public Nodo(T value, int prioridad) {       //Para la queue con prioridad
        this.value = value;
        this.siguiente = null;
        this.anterior = null;
        this.prioridad = prioridad;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nodo<?> nodo)) return false;
        return Objects.equals(value, nodo.value);
    }

}
