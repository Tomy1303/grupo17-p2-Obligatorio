package FilesReader;

import entities.Fecha;
import entities.Hashtag;
import entities.Tweet;
import entities.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Reader {

    private MyLinkedList<User> users;
    private MyLinkedList<Tweet> tweets;

    public MyLinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(MyLinkedList<User> users) {
        this.users = users;
    }

    public MyLinkedList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(MyLinkedList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Reader(){
        this.users = new ListIMPL<>();
        this.tweets =  new ListIMPL<>();
    }

    public static void main(String[] args) throws EmptyLinkedListException {
        Reader reader = new Reader();
        reader.CSVReader();
    }

    public MyLinkedList Drivers() {
        String nombreArchivo = "src/FilesReader/drivers.txt";
        MyLinkedList<String> drivers = new ListIMPL<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                drivers.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    public void CSVReader() throws EmptyLinkedListException {
        String archivoCSV = "src/FilesReader/f1_dataset_test.csv";

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            int t=0;
            for (CSVRecord csvRecord : csvParser) {
                if (t != 0) {
                    try {
                        String[] fechayHora = csvRecord.get(4).split(" ");
                        String[] fechaComponents = fechayHora[0].split("-");
                        int año = Integer.parseInt(fechaComponents[0]);
                        int mes = Integer.parseInt(fechaComponents[1]);
                        int dia = Integer.parseInt(fechaComponents[2]);
                        Fecha fecha = new Fecha(año, mes, dia);

                        long id = Long.parseLong(csvRecord.get(0));
                        String name = csvRecord.get(1);
                        int followers = (int) Double.parseDouble(csvRecord.get(5));
                        int favorites = (int) Double.parseDouble(csvRecord.get(7));
                        int friends = (int) Double.parseDouble(csvRecord.get(6));
                        String location = csvRecord.get(2);
                        String description = csvRecord.get(3);
                        boolean verified = Boolean.parseBoolean(csvRecord.get(8));

                        User user = new User(id, name, followers, favorites, friends, location, description, fecha, verified);
                        users.add(user);

                        long tweetId = Long.parseLong(csvRecord.get(0));
                        String content = csvRecord.get(10);
                        String source = csvRecord.get(12);
                        boolean isRetweet = Boolean.parseBoolean(csvRecord.get(13));

                        String hashtagsString = csvRecord.get(11).replaceAll("[\\[\\]\"]", "");
                        String[] hashtagsArray = hashtagsString.split(",");
                        MyLinkedList<Hashtag> hashtagsTweet = new ListIMPL<>();
                        for (String s : hashtagsArray) {
                            Hashtag hashtag = new Hashtag(Long.parseLong(csvRecord.get(0)), s);
                            hashtagsTweet.add(hashtag);
                        }
                        Tweet tweet = new Tweet(tweetId, content, source, isRetweet, user, fecha, hashtagsTweet);
                        tweets.add(tweet);
                    } catch (Exception ignored) {
                    }
                }else{
                    t++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}