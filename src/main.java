import entities.TweeterIMPL;
import java.time.LocalDate;
import java.time.LocalDateTime;

import entities.User;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import uy.edu.um.prog2.adt.TADs.Heap.EmptyHeapException;
import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;
import uy.edu.um.prog2.adt.TADs.Queue.MyQueue;
import uy.edu.um.prog2.adt.TADs.Tree.EmptyTreeException;

import java.util.Scanner;

public class main {
    public static void main(String[] args) throws EmptyLinkedListException, EmptyHeapException, EmptyQueueException, EmptyTreeException {
        TweeterIMPL Obj = new TweeterIMPL();
        boolean loop = true;
        Scanner SC = new Scanner(System.in);
        int opcion;

        while (loop) {
            try {
                System.out.println("Menú de opciones:");
                System.out.println("1. Obtener Top 10 Pilotos Activos");
                System.out.println("2. Obtener Top 15 Usuarios con más Tweets");
                System.out.println("3. Obtener Cantidad de Hashtags Distintos");
                System.out.println("4. Obtener Hashtag Más Usado");
                System.out.println("5. Obtener Top 7 Cuentas con más Favoritos");
                System.out.println("6. Obtener Cantidad de Tweets con una Palabra");
                System.out.println("7. Carga de datos");
                System.out.println("0. Salir");
                System.out.print("Ingrese la opción deseada: ");
                opcion = SC.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el año: ");
                        int año = SC.nextInt();
                        if (año < 2010 || año > 2024) {
                            System.out.println("Año inválido");
                            break;
                        } else {
                            System.out.print("Ingrese el mes: ");
                            int mes = SC.nextInt();
                            if (mes < 1 || mes > 12) {
                                System.out.println("Mes inválido");
                                break;
                            } else {
                                Obj.obtenerTop10PilotosActivos(mes, año);
                                break;
                            }
                        }
                    case 2:
                        MyQueue<User> top15UsuariosTweets = Obj.obtenerTop15UsuariosTweets();
                        while (top15UsuariosTweets.size() > 0) {
                            User usuario = top15UsuariosTweets.dequeue();
                            System.out.println("Nombre de Usuario: " + usuario.getName() + "      Cantidad de tweets: " + usuario.getTweets().size() + "      Verificacion: " + usuario.getVerified());
                        }
                        break;
                    case 3:
                        System.out.print("Ingrese el año: ");
                        int año2 = SC.nextInt();
                        System.out.print("Ingrese el mes: ");
                        int mes2 = SC.nextInt();
                        System.out.print("Ingrese el dia: ");
                        int dia2 = SC.nextInt();
                        LocalDate fecha2 = LocalDate.of(año2, mes2, dia2);
                        int cantidadHashtags = Obj.obtenerCantidadHashtagsDistintos(fecha2);
                        System.out.println("La cantidad de Hashtags es: " + cantidadHashtags);
                        break;
                    case 4:
                        System.out.print("Ingrese el año: ");
                        int año3 = SC.nextInt();
                        System.out.print("Ingrese el mes: ");
                        int mes3 = SC.nextInt();
                        System.out.print("Ingrese el dia: ");
                        int dia3 = SC.nextInt();
                        LocalDate fecha3 = LocalDate.of(año3, mes3, dia3);
                        String hashtagMasUsado = Obj.obtenerHashtagMasUsado(fecha3);
                        System.out.println("El hashtag mas usado es: " + hashtagMasUsado);
                        break;
                    case 5:
                        MyQueue<User> top7CuentasFavoritos = Obj.obtenerTop7CuentasFavoritos();
                        while (top7CuentasFavoritos.size() > 0) {
                            User usuario = top7CuentasFavoritos.dequeue();
                            System.out.println("Nombre de usuario: " + usuario.getName() + "    Cantidad de favoritos: " + usuario.getFavorites() + " ");
                        }
                        break;
                    case 6:
                        System.out.print("Ingrese una palabra: ");
                        String palabra = SC.next();
                        int cantidadTweets = Obj.obtenerCantidadTweetsConPalabra(palabra);
                        System.out.println("La cantidad de veces que aparece la palabra " + palabra + " es: " + cantidadTweets);
                        break;
                    case 7:
                        Obj.CargaDeDatos();
                        System.out.println("Datos cargados correctamente");
                        break;
                    case 0:
                        loop = false;
                        break;
                    default:
                        System.out.println("Opción inválida. Inténtalo nuevamente.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Opción inválida. Inténtalo nuevamente.");
            }
        }
    }
}
