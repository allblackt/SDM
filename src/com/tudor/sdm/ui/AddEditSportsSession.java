package com.tudor.sdm.ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -144404306409409293L;
	private JTextField txtSportsSessionName;
	
	public AddEditSportsSession() {
		getContentPane().setLayout(null);
		
		lblSportsSessionName = new JLabel("New label");
		lblSportsSessionName.setBounds(23, 24, 70, 15);
		getContentPane().add(lblSportsSessionName);
		
		lblSportsSessionDuration = new JLabel("New label");
		lblSportsSessionDuration.setBounds(23, 51, 70, 15);
		getContentPane().add(lblSportsSessionDuration);
		
		lblSportsSessionPrice = new JLabel("New label");
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
		
		btnSave = new JButton("New button");
		btnSave.setBounds(167, 108, 117, 25);
		getContentPane().add(btnSave);
		
		btnCancel = new JButton("New button");
		btnCancel.setBounds(306, 108, 117, 25);
		getContentPane().add(btnCancel);
	}
}
