import entities.TweeterIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;

public class main {
    public static void main(String[] args) throws EmptyLinkedListException {
        TweeterIMPL Obj = new TweeterIMPL();
        System.out.println(Obj.obtenerCantidadTweetsConPalabra("Russell"));
    }
}
