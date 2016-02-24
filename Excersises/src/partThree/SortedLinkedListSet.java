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
}
