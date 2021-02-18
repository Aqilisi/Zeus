package gr.zeus;

import java.util.ArrayList;

public class ListAdmin {

    /** List admin is responsible for all functions about the data */

    private static ArrayList<Order> list = new ArrayList<>(0);
    private static int listIndex = -1;

    /** These constructors create odjects type Order */
    /** Constructor called by Open */
    ListAdmin(int appID_S, String orderID_S, String orderDate_S, String clientName_S, String itemName_S, int unitsCount_S, double netItemPrice_S, double taxPercentage_S) {
        list.add( new Order( appID_S, orderID_S, orderDate_S, clientName_S, itemName_S, unitsCount_S, netItemPrice_S, taxPercentage_S ) );
        listIndex++;
        MainWindow.refreshOrdersArea(listIndex);
        MainWindow.setSaveState(false);
        CalculateStatistics.calculateSumOrderNumber();
    }

    /** Constructor called by OrderWindow */
    ListAdmin(String orderID_S, String orderDate_S, String clientName_S, String itemName_S, String unitsCount_S, String netItemPrice_S, String taxPercentage_S) {
        list.add( new Order(orderID_S, orderDate_S, clientName_S, itemName_S, unitsCount_S, netItemPrice_S, taxPercentage_S ) );
        listIndex++;
        MainWindow.refreshOrdersArea(listIndex);
        MainWindow.setSaveState(false);
        CalculateStatistics.calculateSumOrderNumber();
    }


    /** Getters */
    public static int getListIndex() {
        return listIndex;
    }

    public static int getListAppID(int requestIndex){
        return list.get(requestIndex).getAppID();
    }
    public static String getListOrderID(int requestIndex){
        return list.get(requestIndex).getOrderID();
    }
    public static String getListOrderDate(int requestIndex){
        return list.get(requestIndex).getOrderDate();
    }
    public static String getListClientName(int requestIndex){
        return list.get(requestIndex).getClientName();
    }
    public static String getListItemName(int requestIndex){
        return list.get(requestIndex).getItemName();
    }
    public static int getListUnitsCount(int requestIndex){
        return list.get(requestIndex).getUnitsCount();
    }
    public static double getListNetItemPrice(int requestIndex){
        return list.get(requestIndex).getNetItemPrice();
    }
    public static double getListTaxPercentage(int requestIndex){
        return list.get(requestIndex).getTaxPercentage();
    }
}
