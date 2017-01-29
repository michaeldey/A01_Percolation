package A01_Percolation;

public class Percolation {
	
	 public Percolation(int N){
		 // create N‐by‐N grid, with all sites blocked 
		 System.out.println("Percolation constructor has been called with " + N + " as the size.");
	 }
		 
	 public void open(int i, int j){          
		// open site (row i, column j) if it is not open already
		 
		 /*
		  *  Throw a java.lang.IndexOutOfBoundsException 
		  *  if any argument to open(), 
		  *  isOpen(), or isFull() is outside its prescribed range
		  */
		 System.out.printf("Open method has been called.  x:%d y:%d\n", i, j);
	 }
			 
	 public boolean isOpen(int i, int j){			 
		 // is site (row i, column j) open?
		 
		 /*
		  *  Throw a java.lang.IndexOutOfBoundsException 
		  *  if any argument to open(), 
		  *  isOpen(), or isFull() is outside its prescribed range
		  */
		 
		 return false;
	 }
		 
	 public boolean isFull(int i, int j){     
		 // is site (row i, column j) full?
		 
		 /*
		  *  Throw a java.lang.IndexOutOfBoundsException 
		  *  if any argument to open(), 
		  *  isOpen(), or isFull() is outside its prescribed range
		  */
		 
		 return false;
	 }
		 
	 public boolean percolates(){             
		 // does the system percolate?
		 return false;
	 }

	public String numberOfOpenSites() {
		// TODO Auto-generated method stub
		// this should cound the number of sites that have been opened
		return null;
	}
	
	/*
	 * getX returns the X value of the site
	 * 
	 * int P represents the index of 1DArray[ ]
	 * int N represents the width of the grid
	 */
	private int getX (int P, int N){
		int i = (P%N);
		return i;
	}
	
	/*
	 * getY returns the Y value of the site
	 * 
	 * int P represents the index of 1DArray[ ]
	 * int N represents the width of the grid
	 */
	private int getY (int P, int N){return (P/N);}
	
	/*
	 * xyTo1D takes x and y values and returns a position on 1D array
	 * 
	 * x = x value
	 * y = y value
	 * N = width of N x N array
	 */
	private int xyTo1D(int x, int y, int N){ return ((y * N)+x); }
	
	/*
	 * checkIfTop checks if site is at the top of the grid
	 * 
	 * P = index location of site in 1D grid
	 * N = width of N x N grid
	 * 
	 * This method gets the Y value of P
	 * If y == 0, site is at top of grid
	 */
	private boolean checkIfTop(int P, int N){ return (getY(P, N)==0); }
	
	/*
	 * checkIfBottom checks if site is at the bottom of the grid
	 * 
	 * P = index location of site in 1D grid
	 * N = width of N x N grid
	 * 
	 * This method gets the Y value of P
	 * If y == N-1, site is at bottom of grid
	 */
	private boolean checkIfBottom(int P, int N){ return (getY(P, N)==N-1);}
	
	/*
	 * checkIfRight checks if site is at the right of the grid
	 * 
	 * P = index location of site in 1D grid
	 * N = width of N x N grid
	 * 
	 * This method gets the X value of P
	 * If x == N-1, site is at right side of grid
	 */
	private boolean checkIfRight(int P, int N){return (((P+1)%N) == 0);}
	
	/*
	 * checkIfLeft checks if site is at the left side of the grid
	 * 
	 * P = index location of site in 1D grid
	 * N = width of N x N grid
	 * 
	 * This method gets the X value of P
	 * If x == 0, site is at left side of grid
	 */
	private boolean checkIfLeft(int P, int N){	return (getX(P, N)==0);	}
	
	public void checkNeighbors(int P, int[] oneDArray, int N){
		int neighbor;
		
		// if site isn't on the top border
		if (!checkIfTop(P, N)){ 
			System.out.print("Top: ");
			neighbor = neighborValue(P, (P-N), oneDArray); }
		else {
			//site is on the top
			// todo connect site to water
			System.out.println("This site is water, there is no one above.");
		}
		
		// if site isn't on the bottom border
		if (!checkIfBottom(P, N)){ 
			System.out.print("Bottom: ");
			neighbor = neighborValue(P, (P+N), oneDArray);}
		else{
			// site is on bottom
			// todo connect site to ground
			System.out.println("This site is ground, there is no one below.");
		}
		
		// if site isn't on the right side border
		if (!checkIfRight(P, N)){
			System.out.print("Right");
			neighbor = neighborValue(P, (P+1), oneDArray);
			
			// todo connect neighbors if applicable
		}
		else{
			//site is on the right
			System.out.println("This site is on the right, there is no one to the right.");
		}
		
		// if site isn't on the left side border
		if (!checkIfLeft(P, N)){
			System.out.print("left:");
			neighbor = neighborValue(P, (P-1), oneDArray);
			
			//todo connect neighbors if applicable
		}	
		else{
			//site is on the left
			System.out.println("This site is on the left, there is no one to the left");
		}

	}//end of checkNeighbors
	
	public int neighborValue(int P, int neighbor, int[] oneDArray){
		switch (oneDArray[(neighbor)]){
		case 0:
			System.out.println("my neighbor is closed");

			return 0;
		case 1:
			System.out.println("my neighbor is open");
			//todo merge P and neighbor
			return 1;
		case 2:
			System.out.println("my neighbor is water");
			//todo merge P and neighbor
			// make P water
			return 2;
		case 3:
			System.out.println("my neighbor is ground");
			//todo merge P and neighbor
			// make P ground
			return 3;
		default:
			return 0;
	}//end of switch
	}
	
}
