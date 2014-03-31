package com.tudor.sdm;

import com.tudor.sdm.ui.WelcomeWindow;
import org.apache.log4j.Logger;

import javax.swing.*;

public class SDM {

	private static final Logger log = Logger.getLogger(SDM.class.getName());
	public static void main(String[] args) {
        if(!(new SingleInstanceChecker().isAppActive())) {
            log.info("Started app from " + System.getProperty("user.dir"));
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new WelcomeWindow();
                }
            });
        } else {
            log.info("Application is already running...");
        }
	}
}
