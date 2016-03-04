package partFour;
import java.util.*;



public class DirectedGraph<E extends Edge> {

	public class Data{
		private final int node; 
		private final double distance;

		private final LinkedList<E> path;
		public Data(int node, LinkedList<E> path, double distance){
			this.node = node;
			this.distance = distance;
			this.path = path;
		}

		public int getNode() {
			return node;
		}

		public double getWeight() {
			return distance;
		}

		public LinkedList<E> getPath() {
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
		List<Integer> visitedNodes = new LinkedList<Integer>();
		PriorityQueue<Data> pq = new PriorityQueue<Data>(new CompDijkstraPath<>());
		LinkedList<E> path = new LinkedList<E>();
		pq.add(new Data(from, path, 0));
		Data currData;
		while(!pq.isEmpty()){
			while(true){
				currData = pq.poll();
				if(!visitedNodes.contains(currData.getNode())){
					break;
				}
			}
			if(currData.getNode() == to){
				return currData.getPath().iterator();
			}
			visitedNodes.add(currData.getNode());

			for(E e: edges){
				if(e.from == currData.getNode() && !visitedNodes.contains(e.to)){
					LinkedList<E> currPath = (LinkedList<E>) currData.getPath().clone();//hmmmm	 
					currPath.add(e);
					Data newData = new Data(e.to, currPath,currData.getWeight() +e.getWeight());
					pq.add(newData);
				}
			}
		}
		return null;
	}

	public Iterator<E> minimumSpanningTree() {
		Map<Integer, LinkedList<E>> paths = new HashMap<Integer, LinkedList<E>>();
		PriorityQueue<E> pq = new PriorityQueue<E>(new CompKruskalEdge<E>());
		E currEdge = null;
		for(E e: edges){
			paths.put(e.from, new LinkedList<E>());
			paths.put(e.to, new LinkedList<E>());
			pq.add(e);
		}
		while(!pq.isEmpty()){
			while(!pq.isEmpty()){
				int i = 0;
				currEdge = pq.poll();
				if(paths.get(currEdge.from)!= paths.get(currEdge.to)) break;
				i++;
			}
			LinkedList<E> lk = paths.get(currEdge.from);
			LinkedList<E> ll = paths.get(currEdge.to);
			ll.add(currEdge);
			ll.addAll(lk);
			if(!ll.isEmpty()){
				for(E e: ll){
					paths.put(e.from, ll);
					paths.put(e.to, ll);
				}
			} 
		}
		return paths.get(currEdge.from).iterator();
	}
}

