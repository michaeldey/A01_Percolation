package A01_Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	public static void main(String args[]){
		int width = 200;
		int experiments = 100;		
		PercolationStats ps = new PercolationStats(width, experiments);		
		
	}
	
	private int N; // N is the width of an N x N grid
	private int T; // T is the number of experiments to run	
	private int[] nArray; //holds the values to feed to Percolation, values will be shuffled
	private Percolation perc;
	private double[] percScores;// array to record all the percolation scores

	private double standardDeviation=0;
	private double mean=0;
	private double confidenceLow=0;
	private double confidenceHigh=0;

	public PercolationStats(int N, int T){
		//The constructor should take two arguments N and T		
		//The constructor should throw a java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0.
		if (N <= 0 || T <= 0){
			 throw new IndexOutOfBoundsException("Both values must be greater than 0");
		}
		this.N = N;
		this.T= T;		
		int nByN = N * N;// speed up the process by not constantly multiplying N x N
		double count = 0;		
		percScores = new double[T]; // array to record all the percolation scores
		int pSPointer = 0; //pointer for percScores[]
		
		
		//create an array of all possible input values
		nArray = new int[nByN];
		for (int i = 0; i < (nByN); i++){nArray[i] = i;}
		
		// perform T independent experiments on an N‐by‐N grid
		while (T > 0){
			perc = new Percolation(N); //create a new Percolation object of N width
			shuffle(nArray); //shuffle the array so it contains random numbers
			
			//loop through every value in the array
			for (int m : nArray){
				perc.open(getX(m), getY(m));//feed perc x and y values
				count++;
				
				//when the system percolates
				if (perc.percolates()){
					percScores[pSPointer++] = (count/nByN);//record threshold into array					
					count = 0; //reset the count
					break; //stop once percolation occurs and go to next simulation
				}
			}
			T--;
		}//end of while loop
				
		mean = calculateMean();
		standardDeviation = calculateStddev();
		confidenceLow = calculateConfidenceLow();
		confidenceHigh = calculateConfidenceHigh();
		
		System.out.printf("Mean: %f\n", mean());
		System.out.printf("Standard Deviation: %f\n", stddev());
		System.out.printf("Confidence Low: %f\n", confidenceLow());
		System.out.printf("Confidence High: %f\n", confidenceHigh());		
	}//end of constructor
	
	//calculate mean
	private double calculateMean(){return StdStats.mean(percScores);}
	//calculate standard deviation
	private double calculateStddev(){return StdStats.stddev(percScores);}	
	//calculate confidence low
	private double calculateConfidenceLow(){return (mean - ((1.96 * standardDeviation)/T));}
	//calculate confidence high
	public double calculateConfidenceHigh(){return (mean + ((1.96 * standardDeviation)/T));}
	
	//return mean
	public double mean(){return mean;}
	//return standard deviation
	public double stddev(){return standardDeviation;}
	//return confidence low	
	public double confidenceLow(){return confidenceLow;}
	//return confidence high
	public double confidenceHigh(){return confidenceHigh;}
	
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


