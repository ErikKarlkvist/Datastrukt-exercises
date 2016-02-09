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
				return 0;
			}else*/ if(node1.getNext() == null || node1.getPrev() == null){
				return 1;
			}else if(node2.getNext() == null  || node2.getPrev() == null){
				return -1;
			}else{
				return (int)(calculateValue(node1) - calculateValue(node2));
			}
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

	/*public static class Point implements Comparable{
		double x,y, value;
		Point(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		public void setValue(double value){
			this.value = value;
		}
		
		public double getValue(){
			return value;
		}

		@Override
		public int compareTo(Object o) {
			if(o instanceof Point){
				Point compPoint = (Point)o;
				return (int)(value - compPoint.getValue());	
			}
			throw new IllegalArgumentException();
		}
	};*/
	
	public static double[] simplifyShape(double[] poly, int k)
	{
		DLList<Point> pointList = new DLList<Point>();
		PriorityQueue<DLList.Node> pQueue = new PriorityQueue<DLList.Node>(11, new NodeComparator());
		Point first = new Point();
		Point last = new Point();
		
		first.setLocation(poly[0], poly[1]);
		last.setLocation(poly[poly.length-2], poly[poly.length-1]);
		pointList.addFirst(first);
		pQueue.add(pointList.first);
		
		pointList.addLast(last);
		pQueue.add(pointList.last);
		for(int i=2; i < poly.length-2; i+=2){
			Point p = new Point();
			p.setLocation(poly[i], poly[i+1]);
			pointList.insertBefore(p, pointList.last);
			pQueue.add(pointList.last.prev);
		}
		
		while(pQueue.size() > k){
			Node rNode = pQueue.peek();
			pointList.remove(rNode);
			pQueue.remove(rNode);
			pQueue.remove(rNode.next);
			pQueue.remove(rNode.prev);
			pQueue.add(rNode.next);
			pQueue.add(rNode.prev);			
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
	
	public static void main(String[] args){
		double[] test = {
				2.0145, 1.0134,
				3.0746, 1.0232,
				4.0163, 2.0972,
				4.0516, 4.0152,
				9.0862, 4.0917,
				10.0828, 5.0998,
				10.0862, 7.0552,
				9.0382, 8.0277,
				7.0287, 8.0735,
				2.0933, 5.0293,
				1.0238, 4.0023,
				2.9283, 3.0283,
				2.093, 2.0239
		};
		double[] out = simplifyShape(test, 8);
		double[] out2 = simplifyShape(test, 6);
		
		System.out.print("{");
		for(int i = 0; i < out.length; i++){
			System.out.print(out[i] + ", ");
		}
		System.out.print("}");
		System.out.println("");
		System.out.println("");
		System.out.print("{");
		for(int i = 0; i < out2.length; i++){
			System.out.print(out2[i] + ", ");
		}
		System.out.print("}");
	}
}
