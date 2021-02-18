package gr.zeus;

import java.awt.*;
import javax.swing.*;

public class StatisticsWindow extends JFrame {

    /** With the class StatisticsWindow we create a new window for the user to see some statistics about the orders */

    private final int FRAME_WIDTH = 700;
    private final int FRAME_HEIGHT = 300;
    private JPanel statisticsWindowPanel;
    private JLabel logo, sumOrderNumberLabel, sumCostNoTaxLabel, sumCostWithTaxLabel, expensiveOrderIDLabel, cheapOrderIDLabel;

    StatisticsWindow(){
        super("Statistics");

        /** Create JPanel with GridLayout Manager */
        statisticsWindowPanel = new JPanel(new GridLayout(6,1));

        /**  Create elements and add them to Panel */
        createJLabels();
        addJLabelsToPanel();

        /** Adjust window properties */
        this.add(statisticsWindowPanel);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
    }

    /** Create JLabel */
    public void createJLabels() {
        logo = new JLabel("Zeus - Statistics", JLabel.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        sumOrderNumberLabel = new JLabel("Number of all orders: " + CalculateStatistics.getSumOrderNumber(), JLabel.CENTER);
        sumCostNoTaxLabel = new JLabel("Summary cost of all orders (Excludes Tax): " + CalculateStatistics.getSumCostNoTax() + " $", JLabel.CENTER);
        sumCostWithTaxLabel = new JLabel("Summary cost of all orders (Includes Tax): " + CalculateStatistics.getSumCostWithTax() + " $", JLabel.CENTER);
        expensiveOrderIDLabel = new JLabel("OrderID of the most expensive order: " + CalculateStatistics.getExpensiveOrderID(), JLabel.CENTER);
        cheapOrderIDLabel = new JLabel("OrderID of the cheapest order: " + CalculateStatistics.getCheapOrderID(), JLabel.CENTER);
    }

    /** Add JLabel to Panel */
    public void addJLabelsToPanel() {
        statisticsWindowPanel.add(logo);
        statisticsWindowPanel.add(sumOrderNumberLabel);
        statisticsWindowPanel.add(sumCostNoTaxLabel);
        statisticsWindowPanel.add(sumCostWithTaxLabel);
        statisticsWindowPanel.add(expensiveOrderIDLabel);
        statisticsWindowPanel.add(cheapOrderIDLabel);
    }
}
