
import java.util.*;
public class TicTacToe {
	private char[][] board= new char[3][3];
	private char computerSymbol;
	private char userSymbol;
	
	TicTacToe() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.board[i][j] = ' ';
			}
		}
	}
	public void play() {
		//Creating Scanner class Object to take input 
		Scanner sc = new Scanner(System.in);
		//Creating random object
		Random rand = new Random();
		char input;
		// Generating a random number in range[0,1] if 0 then computer chance and 1 then player chance
		int checkOption = rand.nextInt(2);
		
		if(checkOption == 1) {
			System.out.println("Player Chance to Play");
			System.out.println("Choose Either X or O");
			//Taking input from the user
			input = sc.next().charAt(0);
			this.userSymbol = input;
			if(input == 'X') {
				this.computerSymbol = 'O';
			}
			else {
				this.computerSymbol = 'X';
			}
		}
		
		else {
			System.out.println("Computer Chance to Play");
			//Generating a random number in range[0,1] if 0 then input is "O" else "X"
			int computerChance = rand.nextInt(2);
			if(computerChance == 0) {
				this.computerSymbol = 'O';
				this.userSymbol = 'X';
			}
			else {
				this.computerSymbol = 'X';
				this.userSymbol = 'O';
			}
			System.out.println("The computer chooses "+this.computerSymbol);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creating objects for TicTacToe Game
		TicTacToe gamePlay = new TicTacToe();
		gamePlay.play();
	}

}
