package js.util;

import js.util.Iterator;
import js.util.function.Consumer;

public interface Collection<T> extends Iterable<T> {
	void add(T item);

	void addAll(Collection<T> collection);

	boolean isEmpty();

	boolean contains(Object o);

	Iterator<T> iterator();

	int size();

	<X> X[] toArray(X[] a);

	void forEach(Consumer<? super T> action);

}
