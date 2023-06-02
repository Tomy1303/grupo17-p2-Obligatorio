package entities;

import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

public class Tweet {
    private long id;
    private String content;
    private String source;
    private Boolean isReTweet;
    private User user;
    private Fecha fecha;
    private MyLinkedList<Hashtag> hashtags = new ListIMPL<>();

    public Tweet(long id, String content, String source, Boolean isReTweet, User user, Fecha fecha) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.isReTweet = isReTweet;
        this.user = user;
        this.fecha = fecha;
        this.hashtags = new ListIMPL<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getReTweet() {
        return isReTweet;
    }

    public void setReTweet(Boolean reTweet) {
        isReTweet = reTweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public MyLinkedList<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(MyLinkedList<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
