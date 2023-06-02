package entities;

import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

class PilotoMencionado {
    private String nombre;
    private int menciones;

    public PilotoMencionado(String nombre) {
        this.nombre = nombre;
        this.menciones = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMenciones() {
        return menciones;
    }

    public void incrementarMenciones() {
        menciones++;
    }

}
