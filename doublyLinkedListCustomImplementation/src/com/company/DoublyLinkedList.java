package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

class DoublyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;


    private final static class Node<T> {
        private final T data;
        private Node<T> next;
        private Node<T> previous;

        public Node(T data) {
            this.data = data;
        }
    }

    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (head == null) {
            head = newNode;
            tail = head;
            size++;

            return;
        }
        newNode.previous = tail;
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void add(int index, T data) {
        Node<T> newNode = new Node<T>(data);
        if (head == null && index != 0) {
            throw new IndexOutOfBoundsException();
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            head = newNode;
            tail = head;
            size++;

            return;
        } else if (index == size) {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            current.next.previous = current;
            tail = current.next;
            size++;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        Node<T> previousNode = current.previous;
        previousNode.next = newNode;
        previousNode.next.next = current;
        size++;
    }


    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> current = head;
        head = current.next;
        size--;

        return current.data;
    }

    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        } else if (tail.previous == null) {
            T data = tail.data;
            head = null;
            tail = null;
            size--;
            return data;
        }

        T data = tail.data;
        tail = tail.previous;
        tail.next = null;
        size--;

        return data;
    }

    public T remove(int index) {
        checkElementIndex(index);

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }
        Node<T> previousNode = getNode(index - 1);
        T removedElement = previousNode.next.data;
        previousNode.next = previousNode.next.next;
        size--;
        return removedElement;
    }

    public int size() {
        return this.size;
    }

    private Node<T> getNode(int index) {
        checkElementIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
}

