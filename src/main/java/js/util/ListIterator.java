package js.util;

import js.util.Iterator;

public interface ListIterator<E> extends Iterator<E> {

	void add(E e);

	boolean hasPrevious();

	E previous();

	void set(E e);

}
