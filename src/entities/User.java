package entities;


import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

public class User {
    private long id;
    private String name;

    private int followers;

    private int favorites;

    private int friends;

    private String location;

    private String description;

    private Fecha fecha;

    private Boolean verified;
    private MyLinkedList<Tweet> tweets = new ListIMPL<>();

    public User(long id, String name, int followers, int favorites, int friends, String location, String description, Fecha fechaCreacion, Boolean verified) {
        this.id = id;
        this.name = name;
        this.followers = followers;
        this.favorites = favorites;
        this.friends = friends;
        this.location = location;
        this.description = description;
        this.fecha = fechaCreacion;
        this.verified = verified;
        this.tweets = new ListIMPL<>();
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyLinkedList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Tweet tweet) {
        this.tweets.add(tweet);
    }
}
