package partTwo;

import java.awt.Point;
import java.util.Comparator;
import java.util.PriorityQueue;

import partTwo.DLList.Node;;

public class Lab2b {
	
	public static class NodeComparator<E extends DLList<Point>.Node> implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			/** this does not work 
			 * 
			 */
			Node node1 = (Node)arg0;
			Node node2 = (Node)arg1;
	
			/*if((node1.getNext() == null && node2.getPrev() == null) ||
					((node2.getNext() == null && node1.getPrev() == null))){
				System.out.println("first if");
				return 1;
			}else if(node1.getNext() == null || node1.getPrev() == null){
				return 1;
			}else if(node2.getNext() == null  || node2.getPrev() == null){
				return -1;
			}else{*/
			double value1 = calculateValue(node1);
			double value2 = calculateValue(node2);
			int comp = Double.compare(value1,value2);
			return (comp);
			//}
		}
		
		/**
		 * 
		 * @param node must have prev and next
		 * @return
		 */
		private double calculateValue(Node node){
			
			Point p1 = (Point)node.prev.elt;
			Point p2 = (Point)node.elt;
			Point p3 = (Point)node.next.elt;
			
			double l1 = calcDistBetweenPoints(p1, p2);
			double l2 = calcDistBetweenPoints(p2, p3);
			double l3 = calcDistBetweenPoints(p1 ,p3);
			
			return (l1 + l2 - l3);
		}
		
		private double calcDistBetweenPoints(Point p1, Point p2){
			double x = (p1.getX()-p2.getX());
			double y = (p1.getY()-p2.getY());
			return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
		}
		
	}
	
	public static double[] simplifyShape(double[] poly, int k)
	{
		DLList<Point> pointList = new DLList<Point>();
		PriorityQueue<DLList.Node> pQueue = new PriorityQueue<DLList.Node>(11, new NodeComparator());
		Point first = new Point();
		Point last = new Point();
		
		first.setLocation(poly[0], poly[1]);
		last.setLocation(poly[poly.length-2], poly[poly.length-1]);
		pointList.addFirst(first);
		//pQueue.add(pointList.first);
		
		pointList.addLast(last);
		//pQueue.add(pointList.last);
		for(int i=2; i < poly.length-2; i+=2){
			Point p = new Point();
			p.setLocation(poly[i], poly[i+1]);
			pointList.insertBefore(p, pointList.last);
			//pQueue.add(pointList.last.prev);
		}
		
		DLList.Node node = pointList.getFirst().next;
		while(node != pointList.getLast()){
			pQueue.add(node);
			node = node.next;
		}
		
		while(pQueue.size()+2 > k){
			Node rNode = pQueue.peek();
			pointList.remove(rNode);
			pQueue.remove(rNode);
			
			if(rNode.prev != pointList.getFirst()){
				pQueue.remove(rNode.prev);
				pQueue.add(rNode.prev);
			}
			if(rNode.next != pointList.getLast()){
				pQueue.remove(rNode.next);
				pQueue.add(rNode.next);
			}		
		}
		
		Node theNode = pointList.getFirst();
		double[] newArray = new double[2*k];
		for(int i=0; i<2*k; i+=2){
			newArray[i] = ((Point)theNode.elt).getX();
			newArray[i+1] = ((Point)theNode.elt).getY();
			theNode = theNode.next;
		}
		return newArray;
	}
}
