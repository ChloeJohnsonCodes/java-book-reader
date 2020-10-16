import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
* Manages books stored in an ArrayList by allowing users to retrieve, remove, and add books. 
*
* @author chloejohnson
*/
public class Library implements LibraryInterface {

	private ArrayList<Book> listOfBooks;

	/**
	* Creates a Library from an ArrayList of books.
	*/
	public Library() {
		listOfBooks = new ArrayList<Book>();
	}

	/**
	* Returns the books stored in the Library.
	*
	* @return the ArrayList of books
	*/
	public ArrayList<Book> getBooks() {
		ArrayList<Book> list = new ArrayList<Book>();
		for (Book book : listOfBooks) {
			list.add(book);
		}

		return list;
	}

	/**
	* Adds a book to the library.
	* 
	* @param newBook book to be added to the library
	*/
	public void addBook(Book newBook) {
		listOfBooks.add(newBook);
	}

	/**
	* Removes a book from the library.
	*
	* @param index index of the book to be removed
	*/
	public void removeBook(int index) {
		if ((index > -1) && (index < listOfBooks.size())) {
			listOfBooks.remove(index);
		}
	}

	/**
	* Retrieves a book at the specified index from the library 
	*
	* @param index index of the book to be returned
	*
	* @return a book in the library 
	*/
	public Book getBook(int index) {
		if ((index > -1) && (index < listOfBooks.size())) {
			return listOfBooks.get(index);
		} else {
			return null;
		}
	}

	/**
	* Converts the library into a printable string
	*
	* @return formatted string of library books
	**/
	public String toString() {
		String catalog = "";
		String bookWithIndex = "";
		for (Book book : listOfBooks) {
			bookWithIndex = "Index: (" + listOfBooks.indexOf(book) + ") " + book.toString()
					+ "--------------------------------------------------------\n";
			catalog = catalog.concat(bookWithIndex);
		}
		return catalog;
	}

	/**
	* Adds books to the library from a CSV file.
	*
	* @param csvFilename a filename for a CSV file
	*/
	public void loadLibraryFromCSV(String csvFilename) {
		File file = new File(csvFilename);
		try {
			listOfBooks.clear();
			Scanner fileScan = new Scanner(file);
			while (fileScan.hasNextLine()) {
				String line = fileScan.nextLine();
				Scanner lineScan = new Scanner(line);
				lineScan.useDelimiter(",");
				while (lineScan.hasNext()) {
					String title = lineScan.next();
					String author = lineScan.next();
					String genre = lineScan.next();
					String fileName = lineScan.next();
					Book book = new Book(title, author);
					book.setGenre(genre);
					book.setFilename(fileName);
					listOfBooks.add(book);
				}
				lineScan.close();
			}
			fileScan.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("The file cannot be found.");
		}
	}
}