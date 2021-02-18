package gr.zeus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class OrderWindow extends JFrame {

    /** Class OrderWindow creates a new windows for the user to submit data */

    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 250;
    private JTextField orderIDField, orderDateField, clientNameField, itemNameField, unitsCountField, netItemPriceField, taxPercentageField;
    private JLabel orderIDLabel, orderDateLabel, clientNameLabel, itemNameLabel, unitsCountLabel, netItemPriceLabel, taxPercentageLabel;
    private JPanel orderWindowPanel, logoPanel, buttonPanel;
    private JButton confirmOrderButton;
    private OrderActionHandler handler;
    private JLabel logo;

    /** Constructor of new order window */
    OrderWindow(){
        super("New Order");

        /** Create JPanels */
        orderWindowPanel = new JPanel(new GridLayout(7,2));
        logoPanel = new JPanel();
        buttonPanel = new JPanel();

        /** Create fields and add them to Panels */
        new createFields();

        /** Adjust window properties */
        this.setLayout(new BorderLayout());
        this.add(logoPanel, BorderLayout.NORTH);
        this.add(orderWindowPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
    }

    /** Create fields */
    class createFields{

        createFields(){
            createJLabels();
            createJTextFields();
            createButton();
            addToPanels();
        }

        /** Create JLabels */
        public void createJLabels(){
            logo = new JLabel("Zeus - Add New Order");
            logo.setFont(new Font("Arial", Font.BOLD, 20));
            orderIDLabel = new JLabel("Order ID:", JLabel.CENTER);
            orderDateLabel = new JLabel("Order Date:", JLabel.CENTER);
            clientNameLabel = new JLabel("Client name:", JLabel.CENTER);
            itemNameLabel = new JLabel("Item Name:", JLabel.CENTER);
            unitsCountLabel = new JLabel("Units Count:", JLabel.CENTER);
            netItemPriceLabel = new JLabel("Net Item Price:", JLabel.CENTER);
            taxPercentageLabel = new JLabel("Tax Percentage:", JLabel.CENTER);
        }

        /** Create text fields for the user to type the data */
        public void createJTextFields(){
            orderIDField = new JTextField();
            orderDateField = new JTextField();
            clientNameField = new JTextField();
            itemNameField = new JTextField();
            unitsCountField = new JTextField();
            netItemPriceField = new JTextField();
            taxPercentageField = new JTextField();
        }

        /** Create button to submit data */
        public void createButton(){
            confirmOrderButton = new JButton("Confirm Order");
            handler = new OrderActionHandler();
            confirmOrderButton.addActionListener(handler);
        }

        /** Add elements to Panel */
        public void addToPanels(){
            logoPanel.add(logo);
            orderWindowPanel.add(orderIDLabel);
            orderWindowPanel.add(orderIDField);
            orderWindowPanel.add(orderDateLabel);
            orderWindowPanel.add(orderDateField);
            orderWindowPanel.add(clientNameLabel);
            orderWindowPanel.add(clientNameField);
            orderWindowPanel.add(itemNameLabel);
            orderWindowPanel.add(itemNameField);
            orderWindowPanel.add(unitsCountLabel);
            orderWindowPanel.add(unitsCountField);
            orderWindowPanel.add(netItemPriceLabel);
            orderWindowPanel.add(netItemPriceField);
            orderWindowPanel.add(taxPercentageLabel);
            orderWindowPanel.add(taxPercentageField);
            buttonPanel.add(confirmOrderButton);
        }

    }

    /** Assign behavior for the submit button */
    class OrderActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == confirmOrderButton) {

                /** Check for empty text fields */
                if(orderIDField.getText().equals("") || orderDateField.getText().equals("") || clientNameField.getText().equals("") ||
                        itemNameField.getText().equals("") || unitsCountField.getText().equals("") || netItemPriceField.getText().equals("") ||
                        taxPercentageField.getText().equals("")) {
                    JOptionPane.showMessageDialog(orderWindowPanel, "Cannot confirm order, you have to enter all details.","Error",0);
                }
                else {
                    /** Call list admin and close this window*/
                    new ListAdmin(orderIDField.getText(), orderDateField.getText(), clientNameField.getText(), itemNameField.getText(),
                            unitsCountField.getText(), netItemPriceField.getText(), taxPercentageField.getText());
                    dispose();
                }
            }
        }
    }

}
