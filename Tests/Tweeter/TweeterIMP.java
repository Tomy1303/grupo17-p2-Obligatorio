package Tweeter;

import entities.*;
import exceptions.EntidadYaExiste;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class TweeterIMP {
    @Test
    public void obtenerCantidadHashtagsDistinctos() throws EmptyLinkedListException, EntidadYaExiste {
        TweeterIMPL tweeter = new TweeterIMPL();
        MyLinkedList<Hashtag> hashtags = new ListIMPL<>();
        MyLinkedList<Hashtag> hashtags2 = new ListIMPL<>();
        hashtags.add(new Hashtag(1,"Hola"));
        hashtags.add(new Hashtag(2,"pepe"));
        tweeter.agregarTweet(new Tweet(1, "Hola", "Twitter", false, new User(1, "Juan"), new Fecha(2023, 12, 12), hashtags));
        tweeter.agregarTweet(new Tweet(2, "Hola", "Twitter", false, new User(2, "Juan"), new Fecha(2023, 12, 12), hashtags));
        tweeter.agregarTweet(new Tweet(3, "Hola", "Twitter", false, new User(3, "Juan"), new Fecha(2023, 12, 12), hashtags2));
        tweeter.agregarTweet(new Tweet(4, "Hola", "Twitter", false, new User(4, "Juan"), new Fecha(2023, 11, 12), hashtags));
        Fecha fecha = new Fecha(2023, 12, 12);
        assertEquals(2,tweeter.obtenerCantidadHashtagsDistintos(fecha));
    }

    @Test
    public void obtenerCantidadTweetsConPalabra() throws EntidadYaExiste, EmptyLinkedListException {
        TweeterIMPL tweeter = new TweeterIMPL();
        tweeter.agregarTweet(new Tweet(1, "Hola soy fan de f1", "Twitter", false, new User(1, "Juan"), new Fecha(2023, 12, 12), null));
        tweeter.agregarTweet(new Tweet(2, "juen perez de madrid", "Twitter", false, new User(2, "Juan"), new Fecha(2023, 12, 12), null));
        tweeter.agregarTweet(new Tweet(3, "soy el mas fan de perez", "Twitter", false, new User(3, "Juan"), new Fecha(2023, 12, 12),null ));
        tweeter.agregarTweet(new Tweet(4, "sdbsehbVJHB FAN", "Twitter", false, new User(4, "Juan"), new Fecha(2023, 11, 12), null));
        assertEquals(3,tweeter.obtenerCantidadTweetsConPalabra("fan"));
    }
}
