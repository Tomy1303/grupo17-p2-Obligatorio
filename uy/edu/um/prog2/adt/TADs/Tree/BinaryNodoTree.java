package uy.edu.um.prog2.adt.TADs.Tree;

public class BinaryNodoTree<K extends Comparable<K>, T>{
    K key;
    T data;
    BinaryNodoTree<K, T> leftChild;
    BinaryNodoTree<K, T> rightChild;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNodoTree<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryNodoTree<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryNodoTree<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryNodoTree<K, T> rightChild) {
        this.rightChild = rightChild;
    }

    public BinaryNodoTree(K key, T data) {
        this.key = key;
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

}