package com.tudor.sdm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tudor.sdm.Constants;
import com.tudor.sdm.Constants.StringNames;
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
		
		addButtons();
		
		window.setVisible(true);
	}
	
	private void addButtons() {
		JPanel panel = new JPanel();
		window.getContentPane().add(panel);
		JButton btnManageClients = new JButton(Language.get().getString(StringNames.BTN_MANAGE_CLIENTS_LABEL));
		btnManageClients.setBounds(10, 20, 150, 30);
		
		btnManageClients.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ClientList();
			}
		});
		
		panel.add(btnManageClients);
	}

	


}
