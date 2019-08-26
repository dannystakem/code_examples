
// importing classes from the Java API for later use
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class Tokenizer {

	/*
	 * declaring three different static arraylists with generics to specify what
	 * type of element each arraylist can contain, that of Long, Double and
	 * String
	 */
	private static ArrayList<Long> integers = new ArrayList<Long>();
	private static ArrayList<Double> realNumbers = new ArrayList<Double>();
	private static ArrayList<String> otherTokens = new ArrayList<String>();

	// declaring a String array for the column headers
	String tableHeaders[] = { "Integral Numbers", "Real Numbers", "Other Tokens" };

	// main method throws IOExceptions and will simply terminate if one occurs
	public static void main(String[] args) throws IOException {

		/*
		 * calling the method and passing in the full file path name of the text
		 * file we wish to tokenize
		 */
		tokenizeInput("C:\\Users\\Daniel\\Desktop\\JavaAssignSix\\AssignmentSixInput.txt");

		/*
		 * printing each of the arraylists to the console window and using the
		 * replace() method to get rid of any unwanted elements, such as the
		 * arraylist brackets
		 */
		System.out.println("\n===================================================================================");
		System.out.println("\nItems in Category 1 (integers): " + integers.toString().replace("[", "").replace("]", ""));
		System.out.println("\nItems in Category 2 (real numbers): " + realNumbers.toString().replace("[", "").replace("]", ""));
		System.out.println("\nItems in Category 3 (other tokens): " + otherTokens.toString().replace(", ", "\", \"").replace("[", "\"").replace("]", "\""));
		System.out.println("\n===================================================================================");

		/*
		 * creating an object of the Tokenizer class and using it as a reference
		 * to call the CreateTable() method
		 */
		Tokenizer token = new Tokenizer();
		token.createTable();
	}

	// method throws back IOExceptions to the method which called it
	public static void tokenizeInput(String file) throws IOException {

		Scanner scan = null; // declare a Scanner

		try {

			/*
			 * assigning the contents of the passed in text file to the Scanner
			 * reference, the file is read by the FileReader, which is wrapped
			 * in a BufferedReader, which is also wrapped in a Scanner
			 */
			scan = new Scanner(new BufferedReader(
					new FileReader("C:\\Users\\Daniel\\Desktop\\JavaAssignSix\\AssignmentSixInput.txt")));

			// check if scan still contains information
			while (scan.hasNext()) {

				/*
				 * if the next token is a long then it is put in the integers
				 * arraylist, if it's a double it's put in the realNumbers
				 * arraylist, and if it's a String it's placed in the otherTokens
				 * arraylist, it should be noted that with Scanner the default
				 * delimiters are the whitespace characters
				 */
				if (scan.hasNextLong()) {
					integers.add(scan.nextLong());
				} else if (scan.hasNextDouble()) {
					realNumbers.add(scan.nextDouble());
				} else {
					otherTokens.add(scan.next());
				}
			}

		// check that the scanner has been used and is not set to its default value
		} finally {
			if (scan != null)
				scan.close();
		}
	}

	// method to create a new table
	public void createTable() {

		/*
		 * creating a JFrame object to create a GUI frame, components will be
		 * added to the JFrame through the use of a content pane, the
		 * setDefaultCloseOperation() method will let a user click the close
		 * icon to close the window
		 */
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * creating a JTable by passing the AbstractTableModel object to it's
		 * constructor, then setting the color of the grid lines within the
		 * table
		 */
		JTable table = new JTable(myTable);
		table.setGridColor(Color.LIGHT_GRAY);

		/*
		 * passing in the JTable reference to make the table scrollable, and
		 * adding it to the frame using a border layout manager
		 */
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);

		frame.pack(); 			// sizing the frame preferably, relative to its contents
		frame.setLocationRelativeTo(null); 	// open the frame in the center of the screen
		frame.setVisible(true);		// make the frame visible, called at the end

	}

	// using an anonymous class to define table content
	TableModel myTable = new AbstractTableModel() {

		/*
		 * providing a serialVersionUID so that when deserialization occurs,
		 * this number can be used to check that a loaded class corresponds
		 * exactly to a serialized object. This will prevent an
		 * InvalidClassException being thrown if no match is found.
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Object getValueAt(int row, int column) {

			// printing the different arraylists into the different columns
			if (column == 0 && row < integers.size()) {
				return integers.get(row);
			} else if (column == 1 && row < realNumbers.size()) {
				return realNumbers.get(row);
			} else if (column == 2 && row < otherTokens.size()) {
				return otherTokens.get(row);
			}

			// exit the method when the end of each arraylist is met
			return null;
		}

		/*
		 * overriding the abstract methods of the AbstractTableModel swing
		 * class, which includes the getValueAt() method above, getColumnCount()
		 * defines the number of table columns, while getRowCount() defines the
		 * number of table rows. The getColumnName will print out the String
		 * header for each column stored in the tableHeaders arraylist
		 */
		@Override
		public int getColumnCount() {
			return tableHeaders.length;
		}

		@Override
		public int getRowCount() {
			return 30;
		}

		public String getColumnName(int index) {
			return tableHeaders[index];
		}
	};
}