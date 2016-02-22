package partThree;

public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {
	
	int size = 0;
	Node<E> root;

	public class Node<E>{
		E elt;
		Node<E> left, right, parent;

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
		if(root == null){
			root = new Node<E>(x);
		} else if(x.compareTo(root.elt) > 0){
			
		}
	}
	
	private boolean addRec(Node<E> node, x){
		
	}

	@Override
	public boolean remove(E x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(E x) {
		// TODO Auto-generated method stub
		return false;
	}

}
