package js.util;

import java.util.Iterator;
import js.util.ListIterator;
import java.util.function.Consumer;

public class ArrayList<T> implements List<T> {

	public ArrayList(int i) {
		// TODO Auto-generated constructor stub
		throw new RuntimeException("TODO");
	}

	public ArrayList(Collection<? extends T> gop) {
		// TODO Auto-generated constructor stub
		throw new RuntimeException("TODO");
	}

	public ArrayList() {
		throw new RuntimeException("TODO");
	}

	@Override
	public T get(int idx) {
		throw new RuntimeException("TODO");
	}

	@Override
	public <X> X[] toArray(X[] a) {
		throw new RuntimeException("TODO");
	}

	@Override
	public int size() {
		throw new RuntimeException("TODO");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

	@Override
	public T remove(int i) {
		throw new RuntimeException("TODO");
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	@Override
	public void add(int i, T t) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}


	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	@Override
	public int indexOf(T t) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
	}

	@Override
	public Iterator<T> iterator() {
		throw new RuntimeException("TODO");
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

    @Override
    public Object[] toArray() {
        throw new RuntimeException("TODO Collection<T>.toArray");
    }

    @Override
    public boolean add(T e) {
        throw new RuntimeException("TODO Collection<T>.add");
    }

    @Override
    public boolean remove(Object o) {
        throw new RuntimeException("TODO Collection<T>.remove");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<T>.containsAll");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new RuntimeException("TODO Collection<T>.addAll");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<T>.removeAll");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<T>.retainAll");
    }

}
