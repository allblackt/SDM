package com.tudor.sdm.ui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;
import com.tudor.sdm.dao.ClientDAO;
import com.tudor.sdm.entity.Client;

public class ClientList {
	private JDialog dialog;
	private	JTable		table;
	private	JScrollPane scrollPane;
    private List<Long> idIndexMap;
	
	public ClientList(JFrame parent) {
		dialog = new JDialog(parent);
		dialog.setTitle(Language.get().getString(StringNames.WINDOW_MANAGE_CLIENTS_TITLE));
		dialog.setSize(1000, 500);
		dialog.setLocationRelativeTo(parent);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().setLayout(null);

        addTableToView();

        JButton btnAddSportsSession =  UIElementGenerator.createJButton(Language.get()
                .getString(StringNames.BTN_ADD_NEW_CLIENT_LABEL));
        btnAddSportsSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddEditClient(dialog).addWindowAdapter(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        reloadData();
                    }
                });
            }
        });
        btnAddSportsSession.setBounds(861, 12, 117, 25);
        dialog.getContentPane().add(btnAddSportsSession);

        JButton btnEditSportsSession = UIElementGenerator.createJButton(Language.get()
                .getString(StringNames.BTN_EDIT_CLIENT_LABEL));
        btnEditSportsSession.addActionListener(editButtonActionListener());
        btnEditSportsSession.setBounds(861, 49, 117, 25);
        dialog.getContentPane().add(btnEditSportsSession);

        JButton btnCancel = UIElementGenerator.createCancelButton(dialog);
        btnCancel.setBounds(861, 86, 117, 25);
        dialog.getContentPane().add(btnCancel);

		dialog.setVisible(true);
	}

    private ActionListener editButtonActionListener() {
        return null;
    }

	//TODO: Move this to be on a SwingWorker
	private void addTableToView() {
        idIndexMap = new ArrayList<>();

        String[] columnNames = Language.get().getString(StringNames.TBL_COLUMN_TABLES_LIST_CLIENTS).split(",");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable(model);
        scrollPane = new JScrollPane( table );
        scrollPane.setBounds(5,5,850,460);
        dialog.add( scrollPane, BorderLayout.CENTER );

        reloadData();
	}

    private void reloadData() {
        idIndexMap.clear();
        if(table == null || table.getModel() == null) {
            throw new IllegalStateException();
        }
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        //clear the rows
        model.setRowCount(0);
        for (Client cli: ClientDAO.get().getAll("name",true)) {
            idIndexMap.add(cli.getId());
            model.addRow(new Object[] {
                    cli.getName(),
                    cli.getCity(),
                    cli.getDistrict(),
                    cli.getStreet(),
                    cli.getStreetNo(),
                    cli.getMiscAddress(),
                    cli.getCountry(),
                    cli.getIban(),
                    cli.getPersonalnumber()
            });
        }
    }
}
