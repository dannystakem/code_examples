
// importing classes from the Java API
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTwo {

	public static void main(String[] args) throws IOException {

		// declaring String variables
		String request;
		String response;

		/*
		 * creating a Socket to connect to the Server with the 'localhost' and
		 * the Server port number as arguments
		 */
		Socket clientTwo = new Socket("localhost", Server.PORT);

		/*
		 * creating input and output streams to pass information to the Server
		 * and receive information back from the Server
		 */
		InputStream input = clientTwo.getInputStream();
		OutputStream output = clientTwo.getOutputStream();

		// creating a Scanner object to pass information to the input stream
		Scanner in = new Scanner(input);

		// creating a PrintWriter object to pass information to the output stream
		PrintWriter out = new PrintWriter(output);

		// string that will be passed to the Server
		//request = "PUSH 325\n" + "PUSH 475\n" + "PUSH 550\n" + "PUSH 5\n" + "ADD\n" + "QUIT\n";

		// string with deliberate errors to test the error checking measures
		request = "PLUSH 10\n" + "PUSH\n" + "PUSH ADD\n" + "ADD\n" + "MULT\n" + "QUIT\n";

		/*
		 * sending the string to the server on the output stream, then flushing
		 * out the stream
		 */
		out.print(request);
		out.flush();

		/*
		 * checking that the string received back from the server is not empty,
		 * and printing the result to the console
		 */
		while (in.hasNext()) {
			response = in.nextLine();
			System.out.println(response);
		}

		// closing the different streams
		in.close();
		clientTwo.close();
		input.close();
		output.close();
	}
}
