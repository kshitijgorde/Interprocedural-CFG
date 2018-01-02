// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.util.Hashtable;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class Configurator
{
    private static Properties defaultprops;
    private static Properties props;
    private static HashMap hashParameters;
    private static String[][] settings;
    
    static {
        Configurator.settings = new String[][] { { "backgroundColor", "#e0e0e0", "String" }, { "completeURL", "", "URL" }, { "actionURL", "http://localhost/jupload/demo/JUpload.php", "URL" }, { "askAuthentification", "false", "Boolean" }, { "tagName", "uploadedFiles", "String" }, { "checkResponse", "true", "Boolean" }, { "showSuccessDialog", "true", "Boolean" }, { "imageFileFilter", "false", "Boolean" }, { "hideShowAll", "false", "Boolean" }, { "showPicturePreview", "true", "Boolean" }, { "addWindowTitle", "Add files", "String" }, { "imageURL", "", "String" }, { "labelFiles", "Files:", "String" }, { "labelBytes", "Bytes:", "String" }, { "labelAdd", "Add", "String" }, { "labelRemove", "Remove", "String" }, { "labelUpload", "Upload", "String" }, { "successDialogMessage", "Upload was reported to be successfull.", "String" }, { "successDialogTitle", "Upload successfull", "String" }, { "addToolTip", "Press to open file chooser to add files to upload queue", "String" }, { "removeToolTip", "Select files in queue and click this button to remove them from queue", "String" }, { "uploadToolTip", "Press to start uploading the files in the queue to the configured webserver", "String" }, { "useRecursivePaths", "false", "Boolean" }, { "debug", "true", "Boolean" }, { "checkJavaVersion", "true", "Boolean" }, { "checkJavaVersionGotoURL", "http://java.sun.com/j2se/downloads.html", "URL" }, { "browserCookie", "", "String" }, { "defaultAddDirectory", "", "String" }, { "usePutMethod", "false", "Boolean" }, { "maxFreeSpaceOnServer", "-1", "Long" }, { "maxFreeSpaceOnServerWarning", "File is too large for server", "String" }, { "maxFreeSpaceOnServerTitle", "File too large", "String" }, { "maxTotalRequestSize", "2000000", "Long" }, { "maxTotalRequestSizeWarning", "File too large for upload", "String" }, { "maxTotalRequestSizeTitle", "File too large", "String" }, { "customFileFilter", "false", "Boolean" }, { "customFileFilterDescription", "Customized", "String" }, { "customFileFilterExtensions", "gif,jpeg,jpg,png,bmp,tif", "String" } };
    }
    
    public static URL getActionURL() {
        String strValue = ((Hashtable<K, String>)Configurator.props).get("actionURL");
        URL url = null;
        if (getUsePutMethod() && !strValue.endsWith("/")) {
            strValue = String.valueOf(strValue) + "/";
        }
        try {
            url = new URL(strValue);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            try {
                url = new URL(((Hashtable<K, String>)Configurator.defaultprops).get("actionURL"));
            }
            catch (MalformedURLException e2) {
                e2.printStackTrace();
            }
        }
        return url;
    }
    
    public static String getAddToolTip() {
        return ((Hashtable<K, String>)Configurator.props).get("addToolTip");
    }
    
    public static String getAddWindowTitle() {
        return ((Hashtable<K, String>)Configurator.props).get("addWindowTitle");
    }
    
    public static boolean getAskAuthentificate() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("askAuthentification");
        return checkBoolean(strValue);
    }
    
    public static Color getBackgroundColor() {
        final String strBackgroundColor = ((Hashtable<K, String>)Configurator.props).get("backgroundColor");
        final Color colBackgroundColor = getColor(strBackgroundColor);
        return colBackgroundColor;
    }
    
    public static String getBrowserCookie() {
        return ((Hashtable<K, String>)Configurator.props).get("browserCookie");
    }
    
    public static boolean getCheckResponse() {
        final String strCheckResponse = ((Hashtable<K, String>)Configurator.props).get("checkResponse");
        return checkBoolean(strCheckResponse);
    }
    
    public static URL getCompleteURL() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("completeURL");
        URL url = null;
        try {
            url = new URL(strValue);
        }
        catch (MalformedURLException e) {
            debug("Configurator() getCompleteURL() could not get completeURL. error.");
            url = null;
        }
        return url;
    }
    
    public static boolean getCustomFileFilter() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("customFileFilter");
        return checkBoolean(strValue);
    }
    
    public static String getCustomFileFilterDescription() {
        return ((Hashtable<K, String>)Configurator.props).get("customFileFilterDescription");
    }
    
    public static String getCustomFileFilterExtensions() {
        return ((Hashtable<K, String>)Configurator.props).get("customFileFilterExtensions");
    }
    
    public static boolean getDebug() {
        if (Configurator.props == null) {
            return false;
        }
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("debug");
        return checkBoolean(strValue);
    }
    
    public static String getDefaultAddDirectory() {
        return ((Hashtable<K, String>)Configurator.props).get("defaultAddDirectory");
    }
    
    public static String getHTTPTagName() {
        return ((Hashtable<K, String>)Configurator.props).get("tagName");
    }
    
    public static boolean getHideShowAll() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("hideShowAll");
        return checkBoolean(strValue);
    }
    
    public static boolean getImageFileFilter() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("imageFileFilter");
        return checkBoolean(strValue);
    }
    
    public static String getImageURL() {
        return ((Hashtable<K, String>)Configurator.props).get("imageURL");
    }
    
    public static String getLabelAdd() {
        return ((Hashtable<K, String>)Configurator.props).get("labelAdd");
    }
    
    public static String getLabelBytes() {
        return ((Hashtable<K, String>)Configurator.props).get("labelBytes");
    }
    
    public static String getLabelFiles() {
        return ((Hashtable<K, String>)Configurator.props).get("labelFiles");
    }
    
    public static String getLabelRemove() {
        return ((Hashtable<K, String>)Configurator.props).get("labelRemove");
    }
    
    public static String getLabelUpload() {
        return ((Hashtable<K, String>)Configurator.props).get("labelUpload");
    }
    
    public static long getMaxFreeSpaceOnServer() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("maxFreeSpaceOnServer");
        long lValue = 0L;
        try {
            lValue = Long.parseLong(strValue);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lValue;
    }
    
    public static String getMaxFreeSpaceOnServerTitle() {
        return ((Hashtable<K, String>)Configurator.props).get("maxFreeSpaceOnServerTitle");
    }
    
    public static String getMaxFreeSpaceOnServerWarning() {
        return ((Hashtable<K, String>)Configurator.props).get("maxFreeSpaceOnServerWarning");
    }
    
    public static long getMaxTotalRequestSize() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("maxTotalRequestSize");
        long lValue = 0L;
        try {
            lValue = Long.parseLong(strValue);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lValue;
    }
    
    public static String getMaxTotalRequestSizeTitle() {
        return ((Hashtable<K, String>)Configurator.props).get("maxTotalRequestSizeTitle");
    }
    
    public static String getMaxTotalRequestSizeWarning() {
        return ((Hashtable<K, String>)Configurator.props).get("maxTotalRequestSizeWarning");
    }
    
    public static String getRemoveToolTip() {
        return ((Hashtable<K, String>)Configurator.props).get("removeToolTip");
    }
    
    public static boolean getShowPicturePreview() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("showPicturePreview");
        return checkBoolean(strValue);
    }
    
    public static boolean getShowSuccessDialog() {
        final String strShowSuccessDialog = ((Hashtable<K, String>)Configurator.props).get("ShowSuccessDialog");
        return checkBoolean(strShowSuccessDialog);
    }
    
    public static String getSuccessDialogMessage() {
        return ((Hashtable<K, String>)Configurator.props).get("successDialogMessage");
    }
    
    public static String getSuccessDialogTitle() {
        return ((Hashtable<K, String>)Configurator.props).get("successDialogTitle");
    }
    
    public static String getUploadToolTip() {
        return ((Hashtable<K, String>)Configurator.props).get("uploadToolTip");
    }
    
    public static boolean getUsePutMethod() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("usePutMethod");
        return checkBoolean(strValue);
    }
    
    public static boolean getUseRecursivePaths() {
        final String strValue = ((Hashtable<K, String>)Configurator.props).get("useRecursivePaths");
        return checkBoolean(strValue);
    }
    
    public static String helpPage() {
        String helpPage = "<applet \n  code=\"JUpload/startup.class\"\n  archive=\"JUpload.jar\"\n  width=\"450\"\n  height=\"200\"\n  alt=\"JUpload java applet for uploading multiple files at once with http post method\">\n <!-- Java Plug-In Options -->\n <param name=\"progressbar\" value=\"true\">\n <param name=\"boxmessage\" value=\"Loading JUpload Applet ...\">\n <param name=\"boxbgcolor\" value=\"#e0e0ff\">\n\n\n";
        for (int i = 0; i < Configurator.settings.length; ++i) {
            final String strKey = Configurator.settings[i][0];
            final String strValue = Configurator.settings[i][1];
            final String strType = Configurator.settings[i][2];
            helpPage = String.valueOf(helpPage) + "<PARAM NAME=\"" + strKey + "\" VALUE=\"" + strValue + "\"><!-- Type is " + strType + " -->\n";
        }
        helpPage = String.valueOf(helpPage) + "</applet>";
        return helpPage;
    }
    
    public static void readConfiguration(final startup applet) {
        Configurator.props = new Properties();
        Configurator.defaultprops = new Properties();
        debug("Configurator() there are " + Configurator.settings.length + " known parameters.");
        for (int i = 0; i < Configurator.settings.length; ++i) {
            final String strKey = Configurator.settings[i][0];
            final String strDefaultValue = Configurator.settings[i][1];
            final String strKeyType = Configurator.settings[i][2];
            final String strUserValue = applet.getParameter(strKey);
            debug("Configurator() key=[" + strKey + "] default=[" + strDefaultValue + "] uservalue=[" + strUserValue + "] type=[" + strKeyType + "]");
            ((Hashtable<String, String>)Configurator.defaultprops).put(strKey, strDefaultValue);
            if (strUserValue == null) {
                ((Hashtable<String, String>)Configurator.props).put(strKey, strDefaultValue);
            }
            else {
                ((Hashtable<String, String>)Configurator.props).put(strKey, strUserValue);
            }
        }
    }
    
    protected static void changeProperty(final String strKey, final String strValue) {
        ((Hashtable<String, String>)Configurator.props).put(strKey, strValue);
    }
    
    private static Color getColor(final String string) {
        Color col;
        try {
            final String strRed = string.substring(1, 3);
            final String strGreen = string.substring(3, 5);
            final String strBlue = string.substring(5, 7);
            final int iRed = Integer.parseInt(strRed, 16);
            final int iGreen = Integer.parseInt(strGreen, 16);
            final int iBlue = Integer.parseInt(strBlue, 16);
            col = new Color(iRed, iGreen, iBlue);
        }
        catch (NullPointerException e) {
            col = Color.white;
        }
        return col;
    }
    
    private static boolean checkBoolean(final String strCheckResponse) {
        return strCheckResponse != null && !strCheckResponse.equalsIgnoreCase("false") && !strCheckResponse.equalsIgnoreCase("0") && !strCheckResponse.equalsIgnoreCase("off") && !strCheckResponse.equalsIgnoreCase("no") && (strCheckResponse.equalsIgnoreCase("true") || strCheckResponse.equalsIgnoreCase("1") || strCheckResponse.equalsIgnoreCase("on") || (strCheckResponse.equalsIgnoreCase("yes") && false));
    }
    
    private static void debug(final String string) {
        System.out.println(string);
    }
}
