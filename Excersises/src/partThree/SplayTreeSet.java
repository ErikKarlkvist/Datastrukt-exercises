package partThree;

public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {

	int size = 0;
	Node<E> root;
//
//	public class Node<E>{
//		E elt;
//		Node<E> leftChild, rightChild, parent;
//
//		public Node(E elt){
//			this.elt = elt;
//		}
//	}

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

	public boolean moveToRoot(Node<E> node){
		return moveToRootRec(node);
	}

	/**
	 * Decides what operation should be performed of zig, zigzig and zigzag in
	 * order to bring the node to the root (splaying). Continues recursively 
	 * until node == root.
	 * @param node
	 * @return
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
	 * Sets the left child of root to root/performs right rotation
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
	 * @param node must have a parent
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

	/**
	 * 
	 * @param node is leftchild and parent is rightchild
	 */
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

	/**
	 * 
	 * @param child is not root or null
	 */
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
		
		
//		if(lChild == null){
//			System.out.println("här");
//			root = node.rightChild;
//		} else if(rChild != null){
		
		if(lChild == null && rChild == null){
			root = null;
		} else if ( lChild != null){
			Node<E> largestLeft = findLargestRec(lChild);
			root.rightChild = null;
			moveToRoot(largestLeft);
			if(rChild != null){
				rChild.parent = root;
				root.rightChild = rChild;
			}
		}else{
			root = rChild;
		}
//			System.out.println("här då");
//			lChild.parent = null;
//			rChild.parent = null;
//			// Finds the largest node left of the root, the node to
//			// be removed, and sets it to the new root
//			Node<E> largestLeft = findLargestRec(lChild);
//			System.out.println(largestLeft.elt);
//			moveToTop(largestLeft);
//			largestLeft.rightChild = rChild;
//			rChild.parent = largestLeft;
//			lChild.parent = largestLeft;
//			root = largestLeft;
//		} else {
//			// if both the left and the right child are null, the tree is
//			// empty, and the root is therefore null
//			System.out.println("men");
//			root = null;
//		}
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
	 * @return
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
	
	public static void main(String[] args){
		SplayTreeSet<Integer> test = new SplayTreeSet<Integer>();
		test.add(5);
		BTreePrinter.printNode(test.root);
		System.out.println(test.root.elt + " 5?");
		test.add(8);
		BTreePrinter.printNode(test.root);
		System.out.println(test.root.elt + " 8?");
		System.out.println(test.root.leftChild.elt + " 5?");
		test.add(6);
		BTreePrinter.printNode(test.root);
		System.out.println(test.root.elt + " 6?");
		System.out.println(test.root.leftChild.elt + " 5?");
		System.out.println(test.root.rightChild.elt + " 8?");
		test.add(9);
		BTreePrinter.printNode(test.root);
		System.out.println(test.root.elt + " 9?");
		System.out.println(test.root.leftChild.elt + " 8?");
		System.out.println(test.root.rightChild + " NULL?");
		System.out.println(test.root.leftChild.leftChild.elt + " 6?");
		System.out.println(test.root.leftChild.leftChild.leftChild.elt + " 5?");
		test.add(10);
		BTreePrinter.printNode(test.root);
		System.out.println(test.root.elt + " 10?");
		System.out.println(test.root.leftChild.elt + " 9?");
		System.out.println(test.root.rightChild + " NULL?");
		System.out.println(test.root.leftChild.leftChild.elt + " 8?");
		test.moveToRoot(test.root.leftChild.leftChild);
		System.out.println(test.root.elt + " 8?");
		System.out.println(test.root.leftChild.elt + " 6?");
		System.out.println(test.root.rightChild.elt + " 9?");
		System.out.println(test.size() + " size 5?");
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("");
		System.out.println(test.contains(5) + " true");
		BTreePrinter.printNode(test.root);
		System.out.println(test.root.elt + " 5?");
		System.out.println(test.contains(12) + " false");
		BTreePrinter.printNode(test.root);
		System.out.println(test.root.elt + " 5?");
		System.out.println("-----------------------------------------------------------");
		test.remove(6);
		BTreePrinter.printNode(test.root);
		System.out.println(test.root.elt + " 5?");
		System.out.println(test.root.leftChild + " null?");
		System.out.println(test.root.rightChild.elt + " 8?");
		System.out.println(test.root.rightChild.rightChild.elt + " 9?");
		System.out.println(test.root.rightChild.rightChild.rightChild.elt + " 10?");
		System.out.println(test.root.rightChild.leftChild + " null?");
		test.remove(9);
		BTreePrinter.printNode(test.root);
		System.out.println(test.root.elt + " 8?");
		System.out.println(test.root.rightChild.elt + " 10?");
		System.out.println(test.root.leftChild.elt + " 5?");
		System.out.println(test.remove(9) + " false?");
		
		

	}

}
