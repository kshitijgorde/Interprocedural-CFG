import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvBalloonMessage extends Canvas implements Runnable
{
    private static final int HEIGHT = 30;
    private static final Dimension SIZE;
    private int width;
    private int height;
    private int messYpos;
    private int messXpos;
    private boolean isEnglish;
    private Thread thread;
    private Image backBuffer;
    private Graphics backGC;
    private String message;
    private int[] arrowFgX;
    private int[] arrowFgY;
    private int[] arrowBgX;
    private int[] arrowBgY;
    private String fontName;
    WvDispatcher wvDispatcher;
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        if (this.backGC != null) {
            this.backGC.setColor(this.getBackground());
            this.backGC.fillRect(0, 0, this.width, this.height);
        }
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.paint(graphics);
            return;
        }
        this.repaint();
    }
    
    public WvBalloonMessage(final WvDispatcher wvDispatcher) {
        this.arrowFgX = new int[] { -22, -15, -15, -4, -15, -15, -22 };
        this.arrowFgY = new int[] { 4, 4, 11, 0, -11, -4, -4 };
        this.arrowBgX = new int[] { -22, -15, -15, -4, -15, -15, -22 };
        this.arrowBgY = new int[] { 4, 4, 11, 0, -11, -4, -4 };
        this.fontName = "Dialog";
        this.isEnglish = wvDispatcher.isEnglish();
        this.fontName = wvDispatcher.getFontName();
        this.wvDispatcher = wvDispatcher;
    }
    
    public void paint(final Graphics graphics) {
        if (this.backBuffer == null && !this.createOffScreen()) {
            return;
        }
        graphics.drawImage(this.backBuffer, 0, 0, this);
    }
    
    public Dimension minimumSize() {
        return WvBalloonMessage.SIZE;
    }
    
    private synchronized boolean createOffScreen() {
        if (this.backBuffer != null) {
            return true;
        }
        this.width = this.size().width;
        this.height = this.size().height;
        if (this.width == 0 || this.height == 0) {
            return false;
        }
        this.height = Math.max(this.height, 30);
        this.backBuffer = this.createImage(this.width, this.height);
        if (this.backBuffer == null) {
            return false;
        }
        (this.backGC = this.backBuffer.getGraphics()).setFont(new Font(this.fontName, 0, 12));
        this.backGC.setColor(this.getBackground());
        this.backGC.fillRect(0, 0, this.width, this.height);
        int i = 0;
        final int n = this.height / 2;
        while (i < this.arrowFgX.length) {
            final int[] arrowFgX = this.arrowFgX;
            final int n2 = i;
            arrowFgX[n2] += this.width;
            final int[] arrowFgY = this.arrowFgY;
            final int n3 = i;
            arrowFgY[n3] += n;
            this.arrowBgX[i] = this.arrowFgX[i] + 3;
            this.arrowBgY[i] = this.arrowFgY[i] + 3;
            ++i;
        }
        final FontMetrics fontMetrics = this.backGC.getFontMetrics();
        if (this.isEnglish) {
            final String stringObject = this.wvDispatcher.getStringObject("push_control_msg");
            this.message = stringObject;
            if (stringObject == null || !this.wvDispatcher.IsAscii(this.message)) {
                this.message = "push \"control\" button to get camera control";
            }
            if (fontMetrics.stringWidth(this.message) > this.width - 30 && (this.message = this.wvDispatcher.getStringObject("alt_push_control_msg")) == null) {
                this.message = "push \"control\" button";
            }
        }
        else {
            if ((this.message = this.wvDispatcher.getStringObject("push_control_msg")) == null) {
                this.message = "\u53f3\u306e\u30dc\u30bf\u30f3\u3092\u62bc\u3057\u3066\u5236\u5fa1\u6a29\u3092\u53d6\u3063\u3066\u304f\u3060\u3055\u3044";
            }
            if (fontMetrics.stringWidth(this.message) > this.width - 30 && (this.message = this.wvDispatcher.getStringObject("alt_push_control_msg")) == null) {
                this.message = "\u53f3\u306e\u30dc\u30bf\u30f3\u3092\u62bc\u3057\u3066\u4e0b\u3055\u3044";
            }
        }
        this.messYpos = (this.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        this.messXpos = (this.width - 18 - fontMetrics.stringWidth(this.message)) / 2;
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        SIZE = new Dimension(0, 30);
    }
    
    public void start() {
        if (this.thread != null && this.thread.isAlive()) {
            return;
        }
        (this.thread = new Thread(this)).start();
    }
    
    public Dimension preferredSize() {
        return WvBalloonMessage.SIZE;
    }
    
    public void run() {
        if (this.backBuffer == null && !this.createOffScreen()) {
            return;
        }
        final int n = 12;
        final int n2 = n * 2;
        final int n3 = (this.height - n2) / 2;
        final int n4 = this.width - 18;
        int n5 = 6;
        do {
            Thread.yield();
            this.backGC.setColor(this.getBackground());
            this.backGC.fillRect(0, 0, this.width, this.height);
            int n6 = 0;
            do {
                this.backGC.setColor((n6 == 0) ? Color.lightGray.darker() : Color.white);
                final int n7 = (n6 == 0) ? 3 : 0;
                this.backGC.fillOval(n3 + n7, n3 + n7, n2, n2);
                this.backGC.fillOval(n4 - (n2 + n3) + n7, n3 + n7, n2, n2);
                this.backGC.fillRect(n + n3 + n7, n3 + n7, n4 - (n2 + n3), n2);
                this.backGC.fillPolygon((n6 == 0) ? this.arrowBgX : this.arrowFgX, (n6 == 0) ? this.arrowBgY : this.arrowFgY, 7);
            } while (++n6 < 2);
            this.backGC.setColor(Color.black);
            this.backGC.drawArc(n3, n3, n2, n2, 90, 180);
            this.backGC.drawArc(n4 - (n2 + n3), n3, n2, n2, 270, 70);
            this.backGC.drawArc(n4 - (n2 + n3), n3, n2, n2, 20, 80);
            this.backGC.drawLine(n + n3, n3, n4 - (n + n3) + 1, n3);
            this.backGC.drawLine(n + n3, n3 + n2, n4 - (n + n3) + 1, n3 + n2);
            if (this.messXpos != 0 && this.messYpos != 0) {
                if (n5 % 2 == 0) {
                    this.backGC.setColor(Color.black);
                    this.backGC.drawString(this.message, this.messXpos, this.messYpos);
                }
                int n8 = 0;
                do {
                    this.backGC.drawLine(this.arrowFgX[n8], this.arrowFgY[n8], this.arrowFgX[n8 + 1], this.arrowFgY[n8 + 1]);
                } while (++n8 < 6);
                this.repaint();
                try {
                    Thread.sleep((n5 % 2 == 0) ? 1000 : 500);
                }
                catch (InterruptedException ex) {}
            }
        } while (--n5 > 0);
        this.backGC.setColor(this.getBackground());
        this.backGC.fillRect(0, 0, this.width, this.height);
        this.thread = null;
        this.repaint();
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.paint(graphics);
        }
    }
}
