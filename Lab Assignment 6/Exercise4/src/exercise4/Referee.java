package exercise4;

import java.io.IOException;

/**
 * @author aidan
 * @version final
 * class Referee sets the opponents the players and decides whose turn it is
 * 
 */

public class Referee {
	/**
	 * creates xPlayer and oPlayer of type Player
	 * creates board var of type Board
	 */
	private Player xPlayer;
	private Player oPlayer;
	private Board board;
	
	/**
	 * starts the game by setting each player as the opponents for the other and then displays a blank board
	 * @throws IOException
	 */
	public void runTheGame() throws IOException {
		oPlayer.setOpponent(xPlayer);
		xPlayer.setOpponent(oPlayer);
		
		board.display();
		
		
		int i = 1;
		while(true) {
			if(i%2 == 1) {
				System.out.println(xPlayer + "'s turn");
				xPlayer.play();
			}
			else {
				System.out.println(oPlayer + "'s turn");
				oPlayer.play();
			}
			
			i++;
			
			if(board.oWins() || board.xWins() || board.isFull()) {
				xPlayer.play();
				break;
			}
			
		}
	}

	/**
	 * sets player x
	 * @param xPlayer
	 */
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}

	/**
	 * sets player o
	 * @param oPlayer
	 */
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}

	/**
	 * sets the board
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	
	
	
}
