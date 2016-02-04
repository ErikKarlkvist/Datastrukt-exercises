package partTwo;

/** Doubly-linked list with user access to nodes
 */
public class DLList<E> {
	public class Node {
		/** The contents of the node is public */
		public E elt;

		protected Node prev, next;

		Node() {
			this(null);
		}
		Node(E elt) {
			this.elt = elt;
			prev = next = null;
		}

		public Node getNext() {
			return next;
		}

		public Node getPrev() {
			return prev;
		}
	}

	/** first and last nodes in list, null when list is empty */
	Node first, last;

	DLList() {
		first = last = null;
	}

	/** inserts an element at the beginning of the list
	 * @param e   the new element value
	 * @return    the node holding the added element
	 */
	public Node addFirst(E e) {
		Node newNode = new Node(e);
		if(first == null){
			last = newNode;
		}else{
			first.prev = newNode;
		}
		newNode.next = first;
		first = newNode;
		return newNode;
	}

	/** inserts an element at then end of the list
	 * @param e   the new element
	 * @return    the node holding the added element
	 */
	public Node addLast(E e) {
	      Node newNode = new Node(e);
	      if(first.equals(null)){
	    	  first = newNode;
	      }else{
	    	  last.next = newNode;
	      }
	      newNode.prev = last;
	      last = newNode;
	      return newNode;      
	  }

	/**
	 * @return    the node of the list's first element, null if list is empty
	 */
	public Node getFirst() {
		return first;
	}

	/**
	 * @return    the node of the list's last element, null if list is empty
	 */
	public Node getLast() {
		return last;
	}

	/** inserts a new element after a specified node
	 * @param e   the new element
	 * @param l   the node after which to insert the element, must be non-null and a node belonging to this list
	 * @return    the node holding the inserted element
	 */
	public Node insertAfter(E e, Node l) {
		Node newNode = new Node(e);
		newNode.next = l.next;
		if(l.next != null){
			l.next.prev = newNode;
		}
		l.next = newNode;
		newNode.prev = l;
		return newNode;
	}

	/** inserts a new element before a specified node
	 * @param e   the new element
	 * @param l   the node before which to insert the element, must be non-null and a node belonging to this list
	 * @return    the node holding the inserted element
	 */
	public Node insertBefore(E e, Node l) {
		Node newNode = new Node(e);
		
	}

	/** removes an element
	 * @param l   then node containing the element that will be removed, must be non-null and a node belonging to this list
	 */
	public void remove(Node l) {
		// TODO
	}
}
