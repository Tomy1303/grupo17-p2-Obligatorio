package entities;

import FilesReader.Reader;
import exceptions.EntidadYaExiste;
import uy.edu.um.prog2.adt.TADs.Hash.HashIMPL;
import uy.edu.um.prog2.adt.TADs.Hash.MyHash;
import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.Heap.HeapIMPL;
import uy.edu.um.prog2.adt.TADs.Heap.MyHeap;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;
import uy.edu.um.prog2.adt.TADs.Queue.MyPriorityQueue;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TweeterIMPL implements Tweeter{
    private MyLinkedList<Tweet> tweets;
    private MyLinkedList<User> users;
    private Reader R;


    public TweeterIMPL() {
        this.R = new Reader();
        this.users = new ListIMPL<>();
        this.tweets = new ListIMPL<>();
    }

    public void CargaDeDatos() throws EmptyLinkedListException {
        R.CSVReader();
        this.tweets = R.getTweets();
        this.users = R.getUsers();
    }
    public void agregarTweet(Tweet tweet) throws EntidadYaExiste, EmptyLinkedListException {
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getId() == tweet.getId()) {
                throw new EntidadYaExiste();
            }
        }
        tweets.add(tweet);
    }



    public void agregarUser(User user) throws EmptyLinkedListException, EntidadYaExiste {
        for( int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == user.getId()){
                throw new EntidadYaExiste();
            }
        }
        users.add(user);
    }

    public void agregarHashtag(Hashtag hashtag) {
        //hashtags.add(hashtag);
    }


    @Override
    public void obtenerTop10PilotosActivos(int mes, int año) throws EmptyLinkedListException {
        MyLinkedList<String> pilotos;
        pilotos = R.Drivers();
        MyPriorityQueue<String> queue = new ListIMPL<>();
        for(int i = 0; i < pilotos.size(); i++){
            queue.enqueueWithPriority(pilotos.get(i),obtenerCantidadTweetsConPalabra(pilotos.get(i)));
            System.out.println(queue.get(0));
        }
    }



    @Override
    public MyLinkedList<Object> obtenerTop15UsuariosTweets() throws EmptyLinkedListException, EmptyHeapException {
        MyHeap<MyLinkedList<Tweet>> heap= new HeapIMPL<>(true);
        MyLinkedList<User> users = new ListIMPL<>();
        MyLinkedList<Object> lista = new ListIMPL<>();
        for (int i = 0; i < this.users.size(); i++) {
            heap.agregar(this.users.get(i).getTweets());
        }
        int i = 0;
        while (i < 15){
            MyLinkedList<Tweet> list = heap.obtenerYEliminar();
            users.add(list.get(0).getUser());
            i++;
        }
        for (int j = 0; j < users.size(); j++) {
            MyLinkedList<Object> list = new ListIMPL<>();
            list.add(users.get(j).getName());
            list.add(users.get(j).getVerified());
            list.add(users.get(j).getTweets().size());
            lista.add(list);
        }
        return lista;
    }


    @Override
    public int obtenerCantidadHashtagsDistintos(LocalDate dia) throws EmptyLinkedListException {
        MyHash<String, Boolean> hashtagsDistintos = new HashIMPL<>(1000);
        for(int i=0; i<tweets.size(); i++){
            if(tweets.get(i).getFecha().toLocalDate().equals(dia)){
                MyLinkedList<Hashtag> hashtags = tweets.get(i).getHashtags();
                for(int j=0; j<hashtags.size(); j++){
                    hashtagsDistintos.put(hashtags.get(j).getText(), true);
                }
            }
        }
        return hashtagsDistintos.size();
    }

    @Override
    public String obtenerHashtagMasUsado(LocalDateTime dia) throws EmptyLinkedListException {
            MyLinkedList<Hashtag> hashtags = new ListIMPL<>();
            
            for (int i = 0; i < tweets.size(); i++) {
                Tweet tweet = tweets.get(i);
                if (tweet.getFecha().isEqual(dia)) {
                    MyLinkedList<Hashtag> tweetHashtags = tweet.getHashtags();
                    for (int j = 0; j < tweetHashtags.size(); j++) {
                        Hashtag hashtag = tweetHashtags.get(j);

                        // Excluye el hashtag "#f1"
                        if (!hashtag.getText().equalsIgnoreCase("#f1")) {
                            hashtags.add(hashtag);
                        }
                    }
                }
            }

            // Incrementa los contadores de los hashtags
            for (int i = 0; i < hashtags.size(); i++) {
                Hashtag hashtag = hashtags.get(i);
                hashtag.incrementCounter();
            }

            // Encuentra el hashtag más usado
            String maxHashtag = "";
            int maxCount = 0;
            for (int i = 0; i < hashtags.size(); i++) {
                Hashtag hashtag = hashtags.get(i);
                int count = hashtag.getCounter();
                if (count > maxCount) {
                    maxHashtag = hashtag.getText();
                    maxCount = count;
                }
            }

            // Reinicia los contadores de los hashtags para el siguiente día
            for (int i = 0; i < hashtags.size(); i++) {
                Hashtag hashtag = hashtags.get(i);
                hashtag.resetCounter();
            }

            return maxHashtag;
        }


    @Override
    public MyLinkedList<Object> obtenerTop7CuentasFavoritos() throws EmptyLinkedListException, EmptyQueueException {
        MyPriorityQueue<User> queue = new ListIMPL<>();
        MyLinkedList<Object> lista = new ListIMPL<>();
        for (int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            queue.enqueueWithPriority(user, user.getFavorites());
        }
        int i = 0;
        while (!queue.isEmpty() && i < 7) {
            MyLinkedList<Object> list = new ListIMPL<>();
            User user = queue.dequeue();
            list.add(user.getName());
            list.add(user.getFavorites());
            lista.add(list);
            i++;
        }
        return lista;
    }


    @Override
    public int obtenerCantidadTweetsConPalabra(String palabra) throws EmptyLinkedListException {
        int counter=0;
        for (int i = 0; i < tweets.size(); i++) {
            if(tweets.get(i).getContent().toUpperCase().contains(palabra.toUpperCase())){
                counter++;
            }
        }
        return counter;
    }
}
