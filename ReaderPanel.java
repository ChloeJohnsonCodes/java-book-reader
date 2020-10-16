import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * Creates a reader panel with several methods.
 * 
 * @author chloejohnson
 *
 */
@SuppressWarnings("serial")

public class ReaderPanel extends JPanel {
	// Provides the title, author, and page number information for the book
	private JPanel informationPanel;
	// Stores the JScrollPane scroller and JTextArea textArea.
	private JPanel contentPanel;
	// Stores the JButtons that move the scroll bar up and down.
	private JPanel navigationPanel;
	private JLabel title;
	private JLabel author;
	private JLabel pageNumber;
	private TitledBorder rTitle;
	private TitledBorder iTitle;
	private TitledBorder cTitle;
	private TitledBorder nTitle;
	private JTextArea textArea;
	private JButton pageDown;
	private JButton pageUp;
	private JScrollPane scroller;
	private JScrollBar scrollBar;
	private int maxPageNumber;
	private int currentValue;
	private String bookButtonAuthor;
	private String bookButtonTitle;
	private String blankSpaceAuthor;
	private String blankSpaceTitle;
	private int currentPageNumber;
	private Font font;
	private JLabel titleWords;
	private JLabel authorWords;
	private JLabel pageNumberWords;

	/**
	 * Creates a reader panel.
	 */
	public ReaderPanel() {
		rTitle = BorderFactory.createTitledBorder("Reader");
		bookButtonAuthor = "                                       ";
		bookButtonTitle = "                                       ";
		blankSpaceAuthor = "";
		blankSpaceTitle = "";

		setBorder(rTitle);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	/**
	 * Adds an info panel to the reader panel.
	 */
	public void addInfoPanel() {
		informationPanel = new JPanel();
		iTitle = BorderFactory.createTitledBorder("Information");
		informationPanel.setBorder(iTitle);
		informationPanel.setPreferredSize(new Dimension(700, 50));

		font = new Font("Arial", Font.BOLD, 15);
		titleWords = new JLabel("Title: ");
		authorWords = new JLabel("Author: ");
		pageNumberWords = new JLabel("Page Number: ");
		pageNumberWords.setFont(font);
		titleWords.setFont(font);
		authorWords.setFont(font);
		informationPanel.add(titleWords);
		title = new JLabel(bookButtonTitle + blankSpaceTitle);
		informationPanel.add(title);
		informationPanel.add(authorWords);
		author = new JLabel(bookButtonAuthor + blankSpaceAuthor);
		informationPanel.add(author);
		informationPanel.add(pageNumberWords);
		pageNumber = new JLabel("1/1");
		informationPanel.add(pageNumber);
		add(informationPanel);

	}

	/**
	 * Adds a content panel to the reader panel.
	 */
	public void addContentPanel() {
		contentPanel = new JPanel();
		cTitle = BorderFactory.createTitledBorder("Content");
		contentPanel.setBorder(cTitle);
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(700, 500));
		add(contentPanel);
	}

	/**
	 * Adds a text area to the content panel.
	 */
	public void addTextArea() {
		textArea = new JTextArea(0, 450);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		contentPanel.add(textArea, BorderLayout.CENTER);

	}

	/**
	 * Updates text in the text area and accept
	 * 
	 * @param text
	 */
	public void updateTextArea(String text) {
		textArea.setText(text);
		textArea.setCaretPosition(0);

	}

	/**
	 * Returns the text area.
	 * 
	 * @return
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * Adds a JScrollPane to the content panel.
	 */
	public void addTextAreaScroller() {
		scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setBorder(BorderFactory.createEmptyBorder());

		scroller.setPreferredSize(new Dimension(500, 450));
		contentPanel.add(scroller, BorderLayout.CENTER);

	}

	/**
	 * Adds the navigation panel to the reader panel.
	 */
	public void addNavigationPanel() {
		navigationPanel = new JPanel();
		navigationPanel.setPreferredSize(new Dimension(700, 50));
		nTitle = BorderFactory.createTitledBorder("Navigation");
		navigationPanel.setBorder(nTitle);
		add(navigationPanel);
	}

	/**
	 * Adds the page up button and the page down button to the navigation panel
	 */
	public void addNavigationButtons() {
		scrollBar = scroller.getVerticalScrollBar();
		scrollBar.addAdjustmentListener(new AddScrollBarAdjustmentListener());
		currentValue = 0;
		maxPageNumber = 0;
		pageUp = new JButton("Page Up");
		pageUp.addActionListener(new AddPageUpButtonListener());
		pageDown = new JButton("Page Down");
		pageDown.addActionListener(new AddPageDownButtonListener());
		navigationPanel.add(pageUp);
		navigationPanel.add(pageDown);
	}

	/**
	 * Updates the information panel. Takes in a book button as a parameter.
	 * 
	 * @param bookButton
	 */
	public void updateInfoPanel(BookButton bookButton) {

		bookButtonAuthor = bookButton.getBookButtonAuthor();
		bookButtonTitle = bookButton.getBookButtonTitle();

		title.setText(bookButtonTitle);
		author.setText(bookButtonAuthor);

	}

	/**
	 * Changes the position of the scrollbar if the page up button is pressed.
	 * 
	 * @author chloejohnson
	 *
	 */
	private class AddPageUpButtonListener implements ActionListener {

		/**
		 * Runs the code that allows the page up button to work.
		 */
		public void actionPerformed(ActionEvent e) {

			int height = scrollBar.getBlockIncrement(-1);
			scrollBar.setValue(currentValue - height);
			currentValue = scrollBar.getValue();

		}

	}

	/**
	 * Changes the position of the scrollbar if the page down button is pressed.
	 * 
	 * @author chloejohnson
	 *
	 */
	private class AddPageDownButtonListener implements ActionListener {

		/**
		 * Runs the code that allows the page down button to work.
		 */
		public void actionPerformed(ActionEvent e) {

			int height = scrollBar.getBlockIncrement(1);
			scrollBar.setValue(currentValue + height);
			currentValue = scrollBar.getValue();

		}

	}

	/**
	 * Adjusts the page numbers when the scrollbar is moved. Enables and disables
	 * page up and page down buttons if minimum or maximum amount of pages is
	 * reached.
	 * 
	 * @author chloejohnson
	 *
	 */
	private class AddScrollBarAdjustmentListener implements AdjustmentListener {

		/**
		 * Run the code that changes page numbers, enables buttons, and disables
		 * buttons.
		 */
		public void adjustmentValueChanged(AdjustmentEvent e) {

			currentValue = scrollBar.getValue();
			currentPageNumber = (int) Math.ceil(currentValue / (float) (scroller.getViewport().getHeight())) + 1;
			maxPageNumber = (int) (Math.ceil(scrollBar.getMaximum() / (float) (scroller.getViewport().getHeight())));

			if (currentPageNumber == 0 || currentPageNumber == 1) {
				pageUp.setEnabled(false);
			}

			else {
				pageUp.setEnabled(true);
			}

			if (currentPageNumber == maxPageNumber || maxPageNumber == 0) {
				pageDown.setEnabled(false);
			}

			else {
				pageDown.setEnabled(true);
			}

			pageNumber.setText(currentPageNumber + "/" + maxPageNumber);

		}

	}
}