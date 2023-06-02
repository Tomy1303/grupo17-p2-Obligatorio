package entities;

import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;

public interface Tweeter {


    void obtenerTop10PilotosActivos(int mes, int año);
    void obtenerTop15UsuariosTweets() throws EmptyLinkedListException, EmptyHeapException;
    int obtenerCantidadHashtagsDistinctos(Fecha dia);
    String obtenerHashtagMasUsado(Fecha dia);
    void obtenerTop7CuentasFavoritos();
    int obtenerCantidadTweetsConPalabra(String palabra);



}
