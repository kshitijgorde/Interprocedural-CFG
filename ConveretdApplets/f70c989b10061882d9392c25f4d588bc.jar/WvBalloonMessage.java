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
    
    static {
        SIZE = new Dimension(0, 30);
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        if (this.backGC != null) {
            this.backGC.setColor(this.getBackground());
            this.backGC.fillRect(0, 0, this.width, this.height);
        }
        final Graphics g = this.getGraphics();
        if (g != null) {
            this.paint(g);
            return;
        }
        this.repaint();
    }
    
    public WvBalloonMessage(final WvDispatcher wvdispatcher) {
        this.arrowFgX = new int[] { -22, -15, -15, -4, -15, -15, -22 };
        this.arrowFgY = new int[] { 4, 4, 11, 0, -11, -4, -4 };
        this.arrowBgX = new int[] { -22, -15, -15, -4, -15, -15, -22 };
        this.arrowBgY = new int[] { 4, 4, 11, 0, -11, -4, -4 };
        this.fontName = "Dialog";
        this.isEnglish = wvdispatcher.isEnglish();
        this.fontName = wvdispatcher.getFontName();
        this.wvDispatcher = wvdispatcher;
    }
    
    public void paint(final Graphics g) {
        if (this.backBuffer == null && !this.createOffScreen()) {
            return;
        }
        g.drawImage(this.backBuffer, 0, 0, this);
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
        final int j = this.height / 2;
        while (i < this.arrowFgX.length) {
            final int[] arrowFgX = this.arrowFgX;
            final int n = i;
            arrowFgX[n] += this.width;
            final int[] arrowFgY = this.arrowFgY;
            final int n2 = i;
            arrowFgY[n2] += j;
            this.arrowBgX[i] = this.arrowFgX[i] + 3;
            this.arrowBgY[i] = this.arrowFgY[i] + 3;
            ++i;
        }
        final FontMetrics fontmetrics = this.backGC.getFontMetrics();
        if (this.isEnglish) {
            final String stringObject = this.wvDispatcher.getStringObject("push_control_msg");
            this.message = stringObject;
            if (stringObject == null || !this.wvDispatcher.IsAscii(this.message)) {
                this.message = "push \"control\" button to get camera control";
            }
            if (fontmetrics.stringWidth(this.message) > this.width - 30 && (this.message = this.wvDispatcher.getStringObject("alt_push_control_msg")) == null) {
                this.message = "push \"control\" button";
            }
        }
        else {
            if ((this.message = this.wvDispatcher.getStringObject("push_control_msg")) == null) {
                this.message = "\u53f3\u306e\u30dc\u30bf\u30f3\u3092\u62bc\u3057\u3066\u5236\u5fa1\u6a29\u3092\u53d6\u3063\u3066\u304f\u3060\u3055\u3044";
            }
            if (fontmetrics.stringWidth(this.message) > this.width - 30 && (this.message = this.wvDispatcher.getStringObject("alt_push_control_msg")) == null) {
                this.message = "\u53f3\u306e\u30dc\u30bf\u30f3\u3092\u62bc\u3057\u3066\u4e0b\u3055\u3044";
            }
        }
        this.messYpos = (this.height - fontmetrics.getHeight()) / 2 + fontmetrics.getAscent();
        this.messXpos = (this.width - 18 - fontmetrics.stringWidth(this.message)) / 2;
        return true;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
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
        final byte byte0 = 12;
        final int i = byte0 * 2;
        final int j = (this.height - i) / 2;
        final int k = this.width - 18;
        int l = 6;
        do {
            Thread.yield();
            this.backGC.setColor(this.getBackground());
            this.backGC.fillRect(0, 0, this.width, this.height);
            int i2 = 0;
            do {
                this.backGC.setColor((i2 != 0) ? Color.white : Color.lightGray.darker());
                final byte byte2 = (byte)((i2 != 0) ? 0 : 3);
                this.backGC.fillOval(j + byte2, j + byte2, i, i);
                this.backGC.fillOval(k - (i + j) + byte2, j + byte2, i, i);
                this.backGC.fillRect(byte0 + j + byte2, j + byte2, k - (i + j), i);
                this.backGC.fillPolygon((i2 != 0) ? this.arrowFgX : this.arrowBgX, (i2 != 0) ? this.arrowFgY : this.arrowBgY, 7);
            } while (++i2 < 2);
            this.backGC.setColor(Color.black);
            this.backGC.drawArc(j, j, i, i, 90, 180);
            this.backGC.drawArc(k - (i + j), j, i, i, 270, 70);
            this.backGC.drawArc(k - (i + j), j, i, i, 20, 80);
            this.backGC.drawLine(byte0 + j, j, k - (byte0 + j) + 1, j);
            this.backGC.drawLine(byte0 + j, j + i, k - (byte0 + j) + 1, j + i);
            if (this.messXpos != 0 && this.messYpos != 0) {
                if (l % 2 == 0) {
                    this.backGC.setColor(Color.black);
                    this.backGC.drawString(this.message, this.messXpos, this.messYpos);
                }
                int j2 = 0;
                do {
                    this.backGC.drawLine(this.arrowFgX[j2], this.arrowFgY[j2], this.arrowFgX[j2 + 1], this.arrowFgY[j2 + 1]);
                } while (++j2 < 6);
                this.repaint();
                try {
                    Thread.sleep((l % 2 != 0) ? 500 : 1000);
                }
                catch (InterruptedException ex) {}
            }
        } while (--l > 0);
        this.backGC.setColor(this.getBackground());
        this.backGC.fillRect(0, 0, this.width, this.height);
        this.thread = null;
        this.repaint();
        final Graphics g = this.getGraphics();
        if (g != null) {
            this.paint(g);
        }
    }
}
