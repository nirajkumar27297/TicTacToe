
import java.util.*;
public class TicTacToe {
	private char[][] board;
	private char userSymbol;
	private char computerSymbol;
	private ArrayList<Integer> playerPosition;
	private ArrayList<Integer> computerPosition;
	TicTacToe() {
		board = new char[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.board[i][j] = ' ';
			}
		}
		playerPosition = new ArrayList<Integer>();
		computerPosition = new ArrayList<Integer>();
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
			while(true) {
				System.out.println("Player Chance to Play");
				System.out.println("Choose Either X or O");
				//Taking input from the user
				input = sc.next().charAt(0);
				if(input != 'X') {
					System.out.println("Enter Right Value");
					continue;
				}
				this.userSymbol = input;
				System.out.println("You chose "+this.userSymbol);
				
				if(input == 'X') {
					
					this.computerSymbol = 'O';
				}
				else {
					this.computerSymbol = 'X';
				}
				break;
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
		//start playing
		this.startPlaying(checkOption);
	}
	//Game started with turn
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
				this.playerPosition.add(move);
				this.board[(int)(move / 3)][move % 3] = this.userSymbol;
			}
			else {
				System.out.println("Computer Turn");
				computerMove();
			}
			
			this.displayBoard();
			
			String resultWinner=this.checkWinner();
			boolean dre = this.draw();
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
	public int checkComputerWinning(ArrayList<Integer> position) {
		//Storing all the wining Positions
		int[][] winningPosition = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
                { 2, 4, 6 } };
		int k = 0;
		ArrayList<Integer> unMatchedPosition = new ArrayList<Integer>();
		while ( k < 8) {
			int countMatch=0;
			unMatchedPosition.clear();
			
			for(int i = 0; i < winningPosition[k].length; i++) {
				
				// if position array list contains winning positing incrementing by 1
				if (position.contains(winningPosition[k][i])) {
					countMatch++;
				}
				else {
					unMatchedPosition.add(winningPosition[k][i]);
				}
			}
			if( countMatch == 2 && unMatchedPosition.size() == 1) {
				return unMatchedPosition.get(0);
			}
			k++;
		}
		return -1;
	}
	
	public void computerMove() {
		
		//Winning Position
		int move = this.checkComputerWinning(this.computerPosition);
		if( move != -1 && this.moveValid(move)) {
			this.board[(int)(move / 3)][move % 3] = this.computerSymbol;
			this.computerPosition.add(move);
			return;
		}
		//Blocking Position
		move = this.checkComputerWinning(this.playerPosition);
		if( move != -1 && this.moveValid(move)) {
			this.board[(int)(move / 3)][move % 3] = this.computerSymbol;
			this.computerPosition.add(move);
			return ;
		}	
		//Random Sides
		while(true) {
			Random rand = new Random();
			move = rand.nextInt(9);
			if(this.moveValid(move)) {
				this.board[(int)(move / 3)][move % 3] = this.computerSymbol;
				this.computerPosition.add(move);
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
	  
	  
	  if (this.draw() == true && winner == null  ) {
		  return "tie";
	  } else {
	    return winner;
	  }
	}
	
	//Checking for draw
	public boolean draw() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if (this.board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}
