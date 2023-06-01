package entities;

public interface Tweeter {
    void agregarTweet(Tweet tweet);

    void agregarUser(User user);


    void obtenerTop10PilotosActivos(int mes, int año);
    void obtenerTop15UsuariosTweets();
    int obtenerCantidadHashtagsDistinctos(Fecha dia);
    String obtenerHashtagMasUsado(Fecha dia);
    void obtenerTop7CuentasFavoritos();
    int obtenerCantidadTweetsConPalabra(String palabra);



}
