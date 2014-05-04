package com.tudor.sdm.ui;

import com.tudor.sdm.Constants;
import com.tudor.sdm.Language;
import com.tudor.sdm.dao.TrainerDAO;
import com.tudor.sdm.entity.Trainer;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tudor on 04/05/14.
 * Multi purpose GUI for trainer management.
 * Should display a list of trainers, and provide text fields for editing the properties of a trainer.
 */
public class AddEditListTrainer {
    private final Logger log = Logger.getLogger(AddEditClient.class.getName());
    JDialog dialog;
    JTable tblTrainers;
    JTextField txtTrainerName;
    JTextField txtTrainerPhoneNumber;
    JButton btnSave;
    JButton btnCancel;
    JLabel lblTrainerName;
    JLabel lblTrainerPhoneNumber;
    List<Long> trainerIDs;
    String errorMessage;

    AddEditListTrainer() {
        log.debug("Initializing window...");
        dialog = new JDialog();
        dialog.setTitle(Language.get().getString(Constants.StringNames.WINDOW_TITLE_VIEW_ADD_EDIT_TRAINER));
        dialog.setSize(700, 500);
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());
        addComponents();
        dialog.setVisible(true);
    }

    private void addComponents() {
        addTable();
        addLabelsAndTextBoxes();
        addButtons();
        arrangeRightSide();
    }

    private void addTable() {
        log.debug("Started adding the table...");
        trainerIDs = new ArrayList<>();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(Language.get().getString(Constants.StringNames.TBL_COLUMN_TABLES_LIST_TRAINERS)
                .split(","));
        tblTrainers = new JTable(model);
        JScrollPane pane = new JScrollPane(tblTrainers, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        tblTrainers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tblTrainers.getSelectedRow();
                txtTrainerName.setText(
                        tblTrainers.getModel().getValueAt(selectedRow,0).toString()
                );
                txtTrainerPhoneNumber.setText(
                        tblTrainers.getModel().getValueAt(selectedRow,1).toString()
                );
            }
        });


        pane.setSize(300, 500);
        dialog.add(pane, BorderLayout.WEST);




        log.debug("Added the table to the panel...");
        reloadData();
    }

    /**
     * (Re)loads data from the database. Requires addTable to be called before to initialize objects.
     */
    private void reloadData() {
        log.debug("Reloading data from the database...");
        trainerIDs.clear();
        DefaultTableModel model = (DefaultTableModel) tblTrainers.getModel();
        model.setRowCount(0);
        for (Trainer t: TrainerDAO.get().getAll("name", true)) {
            trainerIDs.add(t.getId());
            model.addRow(
                    new Object[] {
                            t.getName(),
                            t.getPhoneNumber()
                    }
            );
        }
        log.debug("Done with loading the data...");
    }

    private void addLabelsAndTextBoxes() {
        lblTrainerName = new JLabel(Language.get().getString(Constants.StringNames.LBL_TRAINER_NAME));
        lblTrainerPhoneNumber = new JLabel(Language.get().getString(Constants.StringNames.LBL_TRAINER_PHONE_NUMBER));
        txtTrainerName = new JTextField();
        txtTrainerPhoneNumber = new JTextField();
        lblTrainerName.setSize(150,25);
        lblTrainerPhoneNumber.setSize(150,25);
        txtTrainerName.setSize(200, 25);
        txtTrainerPhoneNumber.setSize(200, 25);
    }

    private void addButtons() {
        btnSave = UIElementGenerator.createJButton(
                Language.get().getString(Constants.StringNames.BTN_GENERIC_SAVE_LABEL)
        );
        btnSave.addActionListener(saveAction());
        btnCancel = UIElementGenerator.createCancelButton(dialog);
    }

    /**
     * Depends on addLabelsAndTextBoxes and addButtons
     * TODO: Fix layout
     */
    private void arrangeRightSide() {
        JPanel panel = new JPanel();
        panel.setSize(400,120);
        GridLayout layout = new GridLayout(3,2,5,5);
        panel.setLayout(layout);
        panel.add(lblTrainerName);
        panel.add(txtTrainerName);
        panel.add(lblTrainerPhoneNumber);
        panel.add(txtTrainerPhoneNumber);
        panel.add(btnSave);
        panel.add(btnCancel);
        dialog.add(panel, BorderLayout.CENTER);
    }

    private boolean isInputValid() {
        if(txtTrainerName.getText().length() < 3) {
            errorMessage = Language.get().getString(Constants.StringNames.ERR_TRAINER_ADD_EDIT_NAME_INVALID);
            return false;
        }
        if(txtTrainerPhoneNumber.getText().length() < 3) {
            errorMessage = Language.get().getString(Constants.StringNames.ERR_TRAINER_ADD_EDIT_PHONE_NO_INVALID);
            return false;
        }
        return true;
    }

    private ActionListener saveAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputValid()) {
                    Trainer trainer = new Trainer.Builder().
                            name(txtTrainerName.getText()).
                            phoneNumber(txtTrainerPhoneNumber.getText())
                            .build();
                    TrainerDAO.get().add(trainer);
                    reloadData();
                } else {
                    new ErrorMessage(errorMessage);
                }
            }
        };
    }
}
