package be.rafvdl.rfid.toegangscontrole;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import be.rafvdl.rfid.api.Reader;
import be.rafvdl.rfid.api.Tag;

public class Main {

	private JFrame frame;

	private Reader reader;
	private List<Tag> allowed = new ArrayList<Tag>();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
							.getSystemLookAndFeelClassName());
					Main window = new Main();

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		reader = new Reader("COM4");
		reader.setDebug(true);
		if (!reader.open()) {
			System.err.println("Reader not found. Aborting");
			return;
		}
		reader.init();

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnKaartToevoegen = new JButton("Kaart toevoegen");
		btnKaartToevoegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Tag> tags = reader.getCurrentTags();

				new KaartToevoegen(tags, allowed);
			}
		});
		toolBar.add(btnKaartToevoegen);

		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);

		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Tag> tags = reader.getCurrentTags();

				boolean found = false;
				for (Tag tag1 : tags) {
					for (Tag tag2 : allowed) {
						if (tag1.getUid().equals(tag2.getUid())) {
							found = true;
							textField.setText("Deur open");
							break;
						}
					}
				}
				if (!found) {
					textField.setText("Deur gesloten");
				}
				found = false;
			}
		});
		toolBar.add(btnTest);

		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				reader.close();
			}
		});
	}

}
