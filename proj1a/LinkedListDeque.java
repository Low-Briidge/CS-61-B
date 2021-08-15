public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

//    public LinkedListDeque(LinkedListDeque other) {
//        size = other.size();
//        sentinel = new Node();
//        sentinel.prev = sentinel;
//        sentinel.next = sentinel;
//        for (int i = 0; i < size; i++) {
//            this.addLast((T) other.get(i));
//        }
//    }

    private class Node {
        private Node prev;
        private T item;
        private Node next;
    }

    public void addFirst(T item) {
        size += 1;
        Node temp = new Node();
        temp.item = item;
        temp.next = sentinel.next;
        temp.prev = sentinel;
        sentinel.next.prev = temp;
        sentinel.next = temp;

    }

    public void addLast(T item) {
        size += 1;
        Node temp = new Node();
        temp.item = item;
        sentinel.prev.next = temp;
        temp.prev = sentinel.prev;
        sentinel.prev = temp;
        temp.next = sentinel;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node last = sentinel.prev;
        T res = sentinel.prev.item;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        last.prev = null;
        last.next = null;
        return res;
    }

    public T get(int index) {
        if (index + 1 > size) {
            return null;
        }
        Node p = sentinel.next;
        while (index-- > 0) {
            p = p.next;
        }
        return p.item;
    }

    public Node recursiveHelp(Node p, int index) {
        if (index == 0) {
            return p;
        }
        return recursiveHelp(p.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index + 1 > size) {
            return null;
        }
        return recursiveHelp(sentinel.next, index).item;
    }
}
