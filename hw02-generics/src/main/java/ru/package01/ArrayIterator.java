package ru.package01;

import java.util.*;

class ArrayIterator<T> implements Iterator<T>, ListIterator<T> {
    //   private int COPY_THRESHOLD = 10;
    private int index = 0;
    private T[] list1;
    private int size = 0;


    public int size() {
        size = list1.length;
        return size;
    }

    ArrayIterator(T[] list1) {
        this.list1 = list1;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public T next() {
        return list1[index++];
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public T previous() {
        return list1[index--];
    }

    @Override
    public int nextIndex() {
        return index++;
    }

    @Override
    public int previousIndex() {
        return index--;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(Object o) {
    }

    private T set(int index, T element) {
        return list1[index] = element;
    }

    @Override
    public void add(Object o) {
        throw new UnsupportedOperationException();
    }
}

