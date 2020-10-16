import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates a book object with several methods
 * 
 * @author chloejohnson
 *
 */
public class Book implements BookInterface {

	private String title;
	private String author;
	private String genre;
	private String filename;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		genre = null;
		filename = null;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public String toString() {
		return "\nTitle: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nFilename: " + filename + "\n";
	}

	public boolean isValid() {

		if (filename == null) {
			return false;
		} else {
			File file = new File(filename);

			if (title == null) {
				return false;
			}

			else if (author == null) {
				return false;
			}

			else if (genre == null) {
				return false;
			}

			else if (!file.exists()) {
				return false;
			}

			else {
				return true;
			}
		}
	}

	public String getText() {
		File file = new File(filename);
		ArrayList<String> lines = new ArrayList<String>();
		String text = "";

		try {
			Scanner fileScan = new Scanner(file);
			while (fileScan.hasNextLine()) {
				lines.add(fileScan.nextLine());
			}
			for (String line : lines) {
				text = text + line + "\n";
			}
			fileScan.close();
		}

		catch (FileNotFoundException e) {
			return "Unable to open file: " + file + "\nTry again.";
		}

		return text;
	}
}