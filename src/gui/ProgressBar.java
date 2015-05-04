package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class ProgressBar extends JProgressBar{
	
	private JFrame frame;
	
	public ProgressBar(int size) {
		super(0, size - 1);
		setStringPainted(true);
		setForeground(Color.GREEN);
		frame = new JFrame("Laddar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 70));
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.add(this);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getPreferredSize().width/2, dim.height/2-frame.getPreferredSize().height/2);
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void setValue(int n) {
		if(getValue() == super.getMaximum())
			frame.dispose();
		else
			super.setValue(n);
	}
	
	public void addValue(int n) {
		if(getValue() == super.getMaximum())
			frame.dispose();
		else
			super.setValue(getValue() + n);
	}
	
	@Override
	public int getMaximum() {
		return super.getMaximum() + 1;
	}
	
	public static void main(String[] args) {
		ProgressBar bar = new ProgressBar(10);
		try {
			for (int i = 1; i <= bar.getMaximum() - 1; i++) {
				bar.addValue(1);
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
