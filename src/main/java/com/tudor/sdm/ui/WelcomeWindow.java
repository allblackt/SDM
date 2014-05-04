package com.tudor.sdm.ui;

import com.tudor.sdm.Constants;
import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		addButtons();
        addMenuBarAndItems();

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

        //TODO: Put a string for this
        JButton btnShowReservations = UIElementGenerator.createJButton("Reservations");
        btnShowReservations.setBounds(10, 20, 180, 30);

        btnShowReservations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new CalendarUI();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.add(btnShowReservations);
	}

    private void addMenuBarAndItems() {
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

        JMenu mnuClientMenu = new JMenu(Language.get().getString(StringNames.MNU_ADMIN_LABEL));
        menuBar.add(mnuAdminMenu);

        JMenu mnuDataManagement = new JMenu("DATA MANAGEMENT");
        JMenuItem mniViewAddEditTrainers = new JMenuItem(Language.get().getString(
                StringNames.MNU_VIEW_ADD_EDIT_TRAINER
        ));
        mniViewAddEditTrainers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEditListTrainer();
            }
        });
        mnuDataManagement.add(mniViewAddEditTrainers);

        menuBar.add(mnuDataManagement);
    }
}
