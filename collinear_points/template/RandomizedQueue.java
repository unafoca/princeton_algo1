/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // construct an empty randomized queue
    private Item[] s;
    private int N;
    private int last;

    // need to avoid this cheat later
    public RandomizedQueue() {
        s = (Item[]) new Object[4];
        N = 0;

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] s_copy = (Item[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i <= last; i++) {
            if (s[i] != null) {
                s_copy[j] = s[i];
                j++;
            }
        }
        s = s_copy;
        last = j - 1;

    }

    // add the item
    public void enqueue(Item item) {
        if (isEmpty()) {
            last = 0;
            s[last] = item;
            N += 1;
        }
        else {
            last++;
            s[last] = item;
            N += 1;
        }
        if (last == s.length - 1) {
            resize(2 * N);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        int indRand = StdRandom.uniformInt(last + 1);
        Item res = s[indRand];
        while (res == null) {
            indRand = StdRandom.uniformInt(last + 1);
            res = s[indRand];
        }
        s[indRand] = null;
        N--;

        // find the last available index
        while (N > 0 && s[last] == null) {
            last--;
        }

        if (N <= s.length / 4) {
            resize(Math.max(s.length / 2, 4));
        }

        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int indRand = StdRandom.uniformInt(last + 1);
        Item res = s[indRand];
        while (res == null) {
            indRand = StdRandom.uniformInt(last + 1);
            res = s[indRand];
        }
        return res;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int current;

        public boolean hasNext() {
            return N > 0 && current < last;
        }

        public void remove() { /* not supported */ }

        public Item next() {
            while (s[current] == null) {
                current++;
            }
            Item res = s[current];
            current++;
            return res;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        System.out.println("first test");
        System.out.println(q.size()); // 0
        q.enqueue(5);
        q.enqueue(3);
        q.enqueue(2);
        System.out.println("second test");
        System.out.println(q.size()); // 3
        System.out.println("third test");
        System.out.println(q.last);
        System.out.println(q.sample()); // 2 / 3/ 5
        int x = q.dequeue();
        int y = q.dequeue();
        int z = q.dequeue();
        System.out.println("fourth test");
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(q.size()); // 0
        q.resize(10);
        q.enqueue(5);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(5);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(5);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(5);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(2);
        System.out.println("fifth test");
        System.out.println(q.size()); // 18
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        System.out.println("sixth test");
        System.out.println(q.size()); // 5
        System.out.println(((Object[]) q.s).length); // 5
    }
}
