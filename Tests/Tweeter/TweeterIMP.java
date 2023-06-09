package Tweeter;

import entities.*;
import exceptions.EntidadYaExiste;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;
import uy.edu.um.prog2.adt.TADs.Queue.MyPriorityQueue;
import uy.edu.um.prog2.adt.TADs.Tree.EmptyTreeException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TweeterIMP {
    @Test
    public void obtenerTop10PilotosActivos() throws EmptyLinkedListException, EmptyTreeException, EmptyQueueException, EntidadYaExiste {
        TweeterIMPL tweeter = new TweeterIMPL();
        LocalDateTime fecha = LocalDate.of(2023, 12, 12).atStartOfDay();
        User user = new User(1, "Juan", 1000, 1000, 1000, "1000", "1000", fecha, true);
        User user2 = new User(2, "Juan", 1000, 1000, 1000, "1000", "1000", fecha, true);
        User user3 = new User(3, "Juan", 1000, 1000, 1000, "1000", "1000", fecha, true);
        Tweet tweet = new Tweet(1, "Carlos Sainz", "Twitter", false, user, fecha, null);
        Tweet tweet2 = new Tweet(2, "Lewis Hamilton", "Twitter", false, user2, fecha, null);
        Tweet tweet3 = new Tweet(3, "Max Verstappen", "Twitter", false, user3, fecha, null);
        Tweet tweet4 = new Tweet(4, "Fernando Alonso", "Twitter", false, user, fecha, null);
        Tweet tweet5 = new Tweet(5, "Carlos Sainz", "Twitter", false, user2, fecha, null);
        Tweet tweet6 = new Tweet(6, "Lewis Hamilton", "Twitter", false, user3, fecha, null);
        Tweet tweet7 = new Tweet(7, "Lewis Hamilton", "Twitter", false, user, fecha, null);
        tweeter.agregarTweet(tweet);
        tweeter.agregarTweet(tweet2);
        tweeter.agregarTweet(tweet3);
        tweeter.agregarTweet(tweet4);
        tweeter.agregarTweet(tweet5);
        tweeter.agregarTweet(tweet6);
        tweeter.agregarTweet(tweet7);
        tweeter.obtenerTop10PilotosActivos(12, 2023);

    }
    @Test
    public void obtenerCantidadHashtagsDistinctos() throws EmptyLinkedListException, EntidadYaExiste {
        TweeterIMPL tweeter = new TweeterIMPL();
        MyLinkedList<Hashtag> hashtags = new ListIMPL<>();
        MyLinkedList<Hashtag> hashtags2 = new ListIMPL<>();
        hashtags.add(new Hashtag(1, "Hola"));
        hashtags.add(new Hashtag(2, "pepe"));
        LocalDateTime fecha = LocalDate.of(2023, 12, 12).atStartOfDay();
        User user = new User(1, "Juan", 1000, 1000, 1000, "1000", "1000", fecha, true);
        tweeter.agregarTweet(new Tweet(1, "Hola", "Twitter", false, user, fecha, hashtags));
        tweeter.agregarTweet(new Tweet(2, "Hola", "Twitter", false, user, fecha, hashtags));
        tweeter.agregarTweet(new Tweet(3, "Hola", "Twitter", false, user, fecha, hashtags2));
        tweeter.agregarTweet(new Tweet(4, "Hola", "Twitter", false, user, fecha, hashtags));
        assertEquals(2, tweeter.obtenerCantidadHashtagsDistintos(fecha.toLocalDate()));
    }

    @Test
    public void obtenerCantidadTweetsConPalabra() throws EntidadYaExiste, EmptyLinkedListException {
        TweeterIMPL tweeter = new TweeterIMPL();
        LocalDateTime fecha = LocalDate.of(2023, 12, 12).atStartOfDay();
        User user = new User(1, "Juan", 1000, 1000, 1000, "1000", "1000", fecha, true);
        tweeter.agregarTweet(new Tweet(1, "Hola soy fan de f1", "Twitter", false, user,fecha, null));
        tweeter.agregarTweet(new Tweet(2, "juen perez de madrid", "Twitter", false, user, fecha, null));
        tweeter.agregarTweet(new Tweet(3, "soy el mas fan de perez", "Twitter", false, user,fecha, null));
        tweeter.agregarTweet(new Tweet(4, "sdbsehbVJHB FAN", "Twitter", false, user, fecha, null));
        assertEquals(3, tweeter.obtenerCantidadTweetsConPalabra("fan"));
    }

    @Test
    public void obtenerTop7CuentasFavoritos() throws EntidadYaExiste, EmptyLinkedListException, EmptyQueueException, EmptyHeapException {
        LocalDateTime fecha = LocalDate.of(2023, 12, 12).atStartOfDay();
        User user = new User(1, "3", 1000, 1000, 1000, "1000", "1000",fecha, true);
        User user2 = new User(2, "1", 1000, 78346, 1000, "1000", "1000", fecha, true);
        User user3 = new User(3, "8", 1000, 1, 1000, "1000", "1000", fecha, true);
        User user4 = new User(4, "2", 1000, 1640, 1000, "1000", "1000", fecha, true);
        User user5 = new User(5, "7", 1000, 70, 1000, "1000", "1000", fecha, true);
        User user6 = new User(6, "5", 1000, 100, 1000, "1000", "1000", fecha, true);
        User user7 = new User(7, "4", 1000, 175, 1000, "1000", "1000", fecha, true);
        User user8 = new User(8, "6", 1000, 90, 1000, "1000", "1000", fecha, true);
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

    @Test
    public void obtenerHashtagMasUsado() throws EmptyLinkedListException, EntidadYaExiste {
        TweeterIMPL tweeter = new TweeterIMPL();
        MyLinkedList<Hashtag> hashtags = new ListIMPL<>();
        MyLinkedList<Hashtag> hashtags2 = new ListIMPL<>();
        hashtags.add(new Hashtag(1, "Hola"));
        hashtags.add(new Hashtag(2, "pepe"));
        LocalDateTime fecha = LocalDate.of(2023, 12, 12).atStartOfDay();
        User user = new User(1, "Juan", 1000, 1000, 1000, "1000", "1000", fecha, true);
        tweeter.agregarTweet(new Tweet(1, "Hola", "Twitter", false, user, fecha, hashtags));
        tweeter.agregarTweet(new Tweet(2, "Hola", "Twitter", false, user, fecha, hashtags));
        tweeter.agregarTweet(new Tweet(3, "Hola", "Twitter", false, user, fecha, hashtags2));
        tweeter.agregarTweet(new Tweet(4, "Hola", "Twitter", false, user, fecha, hashtags));
        assertEquals("Hola", tweeter.obtenerHashtagMasUsado(fecha.toLocalDate()));
    }
    @Test
    public void obtenerTop15UsuariosTweets() throws EntidadYaExiste, EmptyLinkedListException, EmptyQueueException, EmptyHeapException {
        LocalDateTime fecha = LocalDate.of(2023, 12, 12).atStartOfDay();
        User user = new User(1,"3",1000,1000,1000,"1000","1000",fecha,true);
        User user2 = new User(2,"1",1000,78346,1000,"1000","1000",fecha,true);
        User user3 = new User(3,"8",1000,1,1000,"1000","1000",fecha,true);
        User user4 = new User(4,"2",1000,1640,1000,"1000","1000",fecha,true);
        User user5 = new User(5,"7",1000,70,1000,"1000","1000",fecha,true);
        User user6 = new User(6,"5",1000,100,1000,"1000","1000",fecha,true);
        User user7 = new User(7,"4",1000,175,1000,"1000","1000",fecha,true);
        User user8 = new User(8,"6",1000,90,1000,"1000","1000",fecha,true);
        User user9 = new User(9,"9",1000,90,1000,"1000","1000",fecha,true);
        User user10 = new User(10,"10",1000,90,1000,"1000","1000",fecha,true);
        User user11 = new User(11,"11",1000,90,1000,"1000","1000",fecha,true);
        User user12 = new User(12,"12",1000,90,1000,"1000","1000",fecha,true);
        User user13 = new User(13,"13",1000,90,1000,"1000","1000",fecha,true);
        User user14 = new User(14,"14",1000,90,1000,"1000","1000",fecha,true);
        User user15 = new User(15,"15",1000,90,1000,"1000","1000",fecha,true);
        User user16 = new User(16,"16",1000,90,1000,"1000","1000",fecha,true);
        Tweet tweet = new Tweet(1,"Hola soy fan de f1","Twitter",false,user,fecha,null);
        Tweet tweet2 = new Tweet(2,"juen perez de madrid","Twitter",false,user2,fecha,null);
        Tweet tweet3 = new Tweet(3,"soy el mas fan de perez","Twitter",false,user3,fecha,null);
        Tweet tweet4 = new Tweet(4,"sdbsehbVJHB FAN","Twitter",false,user4,fecha,null);
        Tweet tweet5 = new Tweet(5,"Hola soy fan de f1","Twitter",false,user5,fecha,null);
        Tweet tweet6 = new Tweet(6,"juen perez de madrid","Twitter",false,user6,fecha,null);
        Tweet tweet7 = new Tweet(7,"soy el mas fan de perez","Twitter",false,user7,fecha,null);
        Tweet tweet8 = new Tweet(8,"sdbsehbVJHB FAN","Twitter",false,user8,fecha,null);
        Tweet tweet9 = new Tweet(9,"Hola soy fan de f1","Twitter",false,user9,fecha,null);
        Tweet tweet10 = new Tweet(10,"juen perez de madrid","Twitter",false,user10,fecha,null);
        Tweet tweet11 = new Tweet(11,"soy el mas fan de perez","Twitter",false,user11,fecha,null);
        Tweet tweet12 = new Tweet(12,"sdbsehbVJHB FAN","Twitter",false,user12,fecha,null);
        Tweet tweet13 = new Tweet(13,"Hola soy fan de f1","Twitter",false,user13,fecha,null);
        Tweet tweet14 = new Tweet(14,"juen perez de madrid","Twitter",false,user14,fecha,null);
        Tweet tweet15 = new Tweet(15,"soy el mas fan de perez","Twitter",false,user16,fecha,null);
        Tweet tweet16 = new Tweet(16,"sdbsehbVJHB FAN","Twitter",false,user12,fecha,null);
        Tweet tweet17 = new Tweet(16,"sdbsehbVJHB FAN","Twitter",false,user14,fecha,null);
        Tweet tweet18 = new Tweet(16,"sdbsehbVJHB FAN","Twitter",false,user12,fecha,null);
        TweeterIMPL tweeter = new TweeterIMPL();
        user.setTweets(tweet);
        user2.setTweets(tweet2);
        user3.setTweets(tweet3);
        user4.setTweets(tweet4);
        user5.setTweets(tweet5);
        user6.setTweets(tweet6);
        user7.setTweets(tweet7);
        user8.setTweets(tweet8);
        user9.setTweets(tweet9);
        user10.setTweets(tweet10);
        user11.setTweets(tweet11);
        user12.setTweets(tweet12);
        user12.setTweets(tweet16);
        user12.setTweets(tweet18);
        user13.setTweets(tweet13);
        user14.setTweets(tweet14);
        user16.setTweets(tweet15);
        user14.setTweets(tweet17);
        tweeter.agregarUser(user);
        tweeter.agregarUser(user2);
        tweeter.agregarUser(user3);
        tweeter.agregarUser(user4);
        tweeter.agregarUser(user5);
        tweeter.agregarUser(user6);
        tweeter.agregarUser(user7);
        tweeter.agregarUser(user8);
        tweeter.agregarUser(user9);
        tweeter.agregarUser(user10);
        tweeter.agregarUser(user11);
        tweeter.agregarUser(user12);
        tweeter.agregarUser(user13);
        tweeter.agregarUser(user14);
        tweeter.agregarUser(user16);
        tweeter.agregarUser(user15);
        assertEquals(15,tweeter.obtenerTop15UsuariosTweets().size());
        MyLinkedList<Object> linkedList2 = (MyLinkedList<Object>) tweeter.obtenerTop15UsuariosTweets().get(0);
        assertEquals("12",linkedList2.get(0));
        MyLinkedList<Object> linkedList3 = (MyLinkedList<Object>) tweeter.obtenerTop15UsuariosTweets().get(1);
        assertEquals("14", linkedList3.get(0));
    }
}
