package exercise2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class Client2 {
	
	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;
	
	
	/**
	 * creates a client with the name if the server and a port number for the server
	 * @param serverName
	 * @param portNumber
	 */
	public Client2(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
					palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	/**
	 * communicate sends information to the server, prompts the user to make a choice
	 * DATE
	 * TIME
	 * or QUIT
	 */
	public void communicate()  {

		String line = "";
		String response = "";
		boolean running = true;
		while (running) {
			try {
				System.out.println("please select an option (DATE/TIME): ");
				line = stdIn.readLine();
				if (!line.equals("QUIT")){
					response = socketIn.readLine();
					System.out.println(response);	
				}else{
					running = false;
				}
				
			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}

	}
	/**
	 * creates a client object, calls communicate()
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException  {
		Client2 aClient = new Client2("localhost", 9090);
		aClient.communicate();
	}
}

