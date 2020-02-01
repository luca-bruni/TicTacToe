/**
 * Luca Bruni
 * 13 July 2017
 * This program allows the user to
 * play tictactoe with a non-AI computer by
 * selecting coordinates within a 3x3 array and
 * changing the stored values in the array. The game
 * ends when user/computer wins or cat's game, and the
 * final board is displayed.
 */

import javax.swing.*;
public class TicTacToeBruL {

	public static void main(String[] args) {
		//Tic tac toe board stored in 2D array
		char[][] board = new char[3][3];
		//Player's letter value
		char letter = '-';
		//Location on board
		int[] location;
		//Computer's letter value
		char compLetter = '-';
		//Checks if player turn
		boolean turn = false;
		
		introduceProgram();
		initBoard(board);
		
		try {
			letter = getPlayerLetter();
			compLetter = computerLetter(letter);
			turn = determineFirstTurn(letter);
			
			do {
				try {
					if(turn) {
						location = getLocation(board, letter);
					} else {
						location = generateCompLocation(board);
					}
				}
				catch(Exception e) {
					break;
				}
				
				if(checkOccupation(board, location) == true){
					if(turn) {
						placeLetter(board, location, letter);
						turn = false;
					}else {
						placeLetter(board, location, compLetter);
						turn = true;
					}
				}
				
			}while(isWin(board, letter, compLetter) == "Play again next time");
			displayMessage(printBoard(board) + isWin(board, letter, compLetter));
		}
		catch(Exception e) {
			
		}
	}
	
	/**
	 * Checks who wins or ifcat's game
	 * @param board the tic tac toe board
	 * @param letter the letter in an array location
	 * @param compLetter the computer letter in an array location
	 * @return statement if player or computer won, or cat's game
	 */
	public static String isWin (char[][] board, char letter, char compLetter) {
		int counter = 0;
		for(int i = 0; i < board.length; i++) {
			if (board[i][0] == letter && board[i][1] == letter && board[i][2] == letter) {
				return "You WIN!";
			}
			if (board[0][i] == letter && board[1][i] == letter && board[2][i] == letter) {
				return "You WIN!";
			}
		}
		if (board[0][0] == letter && board[1][1] == letter && board[2][2] == letter) {
			return "You WIN!";
		}
		if (board[0][2] == letter && board[1][1] == letter && board[2][0] == letter) {
			return "You WIN!";
		}
		
		for(int i = 0; i < board.length; i++) {
			if (board[i][0] == compLetter && board[i][1] == compLetter && board[i][2] == compLetter) {
				return "Computer WIN!";
			}
			if (board[0][i] == compLetter && board[1][i] == compLetter && board[2][i] == compLetter) {
				return "Computer WIN!";
			}
		}
		if (board[0][0] == compLetter && board[1][1] == compLetter && board[2][2] == compLetter) {
			return "Computer WIN!";
		}
		if (board[0][2] == compLetter && board[1][1] == compLetter && board[2][0] == compLetter) {
			return "Computer WIN!";
		}
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board.length; col++) {
				if(board[row][col] != '-') {
				counter++;				
				}
			}
		}
		if(counter == 9) {
			return "Cat's Game!";
		}
		return "Play again next time";
		
	}
		
	/**
	 * Checks if a space is occupied
	 * @param board tic tac toe board
	 * @param location the location within the board's array
	 * @return boolean statement to determine whether or not the space is occupied
	 */
	public static boolean checkOccupation(char[][] board, int[] location) {
		if(board[location[0]][location[1]] == '-') {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines the first turn if X is chosen
	 * @param letter the letter the player or computer uses
	 * @return boolean statement to determine whether or not the player has first turn
	 */
	public static boolean determineFirstTurn(char letter) {
		if(letter == 'X') {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines computer letter
	 * @param letter the letter chosen by the player
	 * @return the computer's letter, opposite of the player's
	 */
	public static char computerLetter(char letter) {
		char compLetter = '-';
		if(letter == 'X') {
			compLetter = 'O';
		}
		else if(letter == 'O') {
			compLetter = 'X';
		}
		return compLetter;
	}
	
	/**
	 * Generates a random computer location
	 * @param board tic tac toe board
	 * @return the location of the computer's spot
	 */
	public static int[] generateCompLocation(char[][] board) {
		int[] compLoc = new int[2];
		for(int i = 0; i < compLoc.length; i++) {
			compLoc[i] = (int)(Math.random()*3);
		}
		return compLoc;
	}
	
	/**
	 * Places a letter on the tic tac toe board
	 * @param board tic tac toe board
	 * @param location the location of where the letter should be placed
	 * @param letter the actual letter to be placed
	 */
	public static void placeLetter(char[][] board, int[] location, char letter) {
		board[location[0]][location[1]] = letter;
	}
	
	/**
	 * Gets user input of their letter
	 * @return their input at the first index location (just the letter)
	 */
	@SuppressWarnings("null")
	public static char getPlayerLetter() {
		String input; 
		input = JOptionPane.showInputDialog("Would you like to be "
				+ "X or O?").toUpperCase();
		if(!(input.equals("X") || input.equals("O"))){
			displayMessage("Invalid character");
			return (Character) null;
		}
		return input.charAt(0);
	}
	
	/**
	 * Gets the user's location coordinates
	 * @param board tic tac toe board
	 * @param letter the letter entered by the user
	 * @return
	 */
	public static int[] getLocation (char[][] board, char letter) {
		String[] cords;
		int[] location = new int [2];
		cords = JOptionPane.showInputDialog(printBoard(board) + "\nWhere would you like to pla"
				+ "ce an " + letter + "?\n Format: [row]space[column]\n" + "\nExample: 1 1").split(" ");
		location[0] = Integer.parseInt(cords[0]) - 1;
		location[1] = Integer.parseInt(cords[1]) - 1;
		return location;
	}
	
	/**
	 * Initializes the board with hyphens
	 * @param board tic tac toe board
	 */
	public static void initBoard(char[][] board) {
		for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
            	board[row][col] = '-';
            }
		}
	}
	
	/**
	 * Displays the board to the user
	 * @param board tic tac toe board
	 * @return the board to be displayed later on
	 */
    public static String printBoard(char[][] board) {
    		String line = "   ";
    		String newL = "\n";
    		String wholeBoard = "";
        wholeBoard += newL;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                    wholeBoard += (board[row][col]);              
                if (col < 2) {
                    wholeBoard += line;
                } else{
                    wholeBoard += newL;
                } 
             }
         }
         wholeBoard += newL;
         return wholeBoard;
    }

	/**
	 * Displays a message to user
	 * @param msg is the actual message displayed to the user 
	 */
	public static void displayMessage (String msg) {
		JOptionPane.showMessageDialog(null, msg);	
	}
	
	/**
	 * Displays introduction title and description to user
	 */
	public static void introduceProgram() {
		  displayMessage("TIC TAC TOE\n Welcome! This program runs a"
		  		+ " tic tac toe game, where you can\n face the computer! ");		  	
	}
	
}
