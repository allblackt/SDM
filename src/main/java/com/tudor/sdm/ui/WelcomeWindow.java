package com.tudor.sdm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.tudor.sdm.Constants;
import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


public class WelcomeWindow {

	private static final Logger log = Logger.getLogger(WelcomeWindow.class.getName());
	JFrame window;
	public WelcomeWindow() {
		log.debug("Loading the welcome window...");
		window = new JFrame();
		init();
	}
	
	private void init() {
		log.debug("Initializing welcome window properties...");
		window.setTitle(Language.get().getString(Constants.StringNames.TITLE));
		window.setSize(300, 200);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addButtons();
		
		window.setVisible(true);
	}
	
	private void addButtons() {
		log.debug("Adding welcome window buttons...");
		JPanel panel = new JPanel();
		window.getContentPane().add(panel);
		JButton btnManageClients =  UIElementGenerator.createJButton(Language.get().getString(StringNames.BTN_MANAGE_CLIENTS_LABEL));
		btnManageClients.setBounds(10, 20, 150, 30);
		
		btnManageClients.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ClientList(window);
			}
		});
		panel.add(btnManageClients);
		
		JMenuBar menuBar = new JMenuBar();
		window.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnuAdminMenu = new JMenu(Language.get().getString(StringNames.MNU_ADMIN_LABEL));
		menuBar.add(mnuAdminMenu);
		
		JMenuItem mniListSportsSessionTypes = new JMenuItem(Language.get().getString(StringNames.MNU_LIST_SPORTS_SESSION_TYPES));
		mniListSportsSessionTypes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SportsSessionListView(window);
			}
		});
		mnuAdminMenu.add(mniListSportsSessionTypes);
	}
}
