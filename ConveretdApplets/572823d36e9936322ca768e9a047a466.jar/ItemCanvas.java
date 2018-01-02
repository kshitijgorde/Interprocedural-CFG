import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ItemCanvas extends Panel implements Runnable
{
    private CascadeMenu cm;
    private Color color;
    private Color border;
    private Color dborder;
    private MenItem[] items;
    private int itemsC;
    private boolean inside;
    private int height;
    private boolean isSub;
    private int maxw;
    private boolean isMain;
    private MenuSet ms;
    private Color bgColor;
    private Thread runThread;
    protected boolean animating;
    private Graphics gbuf;
    private Image buf;
    boolean firstStart;
    protected boolean stopFlag;
    
    public ItemCanvas(final MenuSet ms, final CascadeMenu cm, final boolean isMain) {
        this.color = new Color(0, 0, 122);
        this.itemsC = 0;
        this.isSub = false;
        this.isMain = true;
        this.animating = false;
        this.firstStart = true;
        this.isMain = isMain;
        this.setLayout(null);
        this.itemsC = ms.getSize();
        this.ms = ms;
        this.cm = cm;
        this.setBackground(this.bgColor = cm.getColor("Item", 0, 0));
        this.border = this.brighter(this.bgColor);
        this.dborder = this.darker(this.bgColor);
        this.items = new MenItem[this.itemsC];
        this.maxw = 0;
        for (int i = 0; i < this.itemsC; ++i) {
            this.items[i] = new MenItem(ms.getNextItem(), cm, this);
            if (this.items[i].fontWidth() > this.maxw - 40) {
                this.maxw = this.items[i].fontWidth() + 40;
            }
        }
        if (!cm.useAnim()) {
            this.addItems(true);
        }
    }
    
    public void animate(final int height) {
        this.height = height;
        this.stopFlag = false;
        if (this.cm.useAnim()) {
            for (int i = 0; i < this.itemsC; ++i) {
                this.remove(this.items[i]);
            }
            (this.runThread = new Thread(this)).start();
        }
        else {
            this.setSize(this.getPrefferedWidth(), this.height);
            this.repaint();
        }
    }
    
    public void run() {
        this.animating = true;
        int n = 0;
        if (this.isMain && !this.cm.useVert) {
            while (n < this.height || this.stopFlag) {
                this.setSize(this.getPrefferedWidth(), n);
                n += 10;
                try {
                    Thread.sleep(30L);
                }
                catch (Exception ex) {}
            }
        }
        else {
            while (n < this.getPrefferedWidth() || this.stopFlag) {
                this.setSize(n, this.height);
                n += 10;
                try {
                    Thread.sleep(30L);
                }
                catch (Exception ex2) {}
            }
        }
        this.setSize(this.getPrefferedWidth(), this.height);
        this.animating = false;
        this.addItems(this.firstStart);
    }
    
    public void addItems(final boolean b) {
        final int itemHeight = this.cm.getItemHeight();
        for (int i = 0; i < this.itemsC; ++i) {
            if (b) {
                this.items[i].setBounds(2, 2 + i * itemHeight, this.maxw, itemHeight);
            }
            this.add(this.items[i]);
            this.items[i].repaint();
        }
        this.firstStart = false;
    }
    
    public int getItems() {
        return this.itemsC;
    }
    
    public int getPrefferedWidth() {
        return this.maxw + 10;
    }
    
    public String toString() {
        return "items: " + this.itemsC + " width: " + this.getSize();
    }
    
    private Color brighter(final Color color) {
        int n = color.getRed() + 40;
        int n2 = color.getGreen() + 40;
        int n3 = color.getBlue() + 40;
        if (n > 255) {
            n = 255;
        }
        if (n2 > 255) {
            n2 = 255;
        }
        if (n3 > 255) {
            n3 = 255;
        }
        return new Color(n, n2, n3);
    }
    
    private Color darker(final Color color) {
        int n = color.getRed() - 40;
        int n2 = color.getGreen() - 40;
        int n3 = color.getBlue() - 40;
        if (n < 255) {
            n = 0;
        }
        if (n2 < 255) {
            n2 = 0;
        }
        if (n3 < 255) {
            n3 = 0;
        }
        return new Color(n, n2, n3);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (this.buf == null) {
            this.buf = this.cm.createImage(this.getPrefferedWidth(), this.height);
            this.gbuf = this.buf.getGraphics();
        }
        if (this.cm.useShadow) {
            this.gbuf.setColor(this.border);
            this.gbuf.fillRect(0, 0, width, height);
            this.gbuf.setColor(this.dborder);
            this.gbuf.fillRect(2, 2, width, height);
            this.gbuf.setColor(this.bgColor);
            this.gbuf.fillRect(2, 2, width - 4, height - 4);
        }
        else {
            this.gbuf.setColor(this.bgColor);
            this.gbuf.fillRect(0, 0, width, height);
        }
        graphics.drawImage(this.buf, 0, 0, this.cm);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void removeAll() {
        for (int i = 0; i < this.itemsC; ++i) {
            this.items[i].removeAll();
        }
    }
    
    public void removeNestedSubs() {
        for (int i = 0; i < this.itemsC; ++i) {
            this.items[i].removeAll();
        }
    }
}
