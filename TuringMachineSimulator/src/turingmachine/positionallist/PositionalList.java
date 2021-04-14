package turingmachine.positionallist;

import java.util.Iterator;

/**
 * a custom positional doubly linked list implementation for the turingmachine tape
 * @author Jonathan Buck
 *
 */
public class PositionalList<E> implements Iterable<E>{

	private PositionalNode<E> head;
	public PositionalNode<E> tail;
	private int size;
	
	/**
	 * positional list constructor
	 */
	public PositionalList() {
		head = new PositionalNode<E>(null);
		tail = addAfter(head, null);
	}
	
	/**
	 * constructs list from an array of elements
	 * @param in input array
	 */
	public PositionalList(E[] in) {
		this();
		PositionalNode<E> current = head;
		for (int i = 0; i < in.length; i++) {
			addAfter(current, in[i]);
			current = next(current);
		}
	}
	
	/**
	 * adds element to list after position p
	 * @param p current position
	 * @param element element added
	 * @return node added
	 */
	public PositionalNode<E> addAfter(PositionalNode<E> p, E element) {
		PositionalNode<E> temp = new PositionalNode<E>(element);
		temp.setNext(p.getNext());
		p.getNext().setPrev(temp);
		temp.setPrev(p);
		p.setNext(temp);
		return temp;
	}
	
	/**
	 * adds element to list before position p
	 * @param p current position
	 * @param element element added
	 * @return node added
	 */
	public PositionalNode<E> addBefore(PositionalNode<E> p, E element) {
		PositionalNode<E> temp = new PositionalNode<E>(element);
		temp.setPrev(p.getPrev());
		p.getPrev().setNext(temp);
		temp.setNext(p);
		p.setPrev(temp);
		return temp;
	}
	
	/**
	 * sets the current node to element
	 * @param p current position
	 * @param element element set to
	 */
	public void setCurrent(PositionalNode<E> p, E element) {
		p.setElement(element);
	}
	
	/**
	 * gets element from current node
	 * @param p current position
	 */
	public E setCurrent(PositionalNode<E> p) {
		return p.getElement();
	}
	
	/**
	 * returns the next node
	 * @param p current position
	 * @return next node
	 */
	public PositionalNode<E> next(PositionalNode<E> p) {
		return p.getNext();
	}
	
	/**
	 * returns the previous node
	 * @param p current position
	 * @return previous node
	 */
	public PositionalNode<E> prev(PositionalNode<E> p) {
		return p.getPrev();
	}
	
	/**
	 * returns the front of the list
	 * @return front of the list
	 */
	public PositionalNode<E> front() {
		return head;
	}
	/**
	 * node in a positional list
	 */
	public class PositionalNode<E> {
		private E element;
		private PositionalNode<E> prev;
		private PositionalNode<E> next;
		
		/**
		 * positional node constructor
		 */
		public PositionalNode(E element) {
			setElement(element);
			setPrev(null);
			setNext(null);
		}

		/**
		 * @return the element
		 */
		public E getElement() {
			return element;
		}

		/**
		 * @return the prev
		 */
		public PositionalNode<E> getPrev() {
			if (prev == null) {
				prev = new PositionalNode<E>(null);
				prev.setNext(this);
			}
			return prev;
		}

		/**
		 * @return the next
		 */
		public PositionalNode<E> getNext() {
			if (next == null) {
				next = new PositionalNode<E>(null);
				next.setPrev(this);
			}
			return next;
		}

		/**
		 * @param element the element to set
		 */
		public void setElement(E element) {
			this.element = element;
		}

		/**
		 * @param prev the prev to set
		 */
		public void setPrev(PositionalNode<E> prev) {
			this.prev = prev;
		}

		/**
		 * @param next the next to set
		 */
		public void setNext(PositionalNode<E> next) {
			this.next = next;
		}
		
		
	}
	/**
	 * allows for iteration over the whole positional list
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
