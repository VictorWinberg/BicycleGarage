package gui.forms.buttons;

	import gui.forms.panels.ReserveSlotForm;
import gui.misc.buttons.JModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

	@SuppressWarnings("serial")
public class ReserveSlotButton extends JModifiedButton implements
ActionListener{
	

		private BicycleGarageManager manager;

		public ReserveSlotButton(BicycleGarageManager manager, double size) {
			super("Reservera plats/er", size);
			this.manager = manager;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			manager.enable(false);
			new ReserveSlotForm(manager);
		}
	}
