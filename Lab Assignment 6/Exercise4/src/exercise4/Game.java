package exercise4;

import java.io.*;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 


/**
 * @author aidan
 * @version final
 * class game sets up the game mechanics for creating the referee and is the class that runs the program
 */
public class Game implements Runnable{ //had to remove implements constants in order to implement runnable
	/**
	 * creates variables theBoard of type board
	 * creates theRef of type Referee
	 */
	
	final char SPACE_CHAR = ' ';
	final static char LETTER_O = 'O';
	final static char LETTER_X = 'X';
	private Board theBoard;
	private Referee theRef;
	
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	/**
	 * creates a new board object in constructor
	 */
	public Game( ) {
      theBoard  = new Board();
	}
	
	public Game (PrintWriter socketOut, BufferedReader socketIn) {
		
		this.socketIn = socketIn;
		this.socketOut = socketOut;
		
	}
	/**
	* referee starts the game
	* @param r
	* @throws IOException
	*/
	public void appointReferee(Referee r) throws IOException {
	    theRef = r;
	  	theRef.runTheGame();
    }
	/**
	 * runs the program
	 * takes user input for the players names and where they want to place their marks on the board
	 * @param args
	 * @throws IOException
	 */
	public void runGame() throws IOException { //this will be called in run 
		Referee theRef;
	
		BufferedReader stdin;
		Game theGame = new Game();
		
		while (true) {
			try {
				
				System.out.print("\nPlease enter the name of the \'X\' player: ");
				String name= socketIn.readLine();
				while (name == null) {
					System.out.print("Please try again: ");
					name = socketIn.readLine();
				}

				Player xPlayer = new Player(name, LETTER_X);
				xPlayer.setBoard(theGame.theBoard);
				
				System.out.print("\nPlease enter the name of the \'O\' player: ");
				name = socketIn.readLine();
				while (name == null) {
					System.out.print("Please try again: ");
					name = socketIn.readLine();
				}
				
				Player oPlayer = new Player(name, LETTER_O);
				oPlayer.setBoard(theGame.theBoard);
				
				theRef = new Referee();
				theRef.setBoard(theGame.theBoard);
				theRef.setoPlayer(oPlayer);
				theRef.setxPlayer(xPlayer);
		      
				theGame.appointReferee(theRef);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			runGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}	

