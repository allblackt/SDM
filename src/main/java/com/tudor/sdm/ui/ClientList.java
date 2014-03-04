package com.tudor.sdm.ui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;

public class ClientList {
	private JDialog clientDialog;
	private	JTable		table;
	private	JScrollPane scrollPane;
	
	public ClientList(JFrame parent) {
		clientDialog = new JDialog(parent);
		clientDialog.setTitle(Language.get().getString(StringNames.WINDOW_MANAGE_CLIENTS_TITLE));
		clientDialog.setSize(400, 100);
		clientDialog.setLocationRelativeTo(parent);
		clientDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		
		addTable();
		
		getTableData();
		
		clientDialog.setVisible(true);
	}
	
	private void addTable () {
		// Create columns names
		String columnNames[] = { "Column 1", "Column 2", "Column 3" };

		// Create some data
		String dataValues[][] =
		{
			{ "12", "234", "67" },
			{ "-123", "43", "853" },
			{ "93", "89.2", "109" },
			{ "279", "9033", "3092" }
		};

		// Create a new table instance
		table = new JTable( dataValues, columnNames );
		scrollPane = new JScrollPane( table );
		clientDialog.add( scrollPane, BorderLayout.CENTER );
	}
	
	private void getTableData() {
		
			
	}
}
