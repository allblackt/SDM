package com.tudor.sdm.ui;

import com.tudor.sdm.entity.Client;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.util.List;

/**
 * Created by tudor on 02/05/14.
 * Graphic UI with all the necessary fields to add / edit a client group
 */
public class AddEditClientGroup {
    JDialog dialog;
    JTextField txtGroupName;
    JComboBox cbTrainer;
    List<Client> clients;
    List<ComboBox> cobClients;
}
