package gui.forms.panels;

import interfaces.Database;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.BicycleGarageManager;

public abstract class Form {
	
	protected JTextField[] fields;
	protected BicycleGarageManager manager;
	protected Database db;
	
	public Form(BicycleGarageManager manager, String title) {
		this.manager = manager;
		this.db = manager.getDB();
		JFrame frame = new JFrame(title);
		frame.setMinimumSize(new Dimension(300, 150));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String options[] = { "Ja, stäng", "Nej" };
				if (JOptionPane.showOptionDialog(null,
						"Är du säker på att du vill stänga formuläret?",
						"Säkerhetsfråga", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
					manager.enable(true);
					frame.dispose();
				}
			}
		});
		
		String[] labels = getLabels();
		int[] widths = getWidths();
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
		JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));

		frame.add(labelPanel, BorderLayout.WEST);
		frame.add(fieldPanel, BorderLayout.CENTER);

		fields = new JTextField[labels.length];

		for (int i = 0; i < labels.length; i++) {
			fields[i] = new JTextField();
			if (i < widths.length)
				fields[i].setColumns(widths[i]);

			JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
			lab.setLabelFor(fields[i]);
			labelPanel.add(lab);
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
			p.add(fields[i]);
			fieldPanel.add(p);
		}
		
		JButton submit = new JButton(title.split(" ")[0]);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] stringfield = new String[labels.length];
				StringBuilder sb = new StringBuilder(title);
				for (int i = 0; i < labels.length; i++){
					stringfield[i] = fields[i].getText().trim();
					sb.append("\n" + labels[i] + ": " + stringfield[i]);
				}
				String options[] = { "Ja, " + title.split(" ")[0].toLowerCase(), "Nej" };
				if (check(stringfield) && JOptionPane.showOptionDialog(null,
						sb.toString(),
						"Säkerhetsfråga", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
					action(stringfield);
					manager.enable(true);
					frame.dispose();
				}
			}
		});
		JPanel p = new JPanel();
		p.add(submit);
		frame.add(p, BorderLayout.SOUTH);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public abstract String[] getLabels();
	public abstract int[] getWidths();
	public abstract boolean check(String[] stringfields);
	public abstract void action(String[] stringfields);
	
}
