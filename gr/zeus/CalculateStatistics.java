package gr.zeus;

public class CalculateStatistics {

    /** The class CalculateStatistics we calculate some statistics about orders
        which then we pass to the class StatisticsWindow so it can display it to the user */

    private static int sumOrderNumber = 0;
    private static double sumCostNoTax = 0.0, sumCostWithTax = 0.0, expensiveOrderPrice = 0.0, cheapOrderPrice = Double.POSITIVE_INFINITY;
    private static String expensiveOrderID = "", cheapOrderID = "";

    /** Caclulate sum of all orders */
    public static void calculateSumOrderNumber() {
        sumOrderNumber++;
    }

    /** Calculate all costs before tax */
    public static void calculateSumCostNoTax(double netItemPrice) {
        sumCostNoTax += netItemPrice;
    }

    /** Calculate all costs after tax */
    public static void calculateSumCostWithTax(double netItemPrice, double taxPercentage) {
        sumCostWithTax += netItemPrice + netItemPrice*taxPercentage/100;
    }

    /** Find code of the μοστ expensive order */
    public static void calculateExpensiveOrder(String orderID, double netItemPrice, double taxPercentage) {
        if (expensiveOrderPrice < netItemPrice + netItemPrice*taxPercentage/100) {
            expensiveOrderPrice = netItemPrice + netItemPrice*taxPercentage/100;
            expensiveOrderID = orderID;
        }
    }

    /** Find code of the cheapest order */
    public static void calculateCheapOrder(String orderID, double netItemPrice, double taxPercentage) {
        if (cheapOrderPrice > netItemPrice + netItemPrice*taxPercentage/100) {
            cheapOrderPrice = netItemPrice + netItemPrice*taxPercentage/100;
            cheapOrderID = orderID;
        }
    }

    /** Getters */
    public static int getSumOrderNumber() {
        return sumOrderNumber;
    }

    public static double getSumCostNoTax() {
        return sumCostNoTax;
    }

    public static double getSumCostWithTax() {
        return sumCostWithTax;
    }

    public static String getExpensiveOrderID() {
        return expensiveOrderID;
    }

    public static String getCheapOrderID() {
        return cheapOrderID;
    }


}
