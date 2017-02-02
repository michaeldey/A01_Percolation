package A01_Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	public static void main(String args[]){
		int width = 3;
		int experiments = 3;
		
		PercolationStats ps = new PercolationStats(width, experiments);		
		
	}
	
	private int N; // N is the width of an N x N grid
	private int T; // T is the number of experiments to run	
	private int[] nArray; //holds the values to feed to Percolation, values will be shuffled
	private Percolation perc;
	private int[] percScores;// array to record all the percolation scores

	public PercolationStats(int N, int T){
		//The constructor should take two arguments N and T		
		//The constructor should throw a java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0.
		if (N <= 0 || T <= 0){
			 throw new IndexOutOfBoundsException("Both values must be greater than 0");
		}
		this.N = N;
		this.T= T;
		
		int nByN = N * N;// speed up the process by not constantly multiplying N x N
		int count = 0;
		
		percScores = new int[T]; // array to record all the percolation scores
		int pSPointer = 0; //pointer for percScores[]
		
		
		//create an array of all possible input values
		nArray = new int[nByN];
		for (int i = 0; i < (nByN); i++){nArray[i] = i;}
		
		// perform T independent experiments on an N‐by‐N grid
		while (T > 0){
			perc = new Percolation(N); //create a new Percolation object of N width
			shuffle(nArray); //shuffle the array so it contains random numbers
			
			for (int m : nArray){
				perc.open(getX(m), getY(m));
				count++;
				if (perc.percolates()){
					System.out.println("Percolates at: " + count);
					percScores[pSPointer++] = count;//record count into scores array					
					count = 0; //reset the count
					break; //stop once percolation occurs and go to next simulation
				}
			}
		

			T--;
		}
		
		//print out the values of percScores
		System.out.println("Perc Scores:");
		for (int m : percScores){
			System.out.printf("%d\n", m);
		}
		
		System.out.printf("Mean: %f\n", mean());
		System.out.printf("Standard Deviation: %f\n", stddev());
	}//end of constructor
	
	public double mean(){return StdStats.mean(percScores);}
	
	public double stddev(){return StdStats.stddev(percScores);}
	
	public double confidenceLow(){          
		// low  endpoint of 95% confidence interval
		
		return 0.0;
	}
	
	public double confidenceHigh(){         
		// high endpoint of 95% confidence interval
		
		return 0.0;
	}
	
	/*
	 * shuffle takes an int[] and shuffles it
	 */
	private void shuffle(int[] nLengthArray){StdRandom.shuffle(nLengthArray);}
	
	/*
	 * getX returns the X value of the site
	 * 
	 * int P represents the index of 1DArray[ ]
	 * int N represents the width of the grid
	 */
	private int getX (int P){return (P%N);}
	
	/*
	 * getY returns the Y value of the site
	 * 
	 * int P represents the index of 1DArray[ ]
	 * int N represents the width of the grid
	 */
	private int getY (int P){return (P/N);}
	
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
	private void printShuffle(int[] tempArray) {
		for (int m : tempArray){
			System.out.printf("%d, %d\n", getX(m), getY(m));
		}
		System.out.println();
	}

}//end of PercolationStats


