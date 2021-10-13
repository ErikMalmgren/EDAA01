package textproc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class BookReaderController {

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 800, 600));
		}
	
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		List<Map.Entry<String, Integer>> listPairs = counter.getWordList();
		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(listPairs);

		JList<Map.Entry<String, Integer>> listView = new JList<Entry<String, Integer>>(listModel);
		listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(listView);
		scrollPane.setPreferredSize(new Dimension(200, 100));
		scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10));
		pane.add(scrollPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();

		JRadioButton alphabetic = new JRadioButton("Alphabetic", true);
		JRadioButton frequency = new JRadioButton("Frequency", false);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(alphabetic);
		buttonGroup.add(frequency);
		pane.add(panel, BorderLayout.SOUTH);

		alphabetic.addActionListener(event -> {
			listModel.sort((e1, e2) -> ((Map.Entry<String, Integer>) e1).getKey()
					.compareTo(((Map.Entry<String, Integer>) e2).getKey()));
			listView.ensureIndexIsVisible(0);
		});

		frequency.addActionListener(event -> {
			listModel.sort((e1, e2) -> ((Map.Entry<String, Integer>) e2).getValue()
					- ((Map.Entry<String, Integer>) e1).getValue());
			listView.ensureIndexIsVisible(0);
		});

		JTextArea textInput = new JTextArea("Search");
		textInput.setBackground(Color.WHITE);
		textInput.setForeground(Color.BLACK);

		JButton find = new JButton("Find");

		find.addActionListener(event -> {
			String word = textInput.getText().trim();
			int wordCount = 0;

			for (int i = 0; i < listModel.getSize(); i++) {
				if (listModel.getElementAt(i).getKey().equalsIgnoreCase(word)) {
					listView.setSelectedIndex(i);
					listView.ensureIndexIsVisible(i);
					wordCount++;
					break;
				}
			}

			if (wordCount == 0) {
				String message = "The word " + word + " cannot be found";
				JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		panel.add(alphabetic);
		panel.add(frequency);
		panel.add(find);
		panel.add(textInput);

		// pane �r en beh�llarkomponent till vilken de �vriga komponenterna (listvy,
		// knappar etc.) ska l�ggas till.
		frame.pack();
		frame.setVisible(true);
	}
}
