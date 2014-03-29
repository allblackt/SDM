package com.tudor.sdm.ui;

import com.tudor.sdm.Constants;
import com.tudor.sdm.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by tudor on 3/24/14.
 */
public class UIElementGenerator {

    private static JLabel loadingAnimation = null;

    /**
     * reates a button with font size 12 and margins set to 0,0,0,0. Title sent via parameter is also the text
     * for the buttin tooltip
     * @param title
     * To be used as button text and as button tooltip by default.
     * @return
     * The shiny button for you to use
     */
    public static JButton createJButton(String title) {
        JButton button = new JButton(title);
        button.setToolTipText(title);
        button.setFont(button.getFont().deriveFont(12));
        button.setMargin(new Insets(0,0,0,0));
        return button;
    }

    /**
     * Creates a cancel button which closes the current dialog
     * Method is based on @UIElementGenerator.createCancelButton
     * Default button text is StringNames.BTN_GENERIC_CANCEL_LABEL
     * @return
     * The cancel button which closes the dialog
     */
    public static JButton createCancelButton(final JDialog dialog) {
        JButton cancelButton = createJButton(
                Language.get().getString(Constants.StringNames.BTN_GENERIC_CANCEL_LABEL)
        );
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        return  cancelButton;
    }

    /**
     * Gets a JLabel with a loading animation GIF from img/ajax-loader.gif file
     * and the text specified by StringNames.LBL_LOADING_ANIMATION.
     * @return
     * The JLabel which you can show to symbolize a loading process.
     */
    public static JLabel getLoadingAnimation() {
        if (loadingAnimation == null) {
            ImageIcon loading = new ImageIcon(
                    UIElementGenerator.class.getClassLoader().getResource("img/ajax-loader.gif")
            );
            loadingAnimation = new JLabel(Language.get().getString(Constants.StringNames.LBL_LOADING_ANIMATION),
                    loading, SwingConstants.CENTER);
        }
        return loadingAnimation;
    }
}
