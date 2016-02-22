package partThree;

import java.util.LinkedList;

public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet<E>{

	private LinkedList<E> theList;
	
	public SortedLinkedListSet(){
		theList = new LinkedList<E>();
	}
	
	@Override
	public int size() {
		return theList.size();
	}

	@Override
	public boolean add(E x) {
		for(int index = 0; index < theList.size(); index++){
			E comp = theList.get(index);
			if(x.compareTo(comp) == 0){
				return false;
			}else if(x.compareTo(comp) < 0){
				theList.add(index, x); 
				return true;
			}
		}
		theList.addLast(x);
		return true;
	}

	@Override
	public boolean remove(E x) {
		return theList.remove(x); //yolo för att det funkar?
	}

	@Override
	public boolean contains(E x) {
		return theList.contains(x);
	}

	
	
	
	
	/*int size = 0;
	Node<E> first,last;

	public class Node<E>{
		E elt;
		Node<E> left, right;

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
		if(first == null){
			first = new Node<E>(x);
			size++;
			return true;
		}else{
			return addRec(first, x);
		}
	}

	private boolean addRec(Node<E> node, E x){
		int comp = x.compareTo(node.elt);
		if(comp == 0){
			return false;
		}else if(comp > 0){
			if(node.right == null){
				Node<E> newNode = new Node<E>(x); 
				node.right = newNode;
				newNode.left = node;
				last = newNode;
				size++;
				return true;
			} else {
				return addRec(node.right, x);
			}
		} else {
			Node<E> newNode = new Node<E>(x);
			if(node.left == null){
				first = newNode;
			} else {
				newNode.left = node.left;
			}
			node.left = newNode;
			newNode.right = node;
			size++;
			return true;
		}
	}

	@Override
	public boolean remove(E x) {
		Node<E> node = findNodeRec(first, x);
		if(node == null){
			return false;
		}
		if(node.left == null && node.right == null){
			first = null;
			last = null;
		} else if(node.left == null && node.right != null){
			node.right.left = null;
			node.right = first;
		} else if(node.right == null && node.left != null){
			node.left.right = null;
			node.left = last;
		} else {
			node.right.left = node.left;
			node.left.right = node.right;
		}
		size = size - 1;
		return true;
	}

	@Override
	public boolean contains(E x) {
		return(findNodeRec(first, x) != null);
	}

	private Node <E> findNodeRec(Node<E> node, E x){
		if(node == null){
			return null;
		} else if(node.elt.compareTo(x) == 0){
			return node;
		} else {
			return findNodeRec(node.right, x);
		}
	}*/

}
