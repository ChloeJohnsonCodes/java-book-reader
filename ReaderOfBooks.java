import javax.swing.JFrame;

/**
 * Project 4: A GUI that creates a library from a file that is loaded in the
 * program. Books can be selected from the library and read by the user.
 * 
 * @author chloejohnson
 *
 */
public class ReaderOfBooks {
	/**
	 * Runs the main code for the program. Also creates the GUI frame.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Library");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ReaderOfBooksPanel bookPanel = new ReaderOfBooksPanel();
		frame.getContentPane().add(bookPanel);

		frame.pack();
		frame.setVisible(true);

	}
}