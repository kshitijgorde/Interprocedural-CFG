import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.StringTokenizer;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MenuScrollTrial extends Applet implements Runnable
{
    String[] SColorArray;
    Color[] ColorMap;
    Panel p1;
    Panel p2;
    CardLayout layout;
    String temp;
    Thread thread;
    int numberOfMenus;
    String windows;
    StringTokenizer st;
    String unregistered;
    MenuScroller[] menuScrollerArray;
    String[] menuScrollerNameArray;
    MenuScroller activeSubMenu;
    String error;
    int width;
    int height;
    int appletWidth;
    int appletHeight;
    int mainMenuWidth;
    int mainMenuHeight;
    int subMenuWidth;
    int subMenuHeight;
    Color appletColor;
    int i;
    boolean slideShow;
    int slideShowIndex;
    long slideShowPause;
    boolean done;
    Color arrowColor;
    Color arrowHighliteColor;
    long slidePause;
    int progressWidth;
    int progressHeight;
    float percentage;
    Image offscreenImg;
    Graphics offscreenG;
    Color progressColor;
    Color progressBackground;
    Font f;
    boolean paint;
    String status;
    long displayStatus;
    int nextDispl;
    String slogan;
    boolean once;
    
    public MenuScrollTrial() {
        this.SColorArray = new String[] { "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink", "red", "white", "yellow" };
        this.ColorMap = new Color[] { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
        this.unregistered = "Unregistered: Contact support@consultcom.com";
        this.error = null;
        this.mainMenuWidth = 0;
        this.mainMenuHeight = 0;
        this.slideShow = false;
        this.slideShowIndex = 0;
        this.done = false;
        this.progressWidth = 100;
        this.progressHeight = 20;
        this.percentage = 0.0f;
        this.offscreenImg = null;
        this.progressColor = new Color(200, 0, 0);
        this.progressBackground = Color.lightGray;
        this.paint = true;
        this.status = "";
        this.displayStatus = 0L;
        this.nextDispl = 5000;
        this.slogan = "FREE Java Applets at www.consultcom.com";
        this.once = true;
    }
    
    boolean checkSecurity() {
        final String parameter = this.getParameter("copyright");
        if (parameter == null) {
            this.error = this.unregistered;
            return false;
        }
        if (parameter.compareTo("Menu Scroll v1.0 Copyright (c) 1999, consulting.com Inc.") == 0) {
            return true;
        }
        this.error = this.unregistered;
        return false;
    }
    
    Color getColor(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        final int index = this.temp.indexOf(",");
        if (index <= 0) {
            for (int i = 0; i < this.SColorArray.length; ++i) {
                if (this.temp.compareTo(this.SColorArray[i]) == 0) {
                    return this.ColorMap[i];
                }
            }
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        final String substring = this.temp.substring(0, index);
        final int index2 = this.temp.indexOf(",", index + 1);
        if (index2 < 0) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        final String substring2 = this.temp.substring(index + 1, index2);
        final String substring3 = this.temp.substring(index2 + 1, this.temp.length());
        int intValue;
        try {
            intValue = Integer.valueOf(substring);
        }
        catch (NumberFormatException ex) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        int intValue2;
        try {
            intValue2 = Integer.valueOf(substring2);
        }
        catch (NumberFormatException ex2) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        int intValue3;
        try {
            intValue3 = Integer.valueOf(substring3);
        }
        catch (NumberFormatException ex3) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        if (intValue > 255 || intValue2 > 255 || intValue3 > 255) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        return new Color(intValue, intValue2, intValue3);
    }
    
    MenuScroller getMenu(final String s) {
        for (int i = 0; i < this.menuScrollerArray.length; ++i) {
            if (s.equals(this.menuScrollerArray[i].menuName)) {
                return this.menuScrollerArray[i];
            }
        }
        return null;
    }
    
    int getNumber(final String s) {
        int intValue = 0;
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            return -1;
        }
        try {
            intValue = Integer.valueOf(this.temp);
        }
        catch (NumberFormatException ex) {}
        return intValue;
    }
    
    boolean getSize(final String s) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            this.error = "error: " + s + " value=\"" + parameter + "\"";
            return false;
        }
        this.width = parameter.indexOf(",");
        if (this.width > 0) {
            final String substring = parameter.substring(0, this.width);
            final String substring2 = parameter.substring(this.width + 1, parameter.length());
            try {
                this.width = Integer.valueOf(substring);
            }
            catch (NumberFormatException ex) {
                this.error = "error: " + s + " value=\"" + parameter + "\"";
                return false;
            }
            try {
                this.height = Integer.valueOf(substring2);
            }
            catch (NumberFormatException ex2) {
                this.error = "error: " + s + " value=\"" + parameter + "\"";
                return false;
            }
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.error = null;
        if (!this.checkSecurity()) {
            return;
        }
        this.slideShow = false;
        this.appletColor = this.getColor("appletColor");
        if (this.appletColor == null) {
            return;
        }
        this.setBackground(this.appletColor);
        if (!this.menuInit()) {
            return;
        }
        this.windows = this.getParameter("windows");
        if (this.windows == null) {
            this.error = "error: windows value=\"" + this.windows + "\"";
            return;
        }
        if (this.windows.indexOf(";") == -1) {
            this.error = "error: windows value=\"" + this.windows + "\"";
            return;
        }
        this.f = new Font("Helvetica", 0, 12);
        this.paint = true;
    }
    
    boolean menuInit() {
        this.arrowColor = this.getColor("arrowColor");
        if (this.arrowColor == null) {
            return false;
        }
        this.arrowHighliteColor = this.getColor("arrowHighliteColor");
        if (this.arrowHighliteColor == null) {
            return false;
        }
        if (!this.getSize("mainMenuSize")) {
            return false;
        }
        this.mainMenuWidth = this.width;
        this.mainMenuHeight = this.height;
        this.temp = this.getParameter("subMenuHeight");
        if (this.temp == null) {
            this.error = "error: subMenuHeight value=\"" + this.temp + "\"";
            return false;
        }
        try {
            this.subMenuHeight = Integer.valueOf(this.temp);
        }
        catch (NumberFormatException ex) {
            this.error = "error: subMenuHeight value=\"" + this.temp + "\"";
            return false;
        }
        return true;
    }
    
    boolean menuSetUp() {
        this.subMenuWidth = this.appletWidth - this.mainMenuWidth;
        this.st = new StringTokenizer(this.windows, ";");
        this.numberOfMenus = 0;
        while (this.st.hasMoreTokens()) {
            ++this.numberOfMenus;
            this.st.nextToken();
        }
        this.menuScrollerArray = new MenuScroller[this.numberOfMenus];
        this.menuScrollerNameArray = new String[this.numberOfMenus];
        this.st = new StringTokenizer(this.windows, ";");
        this.i = 0;
        while (this.st.hasMoreTokens()) {
            this.menuScrollerNameArray[this.i] = this.st.nextToken();
            ++this.i;
        }
        int n = 0;
        this.menuScrollerArray[0] = new MenuScroller(this, this.menuScrollerNameArray[0], this.mainMenuWidth, this.mainMenuHeight, 0, this.appletColor);
        this.percentage = 1.0f / this.numberOfMenus;
        this.repaint();
        this.pause(50);
        if (this.menuScrollerArray[0].checkError) {
            this.error = this.menuScrollerArray[0].error;
            return false;
        }
        (this.p1 = new Panel()).setLayout(new CardLayout());
        this.p1.add("Center", this.menuScrollerArray[0]);
        ++n;
        this.p2 = new Panel();
        this.layout = new CardLayout();
        this.p2.setLayout(this.layout);
        for (int i = n; i < this.menuScrollerArray.length; ++i) {
            this.menuScrollerArray[i] = new MenuScroller(this, this.menuScrollerNameArray[i], this.subMenuWidth, this.subMenuHeight, 1, this.appletColor);
            this.percentage = (i + 1) / this.numberOfMenus;
            this.repaint();
            this.pause(50);
            if (this.menuScrollerArray[i].checkError) {
                this.error = this.menuScrollerArray[i].error;
                return false;
            }
            this.p2.add(this.menuScrollerNameArray[i], this.menuScrollerArray[i]);
        }
        this.paint = false;
        final Graphics graphics = this.getGraphics();
        graphics.setColor(this.appletColor);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        this.add(this.p1);
        this.p1.reshape(0, this.appletHeight - this.mainMenuHeight, this.mainMenuWidth, this.mainMenuHeight);
        this.add(this.p2);
        this.p2.reshape(this.mainMenuWidth, this.appletHeight - this.subMenuHeight, this.subMenuWidth, this.subMenuHeight);
        this.activeSubMenu = null;
        for (int j = 0; j < this.menuScrollerArray.length; ++j) {
            this.menuScrollerArray[j].makeImage();
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.paint) {
            graphics.setFont(this.f);
            final int stringWidth = this.getFontMetrics(this.f).stringWidth("Creating objects...");
            graphics.setColor(new Color(127, 127, 127));
            graphics.drawString("Creating objects...", this.size().width / 2 - stringWidth / 2, this.size().height / 2 - this.progressHeight);
            if (this.offscreenImg == null) {
                this.offscreenImg = this.createImage(this.progressWidth, this.progressHeight);
            }
            (this.offscreenG = this.offscreenImg.getGraphics()).setFont(this.f);
            final int width = this.offscreenImg.getWidth(this);
            final int height = this.offscreenImg.getHeight(this);
            this.offscreenG.setColor(this.progressBackground);
            this.offscreenG.fillRect(0, 0, width, height);
            this.offscreenG.setColor(this.progressColor);
            this.offscreenG.fillRect(0, 0, (int)(width * this.percentage), height);
            this.offscreenG.drawString(String.valueOf(Integer.toString((int)(this.percentage * 100.0f))) + "% complete", 7, height / 2 + 5);
            this.offscreenG.clipRect(0, 0, (int)(width * this.percentage), height);
            this.offscreenG.setColor(this.progressBackground);
            this.offscreenG.drawString(String.valueOf(Integer.toString((int)(this.percentage * 100.0f))) + "% complete", 7, height / 2 + 5);
            graphics.setColor(this.progressBackground);
            graphics.drawRect(this.size().width / 2 - this.progressWidth / 2 - 2, this.size().height / 2 - this.progressHeight / 2 - 2, this.progressWidth + 3, this.progressHeight + 3);
            graphics.drawImage(this.offscreenImg, this.size().width / 2 - this.progressWidth / 2, this.size().height / 2 - this.progressHeight / 2, this);
        }
    }
    
    void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        if (this.error == null) {
            this.setUp();
        }
        this.once = false;
        while (this.thread.isAlive()) {
            if (this.error != null) {
                this.showStatus(this.error);
            }
            if (this.done && System.currentTimeMillis() > this.displayStatus) {
                this.showStatus(this.status = this.slogan);
                this.displayStatus = System.currentTimeMillis() + this.nextDispl;
            }
            this.pause(100);
        }
    }
    
    boolean setBoolean(final String s) {
        final String parameter = this.getParameter(s);
        return parameter != null && parameter.compareTo("yes") == 0;
    }
    
    void setUp() {
        this.percentage = 0.0f;
        this.done = false;
        this.paint = true;
        this.error = null;
        this.appletWidth = this.size().width;
        this.appletHeight = this.size().height;
        if (this.once && !this.menuSetUp()) {
            return;
        }
        for (int i = 0; i < this.menuScrollerArray.length; ++i) {
            this.menuScrollerArray[i].start();
        }
        this.menuScrollerArray[0].run = true;
        this.done = true;
    }
    
    void setYpos() {
        for (int i = 0; i < this.menuScrollerArray.length; ++i) {
            if (this.menuScrollerArray[i].menuName.startsWith("msSub")) {
                this.menuScrollerArray[i].ypos = this.menuScrollerArray[i].size().height;
            }
        }
    }
    
    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this);
        }
        this.thread.start();
    }
    
    public void stop() {
        for (int i = 0; i < this.menuScrollerArray.length; ++i) {
            this.menuScrollerArray[i].stop();
        }
        this.percentage = 0.0f;
        if (this.thread != null) {
            this.thread.stop();
        }
        this.thread = null;
    }
    
    void subScrollStop() {
        for (int i = 0; i < this.menuScrollerArray.length; ++i) {
            if (this.menuScrollerArray[i].menuName.startsWith("msSub")) {
                this.menuScrollerArray[i].run = false;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
