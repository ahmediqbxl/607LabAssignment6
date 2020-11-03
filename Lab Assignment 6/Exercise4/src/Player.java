import java.io.*;

/**
 *Methods and variables for the player class
 * 
 * @author Ahmed Iqbal
 * @version 1.0
 * @since October 1, 2020
 *
 */

public class Player implements Constants{
    String name;
    char mark;
    Board board;
    Player opponent;

	public Player(String name, char letterX) {
        this.name = name;
        this.mark = letterX;
	}

	
    /** 
     * sets board
     * @param theBoard
     */
    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }
    
    
    /** 
     * plays the game - each player makes a move until the game is over
     * @throws IOException
     */
    public void play() throws IOException {
        boolean check = false;
        if (board.xWins()){
            check = true;
            System.out.println("Player X has won!");
        }
        if (board.oWins()){
            check = true;
            System.out.println("Player O has won!");
        }
        if (board.isFull()){
            check = true;
            System.out.println("Board is full! No winner!");
        }

        if (check == false){ //check will be true once someone wins or the board is full
            makeMove();
            board.display();
            board.checkWinner(this.mark);
        }
    }

    
    /** 
     * method for actually making a move 
     * @param (char)stdin.read();if((board.getMark(row
     * @param (board.getMark(row
     * @param LETTER_X))board.addMark(row
     * @param col
     * @param mark
     * @throws IOExceptionBufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));System.out.print("\nPlease enter the row between 0-2: ");int row = Integer.parseInt(stdin.readLine());System.out.print("\nPlease enter the col between 0-2: ");int col = Integer.parseInt(stdin.readLine());System.out.print("\nPlease enter either X or O: ");char mark = (char)stdin.read();if((board.getMark(row
     * @throws col) != LETTER_O) && (board.getMark(row
     * @throws col) != LETTER_X))board.addMark(row
     * @throws col
     * @throws mark);+} else
     */
    public void makeMove() throws IOException { // asks player to make a move by entering the row and column numbers
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nPlease enter the row between 0-2: ");
        int row = Integer.parseInt(stdin.readLine());

        System.out.print("\nPlease enter the col between 0-2: ");
        int col = Integer.parseInt(stdin.readLine());

        System.out.print("\nPlease enter either X or O: ");
        char mark = (char)stdin.read();

        if((board.getMark(row, col) != LETTER_O) && (board.getMark(row, col) != LETTER_X)){ //check if there is already a mark there
            board.addMark(row, col, mark);+
        } else {
            System.out.println("This spot has been taken");
        }
    }

    
    /** 
     * sets opponent
     * @param p
     */
    public void setOpponent (Player p){
        this.opponent = p;
    }
}