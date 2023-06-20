package FilesReader;

import entities.Fecha;
import entities.Hashtag;
import entities.Tweet;
import entities.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.adt.TADs.Hash.HashIMPL;
import uy.edu.um.prog2.adt.TADs.Hash.MyHash;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Reader {

    private MyLinkedList<User> users;
    private MyLinkedList<Tweet> tweets;
    private MyHash<String,Hashtag> hashtags;
    private MyHash<String,User> usuariosE;
    private MyHash<String,Tweet> tweetsE;

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
        this.hashtags = new HashIMPL<>(10000);
        this.usuariosE = new HashIMPL<>(200000);
        this.tweetsE = new HashIMPL<>(200000);
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
                        User  user;
                        Fecha fecha;
                        if(usuariosE.contains(csvRecord.get(1))){
                            user = usuariosE.get(csvRecord.get(1));
                            user.setFavorites((int) Double.parseDouble(csvRecord.get(7)));
                        }else {
                            String[] fechayHora = csvRecord.get(4).split(" ");
                            String[] fechaComponents = fechayHora[0].split("-");
                            int año = Integer.parseInt(fechaComponents[0]);
                            int mes = Integer.parseInt(fechaComponents[1]);
                            int dia = Integer.parseInt(fechaComponents[2]);
                            fecha = new Fecha(año, mes, dia);

                            long id = Long.parseLong(csvRecord.get(0));
                            String name = csvRecord.get(1);
                            int followers = (int) Double.parseDouble(csvRecord.get(5));
                            int favorites = (int) Double.parseDouble(csvRecord.get(7));
                            int friends = (int) Double.parseDouble(csvRecord.get(6));
                            String location = csvRecord.get(2);
                            String description = csvRecord.get(3);
                            boolean verified = Boolean.parseBoolean(csvRecord.get(8));

                            user = new User(id, name, followers, favorites, friends, location, description, fecha, verified);
                            users.add(user);
                            usuariosE.put(name,user);
                        }
                        String Fecha_Hora = csvRecord.get(9);
                        if(!tweetsE.contains(Fecha_Hora+csvRecord.get(1))){
                            //long id = Long.parseLong((Fecha_Hora + csvRecord.get(1)));
                            String content = csvRecord.get(10);
                            String source = csvRecord.get(12);
                            boolean isRetweet = Boolean.parseBoolean(csvRecord.get(13));
                            String[] fechayHora = Fecha_Hora.split(" ");
                            String[] fechaComponents = fechayHora[0].split("-");
                            int año = Integer.parseInt(fechaComponents[0]);
                            int mes = Integer.parseInt(fechaComponents[1]);
                            int dia = Integer.parseInt(fechaComponents[2]);
                            fecha = new Fecha(año, mes, dia);
                            String hashtagsString = csvRecord.get(11).replaceAll("[\\[\\]\"]", "");
                            String[] hashtagsArray = hashtagsString.split(",");
                            MyLinkedList<Hashtag> hashtagsTweet = new ListIMPL<>();
                            for (String s : hashtagsArray) {
                                s=s.toLowerCase();
                                if(hashtags.contains(s)){
                                    hashtagsTweet.add(hashtags.get(s));
                                }else{
                                    Hashtag hashtag = new Hashtag(Long.parseLong(csvRecord.get(0)), s);
                                    hashtagsTweet.add(hashtag);
                                    hashtags.put(s,hashtag);
                                }
                            }
                            Tweet tweet = new Tweet(1, content, source, isRetweet, user, fecha, hashtagsTweet);
                            tweets.add(tweet);
                            user.setTweets(tweet);
                            tweetsE.put(Fecha_Hora+csvRecord.get(1),tweet);
                        }
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