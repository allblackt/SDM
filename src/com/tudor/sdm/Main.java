package com.tudor.sdm;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.tudor.sdm.ui.WelcomeWindow;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class.getName()); 
	public static void main(String[] args) {
		log.info("Started app");
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new WelcomeWindow();
				}
			});
	}

}
