// 
// Decompiled by Procyon v0.5.30
// 

package PopupNavigator;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuItem;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.PopupMenu;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

public class PopupNavigatorApplet extends Applet
{
    private boolean mouseIn;
    private boolean mouseDown;
    private static final short MaxSubParameters = 9;
    private static final short MaxMenuDepth = 10;
    private char delimiterChar;
    private char escapeChar;
    private String URLBase;
    private String defaultFontName;
    private String defaultFontStyle;
    private String defaultFontSize;
    private String defaultForegroundColor;
    private String defaultBackgroundColor;
    private String defaultFrame;
    private static final String smRecursive = "RECURSIVE";
    private static final String smItem = "ITEM";
    private static final String smNone = "NONE";
    private String selectionMode;
    private String selectionFontName;
    private String selectionFontStyle;
    private String selectionFontSize;
    private String menuActivation;
    private String statusText;
    private String menuPosition;
    private String missingURL;
    private String labelText;
    private Font labelFont;
    private Color labelForeground;
    private Color labelBackground;
    private String mouseoverLabelText;
    private Font mouseoverLabelFont;
    private Color mouseoverLabelForeground;
    private Color mouseoverLabelBackground;
    private String clickLabelText;
    private Font clickLabelFont;
    private Color clickLabelForeground;
    private Color clickLabelBackground;
    private static final String bsFlat = "FLAT";
    private static final String bsSemiflat = "SEMIFLAT";
    private static final String bsNormal = "NORMAL";
    private static final String bhHover = "HOVER";
    private static final String bhNohover = "NOHOVER";
    private String buttonStyle;
    private String buttonHover;
    private Image buttonImage;
    private Image mouseoverButtonImage;
    private Image clickButtonImage;
    private static final String bipAbsolute = "ABSOLUTE";
    private static final String bipCenter = "CENTER";
    private static final String bipLeft = "LEFT";
    private static final String bipRight = "RIGHT";
    private static final String bipTop = "TOP";
    private static final String bipBottom = "BOTTOM";
    private String buttonImagePosition;
    private String buttonImagePositionX;
    private String buttonImagePositionY;
    private String mouseoverButtonImagePosition;
    private String mouseoverButtonImagePositionX;
    private String mouseoverButtonImagePositionY;
    private String clickButtonImagePosition;
    private String clickButtonImagePositionX;
    private String clickButtonImagePositionY;
    PopupNavigatorMenuItem oldSelection;
    short[] paramCoord;
    boolean isStandalone;
    GridLayout gridLayout1;
    PopupMenu popupMenu;
    
    public String getParameter(final String s, final String s2) {
        String trim = this.isStandalone ? System.getProperty(s, s2) : this.getParameter(s);
        if (trim == null) {
            trim = s2;
        }
        if (trim != null) {
            trim = trim.trim();
        }
        return trim;
    }
    
    private Image getImageFromParam(final String s, final Image image) {
        final String parameter = this.getParameter(s, null);
        if (parameter == null) {
            return image;
        }
        if (parameter.length() == 0) {
            return null;
        }
        return this.getImage(this.encodeURL(parameter));
    }
    
    private boolean isCurrentDomain(final String s) {
        final String trim = s.trim();
        if (trim.startsWith("mailto:")) {
            return false;
        }
        if (trim.indexOf("://") >= 0) {
            final URL documentBase = this.getDocumentBase();
            final String protocol = documentBase.getProtocol();
            String concat;
            if (protocol.equalsIgnoreCase("mailto")) {
                concat = "mailto:";
            }
            else {
                concat = String.valueOf(protocol).concat(String.valueOf("://"));
            }
            return trim.toLowerCase().startsWith(String.valueOf(concat).concat(String.valueOf(documentBase.getHost())).toLowerCase());
        }
        return true;
    }
    
