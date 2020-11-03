import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;   

public class Server {
	
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	
	public Server() {
		try {
			serverSocket = new ServerSocket (8099);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void palindrome () {
		String line = "";
		String rev = "";
		while (true) {
			try {
				line = socketIn.readLine();
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
			} //reading from the client		
		}	
	}
	
	public static void main (String [] args) throws IOException{
		
		Server myServer = new Server ();
		
		//Establishing the connection 
		try {
			myServer.aSocket = myServer.serverSocket.accept();
			System.out.println("Console at Server side says: Connection accepted by the server!");
			myServer.socketIn = new BufferedReader (new InputStreamReader(myServer.aSocket.getInputStream()));
			myServer.socketOut = new PrintWriter (myServer.aSocket.getOutputStream(), true);
			myServer.palindrome();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}


}
