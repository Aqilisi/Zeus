package gr.zeus;

import java.io.*;

public class Data {

    /** Class Data reads data from a file or writes data to a file */

    private static String orderID = "", orderDate = "", clientName = "", itemName = "";
    private static int appID, unitsCount;
    private static double netItemPrice, taxPercentage;

    /** Read data from file */
    public static void readData(String filePath) {
        FileInputStream fin = null;
        DataInputStream din = null;
        int i = 0, fieldCount = 1;
        String temp = "";
        /** We read the value of every character so when we find ; the programm knows to change field.
         * We add every character to a temporary string and we either add it directly to the apropriete field
         * nor we first convert it to int or double and then add it */
        try {
            fin = new FileInputStream(filePath);
            din = new DataInputStream(fin);
            /** Read data until there are not more data to read */
            while (din.available() > 0) {
                /** FieldCount counts in a line which field we read */
                while (fieldCount != 9) {
                    switch (fieldCount) {
                        case 1:
                            i = din.read();
                            while (i != ';') {
                                temp += (char)i;
                                i = din.read();
                            }
                            /** To avoid NumberFormatException or IOException we have to remove some characters -
                             * parasites which are between every data character. All these have a  UTF value equal to \u0000 */
                            temp = temp.replace("\u0000", "");
                            fieldCount++;
                            appID = Integer.parseInt(temp);
                            break;
                        case 2:
                            temp = "";
                            i = din.read();
                            while (i != ';') {
                                orderID += (char)i;
                                i = din.read();
                            }
                            temp = temp.replace("\u0000", "");
                            fieldCount++;
                            break;
                        case 3:
                            temp = "";
                            i = din.read();
                            while (i != ';') {
                                orderDate += (char)i;
                                i = din.read();
                            }
                            temp = temp.replace("\u0000", "");
                            fieldCount++;
                            break;
                        case 4:
                            temp = "";
                            i = din.read();
                            while (i != ';') {
                                clientName += (char)i;
                                i = din.read();
                            }
                            temp = temp.replace("\u0000", "");
                            fieldCount++;
                            break;
                        case 5:
                            temp = "";
                            i = din.read();
                            while (i != ';') {
                                itemName += (char)i;
                                i = din.read();
                            }
                            temp = temp.replace("\u0000", "");
                            fieldCount++;
                            break;
                        case 6:
                            temp = "";
                            i = din.read();
                            while (i != ';') {
                                temp += (char)i;
                                i = din.read();
                            }
                            temp = temp.replace("\u0000", "");
                            fieldCount++;
                            unitsCount = Integer.parseInt(temp);
                            break;
                        case 7:
                            temp = "";
                            i = din.read();
                            temp = "";
                            /** When you find a , (comma) convert it to . (dot) */
                            while (i != ';') {
                                if (i == ',') {
                                    i = '.';
                                }
                                temp += (char)i;
                                i = din.read();
                            }
                            temp = temp.replace("\u0000", "");
                            fieldCount++;
                            netItemPrice = Double.parseDouble(temp);
                            break;
                        case 8:
                            temp = "";
                            i = din.read();
                            /** Check if we find the end */
                            while ((i != '\n') && (i != '\r') && (i != -1)) {
                                /** When you find a , (comma) convert it to . (dot) */
                                if (i == ',') {
                                    i = '.';
                                }
                                temp += (char)i;
                                i = din.read();
                            }
                            temp = temp.replace("\u0000", "");
                            fieldCount++;
                            taxPercentage = Double.parseDouble(temp);
                            break;
                        default:
                            break;

                    }
                }
                /** Call list admin to place data in the list */
                /** List admin gets called for every line that gets read */
                new ListAdmin(appID, orderID, orderDate, clientName, itemName, unitsCount, netItemPrice, taxPercentage);
                /** Restore fields to read next line */
                fieldCount = 1;
                temp = "";
                orderID = "";
                orderDate = "";
                clientName = "";
                itemName = "";
                unitsCount = 0;
                netItemPrice = 0.0;
                taxPercentage = 0.0;
            }
        } catch (NumberFormatException e) {
            ExceptionList.NumberFormatExceptionList();
        } catch (FileNotFoundException e) {
            ExceptionList.FileNotFoundExceptionList();
        } catch (IOException e) {
            ExceptionList.IOExceptionList();
        } finally {
            try {
                din.close();
            } catch (IOException e) {
                ExceptionList.IOExceptionList();
            }
        }
    }

    /** Write data to file */
    public static void writeData(String filePath){
        FileOutputStream fout = null;
        DataOutputStream dout = null;
        /** Ask list admin how many object type Order exist in the list */
        /** List admin returns one index */
        int requestIndex = ListAdmin.getListIndex();
        try {
            fout = new FileOutputStream(filePath);
            dout = new DataOutputStream(fout);
            /** Until the index becomes zero we ask list admin to return all fields for every object */
            while (requestIndex >= 0) {
                    dout.writeChars(Integer.toString( ListAdmin.getListAppID(requestIndex) ) );
                    dout.writeChar(';');
                    dout.writeChars(ListAdmin.getListOrderID(requestIndex));
                    dout.writeChar(';');
                    dout.writeChars(ListAdmin.getListOrderDate(requestIndex));
                    dout.writeChar(';');
                    dout.writeChars(ListAdmin.getListClientName(requestIndex));
                    dout.writeChar(';');
                    dout.writeChars(ListAdmin.getListItemName(requestIndex));
                    dout.writeChar(';');
                    dout.writeChars(Integer.toString( ListAdmin.getListUnitsCount(requestIndex) ));
                    dout.writeChar(';');

                    /** Change . (dot) to , (comma) */
                    /** Converts double to string and then check each character separately
                     * in order to search for the . (dot) and convert it to , (comma) */
                    String str1 = Double.toString( ListAdmin.getListNetItemPrice(requestIndex));
                    for(int z=0; z<str1.length(); z++){
                        char ch = str1.charAt(z);
                        if(ch == '.') {
                            ch=',';
                        }
                        dout.writeChar(ch);
                    }
                    dout.writeChar(';');
                    String str2 = Double.toString( ListAdmin.getListNetItemPrice(requestIndex));
                    for(int z=0; z<str2.length(); z++){
                        char ch = str2.charAt(z);
                        if(ch == '.') {
                            ch=',';
                        }
                        dout.writeChar(ch);
                    }
                    dout.writeChar('\n');
                    requestIndex--;
            }
        }
        catch(FileNotFoundException e) {
            ExceptionList.FileNotFoundExceptionList();
        }
        catch(IOException e){
            ExceptionList.IOExceptionList();
        }
        finally {
            try {
                dout.close();
            } catch (IOException e) {
                ExceptionList.IOExceptionList();
            }
        }
    }

}
