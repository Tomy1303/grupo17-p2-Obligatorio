package uy.edu.um.prog2.adt.TADs.Hash;

import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;

public class HashIMPL<K, V> implements MyHash<K, V> {

    private int capacity;
    private HashNode<K, V>[] table;
    private int size;

    public HashIMPL(int capacity) {
        this.capacity = capacity;
        this.table = new HashNode[capacity];
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        int index = getIndex(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        index = colision(index);
        if (this.table[index]!=null && this.table[index].equals(newNode)) {
            this.table[index].setValue(value);
            return;
        }

        this.table[index] = newNode;
        this.size++;

        if (this.size >= this.capacity * 0.75) {
            resizeTable();
        }
    }

    private int colision(int index){
        while (this.table[index] != null && !this.table[index].isDeleted()) {
            if(index==this.capacity-1){
                index=0;
            }else {
                index = (index + 1) % this.capacity;
            }
        }
        return index;
    }

    @Override
    public boolean contains(K key) {
        int index = getIndex(key);
        HashNode<K, V> targetNode = new HashNode<>(key, null);
        while (this.table[index] != null) {
            if(!this.table[index].isDeleted()) {
                if (this.table[index] != null && this.table[index].equals(targetNode)) {
                    return true;
                }
                if (index == this.capacity - 1) {
                    index = 0;
                } else {
                    index = (index + 1) % this.capacity;
                }
            }else{
                index = (index + 1) % this.capacity;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) throws EmptyHashException {
        int index = getIndex(key);
        HashNode<K, V> targetNode = new HashNode<>(key, null);
        while (this.table[index] != null) {
            if(!this.table[index].isDeleted()) {
                if (this.table[index].equals(targetNode)) {
                    this.table[index].setDeleted(true);
                    this.size--;
                    return;
                }
                if (index == this.capacity - 1) {
                    index = 0;
                } else {
                    index = (index + 1) % this.capacity;
                }
            }else{
                index = (index + 1) % this.capacity;
            }
        }
        throw new EmptyHashException();
    }

    @Override
    public V get(K key) throws EmptyHashException {
        int index = getIndex(key);
        HashNode<K, V> targetNode = new HashNode<>(key, null);
        while (this.table[index] != null) {
            if(!this.table[index].isDeleted()) {
                if (this.table[index].equals(targetNode)) {
                    return this.table[index].getValue();
                }
                if (index == this.capacity - 1) {
                    index = 0;
                } else {
                    index = (index + 1) % this.capacity;
                }
            }else{
                index = (index + 1) % this.capacity;
            }
        }

        throw new EmptyHashException();
    }

    @Override
    public ListIMPL<K> keys() {
        ListIMPL<K> keyList = new ListIMPL<>();

        for (HashNode<K, V> node : this.table) {
            if (node != null && !node.isDeleted()) {
                keyList.add(node.getKey());
            }
        }

        return keyList;
    }

    @Override
    public ListIMPL<V> values() {
        ListIMPL<V> valueList = new ListIMPL<>();

        for (HashNode<K, V> node : this.table) {
            if (node != null && !node.isDeleted()) {
                valueList.add(node.getValue());
            }
        }

        return valueList;
    }

    @Override
    public int size() {
        return this.size;
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % this.capacity;
    }

    private void resizeTable() {
        HashNode<K, V>[] oldTable = this.table;
        this.table = new HashNode[this.capacity * 2];
        this.size = 0;
        this.capacity *= 2;

        for (HashNode<K, V> node : oldTable) {
            if (node != null && !node.isDeleted()) {
                put(node.getKey(), node.getValue());
            }
        }
    }
}
