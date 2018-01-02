import java.awt.Container;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvScrollMessage extends Canvas implements Runnable
{
    private static final int HEIGHT = 30;
    private static final Dimension SIZE;
    protected static final int FailedToGetCameraControl = 0;
    protected static final int CameraControlDisabled = 1;
    protected static final int CameraIsOffLine = 2;
    private String[] messageArray;
    private int width;
    private int height;
    private int fontYpos;
    private int index;
    private int delay;
    private Thread thread;
    private Graphics backGC;
    private Image backBuffer;
    private boolean isScrollMode;
    private String fontName;
    
    static {
        SIZE = new Dimension(0, 30);
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
            this.index = -1;
        }
        if (this.backGC != null) {
            this.backGC.setColor(this.getBackground());
            this.backGC.fillRect(0, 0, this.width, this.height);
        }
        this.repaint();
    }
    
    public WvScrollMessage(final WvDispatcher wvdispatcher) {
        this.messageArray = new String[3];
        this.delay = 80;
        this.fontName = "Dialog";
        if (wvdispatcher.isEnglish()) {
            final String[] messageArray = this.messageArray;
            final int n = 0;
            final String stringObject = wvdispatcher.getStringObject("no_privilege_msg");
            messageArray[n] = stringObject;
            if (stringObject == null || !wvdispatcher.IsAscii(this.messageArray[2])) {
                this.messageArray[0] = "couldn't get camera control";
            }
            final String[] messageArray2 = this.messageArray;
            final int n2 = 1;
            final String stringObject2 = wvdispatcher.getStringObject("no_control_msg");
            messageArray2[n2] = stringObject2;
            if (stringObject2 == null || !wvdispatcher.IsAscii(this.messageArray[2])) {
                this.messageArray[1] = "camera control is disabled";
            }
            final String[] messageArray3 = this.messageArray;
            final int n3 = 2;
            final String stringObject3 = wvdispatcher.getStringObject("camera_off_msg");
            messageArray3[n3] = stringObject3;
            if (stringObject3 == null || !wvdispatcher.IsAscii(this.messageArray[2])) {
                this.messageArray[2] = "camera is off-line or abnormal";
            }
        }
        else {
            if ((this.messageArray[0] = wvdispatcher.getStringObject("no_privilege_msg")) == null) {
                this.messageArray[0] = "\u5236\u5fa1\u6a29\u5f85\u3061\u306b\u5165\u308c\u307e\u305b\u3093\u3067\u3057\u305f";
            }
            if ((this.messageArray[1] = wvdispatcher.getStringObject("no_control_msg")) == null) {
                this.messageArray[1] = "\u30ab\u30e1\u30e9\u5236\u5fa1\u306f\u3067\u304d\u307e\u305b\u3093";
            }
            if ((this.messageArray[2] = wvdispatcher.getStringObject("camera_off_msg")) == null) {
                this.messageArray[2] = "\u30ab\u30e1\u30e9\u304c\u96fb\u6e90OFF\u304b\u7570\u5e38\u3067\u3059";
            }
        }
        this.fontName = wvdispatcher.getFontName();
    }
    
    public void showScrollMessage(final int i) {
        if (i < 0 || 2 < i) {
            return;
        }
        if (this.thread != null && this.index == i) {
            return;
        }
        this.stop();
        this.isScrollMode = true;
        this.index = i;
        (this.thread = new Thread(this, "LiveApplet-ScrollMessage")).start();
    }
    
    public void showBlinkMessage(final int i) {
        if (i < 0 || 2 < i) {
            return;
        }
        this.stop();
        this.isScrollMode = false;
        this.index = i;
        (this.thread = new Thread(this, "LiveApplet-ScrollMessage")).start();
    }
    
    public void paint(final Graphics g) {
        if (this.backBuffer == null && !this.createOffScreen()) {
            return;
        }
        g.drawImage(this.backBuffer, 0, 0, this);
    }
    
    public Dimension minimumSize() {
        return WvScrollMessage.SIZE;
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
        (this.backGC = this.backBuffer.getGraphics()).setColor(this.getBackground());
        this.backGC.fillRect(0, 0, this.width, this.height);
        this.backGC.setFont(new Font(this.fontName, 1, 12));
        final FontMetrics fontmetrics = this.backGC.getFontMetrics();
        this.fontYpos = (this.height - fontmetrics.getHeight()) / 2 + fontmetrics.getAscent();
        return true;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public Dimension preferredSize() {
        return WvScrollMessage.SIZE;
    }
    
    public void run() {
        if (this.backBuffer == null && !this.createOffScreen()) {
            return;
        }
        if (this.index < 0 || 2 < this.index) {
            return;
        }
        final String s = this.messageArray[this.index];
        final int i = this.backGC.getFontMetrics().stringWidth(s);
        if (this.isScrollMode) {
            long l = System.currentTimeMillis();
            int j = this.width;
            while (this.thread != null) {
                if (j <= -i - 20) {
                    break;
                }
                Thread.yield();
                this.backGC.setColor(this.getBackground());
                this.backGC.fillRect(0, 0, this.width, this.height);
                this.backGC.setColor(Color.blue);
                this.backGC.drawString(s, j, this.fontYpos);
                final Graphics g2 = this.getGraphics();
                if (g2 != null) {
                    this.paint(g2);
                }
                l += this.delay;
                try {
                    Thread.sleep(Math.max(0L, l - System.currentTimeMillis()));
                }
                catch (InterruptedException ex) {
                    break;
                }
                j -= 6;
            }
        }
        else {
            Thread.yield();
            this.backGC.setColor(this.getBackground());
            this.backGC.fillRect(0, 0, this.width, this.height);
            this.backGC.setColor(Color.blue);
            this.backGC.drawString(s, (this.width - i) / 2, this.fontYpos);
            final Graphics g3 = this.getGraphics();
            if (g3 != null) {
                this.paint(g3);
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
        }
        this.backGC.setColor(this.getBackground());
        this.backGC.fillRect(0, 0, this.width, this.height);
        final Graphics g4 = this.getGraphics();
        if (g4 != null) {
            this.paint(g4);
        }
        this.thread = null;
        this.index = -1;
        final Container container = this.getParent();
        if (container instanceof WvCardLayoutPanel) {
            ((WvCardLayoutPanel)container).restoreLayout();
        }
    }
}
