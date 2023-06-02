package entities;


import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

public class User {
    private long id;
    private String name;
    private MyLinkedList<Tweet> tweets = new ListIMPL<>();

    public User(long id, String name) {
        this.id = id;
        this.name = name;
        this.tweets = new ListIMPL<>();
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

    public void setTweets(MyLinkedList<Tweet> tweets) {
        this.tweets = tweets;
    }
}
