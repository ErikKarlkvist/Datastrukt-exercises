package partTwo;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Lab2b {

	public static class PointComparator implements Comparator<DLList<double[]>.Node>{

		@Override
		public int compare(DLList<double[]>.Node arg0, DLList<double[]>.Node arg1) {
			return Double.compare(calculateValue(arg0),calculateValue(arg1));
		}

		//calculates distance with formula given in lab pm
		private double calculateValue(DLList<double[]>.Node node){
			double[] L = (double[]) node.getPrev().elt;
			double[] P = (double[]) node.elt;
			double[] R = (double[]) node.getNext().elt;

			double l1 = Math.sqrt(Math.pow(L[0] - P[0], 2) + Math.pow(L[1] - P[1], 2));
			double l2 = Math.sqrt(Math.pow(P[0] - R[0], 2) + Math.pow(P[1] - R[1], 2));
			double l3 = Math.sqrt(Math.pow(L[0] - R[0], 2) + Math.pow(L[1] - R[1], 2));

			return Math.abs(l1+l2-l3);
		}

	}

	public static double[] simplifyShape(double[] poly, int k)
	{
		DLList<double[]> pointList = new DLList<double[]>();
		Comparator<DLList<double[]>.Node> comp = new PointComparator();
		PriorityQueue<DLList<double[]>.Node> pQueue = new PriorityQueue<DLList<double[]>.Node>(poly.length/2, comp);

		//Add the first and last point to the list but not the priorityqueue
		double[] firstPoint = {poly[0], poly[1]};
		double[] lastPoint = {poly[poly.length-2], poly[poly.length-1]};

		DLList<double[]>.Node firstNode = pointList.addFirst(firstPoint);
		pointList.addLast(lastPoint);

		//add all other points to list and priorityqueue
		DLList<double[]>.Node nextNode = firstNode;
		for(int i=2; i < poly.length-2; i+=2){
			double[] tmpPoint = {poly[i],poly[i+1]};
			nextNode = pointList.insertAfter(tmpPoint, nextNode);
			pQueue.add(nextNode);
		}

		//Removes node with highest priority and updates queue
		while (pQueue.size()+2 > k) {
			DLList<double[]>.Node head = pQueue.remove();
			pointList.remove(head);

			// Here we remove the previous node, then add it again in order to update 
			// it's value, and place in the PiorityQueue, but only if it's not the first in the queue (not first or last in list)
			if (head.getPrev().getPrev() != null) {
				pQueue.remove(head.getPrev());
				pQueue.add(head.getPrev());
			}

			// Same here, but with the next node and last node
			if (head.getNext().getNext() != null) {
				pQueue.remove(head.getNext());
				pQueue.add(head.getNext());
			}
		}


		//convert DLList to array
		DLList<double[]>.Node currNode = pointList.getFirst();
		double[] newArray = new double[2*k];
		int i = 0;
		while (currNode != null) {
			newArray[i] = currNode.elt[0];
			newArray[i + 1] = currNode.elt[1];
			i = i + 2;
			currNode = currNode.getNext();

		}
		return newArray;
	}
}