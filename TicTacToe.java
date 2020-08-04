
public class TicTacToe {
	private char[][] board = new char[3][3];
	
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
	}

}
