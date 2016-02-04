package partTwo;

import java.awt.Point;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Lab2b {
	
	public static class NodeComparator<E extends DLList.Node> implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			DLList.Node node1 = (DLList.Node)arg0;
			DLList.Node node2 = (DLList.Node)arg1;
			
			return 0;
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
		for(int i=0; i < poly.length; i+=2){
			Point p = new Point();
			p.setLocation(poly[i], poly[i+1]);
			pointList.addLast(p);
			pQueue.add(pointList.last);
		}
		return poly;
	}
}
