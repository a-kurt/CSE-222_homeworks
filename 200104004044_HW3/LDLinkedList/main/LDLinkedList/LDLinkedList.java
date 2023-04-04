package LDLinkedList.main.LDLinkedList;


import java.util.AbstractList;
import java.util.List;

/**
 * Custom LinkedList with lazy deletion implemented.
 * @param <E> data type.
 */
public class LDLinkedList<E> extends AbstractList<E> implements List<E> {
    /**
     * First element of the list.
     */
    private Node<E> head;

    /**
     * Last element of the list.
     */
    private Node<E> tail;

    /**
     * Number of elements in the list.
     */
    private int size;

    /**
     * Counter for how many nodes are lazy deletion mark.
     */
    private int lazyDeletedNodes;

    /**
     * Nested class. Represents nodes.
     * @param <E> Type of list data.
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;
        public boolean isDeleted;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
            this.isDeleted = false;
        }
    }

    /**
     * Constructor of LDLinkedList.
     */
    public LDLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.lazyDeletedNodes = 0;
    }

    /**
     * Adds node to the end of the list.
     * @param data element whose presence in this collection is to be ensured
     * @return true if added.
     * @throws IllegalArgumentException if argument is invalid.
     */
    @Override
    public boolean add(E data) throws IllegalArgumentException {
        if (data == null) throw new IllegalArgumentException("ERROR! Illegal argument.");
        Node<E> newNode = new Node<E>(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.prev = this.tail;
            tail.next = newNode;
            tail = newNode;
        }
        this.size++;
        return true;
    }

    /**
     * Return the node data at given index. Ignore lazy deleted ones.
     * @param index index of the element to return
     * @return node data.
     * @throws IndexOutOfBoundsException if index is out of bounds.
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("ERROR! Index out of bounds.");

        Node<E> currNode = this.head;
        int lazyCount = 0;

        for (int i = 0; i < index; i++) {
            if (currNode.isDeleted) lazyCount++;
            currNode = currNode.next;
        }
        if (currNode.isDeleted) currNode = currNode.next;
        if (lazyCount == 1) currNode = currNode.next;
        return currNode.data;
    }

    /**
     * Mark the node at selected index lazy deleted. Check if number of lazy deletion and if its 2 call removeLazyNodes function
     * @param index the index of the element to be removed
     * @return Removed node data.
     * @throws IndexOutOfBoundsException if index is out of bounds.
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node<E> currNode = this.head;

        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        if (currNode.isDeleted) currNode = currNode.next;

        currNode.isDeleted = true;
        this.size--;
        this.lazyDeletedNodes++;
        if (lazyDeletedNodes == 2) {
            this.removeLazyNodes();
        }
        return currNode.data;
    }


    /**
     * Removes lazy deleted nodes physically.
     */
    private void removeLazyNodes() {
        Node<E> currNode = this.head;
        for (int i = 0; i < this.size + this.lazyDeletedNodes; i++) {
            if (currNode.isDeleted) {
                if (currNode.prev == null) {
                    this.head = currNode.next;
                    this.head.prev = null;
                    if (this.head == null) this.tail = null;
                } else if (currNode.next == null) {
                    this.tail = currNode.prev;
                    this.tail.next = null;
                    if (this.tail == null) this.head = null;
                } else {
                    currNode.prev.next = currNode.next;
                    currNode.next.prev = currNode.prev;
                }
            }
            currNode = currNode.next;
        }
        this.lazyDeletedNodes = 0;
    }

    /**
     * Returns size of the list.
     * @return size of the list.
     */
    @Override
    public int size() {
        return this.size;
    }
}
