package entities;

import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.Heap.HeapIMPL;
import uy.edu.um.prog2.adt.TADs.Heap.MyHeap;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

public class TweeterIMPL implements Tweeter{
    MyLinkedList<Tweet> tweets = new ListIMPL<>();
    MyLinkedList<User> users = new ListIMPL<>();
    MyLinkedList<Hashtag> hashtags = new ListIMPL<>();

    public void agregarTweet(Tweet tweet) {
        tweets.add(tweet);
    }


    public void agregarUser(User user) {
        users.add(user);
    }

    public void agregarHashtag(Hashtag hashtag) {
        hashtags.add(hashtag);
    }


    @Override
    public void obtenerTop10PilotosActivos(int mes, int año) {

    }

    @Override
    public void obtenerTop15UsuariosTweets() throws EmptyLinkedListException, EmptyHeapException {
        MyHeap heap= new HeapIMPL(true);
        MyLinkedList<Tweet> tweets = new ListIMPL<>();
        for (int i = 0; i < users.size(); i++) {
            heap.agregar(users.get(i).getTweets().size());  //mal
        }
        for (int i = 0; i < 15; i++) {

        }
    }

    @Override
    public int obtenerCantidadHashtagsDistinctos(Fecha dia) {
        return 0;
    }

    @Override
    public String obtenerHashtagMasUsado(Fecha dia) {
        return null;
    }

    @Override
    public void obtenerTop7CuentasFavoritos() {

    }

    @Override
    public int obtenerCantidadTweetsConPalabra(String palabra) {
        return 0;
    }
}
