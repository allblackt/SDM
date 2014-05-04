package com.tudor.sdm.ui;

import javax.swing.*;

/**
 * Created by tudor on 02/05/14.
 */
public class ClientGroupList {
    private JDialog dialog;
    private JTable table;

    public ClientGroupList() {
        dialog = new JDialog();
        dialog.setModal(true);

        dialog.setVisible(true);
    }
}
