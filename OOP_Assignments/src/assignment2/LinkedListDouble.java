package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Two directions linked list, which is traversable in two directions.
 * 
 * @author Ali Masarweh
 *
 * @param <T> the generic parameter T
 */
public class LinkedListDouble<T> {

	/** The head and the tail of the linked list */
	private NodeDouble<T> _head, _tail;
	/** The number of elemnts in the linked list */
	private int _size;

	/**
	 * Constructs a Two directions linked list
	 */
	public LinkedListDouble() {
		_head = null;
		_tail = null;
		_size = 0;
	}

	/**
	 * Adds an item at the end of list
	 * 
	 * @param item the given argument to add at the end of the list
	 */
	public void add(T item) {
		NodeDouble<T> nodeOfItem_TMP = new NodeDouble<T>(item);
		if (_head == null) {
			_head = nodeOfItem_TMP;
			_tail = nodeOfItem_TMP;
		} else {
			_tail._next = nodeOfItem_TMP;
			nodeOfItem_TMP._previous = _tail;
			_tail = nodeOfItem_TMP;
		}
		_size++;
	}

	@Override
	public String toString() {
		String ans = "";
		ListIterator<T> iterator = this.listIterator();
		while (iterator.hasNext()) {
			ans += iterator.next() + ", ";
		}
		return ans;
	}

	/**
	 * 
	 * @return the content of the list. (while traversing in reverse)
	 */
	public String toStringInReverse() {
		String ans = "";
		ListIterator<T> iterator = this.reverseListIterator();
		while (iterator.hasPrevious()) {
			ans += iterator.previous() + ", ";
		}
		return ans;
	}

	/**
	 * 
	 * @return iterator for the list
	 */
	public ListIterator<T> listIterator() {
		/** Once next method is used, the dummy won't be accessible from the iterator, because the head's previous is still null */
		NodeDouble<T> dummyStart = new NodeDouble<T>(null);
		dummyStart._next = _head;
		dummyStart._previous = null;
		return new ListIterator<T>() {

			NodeDouble<T> iterating = dummyStart;

			@Override
			public boolean hasNext() {
				return iterating._next != null;
			}

			@Override
			public T next() throws NoSuchElementException {
				if(!hasNext()) throw new NoSuchElementException();
				iterating = iterating._next;
				return iterating._data;
			}

			@Override
			public boolean hasPrevious() {
				return iterating._previous != null;
			}

			@Override
			public T previous() throws NoSuchElementException {
				if(!hasPrevious()) throw new NoSuchElementException();
				iterating = iterating._previous;
				return iterating._data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * 
	 * @return iterator for the list that starts from the end.
	 */
	public ListIterator<T> reverseListIterator() {
		/**Once previous method is used, the dummy won't be accessible from the iterator, because the tail's next is still null*/
		NodeDouble<T> dummyEnd = new NodeDouble<T>(null);
		dummyEnd._previous = _tail;
		dummyEnd._next = null;
		return new ListIterator<T>() {

			NodeDouble<T> iterating = dummyEnd;

			@Override
			public boolean hasNext() {
				return iterating._next != null;
			}

			@Override
			public T next() throws NoSuchElementException {
				if(!hasNext()) throw new NoSuchElementException();
				iterating = iterating._next;
				return iterating._data;
			}

			@Override
			public boolean hasPrevious() {
				return iterating._previous != null;
			}

			@Override
			public T previous() throws NoSuchElementException {
				if(!hasPrevious()) throw new NoSuchElementException();
				iterating = iterating._previous;
				return iterating._data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * 
	 * @return the size of the list
	 */
	public int getSize() {
		return _size;
	}

	/**
	 * Composite class of LinkedListedDouble, used for linking members.
	 * 
	 * @author Ali Masarweh
	 *
	 * @param <V> the generic parameter V
	 */
	private class NodeDouble<V> {
		/* The class being private, makes only accessible from LinkedListDouble */
		/** The stored data */
		public V _data;
		/** The pointers to other NodeDouble objects */
		public NodeDouble<V> _next, _previous;

		/**
		 * 
		 * @param data - the given argument data to be saved at the node
		 */
		public NodeDouble(V data) {
			_next = null;
			_previous = null;
			_data = data;
		}

		@Override
		public String toString() {
			return _data + "";
		}
	}
}

/**
 * The list iterator interface for DoubleListIterator
 * 
 * @author Ali Masarweh
 *
 * @param <T> the generic parameter T
 */
interface ListIterator<T> extends Iterator<T> {
	/**
	 * 
	 * @return true, if the iterator (helper) has previous, otherwise, false
	 */
	public boolean hasPrevious();

	/**
	 * 
	 * @return moves the iterator (helper) and returns the data.
	 */
	public T previous() throws NoSuchElementException;

	/**
	 * removes the current element
	 */
	public void remove();
}
