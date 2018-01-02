// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.actionbar;

import netscape.javascript.JSObject;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import lotus.notes.util.Bidi;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.lang.reflect.Method;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.ms.io.clientstorage.ClientStorageManager;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.MalformedURLException;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class ActionBar extends Applet implements ActionListener
{
    MenuPanel topPanel;
    boolean isInit;
    URL baseURL;
    int buttonPadding;
    int numLeftActions;
    int numRightActions;
    Image actionBGImage;
    Image borderImage;
    Graphics bg;
    int actionBGImagesHigh;
    int actionBGImagesWide;
    int actionBorderStyle;
    boolean reverseAlignment;
    public int borderStyle;
    public Color borderColor;
    public Insets borderIns;
    public Insets innerIns;
    public Insets outerIns;
    private static final int NUM_TOKENS = 8;
    static final int ON_MOUSE_OVER = 0;
    static final int ALWAYS = 1;
    static final int NEVER = 2;
    static final int NOTES = 3;
    static final String MENUSEPARATOR = "------------";
    private Insets insets;
    
    public ActionBar() {
        this.isInit = false;
        this.baseURL = null;
        this.buttonPadding = 0;
        this.numLeftActions = 0;
        this.numRightActions = 0;
        this.actionBGImage = null;
        this.borderImage = null;
        this.bg = null;
        this.actionBGImagesHigh = 1;
        this.actionBGImagesWide = 1;
        this.actionBorderStyle = 2;
        this.reverseAlignment = false;
        this.borderStyle = 0;
        this.borderColor = null;
        this.borderIns = new Insets(0, 0, 0, 0);
        this.innerIns = new Insets(0, 0, 0, 0);
        this.outerIns = new Insets(0, 0, 0, 0);
        this.insets = new Insets(0, 0, 0, 0);
    }
    
    public void init() {
        String s = System.getProperty("browser");
        if (s != null) {
            s = s.toLowerCase();
        }
        if (s != null && s.indexOf("lotus") == -1 && s.indexOf("netscape") == -1 && s.indexOf("plugin") == -1) {
            this.setInstalled();
        }
        try {
            final URL codeBase = this.getCodeBase();
            final int port = codeBase.getPort();
            String s2 = codeBase.getProtocol() + "://";
            try {
                final String file = codeBase.getFile();
                new URL(file.substring(1, file.length()));
                final String s3 = (port != -1) ? (codeBase.getHost() + ":" + port + codeBase.getFile()) : (codeBase.getHost() + codeBase.getFile());
                s2 += ((s3.indexOf("domjava") != -1) ? s3.substring(0, s3.lastIndexOf("domjava") - 1) : s3);
            }
            catch (Exception ex2) {
                if (codeBase.getHost() == null) {
                    s2 += "localhost";
                }
                else {
                    s2 += codeBase.getHost();
                }
                if (codeBase.getPort() != -1) {
                    s2 = s2 + ":" + String.valueOf(codeBase.getPort());
                }
            }
            this.baseURL = new URL(s2);
        }
        catch (MalformedURLException ex) {
            System.out.println("Exception occurred during initialization: " + ex);
        }
        this.setLayout(null);
        this.topPanel = new MenuPanel();
        this.getParameters();
        this.add(this.topPanel);
        this.topPanel.buttonPanel.calculateActionDimensions();
        this.topPanel.buttonPanel.addActionListener(this);
        this.topPanel.buttonPanelRight.addActionListener(this);
        this.isInit = true;
        this.invalidate();
        this.validate();
    }
    
    public Insets insets() {
        return new Insets(this.borderIns.top + this.innerIns.top + this.outerIns.top, this.borderIns.left + this.innerIns.left + this.outerIns.left, this.borderIns.bottom + this.innerIns.bottom + this.outerIns.bottom, this.borderIns.right + this.innerIns.right + this.outerIns.right);
    }
    
    public boolean setInstalled() {
        boolean b = false;
        int n = 0;
        final String parameter = this.getParameter("archive");
        if (parameter == null) {
            return true;
        }
        if (parameter.indexOf("actionbar") == -1) {
            return true;
        }
        try {
            PolicyEngine.assertPermission(PermissionID.CLIENTSTORE);
            n = ((new BufferedReader(new InputStreamReader(ClientStorageManager.openReadable("dominoapplets.txt"))).readLine().indexOf("_installed=true;") != -1) ? 1 : 0);
        }
        catch (Exception ex3) {}
        if (n != 0) {
            try {
                final Method[] methods = Class.forName("lotus.notes.util.Util").getMethods();
                Method method = null;
                for (int i = 0; i <= methods.length - 1; ++i) {
                    if (methods[i].getName().equals("setInstallCookie")) {
                        method = methods[i];
                        break;
                    }
                }
                if (method != null) {
                    final Object[] array = { "true", this, new Boolean(false) };
                    try {
                        method.invoke(this, array);
                        b = true;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        return b;
    }
    
    public void getParameters() {
        int int1 = 0;
        final String parameter = this.getParameter("bgColor");
        if (parameter != null) {
            final Color borderColor = new Color(this.decodeString(parameter));
            if (borderColor != null) {
                this.setBackground(borderColor);
                this.topPanel.setBackground(borderColor);
                this.borderColor = borderColor;
            }
        }
        final String parameter2 = this.getParameter("BorderColor");
        if (parameter2 != null) {
            final Color borderColor2 = new Color(this.decodeString(parameter2));
            if (borderColor2 != null) {
                this.borderColor = borderColor2;
            }
        }
        final String parameter3 = this.getParameter("BarBGImage");
        if (parameter3 != null) {
            Image image = this.topPanel.getImage(this.baseURL, parameter3);
            if (image != null) {
                try {
                    final Image image2 = this.createImage(image.getWidth(this), image.getHeight(this));
                    image2.getGraphics().drawImage(image, 0, 0, this);
                    image = image2;
                }
                catch (Exception ex2) {}
                this.topPanel.buttonPanel.bgImage = image;
                this.topPanel.buttonPanelRight.bgImage = image;
            }
        }
        final String parameter4 = this.getParameter("BarBGImageStyle");
        if (parameter4 != null) {
            try {
                this.topPanel.buttonPanel.bgImageStyle = Integer.parseInt(parameter4);
                this.topPanel.buttonPanelRight.bgImageStyle = Integer.parseInt(parameter4);
            }
            catch (Exception ex3) {}
        }
        final String parameter5 = this.getParameter("BorderStyle");
        if (parameter5 != null) {
            try {
                this.borderStyle = Integer.parseInt(parameter5);
            }
            catch (Exception ex4) {}
        }
        this.borderIns = this.parseBdrPrms(this.getParameter("BorderWidth"));
        this.innerIns = this.parseBdrPrms(this.getParameter("InnerWidth"));
        this.outerIns = this.parseBdrPrms(this.getParameter("OuterWidth"));
        final String parameter6 = this.getParameter("ButtonBGColor");
        if (parameter6 != null) {
            final Color color = new Color(this.decodeString(parameter6));
            if (color != null) {
                this.topPanel.buttonPanel.setButtonBackground(color);
                this.topPanel.buttonPanelRight.setButtonBackground(color);
            }
        }
        if (this.getParameter("ButtonTransparent") != null) {
            this.topPanel.buttonPanel.setButtonTransparent(true);
            this.topPanel.buttonPanelRight.setButtonTransparent(true);
        }
        final String parameter7 = this.getParameter("ButtonPadding");
        if (parameter7 != null) {
            this.buttonPadding = Integer.parseInt(parameter7);
        }
        final String parameter8 = this.getParameter("ButtonBorderStyle");
        if (parameter8 != null) {
            if (parameter8.equals("ONMOUSEOVER")) {
                this.actionBorderStyle = 0;
            }
            else if (parameter8.equals("ALWAYS")) {
                this.actionBorderStyle = 1;
            }
            else if (parameter8.equals("NOTES")) {
                this.actionBorderStyle = 3;
            }
        }
        if (this.actionBorderStyle == 0) {
            final String parameter9 = this.getParameter("ShowHinkyAlways");
            this.topPanel.buttonPanel.showHinky = (parameter9 != null);
            this.topPanel.buttonPanelRight.showHinky = (parameter9 != null);
        }
        final String parameter10 = this.getParameter("ButtonsRightAligned");
        this.topPanel.setButtonsRightAligned(parameter10 != null);
        this.reverseAlignment = (parameter10 != null);
        final String parameter11 = this.getParameter("ButtonTextJustify");
        if (parameter11 != null) {
            final ButtonPanel buttonPanel = this.topPanel.buttonPanel;
            final ButtonPanel buttonPanelRight = this.topPanel.buttonPanelRight;
            final int int2 = Integer.parseInt(parameter11);
            buttonPanelRight.textJustify = int2;
            buttonPanel.textJustify = int2;
        }
        int int3 = 12;
        final String parameter12 = this.getParameter("FontName");
        final String parameter13 = this.getParameter("FontStyle");
        if (parameter13.indexOf("U") != -1) {
            final ButtonPanel buttonPanel2 = this.topPanel.buttonPanel;
            final ButtonPanel buttonPanelRight2 = this.topPanel.buttonPanelRight;
            final boolean b = true;
            buttonPanelRight2.bunderline = b;
            buttonPanel2.bunderline = b;
        }
        if (parameter13.indexOf("S") != -1) {
            final ButtonPanel buttonPanel3 = this.topPanel.buttonPanel;
            final ButtonPanel buttonPanelRight3 = this.topPanel.buttonPanelRight;
            final boolean b2 = true;
            buttonPanelRight3.bstrike = b2;
            buttonPanel3.bstrike = b2;
        }
        final String parameter14 = this.getParameter("FontSize");
        if (parameter14 != null) {
            int3 = Integer.parseInt(parameter14);
        }
        this.topPanel.buttonPanel.setFont(this.findFont(parameter12, parameter13, int3));
        this.topPanel.buttonPanelRight.setFont(this.findFont(parameter12, parameter13, int3));
        final String parameter15 = this.getParameter("TextColor");
        if (parameter15 != null) {
            final Color color2 = new Color(this.decodeString(parameter15));
            this.topPanel.buttonPanel.setButtonForeground(color2);
            this.topPanel.buttonPanelRight.setButtonForeground(color2);
        }
        final String parameter16 = this.getParameter("ButtonHeight");
        if (parameter16 != null) {
            this.topPanel.buttonPanel.setButtonHeight(Integer.parseInt(parameter16));
            this.topPanel.buttonPanelRight.setButtonHeight(Integer.parseInt(parameter16));
        }
        final String parameter17 = this.getParameter("NumActions");
        if (parameter17 != null) {
            int1 = Integer.parseInt(parameter17);
        }
        if (int1 <= 0) {
            throw new IllegalArgumentException("Number of action buttons must be greater than zero");
        }
        final String parameter18 = this.getParameter("ButtonHeightType");
        if (parameter18 != null) {
            int n = 0;
            if (parameter18.equals("ABSOLUTE")) {
                n = 1;
            }
            else if (parameter18.equals("MINIMUM")) {
                n = 2;
            }
            else if (parameter18.equals("BGSIZE") || this.actionBorderStyle == 3) {
                n = 3;
            }
            this.topPanel.buttonPanel.setHeightType(n);
            this.topPanel.buttonPanelRight.setHeightType(n);
        }
        final String parameter19 = this.getParameter("ButtonWidthType");
        if (parameter19 != null) {
            int n2 = 0;
            if (parameter19.equals("BGSIZE")) {
                n2 = 3;
            }
            if (parameter19.equals("ABSOLUTE")) {
                n2 = 1;
                final String parameter20 = this.getParameter("ButtonWidth");
                if (parameter20 != null) {
                    final ButtonPanel buttonPanel4 = this.topPanel.buttonPanel;
                    final ButtonPanel buttonPanelRight4 = this.topPanel.buttonPanelRight;
                    final int int4 = Integer.parseInt(parameter20);
                    buttonPanelRight4.fixedButtonWidth = int4;
                    buttonPanel4.fixedButtonWidth = int4;
                }
            }
            this.topPanel.buttonPanel.setWidthType(n2);
            this.topPanel.buttonPanelRight.setWidthType(n2);
        }
        final String parameter21 = this.getParameter("ButtonBGImage");
        try {
            if (parameter21 != null) {
                if (parameter21.startsWith("/")) {
                    this.actionBGImage = this.topPanel.getImage(this.baseURL, parameter21);
                }
                else {
                    this.actionBGImage = this.topPanel.getImage(new URL(parameter21));
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Exception occurred while processing applet parameters: " + ex);
            ex.printStackTrace();
        }
        final String parameter22 = this.getParameter("ButtonBGImagesHigh");
        if (parameter22 != null) {
            this.actionBGImagesHigh = Integer.parseInt(parameter22);
        }
        final String parameter23 = this.getParameter("ButtonBGImagesWide");
        if (parameter23 != null) {
            this.actionBGImagesWide = Integer.parseInt(parameter23);
        }
        final Action[] array = new Action[int1];
        this.getActionParameters(array, 0, int1);
        this.topPanel.setActions(this.numLeftActions, this.numRightActions, array);
    }
    
    void getActionParameters(final Action[] array, final int n, final int n2) {
        try {
            for (int i = 1; i <= n2; ++i) {
                String s = null;
                Image image = null;
                boolean b = false;
                boolean b2 = false;
                boolean b3 = false;
                int int1 = 0;
                int int2 = 0;
                String s2;
                if (n == 0) {
                    s2 = this.getParameter("Action" + i);
                }
                else {
                    s2 = this.getParameter("Action" + n + "." + i);
                }
                if (s2 != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s2, ",");
                    if (s2.charAt(0) != ',') {
                        final int n3 = stringTokenizer.countTokens() - 8;
                        s = stringTokenizer.nextToken();
                        if (n3 > 0) {
                            for (int j = 0; j < n3; ++j) {
                                s = s + "," + stringTokenizer.nextToken();
                            }
                        }
                    }
                    final String nextToken = stringTokenizer.nextToken();
                    final int int3 = Integer.parseInt(stringTokenizer.nextToken());
                    if (n == 0) {
                        b = (Integer.parseInt(stringTokenizer.nextToken()) == 1);
                        int1 = Integer.parseInt(stringTokenizer.nextToken());
                        int2 = Integer.parseInt(stringTokenizer.nextToken());
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        b2 = (Integer.parseInt(stringTokenizer.nextToken()) == 1);
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        b3 = (Integer.parseInt(stringTokenizer.nextToken()) == 1);
                    }
                    if (n == 0) {
                        if (this.reverseAlignment) {
                            b2 = !b2;
                        }
                        if (b2) {
                            ++this.numRightActions;
                        }
                        else {
                            ++this.numLeftActions;
                        }
                    }
                    if (nextToken != null && !nextToken.equals(" ")) {
                        if (nextToken.startsWith("/")) {
                            image = this.topPanel.getImage(this.baseURL, nextToken);
                        }
                        else {
                            image = this.topPanel.getImage(new URL(nextToken));
                        }
                    }
                    if (b3) {
                        s = Bidi.BidiString(s);
                    }
                    array[i - 1] = new Action(this, s, image, (n == 0) ? this.actionBGImage : null, this.actionBGImagesHigh, this.actionBGImagesWide, int3, b, b2, int1, int2, b3);
                    if (int3 < 0) {
                        final int n4 = int3 * -1;
                        final Action[] array2 = new Action[n4];
                        this.getActionParameters(array2, i, n4);
                        this.add(array[i - 1].menu = this.createSubactionMenu(array2));
                        array[i - 1].hasSubactions = true;
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Exception occurred while processing action parameters: " + ex);
            ex.printStackTrace();
        }
    }
    
    private Insets parseBdrPrms(final String s) {
        final int[] array = { 0, 0, 0, 0 };
        if (s != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                for (int i = 0; i <= 3; ++i) {
                    array[i] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            catch (Exception ex) {}
        }
        return new Insets(array[0], array[2], array[1], array[3]);
    }
    
    private PopupMenu createSubactionMenu(final Action[] array) {
        Menu menu = null;
        if (array != null && array.length > 0) {
            menu = new PopupMenu();
            for (int i = 0; i < array.length; ++i) {
                if (array[i].index == 0) {
                    try {
                        menu.addSeparator();
                    }
                    catch (Exception ex) {
                        menu.add(new MenuItem("------------"));
                    }
                }
                else {
                    final MenuItem menuItem = new MenuItem(array[i].text);
                    menuItem.setActionCommand(String.valueOf(array[i].index));
                    menu.add(menuItem);
                }
            }
            menu.addActionListener(this);
        }
        return (PopupMenu)menu;
    }
    
    public void layout() {
        if (this.isInit) {
            final Insets insets = this.insets();
            final Dimension size = this.size();
            final int n = size.width - insets.left - insets.right;
            final int n2 = size.height - insets.top - insets.bottom;
            final int left = insets.left;
            final int top = insets.top;
            this.topPanel.resize(new Dimension(n, n2));
            this.topPanel.move(left, top);
        }
    }
    
    private Font findFont(String s, final String s2, int n) {
        int n2 = 0;
        String language = "";
        String property = "";
        try {
            property = System.getProperty("os.name");
        }
        catch (Exception ex) {}
        try {
            language = this.getLocale().getLanguage();
        }
        catch (Exception ex2) {}
        if (language != null && language.length() > 0 && language.equals("th") && property != null && property.indexOf("Win") != -1 && property.indexOf("98") != -1) {
            s = "DialogInput";
        }
        else if (s == null) {
            s = "Helvetica";
        }
        else if (s.indexOf("Helv") != -1) {
            s = "Helvetica";
        }
        else if (s.indexOf("Times") != -1) {
            s = "TimesRoman";
        }
        else if (s.indexOf("Sans") != -1) {
            s = "SansSerif";
        }
        else if (s.indexOf("Courier") != -1) {
            s = "Courier";
        }
        else {
            s = "Helvetica";
        }
        if (s2 != null) {
            final boolean b = s2.indexOf("B") != -1;
            final boolean b2 = s2.indexOf("I") != -1;
            if (b && b2) {
                n2 = 3;
            }
            else if (b) {
                n2 = 1;
            }
            else if (b2) {
                n2 = 2;
            }
        }
        n += (int)(n * 0.4);
        return new Font(s, n2, n);
    }
    
    int decodeString(final String s) throws NumberFormatException {
        if (s.startsWith("0x")) {
            return Integer.parseInt(s.substring(2), 16);
        }
        if (s.startsWith("#")) {
            return Integer.parseInt(s.substring(1), 16);
        }
        return Integer.parseInt(s, 16);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.topPanel.buttonPanel.reset();
        this.doAction(Integer.parseInt(actionEvent.getActionCommand()));
    }
    
    void doAction(final int n) {
        Class<?> forName;
        try {
            forName = Class.forName("netscape.javascript.JSObject");
        }
        catch (ClassNotFoundException ex2) {
            forName = null;
        }
        if (forName != null) {
            try {
                JSObject.getWindow((Applet)this).eval("doAction(" + n + ")");
            }
            catch (Exception ex) {
                System.out.println("Exception occurred while executing action: " + ex);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.borderStyle != 0) {
            final int left = this.outerIns.left;
            final int top = this.outerIns.top;
            final int width = this.size().width;
            final int height = this.size().height;
            final int n = width - this.outerIns.left - this.outerIns.right;
            final int n2 = height - this.outerIns.top - this.outerIns.bottom;
            final Color background = this.getBackground();
            if (this.borderImage != null) {
                this.borderImage.flush();
            }
            this.borderImage = this.createImage(width, height);
            (this.bg = this.borderImage.getGraphics()).setColor(background);
            this.bg.fillRect(0, 0, width, height);
            if (this.borderStyle <= 2) {
                this.bg.setColor(this.borderColor);
                this.bg.fillRect(left, top, n, this.borderIns.top);
                this.bg.fillRect(left, top, this.borderIns.left, n2);
                this.bg.fillRect(left, height - this.borderIns.bottom - this.outerIns.bottom, n, this.borderIns.bottom);
                this.bg.fillRect(width - this.borderIns.right - this.outerIns.right, top, this.borderIns.right, n2);
                if (this.borderStyle == 2) {
                    this.bg.setColor(background);
                    final int n3 = (this.borderIns.left >= 3) ? (this.borderIns.left / 3) : this.borderIns.left;
                    final int n4 = (this.borderIns.top >= 3) ? (this.borderIns.top / 3) : this.borderIns.top;
                    final int n5 = (this.borderIns.bottom >= 3) ? (this.borderIns.bottom / 3) : this.borderIns.bottom;
                    final int n6 = (this.borderIns.right >= 3) ? (this.borderIns.right / 3) : this.borderIns.right;
                    final int n7 = n - n6 - n3;
                    final int n8 = n2 - n4 - n5;
                    if (this.borderIns.top >= 3) {
                        this.bg.fillRect(left + n3, top + n4, n7, this.borderIns.top / 3);
                    }
                    if (this.borderIns.left >= 3) {
                        this.bg.fillRect(left + n3, top + n4, n3, n8);
                    }
                    if (this.borderIns.bottom >= 3) {
                        this.bg.fillRect(left + n3, height - n5 * 2 - this.outerIns.bottom, n7, n5);
                    }
                    if (this.borderIns.top >= 3) {
                        this.bg.fillRect(width - n6 * 2 - this.outerIns.right, top + n4, n6, n8);
                    }
                }
            }
            else if (this.borderStyle == 7 || this.borderStyle == 8) {
                final int n9 = (this.borderStyle == 8) ? 3 : 1;
                final int n10 = left + this.borderIns.left - 1;
                final int n11 = top + this.borderIns.top - 1;
                final int n12 = n - this.borderIns.right;
                final int n13 = n2 - this.borderIns.bottom;
                final int n14 = n12 + 1;
                final int n15 = n13 + 1;
                for (int i = n10; i < n14; i += n9) {
                    if (i % 2 != 0) {
                        this.bg.setColor(this.borderColor);
                    }
                    else {
                        this.bg.setColor(background);
                    }
                    if (i + n9 >= n14 && this.borderStyle == 8) {
                        this.bg.fillRect(i, n11, n14 - i, 1);
                        this.bg.fillRect(i, n13, n14 - i, 1);
                    }
                    else {
                        this.bg.fillRect(i, n11, n9, 1);
                        this.bg.fillRect(i, n13, n9, 1);
                    }
                }
                for (int j = n11; j < n15; j += n9) {
                    if (j % 2 != 0) {
                        this.bg.setColor(this.borderColor);
                    }
                    else {
                        this.bg.setColor(background);
                    }
                    if (j + n9 >= n15 && this.borderStyle == 8) {
                        this.bg.fillRect(n10, j, 1, n15 - j);
                        this.bg.fillRect(n12, j, 1, n15 - j);
                    }
                    else {
                        this.bg.fillRect(n10, j, 1, n9);
                        this.bg.fillRect(n12, j, 1, n9);
                    }
                }
            }
            graphics.drawImage(this.borderImage, 0, 0, this);
        }
    }
}
