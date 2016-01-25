package partOne;

public class MySqrt {
	public static double mySqrtLoop(double x, double epsilon){
		
		double ymin,ymax,ymitt,ysq;
		if(x < 0){
			return Double.NaN;
		} else if(x <= 1){
			ymin = 0;
			ymax = 1;
		} else {
			ymin = 1;
			ymax = x;
		}
		ymitt = (ymin+ymax)/2;
		ysq = ymitt*ymitt;
		while(!(ysq >= x-epsilon && ysq <= x+epsilon)){
			if(ysq > x){
				ymax = ymitt;
			} else if(ysq < x){
				ymin = ymitt;
			}
			ymitt = (ymin+ymax)/2;
			ysq = ymitt*ymitt;
		}
		return ymitt;
	}
	public static double mySqrtRecurse(double x, double epsilon){
		double ymin,ymax;
		if(x < 0){
			return Double.NaN;
		} else if(x <= 1){
			ymin = 0;
			ymax = 1;
		} else {
			ymin = 1;
			ymax = x;
		}
		return helpRecurse(ymin, ymax, x, epsilon);	
	}
	
	private static double helpRecurse(double ymin, double ymax, double x, double epsilon){
		double ymitt, ysq;
		ymitt = (ymin+ymax)/2;
		ysq = ymitt*ymitt;
		
		if(Math.abs(ysq-x) < epsilon){
			return ymitt;
		} else if(ysq > x){
			return helpRecurse(ymin, ymitt, x, epsilon);
		} else {
			return helpRecurse(ymitt, ymax, x, epsilon);
		}
	}
	
	
	public static void main(String args[]){
		double epsilon = 0.000001;
		System.out.println("Correct answer: 2, Given answer: " + mySqrtLoop(4, epsilon));
		System.out.println("Correct answer: -1, Given answer: " + mySqrtLoop(-5, epsilon));
		System.out.println("Correct answer: 0, Given answer: " + mySqrtLoop(0, epsilon));
		System.out.println("Correct answer: 1.73205, Given answer: " + mySqrtLoop(3, epsilon));
		System.out.println("Correct answer: 1000, Given answer: " + mySqrtLoop(100000, epsilon));
		System.out.println("Correct answer: 0.44721, Given answer: " + mySqrtLoop(0.2, epsilon));
		
		System.out.println("R, Correct answer: 2, Given answer: " + mySqrtRecurse(4, epsilon));
		System.out.println("R, Correct answer: -1, Given answer: " + mySqrtRecurse(-5, epsilon));
		System.out.println("R, Correct answer: 0, Given answer: " + mySqrtRecurse(0, epsilon));
		System.out.println("R, Correct answer: 1.73205, Given answer: " + mySqrtRecurse(3, epsilon));
		System.out.println("R, Correct answer: 1000, Given answer: " + mySqrtRecurse(100000, epsilon));
		System.out.println("R, Correct answer: 0.44721, Given answer: " + mySqrtRecurse(0.2, epsilon));
	}
}
