package turingmachine.positionallist;

/**
 * a custom positional doubly linked list implementation for the turingmachine tape
 * @author Jonathan Buck
 *
 */
public class PositionalList {

	private PositionalNode head;
	public PositionalNode tail;
	private int size;
	
	/**
	 * positional list constructor
	 */
	public PositionalList() {
		head = new PositionalNode('\0');
		tail = addAfter(head, '\0');
	}
	
	/**
	 * constructs list from an array of elements
	 * @param in input array
	 */
	public PositionalList(char[] in) {
		this();
		PositionalNode current = head;
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
	public PositionalNode addAfter(PositionalNode p, char element) {
		PositionalNode temp = new PositionalNode(element);
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
	public PositionalNode addBefore(PositionalNode p, char element) {
		PositionalNode temp = new PositionalNode(element);
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
	public void setCurrent(PositionalNode p, char element) {
		p.setElement(element);
	}
	
	/**
	 * gets element from current node
	 * @param p current position
	 */
	public Character setCurrent(PositionalNode p) {
		return p.getElement();
	}
	
	/**
	 * returns the next node
	 * @param p current position
	 * @return next node
	 */
	public PositionalNode next(PositionalNode p) {
		return p.getNext();
	}
	
	/**
	 * returns the previous node
	 * @param p current position
	 * @return previous node
	 */
	public PositionalNode prev(PositionalNode p) {
		return p.getPrev();
	}
	
	/**
	 * returns the front of the list
	 * @return front of the list
	 */
	public PositionalNode front() {
		return head;
	}
	/**
	 * node in a positional list
	 */
	public class PositionalNode {
		private char element;
		private PositionalNode prev;
		private PositionalNode next;
		
		/**
		 * positional node constructor
		 */
		public PositionalNode(char element) {
			setElement(element);
			setPrev(null);
			setNext(null);
		}

		/**
		 * @return the element
		 */
		public char getElement() {
			if(element == '\0') {
				element =  '_';
			}
			return element;
		}

		/**
		 * @return the prev
		 */
		public PositionalNode getPrev() {
			if (prev == null) {
				prev = new PositionalNode('_');
				prev.setNext(this);
			}
			return prev;
		}

		/**
		 * @return the next
		 */
		public PositionalNode getNext() {
			if (next == null) {
				next = new PositionalNode('_');
				next.setPrev(this);
			}
			return next;
		}

		/**
		 * @param element the element to set
		 */
		public void setElement(Character element) {
			this.element = element;
		}

		/**
		 * @param prev the prev to set
		 */
		public void setPrev(PositionalNode prev) {
			this.prev = prev;
		}

		/**
		 * @param next the next to set
		 */
		public void setNext(PositionalNode next) {
			this.next = next;
		}
		
		
	}

}
