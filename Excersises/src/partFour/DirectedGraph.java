package partFour;
import java.util.*;



public class DirectedGraph<E extends Edge> {
	
	public class Data{
		private final int node; 
		private final double distance;
		
		private final List<E> path;
		public Data(int node, List<E> path, double distance){
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
		List<Integer> visitedNodes = new LinkedList<Integer>();
		PriorityQueue<Data> pq = new PriorityQueue<Data>(new CompDijkstraPath<>());
		List<E> path = new LinkedList<E>();
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
					List<E> currPath = (List<E>)((LinkedList<E>) currData.getPath()).clone();//hmmmm
					currPath.add(e);
					Data newData = new Data(e.to, currPath,currData.getWeight() +e.getWeight());
					pq.add(newData);
				}
			}
		}
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
