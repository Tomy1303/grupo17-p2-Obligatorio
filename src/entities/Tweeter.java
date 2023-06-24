package entities;

import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;
import uy.edu.um.prog2.adt.TADs.Queue.MyQueue;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Tweeter {


    void obtenerTop10PilotosActivos(int mes, int año) throws EmptyLinkedListException;
    MyQueue<User> obtenerTop15UsuariosTweets() throws EmptyLinkedListException, EmptyHeapException;
    int obtenerCantidadHashtagsDistintos(LocalDate dia) throws EmptyLinkedListException;
    String obtenerHashtagMasUsado(LocalDateTime dia) throws EmptyLinkedListException;
    MyQueue<User> obtenerTop7CuentasFavoritos() throws EmptyLinkedListException, EmptyQueueException;
    int obtenerCantidadTweetsConPalabra(String palabra) throws EmptyLinkedListException;



}
