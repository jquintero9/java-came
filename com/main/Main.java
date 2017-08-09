package com.main;

import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Game");
		window.setContentPane(new Game());
		window.pack();
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
