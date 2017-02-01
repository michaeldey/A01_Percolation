package A01_Percolation;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	
	public static void main(String args[]){
		int width = 3;
		int experiments = 100;
		
		PercolationStats ps = new PercolationStats(width, experiments);
		
		
	}
	
	private int N; // N is the width of an N x N grid
	private int T; // T is the number of experiments to run
	
	//holds the values to feed to Percolation, values will be shuffled
	private int[] nArray; 
 

	public PercolationStats(int N, int T){
		//The constructor should take two arguments N and T, 

		
		//The constructor should throw a java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0.
		if (N <= 0 || T <= 0){
			 throw new IndexOutOfBoundsException("Both values must be greater than 0");
		}
		this.N = N;
		this.T= T;
		// perform T independent experiments on an N‐by‐N grid
		
		//create two arrays of N size (one designated for x, and one for y)
		nArray = new int[N*N];

		for (int i = 0; i < (N*N); i++){
			nArray[i] = i;

		}
		//shuffle the arrays so they output random numbers
		shuffle(nArray);

		
//		printShuffle(nArray); //print the values of the array in x,y coordinates

		
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
	
	private void shuffle(int[] nLengthArray){
		StdRandom.shuffle(nLengthArray);
	}
	

	
	/*
	 * getX returns the X value of the site
	 * 
	 * int P represents the index of 1DArray[ ]
	 * int N represents the width of the grid
	 */
	private int getX (int P){
//		System.out.println("X value of " + P + ": " + (P%N));
		return (P%N);}
	
	/*
	 * getY returns the Y value of the site
	 * 
	 * int P represents the index of 1DArray[ ]
	 * int N represents the width of the grid
	 */
	private int getY (int P){
//		System.out.println("Y value of " + P + ": " + (P/N));
		return (P/N);}
	
	/*
	 * xyTo1D takes x and y values and returns a position on 1D array
	 * 
	 * x = x value
	 * y = y value
	 * N = width of N x N array
	 */
	private int xyTo1D(int x, int y){ return ((x * N)+y); }

	/*
	 * printShuffle cycles through the int[] array, converts to x and y and prints that value
	 * 
	 * it is for troubleshooting purposes
	 */
	//private void printShuffle(int[] tempArray) {
//		for (int m : tempArray){
//			System.out.printf("%d, %d\n", getX(m), getY(m));
//		}
//		System.out.println();
	//}

}//end of PercolationStats


