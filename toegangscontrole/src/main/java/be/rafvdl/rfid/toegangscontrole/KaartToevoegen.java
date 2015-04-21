package be.rafvdl.rfid.toegangscontrole;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import be.rafvdl.rfid.api.Tag;

import javax.swing.AbstractListModel;

public class KaartToevoegen extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public KaartToevoegen(final List<Tag> tags, final List<Tag> allowed) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		final JList<Tag> list = new JList<Tag>();
		list.setModel(new AbstractListModel<Tag>() {
			private static final long serialVersionUID = 1L;

			public int getSize() {
				return tags.size();
			}

			public Tag getElementAt(int index) {
				return tags.get(index);
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPanel.add(list, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Add");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allowed.add(tags.get(list.getSelectedIndex()));
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
