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
import uy.edu.um.prog2.adt.TADs.LinkedList.MySimpleLinkedList;
import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;
import uy.edu.um.prog2.adt.TADs.Queue.MyPriorityQueue;
import uy.edu.um.prog2.adt.TADs.Queue.MyQueue;
import uy.edu.um.prog2.adt.TADs.Tree.BinaryTreeIMPL;
import uy.edu.um.prog2.adt.TADs.Tree.EmptyTreeException;
import uy.edu.um.prog2.adt.TADs.Tree.MyBinaryTree;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
    public void obtenerTop10PilotosActivos(int mes, int año) throws EmptyLinkedListException, EmptyTreeException, EmptyQueueException {
        LocalDateTime fechamax;
        if (mes == 12){
             fechamax = LocalDateTime.of(año +1, 1 , 1, 0, 0);

        } else{
            fechamax = LocalDateTime.of(año, mes +1 , 1, 0, 0);
        }
        LocalDateTime fechamin = LocalDateTime.of(año, mes, 1, 0, 0);

        MyLinkedList<String> pilotos;
        pilotos = R.Drivers();
        MyBinaryTree<String, Integer> arbol = new BinaryTreeIMPL<>();
        Tweet tweet;
        String piloto;
        Integer pilotoMenciones;
        for( int i = 0 ; i< this.tweets.size(); i++){
            tweet = this.tweets.get(i);
            if (tweet.getFecha().isAfter(fechamin) && tweet.getFecha().isBefore(fechamax)){
                for (int j = 0; j < pilotos.size(); j++) {
                    piloto = pilotos.get(j);
                    if (tweet.getContent().contains(piloto)){
                        pilotoMenciones = arbol.find(piloto);
                        if (pilotoMenciones != null){
                            arbol.insert(piloto, pilotoMenciones + 1);
                        } else {
                            arbol.insert(piloto, 1);
                        }
                    }
                }
            }
        }
        MyPriorityQueue <String> ordenados  = new ListIMPL<>();
        Integer cantidad;
        for (int i = 0; i<pilotos.size(); i++) {
            piloto = pilotos.get(i);
            cantidad = arbol.find(piloto);
            if (cantidad != null){
                ordenados.enqueueWithPriority(piloto, cantidad);
            }
        }
        int contador = 1;
        while (ordenados.size() > 0) {
            if (contador < 11) {
                System.out.println(contador + "- " + ordenados.dequeue());
                contador++;
            }
        }
    }




    @Override
    public MyQueue<User> obtenerTop15UsuariosTweets() throws EmptyLinkedListException, EmptyHeapException {
        MyHeap<MyLinkedList<Tweet>> heap= new HeapIMPL<>(true);
        MyQueue<User> users = new ListIMPL<>();
        for (int i = 0; i < this.users.size(); i++) {
            heap.agregar(this.users.get(i).getTweets());
        }
        int i = 0;
        while (i < 15 && heap.size() > 0){
            MyLinkedList<Tweet> list = heap.obtenerYEliminar();
            if (list.size() > 0){
                users.enqueue(list.get(0).getUser());
                i++;
            }
        }
        return users;
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
    public String obtenerHashtagMasUsado(LocalDate dia) throws EmptyLinkedListException {
            MyLinkedList<Hashtag> hashtags = new ListIMPL<>();
            
            for (int i = 0; i < tweets.size(); i++) {
                Tweet tweet = tweets.get(i);
                if (tweet.getFecha().toLocalDate().equals(dia)) {
                    MyLinkedList<Hashtag> tweetHashtags = tweet.getHashtags();
                    for (int j = 0; j < tweetHashtags.size(); j++) {
                        Hashtag hashtag = tweetHashtags.get(j);
                        String text = hashtag.getText();
                        // Excluye el hashtag "f1"
                        if (!text.equalsIgnoreCase("'f1'")) {
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
    public MyQueue<User> obtenerTop7CuentasFavoritos() throws EmptyLinkedListException, EmptyQueueException {
        MyPriorityQueue<User> queue = new ListIMPL<>();
        MyQueue<User> lista = new ListIMPL<>();
        for (int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            queue.enqueueWithPriority(user, user.getFavorites());
        }
        int i = 0;
        while (!queue.isEmpty() && i < 7) {
            MyLinkedList<Object> list = new ListIMPL<>();
            User user = queue.dequeue();
            lista.enqueue(user);
            i++;
        }
        return lista;
    }


    @Override
    public int obtenerCantidadTweetsConPalabra(String palabra) throws EmptyLinkedListException {
        int counter=0;
        for (int i = 0; i < tweets.size(); i++) {
            if(tweets.get(i).getContent().contains(palabra)){
                counter++;
            }
        }
        return counter;
    }
}
