import io.github.pixee.security.BoundedLineReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//import exercise4.Server;   

public class Server {
	
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	/**
	 * creates a server with a port number
	 */
	public Server() {
		try {
			serverSocket = new ServerSocket (5646);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * checks if the input is a palindrome or not
	 */
	public void palindrome () {
		String line = "";
		String rev = "";
		while (true) {
			try {
				line = BoundedLineReader.readLine(socketIn, 5_000_000);
				if (line.equals(null)) {
					line = "Please enter another word: !";
					socketOut.println(line);
					break;
				} else {
					  int length = line.length();   
				      for ( int i = length - 1; i >= 0; i-- )  
				         rev += line.charAt(i);  
				      if (line.equals(rev))  
				         socketOut.println(line + " is a palindrome.");  
				      else  
				         socketOut.println(line + " isn't a palindrome.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}	
	}
	/**
	 * main runs the server, connects with the client, receives words, checks palindrome and then sends back a response on 
	 * Whether or not it was a palindorme
	 * @param args
	 * @throws IOException
	 */
	public static void main (String [] args) throws IOException{
		
		Server myServer = new Server();
		System.out.println("server is running....");
		//Establishing the connection 
		try {
			myServer.aSocket = myServer.serverSocket.accept();
			System.out.println("Console at Server side says: Connection accepted by the server!");
			myServer.socketIn = new BufferedReader (new InputStreamReader(myServer.aSocket.getInputStream()));
			myServer.socketOut = new PrintWriter (myServer.aSocket.getOutputStream(), true);
			myServer.palindrome();

			myServer.socketIn.close();
			myServer.socketOut.close();
			myServer.aSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}


}
