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
		return null;
	}

}
