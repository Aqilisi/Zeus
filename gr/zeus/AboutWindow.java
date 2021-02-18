package gr.zeus;

import java.awt.*;
import javax.swing.*;

public class AboutWindow extends JFrame {

    /** The class AboutWindow creates a new windows that displays information about the app */

    private final int FRAME_WIDTH = 1200;
    private final int FRAME_HEIGHT = 550;
    private JPanel aboutWindowPanel;
    private JLabel logo, description, descriptionImage;
    private final String text = "Developer: Pappas Achilleas 18390010," +
            " Creation Period: 24/05/2020 - 28/05/2020," + " Created on: MacOS, Java version: JDK 1.8,  IDE: Intellij";

    AboutWindow(){
        super("About Zeus");
        /** Create panel with GridLayout Manager */
        aboutWindowPanel = new JPanel(new BorderLayout());

        /**  Create elements and add them to Panel */
        setJLabels();
        setFonts();
        addToPanel();

        /** Adjust window properties */
        this.add(aboutWindowPanel);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
    }

    /** Create JLabels */
    public void setJLabels() {
          logo = new JLabel("Zeus", JLabel.CENTER);
          description = new JLabel(text, JLabel.CENTER);
          Icon img = new ImageIcon("src/gr/zeus/zeus-screenshot.png");
          descriptionImage = new JLabel(img, JLabel.CENTER);
    }

    /** Set fonts */
    public void setFonts() {
          logo.setFont(new Font("Arial", Font.BOLD, 28));
          description.setFont(new Font("Arial", Font.ITALIC, 18));
    }

    /** Add to Panel */
    public void addToPanel() {
        aboutWindowPanel.add(logo, BorderLayout.NORTH);
        aboutWindowPanel.add(descriptionImage, BorderLayout.CENTER);
        aboutWindowPanel.add(description,  BorderLayout.SOUTH);
    }

}
