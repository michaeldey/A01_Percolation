package A01_Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	 public static void main(String[] args) {
	 //todo write method tests
		 //test open method
		 
		 //test percolation constructor
	 }
	
	private int N; // N is the length of one array side
	private int[] oneDArray;//this will hold the value of sites of the N x N grid
	private int count = 0; //this counts how many sites have been opened
	private WeightedQuickUnionUF uf;
	private boolean percolates = false;
	
	/*
	 * Percolation creates an N by N grid with all sites blocked
	 * 
	 * A value of 0 in the array means blocked 
	 * A value of 1 in the array means open
	 * A value of 2 in the array means water
	 * A value of 3 in the array means ground
	 */
	public Percolation(int N){
		 this.N = N;
		 oneDArray = new int[N*N];
		for (int i = 0; i < oneDArray.length; i++){
			oneDArray[i] = 0;
		}
		uf = new WeightedQuickUnionUF(N*N);//create Union find object with N*N initial values
//		printValues();
	 }
	
	/*
	 * open takes x and y value of the NxN array and makes it "open"
	 * 
	 * A value of 1 means a site is now open, and not connected to water or ground
	 */
	 public void open(int i, int j){
//		 System.out.printf("\n*****\n");
		 /*
		  *  Throw a java.lang.IndexOutOfBoundsException 
		  *  if any argument to open(), 
		  *  isOpen(), or isFull() is outside its prescribed range
		  */
		 catchOutOfBounds(i,j);		  
		 int pointer = xyTo1D(i, j);
		 //if statement is to eliminate fake "opens" in interactive Percolation
		 if (oneDArray[pointer]==0) count++; //add to the "open" count
		 
		 oneDArray[pointer]=1; // 1 represents open
		 if (checkIfRight(pointer)) oneDArray[pointer]=1;//open site
		 if (checkIfLeft(pointer)) oneDArray[pointer]=1;//open site
		 if (checkIfTop(pointer)) oneDArray[pointer]=2;//connected to water
		 if (checkIfBottom(pointer)) oneDArray[pointer]=3;//connected to ground
		
//		 printValues();
		 checkNeighbors(pointer);
		 
	 }
	
	 /*
	  * isOpen takes x and y value of the NxN array and checks if it's open
	  * 
	  * a value of 1 = "open"
	  */
	 public boolean isOpen(int i, int j){			 
		 /*
		  *  Throw a java.lang.IndexOutOfBoundsException 
		  *  if any argument to open(), 
		  *  isOpen(), or isFull() is outside its prescribed range
		  */
		 catchOutOfBounds(i,j);		  
		 int pointer = xyTo1D(i, j);
		 return(!(oneDArray[pointer]==0)); // Site is open if it is not a 0
	 }
	
	 /*
	  * isFull takes x and y value of the NxN array and checks if it's full
	  * 
	  * a value of 2 = "Full" which means it has water
	  */
	 public boolean isFull(int i, int j){     
		 /*
		  *  Throw a java.lang.IndexOutOfBoundsException 
		  *  if any argument to open(), 
		  *  isOpen(), or isFull() is outside its prescribed range
		  */
		 catchOutOfBounds(i,j);		  
		 int pointer = xyTo1D(i, j);
		 oneDArray[pointer] = oneDArray[uf.find(pointer)];
		 // find the value of the parent of pointer and return that value
		 return(oneDArray[pointer]==2); // 2 represents water
	 }
		 
	 public boolean percolates(){             
		 // does the system percolate?
		 return percolates;
	 }

	 /*
	  * numberOfOpenSites returns a string telling how many open sites there are
	  */
	public String numberOfOpenSites() {
		// this should count the number of sites that have been opened		
		return String.format("There are %d open sites.\n", count);
	}

	/*
	 * catchOutOfBounds determines if a given pointer is outside of the array bounds
	 * 
	 * If the pointer is outside of the array bounds, it throws an exception
	 */
	private void catchOutOfBounds(int i, int j){
		 /*
		  *  Throw a java.lang.IndexOutOfBoundsException 
		  *  if any argument to open(), 
		  *  isOpen(), or isFull() is outside its prescribed range
		  */
		 if (i < 0 || i >= N){
			 throw new IndexOutOfBoundsException("X value " + i +
					 " must be between 0 and " + (N-1));
		 }
		 if (j < 0 || j >= N){
			 throw new IndexOutOfBoundsException("Y value " + j +
					 " must be between 0 and " + (N-1));
		 }		 
		 return;
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
	 * checkIfTop checks if site is at the top of the grid
	 * 
	 * P = index location of site in 1D grid
	 * N = width of N x N grid
	 * 
	 * This method gets the Y value of P
	 * If y == 0, site is at top of grid
	 */
	private boolean checkIfTop(int P){ 
		boolean m = (getY(P)==0);
//		if (m) System.out.println("I am at the top");
		return (getY(P)==0); 
		}
	
	/*
	 * checkIfBottom checks if site is at the bottom of the grid
	 * 
	 * P = index location of site in 1D grid
	 * N = width of N x N grid
	 * 
	 * This method gets the Y value of P
	 * If y == N-1, site is at bottom of grid
	 */
	private boolean checkIfBottom(int P){
		boolean m = ((getY(P))==(N-1));
//		if (m) System.out.println("I am at the bottom");
		return ((getY(P))==(N-1));
		}
	
	/*
	 * checkIfRight checks if site is at the right of the grid
	 * 
	 * P = index location of site in 1D grid
	 * N = width of N x N grid
	 * 
	 * This method gets the X value of P
	 * If x == N-1, site is at right side of grid
	 */
	private boolean checkIfRight(int P){
//		if ((((P+1)%N) == 0)) System.out.println("I am on the right.");
		return (((P+1)%N) == 0);
	}
	
	/*
	 * checkIfLeft checks if site is at the left side of the grid
	 * 
	 * P = index location of site in 1D grid
	 * N = width of N x N grid
	 * 
	 * This method gets the X value of P
	 * If x == 0, site is at left side of grid
	 */
	private boolean checkIfLeft(int P){	
//		System.out.printf("On left border: %b\n", (getX(P)==0));
		return (getX(P)==0);		
	}
	
	private void checkNeighbors(int P){
		int neighbor;
		
		// if site isn't on the top border
		if (!checkIfTop(P)){ 
//			System.out.print("Top: ");
			neighbor = neighborValue(P, (P-N)); 
			
		}
		else {
			//site is on the top
			// todo connect site to water
//			System.out.println("This site is water, there is no one above.");
		}
		
		// if site isn't on the bottom border
		if (!checkIfBottom(P)){ 
//			System.out.print("Bottom: ");
			neighbor = neighborValue(P,(P+N));}
		else{
			// site is on bottom
			// todo connect site to ground
//			System.out.println("This site is ground, there is no one below.");
		}
		
		// if site isn't on the right side border
		if (!checkIfRight(P)){
//			System.out.print("Right: ");
			neighbor = neighborValue(P,(P+1));
			
			// todo connect neighbors if applicable
		}
		else{
			//site is on the right
//			System.out.println("This site is on the right, there is no one to the right.");
		}
		
		// if site isn't on the left side border
		if (!checkIfLeft(P)){
//			System.out.print("left: ");
			neighbor = neighborValue(P,(P-1));
			
			//todo connect neighbors if applicable
		}	
		else{
			//site is on the left
//			System.out.println("This site is on the left, there is no one to the left");
		}

	}//end of checkNeighbors
	
	/*
	 * neighborValue takes in a pointer value of the current site's neighbor
	 *  
	 *  it evaluates what value the neighbor has, 
	 *  and takes the appropriate action based on it's value
	 */
	private int neighborValue(int P, int neighbor){
		boolean hasWater=(oneDArray[P] == 2 || oneDArray[neighbor] == 2);
		boolean hasGround=(oneDArray[P] == 3 || oneDArray[neighbor] == 3);
		
		switch (oneDArray[(neighbor)]){
		case 0:
//			System.out.println("my neighbor is closed");
			return 0;
		case 1:
//			System.out.println("my neighbor is open");
			//merge P and neighbor
			uf.union(P, neighbor);
			if (hasWater) {
				oneDArray[uf.find(P)]=2; //make the head of the list water
				return 2;
			}
			else if (hasGround) {
				oneDArray[uf.find(P)]=3; //make the head of the list ground
				return 3;
			}
			else return 1;
		case 2:
//			System.out.println("my neighbor is water");
			uf.union(P, neighbor);
			oneDArray[P]=2;
			oneDArray[uf.find(P)]=2;			
			// make P water

			if (hasGround){
				percolates = true;//I am ground and I am touching water
			}
			return 2;
		case 3:
//			System.out.println("my neighbor is ground");
			uf.union(P, neighbor);

			if (hasWater){
				percolates = true;//I am ground and I am touching water
				oneDArray[P]=2;
				oneDArray[uf.find(P)]=2;
				return 2;
			}
			else{
				// make P ground
				oneDArray[P]=3;
				oneDArray[uf.find(P)]=3;
				return 3;
			}
		default:
			return 0;
	}//end of switch
	}
	
//	public void printValues(){
//		for (int i = 0; i < oneDArray.length; i++){
//			if (i%N == 0)System.out.println();
//			System.out.print(oneDArray[i]+" ");
//		}
//		System.out.println();
//	}
}
