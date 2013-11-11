package com.tudor.sdm.ui;

import javax.swing.JFrame;

import com.tudor.sdm.Constants;
import com.tudor.sdm.Language;


public class WelcomeWindow {

	JFrame window;
	public WelcomeWindow() {
		window = new JFrame();
		init();
	}
	private void init() {
		window.setTitle(Language.get().getString(Constants.StringNames.TITLE));
		window.setSize(300, 200);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	


}
