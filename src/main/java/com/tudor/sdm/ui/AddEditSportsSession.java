package com.tudor.sdm.ui;

import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;
import com.tudor.sdm.dao.SportsSessionDAO;
import com.tudor.sdm.entity.SportsSession;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;


//TODO: Refactor this with builder pattern
public class AddEditSportsSession {

    private JDialog dialog;
	private SportsSession session;
	JLabel lblSportsSessionName;
	JLabel lblSportsSessionDuration;
	JLabel lblSportsSessionPrice;
	JSpinner spnSportsSessionDuration;
	JSpinner spnSportsSessionPrice;
	JButton btnSave;
	JButton btnCancel;
    JDialog parent;
    private static final Logger log = Logger.getLogger(AddEditSportsSession.class.getName());
	
	private JTextField txtSportsSessionName;

    private void initDialog() {
        log.debug("Initializing dialog...");
        this.dialog = new JDialog();

        dialog.setModal(true);
        dialog.setSize(500, 300);
        dialog.setTitle(Language.get().getString(StringNames.WINDOW_TITLE_ADD_EDIT_SPORTS_SESSIONS));
        dialog.setLocationRelativeTo(dialog.getParent());
        dialog.getContentPane().setLayout(null);

        addInputs();
        addButtons();
        log.debug("Finished initializing the dialog...");
    }

    private void addInputs() {
        log.debug("Adding the input fields...");
        lblSportsSessionName = new JLabel(Language.get().getString(StringNames.LBL_SPORTS_SESSION_NAME));
        lblSportsSessionName.setBounds(23, 24, 70, 15);
        dialog.getContentPane().add(lblSportsSessionName);

        lblSportsSessionDuration = new JLabel(Language.get().getString(StringNames.LBL_SPORTS_SESSION_DURATION));
        lblSportsSessionDuration.setBounds(23, 51, 70, 15);
        dialog.getContentPane().add(lblSportsSessionDuration);

        lblSportsSessionPrice = new JLabel(Language.get().getString(StringNames.LBL_SPORTS_SESSION_PRICE));
        lblSportsSessionPrice.setBounds(23, 78, 70, 15);
        dialog.getContentPane().add(lblSportsSessionPrice);

        txtSportsSessionName = new JTextField();
        txtSportsSessionName.setBounds(117, 22, 311, 19);
        dialog.getContentPane().add(txtSportsSessionName);
        txtSportsSessionName.setColumns(10);

        spnSportsSessionDuration = new JSpinner();
        spnSportsSessionDuration.setBounds(127, 49, 296, 20);
        dialog.getContentPane().add(spnSportsSessionDuration);

        spnSportsSessionPrice = new JSpinner();
        spnSportsSessionPrice.setBounds(127, 76, 296, 20);
        dialog.getContentPane().add(spnSportsSessionPrice);
        log.debug("Finished adding the inputs to the dialog...");
    }

    private void addButtons() {
        log.debug("Adding buttons to the dialog...");
        btnSave = new JButton(Language.get().getString(StringNames.BTN_GENERIC_SAVE_LABEL));
        btnSave.setBounds(167, 108, 117, 25);
        dialog.getContentPane().add(btnSave);
        btnSave.addActionListener(saveButtonActionListener());

        btnCancel = new JButton(Language.get().getString(StringNames.BTN_GENERIC_CANCEL_LABEL));
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

    public AddEditSportsSession(JDialog parent) {
        this(parent, null);
    }

	public AddEditSportsSession(JDialog parent, SportsSession session) {
		// Call the no args constructor to init the values
		this(session);
        //point to the parent
        this.parent = parent;
	}
	
	public AddEditSportsSession(SportsSession p_session) {
        initDialog();

        this.session = p_session;
        if (this.session != null) {
            // populate with data
            txtSportsSessionName.setText(this.session.getName());
            spnSportsSessionDuration.setValue(this.session.getDuration());
            spnSportsSessionPrice.setValue(this.session.getPrice());
        }

		dialog.setVisible(true);
	}

    private boolean isInputValid() {
		if (Long.parseLong(spnSportsSessionPrice.getValue().toString()) <= 0L) { return false; }
		if (Integer.parseInt(spnSportsSessionDuration.getValue().toString()) <= 0) { return false; }
		if (txtSportsSessionName.getText().length() <= 5) { return false; }
		return true;
	}

    public void addWindowAdapter(WindowAdapter adapter) {
        dialog.addWindowListener(adapter);
    }

    private ActionListener saveButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // on click
                if (isInputValid()) {
                    try {
                        if (session == null) {
                            session = new SportsSession.SportsSessionBuilder().name(txtSportsSessionName.getText())
                                    .duration(Integer.parseInt(spnSportsSessionDuration.getValue().toString()))
                                    .price(Long.parseLong(spnSportsSessionPrice.getValue().toString()))
                                    .build();
                            SportsSessionDAO.get().add(session);
                        } else {
                            session.setName(txtSportsSessionName.getText());
                            session.setDuration(Integer.parseInt(spnSportsSessionDuration.getValue().toString()));
                            session.setPrice(Long.parseLong(spnSportsSessionPrice.getValue().toString()));
                            SportsSessionDAO.get().save(session);
                        }
                        dialog.dispose();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        new ErrorMessage(e1.getMessage());
                    }
                } else {
                    new ErrorMessage(Language.get().getString(StringNames.ERR_INVALID_DATA_INPUTED_FOR_SPORTS_SESSION));
                }
            }
        };
    }
}
