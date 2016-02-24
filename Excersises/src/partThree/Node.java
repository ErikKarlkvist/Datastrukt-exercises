package partThree;

public class Node<E>{
	E elt;
	Node<E> leftChild, rightChild, parent;

	public Node(E elt){
		this.elt = elt;
	}
}