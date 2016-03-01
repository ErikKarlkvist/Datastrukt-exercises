package partFour;

import java.util.Comparator;

import partFour.DirectedGraph.Data;

public class CompDijkstraPath<E extends Edge> implements Comparator<DirectedGraph<E>.Data> {

	@Override
	public int compare(Data o1, Data o2) {
		return (o1.getDistance() - o2.getDistance());
	}

}
