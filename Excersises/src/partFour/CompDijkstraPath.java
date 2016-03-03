package partFour;

import java.util.Comparator;

import partFour.DirectedGraph.Data;

public class CompDijkstraPath<E extends Edge> implements Comparator<DirectedGraph<E>.Data> {

	@Override
	public int compare(Data o1, Data o2) {
		if(o1 == null){
			return -1;
		}
		if(o2 == null){
			return 1;
		}
		return Double.compare(o1.getDistance(),o2.getDistance());
	}

}
