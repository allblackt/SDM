package com.tudor.sdm.ui;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;

import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class SportsSessionListView extends JFrame {
	private JTable tblSportsSessionList;
	public SportsSessionListView() {
		setSize(new Dimension(900, 500));
		getContentPane().setLayout(null);
		
		JButton brnAddSportsSession = new JButton(Language.get().getString(StringNames.BTN_ADD_NEW_SPORTS_SESSION_LABEL));
		brnAddSportsSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		brnAddSportsSession.setBounds(761, 12, 117, 25);
		getContentPane().add(brnAddSportsSession);
		
		JButton btnEditSportsSession = new JButton(Language.get().getString(StringNames.BTN_EDIT_SPORTS_SESSION_LABEL));
		btnEditSportsSession.setBounds(761, 49, 117, 25);
		getContentPane().add(btnEditSportsSession);
		
		tblSportsSessionList = new JTable();
		tblSportsSessionList.setBounds(12, 12, 1, 1);
		getContentPane().add(tblSportsSessionList);
	}
}
