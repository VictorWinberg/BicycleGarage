package database;

public class Bicycle {
	private String personnr, barcode;
	private boolean isParked;
	
	public Bicycle(String personnr) {
		this.personnr = personnr;
		isParked = false;
		String chars = "0123456789";
		StringBuilder sb = new StringBuilder();
		while (sb.length() < 5) {
			int index = (int) (Math.random() * chars.length());
			sb.append(chars.charAt(index));
		}
		barcode = sb.toString();
	}
	
	public String getUserPersonnr() { return personnr; }
	public String getBarcode() { return barcode; }
	public void park() { isParked = true; }
	public void unpark() { isParked = false; }
	public boolean isParked() { return isParked; }
}
