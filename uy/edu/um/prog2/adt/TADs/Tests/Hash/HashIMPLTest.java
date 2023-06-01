package uy.edu.um.prog2.adt.TADs.Tests.Hash;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.TADs.Hash.EmptyHashException;
import uy.edu.um.prog2.adt.TADs.Hash.HashIMPL;
import uy.edu.um.prog2.adt.TADs.Hash.MyHash;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;

import static org.junit.jupiter.api.Assertions.*;

class HashIMPLTest {

    @Test
    void put() throws EmptyHashException {
        MyHash<Integer, String> hash = new HashIMPL<>(10);
        for(int i=0;i<1000;i++){
            hash.put(i, "a");
        }
        assertTrue(hash.size() == 1000);
        assertTrue(hash.contains(145));

    }

    @Test
    void contains() throws EmptyHashException {
        MyHash<Integer, String> hash = new HashIMPL<>(10);
        hash.put(1, "a");
        hash.put(2, "b");
        hash.put(3, "c");
        assertTrue(hash.contains(1));
        assertFalse(hash.contains(5));
        assertEquals("a", hash.get(1));
        assertAll(
                () -> assertTrue(hash.contains(1)),
                () -> assertFalse(hash.contains(5)),
                () -> assertEquals("a", hash.get(1)));
    }

    @Test
    void remove() throws EmptyHashException {
        MyHash<Integer, String> hash = new HashIMPL<>(10);
        hash.put(1, "a");
        hash.put(2, "b");
        hash.put(11, "c");
        hash.put(4, "d");
        hash.remove(2);
        assertTrue(hash.size() == 3);
        assertFalse(hash.contains(2));
        assertTrue(hash.contains(1));
        assertTrue(hash.contains(11));
        assertTrue(hash.contains(4));
        hash.remove(11);
    }

    @Test
    void get() throws EmptyHashException {
        MyHash<Integer, String> hash = new HashIMPL<>(10);
        hash.put(1, "a");
        hash.put(2, "b");
        hash.put(11, "c");
        hash.put(4, "d");
        hash.remove(2);
        assertAll(
                () -> assertEquals("a", hash.get(1)),
                () -> assertEquals("c", hash.get(11)),
                () -> assertEquals("d", hash.get(4))
        );
        assertFalse(hash.contains(5));
    }

    @Test
    void keys() throws EmptyLinkedListException {
        MyHash<Integer, String> hash = new HashIMPL<>(10);
        hash.put(1, "a");
        hash.put(2, "b");
        hash.put(3, "c");
        hash.put(4, "d");
        assertTrue(hash.keys().size() == 4);
        assertEquals(1, hash.keys().get(0));
        assertEquals(2, hash.keys().get(1));
        assertFalse(hash.keys().get(2).equals(4));
    }

    @Test
    void values() throws EmptyLinkedListException {
        MyHash<Integer, String> hash = new HashIMPL<>(10);
        hash.put(1, "a");
        hash.put(2, "b");
        hash.put(3, "c");
        hash.put(4, "d");
        assertTrue(hash.values().size() == 4);
        assertEquals("a", hash.values().get(0));
        assertFalse(hash.values().get(1).equals("c"));
    }

    @Test
    void size() {
        MyHash<Integer, String> hash = new HashIMPL<>(10);
        hash.put(1, "a");
        hash.put(2, "b");
        hash.put(3, "c");
        hash.put(4, "d");
        assertTrue(hash.size() == 4);
        assertFalse(hash.size() == 5);

    }
}