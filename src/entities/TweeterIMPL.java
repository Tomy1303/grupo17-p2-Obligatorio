package entities;

import exceptions.EntidadYaExiste;
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

    MyLinkedList<PilotoMencionado> pilotosMencionados = new ListIMPL<>();

    public void agregarTweet(Tweet tweet) throws EntidadYaExiste, EmptyLinkedListException {
        for (int i = 0; i < tweets.size(); i++) {
                if (tweets.get(i).getId() == tweet.getId()) {
                    throw new EntidadYaExiste();
                } else {
                    tweets.add(tweet);
                }
            }
        }



    public void agregarUser(User user) throws EmptyLinkedListException, EntidadYaExiste {
        for( int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == user.getId()){
                throw new EntidadYaExiste();
            }else{
                users.add(user);
            }
        }
    }

    public void agregarHashtag(Hashtag hashtag) {
        hashtags.add(hashtag);
    }

    /*
    private PilotoMencionado buscarPiloto(MyLinkedList<PilotoMencionado> pilotos, String nombrePiloto) {
        for (PilotoMencionado piloto : pilotos) {
            if (piloto.getNombre().equals(nombrePiloto)) {
                return piloto;
            }
        }
        return null;
    }
*/


    @Override
    public void obtenerTop10PilotosActivos(int mes, int año) throws EmptyLinkedListException {
    }
        /*
        MyLinkedList<PilotoMencionado> pilotosMencionados = new ListIMPL<>();

        // Obtener todos los tweets del mes y año proporcionados
        for (int i = 0; i < tweets.size(); i++) {
            Tweet tweet = tweets.get(i);
            Fecha fechaTweet = tweet.getFecha();
            if (fechaTweet.getMes() == mes && fechaTweet.getAño() == año) {
                // Verificar si el tweet menciona pilotos
                for (int j = 0; j < tweet.getHashtags().size(); j++) {
                    Hashtag hashtag = tweet.getHashtags().get(j);
                    if (hashtag.getText().startsWith("#piloto")) {
                        String nombrePiloto = hashtag.getText().substring(7); // Obtener el nombre del piloto sin el hashtag
                        // Buscar si el piloto ya está en la lista
                        PilotoMencionado piloto = buscarPiloto(pilotosMencionados, nombrePiloto);
                        if (piloto != null) {
                            piloto.incrementarMenciones();
                        } else {
                            pilotosMencionados.add(new PilotoMencionado(nombrePiloto));
                        }
                    }
                }
            }
        }

        // Ordenar los pilotos por la cantidad de menciones de manera descendente
        pilotosMencionados.sort(Comparator.comparingInt(PilotoMencionado::getMenciones).reversed());

        // Tomar los 10 primeros pilotos de la lista ordenada
        MyLinkedList<PilotoMencionado> top10Pilotos = pilotosMencionados.subList(0, Math.min(10, pilotosMencionados.size()));

        // Imprimir el listado de los 10 pilotos más mencionados
        System.out.println("Los 10 pilotos más mencionados en " + mes + "/" + año + " son:");
        for (PilotoMencionado piloto : top10Pilotos) {
            System.out.println(piloto.getNombre() + ": " + piloto.getMenciones() + " menciones");
        }
    }

         */

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
