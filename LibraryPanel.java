import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")

/**
 * Creates a JPanel called LibraryPanel with several methods.
 * 
 * @author chloejohnson
 *
 */
public class LibraryPanel extends JPanel {

	// Panel for storing the book button panel and the JScrollPane.
	private JPanel bookListPanel;
	// Panel for storing the book buttons.
	private JPanel bookButtonPanel;
	// Panel that stores the load JButton and JTextField.
	private JPanel importBooksPanel;
	private JScrollPane scroller;
	private Library library;
	private JButton load;
	private JTextField txtField;
	private TitledBorder lTitle;
	private ArrayList<Book> books;
	// Size of library
	private int librarySize;
	private BookButton button;
	private ArrayList<BookButton> bookButtonList;
	private ActionListener listener;

	/**
	 * 
	 * Creates a library panel that accepts a library and an action listener as
	 * parameters.
	 * 
	 * @param l
	 * @param listener
	 */
	public LibraryPanel(Library l, ActionListener listener) {
		this.library = l;
		this.listener = listener;
		bookListPanel = new JPanel();
		bookButtonPanel = new JPanel();
		importBooksPanel = new JPanel();
		load = new JButton("Load");
		txtField = new JTextField(15);
		lTitle = BorderFactory.createTitledBorder("Library");
		librarySize = 0;
		setBorder(lTitle);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(300, 800));
	}

	/**
	 * Adds a JScrollPane to the library to the book list panel.
	 */
	public void addScroller() {
		scroller = new JScrollPane(bookButtonPanel);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setBorder(BorderFactory.createEmptyBorder());
		scroller.setPreferredSize(new Dimension(250, 550));
		bookListPanel.add(scroller, BorderLayout.CENTER);
	}

	/**
	 * Adds book buttons to the book button list and the book button panel.
	 */
	public void addBookButtons() {
		books = library.getBooks();
		bookButtonList = new ArrayList<BookButton>();
		for (Book b : books) {
			button = new BookButton(b);
			button.setPreferredSize(new Dimension(220, 35));
			button.addActionListener(listener);
			bookButtonList.add(button);
			bookButtonPanel.add(button);
		}
	}

	/**
	 * Returns a list of buttons.
	 * 
	 * @return
	 */
	public ArrayList<BookButton> getBookButtons() {
		return bookButtonList;
	}

	/**
	 * Returns the book button panel.
	 * 
	 * @return
	 */
	public JPanel getBookButtonPanel() {
		return bookButtonPanel;
	}

	/**
	 * Adds the book list panel to the library panel.
	 */
	public void addBookListPanel() {
		TitledBorder bTitle = BorderFactory.createTitledBorder("Book List");
		bookListPanel.setBorder(bTitle);
		add(bookListPanel);
	}

	/**
	 * Adds the book button panel to the book list panel.
	 */
	public void addBookButtonPanel() {
		bookButtonPanel.setPreferredSize(new Dimension(250, 600));
		bookListPanel.add(bookButtonPanel);
	}

	/**
	 * Adds the import books panel to the library panel.
	 */
	public void addImportBooksPanel() {
		txtField.addActionListener(new AddLoadButtonListener());
		importBooksPanel.add(txtField);
		load.addActionListener(new AddLoadButtonListener());
		importBooksPanel.add(load);
		TitledBorder iTitle = BorderFactory.createTitledBorder("Import Books");
		importBooksPanel.setBorder(iTitle);
		add(importBooksPanel);
	}

	/**
	 * Defines the action listener for the load button. When the load button is
	 * clicked, books are loaded to the library and book buttons are created to
	 * represent the books in the library.
	 * 
	 * @author chloejohnson
	 *
	 */
	private class AddLoadButtonListener implements ActionListener {

		/**
		 * Runs the code that allows the load button to work properly.
		 */
		public void actionPerformed(ActionEvent e) {
			bookButtonPanel.removeAll();
			if (bookButtonList != null) {
				bookButtonList.clear();
			}
			String libraryToLoad = txtField.getText();
			txtField.setText("");
			library.loadLibraryFromCSV(libraryToLoad);
			addBookButtons();

			books = library.getBooks();
			for (Book b : books) {
				button = new BookButton(b);
				button.setPreferredSize(new Dimension(220, 35));
				bookButtonList.add(button);
			}

			librarySize = books.size();
			bookButtonPanel.setPreferredSize(new Dimension(250, 590 + (10 * librarySize)));
			revalidate();
		}

	}
}