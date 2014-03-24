package com.tudor.sdm.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tudor on 3/24/14.
 */
public class UIElementGenerator {
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

}
