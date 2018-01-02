import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvTimeIndicator extends Canvas implements Runnable, WvEventListener, WvFloorListener
{
    private static final int WIDTH = 77;
    private static final int HEIGHT = 19;
    private static final Dimension SIZE;
    private int controlTime;
    private int waitTime;
    private Thread thread;
    private boolean drawClock;
    private Font baldFont;
    private Font plainFont;
    private FontMetrics fm;
    private Image backBuffer;
    private Graphics backGC;
    private WvDispatcher wvDispatcher;
    private WvCameraInfo wvCameraInfo;
    private int prevState;
    private static final int[] personX;
    private static final int[] personY;
    private static final int[] clockX;
    private static final int[] clockY;
    private static final int[][] clockLine;
    
    static {
        SIZE = new Dimension(77, 19);
        personX = new int[] { 10, 10, 13, 11, 11, 13, 16, 18, 18, 16, 19, 19, 10 };
        personY = new int[] { 15, 12, 10, 8, 5, 3, 3, 5, 8, 10, 12, 15, 15 };
        clockX = new int[] { 4, 7, 11, 14, 14, 11, 7, 4, 4 };
        clockY = new int[] { 8, 5, 5, 8, 12, 15, 15, 12, 8 };
        clockLine = new int[][] { { 9, 7, 9, 10 }, { 5, 4, 5, 5 }, { 5, 5, 4, 5 }, { 13, 4, 13, 5 }, { 13, 5, 14, 5 } };
    }
    
    private void stop() {
        if (this.thread != null && this.thread.isAlive()) {
            this.thread.stop();
            this.thread = null;
        }
        this.repaint();
    }
    
    public void kickOff() {
        this.stop();
    }
    
    public void enabledCameraControl(final int i) {
        this.drawClock = true;
        this.controlTime = this.wvDispatcher.getWvCameraInfo().getControlTime();
        this.start();
    }
    
    public void disabledCameraControl() {
        this.repaint();
    }
    
    public void cameraConnected(final boolean flag) {
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(77, 19);
            if (this.backBuffer == null) {
                return;
            }
            this.backGC = this.backBuffer.getGraphics();
            this.fm = this.backGC.getFontMetrics();
            this.backGC.setColor(this.getBackground());
            this.backGC.fillRect(0, 0, 77, 19);
        }
        final int i = this.wvCameraInfo.getCameraState();
        if (i != this.prevState) {
            this.controlTime = this.wvCameraInfo.getControlTime();
            this.waitTime = this.wvCameraInfo.getWaitingTime();
        }
        switch (this.prevState = i) {
            case 0:
            case 4:
            case 7: {
                this.backGC.setColor(this.getBackground());
                this.backGC.fillRect(0, 0, 77, 19);
                break;
            }
            case 3: {
                this.backGC.setColor(Color.green);
                this.backGC.fillRect(0, 0, 77, 19);
                final String s = this.timeToString(this.controlTime);
                this.backGC.setColor(Color.black);
                this.backGC.setFont(this.baldFont);
                this.backGC.drawString(s, 77 - this.fm.stringWidth(s) - 15, 14);
                if (!this.drawClock) {
                    break;
                }
                this.backGC.drawPolygon(WvTimeIndicator.clockX, WvTimeIndicator.clockY, WvTimeIndicator.clockX.length);
                for (int j = 0; j < WvTimeIndicator.clockLine.length; ++j) {
                    this.backGC.drawLine(WvTimeIndicator.clockLine[j][0], WvTimeIndicator.clockLine[j][1], WvTimeIndicator.clockLine[j][2], WvTimeIndicator.clockLine[j][3]);
                }
                break;
            }
            case 1: {
                this.backGC.setColor(this.getBackground());
                this.backGC.fillRect(0, 0, 77, 19);
                this.backGC.setColor(Color.black);
                this.backGC.setFont(this.plainFont);
                this.fm = this.backGC.getFontMetrics();
                final int k = (77 - this.fm.stringWidth("WAIT")) / 2;
                this.backGC.drawString("WAIT", k, this.fm.getHeight());
                break;
            }
            case 2: {
                this.backGC.setColor(this.getBackground());
                this.backGC.fillRect(0, 0, 77, 19);
                final String s2 = this.timeToString(this.waitTime);
                this.backGC.setColor(Color.black);
                this.backGC.setFont(this.baldFont);
                this.backGC.drawString(s2, 77 - this.fm.stringWidth(s2) - 15, 14);
                this.backGC.drawPolygon(WvTimeIndicator.personX, WvTimeIndicator.personY, WvTimeIndicator.personX.length);
                this.backGC.fillPolygon(WvTimeIndicator.personX, WvTimeIndicator.personY, WvTimeIndicator.personX.length);
                int l = 0;
                do {
                    this.backGC.translate(-(l + 2), 0);
                    this.backGC.setColor(this.getBackground());
                    this.backGC.fillPolygon(WvTimeIndicator.personX, WvTimeIndicator.personY, WvTimeIndicator.personX.length);
                    this.backGC.setColor(Color.black);
                    this.backGC.drawPolygon(WvTimeIndicator.personX, WvTimeIndicator.personY, WvTimeIndicator.personX.length);
                } while (++l < 2);
                this.backGC.translate(5, 0);
                break;
            }
            case 5:
            case 6: {
                this.backGC.setColor(Color.red);
                this.backGC.fillRect(0, 0, 77, 19);
                break;
            }
        }
        this.backGC.setColor(this.getBackground());
        this.backGC.draw3DRect(0, 0, 76, 18, false);
        g.drawImage(this.backBuffer, 0, 0, this);
    }
    
    public void disconnect(final int i) {
        this.stop();
        this.repaint();
    }
    
    public Dimension minimumSize() {
        return WvTimeIndicator.SIZE;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void videoConnected(final boolean flag) {
    }
    
    public WvTimeIndicator(final WvDispatcher wvdispatcher) {
        this.controlTime = 10000;
        this.waitTime = 10000;
        this.wvDispatcher = wvdispatcher;
        this.wvCameraInfo = wvdispatcher.getWvCameraInfo();
        this.resize(77, 19);
        this.prevState = this.wvCameraInfo.getCameraState();
        int i = 12;
        FontMetrics fontmetrics;
        do {
            this.plainFont = new Font(wvdispatcher.getFontName(), 0, i);
            fontmetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.plainFont);
        } while (fontmetrics.getHeight() >= 15 && --i > 8);
        this.baldFont = new Font(wvdispatcher.getFontName(), 1, 13);
    }
    
    private void start() {
        if (this.thread != null && this.thread.isAlive()) {
            return;
        }
        (this.thread = new Thread(this, "LiveApplet-TimeIndicator")).start();
    }
    
    public void waitingCameraControl(final int i) {
        this.waitTime = this.wvDispatcher.getWvCameraInfo().getWaitingTime();
        this.start();
    }
    
    public void failedToGetCameraControl() {
        this.repaint();
    }
    
    public Dimension preferredSize() {
        return WvTimeIndicator.SIZE;
    }
    
    public void run() {
        this.drawClock = true;
        boolean flag = false;
        long l = System.currentTimeMillis();
        while (this.thread != null && this.wvDispatcher.getRunnable()) {
            this.repaint();
            try {
                l += 1000L;
                Thread.sleep(Math.max(0L, l - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
            switch (this.wvCameraInfo.getCameraState()) {
                case 3: {
                    if (this.controlTime > 0) {
                        this.controlTime -= 1000;
                    }
                    else {
                        flag = true;
                    }
                    if (flag) {
                        this.drawClock = !this.drawClock;
                        break;
                    }
                    break;
                }
                case 2: {
                    final int waitTime = this.waitTime - 1000;
                    this.waitTime = waitTime;
                    if (waitTime < 0) {
                        return;
                    }
                    this.wvDispatcher.getWvCameraInfo().setWaitingTime(this.waitTime);
                    break;
                }
            }
            Thread.yield();
        }
    }
    
    private String timeToString(final int i) {
        int j;
        if ((j = i / 1000) <= 0) {
            return "";
        }
        final int k = j / 60;
        j %= 60;
        String s = Integer.toString(j);
        final String s2 = Integer.toString(k);
        if (j < 10 && k > 0) {
            s = "0" + s;
        }
        final String s3 = (k <= 0) ? s : (String.valueOf(s2) + ":" + s);
        return s3;
    }
    
    public void connect(final String s) {
    }
}
