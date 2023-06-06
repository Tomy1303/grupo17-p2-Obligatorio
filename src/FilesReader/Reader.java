package FilesReader;

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

    public void Drivers() {
        String nombreArchivo = "src/FilesReader/drivers.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                // Procesar la línea leída
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CSVReader(){
        String archivoCSV = "src/FilesReader/f1_dataset_test.csv";

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String columna1 = csvRecord.get(0); // Obtener el valor de la primera columna
                String columna2 = csvRecord.get(1); // Obtener el valor de la segunda columna
                // Leer más columnas según sea necesario

                // Procesar los valores leídos
                System.out.println("Columna 1: " + columna1);
                System.out.println("Columna 2: " + columna2);
                System.out.println("-------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}