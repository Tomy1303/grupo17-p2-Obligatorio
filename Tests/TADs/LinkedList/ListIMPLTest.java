package TADs.LinkedList;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.TADs.LinkedList.EmptyLinkedListException;
import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;
import uy.edu.um.prog2.adt.TADs.LinkedList.MyLinkedList;
import uy.edu.um.prog2.adt.TADs.LinkedList.MySimpleLinkedList;
import uy.edu.um.prog2.adt.TADs.Queue.EmptyQueueException;
import uy.edu.um.prog2.adt.TADs.Queue.MyPriorityQueue;
import uy.edu.um.prog2.adt.TADs.Queue.MyQueue;
import uy.edu.um.prog2.adt.TADs.Stack.MyStack;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class ListIMPLTest {

    @Test
    void add() {
        MyLinkedList linkedList = new ListIMPL();
        linkedList.add(1);
        linkedList.add(2);
        assertTrue(linkedList.size() == 2);

    }

    @Test
    void remove() throws EmptyLinkedListException {
        MyLinkedList linkedList = new ListIMPL();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.remove(1);
        assertTrue(linkedList.size() == 3);
        assertTrue(linkedList.get(2).equals(4));
    }

    @Test
    void get() throws EmptyLinkedListException {
        MyLinkedList linkedList = new ListIMPL();
        linkedList.add(1);
        linkedList.add(5);
        try {
            assertTrue(linkedList.get(1).equals(5));
        } catch (EmptyLinkedListException e) {
            e.printStackTrace();
        }
        MySimpleLinkedList<Integer> linkedList1 = new ListIMPL<>();
        linkedList1.addS(1);
        linkedList1.addS(5);
        assertEquals(5, linkedList1.get(1));
    }

    @Test
    void enqueue() throws EmptyQueueException, EmptyLinkedListException {
        MyQueue queue = new ListIMPL();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.get(0));
    }

    @Test
    void enqueueWithPriority() throws EmptyLinkedListException {
        MyPriorityQueue<Integer> queue = new ListIMPL<>();
        queue.enqueueWithPriority(9, 3);
        queue.enqueueWithPriority(7, -6);
        queue.enqueueWithPriority(5, 1);
        assertEquals(9, queue.get(0));
    }

    @Test
    void dequeue() throws EmptyQueueException, EmptyLinkedListException {
        MyQueue queue = new ListIMPL();
        queue.enqueue(9);
        queue.enqueue(2);
        assertEquals(9, queue.dequeue());
        MyPriorityQueue<Integer> queue1 = new ListIMPL<>();
        queue1.enqueueWithPriority(9, 3);
        queue1.enqueueWithPriority(7, -6);
        queue1.enqueueWithPriority(5, 1);
        assertEquals(9, queue1.dequeue());
    }

    @Test
    void existe() {
        MyQueue queue = new ListIMPL();
        queue.enqueue(1);
        queue.enqueue(2);
        assertTrue(queue.existe(1));
    }


    @Test
    void addFirst() {
        MyLinkedList linkedList = new ListIMPL();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        assertTrue(linkedList.size() == 2);
    }

    @Test
    void pop() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertTrue(stack.size() == 1);
    }

    @Test
    void top() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        assertTrue(stack.peek().equals(2));
    }

    @Test
    void push() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        assertEquals(1, stack.peek());
        stack.push(2);
        assertTrue(stack.size() == 2);
    }

    @Test
    void isEmpty() {
        MyStack<Integer> stack = new ListIMPL<>();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void makeEmpty() {
        MyStack<Integer> stack = new ListIMPL<>();
        stack.push(1);
        stack.push(2);
        stack.makeEmpty();
        assertTrue(stack.isEmpty());
    }

    @Test
    void size() throws EmptyLinkedListException {
        MyStack<Integer> stack = new ListIMPL<>();
        stack.push(1);
        stack.push(2);
        assertTrue(stack.size() == 2);
        MySimpleLinkedList<Integer> simpleLinkedList = new ListIMPL<>();
        simpleLinkedList.addS(1);
        simpleLinkedList.addS(2);
        simpleLinkedList.removeS(0);
        assertEquals(1,simpleLinkedList.size());
    }

    @Test
    void change() throws EmptyLinkedListException {
        MyLinkedList<Integer> linkedList = new ListIMPL<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.change(0, 1);
        assertTrue(linkedList.get(0).equals(2));
    }

    @Test
    void containsS() {
        MySimpleLinkedList<Integer> linkedList = new ListIMPL<>();
        linkedList.addS(43);
        linkedList.addS(7);
        assertTrue(linkedList.containsS(43));
    }
}

