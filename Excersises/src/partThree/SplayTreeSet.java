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
		boolean value;
		if(root == null){
			root = new Node<E>(x);
			size++;
			return true;
		} else {
			return value = addRec(root, x);
		}
	}

	private boolean addRec(Node<E> parent, E x){
		int comp = x.compareTo(parent.elt);
		if(comp == 0){
			return false;
		} else if(comp < 0){
			if(parent.leftChild == null){
				parent.leftChild = new Node<E>(x);
				parent.leftChild.parent = parent;
				moveToRoot(parent.leftChild);
				size++;
				return true;
			} else {
				return addRec(parent.leftChild, x);
			}
		} else {
			if(parent.rightChild == null){
				parent.rightChild = new Node<E>(x);
				parent.rightChild.parent = parent;
				moveToRoot(parent.rightChild);
				size++;
				return true;
			} else {
				return addRec(parent.rightChild, x);
			}
		}
	}

	public boolean moveToRoot(Node<E> node){
		return moveToRootRec(node);
	}

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
		Node<E> node = findRec(root, x);
		if(node == null){
			return false;
		}
		moveToRoot(node);
		Node<E> lChild = node.leftChild;
		Node<E> rChild = node.rightChild;
		if(lChild == null){
			root = node.rightChild;
		} else if(rChild != null){
			lChild.parent = null;
			rChild.parent = null;
			Node<E> largestLeft = findLargestRec(lChild);
			moveToTop(largestLeft);
			largestLeft.rightChild = rChild;
			rChild.parent = largestLeft;
			lChild.parent = largestLeft;
			root = largestLeft;
		} else {
			root = null;
		}
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
		System.out.println(test.root.elt + " 5?");
		test.add(8);
		System.out.println(test.root.elt + " 8?");
		System.out.println(test.root.leftChild.elt + " 5?");
		test.add(6);
		System.out.println(test.root.elt + " 6?");
		System.out.println(test.root.leftChild.elt + " 5?");
		System.out.println(test.root.rightChild.elt + " 8?");
		test.add(9);
		System.out.println(test.root.elt + " 9?");
		System.out.println(test.root.leftChild.elt + " 8?");
		System.out.println(test.root.rightChild + " NULL?");
		System.out.println(test.root.leftChild.leftChild.elt + " 6?");
		System.out.println(test.root.leftChild.leftChild.leftChild.elt + " 5?");
		test.add(10);
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
		System.out.println(test.root.elt + " 5?");
		System.out.println(test.contains(12) + " false");
		System.out.println(test.root.elt + " 5?");
		System.out.println("-----------------------------------------------------------");
		test.remove(6);
		System.out.println(test.root.elt + " 5?");
		System.out.println(test.root.leftChild + " null?");
		System.out.println(test.root.rightChild.elt + " 8?");
		System.out.println(test.root.rightChild.rightChild.elt + " 9?");
		System.out.println(test.root.rightChild.rightChild.rightChild.elt + " 10?");
		System.out.println(test.root.rightChild.leftChild + " null?");
		test.remove(9);
		System.out.println(test.root.elt + " 8?");
		System.out.println(test.root.rightChild.elt + " 10?");
		System.out.println(test.root.leftChild.elt + " 5?");
		System.out.println(test.remove(9) + " false?");
		

	}

}
