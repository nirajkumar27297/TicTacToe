import java.util.*;
public class TicTacToe {
	private char[][] board = new char[3][3];
	
	
	TicTacToe() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.board[i][j] = ' ';
			}
		}
	}
	public void play() {
		//Creating random object
		Random rand = new Random();
		// Generating a random number in range[0,1] if 0 then computer chance and 1 then player chance
		int checkOption = rand.nextInt(2);
		
		if(checkOption == 1) {
			System.out.println("Player Chance to Play");
		}
		else {
			System.out.println("Computer Chance to Play");
		}
	}

	public static void main(String[] args) {
		
		//Creating objects for TicTacToe Game
		TicTacToe gamePlay = new TicTacToe();
		gamePlay.play();
	}

}
