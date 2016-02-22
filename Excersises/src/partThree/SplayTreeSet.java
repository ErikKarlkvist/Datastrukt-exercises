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
			value = addRec(root, x);
		}
		return value;
	}

	private boolean addRec(Node<E> parent, E x){
		int comp = x.compareTo(parent.elt);
		if(comp == 0){
			return false;
		} else if(comp < 0){
			if(parent.leftChild == null){
				parent.leftChild = new Node<E>(x);
				parent.leftChild.parent = parent;
				size++;
				return true;
			} else {
				return addRec(parent.leftChild, x);
			}
		} else {
			if(parent.rightChild == null){
				parent.rightChild = new Node<E>(x);
				parent.rightChild.parent = parent;
				size++;
				return true;
			} else {
				return addRec(parent.rightChild, x);
			}
		}
	}
	
	private boolean moveToRoot(Node<E> node){
		if(node != root){
			return moveToRootRec(node);
		}
		return true;
	}
	
	private boolean moveToRootRec(Node<E> node){
		
	}

	/**
	 * Sets the left child of root to root/performs right rotation
	 */
	private void zig(){
		if(root != null && root.leftChild != null){
			Node<E> child = root.leftChild;
			root.leftChild = child.rightChild;
			if(child.rightChild != null){
				child.rightChild.parent = root;
			}
			child.rightChild = root;
			root.parent = child;
			root = child;
		}
	}

	/**
	 * 
	 * @param node and parent are leftchild
	 */
	private void zigzig(Node<E> node){
		rightRotation(node.parent);
		rightRotation(node);
	}

	/**
	 * 
	 * @param node is leftchild and parent is rightchild
	 */
	private void zigzag(Node<E> node){
		leftRotation(node);
		rightRotation(node);
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
	}

	@Override
	public boolean remove(E x) {
		return true;
	}

	@Override
	public boolean contains(E x) {
		// TODO Auto-generated method stub
		return false;
	}

}
