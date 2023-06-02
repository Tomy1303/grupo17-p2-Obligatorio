package entities;

import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

public class Hashtag {
    private long id;
    private String text;
    MyLinkedList<Tweet> tweets = new ListIMPL<>();

    public Hashtag(long id, String text) {
        this.id = id;
        this.text = text;
        this.tweets = new ListIMPL<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MyLinkedList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(MyLinkedList<Tweet> tweets) {
        this.tweets = tweets;
    }
}
