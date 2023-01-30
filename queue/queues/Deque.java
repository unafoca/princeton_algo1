/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // public class Deque<Item> {
    private Node first, last;
    private int n;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        }
        else {
            last.prev = oldlast;
            oldlast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (n > 1) {
            first.prev = null;
        }
        else {
            last = null;
        }
        n--;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        if (n > 1) {
            last.next = null;
        }
        else {
            first = null;
        }
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() { /* not supported */ }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<>();
        test.addFirst(3);
        test.addFirst(5);
        test.addFirst(7);
        test.addFirst(9);
        test.removeFirst();
        System.out.println("first test");
        System.out.println(test.size());
        System.out.println(test.first.item);
        System.out.println(test.first.next.item);
        System.out.println(test.first.next.next.item);
        System.out.println("second test");
        test.removeLast();
        test.removeLast();
        System.out.println(test.size());
        System.out.println(test.first.item);
        System.out.println(test.last.item);
        System.out.println("last test");
        test.removeLast();
        System.out.println(test.first);
        System.out.println(test.last);


    }

}
