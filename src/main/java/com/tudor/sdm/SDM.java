package com.tudor.sdm;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.tudor.sdm.ui.WelcomeWindow;

public class SDM {

	private static final Logger log = Logger.getLogger(SDM.class.getName()); 
	public static void main(String[] args) {
		
		log.info("Started app from " + System.getProperty("user.dir"));
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new WelcomeWindow();
				}
			});
	}

}
