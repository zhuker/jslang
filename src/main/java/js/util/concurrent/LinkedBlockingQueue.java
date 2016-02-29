package js.util.concurrent;

import js.util.Collection;
import java.util.Iterator;

import java.util.function.Consumer;


public class LinkedBlockingQueue<E> implements BlockingQueue<E> {

    @Override
    public Object add(Object... arguments) {
        throw new RuntimeException("TODO Queue<E>.add");
    }

    @Override
    public boolean offer(E e) {
        throw new RuntimeException("TODO Queue<E>.offer");
    }

    @Override
    public E remove(Object... arguments) {
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
    public <T> T[] toArray(T[] a) {
        throw new RuntimeException("TODO Collection<E>.toArray");
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

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.containsAll");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.removeAll");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.retainAll");
    }

    @Override
    public boolean addAll(Object... arguments) {
        throw new RuntimeException("TODO Collection<E>.addAll");
    }


}
