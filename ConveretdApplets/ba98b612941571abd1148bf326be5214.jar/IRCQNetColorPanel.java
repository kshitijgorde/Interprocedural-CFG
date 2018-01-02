import java.awt.Event;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetColorPanel extends Canvas
{
    private IRCQNet theApp;
    private int width;
    private int height;
    private Vector vColor;
    private int minWidth;
    private int minHeight;
    private boolean noFrame;
    private Vector vRect;
    private boolean ModeLine;
    
    public IRCQNetColorPanel(final IRCQNet theApp) {
        this.vColor = new Vector(16, 1);
        this.minWidth = 60;
        this.minHeight = 60;
        this.noFrame = false;
        this.vRect = new Vector(16, 1);
        this.ModeLine = false;
        this.theApp = theApp;
        this.initvColor();
    }
    
    public void setNoFrame(final boolean noFrame) {
        this.noFrame = noFrame;
    }
    
    public void senMinSize(final int minWidth, final int minHeight) {
        this.minWidth = minWidth;
        this.minHeight = minHeight;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        int n = this.width / 8;
        int height = this.height / 2;
        if (this.ModeLine) {
            n = this.width / 16;
            height = this.height;
        }
        if (!this.noFrame) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, this.width - 1, this.height - 1);
        }
        this.vRect.removeAllElements();
        if (this.ModeLine) {
            for (int i = 0; i < 16; ++i) {
                graphics.setColor((Color)this.vColor.elementAt(i));
                graphics.fill3DRect(i * n, 0, n, height, true);
                this.vRect.addElement(new Rectangle(i * n, 0, n, height));
            }
            return;
        }
        for (int j = 0; j < 2; ++j) {
            for (int k = j * 8; k < 8 * (j + 1); ++k) {
                graphics.setColor((Color)this.vColor.elementAt(k));
                graphics.fill3DRect((k - j * 8) * n, j * height, n, height, true);
                this.vRect.addElement(new Rectangle((k - j * 8) * n, j * height, n, height));
            }
        }
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
    }
    
    public void initvColor() {
        this.vColor.addElement(new Color(255, 255, 255));
        this.vColor.addElement(new Color(0, 0, 0));
        this.vColor.addElement(new Color(0, 0, 123));
        this.vColor.addElement(new Color(0, 146, 0));
        this.vColor.addElement(new Color(255, 0, 0));
        this.vColor.addElement(new Color(123, 0, 0));
        this.vColor.addElement(new Color(156, 0, 156));
        this.vColor.addElement(new Color(255, 125, 0));
        this.vColor.addElement(new Color(255, 255, 0));
        this.vColor.addElement(new Color(0, 255, 0));
        this.vColor.addElement(new Color(0, 146, 148));
        this.vColor.addElement(new Color(0, 255, 255));
        this.vColor.addElement(new Color(0, 0, 255));
        this.vColor.addElement(new Color(255, 0, 255));
        this.vColor.addElement(new Color(123, 125, 123));
        this.vColor.addElement(new Color(214, 211, 214));
    }
    
    public void setModeLine(final boolean modeLine) {
        this.ModeLine = modeLine;
        if (modeLine) {
            this.minWidth = 10;
            this.minHeight = 10;
            return;
        }
        this.minWidth = 60;
        this.minHeight = 60;
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.minWidth, this.minHeight);
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.minWidth, this.minHeight);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int size = this.vRect.size(), i = 0; i < size; ++i) {
            if (((Rectangle)this.vRect.elementAt(i)).inside(n, n2)) {
                this.postEvent(new Event(this, 1001, new Integer(i)));
            }
        }
        return super.mouseDown(event, n, n2);
    }
}
