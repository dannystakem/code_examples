
// importing classes from the Java API
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Stack;
import java.util.Scanner;

/*
 *  class that implements the Runnable interface for using threads, and 
 *  therefore is required to provide an implementation for the run() method
 */
public class Server implements Runnable {

	/*
	 * declaring three private variables form the different API classes, one for
	 * a client Socket, one for Scanner input and one PrintWriter output
	 */
	private Socket sock;
	private Scanner input;
	private PrintWriter output;

	// declaring a static final int for port number
	public static final int PORT = 8888;

	// constructor that takes in the Socket variable as an argument
	public Server(Socket sock) {
		this.sock = sock;
	}

	// creating a stack with parameters of type Integer
	static Stack<Integer> lifo = new Stack<Integer>();
	
	public static void main(String[] args) throws IOException {

		// creating a new Server Socket using the PORT variable
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("Server is running and is waiting for a Client to connect!");

		while (true) {
			
			// let an available Client connect to the Server
			Socket sock = server.accept();
			System.out.println("Client Connected");

			/*
			 * create a new Thread object, with a Server object as a parameter
			 * and call the start() method to run the thread
			 */
			new Thread(new Server(sock)).start();
		}
	}

	// implementing the run() method from the Interface Runnable
	public void run() {

		// allowing only one thread to enter the critical section
		synchronized (this) {
			try {
				try {
					input = new Scanner(sock.getInputStream());
					output = new PrintWriter(sock.getOutputStream());

					handleRequest(); // calling the method

					/*
					 * enter this block when the method above has finished, the
					 * socket is closed when the Client has finished with the
					 * Server
					 */
				} finally {
					sock.close();
				}
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

	// method to handle the information entered by the Client
	public void handleRequest() throws IOException {

		// if there is no more input from the input stream the while loop is exited
		while (true) {
			if (!input.hasNext())
				return;

			// user input from the input stream is assigned to a string
			String request = input.next();

			/*
			 * using if/else statements to confirm what what entered, and that
			 * everything was entered correctly, if 'PUSH' is entered, then the
			 * number following the command is added to the stack, if 'ADD' is
			 * entered the two numbers at the top of the stack are removed and
			 * added together, if 'MULT' is entered the same happens but the two
			 * numbers are multiplied instead, and if 'QUIT' is entered, then
			 * the connection ends
			 */
			if (request.equals("PUSH")) {
				try {

					/*
					 * assigning the number from the 'PUSH' command to an int
					 * after the valueOf() method changes the String to an
					 * Integer
					 */
					String value = input.next();
					int number = Integer.valueOf(value);
					System.out.println("Received from Client: " + request + " " + number);

					/*
					 * if statement that pushes the number onto the stack and
					 * outputs a string to the Client's console with the output
					 * stream
					 */
					if (request.equals("PUSH")) {
						lifo.push(number);
						output.println("OK");// Clients Response
						System.out.println("Contents of Stack: " + lifo);
					}
				}

				// if a Client doesn't enter a number, catch the error
				catch (NumberFormatException e) {
					output.println("ERROR");
				}
				output.flush();
				pause();

			} else if (request.equals("ADD")) {
				System.out.println("Received from Client: " + request);

				// checking that the stack contains more than two numbers
				if (request.equals("ADD") && lifo.size() > 1) {

					/*
					 * using the pop() method to remove the two top numbers on
					 * the stack the stack contents before and after the
					 * operation are printed to the Server console while the
					 * result is printed to the Client console
					 */
					System.out.println("Stack contents before ADD: " + lifo);
					int add = lifo.pop() + lifo.pop();

					System.out.println("Stack contents after ADD: " + lifo);
					output.println("RESULT of ADD: " + add);

				/*
				 * If the stack has less than two elements, display an error message 
				 * in the Client console
				 */
				} else {
					output.println("TOO_FEW_ELEMENTS");
				}
				output.flush();
				pause();

			} else if (request.equals("MULT")) {
				System.out.println("Received from Client: " + request);

				// checking that the stack contains more than two numbers
				if (request.equals("MULT") && lifo.size() > 1) {

					// popping the top two numbers off the stack and multiplying them
					System.out.println("Stack contents before MULT: " + lifo);
					int mult = lifo.pop() * lifo.pop();

					System.out.println("Stack contents after MULT: " + lifo);
					output.println("RESULT of MULT: " + mult);

				/* 
				 * If the stack has less than two elements display an error message 
				 * in the Client console
				 */
				} else {
					output.println("TOO_FEW_ELEMENTS");
				}

				/*
				 * flushing the output stream and calling the pause() method to
				 * put the running thread to sleep to allow another thread to
				 * enter the runnable state
				 */
				output.flush();
				pause();
			}

			// close the connection
			else if (request.equals("QUIT")) {
				System.out.println("Received from client: " + request);
				System.out.println("Client disconnected!");
				System.exit(0);
			
			} else {
				System.err.println("Client request was invalid!");
			output.flush();
			}
		}
	}

	/*
	 * creating a method that will put the currently running thread to sleep for
	 * one second, so that another thread can enter the runnable state, the code
	 * is placed in a try/catch block to catch any errors
	 */
	private void pause() {
		try {
			Thread.sleep(Math.round(1000.0));

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
