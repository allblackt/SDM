package com.tudor.sdm.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;
import com.tudor.sdm.dao.SportsSessionDAO;
import com.tudor.sdm.entity.SportsSession;

public class AddEditSportsSession extends JDialog {
	
	private SportsSession session;
	JLabel lblSportsSessionName;
	JLabel lblSportsSessionDuration;
	JLabel lblSportsSessionPrice;
	JSpinner spnSportsSessionDuration;
	JSpinner spnSportsSessionPrice;
	JButton btnSave;
	JButton btnCancel;
	
	private static final long serialVersionUID = -144404306409409293L;
	private JTextField txtSportsSessionName;
	
	public AddEditSportsSession(SportsSession session) {
		// Call the no args constructor to init the values
		this();

		// populate with data
		txtSportsSessionName.setText(session.getName());
		spnSportsSessionDuration.setValue(session.getDuration());
		spnSportsSessionPrice.setValue(session.getPrice());
	}
	
	public AddEditSportsSession() {
		setModal(true);
		
		setSize(500, 300);
		
		setTitle(Language.get().getString(StringNames.WINDOW_TITLE_ADD_EDIT_SPORTS_SESSIONS));
		
		setLocationRelativeTo(getParent());
		getContentPane().setLayout(null);
		
		lblSportsSessionName = new JLabel(Language.get().getString(StringNames.LBL_SPORTS_SESSION_NAME));
		lblSportsSessionName.setBounds(23, 24, 70, 15);
		getContentPane().add(lblSportsSessionName);
		
		lblSportsSessionDuration = new JLabel(Language.get().getString(StringNames.LBL_SPORTS_SESSION_DURATION));
		lblSportsSessionDuration.setBounds(23, 51, 70, 15);
		getContentPane().add(lblSportsSessionDuration);
		
		lblSportsSessionPrice = new JLabel(Language.get().getString(StringNames.LBL_SPORTS_SESSION_PRICE));
		lblSportsSessionPrice.setBounds(23, 78, 70, 15);
		getContentPane().add(lblSportsSessionPrice);
		
		txtSportsSessionName = new JTextField();
		txtSportsSessionName.setBounds(117, 22, 311, 19);
		getContentPane().add(txtSportsSessionName);
		txtSportsSessionName.setColumns(10);
		
		spnSportsSessionDuration = new JSpinner();
		spnSportsSessionDuration.setBounds(127, 49, 296, 20);
		getContentPane().add(spnSportsSessionDuration);
		
		spnSportsSessionPrice = new JSpinner();
		spnSportsSessionPrice.setBounds(127, 76, 296, 20);
		getContentPane().add(spnSportsSessionPrice);
		
		btnSave = new JButton(Language.get().getString(StringNames.BTN_GENERIC_SAVE_LABEL));
		btnSave.setBounds(167, 108, 117, 25);
		getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// on click
				if (isInputValid()) {
					try {
						if(session == null) {
							session = new SportsSession.SportsSessionBuilder().name(txtSportsSessionName.getText())
									.duration(Integer.parseInt(spnSportsSessionDuration.getValue().toString()))
									.price(Long.parseLong(spnSportsSessionPrice.getValue().toString()))
									.build();
							SportsSessionDAO.get().add(session);
						} else {
							session.setName(txtSportsSessionName.getText());
							session.setDuration(Integer.parseInt(spnSportsSessionDuration.getValue().toString()));
							session.setPrice(Long.parseLong(spnSportsSessionPrice.getValue().toString()));
							SportsSessionDAO.get().save(session);
						}
						dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
						new ErrorMessage(e1.getMessage());
					}
				} else {
					new ErrorMessage(Language.get().getString(StringNames.ERR_INVALID_DATA_INPUTED_FOR_SPORTS_SESSION));
				}
			}

			
		});
		
		
		btnCancel = new JButton(Language.get().getString(StringNames.BTN_GENERIC_CANCEL_LABEL));
		btnCancel.setBounds(306, 108, 117, 25);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
//		pack();
		setVisible(true);
		
	}
	
	private boolean isInputValid() {
		if (Long.parseLong(spnSportsSessionPrice.getValue().toString()) <= 0L) { return false; }
		if (Integer.parseInt(spnSportsSessionDuration.getValue().toString()) <= 0) { return false; }
		if (txtSportsSessionName.getText().length() <= 5) { return false; }
		return true;
	}
}
