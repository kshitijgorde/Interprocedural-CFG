import java.awt.Dimension;
import java.awt.Event;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetToolBarPanel extends Canvas
{
    private IRCQNet theApp;
    private int Mwidth;
    private int Mheight;
    private Vector ButtonsLabels;
    private Vector ButtonsImages;
    private Vector disableList;
    private Vector Images;
    private Vector Rect;
    private int Down;
    private boolean haveBeenDown;
    private boolean tabMode;
    private int ButtonWidth;
    private int minButtonWidth;
    private FontMetrics FMetrics;
    private Font tFont;
    private int FontHeight;
    private Image Image0;
    private Image Image1;
    private Image Image2;
    private Image Image3;
    private Image Image4;
    private Image Image5;
    private Image Image6;
    private Image Image7;
    private Image Image8;
    private Image Image9;
    private Image Image10;
    private Image Image11;
    public static final int CONNECT = 0;
    public static final int DISCONNECT = 1;
    public static final int OPTIONS = 2;
    public static final int JOIN = 3;
    public static final int QUIT = 4;
    public static final int FLOAT = 5;
    public static final int EMBED = 6;
    public static final int ROOMS = 7;
    public static final int NICK = 8;
    public static final int GENERAL = 9;
    public static final int TEXT_SFX = 10;
    public static final int SEARCH = 11;
    
    public IRCQNetToolBarPanel(final IRCQNet theApp) {
        this.ButtonsLabels = new Vector(10, 10);
        this.ButtonsImages = new Vector(10, 10);
        this.disableList = new Vector(10, 10);
        this.Images = new Vector(3);
        this.Rect = new Vector(10, 10);
        this.Down = -1;
        this.haveBeenDown = false;
        this.tabMode = false;
        this.ButtonWidth = 60;
        this.minButtonWidth = 50;
        this.tFont = new Font("Helvetica", 0, 14);
        this.theApp = theApp;
        try {
            theApp.getToolkit().prepareImage(this.Image0 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/connect.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image1 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/disconnect.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image2 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/option.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image3 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/join.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image4 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/quit.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image5 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/float.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image6 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/embed.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image7 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/room.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image8 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/nick.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image9 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/general.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image10 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/text.gif"), 20, 20, this);
            theApp.getToolkit().prepareImage(this.Image11 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/search.gif"), 20, 20, this);
        }
        catch (NullPointerException ex) {}
        this.Images.addElement(this.Image0);
        this.Images.addElement(this.Image1);
        this.Images.addElement(this.Image2);
        this.Images.addElement(this.Image3);
        this.Images.addElement(this.Image4);
        this.Images.addElement(this.Image5);
        this.Images.addElement(this.Image6);
        this.Images.addElement(this.Image7);
        this.Images.addElement(this.Image8);
        this.Images.addElement(this.Image9);
        this.Images.addElement(this.Image10);
        this.Images.addElement(this.Image11);
        this.FMetrics = this.getFontMetrics(this.tFont);
    }
    
    public IRCQNetToolBarPanel(final IRCQNet ircqNet, final boolean b) {
        this(ircqNet);
    }
    
    public void DelAllButtons() {
        for (int i = this.ButtonsLabels.size() - 1; i > 0; --i) {
            if (!((String)this.ButtonsLabels.elementAt(i)).equals("!STATUS!")) {
                this.DelButton((String)this.ButtonsLabels.elementAt(i));
            }
        }
        this.Down = -1;
    }
    
    public boolean setButtonsWidth(final int buttonWidth) {
        this.ButtonWidth = buttonWidth;
        return true;
    }
    
    public void setTabMode(final boolean tabMode) {
        this.tabMode = tabMode;
    }
    
    public boolean setFontSize(final int n) {
        this.tFont = new Font("Helvetica", 0, n);
        this.FMetrics = this.getFontMetrics(this.tFont);
        this.FontHeight = this.FMetrics.getHeight();
        this.repaint();
        return true;
    }
    
    public void reshape(final int n, final int n2, final int mwidth, final int mheight) {
        super.reshape(n, n2, mwidth, mheight);
        this.Mwidth = mwidth;
        this.Mheight = mheight;
        this.Down = -1;
    }
    
    public boolean DelButton(final String s) {
        for (int size = this.ButtonsLabels.size(), i = 0; i < size; ++i) {
            if (s.equalsIgnoreCase((String)this.ButtonsLabels.elementAt(i))) {
                this.ButtonsLabels.removeElementAt(i);
                this.ButtonsImages.removeElementAt(i);
                this.disableList.removeElementAt(i);
                this.Down = -1;
                this.repaint();
                return true;
            }
        }
        return false;
    }
    
    public boolean changeButton(final String s, final String s2, final int n) {
        for (int size = this.ButtonsLabels.size(), i = 0; i < size; ++i) {
            if (s.equalsIgnoreCase((String)this.ButtonsLabels.elementAt(i))) {
                this.ButtonsLabels.removeElementAt(i);
                this.ButtonsLabels.insertElementAt(s2, i);
                this.ButtonsImages.removeElementAt(i);
                this.ButtonsImages.insertElementAt(new Integer(n), i);
            }
        }
        if (this.isVisible()) {
            this.repaint();
        }
        return true;
    }
    
    public boolean disableButton(final String s) {
        for (int size = this.ButtonsLabels.size(), i = 0; i < size; ++i) {
            if (s.equalsIgnoreCase((String)this.ButtonsLabels.elementAt(i))) {
                this.disableList.removeElementAt(i);
                this.disableList.insertElementAt(new Boolean(true), i);
            }
        }
        return true;
    }
    
    public boolean enableButton(final String s) {
        for (int size = this.ButtonsLabels.size(), i = 0; i < size; ++i) {
            if (s.equalsIgnoreCase((String)this.ButtonsLabels.elementAt(i))) {
                this.disableList.removeElementAt(i);
                this.disableList.insertElementAt(new Boolean(false), i);
            }
        }
        return true;
    }
    
    public boolean NewButton(final String s, final int n) {
        for (int size = this.ButtonsLabels.size(), i = 0; i < size; ++i) {
            if (s.equalsIgnoreCase((String)this.ButtonsLabels.elementAt(i))) {
                return false;
            }
        }
        this.ButtonsLabels.addElement(s);
        this.disableList.addElement(new Boolean(false));
        if (s.equalsIgnoreCase("!STATUS!")) {
            this.ButtonsImages.addElement(new Integer(0));
        }
        else {
            this.ButtonsImages.addElement(new Integer(n));
        }
        if (this.isVisible()) {
            this.repaint();
        }
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        Image image;
        try {
            image = this.createImage(this.Mwidth, this.Mheight);
        }
        catch (IllegalArgumentException ex) {
            return;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(this.tFont);
        graphics.setColor(IRCQNetColors.controlColor);
        graphics.fillRect(0, 0, this.Mwidth, this.Mheight);
        graphics2.setColor(IRCQNetColors.controlColor);
        graphics2.fillRect(0, 0, this.Mwidth, this.Mheight);
        if (this.ButtonsLabels.size() < 1) {
            return;
        }
        final int n = this.ButtonsLabels.size() - 1;
        int n2;
        if ((n + 1) * this.ButtonWidth > this.Mwidth) {
            n2 = this.Mwidth / (n + 1);
        }
        else {
            n2 = this.ButtonWidth;
        }
        if (n2 < this.minButtonWidth) {
            n2 = this.minButtonWidth;
        }
        this.Rect.removeAllElements();
        int n3 = this.Mheight - 5;
        if (n2 < this.Mheight) {
            n3 = n2 - 5;
        }
        for (int i = 0; i <= n; ++i) {
            Image image2;
            if (i == this.Down) {
                image2 = this.DrawButton(this.ButtonsLabels.elementAt(i), n2, n3, false, this.ButtonsImages.elementAt(i));
            }
            else {
                image2 = this.DrawButton(this.ButtonsLabels.elementAt(i), n2, n3, true, this.ButtonsImages.elementAt(i));
            }
            if (image2 != null) {
                graphics2.drawImage(image2, i * n2, 4, null);
            }
            this.Rect.addElement(new Rectangle(i * n2, 4, n2, n3));
        }
        graphics2.setColor(IRCQNetColors.controlColor.darker());
        graphics2.drawLine(0, 0, this.Mwidth, 0);
        graphics2.setColor(Color.white);
        graphics2.drawLine(0, 1, this.Mwidth, 1);
        graphics.drawImage(image, 0, 0, null);
    }
    
    public Image DrawButton(String s, int n, int n2, final boolean b, int n3) {
        this.FMetrics = this.getFontMetrics(this.tFont);
        this.FontHeight = this.FMetrics.getHeight();
        Image image;
        try {
            image = this.createImage(n, n2);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setFont(this.tFont);
        graphics.setColor(IRCQNetColors.controlColor);
        graphics.fillRect(0, 0, n, n2);
        n2 -= 2;
        n -= 2;
        if (!b) {
            graphics.setColor(IRCQNetColors.controlColor.brighter());
            graphics.drawLine(0, 0, n, 0);
            graphics.drawLine(0, 0, 0, n2);
            graphics.setColor(Color.black);
            graphics.drawLine(0, n2, n, n2);
            graphics.drawLine(n, 0, n, n2);
            graphics.setColor(IRCQNetColors.controlColor.darker());
            graphics.drawLine(1, n2 - 1, n - 1, n2 - 1);
            graphics.drawLine(n - 1, 1, n - 1, n2 - 1);
        }
        graphics.setColor(Color.black);
        if (s.equalsIgnoreCase("!STATUS!")) {
            s = "Status";
            n3 = 0;
        }
        if (s.equalsIgnoreCase("!CHANNEL!")) {
            s = "Rooms";
            n3 = 1;
        }
        final Image image2 = this.Images.elementAt(n3);
        graphics.drawImage(image2, (n - image2.getWidth(null)) / 2, 3, null);
        graphics.drawString(s, (n - this.FMetrics.stringWidth(s)) / 2, 27 + this.FontHeight);
        image.flush();
        return image;
    }
    
    public boolean PaintAt(final int n, final boolean b) {
        if (!this.isVisible()) {
            return false;
        }
        final Graphics graphics = this.getGraphics();
        final int n2 = this.ButtonsLabels.size() - 1;
        int n3;
        if ((n2 + 1) * this.ButtonWidth > this.Mwidth) {
            n3 = this.Mwidth / (n2 + 1);
        }
        else {
            n3 = this.ButtonWidth;
        }
        if (n3 < this.minButtonWidth) {
            n3 = this.minButtonWidth;
        }
        int n4 = n3;
        int n5 = this.Mheight - 5;
        if (n3 - 5 < n5) {
            n5 = n3 - 5;
        }
        final int n6 = n * n3;
        Image image;
        try {
            image = this.createImage(n4, n5);
        }
        catch (IllegalArgumentException ex) {
            return false;
        }
        if (image == null) {
            return false;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(this.tFont);
        graphics2.setColor(IRCQNetColors.controlColor);
        graphics2.fillRect(0, 0, n4, n5);
        n5 -= 2;
        n4 -= 2;
        if (!b) {
            graphics2.setColor(IRCQNetColors.controlColor.brighter());
            graphics2.drawLine(0, 0, n4, 0);
            graphics2.drawLine(0, 0, 0, n5);
            graphics2.setColor(Color.black);
            graphics2.drawLine(0, n5, n4, n5);
            graphics2.drawLine(n4, 0, n4, n5);
            graphics2.setColor(IRCQNetColors.controlColor.darker());
            graphics2.drawLine(1, n5 - 1, n4 - 1, n5 - 1);
            graphics2.drawLine(n4 - 1, 1, n4 - 1, n5 - 1);
        }
        String s = new String(this.ButtonsLabels.elementAt(n));
        final int intValue = this.ButtonsImages.elementAt(n);
        if (s.equalsIgnoreCase("!STATUS!")) {
            s = "Status";
        }
        if (s.equalsIgnoreCase("!CHANNEL!")) {
            s = "Rooms";
        }
        graphics2.setColor(Color.black);
        final Image image2 = this.Images.elementAt(intValue);
        graphics2.drawImage(image2, (n4 - image2.getWidth(null)) / 2, 3, null);
        graphics2.drawString(s, (n4 - this.FMetrics.stringWidth(s)) / 2, 27 + this.FontHeight);
        image.flush();
        graphics.drawImage(image, n6, 4, null);
        return true;
    }
    
    public boolean PaintAtDown(final int n, final boolean b) {
        if (!this.isVisible()) {
            return false;
        }
        final Graphics graphics = this.getGraphics();
        final int n2 = this.ButtonsLabels.size() - 1;
        int n3;
        if ((n2 + 1) * this.ButtonWidth > this.Mwidth) {
            n3 = this.Mwidth / (n2 + 1);
        }
        else {
            n3 = this.ButtonWidth;
        }
        if (n3 < this.minButtonWidth) {
            n3 = this.minButtonWidth;
        }
        int n4 = n3;
        int n5 = this.Mheight - 5;
        if (n3 - 5 < n5) {
            n5 = n3 - 5;
        }
        final int n6 = n * n3;
        Image image;
        try {
            image = this.createImage(n4, n5);
        }
        catch (IllegalArgumentException ex) {
            return false;
        }
        if (image == null) {
            return false;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(this.tFont);
        graphics2.setColor(IRCQNetColors.controlColor);
        graphics2.fillRect(0, 0, n4, n5);
        n5 -= 2;
        n4 -= 2;
        if (b) {
            graphics2.setColor(Color.black);
            graphics2.drawLine(0, 0, n4, 0);
            graphics2.drawLine(0, 0, 0, n5);
            graphics2.setColor(IRCQNetColors.controlColor.brighter());
            graphics2.drawLine(0, n5, n4, n5);
            graphics2.drawLine(n4, 0, n4, n5);
            graphics2.setColor(IRCQNetColors.controlColor.darker());
            graphics2.drawLine(1, 1, n4 - 1, 1);
            graphics2.drawLine(1, 1, 1, n5 - 1);
        }
        else {
            graphics2.setColor(IRCQNetColors.controlColor.brighter());
            graphics2.drawLine(0, 0, n4, 0);
            graphics2.drawLine(0, 0, 0, n5);
            graphics2.setColor(Color.black);
            graphics2.drawLine(0, n5, n4, n5);
            graphics2.drawLine(n4, 0, n4, n5);
            graphics2.setColor(IRCQNetColors.controlColor.darker());
            graphics2.drawLine(1, n5 - 1, n4 - 1, n5 - 1);
            graphics2.drawLine(n4 - 1, 1, n4 - 1, n5 - 1);
        }
        String s = new String(this.ButtonsLabels.elementAt(n));
        final int intValue = this.ButtonsImages.elementAt(n);
        if (s.equalsIgnoreCase("!STATUS!")) {
            s = "Status";
        }
        if (s.equalsIgnoreCase("!CHANNEL!")) {
            s = "Rooms";
        }
        graphics2.setColor(Color.black);
        final Image image2 = this.Images.elementAt(intValue);
        graphics2.drawImage(image2, (n4 - image2.getWidth(null)) / 2, 3, null);
        graphics2.drawString(s, (n4 - this.FMetrics.stringWidth(s)) / 2, 27 + this.FontHeight);
        image.flush();
        graphics.drawImage(image, n6, 4, null);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        for (int size = this.Rect.size(), i = 0; i < size; ++i) {
            if (((Rectangle)this.Rect.elementAt(i)).inside(n, n2)) {
                if (this.Down != i && this.Down >= 0) {
                    this.PaintAt(this.Down, true);
                }
                this.PaintAt(this.Down = i, false);
                return true;
            }
        }
        if (this.Down >= 0) {
            this.PaintAt(this.Down, true);
        }
        this.Down = -1;
        return super.mouseMove(event, n, n2);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.haveBeenDown = false;
        if (this.Down >= 0) {
            this.PaintAt(this.Down, true);
            this.Down = -1;
        }
        return super.mouseExit(event, n, n2);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int size = this.Rect.size(), i = 0; i < size; ++i) {
            if (((Rectangle)this.Rect.elementAt(i)).inside(n, n2)) {
                if (this.Down != i && this.Down >= 0) {
                    this.PaintAtDown(this.Down, false);
                }
                this.PaintAtDown(this.Down = i, true);
                return this.haveBeenDown = true;
            }
        }
        return super.mouseDown(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        for (int size = this.Rect.size(), i = 0; i < size; ++i) {
            if (((Rectangle)this.Rect.elementAt(i)).inside(n, n2)) {
                if (this.Down >= 0 && this.Down != i) {
                    this.PaintAtDown(this.Down, false);
                }
                this.PaintAtDown(this.Down = i, true);
                if (this.haveBeenDown && !this.disableList.elementAt(this.Down)) {
                    if (this.tabMode) {
                        this.postEvent(new Event(this, 1001, this.ButtonsLabels.elementAt(this.Down)));
                    }
                    else {
                        this.theApp.MPanel.postEvent(new Event(this, 1001, this.ButtonsLabels.elementAt(this.Down)));
                    }
                }
            }
        }
        return super.mouseUp(event, n, n2);
    }
    
    public Dimension preferredSize() {
        return new Dimension(60 * this.ButtonsLabels.size(), 60);
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.ButtonWidth * this.ButtonsLabels.size(), 60);
    }
}
