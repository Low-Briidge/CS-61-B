public interface Deque<T> {
    public boolean isEmpty();

    public int size();

    public void addFirst(T item);

    public void addLast(T item);

    public T removeFirst();

    public T removeLast();

    public T get(int index);

    public void printDeque();

    public class ArrayDeque<T> implements Deque<T> {
        private Array items;
        private int capacity;
        private int size;
        private int nextFirst;
        private int nextLast;

        private class Array {
            private T[] item;

            public Array(int capacity) {
                item = (T[]) new Object[capacity];
            }
        }

        public ArrayDeque() {
            items = new Array(8);
            capacity = 8;
            nextFirst = 3;
            nextLast = 4;
            size = 0;
        }

        //    public ArrayDeque(ArrayDeque other) {
//        items.item = (T[]) new Object[other.capacity];
//        size = other.size;
//        capacity = other.capacity;
//        nextFirst = other.nextFirst;
//        nextLast = other.nextLast;
//        for (int i = 0; i < other.capacity; i++) {
//            items.item[i] = (T) other.items.item[i];
//        }
//    }
        private void resizing(String str) {
            Array temp;
            if (str.equals("increase")) {
                temp = new Array(capacity * 2);
                for (int i = 0; i < capacity; i++) {
                    temp.item[i] = this.get(i);
                }
            } else {
                temp = new Array(capacity / 2);
                for (int i = 0; i < size; i++) {
                    temp.item[i] = this.get(i);
                }
            }
            items = temp;
            nextLast = size;
            capacity = items.item.length;
            nextFirst = capacity - 1;
        }

        @Override
        public void addFirst(T item) {
            if (nextFirst == nextLast) {
                resizing("increase");
            }
            size += 1;
            items.item[nextFirst] = item;
            if (nextFirst > 0) {
                nextFirst -= 1;
            } else {
                nextFirst = capacity - 1;
            }
        }

        @Override
        public void addLast(T item) {
            if (nextLast == nextFirst) {
                resizing("increase");
            }
            size += 1;
            items.item[nextLast] = item;
            if (nextLast < capacity - 1) {
                nextLast += 1;
            } else {
                nextLast = 0;
            }
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public void printDeque() {
            for (int i = (nextFirst + 1) % capacity; i != nextLast; i = (i + 1) % capacity) {
                System.out.print(items.item[i] + " ");
            }
            System.out.println();
        }

        @Override
        public T removeFirst() {
            if (size == 0) {
                return null;
            }
            if (capacity >= 16 && 1.0 * size / capacity < 0.25) {
                resizing("decrease");
            }
            size -= 1;
            T res = items.item[(nextFirst + 1) % capacity];
            nextFirst = (nextFirst + 1) % capacity;
            return res;
        }

        @Override
        public T removeLast() {
            if (size == 0) {
                return null;
            }
            if (capacity >= 16 && 1.0 * size / capacity < 0.25) {
                resizing("decrease");
            }
            size -= 1;
            if (nextLast > 0) {
                nextLast -= 1;
            } else {
                nextLast = capacity - 1;
            }
            return items.item[nextLast];
        }

        @Override
        public T get(int index) {
            int i = (nextFirst + 1) % capacity;
            if (index + 1 > size) {
                return null;
            }
            for (; i != nextLast && index > 0; i = (i + 1) % capacity) {
                index--;
            }
            return items.item[i];
        }


    }

    public class LinkedListDeque<T> implements Deque<T> {
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

        @Override
        public void addFirst(T item) {
            size += 1;
            Node temp = new Node();
            temp.item = item;
            temp.next = sentinel.next;
            temp.prev = sentinel;
            sentinel.next.prev = temp;
            sentinel.next = temp;

        }

        @Override
        public void addLast(T item) {
            size += 1;
            Node temp = new Node();
            temp.item = item;
            sentinel.prev.next = temp;
            temp.prev = sentinel.prev;
            sentinel.prev = temp;
            temp.next = sentinel;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public void printDeque() {
            Node p = sentinel.next;
            while (p != sentinel) {
                System.out.print(p.item + " ");
                p = p.next;
            }
            System.out.println();
        }

        @Override
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

        @Override
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

        @Override
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

        private Node recursiveHelp(Node p, int index) {
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

}
