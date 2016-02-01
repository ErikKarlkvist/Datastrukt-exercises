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
				double L = calcDist(polyCopy[i],polyCopy[i+1]);
				double P = calcDist(polyCopy[i+2],polyCopy[i+3]);
				double R = calcDist(polyCopy[i+4],polyCopy[i+5]);
				
				double l1 = L - P;
				double l2 = P - R;
				double l3 = L - R;
				double value = (l1 + l2 - l3);
				/*if(value==0){
					System.out.println("if(value == 0)"+value);
					polyCopy = removePoint(polyCopy, i);
					pLength = polyCopy.length;
				} else*/ 
				if(value < minValue){
					minValue = value;
					indexSValue = i+2;
				}
			}//end for
			System.out.println("End of for" + minValue);
			polyCopy = removePoint(polyCopy, indexSValue);
			pLength = polyCopy.length;
			minValue = Double.MAX_VALUE;
		  }
		  return polyCopy;
	  } else if (pLength == 4)
		  return poly;
	  else{
    	  throw new IllegalArgumentException();
	  }
  }
  
  private static double[] removePoint(double[] array, int index){
	  int length = array.length;
	  double[] tmpArray = new double[length - 2];
	  for(int m = 0; m <= index+1; m++){
			tmpArray[m] = array[m];
		}
		for(int j = index+4; j < length; j++){
			tmpArray[index+2] = array[j];
		}
		return tmpArray;
  }
  
  private static double calcDist(double x, double y){
	  return Math.sqrt(Math.pow(x,2)+ Math.pow(y,2));
  }
  
  public static void main(String[] args){
	  double[] test = {5,8,10,3,2,9,1,1,2,2,3,3};
	  double[] out = simplifyShape(test, 4);
	  System.out.print("{");
	  for(int i = 0; i < out.length; i++){
		  System.out.print(out[i] + ", ");
	  }
	  System.out.print("}");
  }
}
