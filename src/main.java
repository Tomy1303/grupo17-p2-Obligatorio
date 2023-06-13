import entities.Fecha;
import entities.TweeterIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws EmptyLinkedListException, EmptyHeapException, EmptyQueueException {
        TweeterIMPL Obj = new TweeterIMPL();
        boolean loop = true;
        Scanner SC = new Scanner(System.in);
        int opcion;

        while (loop) {
            System.out.println("Menú de opciones:");
            System.out.println("1. Obtener Top 10 Pilotos Activos");
            System.out.println("2. Obtener Top 15 Usuarios con más Tweets");
            System.out.println("3. Obtener Cantidad de Hashtags Distintos");
            System.out.println("4. Obtener Hashtag Más Usado");
            System.out.println("5. Obtener Top 7 Cuentas con más Favoritos");
            System.out.println("6. Obtener Cantidad de Tweets con una Palabra");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción deseada: ");
            opcion = SC.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el mes: ");
                    int mes = SC.nextInt();
                    System.out.print("Ingrese el año: ");
                    int año = SC.nextInt();
                    Obj.obtenerTop10PilotosActivos(mes, año);
                    break;
                case 2:
                    MyLinkedList<Object> top15UsuariosTweets = Obj.obtenerTop15UsuariosTweets();
                    // Haz algo con top15UsuariosTweets
                    System.out.println(top15UsuariosTweets.size());
                    break;
                case 3:
                    System.out.print("Ingrese el mes: ");
                    int mes2 = SC.nextInt();
                    System.out.print("Ingrese el año: ");
                    int año2 = SC.nextInt();
                    System.out.print("Ingrese el dia: ");
                    int dia2 = SC.nextInt();
                    Fecha f=new Fecha(año2,mes2,dia2);
                    int cantidadHashtags = Obj.obtenerCantidadHashtagsDistintos(f);
                    // Haz algo con cantidadHashtags
                    break;
                case 4:
                    System.out.print("Ingrese el mes: ");
                    int mes3 = SC.nextInt();
                    System.out.print("Ingrese el año: ");
                    int año3 = SC.nextInt();
                    System.out.print("Ingrese el dia: ");
                    int dia3 = SC.nextInt();
                    Fecha f2=new Fecha(año3,mes3,dia3);
                    String hashtagMasUsado = Obj.obtenerHashtagMasUsado(f2);
                    // Haz algo con hashtagMasUsado
                    break;
                case 5:
                    MyLinkedList<Object> top7CuentasFavoritos = Obj.obtenerTop7CuentasFavoritos();
                    // Haz algo con top7CuentasFavoritos
                    break;
                case 6:
                    System.out.print("Ingrese una palabra: ");
                    String palabra = SC.next();
                    int cantidadTweets = Obj.obtenerCantidadTweetsConPalabra(palabra);
                    // Haz algo con cantidadTweets
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo nuevamente.");
                    break;
            }
        }
    }
}
