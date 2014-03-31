package com.tudor.sdm.ui;

import com.tudor.sdm.Constants;
import com.tudor.sdm.Language;
import com.tudor.sdm.entity.Client;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

/**
 * Created by tudor on 3/30/14.
 */
public class AddEditClient {
    private JDialog dialog;
    private Client client;
    JButton btnSave;
    JButton btnCancel;
    JDialog parent;

    private JLabel nameLabel;
    private JLabel ibanLabel;
    private JLabel personalNumberLabel;
    private JLabel countryLabel;
    private JLabel cityLabel;
    private JLabel districtLabel;
    private JLabel miscAddressLabel;

    private static final Logger log = Logger.getLogger(AddEditClient.class.getName());

    public AddEditClient(JDialog parent) {
        this.parent = parent;
        initDialog();
    }

    private void initDialog() {
        log.debug("Initializing dialog...");

        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(500, 300);
        dialog.setTitle(Language.get().getString(Constants.StringNames.WINDOW_TITLE_ADD_EDIT_CLIENT));
        dialog.setLocationRelativeTo(dialog.getParent());
        dialog.getContentPane().setLayout(null);
        addButtons();

        dialog.setVisible(true);
        log.debug("Done initializing the dialog...");
    }

    private void addButtons() {
        log.debug("Adding buttons to the dialog...");
        btnSave = new JButton(Language.get().getString(Constants.StringNames.BTN_GENERIC_SAVE_LABEL));
        btnSave.setBounds(167, 108, 117, 25);
        dialog.getContentPane().add(btnSave);
        btnSave.addActionListener(saveButtonActionListener());

        btnCancel = new JButton(Language.get().getString(Constants.StringNames.BTN_GENERIC_CANCEL_LABEL));
        btnCancel.setBounds(306, 108, 117, 25);
        dialog.getContentPane().add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        log.debug("Finished adding the buttons to the dialog...");
    }

    private ActionListener saveButtonActionListener() {
        return null;
    }

    public void addWindowAdapter(WindowAdapter windowAdapter) {
        dialog.addWindowListener(windowAdapter);
    }
}
