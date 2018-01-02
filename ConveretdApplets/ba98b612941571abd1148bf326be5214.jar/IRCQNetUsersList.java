import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.util.Vector;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetUsersList extends Panel
{
    private IRCQNetScrollBar SBar;
    private IRCQNetPanel parent;
    private FontMetrics FMatrics;
    private int LHeight;
    private Font MFont;
    private int mWidth;
    private int mHeight;
    public int onLine;
    private int top;
    private int newLineAt;
    private int lineAt;
    private int Viewable;
    private int drawAt;
    private int xVar;
    private Vector Images;
    private Image Image0;
    private Image Image1;
    private Image Image2;
    private boolean showImage;
    private Vector NamesList;
    private Vector ImageList;
    public static final int ONLINE = 0;
    public static final int OFFLINE = 1;
    public static final int NA = 2;
    
    public IRCQNetUsersList(final IRCQNetPanel parent) {
        this.MFont = new Font("Helvetica", 0, 14);
        this.xVar = 20;
        this.Images = new Vector(3);
        this.showImage = true;
        this.NamesList = new Vector(10, 10);
        this.ImageList = new Vector(10, 10);
        this.SBar = new IRCQNetScrollBar();
        this.parent = parent;
        this.setLayout(new BorderLayout());
        this.add("East", this.SBar);
        try {
            parent.theApp.getToolkit().prepareImage(this.Image0 = parent.theApp.getImage(parent.theApp.getCodeBase(), "icons/online.gif"), 16, 16, this);
            parent.theApp.getToolkit().prepareImage(this.Image1 = parent.theApp.getImage(parent.theApp.getCodeBase(), "icons/offline.gif"), 16, 16, this);
            parent.theApp.getToolkit().prepareImage(this.Image2 = parent.theApp.getImage(parent.theApp.getCodeBase(), "icons/disabled.gif"), 16, 16, this);
        }
        catch (NullPointerException ex) {}
        this.Images.addElement(this.Image0);
        this.Images.addElement(this.Image1);
        this.Images.addElement(this.Image2);
        this.showImage = false;
        this.xVar = 0;
    }
    
    public IRCQNetParam Params() {
        return this.parent.theApp.MPanel.getParams();
    }
    
    public void setMultipleSelections(final boolean b) {
    }
    
    public int countItems() {
        return this.NamesList.size();
    }
    
    public void addItem(final String s) {
        this.NamesList.addElement(s);
        this.ImageList.addElement(String.valueOf(2));
        this.SyncScroll();
        this.repaint();
    }
    
    public void addItem(final String s, final int n) {
        this.NamesList.insertElementAt(s, n);
        this.ImageList.insertElementAt(String.valueOf(2), n);
        this.SyncScroll();
        this.repaint();
    }
    
    public void addItemStatus(final String s, final int n) {
        this.NamesList.addElement(s);
        this.ImageList.addElement(String.valueOf(n));
        this.SyncScroll();
        this.repaint();
    }
    
    public void addItemStatus(final String s, final int n, final int n2) {
        this.NamesList.insertElementAt(s, n);
        this.ImageList.insertElementAt(String.valueOf(n2), n);
        this.SyncScroll();
        this.repaint();
    }
    
    public String getItem(final int n) {
        return this.NamesList.elementAt(n);
    }
    
    public String getItemStatus(final int n) {
        return this.ImageList.elementAt(n);
    }
    
    public void delItem(final int n) {
        this.NamesList.removeElementAt(n);
        this.ImageList.removeElementAt(n);
        this.SyncScroll();
        this.repaint();
    }
    
    public String getSelectedItem() {
        return this.NamesList.elementAt(this.onLine);
    }
    
    public Dimension preferredSize() {
        return new Dimension(130, 100);
    }
    
    public Dimension minimumSize() {
        return new Dimension(130, 50);
    }
    
    public int whichLine(final int n, final int n2) {
        final int onLine = n2 / this.FMatrics.getHeight() + this.top;
        this.onLine = onLine;
        this.repaint();
        return onLine;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.newLineAt = this.whichLine(event.x, event.y);
                if (event.clickCount >= 2 && this.newLineAt == this.lineAt) {
                    this.postEvent(new Event(this, 1001, ""));
                }
                if (this.newLineAt != this.lineAt) {
                    this.lineAt = this.newLineAt;
                }
                return true;
            }
            case 602: {
                ++this.onLine;
                this.SyncScroll();
                this.repaint();
                break;
            }
            case 601: {
                --this.onLine;
                this.SyncScroll();
                this.repaint();
                break;
            }
            case 603:
            case 604:
            case 605: {
                this.SBarMove();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public void reshape(final int n, final int n2, final int mWidth, final int mHeight) {
        super.reshape(n, n2, mWidth, mHeight);
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.SyncScroll();
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
            image = this.createImage(this.mWidth, this.mHeight);
        }
        catch (Exception ex) {
            return;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(Color.white);
        graphics2.fillRect(0, 0, this.mWidth, this.mHeight);
        this.FMatrics = this.getFontMetrics(this.MFont);
        this.LHeight = this.FMatrics.getHeight();
        this.Viewable = this.mHeight / this.LHeight;
        graphics2.setColor(Color.black);
        final int onLine = this.NamesList.size() - 1;
        if (this.onLine == this.top - 1) {
            --this.top;
        }
        if (this.onLine < 0) {
            this.onLine = 0;
        }
        if (this.onLine > onLine) {
            this.onLine = onLine;
        }
        if (this.top < 0) {
            this.top = 0;
        }
        if (this.top > onLine - this.Viewable) {
            if (onLine > this.Viewable) {
                this.top = onLine - this.Viewable;
            }
            else {
                this.top = 0;
            }
        }
        if (this.onLine + 1 - this.Viewable - 1 >= this.top) {
            ++this.top;
        }
        int viewable;
        if (onLine < this.Viewable) {
            viewable = onLine;
            ++viewable;
        }
        else {
            viewable = this.Viewable;
        }
        this.drawAt = this.LHeight;
        for (int i = this.top; i < this.top + viewable; ++i) {
            if (this.showImage) {
                graphics2.drawImage((Image)this.Images.elementAt(new Integer((String)this.ImageList.elementAt(i))), 0, this.drawAt - this.LHeight, null);
            }
            if (this.Params().nick.equalsIgnoreCase((String)this.NamesList.elementAt(i))) {
                graphics2.setColor(Color.red);
            }
            else {
                graphics2.setColor(Color.black);
            }
            if (i != this.onLine) {
                graphics2.drawString((String)this.NamesList.elementAt(i), this.xVar, this.drawAt - 2);
            }
            else {
                this.paintSelectedLine(graphics2, this.NamesList.elementAt(this.onLine), this.ImageList.elementAt(this.onLine), this.xVar, this.drawAt - 2);
            }
            this.drawAt += this.LHeight;
        }
        image.flush();
        graphics2.dispose();
        graphics.drawImage(image, 0, 0, null);
    }
    
    public void SBarMove() {
        if (this.LHeight > 0) {
            final int n = this.mHeight / this.LHeight;
            this.onLine = this.SBar.getValue();
            if (this.onLine >= this.top + n) {
                this.top = this.onLine;
            }
            if (this.onLine < this.top + n) {
                this.top = this.onLine;
            }
        }
        else {
            this.onLine = this.SBar.getValue();
        }
        this.SyncScroll();
    }
    
    public void SyncScroll() {
        if (this.LHeight > 0) {
            final int n = this.mHeight / this.LHeight;
            if (this.NamesList.size() >= n) {
                this.SBar.setValues(this.onLine, n, 0, this.NamesList.size());
            }
            else {
                this.SBar.setValues(0, 0, 0, 0);
            }
        }
        else {
            this.SBar.setValues(0, 0, 0, 0);
        }
        this.repaint();
        System.gc();
    }
    
    private void paintSelectedLine(final Graphics graphics, final String s, final String s2, final int n, final int n2) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, n2 - this.LHeight + 2, this.mWidth, this.LHeight + 2);
        if (this.showImage) {
            graphics.drawImage(this.Images.elementAt(1), 0, this.drawAt - this.LHeight, null);
        }
        graphics.setColor(Color.green);
        graphics.drawString(s, n, n2);
        graphics.setColor(Color.black);
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 1004: {
                return this.lineUp();
            }
            case 1005: {
                return this.lineDown();
            }
            case 1002: {
                return this.pageUp();
            }
            case 1003: {
                return this.pageDown();
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean lineUp() {
        --this.onLine;
        if (this.onLine < 0) {
            this.onLine = 0;
        }
        this.SyncScroll();
        return true;
    }
    
    public boolean lineDown() {
        ++this.onLine;
        if (this.onLine > this.NamesList.size() - 1) {
            this.onLine = this.NamesList.size() - 1;
        }
        this.SyncScroll();
        return true;
    }
    
    public boolean pageDown() {
        this.onLine += this.Viewable;
        if (this.onLine > this.NamesList.size() - 1) {
            this.onLine = this.NamesList.size() - 1;
        }
        this.top += this.Viewable;
        this.SyncScroll();
        return true;
    }
    
    public boolean pageUp() {
        this.onLine -= this.Viewable;
        if (this.onLine < 0) {
            this.onLine = 0;
        }
        this.top -= this.Viewable;
        this.SyncScroll();
        return true;
    }
}
