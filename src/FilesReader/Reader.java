package FilesReader;

import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public Reader(){}

    public static void main(String[] args) {
        Reader r = new Reader();
        r.CSVReader();
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

    public void CSVReader(){
        String archivoCSV = "src/FilesReader/f1_dataset_test.csv";

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String id = csvRecord.get(0);
                String name = csvRecord.get(1);
                String localizacion = csvRecord.get(2);
                String description = csvRecord.get(3);
                String created = csvRecord.get(4);
                String followers = csvRecord.get(5);
                String friends = csvRecord.get(6);
                String favoritos = csvRecord.get(7);
                String verificacion = csvRecord.get(8);
                String data = csvRecord.get(9);
                String text = csvRecord.get(10);
                String hashtags = csvRecord.get(11);
                String sources = csvRecord.get(12);
                String isRetweet = csvRecord.get(13);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}