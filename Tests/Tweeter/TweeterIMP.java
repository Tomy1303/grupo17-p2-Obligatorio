package Tweeter;

import entities.*;
import exceptions.EntidadYaExiste;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;

import static org.junit.jupiter.api.Assertions.*;

public class TweeterIMP {
    @Test
    public void obtenerCantidadHashtagsDistinctos() throws EmptyLinkedListException, EntidadYaExiste {
        TweeterIMPL tweeter = new TweeterIMPL();
        MyLinkedList<Hashtag> hashtags = new ListIMPL<>();
        MyLinkedList<Hashtag> hashtags2 = new ListIMPL<>();
        hashtags.add(new Hashtag(1,"Hola"));
        hashtags.add(new Hashtag(2,"pepe"));
        Fecha fecha = new Fecha(2023, 12, 12);
        User user = new User(1,"Juan",1000,1000,1000,"1000","1000",fecha,true);
        tweeter.agregarTweet(new Tweet(1, "Hola", "Twitter", false, user, new Fecha(2023, 12, 12), hashtags));
        tweeter.agregarTweet(new Tweet(2, "Hola", "Twitter", false, user, new Fecha(2023, 12, 12), hashtags));
        tweeter.agregarTweet(new Tweet(3, "Hola", "Twitter", false, user, new Fecha(2023, 12, 12), hashtags2));
        tweeter.agregarTweet(new Tweet(4, "Hola", "Twitter", false, user, new Fecha(2023, 11, 12), hashtags));
        assertEquals(2,tweeter.obtenerCantidadHashtagsDistintos(fecha));
    }

    @Test
    public void obtenerCantidadTweetsConPalabra() throws EntidadYaExiste, EmptyLinkedListException {
        TweeterIMPL tweeter = new TweeterIMPL();
        Fecha fecha = new Fecha(2023, 12, 12);
        User user = new User(1,"Juan",1000,1000,1000,"1000","1000",fecha,true);
        tweeter.agregarTweet(new Tweet(1, "Hola soy fan de f1", "Twitter", false, user, new Fecha(2023, 12, 12), null));
        tweeter.agregarTweet(new Tweet(2, "juen perez de madrid", "Twitter", false, user, new Fecha(2023, 12, 12), null));
        tweeter.agregarTweet(new Tweet(3, "soy el mas fan de perez", "Twitter", false, user, new Fecha(2023, 12, 12),null ));
        tweeter.agregarTweet(new Tweet(4, "sdbsehbVJHB FAN", "Twitter", false, user, new Fecha(2023, 11, 12), null));
        assertEquals(3,tweeter.obtenerCantidadTweetsConPalabra("fan"));
    }

    @Test
    public void obtenerTop7CuentasFavoritos() throws EntidadYaExiste, EmptyLinkedListException, EmptyQueueException {
        User user = new User(1,"3",1000,1000,1000,"1000","1000",new Fecha(2023, 12, 12),true);
        User user2 = new User(2,"1",1000,78346,1000,"1000","1000",new Fecha(2023, 12, 12),true);
        User user3 = new User(3,"8",1000,1,1000,"1000","1000",new Fecha(2023, 12, 12),true);
        User user4 = new User(4,"2",1000,1640,1000,"1000","1000",new Fecha(2023, 12, 12),true);
        User user5 = new User(5,"7",1000,70,1000,"1000","1000",new Fecha(2023, 12, 12),true);
        User user6 = new User(6,"5",1000,100,1000,"1000","1000",new Fecha(2023, 12, 12),true);
        User user7 = new User(7,"4",1000,175,1000,"1000","1000",new Fecha(2023, 12, 12),true);
        User user8 = new User(8,"6",1000,90,1000,"1000","1000",new Fecha(2023, 12, 12),true);
        TweeterIMPL tweeter = new TweeterIMPL();
        tweeter.agregarUser(user);
        tweeter.agregarUser(user2);
        tweeter.agregarUser(user3);
        tweeter.agregarUser(user4);
        tweeter.agregarUser(user5);
        tweeter.agregarUser(user6);
        tweeter.agregarUser(user7);
        tweeter.agregarUser(user8);
        assertEquals(7, tweeter.obtenerTop7CuentasFavoritos().size());
        MyLinkedList<Object> linkedList2 = new ListIMPL<>();
        linkedList2 = (MyLinkedList<Object>) tweeter.obtenerTop7CuentasFavoritos().get(0);
        System.out.println(linkedList2.get(1));
    }
}
