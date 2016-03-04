package partFour;

import java.util.Comparator;

import partFour.DirectedGraph.Data;

public class CompKruskalEdge <E extends Edge> implements Comparator<E>{

	@Override
	public int compare(E e1, E e2) {
		if(e1 == null){
			return -1;
		}
		if(e2 == null){
			return 1;
		}
		return Double.compare(e1.getWeight(), e2.getWeight());
	}
}
