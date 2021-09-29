package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue are appended to this queue. The specified queue (q) is empty
	 * after the call.
	 * 
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		if (size == 0 & q.size != 0) {
			last = q.last;
			size = q.size;
			q.last = null;
			q.size = 0;
		}
		if (size > 0 && q.size > 0) {
			QueueNode<E> currentHead = last.next;
			last.next = q.last.next;
			q.last.next = currentHead;
			size += q.size;
			q.last = null;
			q.size = 0;
		}

		if (this.equals(q)) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue a
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> element = new QueueNode<>(e);

		if (size == 0) {
			last = element;
			last.next = element;
		} else {
			element.next = last.next;
			last.next = element;
			last = element;
		}
		size++;
		return true;
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			return null;
		}
		return last.next.element;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size == 0) {
			return null;
		}
		QueueNode<E> head = last.next;
		last.next = head.next;
		size--;
		return head.element;
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		int counter = 0;

		/* Konstruktor */
		private QueueIterator() {
			if (last != null) {
				pos = last;
			}
		}

		public boolean hasNext() {
			return pos != null && pos.next != null && counter != size;
		}

		public E next() {
			if (hasNext()) {
				pos = pos.next;
				counter++;
				return pos.element;
			}
			throw new NoSuchElementException();
		}
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}