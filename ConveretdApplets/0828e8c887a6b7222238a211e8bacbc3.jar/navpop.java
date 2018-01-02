import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Event;
import java.net.MalformedURLException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import java.awt.Point;
import java.awt.Window;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import netscape.javascript.JSObject;
import java.applet.AppletContext;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class navpop extends Applet
{
    Color menuBackgroundClr;
    Color menuHighlightClr;
    Color menuTxtClr;
    Color menuTxtHighlightClr;
    Color menuTitleBarClr;
    Color menuTitleTxtClr;
    Color tickerTxtClr;
    Color tickerBackgroundClr;
    Font menuFont;
    Font menuTitleFont;
    String menuList;
    String buttonName;
    float htFactor;
    String defaultFrame;
    int posX;
    int posY;
    int enableClose;
    String menuTitle;
    Image menuTitleIcon;
    Image buttonImg;
    String tickerString;
    Dimension screen_res;
    Thread killMenus;
    nodeClass[] Node;
    Frame F;
    FontMetrics menuFontSize;
    FontMetrics titleFontSize;
    int iconHt;
    int titleIconHt;
    int nodeSelected;
    boolean menuReady;
    menuClass firstMenu;
    AppletContext ac;
    JSObject win;
    int warningBox;
    
    public void stop() {
        this.destroy();
    }
    
    public void closeAllMenus() {
        if (this.firstMenu != null) {
            this.firstMenu.close();
        }
        this.firstMenu = null;
        if (this.F != null) {
            this.F.dispose();
        }
        this.F = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.menuReady) {
            if (this.buttonName.startsWith("pic:")) {
                graphics.drawImage(this.buttonImg, 0, 0, this);
            }
            else {
                final int n = Integer.parseInt(this.getParameter("menuTxtSize")) + 4;
                final int n2 = n * 15 / 10;
                final int n3 = n / 2;
                final int n4 = this.menuFontSize.stringWidth(this.buttonName) + 2 * n3;
                graphics.setColor(this.menuBackgroundClr);
                graphics.fill3DRect(0, 0, n4, n2, true);
                graphics.setColor(this.menuTxtClr);
                graphics.setFont(this.menuFont);
                graphics.drawString(this.buttonName, n3, n2 * 70 / 100);
            }
        }
    }
    
    public navpop() {
        this.posX = 0;
        this.posY = 0;
        this.nodeSelected = -1;
        this.menuReady = false;
    }
    
    public void destroy() {
        this.closeAllMenus();
    }
    
    public void startMenus() {
        (this.F = new Frame(this.menuTitle)).setLocation(-10000, -10000);
        this.F.setSize(0, 0);
        this.F.setVisible(true);
        final Window window = new Window(this.F);
        window.setLocation(-12000, -12000);
        window.setSize(0, 0);
        window.show();
        this.warningBox = window.getInsets().bottom + 1;
        window.dispose();
        this.firstMenu = new menuClass(this.Node[0].childList, new Point(this.posX, this.posY), this.menuTitle, null);
    }
    
    public void start() {
        this.posX = Integer.valueOf(this.getParameter("X"));
        this.posY = Integer.valueOf(this.getParameter("Y"));
        this.closeAllMenus();
        if (this.enableClose != 2) {
            this.startMenus();
        }
    }
    
    public void setUpNodes() {
        final Vector vector = new Vector<String>();
        try {
            final URL url = new URL(this.getDocumentBase(), this.menuList);
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), "|");
                this.menuTitle = stringTokenizer.nextElement().toString();
                if (stringTokenizer.hasMoreElements()) {
                    this.menuTitleIcon = this.getImage(this.getDocumentBase(), stringTokenizer.nextElement().toString()).getScaledInstance(this.titleIconHt, this.titleIconHt, 4);
                }
                final String line = bufferedReader.readLine();
                if (!line.startsWith(":")) {
                    this.tickerString = line;
                }
                else {
                    vector.addElement(line);
                }
                String line2;
                do {
                    line2 = bufferedReader.readLine();
                    if (line2 != null && line2.startsWith(":")) {
                        vector.addElement(line2);
                    }
                } while (line2 != null);
            }
            catch (IOException ex) {}
        }
        catch (MalformedURLException ex2) {}
        this.Node = new nodeClass[vector.size() + 1];
        final int[] array = new int[this.Node.length];
        this.Node[0] = new nodeClass("Root");
        array[0] = 0;
        for (int i = 0; i < vector.size(); ++i) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(vector.elementAt(i).toString(), "|");
            String string;
            int n;
            for (string = stringTokenizer2.nextElement().toString(), n = 0; string.charAt(n) == ':' && n < string.length() - 1; ++n) {}
            if (n > 0 && string.charAt(n) != ':') {
                array[i + 1] = n;
            }
            else {
                array[i + 1] = -1;
            }
            if (array[i + 1] > 0) {
                this.Node[i + 1] = new nodeClass(string.substring(array[i + 1]));
            }
            if (stringTokenizer2.hasMoreElements() && !this.Node[i + 1].Name.startsWith("-")) {
                final String string2 = stringTokenizer2.nextElement().toString();
                if (string2.startsWith("javascript:")) {
                    this.Node[i + 1].jscript = string2.substring(11);
                }
                else if (!string2.startsWith("~")) {
                    try {
                        this.Node[i + 1].url = new URL(this.getDocumentBase(), string2);
                    }
                    catch (MalformedURLException ex3) {}
                }
            }
            if (stringTokenizer2.hasMoreElements() && !this.Node[i + 1].Name.startsWith("-")) {
                final String string3 = stringTokenizer2.nextElement().toString();
                if (!string3.startsWith("~")) {
                    this.Node[i + 1].icon = this.getImage(this.getDocumentBase(), string3).getScaledInstance(this.iconHt, this.iconHt, 4);
                }
            }
            if (stringTokenizer2.hasMoreElements() && !this.Node[i + 1].Name.startsWith("-")) {
                this.Node[i + 1].browserFrame = stringTokenizer2.nextElement().toString();
            }
        }
        this.Node[this.Node.length - 1] = new nodeClass("Web-Nova.com");
        try {
            this.Node[this.Node.length - 1].url = new URL("http://web-nova.com");
        }
        catch (MalformedURLException ex4) {}
        this.Node[this.Node.length - 1].browserFrame = "Wn";
        array[this.Node.length - 1] = 1;
        for (int j = 0; j < this.Node.length - 1; ++j) {
            final Vector vector2 = new Vector<Integer>();
            int n2 = j;
            do {
                ++n2;
                if (array[n2] == array[j] + 1) {
                    vector2.addElement(new Integer(n2));
                }
            } while (n2 < this.Node.length - 1 && array[n2] != array[j]);
            final int size = vector2.size();
            if (size > 0) {
                final int[] children = new int[size];
                for (int k = 0; k < size; ++k) {
                    children[k] = new Integer(vector2.elementAt(k).toString());
                }
                this.Node[j].setChildren(children);
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.closeAllMenus();
        this.startMenus();
        return true;
    }
    
    public void closeAllChildMenus() {
        if (this.firstMenu.childMenu != null) {
            this.firstMenu.childMenu.close();
            this.firstMenu.childMenu = null;
        }
    }
    
    public void init() {
        this.tickerString = null;
        this.menuBackgroundClr = new Color(Integer.parseInt(this.getParameter("menuBackgroundClr"), 16));
        this.menuHighlightClr = new Color(Integer.parseInt(this.getParameter("menuHighlightClr"), 16));
        this.menuTxtClr = new Color(Integer.parseInt(this.getParameter("menuTxtClr"), 16));
        this.menuTxtHighlightClr = new Color(Integer.parseInt(this.getParameter("menuTxtHighlightClr"), 16));
        this.menuTitleBarClr = new Color(Integer.parseInt(this.getParameter("menuTitleBarClr"), 16));
        this.menuTitleTxtClr = new Color(Integer.parseInt(this.getParameter("menuTitleTxtClr"), 16));
        this.tickerTxtClr = new Color(Integer.parseInt(this.getParameter("tickerTxtClr"), 16));
        this.tickerBackgroundClr = new Color(Integer.parseInt(this.getParameter("tickerBackgroundClr"), 16));
        this.menuTitleFont = new Font(this.getParameter("menuTitleFont"), Integer.parseInt(this.getParameter("menuTitleStyle")), Integer.parseInt(this.getParameter("menuTitleSize")));
        this.menuFont = new Font(this.getParameter("menuTxtFont"), Integer.parseInt(this.getParameter("menuTxtStyle")), Integer.parseInt(this.getParameter("menuTxtSize")));
        this.defaultFrame = this.getParameter("defaultFrame");
        this.menuList = this.getParameter("menuList");
        this.htFactor = Float.valueOf(this.getParameter("heightFactor"));
        if (this.htFactor < 1.0f) {
            this.htFactor = 1.0f;
        }
        this.buttonName = this.getParameter("buttonName");
        this.enableClose = Integer.valueOf(this.getParameter("enableClose"));
        if (this.buttonName.startsWith("pic:")) {
            this.buttonImg = this.getImage(this.getDocumentBase(), this.buttonName.substring(4));
        }
        this.menuFontSize = this.getFontMetrics(this.menuFont);
        this.titleFontSize = this.getFontMetrics(this.menuTitleFont);
        this.iconHt = this.menuFontSize.getHeight();
        this.titleIconHt = this.titleFontSize.getHeight();
        this.screen_res = Toolkit.getDefaultToolkit().getScreenSize();
        this.ac = this.getAppletContext();
        try {
            this.win = JSObject.getWindow((Applet)this);
        }
        catch (Exception ex) {}
        this.setUpNodes();
        this.menuReady = true;
        this.repaint();
    }
    
    class menuClass extends Window
    {
        String menuTitle;
        Box menuBox;
        Box tBarBox;
        Box bBox1;
        Box bBox2;
        Box tkBox;
        Box[] itemBox;
        int[] item;
        int tTab;
        int bWidth;
        Graphics wg;
        int lastItem;
        int currentItem;
        Point dragPt;
        boolean drag;
        menuClass childMenu;
        menuClass parentMenu;
        int max;
        int tickerIndex;
        Thread ticker;
        Image tickerBoxImage;
        Graphics tickerBoxG;
        int apparentMenuHt;
        
        public void runTicker() {
            (this.ticker = new Thread(new Runnable() {
                {
                    menuClass.this.getClass();
                }
                
                public void run() {
                    try {
                        while (true) {
                            Thread.sleep(120L);
                            menuClass.this.wg.setColor(navpop.this.menuBackgroundClr);
                            menuClass.this.wg.draw3DRect(menuClass.this.tkBox.Origin.x, menuClass.this.tkBox.Origin.y, menuClass.this.tkBox.Dim.width, menuClass.this.tkBox.Dim.height, false);
                            menuClass.this.tickerBoxG.setColor(navpop.this.tickerBackgroundClr);
                            menuClass.this.tickerBoxG.fillRect(0, 0, menuClass.this.tkBox.Dim.width - 1, menuClass.this.tkBox.Dim.height - 1);
                            if (menuClass.this.tickerIndex + navpop.this.menuFontSize.stringWidth(navpop.this.tickerString) < 0) {
                                menuClass.this.tickerIndex = menuClass.this.tkBox.Dim.width;
                            }
                            final int intValue = (int)(Object)new Float(0.7 * menuClass.this.tkBox.Dim.height);
                            menuClass.this.tickerBoxG.setColor(navpop.this.tickerTxtClr);
                            menuClass.this.tickerBoxG.setFont(navpop.this.menuFont);
                            menuClass.this.tickerBoxG.drawString(navpop.this.tickerString, menuClass.this.tickerIndex, intValue);
                            menuClass.this.wg.drawImage(menuClass.this.tickerBoxImage, menuClass.this.tkBox.Origin.x + 1, menuClass.this.tkBox.Origin.y + 1, navpop.this);
                            menuClass.this.tickerIndex -= 3;
                        }
                    }
                    catch (Exception ex) {}
                }
            })).start();
        }
        
        public boolean mouseEnter(final Event event, final int n, final int n2) {
            if (navpop.this.killMenus != null && navpop.this.killMenus.isAlive()) {
                navpop.this.killMenus.stop();
            }
            return true;
        }
        
        public void repositionMenu() {
            if (this.menuBox.Origin.x + this.menuBox.Dim.width > navpop.this.screen_res.width) {
                if (this.parentMenu != null) {
                    this.menuBox.Origin.x = this.parentMenu.menuBox.Origin.x - this.menuBox.Dim.width;
                }
                else {
                    this.menuBox.Origin.x = navpop.this.screen_res.width - this.menuBox.Dim.width;
                }
            }
            if (this.menuBox.Origin.x < 0) {
                this.menuBox.Origin.x = 0;
            }
            if (this.menuBox.Origin.y + this.apparentMenuHt > navpop.this.screen_res.height - (28 - navpop.this.warningBox)) {
                this.menuBox.Origin.y = navpop.this.screen_res.height - this.apparentMenuHt - (28 - navpop.this.warningBox);
            }
            if (this.menuBox.Origin.y < 0) {
                this.menuBox.Origin.y = 0;
            }
        }
        
        public void close() {
            if (this.childMenu != null) {
                this.childMenu.close();
            }
            this.childMenu = null;
            if (this.ticker != null) {
                this.ticker.stop();
            }
            this.ticker = null;
            this.dispose();
        }
        
        public boolean mouseExit(final Event event, final int n, final int n2) {
            (navpop.this.killMenus = new Thread(new Runnable() {
                {
                    menuClass.this.getClass();
                }
                
                public void run() {
                    try {
                        Thread.sleep(250L);
                        navpop.this.closeAllChildMenus();
                    }
                    catch (Exception ex) {}
                }
            })).start();
            return true;
        }
        
        public void enterItem(final int n) {
            if (n > -1) {
                this.displayItem(n, true);
                if (navpop.this.Node[this.item[n]].numChild > 0) {
                    this.childMenu = new menuClass(navpop.this.Node[this.item[n]].childList, new Point(this.menuBox.Origin.x + this.menuBox.Dim.width, this.menuBox.Origin.y + this.itemBox[n].Origin.y), null, this);
                }
            }
        }
        
        public void exitItem(final int n) {
            if (n > -1) {
                this.displayItem(n, false);
                if (this.childMenu != null) {
                    this.childMenu.close();
                    this.childMenu = null;
                }
            }
        }
        
        public menuClass(final int[] array, final Point origin, final String menuTitle, final menuClass parentMenu) {
            super(navpop.this.F);
            navpop.this.getClass();
            this.menuBox = new Box();
            this.tBarBox = new Box();
            this.bBox1 = new Box();
            this.bBox2 = new Box();
            this.tkBox = new Box();
            this.bWidth = 3;
            this.lastItem = -1;
            this.currentItem = -1;
            this.dragPt = new Point();
            this.drag = false;
            this.max = 1;
            this.menuTitle = menuTitle;
            this.parentMenu = parentMenu;
            this.item = new int[array.length];
            for (int i = 0; i < this.item.length; ++i) {
                this.item[i] = array[i];
            }
            this.tBarBox.Origin = new Point(this.bWidth, this.bWidth);
            if (menuTitle != null) {
                this.tBarBox.Dim.height = (int)(Object)new Float(navpop.this.titleFontSize.getHeight() * navpop.this.htFactor);
                this.tBarBox.Dim.width = navpop.this.titleFontSize.stringWidth(menuTitle) + 3 * this.tBarBox.Dim.height;
            }
            final Dimension dim = this.tBarBox.Dim;
            dim.width += navpop.this.titleIconHt;
            int width = this.tBarBox.Dim.width;
            final int intValue = (int)(Object)new Float(navpop.this.htFactor * navpop.this.menuFontSize.getHeight());
            this.tTab = navpop.this.menuFontSize.getHeight() / 2;
            this.itemBox = new Box[this.item.length];
            for (int j = 0; j < this.item.length; ++j) {
                this.itemBox[j] = new Box();
                if (!navpop.this.Node[this.item[j]].Name.startsWith("-")) {
                    this.itemBox[j].Dim.height = intValue;
                }
                else {
                    this.itemBox[j].Dim.height = (int)(Object)new Float(intValue / 2);
                }
                this.menuBox.Dim.height += this.itemBox[j].Dim.height;
                this.itemBox[j].Origin.x = this.bWidth;
                if (j != 0) {
                    this.itemBox[j].Origin.y = this.itemBox[j - 1].Origin.y + this.itemBox[j - 1].Dim.height;
                }
                else {
                    this.itemBox[j].Origin.y = this.tBarBox.Origin.y + this.tBarBox.Dim.height;
                }
                final int n = navpop.this.menuFontSize.stringWidth(navpop.this.Node[this.item[j]].Name) + 2 * (navpop.this.iconHt + this.tTab);
                if (n > width) {
                    width = n;
                }
            }
            for (int k = 0; k < this.item.length; ++k) {
                this.itemBox[k].Dim.width = width;
            }
            this.tBarBox.Dim.width = width;
            this.menuBox.Dim.height = this.menuBox.Dim.height + this.tBarBox.Dim.height + 2 * this.bWidth + navpop.this.warningBox;
            this.menuBox.Dim.width = 2 * this.bWidth + width;
            if (parentMenu == null && navpop.this.tickerString != null) {
                this.tkBox.Dim.height = (int)(Object)new Float(navpop.this.htFactor * navpop.this.menuFontSize.getHeight());
                this.tkBox.Dim.width = width;
                this.tkBox.Origin.x = this.bWidth;
                this.tkBox.Origin.y = this.itemBox[this.item.length - 1].Origin.y + this.itemBox[this.item.length - 1].Dim.height;
                this.menuBox.Dim.height += this.tkBox.Dim.height;
                this.tickerIndex = this.tkBox.Dim.width;
            }
            final int n2 = this.tBarBox.Dim.height * 22 / 100;
            final int n3 = this.tBarBox.Dim.height - 2 * n2;
            this.bBox1.Origin.x = this.tBarBox.Origin.x + this.tBarBox.Dim.width - n2 - 2 * n3;
            this.bBox1.Origin.y = this.tBarBox.Origin.y + n2;
            this.bBox2.Origin.x = this.bBox1.Origin.x + n3 + 1;
            this.bBox2.Origin.y = this.bBox1.Origin.y;
            this.bBox1.Dim.width = n3;
            this.bBox1.Dim.height = n3;
            this.bBox2.Dim.width = n3;
            this.bBox2.Dim.height = n3;
            this.menuBox.Origin = origin;
            this.apparentMenuHt = this.menuBox.Dim.height;
            this.repositionMenu();
            this.setBackground(navpop.this.menuBackgroundClr);
            this.setLocation(this.menuBox.Origin);
            this.setSize(this.menuBox.Dim);
            this.show();
            this.wg = this.getGraphics();
            this.repaint();
            if (parentMenu == null && navpop.this.tickerString != null) {
                this.tickerBoxImage = this.createImage(this.tkBox.Dim.width - 1, this.tkBox.Dim.height - 1);
                this.tickerBoxG = this.tickerBoxImage.getGraphics();
                this.runTicker();
            }
        }
        
        public void paint(final Graphics graphics) {
            this.wg.setColor(navpop.this.menuBackgroundClr);
            if (this.max == 1) {
                this.wg.draw3DRect(0, 0, this.menuBox.Dim.width - 1, this.menuBox.Dim.height, true);
                for (int i = 0; i < this.item.length; ++i) {
                    this.displayItemTxt(i, false);
                }
                if (this.currentItem >= 0) {
                    this.displayItem(this.currentItem, true);
                }
            }
            else {
                this.wg.draw3DRect(0, 0, this.menuBox.Dim.width - 1, this.tBarBox.Dim.height + 2 * this.bWidth, true);
            }
            if (this.menuTitle != null) {
                this.displayTitleBar();
            }
        }
        
        public boolean mouseUp(final Event event, final int n, final int n2) {
            if (this.drag) {
                navpop.this.closeAllChildMenus();
                this.menuBox.Origin.x += n - this.dragPt.x;
                this.menuBox.Origin.y += n2 - this.dragPt.y;
                this.repositionMenu();
                this.setLocation(this.menuBox.Origin);
                this.setCursor(Cursor.getPredefinedCursor(0));
                this.drag = false;
                navpop.this.posX = this.menuBox.Origin.x;
                navpop.this.posY = this.menuBox.Origin.y;
            }
            return true;
        }
        
        public void displayItemTxt(final int n, final boolean b) {
            if (!navpop.this.Node[this.item[n]].Name.startsWith("-") && navpop.this.Node[this.item[n]].icon != null) {
                this.wg.drawImage(navpop.this.Node[this.item[n]].icon, this.itemBox[n].Origin.x + this.tTab / 2, this.itemBox[n].Origin.y + (this.itemBox[n].Dim.height - navpop.this.iconHt) / 2, this);
            }
            this.wg.setFont(navpop.this.menuFont);
            if (b) {
                this.wg.setColor(navpop.this.menuTxtHighlightClr);
            }
            else {
                this.wg.setColor(navpop.this.menuTxtClr);
            }
            final int n2 = this.itemBox[n].Origin.x + navpop.this.iconHt + this.tTab;
            final int n3 = this.itemBox[n].Dim.height * 75 / 100 + this.itemBox[n].Origin.y;
            if (navpop.this.Node[this.item[n]].Name.startsWith("-")) {
                final int n4 = (int)(Object)new Float(this.itemBox[n].Dim.height * 0.45) + this.itemBox[n].Origin.y;
                final int bWidth = this.bWidth;
                this.wg.setColor(navpop.this.menuBackgroundClr);
                this.wg.draw3DRect(bWidth, n4, this.menuBox.Dim.width - 2 * bWidth, 1, false);
            }
            else {
                this.wg.drawString(navpop.this.Node[this.item[n]].Name, n2, n3);
            }
            if (navpop.this.Node[this.item[n]].numChild > 0) {
                final int n5 = this.itemBox[n].Origin.x + this.itemBox[n].Dim.width - this.tTab;
                final int n6 = n5 + this.tTab / 2;
                final int n7 = this.itemBox[n].Dim.height / 2 + this.itemBox[n].Origin.y;
                int n8 = 0;
                for (int i = n6; i > n5; --i) {
                    this.wg.drawLine(i, n7 - n8, i, n7 + n8);
                    ++n8;
                }
            }
        }
        
        public void displayTitleBar() {
            this.wg.setColor(navpop.this.menuTitleBarClr);
            this.wg.fillRect(this.tBarBox.Origin.x, this.tBarBox.Origin.y, this.tBarBox.Dim.width, this.tBarBox.Dim.height);
            this.wg.setColor(navpop.this.menuTitleTxtClr);
            this.wg.setFont(navpop.this.menuTitleFont);
            if (navpop.this.menuTitleIcon != null) {
                this.wg.drawImage(navpop.this.menuTitleIcon, this.tBarBox.Origin.x + this.tTab / 2, this.tBarBox.Origin.y + (this.tBarBox.Dim.height - navpop.this.titleIconHt) / 2, this);
            }
            this.wg.drawString(this.menuTitle, this.tBarBox.Origin.x + navpop.this.titleIconHt + this.tTab, this.tBarBox.Dim.height * 75 / 100 + this.tBarBox.Origin.y);
            this.wg.setColor(navpop.this.menuBackgroundClr);
            final int width = this.bBox1.Dim.width;
            int n = width * 3 / 10;
            if (n < 2) {
                n = 2;
            }
            if (navpop.this.enableClose != 0) {
                final int x = this.bBox1.Origin.x;
                final int y = this.bBox1.Origin.y;
                final int x2 = this.bBox2.Origin.x;
                final int y2 = this.bBox2.Origin.y;
                this.wg.fill3DRect(x, y, width, width, true);
                this.wg.fill3DRect(x2, y2, width, width, true);
                this.wg.setColor(navpop.this.menuTxtClr);
                this.wg.drawLine(x2 + n, y2 + n, x2 + width - n - 1, y2 + width - n - 1);
                this.wg.drawLine(x2 + n, y2 + width - n - 1, x2 + width - n - 1, y2 + n);
                if (this.max == 1) {
                    this.wg.drawLine(x + n, y + width - n - 1, x + width - n - 1, y + width - n - 1);
                }
                else {
                    this.wg.drawLine(x + n, y + n, x + width - n - 1, y + n);
                }
            }
            else {
                final int x3 = this.bBox2.Origin.x;
                final int y3 = this.bBox2.Origin.y;
                this.wg.fill3DRect(x3, y3, width, width, true);
                this.wg.setColor(navpop.this.menuTxtClr);
                if (this.max == 1) {
                    this.wg.drawLine(x3 + n, y3 + width - n - 1, x3 + width - n - 1, y3 + width - n - 1);
                }
                else {
                    this.wg.drawLine(x3 + n, y3 + n, x3 + width - n - 1, y3 + n);
                }
            }
        }
        
        public boolean mouseDown(final Event event, final int x, final int y) {
            final int selectedItem = this.selectedItem(x, y);
            if (selectedItem == -2) {
                navpop.this.posX = Integer.valueOf(navpop.this.getParameter("X"));
                navpop.this.posY = Integer.valueOf(navpop.this.getParameter("Y"));
                navpop.this.closeAllMenus();
            }
            else if (selectedItem == -1) {
                this.max = (this.max + 1) % 2;
                if (this.max == 0) {
                    this.setSize(this.menuBox.Dim.width, this.tBarBox.Dim.height + navpop.this.warningBox + 2 * this.bWidth);
                    this.apparentMenuHt = this.tBarBox.Dim.height + navpop.this.warningBox + 2 * this.bWidth;
                }
                else {
                    this.apparentMenuHt = this.menuBox.Dim.height;
                    this.repositionMenu();
                    this.setLocation(this.menuBox.Origin);
                    this.setSize(this.menuBox.Dim);
                }
            }
            else if (selectedItem == -3) {
                this.setCursor(Cursor.getPredefinedCursor(13));
                this.dragPt.x = x;
                this.dragPt.y = y;
                this.drag = true;
            }
            else if (this.max == 1) {
                if (this.childMenu != null) {
                    navpop.this.closeAllChildMenus();
                }
                if (navpop.this.Node[this.item[selectedItem]].url != null) {
                    String s = navpop.this.defaultFrame;
                    if (navpop.this.Node[this.item[selectedItem]].browserFrame != null) {
                        s = navpop.this.Node[this.item[selectedItem]].browserFrame;
                    }
                    navpop.this.ac.showDocument(navpop.this.Node[this.item[selectedItem]].url, s);
                }
                else if (navpop.this.Node[this.item[selectedItem]].jscript != null) {
                    final Vector vector = new Vector<String>();
                    final String jscript = navpop.this.Node[this.item[selectedItem]].jscript;
                    final String substring = jscript.substring(0, jscript.indexOf("("));
                    String string = "";
                    int n = 0;
                    for (int i = jscript.indexOf("(") + 1; i < jscript.length(); ++i) {
                        if (jscript.substring(i, i + 1).equals("'")) {
                            n = (n + 1) % 2;
                            if (n == 0) {
                                vector.addElement(string);
                                string = "";
                            }
                        }
                        else if (n == 1) {
                            string += jscript.substring(i, i + 1);
                        }
                    }
                    String[] array = { "" };
                    if (!vector.isEmpty()) {
                        array = new String[vector.size()];
                        for (int j = 0; j < vector.size(); ++j) {
                            array[j] = vector.elementAt(j).toString();
                        }
                    }
                    try {
                        navpop.this.win.call(substring, (Object[])array);
                    }
                    catch (Exception ex) {}
                }
            }
            return true;
        }
        
        public void displayItem(final int n, final boolean b) {
            if (!navpop.this.Node[this.item[n]].Name.startsWith("-")) {
                if (b) {
                    this.wg.setColor(navpop.this.menuHighlightClr);
                }
                else {
                    this.wg.setColor(navpop.this.menuBackgroundClr);
                }
                this.wg.fillRect(this.itemBox[n].Origin.x, this.itemBox[n].Origin.y, this.itemBox[n].Dim.width, this.itemBox[n].Dim.height);
                this.displayItemTxt(n, b);
            }
        }
        
        public int selectedItem(final int n, final int n2) {
            int n3 = -1;
            boolean pointInBox = false;
            if (navpop.this.enableClose != 0) {
                if (this.bBox1.pointInBox(n, n2)) {
                    return -1;
                }
                if (this.bBox2.pointInBox(n, n2)) {
                    return -2;
                }
            }
            if (navpop.this.enableClose == 0 && this.bBox2.pointInBox(n, n2)) {
                return -1;
            }
            if (this.tBarBox.pointInBox(n, n2)) {
                return -3;
            }
            while (!pointInBox && n3 < this.item.length - 1) {
                ++n3;
                pointInBox = this.itemBox[n3].pointInBox(n2);
            }
            if (pointInBox) {
                return n3;
            }
            return -9;
        }
        
        public boolean mouseMove(final Event event, final int n, final int n2) {
            if (this.max == 1) {
                this.currentItem = this.selectedItem(n, n2);
                if (this.currentItem != this.lastItem) {
                    this.exitItem(this.lastItem);
                    this.enterItem(this.currentItem);
                    this.lastItem = this.currentItem;
                }
            }
            return true;
        }
        
        class Box
        {
            Dimension Dim;
            Point Origin;
            
            public Box() {
                menuClass.this.getClass();
                this.Dim = new Dimension();
                this.Origin = new Point();
            }
            
            public boolean pointInBox(final int n, final int n2) {
                return n >= this.Origin.x && n <= this.Origin.x + this.Dim.width && n2 >= this.Origin.y && n2 <= this.Origin.y + this.Dim.height;
            }
            
            public boolean pointInBox(final int n) {
                return n >= this.Origin.y && n <= this.Origin.y + this.Dim.height;
            }
        }
    }
    
    class nodeClass
    {
        String Name;
        int numChild;
        int[] childList;
        Image icon;
        URL url;
        String jscript;
        String browserFrame;
        
        public nodeClass(final String name) {
            navpop.this.getClass();
            this.Name = name;
        }
        
        public void setChildren(final int[] array) {
            this.childList = new int[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.childList[i] = array[i];
            }
            this.numChild = array.length;
        }
    }
}
