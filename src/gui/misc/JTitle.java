package gui.misc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JTitle extends JLabel {

	public JTitle() {
		
	}
	
	public JTitle(String title) {
		super(title);
	}

	@Override
	public Font getFont() {
		return new Font(Font.SANS_SERIF, Font.BOLD, 20);
	}

	@Override
	public int getHorizontalAlignment() {
		return JLabel.CENTER;
	}
	
	@Override
	public int getVerticalAlignment() {
		return JLabel.CENTER;
	}

	@Override
	public Color getForeground() {
		return Color.DARK_GRAY;
	}
}
