package FilesReader;

import entities.Hashtag;
import entities.Tweet;
import entities.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private MyHash<String,Hashtag> hashtags;
    private MyHash<String,User> usuariosE;
    private MyHash<String,Tweet> tweetsE;

    public MyLinkedList<User> getUsers() {
        return usuariosE.values();
    }

    public MyLinkedList<Tweet> getTweets() {
        return tweetsE.values();
    }

    public Reader(){
        this.hashtags = new HashIMPL<>(30000);
        this.usuariosE = new HashIMPL<>(200000);
        this.tweetsE = new HashIMPL<>(200000);
    }

    public MyLinkedList<String> Drivers() {
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
        String archivoCSV = "src/FilesReader/f1_dataset.csv";
        //String archivoCSV = "src/FilesReader/f1_dataset_test.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            int t=0;
            for (CSVRecord csvRecord : csvParser) {
                if (t != 0) {
                    try {
                        User  user;
                        LocalDateTime fecha;
                        if(usuariosE.contains(csvRecord.get(1))){
                            user = usuariosE.get(csvRecord.get(1));
                            user.setFavorites((int) Double.parseDouble(csvRecord.get(7)));
                        }else {
                            fecha = LocalDateTime.parse(csvRecord.get(4), formatter);
                            long id = Long.parseLong(csvRecord.get(0));
                            String name = csvRecord.get(1);
                            int followers = (int) Double.parseDouble(csvRecord.get(5));
                            int favorites = (int) Double.parseDouble(csvRecord.get(7));
                            int friends = (int) Double.parseDouble(csvRecord.get(6));
                            String location = csvRecord.get(2);
                            String description = csvRecord.get(3);
                            boolean verified = Boolean.parseBoolean(csvRecord.get(8));

                            user = new User(id, name, followers, favorites, friends, location, description, fecha, verified);
                            usuariosE.put(name,user);
                        }
                        String Fecha_Hora = csvRecord.get(9);
                        if(!tweetsE.contains(Fecha_Hora+csvRecord.get(1))){
                            long id = Long.parseLong(csvRecord.get(0));
                            String content = csvRecord.get(10);
                            String source = csvRecord.get(12);
                            boolean isRetweet = Boolean.parseBoolean(csvRecord.get(13));
                            fecha = LocalDateTime.parse(Fecha_Hora, formatter);
                            MyLinkedList<Hashtag> hashtagsTweet = new ListIMPL<>();
                            String hashtagsString = csvRecord.get(11).replaceAll("[\\[\\]\"]", "");
                            String[] hashtagsArray = hashtagsString.split(",");
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
                            Tweet tweet = new Tweet(id, content, source, isRetweet, user, fecha, hashtagsTweet);
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