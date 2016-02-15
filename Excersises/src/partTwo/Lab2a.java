package partTwo;

public class Lab2a {
	public static double[] simplifyShape(double[] poly, int k)
	{
		double[] polyCopy = poly.clone();
		int pLength = polyCopy.length;
		double minValue = Double.MAX_VALUE;
		int indexSValue = -1;
		if(pLength>4){
			while(pLength/2 > k){
				for(int i = 0; i < pLength-6; i+=2){
					double l1 = calcDistFromArray(polyCopy,i,i+2);
					double l2 = calcDistFromArray(polyCopy,i+2,i+4);
					double l3 = calcDistFromArray(polyCopy,i,i+4);

					double value = (l1 + l2 - l3);
					if(value < minValue){
						minValue = value;
						indexSValue = i+2;
					}
				}//end for
				polyCopy = removePoint(polyCopy, indexSValue);
				pLength = polyCopy.length;
				minValue = Double.MAX_VALUE;
			}//end while
			return polyCopy;
		} else if (pLength == 4)
			return poly;
		else{//should never happen?
			throw new IllegalArgumentException();
		}
	}

	private static double[] removePoint(double[] array, int index){
		int length = array.length;
		// A new array, 2 shorter (1 point) than the argument array
		double[] tmpArray = new double[length - 2];
		// Fills the first part of the new array up until the 2 doubles that will be removed
		for(int m = 0; m < index; m++){
			tmpArray[m] = array[m];
		}
		// Continues to fill the new array after the 2 doubles to be removed, effectively
		// making a new array without the now removed doubles
		for(int j = index+2; j < length; j++){
			tmpArray[j-2] = array[j];
		}
		return tmpArray;
	}

	private static double calcDistFromArray(double[] array, int point1, int point2){
		double x = (array[point1]-array[point2]);
		double y = (array[point1+1]-array[point2+1]);
		return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));

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
		
		DrawGraph myGraph = new DrawGraph();
		for(int i = 0; i < out2.length; i+=4){
			//myGraph.drawLine(out[i], out[i+1], out[i+2], out[i+3], Color.RED, 5, 0);
		}
	}	
}
