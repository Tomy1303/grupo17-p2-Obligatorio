package entities;

import FilesReader.Reader;
import exceptions.EntidadYaExiste;
import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.Heap.HeapIMPL;
import uy.edu.um.prog2.adt.TADs.Heap.MyHeap;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;
import uy.edu.um.prog2.adt.TADs.Queue.MyPriorityQueue;

public class TweeterIMPL implements Tweeter{
    MyLinkedList<Tweet> tweets;
    MyLinkedList<User> users;


    public TweeterIMPL() throws EmptyLinkedListException {
        Reader R = new Reader();
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
        Reader R  = new Reader();
        pilotos = R.Drivers();
        MyPriorityQueue<String> queue = new ListIMPL<>();
        for(int i = 0; i < pilotos.size(); i++){
            queue.enqueueWithPriority(pilotos.get(i),obtenerCantidadTweetsConPalabra(pilotos.get(i)));
            System.out.println(queue.get(0));
        }
    }


/*
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
    }*/
    @Override
    public MyLinkedList<Object> obtenerTop15UsuariosTweets() throws EmptyLinkedListException, EmptyHeapException {
        MyLinkedList<User> users = new ListIMPL<>();
        MyLinkedList<Object> lista = new ListIMPL<>();

        for (int i = 0; i < this.users.size(); i++) {
            users.add(this.users.get(i));
        }

        int n = users.size();
        for (int i = 0; i < Math.min(15, n); i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (users.get(j).getTweets().size() > users.get(maxIndex).getTweets().size()) {
                    maxIndex = j;
                }
            }
            //users.swap(i, maxIndex);
        }

        for (int j = 0; j < Math.min(15, n); j++) {
            User user = users.get(j);
            MyLinkedList<Object> userInfo = new ListIMPL<>();
            userInfo.add(user.getName());
            userInfo.add(user.getVerified());
            userInfo.add(user.getTweets().size());
            lista.add(userInfo);
        }

        return lista;
    }


    /*     @Override
    public MyLinkedList<Object> obtenerTop15UsuariosTweets() throws EmptyLinkedListException, EmptyQueueException {
        MyLinkedList<User> users = new ListIMPL<>();
        MyLinkedList<Object> lista = new ListIMPL<>();
        MyPriorityQueue<User> queue = new ListIMPL<>();
        for (int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            queue.enqueueWithPriority(user, user.getTweets().size());
        }
        int i = 0;
        while (!queue.isEmpty() && i < 15) {
            User user = queue.dequeue();
            users.add(user);
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
        */


    @Override
    public int obtenerCantidadHashtagsDistintos(Fecha dia) throws EmptyLinkedListException {
        MyLinkedList<Hashtag> hashtagsDistintos = new ListIMPL<>();
        for(int i=0; i<tweets.size(); i++){
            if(tweets.get(i).getFecha().equals(dia)){
                MyLinkedList<Hashtag> hashtagss = tweets.get(i).getHashtags();
                for(int j=0; j<hashtagss.size(); j++){
                    if(!hashtagsDistintos.existe(hashtagss.get(j))){
                        hashtagsDistintos.add(hashtagss.get(j));
                    }
                }
                return hashtagsDistintos.size();
            }
        }
        return hashtagsDistintos.size();
    }

    @Override
    public String obtenerHashtagMasUsado(Fecha dia) {
        return null;
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
