package js.util;

import js.util.ListIterator;

public interface List<T> extends Collection<T> {

	T get(int idx);

	int size();

	void clear();

	T remove(Object... arguments);

	ListIterator<T> listIterator();

	int indexOf(T t);

}
