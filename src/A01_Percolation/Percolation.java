package A01_Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	 public static void main(String[] args) {
		 Percolation perc = new Percolation(2);//make a 9x9 grid
		 perc.printValues(); // look at the initial grid
		 	 
		 //test percolation constructor
		 if (	perc.N!=2 ||
				perc.count!=0 ||
				perc.percolates!=false
			)System.out.println("Constructor has failed test");
		 
		 //test open method
		 perc.open(0, 1);
		 if (perc.count != 1)System.out.println("Count has failed");
		 
		 //test merge method and percolates method
		 if (perc.getValueOfParent(1)!=2)System.out.println("Merge has failed");		 
		 perc.open(1, 1);
		 if (perc.count != 2 || perc.percolates != true)System.out.println("Open has failed");		 
		 if (perc.getValueOfParent(3)!=2)System.out.println("Merge has failed");

		 //test isOpen method
		 if (!perc.isOpen(1, 1))System.out.println("isOpen method failed");
		 //test isFull method
		 if (!perc.isFull(1, 1))System.out.println("isFull method failed");
		 
		 perc.printValues();
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
		 /*
		  *  Throw a java.lang.IndexOutOfBoundsException 
		  *  if any argument to open(), 
		  *  isOpen(), or isFull() is outside its prescribed range
		  */
		 catchOutOfBounds(i,j);		  
		 int pointer = xyTo1D(i, j);
		 //if statement is to eliminate fake "opens" in interactive Percolation
		 if (getValueOfParent(pointer)==0){
			 count++; //add to the "open" count
			 if (checkIfRight(pointer) || checkIfLeft(pointer)) setValueOfParent(pointer,1);//open site
			 if (checkIfTop(pointer)) setValueOfParent(pointer,2);//connected to water
			 if (checkIfBottom(pointer)) setValueOfParent(pointer,3);//connected to ground
			 if (getValueOfParent(pointer)==0)setValueOfParent(pointer,1);
		 }		 
		 
		 mergeAllNeighbors(pointer);
		 oneDArray[pointer] = getValueOfParent(pointer);
//		 printValues();
		 
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
//		 System.out.println("Percolates: " + percolates);
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
	
	private void mergeAllNeighbors(int P){
		int neighbor;
		// check if P is on the outside perimeter, if not merge with neighbor
		if (!checkIfRight(P)){
			neighbor = (P+1);
			merge(P, neighbor);
		}
		if (!checkIfLeft(P)){
			neighbor = (P-1);
			merge(P, neighbor);
		}
		if (!checkIfBottom(P)){
			neighbor = (P+N);
			merge(P, neighbor);
		}
		if (!checkIfTop(P)){
			neighbor = (P-N);
			merge(P, neighbor);
		}		
		
	}
	
	/*
	 * merge takes two integers: P (the current site), and neighbor (a site next to P)
	 * 
	 * it determines if one or the other is water, or ground
	 * if ground is detected, it merges the two, and makes the parent water or ground
	 * 
	 * if both water and ground are detected, percolation is set to true
	 * 
	 * if neither water nor ground is detected, the parent is set to open
	 */
	private void merge(int P, int neighbor){
		int neighborValue = getValueOfParent(neighbor);
		if (neighborValue==0) return; //neighbor is not open. Exit the method
		
		int pValue = getValueOfParent(P);
		boolean hasWater = false;
		boolean hasGround = false;
		if (pValue == 3 || neighborValue == 3){hasGround = true;}
		if (pValue == 2 || neighborValue == 2){hasWater = true;}
		uf.union(P, neighbor); //merge the trees
		if (hasWater && hasGround) percolates = true;
		setValueOfParent(P,1); //make parent open
		if (hasGround == true) setValueOfParent(P,3); //make parent ground
		if (hasWater == true) setValueOfParent(P,2); //make parent water
			// setting water comes after setting ground because I want the squares to turn blue
	}
	
	private int getValueOfParent(int P){
		int i = uf.find(P);
		return oneDArray[i];
	}
	
	private void setValueOfParent(int P, int value){
		int i = uf.find(P);
		oneDArray[i] = value;
		oneDArray[P] = value;//make sure the value of P is correct
	}
	
	//helper method to determine what my grid looks like
	private void printValues(){
		for (int i = 0; i < oneDArray.length; i++){
			if (i%N == 0)System.out.println();
			System.out.print(getValueOfParent(i)+" ");
		}
		System.out.println();
	}
}
