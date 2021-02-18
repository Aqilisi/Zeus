package gr.zeus;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow extends JFrame {

    /** MainWindow creates the central windows of the app */

    private final int FRAME_WIDTH = 1280;
    private final int FRAME_HEIGHT = 720;
    private JMenuBar menuBar;
    private JMenu fileMenu, actionMenu, logoMenu;
    private JMenuItem openFile, saveFile, saveAsFile, exitFile, newOrderAction, statisticsAction, aboutAction;
    private JTextField pathViewer;
    private JPanel pathViewerPanel, buttonsPanel, emptyWestPanel, emptyEastPanel;
    private ActionHandler handler;
    private JButton openButton, saveButton, saveAsButton, exitButton, newOrderButton, statisticsButton, aboutButton;
    private static JTextArea ordersArea;
    private String filePath = "";
    private static boolean saveState = true;

    /**Constructor which makes the main window */
    MainWindow() {
        super("Zeus Orders App");

        /** Initialize Panels */
        buttonsPanel = new JPanel(new FlowLayout());
        pathViewerPanel = new JPanel(new BorderLayout());
        emptyWestPanel = new JPanel();
        emptyEastPanel = new JPanel();
        emptyWestPanel.setPreferredSize(new Dimension(100,100));
        emptyEastPanel.setPreferredSize(new Dimension(100,100));

        /** Create elements and behaviors */
        new CreateElements();
        new CreateBehaviors();

        /** Add elements to Frame */
        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.add(buttonsPanel, BorderLayout.NORTH);
        this.add(ordersArea, BorderLayout.CENTER);
        this.add(emptyWestPanel,BorderLayout.WEST);
        this.add(emptyEastPanel, BorderLayout.EAST);
        this.add(pathViewerPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(true);
        this.setVisible(true);
    }

    /** Method that displays all the orders in the main window */
   public static void refreshOrdersArea(int requestIndex){
       try {
           ordersArea.append("\t\t" + ListAdmin.getListOrderID(requestIndex) + "\t");
           ordersArea.append(ListAdmin.getListOrderDate(requestIndex) + "\t");
           ordersArea.append(ListAdmin.getListClientName(requestIndex) + "\t");
           ordersArea.append(ListAdmin.getListItemName(requestIndex) + "\t");
           ordersArea.append("" + ListAdmin.getListUnitsCount(requestIndex) + "\t");
           ordersArea.append("" + ListAdmin.getListNetItemPrice(requestIndex) + " $" + "\t");
           ordersArea.append("" + ListAdmin.getListTaxPercentage(requestIndex) + " %" + "\t" + "\n");
       }
       catch(IndexOutOfBoundsException e){
           ExceptionList.IndexOutOfBoundsExceptionList();
       }
   }

    /** Με την saveState ελέγχουμε αν έχουν περαστεί καινούργια δεδομένα στην λίστα που δεν έχουν αποθηκευτεί σε αρχείο */
    /** Setter για την saveState */
    public static void setSaveState(boolean saveState){
        MainWindow.saveState = saveState;
    }


    class CreateElements {

        /** Κατασκευαστής για δημιουργία συστατικών */
        CreateElements() {
            /** Συστατικά Μενού */
            menuBar = new JMenuBar();
            createLogoMenu();
            createFileMenu();
            createActionMenu();
            /** Δημιουργία πεδίου PathViewer */
            createPathViewer();
            /** Δημιουργία Buttons */
            createButtons();
            /** Δημιουργία περιοχής εμφάνισεις παραγγελειών */
            createOrdersArea();
        }

        /** Create Menu Logo */
        public void createLogoMenu() {
            logoMenu = new JMenu("Zeus");
            logoMenu.setFont(new Font("Verdana", Font.BOLD, 14));
            menuBar.add(logoMenu);
        }

        /** Create Menu File */
        public void createFileMenu() {
            fileMenu = new JMenu("File");
            openFile = new JMenuItem("Open");
            saveFile = new JMenuItem("Save");
            saveAsFile = new JMenuItem("Save As");
            exitFile = new JMenuItem("Exit");

            menuBar.add(fileMenu);
            fileMenu.add(openFile);
            fileMenu.add(saveFile);
            fileMenu.add(saveAsFile);
            fileMenu.add(exitFile);
        }

        /** Create Menu Action */
        public void createActionMenu() {
            actionMenu = new JMenu("Action");
            newOrderAction = new JMenuItem("New Order");
            statisticsAction = new JMenuItem("Statistics");
            aboutAction = new JMenuItem("About");

            menuBar.add(actionMenu);
            actionMenu.add(newOrderAction);
            actionMenu.add(statisticsAction);
            actionMenu.add(aboutAction);
        }

        /** Create Path Viewer to see the file path */
        public void createPathViewer() {
            pathViewer = new JTextField();
            pathViewerPanel.add(pathViewer, BorderLayout.SOUTH);
        }

        /** Create buttons */
        public void createButtons() {
            openButton = new JButton("Open");
            saveButton = new JButton("Save");
            saveAsButton = new JButton("Save As");
            exitButton = new JButton("Exit");
            newOrderButton = new JButton("New Order");
            statisticsButton = new JButton("Statistics");
            aboutButton = new JButton("About");

            buttonsPanel.add(newOrderButton);
            buttonsPanel.add(openButton);
            buttonsPanel.add(saveButton);
            buttonsPanel.add(saveAsButton);
            buttonsPanel.add(statisticsButton);
            buttonsPanel.add(aboutButton);
            buttonsPanel.add(exitButton);
        }

        /** Create area to display orders */
        public void createOrdersArea() {
            ordersArea = new JTextArea();
            ordersArea.setEditable(false);
        }
    }


    class CreateBehaviors{

        /** Constructor for behavior creation */
        CreateBehaviors(){
            handler = new ActionHandler();
            createFileMenuBehavior();
            createActionMenuBehavior();
            createButtonsBehavior();
        }

        /** File behavior */
        public void createFileMenuBehavior() {
            /** Assign keyboard shortcuts for menu actions */
            openFile.addActionListener(handler);
            openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

            saveFile.addActionListener(handler);
            saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

            saveAsFile.addActionListener(handler);
            saveAsFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));

            exitFile.addActionListener(handler);
            exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        }

        /** Action behavior */
        public void createActionMenuBehavior(){
            newOrderAction.addActionListener(handler);
            newOrderAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

            statisticsAction.addActionListener(handler);
            statisticsAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

            aboutAction.addActionListener(handler);
            aboutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        }

        /** Buttons behavior */
        public void createButtonsBehavior(){
            openButton.addActionListener(handler);
            saveButton.addActionListener(handler);
            saveAsButton.addActionListener(handler);
            exitButton.addActionListener(handler);
            newOrderButton.addActionListener(handler);
            statisticsButton.addActionListener(handler);
            aboutButton.addActionListener(handler);
        }
    }


    class ActionHandler extends Component implements ActionListener {
        /** Bahevior handling */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == openFile || e.getSource() == openButton) {
                new Open();
            }
            else if (e.getSource() == saveFile || e.getSource() == saveButton){
               new Save();
            }

            else if (e.getSource() == saveAsFile || e.getSource() == saveAsButton) {
                new SaveAs();
            }
            else if (e.getSource() == exitFile || e.getSource() == exitButton) {
                /** Check if all the data is stored */
                if(!saveState) {
                    JOptionPane.showConfirmDialog(this,"Save before exitting?", "Save Files",1, 2);
                    new Save();
                }
                int result = JOptionPane.showConfirmDialog(this,"Exit app?", "Confirmation",0, 3);
                if(result == 0) {
                    System.exit(0);
                }
            }
            else if (e.getSource() == newOrderAction || e.getSource() == newOrderButton) {
                new OrderWindow();
            }
            else if (e.getSource() == statisticsAction || e.getSource() == statisticsButton){
                new StatisticsWindow();
            }
            else if (e.getSource() == aboutAction || e.getSource() == aboutButton){
                new AboutWindow();
            }
        }
    }


    class Open {
        /** Open option */
        Open(){
            openDialog();
        }

        /** Create Open window */
        public void openDialog(){
            JFileChooser open = new JFileChooser();
            /** Add filter to open only csv files */
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
            open.setFileFilter(filter);
            /** Check for Ok or Cancel */
            int result = open.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                /** Store path */
                filePath = open.getSelectedFile().getPath();
                pathViewer.setText("Current working path: " + filePath);
                Data.readData(filePath);
            }
            else if(result == JFileChooser.CANCEL_OPTION || result == JFileChooser.ERROR_OPTION){
                pathViewer.setText("Opening File action aborted.");
            }
        }
    }


    class Save {
        /** Save option */
        Save(){
            /** Check if file exists. If not it calls Save As constructor */
            if(filePath.equals("")){
               new SaveAs();
            }
            else{
                saveDialog();
            }
        }

        /** Call Data to write data to files */
        public void saveDialog() {
            Data.writeData(filePath);
            saveState = true;
        }
    }


    class SaveAs {
        /** Save As option */
        SaveAs(){
            /** Constructor gets called to open a new save as window */
            saveAsDialog();
        }

        /** Create SaveAs window */
        public void saveAsDialog(){
            JFileChooser save = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
            save.setFileFilter(filter);
            /** Check for Ok or Cancel */
            int result = save.showSaveDialog(null);
            if(result == JFileChooser.APPROVE_OPTION) {
                filePath = save.getSelectedFile().getPath();
                pathViewer.setText("Current working path: " + filePath);
                Data.writeData(filePath);
                saveState = true;
            }
            else if(result == JFileChooser.CANCEL_OPTION || result == JFileChooser.ERROR_OPTION){
                pathViewer.setText("Saving As action aborted.");
            }
        }
    }
}
