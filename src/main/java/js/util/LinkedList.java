package js.util;

import java.util.Iterator;

import js.util.ListIterator;
import java.util.function.Consumer;


public class LinkedList<T> implements List<T> {

	public LinkedList(Object... arguments) {
		throw new RuntimeException("TODO");
	}

	@Override
	public T get(int idx) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	@Override
	public <X> X[] toArray(X[] a) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

	@Override
	public T remove(Object... arguments) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	@Override
	public boolean isEmpty() {
		throw new RuntimeException("TODO");
	}

	@Override
	public Object add(Object... arguments) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	public T getLast() {
		throw new RuntimeException("TODO");
	}

	public T peekFirst() {
		throw new RuntimeException("TODO");
	}

	public T removeFirst() {
		throw new RuntimeException("TODO");
	}

	@Override
	public int indexOf(T t) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	public void push(T e) {
		throw new RuntimeException("TODO");
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	@Override
	public int size() {
		throw new RuntimeException("TODO size");
	}

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<T>.containsAll");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<T>.removeAll");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<T>.retainAll");
    }

    @Override
    public boolean addAll(Object... arguments) {
        throw new RuntimeException("TODO Collection<T>.addAll");
    }

}
