package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setAutoRequestFocus(false);
		window.setTitle("Schatzinsel");
		
		GamePanel spielfeld = new GamePanel();
		window.add(spielfeld);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		spielfeld.startGameThread();
	}

}
