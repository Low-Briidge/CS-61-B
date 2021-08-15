public class ArrayDeque<T> {
    Array items;
    int capacity;
    int size;
    int nextFirst;
    int nextLast;
    public class Array{
        T[] item;
        public Array(int capacity){
            item = (T[]) new Object[capacity];
        }
    }
    public ArrayDeque (){
        items = new Array(8);
        capacity = 8;
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }
    public ArrayDeque (ArrayDeque other){
        items.item = (T[]) new Object[other.capacity];
        size = other.size;
        capacity = other.capacity;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        for (int i = 0; i < other.capacity; i++){
            items.item[i] = (T) other.items.item[i];
        }
    }
    public void resizing (String str){
        Array temp;
        if (str.equals("increase")){
            temp = new Array(capacity * 4);
            for (int i = 0; i < capacity; i++){
                temp.item[i] = this.get(i);
            }
        }
        else {
            temp = new Array(capacity / 2);
            for (int i = 0; i < size; i++){
                temp.item[i] = this.get(i);
            }
        }
        items = temp;
        nextLast = size;
        capacity = items.item.length;
        nextFirst = capacity - 1;
    }
    public void addFirst(T item){
        if (nextFirst == nextLast){
            resizing("increase");
        }
        size += 1;
        items.item[nextFirst] = item;
        if (nextFirst > 0){
            nextFirst -= 1;
        }
        else {
            nextFirst = capacity - 1;
        }
    }
    public void addLast(T item){
        if (nextLast == nextFirst){
            resizing("increase");
        }
        size += 1;
        items.item[nextLast] = item;
        if (nextLast < capacity - 1){
            nextLast += 1;
        }
        else {
            nextLast = 0;
        }
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for (int i = (nextFirst + 1) % capacity; i != nextLast; i = (i + 1) % capacity){
            System.out.print(items.item[i] + " ");
        }
        System.out.println();
    }
    public T removeFirst(){
        if (capacity >= 16 && 1.0 * size / capacity < 0.25){
            resizing("decrease");
        }
        size -= 1;
        T res = items.item[(nextFirst + 1) % capacity];
        nextFirst = (nextFirst + 1) % capacity;
        return res;
    }
    public T removeLast(){
        if (capacity >= 16 && 1.0 * size / capacity < 0.25){
            resizing("decrease");
        }
        size -= 1;
        if (nextLast > 0){
            nextLast -= 1;
        }
        else {
            nextLast = capacity - 1;
        }
        return items.item[nextLast];
    }
    public T get(int index){
        int i = (nextFirst + 1) % capacity;
        if (index + 1 > size){
            return null;
        }
        for (; i != nextLast && index > 0; i = (i + 1) % capacity){
            index--;
        }
        return items.item[i];
    }


}
