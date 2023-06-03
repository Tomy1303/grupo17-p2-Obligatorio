package entities;

import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;

public interface Tweeter {


    void obtenerTop10PilotosActivos(int mes, int año) throws EmptyLinkedListException;
    void obtenerTop15UsuariosTweets() throws EmptyLinkedListException, EmptyHeapException;
    int obtenerCantidadHashtagsDistintos(Fecha dia) throws EmptyLinkedListException;
    String obtenerHashtagMasUsado(Fecha dia);
    void obtenerTop7CuentasFavoritos();
    int obtenerCantidadTweetsConPalabra(String palabra);



}
