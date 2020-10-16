import javax.swing.JButton;

/**
 * This class creates a book button object with several methods.
 * 
 * @author chloejohnson
 *
 */
@SuppressWarnings("serial")
public class BookButton extends JButton {

	private Book b;

	/**
	 * Creates a book button object that accepts a book as a parameter.
	 * 
	 * @param book
	 */
	public BookButton(Book book) {

		b = book;
		String title = book.getTitle();
		String formattedTitle;
		if (title.length() > 20) {
			formattedTitle = title.substring(0, 19);
			formattedTitle = formattedTitle + "...";
		} else {
			formattedTitle = title;
		}
		setText(formattedTitle);
		setToolTipText(book.toString());
	}

	/**
	 * Returns the title of the book used for the book button.
	 * 
	 * @return
	 */
	public String getBookButtonTitle() {
		String bTitle = b.getTitle();
		return bTitle;
	}

	/**
	 * Returns the author of the book used for the book button.
	 * 
	 * @return
	 */
	public String getBookButtonAuthor() {
		String bAuthor = b.getAuthor();
		return bAuthor;
	}

	/**
	 * Returns the book button.
	 * 
	 * @return
	 */
	public Book getBookButtonBook() {
		Book bookButtonBook = b;
		return bookButtonBook;
	}

}