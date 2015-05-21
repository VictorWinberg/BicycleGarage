package gui.misc.buttons;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ModifiedButton extends JButton {

	private double sizeModifier;
	
	public ModifiedButton(String text, double sizeModifier) {
		super(text);
		this.sizeModifier = sizeModifier;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) (super.getPreferredSize().getWidth() * sizeModifier),
				(int) (super.getPreferredSize().getHeight() * sizeModifier));
	}

	@Override
	public Font getFont() {
		return new Font(Font.SANS_SERIF, Font.BOLD, (int)(12 * sizeModifier));
	}
}
