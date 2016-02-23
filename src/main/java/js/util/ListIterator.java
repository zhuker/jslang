package js.util;

import java.util.Iterator;

public interface ListIterator<E> extends Iterator<E> {

	void add(E e);

	boolean hasPrevious();

	E previous();

	void set(E e);

}
