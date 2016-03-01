package partFour;
import java.util.*;



public class DirectedGraph<E extends Edge> {
	
	public class Data{
		private final int node, distance;
		
		private final List<E> path;
		public Data(int node, List<E> path, int distance){
			this.node = node;
			this.distance = distance;
			this.path = path;
		}
		
		public int getNode() {
			return node;
		}
	
		public int getDistance() {
			return distance;
		}
		
		public List<E> getPath() {
			return path;
		}
	}
	
	private int nodes;
	private List<E> edges;
	
	public DirectedGraph(int noOfNodes) {
		nodes = noOfNodes;
		edges = new LinkedList<E>();
	}

	public void addEdge(E e) {
		edges.add(e);
	}

	public Iterator<E> shortestPath(int from, int to) {
		List<E> visitedNodes = new LinkedList<E>();
		PriorityQueue<Data> pq = new PriorityQueue<Data>(nodes, new CompDijkstraPath<>());
		pq.add(new Data(from, null, 0));
		return edges.iterator();
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
