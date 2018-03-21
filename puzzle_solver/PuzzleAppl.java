package lab4;


public class PuzzleAppl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PuzzleSolver puzzleSlvr = new PuzzleSolver();
		puzzleSlvr.readFromFile("letters.txt");
		int a = 0;
	       for(a = 0; a < puzzleSlvr.wordList.size(); a++){
	        	puzzleSlvr.solveAndPrint(puzzleSlvr.wordList.get(a));
	        }
		System.out.println("Program ends here....");
	}

}
