
import java.util.*;
public class TicTacToe {
	private char[][] board= new char[3][3];
	private char userSymbol;
	private char computerSymbol;
	
	TicTacToe() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.board[i][j] = ' ';
			}
		}
	}
	public static void main(String[] args) {
		//Creating objects for TicTacToe Game
		TicTacToe gamePlay = new TicTacToe();
		gamePlay.play();
	}
	
	public void play() {
		//Creating Scanner class Object to take input 
		Scanner sc = new Scanner(System.in);
		//Creating random object
		Random rand = new Random();
		
		char input;
		// Generating a random number in range[0,1] if 0 then computer chance and 1 then player chance
		int checkOption = rand.nextInt(2);
		this.displayBoard();
		if(checkOption == 1) {
			System.out.println("Player Chance to Play");
			System.out.println("Choose Either X or O");
			//Taking input from the user
			input = sc.next().charAt(0);
			this.userSymbol = input;
			System.out.println("You chose "+this.userSymbol);
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
		this.startPlaying(checkOption);
	
	}
	public void startPlaying(int turn) {
		Scanner sc = new Scanner(System.in);
		int move;
		char symbol;
		while(true) {
					
		
		if(turn == 1) {
			symbol = this.userSymbol;
			System.out.println("Enter the move in range[1-9]");
			move = sc.nextInt();
			move -= 1;
			boolean moveValid = this.moveValid(move);
			
			if(!moveValid ) {
				System.out.println("Invalid Move Try Once More");
				continue;			
			}	
		}
		else {
			symbol = this.computerSymbol;
			move = this.computerMove();
			boolean moveValid = this.moveValid(move);
			
			if(!moveValid ) {
				System.out.println("Invalid Move Try Once More");
				continue;
			}
		}
		this.board[(int)(move / 3)][move % 3] = symbol;
		System.out.println(2);
		this.displayBoard();
		
		String resultWinner=this.checkWinner();
		
		if(resultWinner != null ) {
			System.out.println(resultWinner);
			if(resultWinner.charAt(0) == this.computerSymbol) {
				System.out.println("The winner is Computer");
				
			}
			else if(resultWinner.charAt(0) == this.userSymbol) {
				System.out.println("The winner is Player");
				
			}
			else {
				System.out.println("The Game is Draw");
			}
			
			this.displayBoard();
			return;
		}
		
		if(turn == 0) {
			turn = 1;
		}
		else {
			turn = 0;
		}
		
	}
	}
	
	public int computerMove() {
		Random rand = new Random();
		int move = rand.nextInt(9);
		return move;
	}
		
	
	boolean equals3(char c1, char c2, char c3) {
		  return (c1 == c2 && c2 == c3 && c1 != ' ');
	}
	
	public String checkWinner() {
	  String winner = null;
	  // horizontal
	  for (int i = 0; i < 3; i++) {
	    if (this.equals3(board[i][0], board[i][1], board[i][2])) {
	      winner = Character.toString(board[i][0]);
	    }
	  }

	  // Vertical
	  for (int i = 0; i < 3; i++) {
	    if (equals3(board[0][i], board[1][i], board[2][i])) {
	      winner = Character.toString(board[0][i]);
	    }
	  }

	  // Diagonal
	  if (equals3(board[0][0], board[1][1], board[2][2])) {
	    winner = Character.toString(board[0][0]);
	  }
	  if (equals3(board[2][0], board[1][1], board[0][2])) {
	    winner = Character.toString(board[2][0]);
	  }
	  
	  
	  if (this.staleMate() == true && winner == null  ) {
		  return "tie";
	  } else {
	    return winner;
	  }
	}
	//Checking for staleMate
	public boolean staleMate() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if (this.board[i][j] != ' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	public void displayBoard() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print("|");
				System.out.print(this.board[i][j]+" ");	
			}
			System.out.println();
			System.out.println("---------");
			}	
		}
	
	public boolean moveValid(int move) {
		if(this.board[(int)(move / 3)][move % 3] == ' ') {
			return true;
		}
		return false;
			
		}
}