    private URL encodeURL(final String s) {
        try {
            URL url;
            if (this.URLBase.equalsIgnoreCase("CODEBASE")) {
                url = this.getCodeBase();
            }
            else if (this.URLBase.equalsIgnoreCase("DOCUMENT")) {
                url = this.getDocumentBase();
            }
            else {
                url = new URL(this.URLBase);
            }
            return new URL(url, s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    private String[] decodeParamValue(final String s, final String[] array) throws Exception {
        final String[] array2 = new String[9];
        if (array != null && array.length > 9) {
            throw new Exception("PopupNavigatorApplet.decodeParamValue() Defaults out of range.");
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        int j = 0;
        while (i < s.length()) {
            if (s.charAt(i) == this.escapeChar) {
                if (++i < s.length()) {
                    sb.append(s.charAt(i));
                }
            }
            else if (s.charAt(i) == this.delimiterChar) {
                if (sb.length() > 0) {
                    array2[j] = sb.toString();
                    sb = new StringBuffer();
                }
                else if (array != null && j < array.length) {
                    array2[j] = array[j];
                }
                ++j;
            }
            else {
                sb.append(s.charAt(i));
            }
            ++i;
        }
        if (sb.length() > 0) {
            array2[j++] = sb.toString();
        }
        else if (array != null && j < array.length) {
            array2[j] = array[j];
            ++j;
        }
        if (array != null) {
            while (j < array.length) {
                array2[j] = array[j];
                ++j;
            }
        }
        return array2;
    }
    
    private int strToFontStyle(final String s) {
        final String upperCase = s.toUpperCase();
        if (upperCase.equals("BOLD")) {
            return 1;
        }
        if (upperCase.equals("ITALIC")) {
            return 2;
        }
        if (upperCase.equals("BOLDITALIC") || upperCase.equals("ITALICBOLD")) {
            return 3;
        }
        return 0;
    }
    
    private Color strToColor(final String s) throws Exception {
        Color color = null;
        if (Character.isLetter(s.charAt(0))) {
            final String upperCase = s.trim().toUpperCase();
            if (upperCase.equals("BLACK")) {
                color = Color.black;
            }
            else if (upperCase.equals("BLUE")) {
                color = Color.blue;
            }
            else if (upperCase.equals("CYAN")) {
                color = Color.cyan;
            }
            else if (upperCase.equals("DARKGRAY")) {
                color = Color.darkGray;
            }
            else if (upperCase.equals("GRAY")) {
                color = Color.gray;
            }
            else if (upperCase.equals("GREEN")) {
                color = Color.green;
            }
            else if (upperCase.equals("LIGHTGRAY")) {
                color = Color.lightGray;
            }
            else if (upperCase.equals("MAGENTA")) {
                color = Color.magenta;
            }
            else if (upperCase.equals("ORANGE")) {
                color = Color.orange;
            }
            else if (upperCase.equals("PINK")) {
                color = Color.pink;
            }
            else if (upperCase.equals("RED")) {
                color = Color.red;
            }
            else if (upperCase.equals("WHITE")) {
                color = Color.white;
            }
            else if (upperCase.equals("YELLOW")) {
                color = Color.yellow;
            }
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " ", false);
            Integer n = null;
            Integer n2 = null;
            if (!stringTokenizer.hasMoreTokens()) {
                throw new Exception("PopupNavigator.strToColor() Bad color formatting.");
            }
            final Integer n3 = new Integer(stringTokenizer.nextToken());
            if (stringTokenizer.hasMoreTokens()) {
                n = new Integer(stringTokenizer.nextToken());
                if (stringTokenizer.hasMoreTokens()) {
                    n2 = new Integer(stringTokenizer.nextToken());
                }
            }
            if (n == null) {
                color = new Color(n3);
            }
            else {
                if (n2 == null) {
                    throw new Exception("PopupNavigator.strToColor() Bad color formatting - blue is missing.");
                }
                color = new Color(n3, n, n2);
            }
        }
        return color;
    }
    
    private boolean strToBoolean(final String s) {
        return s.equalsIgnoreCase("TRUE");
    }
    
    public PopupNavigatorApplet() {
        this.mouseIn = false;
        this.mouseDown = false;
        this.delimiterChar = ';';
        this.escapeChar = '\\';
        this.URLBase = "CODEBASE";
        this.defaultFontName = "Dialog";
        this.defaultFontStyle = "PLAIN";
        this.defaultFontSize = "12";
        this.defaultForegroundColor = "BLACK";
        this.defaultBackgroundColor = "LIGHTGRAY";
        this.defaultFrame = null;
        this.selectionMode = "RECURSIVE";
        this.selectionFontName = null;
        this.selectionFontStyle = "BOLD";
        this.selectionFontSize = null;
        this.menuActivation = "CLICK";
        this.statusText = null;
        this.menuPosition = "BOTTOM";
        this.missingURL = "ERROR";
        this.labelText = null;
        this.labelFont = null;
        this.labelForeground = null;
        this.labelBackground = null;
        this.mouseoverLabelText = null;
        this.mouseoverLabelFont = null;
        this.mouseoverLabelForeground = null;
        this.mouseoverLabelBackground = null;
        this.clickLabelText = null;
        this.clickLabelFont = null;
        this.clickLabelForeground = null;
        this.clickLabelBackground = null;
        this.buttonStyle = "NORMAL";
        this.buttonHover = "HOVER";
        this.buttonImage = null;
        this.mouseoverButtonImage = null;
        this.clickButtonImage = null;
        this.buttonImagePosition = "ABSOLUTE";
        this.buttonImagePositionX = "0";
        this.buttonImagePositionY = "0";
        this.mouseoverButtonImagePosition = null;
        this.mouseoverButtonImagePositionX = null;
        this.mouseoverButtonImagePositionY = null;
        this.clickButtonImagePosition = null;
        this.clickButtonImagePositionX = null;
        this.clickButtonImagePositionY = null;
        this.oldSelection = null;
        this.paramCoord = new short[10];
        this.isStandalone = false;
        this.gridLayout1 = new GridLayout();
        this.popupMenu = new PopupMenu();
    }
    
    private Font constructFont(final String[] array, final int n) {
        String s = "Dialog";
        int strToFontStyle = 0;
        int intValue = 12;
        if (array != null) {
            if (array[n] != null) {
                s = array[n];
            }
            if (array[n + 1] != null) {
                strToFontStyle = this.strToFontStyle(array[n + 1]);
            }
            if (array[n + 2] != null) {
                intValue = new Integer(array[n + 2]);
            }
        }
        return new Font(s, strToFontStyle, intValue);
    }
    
    private String formatErrorLabel(final String s, final String s2, final String s3, final String s4) {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf((s == null || s.length() == 0) ? "" : String.valueOf(s).concat(String.valueOf(" "))).concat(String.valueOf((s2 == null || s2.length() == 0) ? "" : String.valueOf(s2).concat(String.valueOf(" "))))).concat(String.valueOf("<PARAM NAME=\""))).concat(String.valueOf((s3 == null) ? "null" : s3))).concat(String.valueOf("\" VALUE=\""))).concat(String.valueOf((s4 == null) ? "null" : s4))).concat(String.valueOf("\">"));
    }
    
    private void selectItem(final MenuItem menuItem, final boolean b) {
        if (menuItem instanceof PopupNavigatorMenuItem) {
            final PopupNavigatorMenuItem popupNavigatorMenuItem = (PopupNavigatorMenuItem)menuItem;
            popupNavigatorMenuItem.setFont(new Font((this.selectionFontName != null) ? this.selectionFontName : popupNavigatorMenuItem.fontName, (this.selectionFontStyle != null) ? this.strToFontStyle(this.selectionFontStyle) : popupNavigatorMenuItem.fontStyle, (this.selectionFontSize != null) ? new Integer(this.selectionFontSize) : ((int)popupNavigatorMenuItem.fontSize)));
        }
        else if (menuItem instanceof PopupNavigatorMenu) {
            final PopupNavigatorMenu popupNavigatorMenu = (PopupNavigatorMenu)menuItem;
            popupNavigatorMenu.setFont(new Font((this.selectionFontName != null) ? this.selectionFontName : popupNavigatorMenu.fontName, (this.selectionFontStyle != null) ? this.strToFontStyle(this.selectionFontStyle) : popupNavigatorMenu.fontStyle, (this.selectionFontSize != null) ? new Integer(this.selectionFontSize) : ((int)popupNavigatorMenu.fontSize)));
        }
        if (b && this.selectionMode.equalsIgnoreCase("RECURSIVE") && menuItem.getParent() != null && menuItem.getParent() instanceof PopupNavigatorMenu) {
            this.selectItem((MenuItem)menuItem.getParent(), true);
        }
    }
    
    private void deselectItem(final MenuItem menuItem, final boolean b) {
        if (menuItem instanceof PopupNavigatorMenuItem) {
            final PopupNavigatorMenuItem popupNavigatorMenuItem = (PopupNavigatorMenuItem)menuItem;
            popupNavigatorMenuItem.setFont(new Font(popupNavigatorMenuItem.fontName, popupNavigatorMenuItem.fontStyle, popupNavigatorMenuItem.fontSize));
        }
        else if (menuItem instanceof PopupNavigatorMenu) {
            final PopupNavigatorMenu popupNavigatorMenu = (PopupNavigatorMenu)menuItem;
            popupNavigatorMenu.setFont(new Font(popupNavigatorMenu.fontName, popupNavigatorMenu.fontStyle, popupNavigatorMenu.fontSize));
        }
        if (b && this.selectionMode.equalsIgnoreCase("RECURSIVE") && menuItem.getParent() != null && menuItem.getParent() instanceof PopupNavigatorMenu) {
            this.deselectItem((MenuItem)menuItem.getParent(), true);
        }
    }
    
    private int loadMenu(final Menu menu, final int n) {
        int n2 = 0;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(this.paramCoord[i]);
            if (i + 1 < n) {
                sb.append(this.delimiterChar);
            }
        }
        if (sb.length() > 0) {
            sb.append(this.delimiterChar);
        }
        this.paramCoord[n] = 0;
        String parameter;
        do {
            final String concat = String.valueOf(sb.toString()).concat(String.valueOf(this.paramCoord[n]));
            parameter = this.getParameter(concat.toString(), null);
            if (parameter != null) {
                if (n2 == 0) {
                    n2 = 1;
                }
                int n3 = 0;
                String[] decodeParamValue;
                try {
                    decodeParamValue = this.decodeParamValue(parameter, null);
                }
                catch (Exception ex) {
                    decodeParamValue = new String[9];
                    decodeParamValue[0] = ex.toString();
                    n3 = 1;
                }
                final PopupNavigatorMenu popupNavigatorMenu = new PopupNavigatorMenu(parameter);
                final int loadMenu = this.loadMenu(popupNavigatorMenu, n + 1);
                if (loadMenu > 0) {
                    try {
                        popupNavigatorMenu.fontName = ((decodeParamValue[1] != null) ? decodeParamValue[1] : this.defaultFontName);
                        popupNavigatorMenu.fontStyle = new Integer(this.strToFontStyle((decodeParamValue[2] != null) ? decodeParamValue[2] : this.defaultFontStyle));
                        popupNavigatorMenu.fontSize = new Integer((decodeParamValue[3] != null) ? decodeParamValue[3] : this.defaultFontSize);
                        if (loadMenu == 2) {
                            this.selectItem(popupNavigatorMenu, false);
                            n2 = loadMenu;
                        }
                        else {
                            this.deselectItem(popupNavigatorMenu, false);
                        }
                    }
                    catch (Exception ex2) {
                        decodeParamValue = new String[9];
                        decodeParamValue[0] = ex2.toString();
                    }
                    popupNavigatorMenu.setLabel(decodeParamValue[0]);
                    menu.add(popupNavigatorMenu);
                }
                else {
                    final PopupNavigatorMenuItem popupNavigatorMenuItem = new PopupNavigatorMenuItem(parameter);
                    try {
                        if (decodeParamValue[0] == null || !decodeParamValue[0].equals("-")) {
                            if (decodeParamValue[1] == null) {
                                if (n3 == 0) {
                                    if (this.missingURL.equalsIgnoreCase("ERROR")) {
                                        decodeParamValue[0] = this.formatErrorLabel(null, "MISSING URL", concat.toString(), parameter);
                                        n3 = 1;
                                    }
                                    else if (this.missingURL.equalsIgnoreCase("DISABLE")) {
                                        popupNavigatorMenuItem.setEnabled(false);
                                    }
                                }
                            }
                            else {
                                if (this.isCurrentDomain(decodeParamValue[1])) {
                                    popupNavigatorMenuItem.url = this.encodeURL(decodeParamValue[1]);
                                    if (popupNavigatorMenuItem.url == null && n3 == 0) {
                                        decodeParamValue[0] = this.formatErrorLabel(null, "MALFORMED URL", concat.toString(), parameter);
                                        n3 = 1;
                                    }
                                }
                                else {
                                    popupNavigatorMenuItem.url_string = decodeParamValue[1];
                                }
                                if (decodeParamValue[2] == null) {
                                    popupNavigatorMenuItem.frame = this.defaultFrame;
                                }
                                else {
                                    popupNavigatorMenuItem.frame = decodeParamValue[2];
                                }
                            }
                            if (decodeParamValue[0] == null) {
                                decodeParamValue[0] = decodeParamValue[1];
                            }
                        }
                        popupNavigatorMenuItem.fontName = ((decodeParamValue[3] != null) ? decodeParamValue[3] : this.defaultFontName);
                        popupNavigatorMenuItem.fontStyle = new Integer(this.strToFontStyle((decodeParamValue[4] != null) ? decodeParamValue[4] : this.defaultFontStyle));
                        popupNavigatorMenuItem.fontSize = new Integer((decodeParamValue[5] != null) ? decodeParamValue[5] : this.defaultFontSize);
                        if ((this.selectionMode.equalsIgnoreCase("RECURSIVE") || this.selectionMode.equalsIgnoreCase("ITEM")) && popupNavigatorMenuItem.url != null && popupNavigatorMenuItem.url.equals(this.getDocumentBase())) {
                            this.selectItem(popupNavigatorMenuItem, false);
                            if (this.selectionMode.equalsIgnoreCase("RECURSIVE")) {
                                n2 = 2;
                            }
                        }
                        else {
                            this.deselectItem(popupNavigatorMenuItem, false);
                        }
                    }
                    catch (Exception ex3) {
                        decodeParamValue = new String[9];
                        decodeParamValue[0] = ex3.toString();
                        n3 = 1;
                    }
                    popupNavigatorMenuItem.setLabel(decodeParamValue[0]);
                    if (n3 != 0) {
                        popupNavigatorMenuItem.setEnabled(false);
                    }
                    menu.add(popupNavigatorMenuItem);
                }
                final short[] paramCoord = this.paramCoord;
                ++paramCoord[n];
            }
        } while (parameter != null);
        return n2;
    }
    
    public void init() {
        try {
            this.jbInit();
            this.delimiterChar = this.getParameter("DelimiterChar", String.valueOf("").concat(String.valueOf(this.delimiterChar))).charAt(0);
            this.escapeChar = this.getParameter("EscapeChar", String.valueOf("").concat(String.valueOf(this.escapeChar))).charAt(0);
            this.URLBase = this.getParameter("URLBase", this.URLBase);
            this.defaultFontName = this.getParameter("DefaultFontName", this.defaultFontName);
            this.defaultFontStyle = this.getParameter("DefaultFontStyle", this.defaultFontStyle);
            this.defaultFontSize = this.getParameter("DefaultFontSize", this.defaultFontSize);
            this.defaultForegroundColor = this.getParameter("DefaultForegroundColor", this.defaultForegroundColor);
            this.defaultFrame = this.getParameter("DefaultFrame");
            final String[] decodeParamValue = this.decodeParamValue(this.getParameter("Label", ""), new String[] { null, this.defaultFontName, this.defaultFontStyle, this.defaultFontSize, this.defaultForegroundColor, this.defaultBackgroundColor });
            if (decodeParamValue[0] == null) {
                if (this.getParameter("Label", null) == null) {
                    decodeParamValue[0] = "Navigate";
                }
                else {
                    decodeParamValue[0] = "";
                }
            }
            this.labelText = decodeParamValue[0];
            this.labelFont = this.constructFont(decodeParamValue, 1);
            this.labelForeground = this.strToColor(decodeParamValue[4]);
            this.labelBackground = this.strToColor(decodeParamValue[5]);
            final String[] decodeParamValue2 = this.decodeParamValue(this.getParameter("MouseoverLabel", ""), decodeParamValue);
            this.mouseoverLabelText = decodeParamValue2[0];
            this.mouseoverLabelFont = this.constructFont(decodeParamValue2, 1);
            this.mouseoverLabelForeground = this.strToColor(decodeParamValue2[4]);
            this.mouseoverLabelBackground = this.strToColor(decodeParamValue2[5]);
            final String[] decodeParamValue3 = this.decodeParamValue(this.getParameter("ClickLabel", ""), decodeParamValue2);
            this.clickLabelText = decodeParamValue3[0];
            this.clickLabelFont = this.constructFont(decodeParamValue3, 1);
            this.clickLabelForeground = this.strToColor(decodeParamValue3[4]);
            this.clickLabelBackground = this.strToColor(decodeParamValue3[5]);
            final String[] decodeParamValue4 = this.decodeParamValue(this.getParameter("ButtonStyle", ""), new String[] { this.buttonStyle, this.buttonHover });
            this.buttonStyle = decodeParamValue4[0];
            this.buttonHover = decodeParamValue4[1];
            final String[] decodeParamValue5 = this.decodeParamValue(this.getParameter("Selection", ""), new String[] { this.selectionMode, this.selectionFontName, this.selectionFontStyle, this.selectionFontSize });
            this.selectionMode = decodeParamValue5[0];
            this.selectionFontName = decodeParamValue5[1];
            this.selectionFontStyle = decodeParamValue5[2];
            this.selectionFontSize = decodeParamValue5[3];
            this.menuActivation = this.getParameter("MenuActivation", this.menuActivation);
            this.statusText = this.getParameter("StatusText", this.statusText);
            this.menuPosition = this.getParameter("MenuPosition", this.menuPosition);
            this.missingURL = this.getParameter("MissingURL", this.missingURL);
            this.buttonImage = this.getImageFromParam("Image", null);
            this.mouseoverButtonImage = this.getImageFromParam("MouseoverImage", this.buttonImage);
            this.clickButtonImage = this.getImageFromParam("ClickImage", this.mouseoverButtonImage);
            final String[] decodeParamValue6 = this.decodeParamValue(this.getParameter("ImagePosition", ""), new String[] { this.buttonImagePosition, this.buttonImagePositionX, this.buttonImagePositionY });
            this.buttonImagePosition = decodeParamValue6[0];
            this.buttonImagePositionX = decodeParamValue6[1];
            this.buttonImagePositionY = decodeParamValue6[2];
            final String[] decodeParamValue7 = this.decodeParamValue(this.getParameter("MouseoverImagePosition", ""), decodeParamValue6);
            this.mouseoverButtonImagePosition = decodeParamValue7[0];
            this.mouseoverButtonImagePositionX = decodeParamValue7[1];
            this.mouseoverButtonImagePositionY = decodeParamValue7[2];
            final String[] decodeParamValue8 = this.decodeParamValue(this.getParameter("ClickImagePosition", ""), decodeParamValue7);
            this.clickButtonImagePosition = decodeParamValue8[0];
            this.clickButtonImagePositionX = decodeParamValue8[1];
            this.clickButtonImagePositionY = decodeParamValue8[2];
            this.loadMenu(this.popupMenu, 0);
            this.enableEvents(16L);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setLayout(this.gridLayout1);
        this.add(this.popupMenu);
    }
    
    public void showPopupMenu() {
        int width;
        int height;
        if (this.menuPosition.equalsIgnoreCase("RIGHT")) {
            width = this.getBounds().width;
            height = 0;
        }
        else {
            width = 0;
            height = this.getBounds().height;
        }
        this.popupMenu.show(this, width, height);
    }
    
    public boolean action(final Event event, final Object o) {
        if (!(event.target instanceof PopupNavigatorMenuItem)) {
            return false;
        }
        final PopupNavigatorMenuItem popupNavigatorMenuItem = (PopupNavigatorMenuItem)event.target;
        switch (event.id) {
            case 1001: {
                this.navigate(popupNavigatorMenuItem);
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        switch (mouseEvent.getID()) {
            case 504: {
                this.mouseIn = true;
                if (this.statusText != null) {
                    this.showStatus(this.statusText);
                }
                if (this.menuActivation.equalsIgnoreCase("MOUSEOVER")) {
                    this.showPopupMenu();
                }
                this.repaint();
                break;
            }
            case 505: {
                if (this.statusText != null) {
                    this.showStatus("");
                }
                this.mouseIn = false;
                this.repaint();
                break;
            }
            case 501: {
                this.mouseDown = true;
                this.repaint();
                break;
            }
            case 502: {
                this.mouseDown = false;
                if (mouseEvent.getX() >= this.getBounds().x && mouseEvent.getY() >= this.getBounds().y && mouseEvent.getX() <= this.getBounds().x + this.getBounds().width && mouseEvent.getY() <= this.getBounds().y + this.getBounds().height) {
                    this.showPopupMenu();
                }
                else {
                    this.mouseIn = false;
                }
                this.repaint();
                break;
            }
        }
    }
    
    public String getAppletInfo() {
        return "PopupNavigatorApplet by Branko Dimitrijevic";
    }
    
    private void navigate(final PopupNavigatorMenuItem popupNavigatorMenuItem) {
        if (this.selectionMode.equalsIgnoreCase("RECURSIVE")) {
            if (this.oldSelection != null) {
                this.deselectItem(this.oldSelection, true);
            }
            this.selectItem(this.oldSelection = popupNavigatorMenuItem, true);
        }
        else if (this.selectionMode.equalsIgnoreCase("ITEM")) {
            if (this.oldSelection != null) {
                this.deselectItem(this.oldSelection, false);
            }
            this.selectItem(this.oldSelection = popupNavigatorMenuItem, false);
        }
        if (popupNavigatorMenuItem.url == null) {
            popupNavigatorMenuItem.url = this.encodeURL(popupNavigatorMenuItem.url_string);
        }
        if (popupNavigatorMenuItem.frame == null) {
            this.getAppletContext().showDocument(popupNavigatorMenuItem.url);
        }
        else {
            this.getAppletContext().showDocument(popupNavigatorMenuItem.url, popupNavigatorMenuItem.frame);
        }
    }
    
    private void paintLabelAndImage(final Graphics graphics, final Color color, final Color color2, final Font font, final String s, final Image image, final String s2, final String s3, final String s4) {
        graphics.setColor(color);
        graphics.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics(graphics.getFont());
        final int width = this.getBounds().width;
        final int height = this.getBounds().height;
        int stringWidth;
        if (s == null) {
            stringWidth = 0;
        }
        else {
            stringWidth = fontMetrics.stringWidth(s);
        }
        int n = (width - stringWidth) / 2;
        int n2 = (height - fontMetrics.getDescent() + fontMetrics.getAscent()) / 2;
        if (image != null) {
            final int width2 = image.getWidth(this);
            final int height2 = image.getHeight(this);
            if (width2 > 0 && height2 > 0) {
                int intValue;
                int intValue2;
                if (s2.equalsIgnoreCase("ABSOLUTE")) {
                    intValue = new Integer(s3);
                    if (intValue < 0) {
                        intValue = width + intValue - width2;
                    }
                    intValue2 = new Integer(s4);
                    if (intValue2 < 0) {
                        intValue2 = height + intValue2 - height2;
                    }
                }
                else if (s2.equalsIgnoreCase("CENTER")) {
                    intValue = (width - width2) / 2;
                    intValue2 = (height - height2) / 2;
                }
                else if (s2.equalsIgnoreCase("LEFT") || s2.equalsIgnoreCase("RIGHT")) {
                    final int intValue3 = new Integer(s3);
                    if (s2.equalsIgnoreCase("LEFT")) {
                        n += intValue3 / 2;
                        intValue = n - intValue3 - width2;
                    }
                    else {
                        n -= intValue3 / 2;
                        intValue = n + stringWidth + intValue3;
                    }
                    intValue2 = (height - height2) / 2;
                }
                else if (s2.equalsIgnoreCase("TOP") || s2.equalsIgnoreCase("BOTTOM")) {
                    final int intValue4 = new Integer(s3);
                    if (s2.equalsIgnoreCase("TOP")) {
                        n2 += intValue4 / 2;
                        intValue2 = n2 - fontMetrics.getAscent() - intValue4 - height2;
                    }
                    else {
                        n2 -= intValue4 / 2;
                        intValue2 = n2 + fontMetrics.getDescent() + intValue4;
                    }
                    intValue = (width - width2) / 2;
                }
                else {
                    intValue = 0;
                    intValue2 = 0;
                }
                graphics.drawImage(image, intValue, intValue2, this);
            }
        }
        if (s != null) {
            graphics.setColor(color2);
            graphics.drawString(s, n, n2);
        }
    }
    
    private void paint3dRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, Color color, Color color2, final boolean b) {
        if (!b) {
            final Color color3 = color;
            color = color2;
            color2 = color3;
        }
        graphics.setColor(color);
        graphics.drawLine(n, n2, n3, n2);
        graphics.drawLine(n, n2, n, n4);
        graphics.setColor(color2);
        graphics.drawLine(n3, n2, n3, n4);
        graphics.drawLine(n, n4, n3, n4);
    }
    
    private void paintThinBorder(final Graphics graphics, final boolean b) {
        this.paint3dRect(graphics, 0, 0, this.getBounds().width - 1, this.getBounds().height - 1, Color.white, Color.gray, b);
    }
    
    private void paintNormalBorder(final Graphics graphics, final boolean b) {
        this.paint3dRect(graphics, 1, 1, this.getBounds().width - 2, this.getBounds().height - 2, Color.lightGray, Color.gray, b);
        this.paint3dRect(graphics, 0, 0, this.getBounds().width - 1, this.getBounds().height - 1, Color.white, Color.darkGray, b);
    }
    
    private void paintThickBorder(final Graphics graphics, final boolean b) {
        this.paint3dRect(graphics, 2, 2, this.getBounds().width - 3, this.getBounds().height - 3, Color.lightGray, Color.gray, b);
        this.paint3dRect(graphics, 1, 1, this.getBounds().width - 2, this.getBounds().height - 2, Color.white, Color.darkGray, b);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.getBounds().width - 1, this.getBounds().height - 1);
    }
    
    private void paintButtonBorder(final Graphics graphics, final boolean b) {
        if (this.buttonStyle.equalsIgnoreCase("SEMIFLAT")) {
            this.paintThinBorder(graphics, b);
        }
        else if (this.buttonStyle.equalsIgnoreCase("NORMAL")) {
            this.paintNormalBorder(graphics, b);
        }
    }
    
    private void paintHoverBorder(final Graphics graphics, final boolean b) {
        if (this.buttonStyle.equalsIgnoreCase("FLAT")) {
            this.paintThinBorder(graphics, b);
        }
        else if (this.buttonStyle.equalsIgnoreCase("SEMIFLAT")) {
            this.paintNormalBorder(graphics, b);
        }
        else if (this.buttonStyle.equalsIgnoreCase("NORMAL")) {
            this.paintThickBorder(graphics, b);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.mouseDown) {
            this.paintLabelAndImage(graphics, this.clickLabelBackground, this.clickLabelForeground, this.clickLabelFont, this.clickLabelText, this.clickButtonImage, this.clickButtonImagePosition, this.clickButtonImagePositionX, this.clickButtonImagePositionY);
            if (this.buttonHover.equalsIgnoreCase("HOVER")) {
                this.paintHoverBorder(graphics, false);
            }
            else {
                this.paintButtonBorder(graphics, false);
            }
        }
        else if (this.mouseIn) {
            this.paintLabelAndImage(graphics, this.mouseoverLabelBackground, this.mouseoverLabelForeground, this.mouseoverLabelFont, this.mouseoverLabelText, this.mouseoverButtonImage, this.mouseoverButtonImagePosition, this.mouseoverButtonImagePositionX, this.mouseoverButtonImagePositionY);
            if (this.buttonHover.equalsIgnoreCase("HOVER")) {
                this.paintHoverBorder(graphics, true);
            }
            else {
                this.paintButtonBorder(graphics, true);
            }
        }
        else {
            this.paintLabelAndImage(graphics, this.labelBackground, this.labelForeground, this.labelFont, this.labelText, this.buttonImage, this.buttonImagePosition, this.buttonImagePositionX, this.buttonImagePositionY);
            this.paintButtonBorder(graphics, true);
        }
    }
}
