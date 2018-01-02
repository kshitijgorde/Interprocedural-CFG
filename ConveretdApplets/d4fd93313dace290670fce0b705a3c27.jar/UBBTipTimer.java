import java.awt.Point;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class UBBTipTimer implements Runnable
{
    UBBComponent parent;
    Rectangle parentArea;
    Thread timer;
    int tipTime;
    boolean disabled;
    boolean started;
    UBBComponent[] tipTextComponent;
    Rectangle[] tipArea;
    Rectangle activeTipArea;
    Rectangle lastTipArea;
    UBBTextTools.FormattedText[] tipText;
    UBBTextTools.FormattedText tipToShow;
    boolean tipVisible;
    int pointerX;
    int pointerY;
    private static final Color bgColor;
    
    public void stop() {
        this.started = false;
        if (this.timer != null) {
            this.timer = null;
            synchronized (this) {
                this.notify();
            }
        }
        this.tipArea = null;
        this.activeTipArea = null;
        this.lastTipArea = null;
        this.tipText = null;
    }
    
    UBBTipTimer(final UBBComponent parent, final int tipTime) {
        this.parent = parent;
        if (parent != null) {
            this.tipTime = tipTime;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.tipVisible && this.started) {
            int n = this.pointerX - this.parentArea.x;
            int y = this.pointerY - this.parentArea.y;
            final int min = Math.min(this.tipToShow.getWidth() + 7, this.parentArea.width);
            final int n2 = this.tipToShow.getHeight() + 4;
            if (min < this.parentArea.width) {
                if (n + min > this.parentArea.width) {
                    n = this.parentArea.width - min;
                }
            }
            else {
                n = 0;
            }
            if (y + 2 + n2 * 2 < this.parentArea.height) {
                y += n2 + 2;
            }
            else if (y - (n2 + 2) > 0) {
                y -= n2 + 2;
            }
            else {
                y += 2;
            }
            if (y > this.parentArea.y + this.parentArea.height - n2 - 1) {
                y = this.parentArea.y;
            }
            graphics.setColor(UBBTipTimer.bgColor);
            graphics.fillRect(n, y, min, n2);
            this.tipToShow.paint(graphics, n + 4, y + 2, null);
            graphics.setColor(Color.black);
            graphics.drawRect(n, y, min - 1, n2);
        }
    }
    
    static {
        bgColor = new Color(255, 255, 221);
    }
    
    public void start() {
        if (this.parent != null) {
            this.parentArea = this.parent.getBounds().getBounds();
            final Vector<UBBTextTools.FormattedText> vector = new Vector<UBBTextTools.FormattedText>(10);
            this.parent.initTipText(vector, this.parentArea.x, this.parentArea.y);
            final int size = vector.size();
            if (size > 0) {
                final int n = size / 3;
                this.tipTextComponent = new UBBComponent[n];
                this.tipText = new UBBTextTools.FormattedText[n];
                this.tipArea = new Rectangle[n];
                for (int i = n - 1; i >= 0; --i) {
                    this.tipTextComponent[i] = (UBBComponent)vector.elementAt(i * 3);
                    this.tipText[i] = vector.elementAt(i * 3 + 1);
                    this.tipArea[i] = (Rectangle)vector.elementAt(i * 3 + 2);
                }
                if (this.tipTime > 0) {
                    (this.timer = new Thread(this)).start();
                }
            }
            vector.removeAllElements();
        }
    }
    
    public synchronized void run() {
        this.started = true;
        while (true) {
            if (this.activeTipArea != null || this.timer != Thread.currentThread()) {
                this.lastTipArea = this.activeTipArea;
                try {
                    if (this.timer != Thread.currentThread()) {
                        break;
                    }
                    this.wait(this.tipTime);
                }
                catch (InterruptedException ex) {}
                if (this.activeTipArea != null && this.activeTipArea.equals(this.lastTipArea) && !this.tipVisible) {
                    this.tipVisible = true;
                    this.activeTipArea = null;
                    this.parent.updateUBBListeners(false);
                }
                else {
                    if (!this.tipVisible) {
                        continue;
                    }
                    this.tipVisible = false;
                    this.parent.updateUBBListeners(false);
                }
            }
            else {
                try {
                    this.wait();
                }
                catch (InterruptedException ex2) {}
                if (!this.tipVisible) {
                    continue;
                }
                this.tipVisible = false;
                this.parent.updateUBBListeners(false);
            }
        }
    }
    
    public void mouseEvent(int n, final Point point) {
        if (this.started) {
            synchronized (this) {
                n &= 0x7F;
                if (n == 4) {
                    this.activeTipArea = null;
                    this.disabled = true;
                }
                else {
                    if (this.disabled && n != 8) {
                        // monitorexit(this)
                        return;
                    }
                    this.disabled = false;
                    if (n == 2) {
                        this.activeTipArea = null;
                    }
                    else {
                        int i;
                        for (i = 0; i < this.tipArea.length; ++i) {
                            if (this.tipArea[i].contains(point) && !this.tipTextComponent[i].isHidden()) {
                                this.activeTipArea = this.tipArea[i];
                                this.tipToShow = this.tipText[i];
                                this.pointerX = point.x;
                                this.pointerY = point.y;
                                break;
                            }
                        }
                        if (i == this.tipArea.length) {
                            this.activeTipArea = null;
                        }
                        else if (this.activeTipArea == this.lastTipArea) {
                            this.lastTipArea = null;
                        }
                    }
                }
                this.notify();
            }
        }
    }
}
