package com.tudor.sdm.ui;

import com.tudor.sdm.Constants;
import com.tudor.sdm.Language;
import com.tudor.sdm.RegionalLayout;
import com.tudor.sdm.entity.Client;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

/**
 * Created by tudor on 3/30/14.
 * Used to display existing information about a client or add one
 */
public class AddEditClient {
    private JDialog dialog;
    private Client client;
    JButton btnSave;
    JButton btnCancel;
    JDialog parent;

    private JLabel lblNameLabel;
    private JLabel lblPhoneNumber;
    private JLabel lblIbanLabel;
    private JLabel lblPersonalNumberLabel;
    private JLabel lblCountryLabel;
    private JLabel lblCityLabel;
    private JLabel lblDistrictLabel;
    private JLabel lblMiscAddressLabel;

    private JTextField txtName;
    private JTextField txtPhoneNumber;
    private JTextField txtIban;
    private JTextField txtPersonalNumber;
    private JTextArea txtMiscAddress;
    private JScrollPane paneMiscAddress;
    private JComboBox cobCountry;
    private JComboBox cobCity;
    private JComboBox cobDistrict;

    private static final Logger log = Logger.getLogger(AddEditClient.class.getName());

    public AddEditClient(JDialog parent) {
        this.parent = parent;
        initDialog();
    }

    private void initDialog() {
        log.debug("Initializing dialog...");

        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(550, 400);
        dialog.setTitle(Language.get().getString(Constants.StringNames.WINDOW_TITLE_ADD_EDIT_CLIENT));
        dialog.setLocationRelativeTo(dialog.getParent());
        dialog.setLayout(new BorderLayout());

        addButtons();
        addLabels();
        addInputs();

        dialog.setVisible(true);
        log.debug("Done initializing the dialog...");
    }

    private void addLabels() {
        lblNameLabel = new JLabel(Language.get().getString(Constants.StringNames.LBL_CLIENT_NAME));
        lblPhoneNumber = new JLabel(Language.get().getString(Constants.StringNames.LBL_CLIENT_PHONE_NUMBER));
        lblIbanLabel = new JLabel(Language.get().getString(Constants.StringNames.LBL_CLIENT_IBAN));
        lblPersonalNumberLabel = new JLabel(Language.get().getString(Constants.StringNames.LBL_CLIENT_PERSONAL_NUMBER));
        lblCountryLabel = new JLabel(Language.get().getString(Constants.StringNames.LBL_CLIENT_COUNTRY));
        lblCityLabel = new JLabel(Language.get().getString(Constants.StringNames.LBL_CLIENT_CITY));
        lblDistrictLabel = new JLabel(Language.get().getString(Constants.StringNames.LBL_CLIENT_DISTRICT));
        lblMiscAddressLabel = new JLabel(Language.get().getString(Constants.StringNames.LBL_CLIENT_ADDRESS));

        lblNameLabel.setBounds(5,5,100,20);
        lblPhoneNumber.setBounds(5,35,100,20);
        lblCountryLabel.setBounds(5,65,100,20);
        lblDistrictLabel.setBounds(5,95,100,20);
        lblCityLabel.setBounds(5,125,100,20);
        lblMiscAddressLabel.setBounds(5,155,100,20);
        lblPersonalNumberLabel.setBounds(5,215,100,20);
        lblIbanLabel.setBounds(5,245,100,20);

        dialog.add(lblNameLabel);
        dialog.add(lblIbanLabel);
        dialog.add(lblPersonalNumberLabel);
        dialog.add(lblCountryLabel);
        dialog.add(lblCityLabel);
        dialog.add(lblDistrictLabel);
        dialog.add(lblMiscAddressLabel);
        dialog.add(lblPhoneNumber);
    }

    private void addInputs() {

        txtName = new JTextField();
        txtPhoneNumber = new JTextField();
        txtIban = new JTextField();
        txtPersonalNumber = new JTextField();
        txtMiscAddress = new JTextArea(5,80);
        paneMiscAddress = new JScrollPane();

        cobCountry = new JComboBox(RegionalLayout.get().getCountryList().toArray(new String[0]));
        cobCountry.setSelectedItem(RegionalLayout.get().getDefaultCountry());

        cobDistrict = new JComboBox(RegionalLayout.get().getDistrictLayout().keySet().toArray(new String[0]));
        cobDistrict.setSelectedItem(RegionalLayout.get().getDefaultDistrict());
        cobDistrict.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCities();
            }
        });

        cobCity = new JComboBox();
        loadCities();

        txtName.setBounds(120,5,250,20);
        txtPhoneNumber.setBounds(120,35,250,20);
        cobCountry.setBounds(120,65,250,20);
        cobDistrict.setBounds(120,95,250,20);
        cobCity.setBounds(120,125,250,20);
        txtMiscAddress.setBounds(120,155,250,50);
        txtPersonalNumber.setBounds(120,215,250,20);
        txtIban.setBounds(120,245,250,20);


        txtMiscAddress.setLineWrap(true);
        txtMiscAddress.setWrapStyleWord(true);
        txtMiscAddress.setBorder(txtName.getBorder());

        //TODO: put the txtMiscAddress field in a scroll pane
//        paneMiscAddress.setBounds(120,185,250,50);
//        paneMiscAddress.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        paneMiscAddress.setLocation(120,185);
//        paneMiscAddress.setViewportView(txtMiscAddress);
//        txtMiscAddress.setSize(200,50);
//        paneMiscAddress.add(txtMiscAddress);

        dialog.add(txtName);
        dialog.add(txtPhoneNumber);
        dialog.add(txtIban);
        dialog.add(txtPersonalNumber);
        dialog.add(cobCountry);
        dialog.add(cobDistrict);
        dialog.add(cobCity);
        dialog.add(txtMiscAddress);
        dialog.add(paneMiscAddress);

    }

    private void loadCities() {
        cobCity.removeAllItems();

        cobCity.addItem("");
        for (String city : RegionalLayout.get().getDistrictLayout().get(cobDistrict.getSelectedItem())) {
            cobCity.addItem(city);
        }
    }

    private void addButtons() {
        log.debug("Adding buttons to the dialog...");
        btnSave = new JButton(Language.get().getString(Constants.StringNames.BTN_GENERIC_SAVE_LABEL));
        btnSave.setBounds(420, 5, 117, 25);
        dialog.getContentPane().add(btnSave);
        btnSave.addActionListener(saveButtonActionListener());

        btnCancel = new JButton(Language.get().getString(Constants.StringNames.BTN_GENERIC_CANCEL_LABEL));
        btnCancel.setBounds(420, 35, 117, 25);
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
