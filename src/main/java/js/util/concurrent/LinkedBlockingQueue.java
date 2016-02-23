package js.util.concurrent;

import js.util.Collection;
import js.util.Iterator;
import js.util.function.Consumer;

public class LinkedBlockingQueue<E> implements BlockingQueue<E> {

    @Override
    public boolean add(E e) {
        throw new RuntimeException("TODO Queue<E>.add");
    }

    @Override
    public boolean offer(E e) {
        throw new RuntimeException("TODO Queue<E>.offer");
    }

    @Override
    public E remove() {
        throw new RuntimeException("TODO Queue<E>.remove");
    }

    @Override
    public E element() {
        throw new RuntimeException("TODO Queue<E>.element");
    }

    @Override
    public E peek() {
        throw new RuntimeException("TODO Queue<E>.peek");
    }

    @Override
    public int size() {
        throw new RuntimeException("TODO Collection<E>.size");
    }

    @Override
    public boolean isEmpty() {
        throw new RuntimeException("TODO Collection<E>.isEmpty");
    }

    @Override
    public boolean contains(Object o) {
        throw new RuntimeException("TODO Collection<E>.contains");
    }

    @Override
    public Iterator<E> iterator() {
        throw new RuntimeException("TODO Collection<E>.iterator");
    }

    @Override
    public Object[] toArray() {
        throw new RuntimeException("TODO Collection<E>.toArray");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new RuntimeException("TODO Collection<E>.toArray");
    }

    @Override
    public boolean remove(Object o) {
        throw new RuntimeException("TODO Collection<E>.remove");
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.containsAll");
    }

    @Override
    public boolean addAll(java.util.Collection<? extends E> c) {
        throw new RuntimeException("TODO Collection<E>.addAll");
    }

    @Override
    public boolean removeAll(java.util.Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.removeAll");
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.retainAll");
    }

    @Override
    public void clear() {
        throw new RuntimeException("TODO Collection<E>.clear");
    }

    @Override
    public E poll() {
        throw new RuntimeException("TODO BlockingQueue<E>.poll");
    }

    @Override
    public E take() {
        throw new RuntimeException("TODO BlockingQueue<E>.take");
    }

    @Override
    public void put(E ret) {
        throw new RuntimeException("TODO BlockingQueue<E>.put");
    }


}
