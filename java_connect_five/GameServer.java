package ConnectFive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class GameServer extends Thread {

	// server vars
	private ServerSocket ss;
	private Socket socket1, socket2;
	private PrintWriter toPlayer1, toPlayer2;
	private BufferedReader fromPlayer1, fromPlayer2;

	// game vars
	private String[][] board = new String[6][19];
	private String player1;
	private String player2;
	private String playerMove;
	private int choice;
	static int drawCount = 0;

	public GameServer() {
		try {
			ss = new ServerSocket(80);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Server running. Waiting for two players to connect...\n");

		Thread t1 = new GameServer();
		t1.start();
	}

	public void run() {
		try {
			socket1 = ss.accept();
			System.out.println("Player 1 connected, waiting for second player");
			socket2 = ss.accept();
			System.out.println("Player 2 connected");

			toPlayer1 = new PrintWriter(socket1.getOutputStream(), true);
			toPlayer2 = new PrintWriter(socket2.getOutputStream(), true);
			fromPlayer1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
			fromPlayer2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));

			toPlayer1.println("Welcome to 5 in a Row! Please enter your name: ");
			player1 = fromPlayer1.readLine();
			toPlayer2.println("Welcome to 5 in a Row! Please enter your name: ");
			player2 = fromPlayer2.readLine();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(" *** An unrecoverable exception has occured ***");
			System.exit(0);
		}

		board = createBoard();
		boolean loop = true;
		int count = 0;

		try {
			while (!socket1.isClosed() || !socket2.isClosed()) {

				while (loop) {
					if (count % 2 == 0)
						playerMove(board, player1, fromPlayer1, toPlayer1, "X");
					else
						playerMove(board, player2, fromPlayer2, toPlayer2, "O");

					count++;
					String winner = checkWinner(board);

					if (winner != null) {
						if (winner == "X") {
							displayPlayerBoard(board, toPlayer1);
							toPlayer1.println("---" + player1 + " has Won. Game over!");
							displayPlayerBoard(board, toPlayer2);
							toPlayer2.println("---" + player1 + " has Won. Game over!");
							System.out.println("\n" + player1 + " has Won. Game over!");
							closeSockets();
						} else {
							displayPlayerBoard(board, toPlayer1);
							toPlayer1.println("---" + player2 + " has Won. Game over!");
							displayPlayerBoard(board, toPlayer2);
							toPlayer2.println("---" + player2 + " has Won. Game over!");
							System.out.println("\n" + player2 + " has Won. Game over!");
							closeSockets();
						}
						loop = false;
					}

					// check for a draw
					if (drawCount >= 54) {
						toPlayer1.println("Phew, good game " + player1 + ". Game is a draw. Game over!");
						toPlayer2.println("Phew, good game " + player2 + ". Game is a draw. Game over!");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" *** An unrecoverable exception has occured ***");
			System.exit(0);
		}
	}

	private void closeSockets() {

		try {
			socket1.close();
			socket2.close();
			ss.close();
			fromPlayer1.close();
			fromPlayer2.close();
			toPlayer1.close();
			toPlayer2.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void playerMove(String[][] board, String player, BufferedReader fromPlayer, PrintWriter toPlayer,
			String playerMarker) {

		displayPlayerBoard(board, toPlayer1);
		displayPlayerBoard(board, toPlayer2);
		toPlayer.println("It's your turn " + player + " - Drop a disk in a column (0–8): ");

		try {
			playerMove = fromPlayer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(" *** An unrecoverable exception has occured ***");
			System.exit(0);
		}

		// Check that the player has entered a digit 1-8, if not, ask for input again
		while (true) {
			if (Pattern.matches("[0-8]", playerMove)) {
				choice = Integer.parseInt(playerMove);
				break;
			}

			toPlayer.println("Error! You entered (" + playerMove + ") - Please enter a digit between 0-8: ");

			try {
				playerMove = fromPlayer.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(" *** An unrecoverable exception has occured ***");
				System.exit(0);
			}
		}

		// check that a column isn't already full
		choice = 2 * Integer.parseInt(playerMove) + 1;
		for (int i = 5;; i--) {

			if (i < 0) {
				toPlayer.print("---Move not possible - column is full ---Please select a different column---");
				break;
			}

			if (board[i][choice] == " ") {
				board[i][choice] = playerMarker;
				break;
			}

		}
		drawCount++;
	}

	private String[][] createBoard() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (j == 0) {
					board[i][j] = "[";
				} else if (j == 18) {
					board[i][j] = "]";
				} else if (j % 2 == 0) {
					board[i][j] = "][";
				} else {
					board[i][j] = " ";
				}
			}
		}
		return board;
	}

	private void displayPlayerBoard(String[][] board, PrintWriter toPlayer) {
		toPlayer.print("---Current Board State------");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				toPlayer.print(board[i][j]);
			}
			toPlayer.print("---");
		}
	}

	private String checkWinner(String[][] cell) {

		// check horizontal - check if player has five markers in a row
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 9; j += 2) {
				if ((cell[i][j + 1] != " ") && (cell[i][j + 3] != " ") && (cell[i][j + 5] != " ")
						&& (cell[i][j + 7] != " " && cell[i][j + 9] != " ")
						&& ((cell[i][j + 1] == cell[i][j + 3]) && (cell[i][j + 3] == cell[i][j + 5])
								&& (cell[i][j + 5] == cell[i][j + 7]) && (cell[i][j + 7] == cell[i][j + 9])))
					return cell[i][j + 1];
			}
		}

		// check vertical - check if player has five markers in a row
		for (int i = 1; i < 19; i += 2) {
			for (int j = 0; j < 2; j++) {
				if ((cell[j][i] != " ") && (cell[j + 1][i] != " ") && (cell[j + 2][i] != " ")
						&& (cell[j + 3][i] != " ") & (cell[j + 4][i] != " ")
						&& ((cell[j][i] == cell[j + 1][i]) && (cell[j + 1][i] == cell[j + 2][i])
								&& (cell[j + 2][i] == cell[j + 3][i]) && (cell[j + 3][i] == cell[j + 4][i])))
					return cell[j][i];
			}
		}

		// check \ diagonal - check if player has five markers in a row
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < 11; j += 2) {
				if ((cell[i][j] != " ") && (cell[i + 1][j + 2] != " ") && (cell[i + 2][j + 4] != " ")
						&& (cell[i + 3][j + 6] != " ") && (cell[i + 4][j + 8] != " ")
						&& ((cell[i][j] == cell[i + 1][j + 2]) && (cell[i + 1][j + 2] == cell[i + 2][j + 4])
								&& (cell[i + 2][j + 4] == cell[i + 3][j + 6])
								&& (cell[i + 3][j + 6] == cell[i + 4][j + 8])))
					return cell[i][j];
			}
		}

		// check / diagonal - check if player has five markers in a row
		for (int i = 0; i < 2; i++) {
			for (int j = 9; j < 19; j += 2) {
				if ((cell[i][j] != " ") && (cell[i + 1][j - 2] != " ") && (cell[i + 2][j - 4] != " ")
						&& (cell[i + 3][j - 6] != " ") && (cell[i + 4][j - 8] != " ")
						&& ((cell[i][j] == cell[i + 1][j - 2]) && (cell[i + 1][j - 2] == cell[i + 2][j - 4])
								&& (cell[i + 2][j - 4] == cell[i + 3][j - 6])
								&& (cell[i + 3][j - 6] == cell[i + 4][j - 8])))
					return cell[i][j];
			}
		}
		return null;
	}
}
