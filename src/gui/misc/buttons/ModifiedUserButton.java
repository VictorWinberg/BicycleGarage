package gui.misc.buttons;

import database.User;

@SuppressWarnings("serial")
public class ModifiedUserButton extends ModifiedButton {

	protected User user;

	public ModifiedUserButton(String text, double sizeModifier) {
		super(text, sizeModifier);
	}

	public void changeUser(User user) {
		this.user = user;
	}
}
