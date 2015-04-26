package gui.buttons;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class JModifiedButton extends JButton {

	public JModifiedButton(String text) {
		super(text);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) (super.getPreferredSize().getWidth() * 1.5),
				(int) (super.getPreferredSize().getHeight() * 1.5));
	}

	@Override
	public Font getFont() {
		return new Font(Font.SANS_SERIF, Font.BOLD, 13);
	}
}
