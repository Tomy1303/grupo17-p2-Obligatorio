package entities;

import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

public class TweeterIMPL implements Tweeter{
    MyLinkedList<Tweet> tweets = new ListIMPL<>();
    MyLinkedList<User> users = new ListIMPL<>();
    MyLinkedList<Hashtag> hashtags = new ListIMPL<>();

    @Override
    public void agregarTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    @Override
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
    public void obtenerTop15UsuariosTweets() {

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
