import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Creates the reader of books panel with an action listener.
 * 
 * @author chloejohnson
 *
 */
@SuppressWarnings("serial")
public class ReaderOfBooksPanel extends JPanel {

	private Library library;
	private LibraryPanel libraryPanel;
	private ReaderPanel readerPanel;
	private ArrayList<BookButton> bookButtonList;
	private BookButton actionBookButton;
	private Book bookButtonBook;
	private String text;
	private ActionListener listener = new AddBookButtonListener();

	/**
	 * Creates the reader of books panel.
	 */
	public ReaderOfBooksPanel() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		library = new Library();
		libraryPanel = new LibraryPanel(library, listener);
		libraryPanel.addBookListPanel();
		libraryPanel.addBookButtonPanel();
		libraryPanel.addScroller();
		libraryPanel.addImportBooksPanel();
		add(libraryPanel);

		readerPanel = new ReaderPanel();
		readerPanel.addInfoPanel();
		readerPanel.addContentPanel();

		readerPanel.addTextArea();
		readerPanel.addTextAreaScroller();
		readerPanel.addNavigationPanel();
		readerPanel.addNavigationButtons();
		add(readerPanel);
	}

	/**
	 * Adds the text from the book to the text area if a book button is pressed.
	 */
	private class AddBookButtonListener implements ActionListener {

		/**
		 * Runs the code that allows the book text to be added to the text area.
		 */
		public void actionPerformed(ActionEvent e) {

			bookButtonList = libraryPanel.getBookButtons();
			for (int i = 0; i < bookButtonList.size(); i++) {
				if (e.getSource() == bookButtonList.get(i)) {

					actionBookButton = bookButtonList.get(i);
					bookButtonBook = actionBookButton.getBookButtonBook();
					text = bookButtonBook.getText();
					readerPanel.updateTextArea(text);
					bookButtonList = libraryPanel.getBookButtons();
					readerPanel.updateInfoPanel(actionBookButton);
					readerPanel.revalidate();
				}
			}
		}
	}
}