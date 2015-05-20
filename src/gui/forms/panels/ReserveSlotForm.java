	package gui.forms.panels;

	import gui.managers.ViewState;
	import interfaces.Database;

	import javax.swing.JOptionPane;

	import main.BicycleGarageManager;
	import database.Bicycle;
	import database.User;

	

public class ReserveSlotForm extends Form{
		private BicycleGarageManager manager;
		
		public ReserveSlotForm(BicycleGarageManager manager) {
			super(manager, "Reservera plats");
			this.manager = manager;
		}

		@Override
		public String[] getLabels() {
			String[] labels = { "Personnummer", "PIN-kod", "Antal platser"};
			return labels;
		}

		@Override
		public int[] getWidths() {
			int[] widths = { 11, 15, 5};
			return widths;
		}
		
		
		@Override
		public boolean check(String[] fields) {
			Database db = manager.getDB();
			User user = db.getUser(fields[0]);
			User userPIN = db.getUserWithPIN(fields[1]);
			String nbrOfSpots = fields[2];
			if(user.equals(userPIN)){
				try {
					int slots = Integer.parseInt(nbrOfSpots);
					for(int i = 0; i < slots; i++ ){
						db.reserveSlot(user);
					}
					return true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			} else JOptionPane.showMessageDialog(null, "Felaktig PIN");
			return false;
		}

		@Override
		public void action(String[] fields) {
			manager.changeState(ViewState.BICYCLE_STATE);
		}
}
