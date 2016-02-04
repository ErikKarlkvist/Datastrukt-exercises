package partTwo;

import java.awt.Color;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

// Erland Holmström 2015 01 22
// modifierad av Fredrik Lindblad

public class Lab2 {

	private static DrawGraph shape;

	/**
	 * Creates a list from user input (or file by redirection).
	 * Format should be two integer points per line separated by space x y
	 * @return a (one-dimensional) array containing the values (x1, y1, x2, y2, etc)
	 */
	public static double[] readShape() {
		Scanner sc = new Scanner(System.in);

		List<Double> list = new ArrayList<Double>();

		int n = 0;
		while (sc.hasNext()) { // antag 2 korrekta tal per rad
			double n1 = sc.nextDouble();
			double n2 = sc.nextDouble();
			list.add(new Double(n1));
			list.add(new Double(n2));
			n+=2;
		}
		//sc.close();

		double[] arr = new double[n];
		for (int i = 0; i < n; i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}


	/**
	 * Draws a line between every point in the list.
	 * @param c The color to draw in
	 * @param layer draw in base layer, overlay1 or overlay2
	 */
	public static void drawShape(double[] points, Color c, DrawGraph.Layer l) {
		shape.clearLayer(l);

		int n = points.length / 2;
		int x1 = (int)points[0];
		int y1 = (int)points[1];
		int x2, y2;
		for (int i = 1; i < n; i++) {
			x2 = (int)points[2 * i];
			y2 = (int)points[2 * i + 1];
			shape.drawLine(x1, y1, x2, y2, c, 2.0, l);
			x1 = x2;
			y1 = y2;
		}
		shape.repaint();
	}

	public static void main(String[] args) {

		int size = 0; //n number of points in shape

		int j = -1; // count arguments
		int w = 15; // width
		int h = 15; // heigth
		int k = 8; // take away to k points
		// tag hand om flaggorna								 
		while  ( j+1 < args.length && args[j+1].charAt(0) == '-' )  {
			j = j+1;
			//System.out.println("j= " + j + " " + args[j] );
			switch ( args[j].charAt(1) ) {
			case 'k': {
				try {
					k = Integer.parseInt( args[j].substring(2) );
				}
				catch ( NumberFormatException e ) {
					System.out.println("main: k must have numerical argument");
					System.exit(0);
				}
			}
			break;
			case 'w' : {
				try {
					w = Integer.parseInt( args[j].substring(2) );
				}
				catch ( NumberFormatException e ) {
					System.out.println("main: w must have numerical argument");
					System.exit(0);
				}
			}
			break;
			case 'h' : {
				try {
					h = Integer.parseInt( args[j].substring(2) );
				}
				catch ( NumberFormatException e ) {
					System.out.println("main: h must have numerical argument");
					System.exit(0);
				}
			}
			break;
			default : 
				System.err.println("main: unknown flag: " + args[j]);
				System.exit(0);   // avsluta
			} // end switch
		} // end loop;
		// kolla argument
		if (k<2 || w<10 || h<10) {
			System.err.println("main: either of k<2 || w<10 || h<10" );
			System.exit(0);   // avsluta
		}

		shape = new DrawGraph(w, h);

		// read from user/file
		double[] points = readShape();

		System.out.println("********** draws original shape in black");
		// draw original
		drawShape(points, Color.BLACK, DrawGraph.Layer.BASE);

		double[] simppointsa = Lab2a.simplifyShape(points, k);

		try{ // take a break - oförklarliga timing problems med uppritningen
			//			outline.wait(1000);
		} catch (Exception e ) {
		}

		System.out.println("********** draws shape generated by algorithm a in red");
		drawShape(simppointsa, Color.RED, DrawGraph.Layer.OVERLAY1);

		double[] simppointsb = Lab2b.simplifyShape(points, k);

		try{ // take a break - oförklarliga timing problems med uppritningen
			//			outline.wait(1000);
		} catch (Exception e ) {
		}
		System.out.println("********** draws shape generated by algorithm b in blue");
		drawShape(simppointsb, Color.BLUE, DrawGraph.Layer.OVERLAY2);

	}
}
