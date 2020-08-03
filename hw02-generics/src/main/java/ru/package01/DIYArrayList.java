package ru.package01;

import java.util.*;


public class DIYArrayList<T> implements List<T> {
    private T[] list1;
    private int size = 0;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;


    public DIYArrayList() {
        list1 = (T[]) new Object[0];
    }

    @Override
    public int size() {
        size = list1.length;
        return size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(list1);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean add(T t) {
        add(t, list1, size);
        return true;
    }

    private void add(T t, Object[] list1, int s) {
        if (s == size)
            list1 = grow();
        list1[s] = t;
        size = s + 1;
    }

    private Object[] grow() {
        return grow(size++);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = size;
        if (oldCapacity > 0 || list1 != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = oldCapacity++;
            return list1 = Arrays.copyOf(list1, newCapacity);
        } else {
            return list1 = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) list1, 0, size, c);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        return list1[index];
    }

    @Override
    public T set(int index, T element) {

        return list1[index] = element;

    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ArrayIterator<T>(list1);
    }

    @Override
    public ListIterator<T> listIterator(int index) {

        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
