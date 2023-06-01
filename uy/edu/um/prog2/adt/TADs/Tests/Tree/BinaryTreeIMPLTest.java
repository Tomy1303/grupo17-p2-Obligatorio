package uy.edu.um.prog2.adt.TADs.Tests.Tree;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.Tree.BinaryTreeIMPL;
import uy.edu.um.prog2.adt.TADs.Tree.EmptyTreeException;
import uy.edu.um.prog2.adt.TADs.Tree.MyBinaryTree;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeIMPLTest {

    @Test
    void insert() throws EmptyTreeException {
        MyBinaryTree<Integer, String> tree = new BinaryTreeIMPL<>();
        tree.insert(1, "A");
        tree.insert(2, "B");
        assertTrue(tree != null);
    }

    @Test
    void find() throws EmptyTreeException {
        MyBinaryTree<Integer, String> tree = new BinaryTreeIMPL<>();
        tree.insert(1, "A");
        tree.insert(2, "B");
        assertTrue(tree.find(1).equals("A"));
    }

    @Test
    void size() {
        MyBinaryTree<Integer, String> tree = new BinaryTreeIMPL<>();
        tree.insert(1, "A");
        tree.insert(2, "B");
        assertTrue(tree.size() == 2);
    }

    @Test
    void delete() throws EmptyTreeException{
        MyBinaryTree<Integer, String> tree = new BinaryTreeIMPL<>();
        tree.insert(6, "A");
        tree.insert(4, "B");
        tree.insert(2, "c");
        tree.insert(5, "d");
        tree.insert(3, "e");
        tree.insert(1, "f");
        tree.delete(4);
        assertTrue(tree.size() == 5);
    }

    @Test
    void countLeaf() {
        MyBinaryTree<Integer, String> tree = new BinaryTreeIMPL<>();
        tree.insert(3, "A");
        tree.insert(2, "B");
        tree.insert(1, "C");
        tree.insert(6, "D");
        tree.insert(5, "E");
        tree.insert(7, "F");
        assertTrue(tree.countLeaf() == 3);
    }

    @Test
    void countCompleteElements() {
        MyBinaryTree<Integer, String> tree = new BinaryTreeIMPL<>();
        tree.insert(3, "A");
        tree.insert(2, "B");
        tree.insert(1, "C");
        tree.insert(6, "D");
        tree.insert(5, "E");
        tree.insert(7, "F");
        assertTrue(tree.countCompleteElements() == 2);
    }

    @Test
    void inOrder() throws EmptyLinkedListException {
        MyBinaryTree<Integer, String> tree = new BinaryTreeIMPL<>();
        tree.insert(3, "A");
        tree.insert(2, "B");
        tree.insert(1, "C");
        tree.insert(6, "D");
        tree.insert(5, "E");
        tree.insert(7, "F");
        ListIMPL<Integer> list = tree.inOrder();
        assertTrue(list.get(0) == 1);
        assertTrue(list.get(1) == 2);
        assertTrue(list.get(2) == 3);
        assertTrue(list.get(3) == 5);
        assertTrue(list.get(4) == 6);
        assertTrue(list.get(5) == 7);
    }

    @Test
    void preOrder() throws EmptyLinkedListException {
        MyBinaryTree<Integer, String> tree = new BinaryTreeIMPL<>();
        tree.insert(3, "A");
        tree.insert(2, "B");
        tree.insert(1, "C");
        tree.insert(6, "D");
        tree.insert(5, "E");
        tree.insert(7, "F");
        ListIMPL<Integer> list = tree.preOrder();
        assertTrue(list.get(0) == 3);
        assertTrue(list.get(1) == 2);
        assertTrue(list.get(2) == 1);
        assertTrue(list.get(3) == 6);
        assertTrue(list.get(4) == 5);
        assertTrue(list.get(5) == 7);
    }

    @Test
    void postOrder() throws EmptyLinkedListException {
        MyBinaryTree<Integer, String> tree = new BinaryTreeIMPL<>();
        tree.insert(3, "A");
        tree.insert(2, "B");
        tree.insert(1, "C");
        tree.insert(6, "D");
        tree.insert(5, "E");
        tree.insert(7, "F");
        ListIMPL<Integer> list = tree.postOrder();
        assertTrue(list.get(0) == 1);
        assertTrue(list.get(1) == 2);
        assertTrue(list.get(2) == 5);
        assertTrue(list.get(3) == 7);
        assertTrue(list.get(4) == 6);
        assertTrue(list.get(5) == 3);
    }
}