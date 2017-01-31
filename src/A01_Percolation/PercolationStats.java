package A01_Percolation;

public class PercolationStats {
	
	private int N; // N is the width of an N x N grid
	private int T; // T is the number of experiments to run

	public PercolationStats(int N, int T){
		//The constructor should take two arguments N and T, 

		
		//The constructor should throw a java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0.
		if (N <= 0 || T <= 0){
			 throw new IndexOutOfBoundsException("Both values must be greater than 0");
		}
				
		// perform T independent experiments on an N‐by‐N grid
	}
	
	public double mean(){                   
		// sample mean of percolation threshold
		
		//use standard statistics from stdlib.jar 
		//to compute the sample mean and standard deviation
		
		return 0.0;
	}
	
	public double stddev(){                 
		// sample standard deviation of percolation threshold
		
		//use standard statistics from stdlib.jar 
		//to compute the sample mean and standard deviation
		
		return 0.0;
	}
	
	public double confidenceLow(){          
		// low  endpoint of 95% confidence interval
		
		return 0.0;
	}
	
	public double confidenceHigh(){         
		// high endpoint of 95% confidence interval
		
		return 0.0;
	}
	
	private int getRandomNumber(){
		//Use standard random from stdlib.jar to generate random numbers
		
		return 0;
	}
	
}
