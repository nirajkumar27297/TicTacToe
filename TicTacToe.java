
import java.util.*;
public class TicTacToe {
	private char[][] board;
	private char userSymbol;
	private char computerSymbol;
	private Map<String,Integer> scoreMap = new HashMap<String,Integer>();
	
	TicTacToe() {
		board = new char[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.board[i][j] = ' ';
			}
		}
		this.scoreMap.put("X", 10);
		this.scoreMap.put("O",-10);
		this.scoreMap.put("tie",0);		
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
		while(true) {
			if(turn == 1) {
				System.out.println("Enter the move in range[1-9]");
				move = sc.nextInt();
				move -= 1;
				boolean moveValid = moveValid(move);
				
				if(!moveValid ) {
					System.out.println("Invalid Move Try Once More");
					continue;			
				}
				this.board[(int)(move / 3)][move % 3] = this.userSymbol;
			}
			else {
				computerMove();
			}
			
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
	
	public void findBestMove() {
		int bestScore = Integer.MIN_VALUE;
		int score;
		int bestXPosition = 0;
		int bestYPosition = 0;
				
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3;j++) {
				if(this.board[i][j] == ' ') {
					this.board[i][j] = this.computerSymbol;
					score = this.minimax(0,false);
					this.board[i][j] = ' ';
					if( score > bestScore ) {
						bestXPosition = i;
						bestYPosition= j;
						bestScore = score;		
					}	
				}
			}
		}
		this.board[bestXPosition][bestYPosition] = this.computerSymbol;
	}
	
	public int minimax(int depth, boolean isMaximising) {
		String resultWinner=this.checkWinner();
		if (resultWinner != null )  {
			return this.scoreMap.get(resultWinner);
	}
		int score;
		
		if(isMaximising) {
			int bestScore = Integer.MIN_VALUE;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3;j++) {
					if(this.board[i][j] == ' ') {
						this.board[i][j] = this.computerSymbol;
						score = this.minimax(depth + 1,false);
						this.board[i][j] = ' ';
						bestScore = Math.max(score,bestScore);	
					}
				}
			}
			return bestScore;	
		}
		else {
			int bestScore = Integer.MAX_VALUE;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3;j++) {
					if(this.board[i][j] == ' ') {
						this.board[i][j] = this.userSymbol;
						score = this.minimax(depth + 1,true);
						this.board[i][j] = ' ';
						bestScore = Math.min(score,bestScore);
					}
				}
			}
			return bestScore;
		}
	}
	
	public void computerMove() {
		//this.findBestMove();
		//Corner Cases
		for(int i=0;i<9;i+=2) {
			boolean validSides=moveValid(i);
			if(i == 4) {
				continue;
			}
			if (validSides) {
				this.board[(int)(i / 3)][i % 3] = this.computerSymbol;
				return;
				 }
			}
		//Center
		boolean centerValid = moveValid(4);
		if(centerValid) {
			this.board[(int)(4 / 3)][4 % 3] = this.computerSymbol;
			return;	
		}
		
		for(int i=1;i<9;i+=2) {
			boolean validSides=moveValid(i);
				if (validSides) {
					this.board[(int)(i / 3)][i % 3] = this.computerSymbol;
					return;
			}
			}
	}
	
	public boolean moveValid(int move) {
		if(this.board[(int)(move / 3)][move % 3] == ' ') {
			return true;
		}
		return false;	
		}
	
	public void displayBoard() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print("|");
				System.out.print(this.board[i][j]+" ");
				
			}
			System.out.println();
			System.out.println("---------");
		}
	}
	boolean equals3(char a, char b, char c) {
		  return (a == b && b == c && a != ' ');
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
}
