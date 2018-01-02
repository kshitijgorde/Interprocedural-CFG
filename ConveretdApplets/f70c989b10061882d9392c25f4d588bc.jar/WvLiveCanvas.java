import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Event;
import java.net.URLConnection;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import sun.awt.image.URLImageSource;
import java.net.URL;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvLiveCanvas extends Canvas implements Runnable, WvEventListener, WvFloorListener
{
    private Dimension size;
    private int width;
    private int height;
    private int imageWidth;
    private int imageHeight;
    private int imageXpos;
    private int imageYpos;
    private Image liveImage;
    private Image backBuffer;
    private Graphics backGC;
    private WvDispatcher wvDispatcher;
    private Thread thread;
    private String idStr;
    private boolean videoConnected;
    private int seq;
    private long prevTime;
    private int count;
    private boolean sunAwtImageAvailable;
    private static final int VERSION = 0;
    private static final int FPS = 1;
    private static final int COUNTER = 2;
    private static final int HOSTNAME = 3;
    private static final int COMMENT = 4;
    public static final String[] overlayName;
    private boolean[] overlayShow;
    private String[] overlayStr;
    private Point[] overlayPos;
    private Font overlayFont;
    private Image imposeImage;
    private Point imposePos;
    private WvVideoState wvVideoState;
    private int prevState;
    private boolean drawOpenImage;
    private Font messageFont;
    private String prevMessage;
    private int animationIndex;
    private Font[] fontArray;
    private Color[] colorArray;
    private Image openImage;
    private Image closeImage;
    private int openImageTime;
    private WvStarModel wvStarModel;
    private Applet applet;
    
    static {
        overlayName = new String[] { "version", "fps", "counter", "hostname", "comment" };
    }
    
    private synchronized boolean getLiveImage(final URL url) {
        if (url == null) {
            return false;
        }
        try {
            URLConnection urlconnection = null;
            urlconnection = url.openConnection();
            urlconnection.setDefaultUseCaches(false);
            urlconnection.setUseCaches(false);
            urlconnection.connect();
            final String s = urlconnection.getHeaderField("Livescope-Status");
            if (s == null || !s.startsWith("0")) {
                WvDebug.println(this + "getHeaderField() Error!! : ");
                return false;
            }
            try {
                if (this.liveImage != null) {
                    this.liveImage.flush();
                }
            }
            catch (Exception exception) {
                WvDebug.println(this + " " + exception);
            }
            this.liveImage = null;
            System.gc();
            if (!this.sunAwtImageAvailable) {
                this.liveImage = this.applet.getImage(url);
            }
            else {
                try {
                    final URLImageSource urlimagesource = new URLImageSource(urlconnection);
                    this.liveImage = this.createImage(urlimagesource);
                }
                catch (Exception exception2) {
                    WvDebug.println(this + " " + exception2);
                    return this.sunAwtImageAvailable = false;
                }
            }
            ++this.seq;
            final MediaTracker mediatracker = new MediaTracker(this);
            mediatracker.addImage(this.liveImage, this.seq);
            if (!mediatracker.waitForID(this.seq, 180000L) || !mediatracker.checkID(this.seq, true)) {
                WvDebug.println(this + " image-loading error");
                return false;
            }
            if (mediatracker.isErrorID(this.seq)) {
                WvDebug.println(this + " image-format error");
                return false;
            }
            this.wvDispatcher.putObject("image_height", new Integer(this.liveImage.getHeight(null)));
            this.wvDispatcher.putObject("image_width", new Integer(this.liveImage.getWidth(null)));
        }
        catch (Exception exception3) {
            WvDebug.println("WvLiveCanvas.getLiveImage():" + exception3);
            exception3.printStackTrace();
            return false;
        }
        return true;
    }
    
    private void drawOverlay() {
        for (int i = 0; i < WvLiveCanvas.overlayName.length; ++i) {
            if (this.overlayShow[i] && this.overlayStr[i] != null) {
                this.backGC.setFont(this.overlayFont);
                this.backGC.setColor(Color.black);
                this.backGC.drawString(this.overlayStr[i], this.overlayPos[i].x + 2, this.overlayPos[i].y + 2);
                final Object obj = this.wvDispatcher.getObject("font_color");
                if (obj != null && obj instanceof Color) {
                    this.backGC.setColor((Color)obj);
                }
                else {
                    this.backGC.setColor(Color.white);
                }
                this.backGC.drawString(this.overlayStr[i], this.overlayPos[i].x, this.overlayPos[i].y);
            }
        }
        if (this.imposeImage != null) {
            this.backGC.drawImage(this.imposeImage, this.imposePos.x, this.imposePos.y, null);
        }
    }
    
    public void kickOff() {
        this.videoConnected = false;
        this.count = 0;
        this.initOverlay();
        if (this.thread != null && this.thread.isAlive()) {
            return;
        }
        (this.thread = new Thread(this)).start();
        this.repaint();
    }
    
    public void enabledCameraControl(final int i) {
        this.enable();
    }
    
    public void disabledCameraControl() {
        this.disable();
    }
    
    public void cameraConnected(final boolean flag) {
    }
    
    public void repaint() {
        final Graphics g = this.getGraphics();
        if (g != null) {
            this.paint(g);
            return;
        }
        super.repaint();
    }
    
    public synchronized void paint(final Graphics g) {
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(this.width, this.height);
            if (this.backBuffer == null) {
                return;
            }
            this.backGC = this.backBuffer.getGraphics();
        }
        final int i = this.wvVideoState.getState();
        switch (i) {
            case 2: {
                if (this.prevState != 2) {
                    this.backGC.setColor(Color.black);
                    this.backGC.fillRect(0, 0, this.width, this.height);
                }
                if (this.drawOpenImage) {
                    this.drawImageOnBuffer(this.openImage);
                    break;
                }
                this.drawImageOnBuffer(this.liveImage);
                this.drawOverlay();
                break;
            }
            case 1: {
                if (this.prevState == 0 || this.prevState == 4 || this.prevState == 3) {
                    this.backGC.setColor(Color.black);
                    this.backGC.fillRect(0, 0, this.width, this.height);
                }
                if (this.prevMessage != null && this.prevMessage.compareTo(this.wvVideoState.getMessage()) != 0) {
                    this.backGC.setColor(Color.black);
                    this.backGC.fillRect(0, 0, this.width, this.height);
                }
                if (this.closeImage != null) {
                    this.drawImageOnBuffer(this.closeImage);
                    break;
                }
                this.drawMessage(this.wvVideoState.getMessage(), this.messageFont, Color.yellow, 0);
                break;
            }
            case 0: {
                this.backGC.setColor(Color.black);
                this.backGC.fillRect(0, 0, this.width, this.height);
                if (this.openImage != null) {
                    this.drawImageOnBuffer(this.openImage);
                    break;
                }
                if (this.wvDispatcher.getIntegerObject("font_size") != Integer.MIN_VALUE) {
                    this.backGC.setColor(Color.black);
                    this.backGC.fillRect(0, 0, this.width, this.height);
                    this.drawMessage(this.wvVideoState.getMessage(), this.messageFont, Color.yellow, 0);
                    break;
                }
                this.animationIndex = (this.animationIndex + 1) % 32;
                final int j = (int)(Math.sin(12.566370614359172 * this.animationIndex / 32.0) * this.height / 10.0);
                this.drawMessage(this.wvVideoState.getMessage(), this.fontArray[this.animationIndex], this.colorArray[this.animationIndex], j);
                this.wvStarModel.tick();
                int k = 0;
                do {
                    final int l = this.wvStarModel.xpos[k];
                    final int i2 = this.wvStarModel.ypos[k];
                    final Color color = this.wvStarModel.getColor(k);
                    this.backGC.setColor(color);
                    this.backGC.drawLine(l, i2, l + 1, i2);
                    this.backGC.drawLine(l, i2 + 1, l + 1, i2 + 1);
                } while (++k < 100);
                break;
            }
            case 3:
            case 4: {
                this.backGC.setColor(Color.black);
                this.backGC.fillRect(0, 0, this.width, this.height);
                this.drawMessage(this.wvVideoState.getMessage(), this.messageFont, Color.yellow, 0);
                break;
            }
        }
        g.drawImage(this.backBuffer, 0, 0, this.width, this.height, null);
        this.prevState = i;
        this.prevMessage = this.wvVideoState.getMessage();
    }
    
    public boolean mouseUp(final Event event, int i, int j) {
        if (!this.isEnabled()) {
            return false;
        }
        final Object obj = this.wvDispatcher.getObject("click_action");
        if (obj != null && obj instanceof String && !((String)obj).equalsIgnoreCase("pt")) {
            return false;
        }
        final WvCameraInfo wvcamerainfo = this.wvDispatcher.getWvCameraInfo();
        if (!wvcamerainfo.hasFloor()) {
            return false;
        }
        if (i < this.imageXpos || i > this.imageXpos + this.imageWidth || j < this.imageYpos || j > this.imageYpos + this.imageHeight) {
            return false;
        }
        i -= this.imageXpos;
        j -= this.imageYpos;
        final int k = (2 * i - this.imageWidth) * 100 / this.imageWidth;
        final int l = -(2 * j - this.imageHeight) * 100 / this.imageHeight;
        this.wvDispatcher.asyncStackCommand(String.valueOf("OperateCameraOnScreen".trim()) + "?pan=" + k + "&tilt=" + l);
        return false;
    }
    
    public void setHostnameString(final String s) {
        this.overlayStr[3] = s;
        final FontMetrics fontmetrics = this.getFontMetrics(this.overlayFont);
        final int i = fontmetrics.getHeight();
        if (this.overlayShow[3]) {
            final int j = fontmetrics.stringWidth(this.overlayStr[3]);
            this.overlayPos[3].x = this.imageXpos + this.imageWidth - j - 10;
            this.overlayPos[3].y = this.imageYpos + i;
        }
    }
    
    public void disconnect(final int i) {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        this.repaint();
    }
    
    public Dimension minimumSize() {
        return this.size;
    }
    
    private void drawMessage(final String s, final Font font, final Color color, final int i) {
        if (s == null || this.backGC == null) {
            return;
        }
        this.backGC.setFont(font);
        final FontMetrics fontmetrics = this.backGC.getFontMetrics(font);
        final int j = fontmetrics.getHeight();
        final StringTokenizer stringtokenizer = new StringTokenizer(s, "\n");
        int k = (this.height - stringtokenizer.countTokens() * j) / 2 + j;
        k += i;
        while (stringtokenizer.hasMoreTokens()) {
            final String s2 = stringtokenizer.nextToken();
            final int l = fontmetrics.stringWidth(s2);
            final int i2 = (this.width - l) / 2;
            this.backGC.setColor(Color.black);
            this.backGC.drawString(s2, i2 + 2, k + 2);
            this.backGC.setColor(color);
            this.backGC.drawString(s2, i2, k);
            k += j;
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void videoConnected(final boolean flag) {
        this.videoConnected = flag;
        if (flag) {
            this.wvDispatcher.initAvailImageSize();
        }
    }
    
    private void updatefps() {
        ++this.count;
        if (this.overlayShow[2]) {
            this.overlayStr[2] = Integer.toString(this.count);
        }
        if (this.overlayShow[1]) {
            if (this.count < 5) {
                this.overlayStr[1] = null;
                return;
            }
            final long l = System.currentTimeMillis();
            if (this.count % 5 == 0) {
                final float f = 5000.0f / (l - this.prevTime);
                this.overlayStr[1] = Float.toString(f);
                if (this.overlayStr[1].length() > 4) {
                    this.overlayStr[1] = this.overlayStr[1].substring(0, 4);
                }
                final String[] overlayStr = this.overlayStr;
                final int n = 1;
                overlayStr[n] = String.valueOf(overlayStr[n]) + "(fps)";
                this.prevTime = l;
            }
        }
    }
    
    private void drawImageOnBuffer(final Image image) {
        if (this.backGC == null || image == null) {
            return;
        }
        int i = 10;
        while (true) {
            while (!this.backGC.drawImage(image, this.imageXpos, this.imageYpos, this.imageWidth, this.imageHeight, null)) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
                if (--i <= 0) {
                    this.backGC.drawImage(image, this.imageXpos, this.imageYpos, this.imageWidth, this.imageHeight, null);
                    return;
                }
            }
            continue;
        }
    }
    
    public void waitingCameraControl(final int i) {
        this.disable();
    }
    
    public void failedToGetCameraControl() {
        this.disable();
    }
    
    public Dimension preferredSize() {
        return this.size;
    }
    
    public WvLiveCanvas(final WvDispatcher wvdispatcher, final Applet applet1, final int i, final int j) {
        this.sunAwtImageAvailable = true;
        this.overlayShow = new boolean[WvLiveCanvas.overlayName.length];
        this.overlayStr = new String[WvLiveCanvas.overlayName.length];
        this.overlayPos = new Point[WvLiveCanvas.overlayName.length];
        this.wvDispatcher = wvdispatcher;
        this.applet = applet1;
        this.imageWidth = Math.max(80, Math.min(768, i));
        this.imageHeight = Math.max(60, Math.min(576, j));
        this.wvVideoState = wvdispatcher.getWvVideoState();
        boolean flag = true;
        final Object obj = wvdispatcher.getObject("controllable");
        if (obj != null && obj instanceof Boolean) {
            flag = (boolean)obj;
        }
        this.width = Math.max(flag ? 240 : 80, i);
        this.height = Math.max(flag ? 180 : 60, j);
        this.imageXpos = (flag ? ((this.width - i) / 2) : 0);
        this.imageYpos = (flag ? ((this.height - j) / 2) : 0);
        this.size = new Dimension(this.width, this.height);
        this.resize(this.width, this.height);
        int k = 0;
        do {
            this.overlayShow[k] = false;
            this.overlayPos[k] = new Point(0, 0);
            this.overlayStr[k] = null;
        } while (++k < 5);
        k = ((i <= 160) ? (i / 12) : (i / 16));
        this.overlayFont = new Font(wvdispatcher.getFontName(), 1, k);
        final int l = wvdispatcher.getIntegerObject("font_size");
        if (l == Integer.MIN_VALUE) {
            this.messageFont = new Font(wvdispatcher.getFontName(), 1, this.width / 10);
        }
        else {
            this.messageFont = new Font(wvdispatcher.getFontName(), 1, l);
        }
        this.fontArray = new Font[32];
        this.colorArray = new Color[32];
        int i2 = 0;
        final int j2 = this.width / 12 - 10;
        while (i2 < 32) {
            final double d = 0.19634954084936207 * i2;
            final int k2 = (int)(Math.sin(d) * 10.0) + this.width / 12;
            final int l2 = (k2 - j2) * 255 / 20;
            this.fontArray[i2] = new Font(wvdispatcher.getFontName(), 1, k2);
            this.colorArray[i2] = new Color(l2, l2, 0);
            ++i2;
        }
        this.wvStarModel = new WvStarModel(this.width, this.height);
    }
    
    public void run() {
        this.drawOpenImage = false;
        if (this.openImage != null) {
            final Object obj = this.wvDispatcher.getObject("openImageTime");
            if (obj != null && obj instanceof Integer) {
                final int j = (int)obj;
                this.drawOpenImage = true;
                this.repaint();
                try {
                    Thread.sleep(Math.max(0, j));
                }
                catch (Exception ex) {}
            }
        }
        this.drawOpenImage = false;
        int i = 1800;
        while (!this.videoConnected) {
            this.repaint();
            try {
                Thread.yield();
                Thread.sleep(100L);
            }
            catch (Exception ex2) {}
            if (i <= 0) {
                this.wvDispatcher.stopAll(10013);
                return;
            }
            --i;
        }
        final String base = String.valueOf(this.wvDispatcher.getUrlBaseStr()) + "GetLiveImage?" + this.idStr;
        this.backGC.setColor(Color.black);
        this.backGC.fillRect(0, 0, this.width, this.height);
        this.prevTime = System.currentTimeMillis();
        final Object obj2 = this.wvDispatcher.getObject("bPureJava");
        if (obj2 != null && obj2 instanceof Boolean) {
            this.sunAwtImageAvailable = !(boolean)obj2;
        }
        int k = 0;
        while (this.wvDispatcher.getRunnable() && k < 3) {
            URL url = null;
            try {
                url = new URL(String.valueOf(base) + "&cache=" + System.currentTimeMillis());
            }
            catch (MalformedURLException _ex) {
                WvDebug.println(_ex);
                this.wvDispatcher.stopAll();
                return;
            }
            final boolean flag = this.getLiveImage(url);
            if (flag) {
                this.drawImageOnBuffer(this.liveImage);
                this.updatefps();
                this.repaint();
                k = 0;
            }
            else {
                ++k;
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex3) {}
            }
            Thread.yield();
        }
    }
    
    private void initOverlay() {
        for (int i = 0; i < WvLiveCanvas.overlayName.length; ++i) {
            Object obj = this.wvDispatcher.getObject("show_" + WvLiveCanvas.overlayName[i]);
            this.overlayShow[i] = false;
            if (obj != null && obj instanceof Boolean && (boolean)obj) {
                this.overlayShow[i] = true;
            }
            obj = this.wvDispatcher.getObject(WvLiveCanvas.overlayName[i]);
            if (obj != null && obj instanceof String) {
                this.overlayStr[i] = (String)obj;
            }
        }
        this.overlayStr[1] = "3.00(fps)";
        this.overlayStr[2] = "1234";
        for (int j = 0; j < WvLiveCanvas.overlayName.length; ++j) {
            if (this.overlayShow[j] && this.overlayStr[j] == null) {
                this.overlayShow[j] = false;
            }
        }
        final FontMetrics fontmetrics = this.getFontMetrics(this.overlayFont);
        final int k = fontmetrics.getHeight();
        int l = 0;
        int k2 = this.imageXpos + 10;
        while (l < 3) {
            if (this.overlayShow[l]) {
                this.overlayPos[l].x = k2;
                this.overlayPos[l].y = this.imageYpos + k;
                k2 += fontmetrics.stringWidth(this.overlayStr[l]) + 10;
            }
            ++l;
        }
        this.overlayStr[1] = null;
        this.overlayStr[2] = null;
        if (this.overlayShow[3]) {
            final int i2 = fontmetrics.stringWidth(this.overlayStr[3]);
            this.overlayPos[3].x = this.imageXpos + this.imageWidth - i2 - 10;
            this.overlayPos[3].y = this.imageYpos + k;
        }
        if (this.overlayShow[4]) {
            final int j2 = fontmetrics.stringWidth(this.overlayStr[4]);
            this.overlayPos[4].x = this.imageXpos + (this.imageWidth - j2) / 2;
            this.overlayPos[4].y = this.imageYpos + this.imageHeight - k;
        }
        Object obj2 = this.wvDispatcher.getObject("openImage");
        this.openImage = ((obj2 == null || !(obj2 instanceof Image)) ? null : ((Image)obj2));
        obj2 = this.wvDispatcher.getObject("closeImage");
        this.closeImage = ((obj2 == null || !(obj2 instanceof Image)) ? null : ((Image)obj2));
        obj2 = this.wvDispatcher.getObject("imposeImage");
        if (obj2 != null && obj2 instanceof Image) {
            this.imposeImage = (Image)obj2;
            obj2 = this.wvDispatcher.getObject("imposePos");
            if (obj2 != null && obj2 instanceof Point) {
                this.imposePos = (Point)obj2;
                return;
            }
            final int l2 = (this.width - this.imposeImage.getWidth(this)) / 2;
            final int i3 = (this.height - this.imposeImage.getHeight(this)) / 2;
            this.imposePos = new Point(l2, i3);
        }
    }
    
    public void setCommentString(final String s) {
        this.overlayStr[4] = s;
        final FontMetrics fontmetrics = this.getFontMetrics(this.overlayFont);
        final int i = fontmetrics.getHeight();
        if (this.overlayShow[4]) {
            final int j = fontmetrics.stringWidth(this.overlayStr[4]);
            this.overlayPos[4].x = this.imageXpos + (this.imageWidth - j) / 2;
            this.overlayPos[4].y = this.imageYpos + this.imageHeight - i;
        }
    }
    
    public void connect(final String s) {
        this.idStr = s;
    }
}
