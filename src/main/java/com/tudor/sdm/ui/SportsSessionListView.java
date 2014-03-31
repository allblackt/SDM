package com.tudor.sdm.ui;

import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;
import com.tudor.sdm.dao.SportsSessionDAO;
import com.tudor.sdm.entity.SportsSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class SportsSessionListView {

    private JDialog     dialog;
    private	JTable		table;
    private JScrollPane scrollPane;
    private List<Long> idIndexMap = null;
    private ActionListener editButtonActionListener = setEditButtonActionListener();

    JLabel loadingAnimation = null;

	public SportsSessionListView(JFrame parent) {
        dialog = new JDialog(parent);
		dialog.setSize(new Dimension(900, 500));
		dialog.getContentPane().setLayout(null);

//        TODO: make loading animation work
//        loadingAnimation = UIElementGenerator.getLoadingAnimation();
//        loadingAnimation.setBounds(0,0,900,500);
//        dialog.add(loadingAnimation);

        dialog.setVisible(true);
		dialog.setTitle(Language.get().getString(StringNames.WINDOW_TITLE_LIST_SPORTS_SESSIONS));
		
		JButton btnAddSportsSession =  UIElementGenerator.createJButton(Language.get()
                .getString(StringNames.BTN_ADD_NEW_SPORTS_SESSION_LABEL));
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
		
		JButton btnEditSportsSession = UIElementGenerator.createJButton(Language.get()
                .getString(StringNames.BTN_EDIT_SPORTS_SESSION_LABEL));
        btnEditSportsSession.addActionListener(editButtonActionListener);
		btnEditSportsSession.setBounds(761, 49, 117, 25);
		dialog.getContentPane().add(btnEditSportsSession);

        JButton btnCancel = UIElementGenerator.createCancelButton(dialog);
        btnCancel.setBounds(761, 86, 117, 25);
        dialog.getContentPane().add(btnCancel);

        addTableToView();
	}

    private void addTableToView() {
        idIndexMap = new ArrayList<>();

        String[] columnNames = Language.get().getString(StringNames.TBL_COLUMN_TABLES_LIST_SPORTS_SESSIONS).split(",");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable(model);
        scrollPane = new JScrollPane( table );
        scrollPane.setBounds(5,5,750,460);
        dialog.add( scrollPane, BorderLayout.CENTER );

        reloadData();
    }

    private void reloadData() throws IllegalStateException{
        idIndexMap.clear();
        if(table == null || table.getModel() == null) {
            throw new IllegalStateException();
        }
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        //clear the rows
        model.setRowCount(0);

        //populate with data - get it sorted by name
        for (SportsSession sps : SportsSessionDAO.get().getAll("name", true)) {
            idIndexMap.add(sps.getId());
            model.addRow(new Object[]{
                    sps.getName(),
                    sps.getDuration(),
                    sps.getPrice()
            });
        }
    }

    //TODO: use this to hide the loading animation when it will be implemented
    private void hideLoadingAnimation() {
        if (loadingAnimation != null) {
            loadingAnimation.setVisible(false);
        }
    }

    private ActionListener setEditButtonActionListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SportsSession selectedSportsSession = SportsSessionDAO.get()
                                                        .getById(idIndexMap.get(table.getSelectedRow()));
                System.out.println(selectedSportsSession.toString());

                new AddEditSportsSession(dialog, selectedSportsSession)
                        .addWindowAdapter(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                super.windowClosed(e);
                                reloadData();
                            }
                        });
            }
        };
        return listener;
    }
}
