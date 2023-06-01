package uy.edu.um.prog2.adt.TADs.Hash;

import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;

public interface MyHash<K,V>  {

    void put(K key, V value);

    boolean contains(K key);

    void remove(K clave) throws EmptyHashException;

    V get(K key) throws EmptyHashException;

    ListIMPL<K> keys();

    ListIMPL<V> values();

    int size();
}
