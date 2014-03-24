package com.tudor.sdm.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;
import com.tudor.sdm.dao.SportsSessionDAO;
import com.tudor.sdm.entity.SportsSession;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SportsSessionListView {

    private JDialog     dialog;
    private	JTable		table;
    private JScrollPane scrollPane;

	public SportsSessionListView(JFrame parent) {
        dialog = new JDialog(parent);
		dialog.setSize(new Dimension(900, 500));
		dialog.getContentPane().setLayout(null);
		
		dialog.setTitle(Language.get().getString(StringNames.WINDOW_TITLE_LIST_SPORTS_SESSIONS));
		
		JButton btnAddSportsSession =  UIElementGenerator.createJButton(Language.get().getString(StringNames.BTN_ADD_NEW_SPORTS_SESSION_LABEL));
		btnAddSportsSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEditSportsSession(dialog).addWindowAdapter(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        reloadData();
                    }
                });
			}
		});
		btnAddSportsSession.setBounds(761, 12, 117, 25);
		dialog.getContentPane().add(btnAddSportsSession);
		
		JButton btnEditSportsSession = UIElementGenerator.createJButton(Language.get().getString(StringNames.BTN_EDIT_SPORTS_SESSION_LABEL));
		btnEditSportsSession.setBounds(761, 49, 117, 25);
		dialog.getContentPane().add(btnEditSportsSession);
		
        showExistingData();

		dialog.setVisible(true);
	}

    private void showExistingData() {
        String[] columnNames = Language.get().getString(StringNames.TBL_COLUMN_ABLES_LIST_SPORTS_SESSIONS).split(",");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        for (SportsSession sps : SportsSessionDAO.get().getAll()) {
            System.out.println(sps.toString());
            model.addRow(new Object[]{
                    sps.getName(),
                    sps.getDuration(),
                    sps.getPrice()
            });
        }

        table = new JTable(model);
        scrollPane = new JScrollPane( table );
        scrollPane.setBounds(5,5,750,460);
        dialog.add( scrollPane, BorderLayout.CENTER );
    }

    private void reloadData() throws IllegalStateException{
        if(table == null || table.getModel() == null) {
            throw new IllegalStateException();
        }
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        //clear the rows
        model.setRowCount(0);
        for (SportsSession sps : SportsSessionDAO.get().getAll()) {
            model.addRow(new Object[]{
                    sps.getName(),
                    sps.getDuration(),
                    sps.getPrice()
            });
        }
    }
}
