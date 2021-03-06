package partThree;

public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {

	int size = 0;
	Node<E> root;

	public class Node<E>{
		E elt;
		Node<E> leftChild, rightChild, parent;

		public Node(E elt){
			this.elt = elt;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(E x) {
		// Adds node to an empty tree
		if(root == null){
			root = new Node<E>(x);
			size++;
			return true;
		} else {
			// or adds node at the correct position in existing tree using
			// a recursive method
			return addRec(root, x);
		}
	}

	private boolean addRec(Node<E> parent, E x){
		int comp = x.compareTo(parent.elt);
		if(comp == 0){
			// Doesn't add anything if the node already exist
			return false;
		} else if(comp < 0){
			// Adds a node(x) as a leftChild to parent if x is less than parent
			// and parent doesn't have any leftChild
			if(parent.leftChild == null){
				parent.leftChild = new Node<E>(x);
				parent.leftChild.parent = parent;
				// Splays
				moveToRoot(parent.leftChild);
				size++;
				return true;
			} else {
				// If parent has leftChild, check if x is lesser than, or greater than 
				// leftChild, using the recursive addRec
				return addRec(parent.leftChild, x);
			}
		} else {
			if(parent.rightChild == null){
				// Adds a node(x) as a rightChild to parent if x is greater than parent
				// and parent doesn't have any rightChild
				parent.rightChild = new Node<E>(x);
				parent.rightChild.parent = parent;
				// Splays
				moveToRoot(parent.rightChild);
				size++;
				return true;
			} else {
				// If parent has rightChild, check if x is lesser than, or greater than 
				// leftChild, using the recursive addRec
				return addRec(parent.rightChild, x);
			}
		}
	}

	private boolean moveToRoot(Node<E> node){
		return moveToRootRec(node);
	}

	/**
	 * Decides what operation should be performed of zig, zigzig and zigzag in
	 * order to bring the node to the root (splaying). Continues recursively 
	 * until node == root.
	 * @param node
	 * @return true if element is root.
	 */
	private boolean moveToRootRec(Node<E> node){
		if(node == root){
			return true;
		}
		if(node.parent == root){
			if(node.parent.leftChild == node){
				zig(node, true);
			}else {
				zig(node, false);
			}
		} else if(node.parent.parent.leftChild == node.parent){
			if(node.parent.leftChild == node){
				zigzig(node, true);
			} else {
				zigzag(node, true);
			}
		} else {
			if(node.parent.rightChild == node){
				zigzig(node, false);
			} else {
				zigzag(node, false);
			}
		}
		return moveToRootRec(node);
	}

	/**
	 * 
	 * @param node you wish to zig
	 * @param goRight if you want to make a right rotation 
	 */
	private void zig(Node<E> node, boolean goRight){
		if(goRight){
			rightRotation(node);
		} else {
			leftRotation(node);
		}
	}

	/**
	 * 
	 * @param node you wish to zigzig
	 * @param goRight true does right rotation, false does left
	 */
	private void zigzig(Node<E> node, boolean goRight){
		if(goRight){
			rightRotation(node.parent);
			rightRotation(node);
		} else {
			leftRotation(node.parent);
			leftRotation(node);
		}
	}


	private void zigzag(Node<E> node, boolean parentRight){
		if(parentRight){
			leftRotation(node);
			rightRotation(node);
		} else {
			rightRotation(node);
			leftRotation(node);
		}
		
	}

	private void leftRotation(Node<E> child){
		Node<E> parent = child.parent;

		//set the childs rightchild to the parents leftchild
		parent.rightChild = child.leftChild;
		if(child.leftChild != null){
			child.leftChild.parent = parent;
		}

		//sets the grandparent of child to parent of child
		if(parent.parent != null){
			if(parent.parent.leftChild == parent){
				parent.parent.leftChild = child;
			} else{
				parent.parent.rightChild = child;
			}
		}
		child.parent = parent.parent;

		child.leftChild = parent;
		parent.parent = child;
		if(parent == root){
			root = child;
		}
	}

	private void rightRotation(Node<E> child){
		Node<E> parent = child.parent;

		//set the childs rightchild to the parents leftchild
		parent.leftChild = child.rightChild;
		if(child.rightChild != null){
			child.rightChild.parent = parent;
		}

		//sets the grandparent of child to parent of child
		if(parent.parent != null){
			if(parent.parent.leftChild == parent){
				parent.parent.leftChild = child;
			} else{
				parent.parent.rightChild = child;
			}
		}
		child.parent = parent.parent;

		child.rightChild = parent;
		parent.parent = child;
		if(parent == root){
			root = child;
		}
	}

	@Override
	public boolean remove(E x) {
		// Tries to find the node
		Node<E> node = findRec(root, x);
		// Return false if node cannot be found
		if(node == null){
			return false;
		}
		// Splays
		moveToRoot(node);
		Node<E> lChild = node.leftChild;
		Node<E> rChild = node.rightChild;
		
		if(lChild == null && rChild == null){
			root = null;
		} else if ( lChild != null){
			lChild.parent = null;
			Node<E> largestLeft = findLargestRec(lChild);
			moveToTop(largestLeft);
			root = largestLeft;
			if(rChild != null){
				rChild.parent = root;
				root.rightChild = rChild;
			}
		}else{
			rChild.parent = null;
			root = rChild;
		}
		size--;
		return true;
	}
	
	private boolean moveToTop(Node<E> node){
		if(node.parent == null){
			return true;
		} else {
			leftRotation(node);
			return moveToTop(node);
		}
	}
	
	// Continues until the rightmost node, starting from "node", is reached in order to
	// find the largest node, lesser than "node", then return it
	private Node<E> findLargestRec(Node<E> node){
		if(node.rightChild == null){
			return node;
		} else {
			return findLargestRec(node.rightChild);
		}
	}

	@Override
	public boolean contains(E x) {
		Node<E> node = findRec(root, x);
		if(node == null){
			return false;
		} else {
			moveToRoot(node);
			return true;
		}
	}
	
	/**
	 * Finds a node by comparing x with the value of a node, then using this
	 * recursive method to compare with the leftChild or righChild, depending
	 * on if x is greater or lesser than the node.
	 * @param compNode
	 * @param x
	 * @return node that's found
	 */
	private Node<E> findRec(Node<E> compNode, E x){
		if(compNode == null){
			return null;
		} else {
			int comp = x.compareTo(compNode.elt);
			if(comp == 0){
				return compNode;
			}else if(comp < 0){
				return findRec(compNode.leftChild, x);
			} else {
				return findRec(compNode.rightChild, x);
			}
		}
	}
}
