package A01_Percolation;


public class PercTest {

	public static void main(String[] args) {
		int N = 5;
		
		Percolation perc = new Percolation(N);
		
		perc.open(1, 2);
		
		System.out.printf("isOpen: %b\n",perc.isOpen(1, 2));
		
		System.out.printf("isFull: %b\n",perc.isFull(1, 2));
		
		System.out.printf("percolates: %b\n",perc.percolates());

	}

}
