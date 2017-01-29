package A01_Percolation;


public class PercTest {

	public static void main(String[] args) {
		int N = 5;

		
		int[] oneDArray = new int[N*N];
		for (int i = 0; i < oneDArray.length; i++){
			oneDArray[i] = 0;
//			System.out.println("oneDArray adds " + i);
		}
		
		Percolation perc = new Percolation(N);
		
		perc.checkNeighbors(2, oneDArray, N);
		

	}

}
