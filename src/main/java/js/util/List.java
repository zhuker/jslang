package js.util;

import js.util.ListIterator;

public interface List<T> extends Collection<T> {

	T get(int idx);

	int size();

	void clear();

	T remove(int i);

	void add(int i, T t);

	ListIterator<T> listIterator();

	int indexOf(T t);

	boolean addAll(int index, Collection<? extends T> c);

}
