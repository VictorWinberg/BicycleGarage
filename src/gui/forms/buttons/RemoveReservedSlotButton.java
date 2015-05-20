	package gui.forms.buttons;

	import gui.forms.panels.RemoveReservedSlotForm;
import gui.misc.buttons.JModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;



	@SuppressWarnings("serial")
public class RemoveReservedSlotButton extends JModifiedButton implements
ActionListener{
	

		private BicycleGarageManager manager;

		public RemoveReservedSlotButton(BicycleGarageManager manager, double size) {
			super("Avreservera plats/er", size);
			this.manager = manager;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			manager.enable(false);
			new RemoveReservedSlotForm(manager);
		}
	}


