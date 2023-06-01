package uy.edu.um.prog2.adt.TADs.Tree;

import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;

public interface MyBinaryTree<K, T> {

    T find(K key) throws EmptyTreeException;

    void insert (K key, T data);

    void delete (K key) throws EmptyTreeException;

    int size();

    int countLeaf();

    int countCompleteElements();

    ListIMPL<K> inOrder();

    ListIMPL<K> preOrder();

    ListIMPL<K> postOrder();

}