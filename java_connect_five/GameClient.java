package ConnectFive;

import java.io.*;
import java.net.*;

public class GameClient {
	public static void main(String[] args) throws IOException {

		// declare local vars
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser;

		try (Socket socket = new Socket("localhost", 80);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

			if (socket.isConnected()) {
				System.out.println("Connected to Server \nWaiting for another player!");
			}

			/*
			 * using the replaceAll() method to correctly display the board sent back from
			 * the server by the displayPlayerBoard() method, since "\n" will end the
			 * currently processing line in a BufferedReader
			 */
			while ((fromServer = in.readLine()) != null) {
				fromServer = fromServer.replaceAll("---", "\n");
				System.out.println(fromServer);
				fromUser = stdIn.readLine();

				if (fromUser != null) {
					out.println(fromUser);
				}
			}

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host localhost");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to localhost");
			System.exit(1);
		}
	}
}