import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Event;
import java.io.InputStream;
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
    private boolean highSpeedMode;
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
    private byte[] wkbuffer;
    
    private synchronized boolean getLiveImage(final URL url) {
        if (url == null) {
            return false;
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setDefaultUseCaches(false);
            openConnection.setUseCaches(false);
            openConnection.connect();
            final int integerObject = this.wvDispatcher.getIntegerObject("hdrdelay");
            if (integerObject >= 0) {
                Thread.sleep(integerObject);
            }
            final String headerField = openConnection.getHeaderField("Livescope-Status");
            if (headerField == null || !headerField.startsWith("0")) {
                WvDebug.println(this + "getHeaderField() Error!! : ");
                return false;
            }
            try {
                if (this.liveImage != null) {
                    this.liveImage.flush();
                }
            }
            catch (Exception ex) {
                WvDebug.println(this + " " + ex);
            }
            this.liveImage = null;
            if (this.highSpeedMode) {
                final InputStream inputStream = openConnection.getInputStream();
                int n = 0;
                final int contentLength = openConnection.getContentLength();
                int read;
                do {
                    n += (read = inputStream.read(this.wkbuffer, n, contentLength - n));
                } while (read != -1 && n != contentLength);
                this.liveImage = this.getToolkit().createImage(this.wkbuffer, 0, contentLength);
            }
            else {
                System.gc();
                if (!this.sunAwtImageAvailable) {
                    this.liveImage = this.applet.getImage(url);
                }
                else {
                    try {
                        this.liveImage = this.createImage(new URLImageSource(openConnection));
                    }
                    catch (Exception ex2) {
                        WvDebug.println(this + " " + ex2);
                        this.sunAwtImageAvailable = false;
                        this.highSpeedMode = true;
                        return false;
                    }
                }
            }
            ++this.seq;
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.liveImage, this.seq);
            if (!mediaTracker.waitForID(this.seq, 180000L) || !mediaTracker.checkID(this.seq, true)) {
                WvDebug.println(this + " image-loading error");
                return false;
            }
            if (mediaTracker.isErrorID(this.seq)) {
                WvDebug.println(this + " image-format error");
                return false;
            }
            this.wvDispatcher.putObject("image_height", new Integer(this.liveImage.getHeight(null)));
            this.wvDispatcher.putObject("image_width", new Integer(this.liveImage.getWidth(null)));
        }
        catch (Exception ex3) {
            WvDebug.println("WvLiveCanvas.getLiveImage():" + ex3);
            ex3.printStackTrace();
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
                final Object object = this.wvDispatcher.getObject("font_color");
                if (object != null && object instanceof Color) {
                    this.backGC.setColor((Color)object);
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
    
    public void enabledCameraControl(final int n) {
        this.enable();
    }
    
    public void disabledCameraControl() {
        this.disable();
    }
    
    public void cameraConnected(final boolean b) {
    }
    
    public void repaint() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.paint(graphics);
            return;
        }
        super.repaint();
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(this.width, this.height);
            if (this.backBuffer == null) {
                return;
            }
            this.backGC = this.backBuffer.getGraphics();
        }
        final int state = this.wvVideoState.getState();
        switch (state) {
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
                this.drawMessage(this.wvVideoState.getMessage(), this.fontArray[this.animationIndex], this.colorArray[this.animationIndex], (int)(Math.sin(12.566370614359172 * this.animationIndex / 32.0) * this.height / 10.0));
                this.wvStarModel.tick();
                int n = 0;
                do {
                    final int n2 = this.wvStarModel.xpos[n];
                    final int n3 = this.wvStarModel.ypos[n];
                    this.backGC.setColor(this.wvStarModel.getColor(n));
                    this.backGC.drawLine(n2, n3, n2 + 1, n3);
                    this.backGC.drawLine(n2, n3 + 1, n2 + 1, n3 + 1);
                } while (++n < 100);
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
        graphics.drawImage(this.backBuffer, 0, 0, this.width, this.height, null);
        this.prevState = state;
        this.prevMessage = this.wvVideoState.getMessage();
    }
    
    public boolean mouseUp(final Event event, int n, int n2) {
        if (!this.isEnabled()) {
            return false;
        }
        final Object object = this.wvDispatcher.getObject("click_action");
        if (object != null && object instanceof String && !((String)object).equalsIgnoreCase("pt")) {
            return false;
        }
        if (!this.wvDispatcher.getWvCameraInfo().hasFloor()) {
            return false;
        }
        if (n < this.imageXpos || n > this.imageXpos + this.imageWidth || n2 < this.imageYpos || n2 > this.imageYpos + this.imageHeight) {
            return false;
        }
        n -= this.imageXpos;
        n2 -= this.imageYpos;
        this.wvDispatcher.asyncStackCommand("OperateCameraOnScreen".trim() + "?pan=" + (2 * n - this.imageWidth) * 100 / this.imageWidth + "&tilt=" + -(2 * n2 - this.imageHeight) * 100 / this.imageHeight);
        return false;
    }
    
    public void setHostnameString(final String s) {
        this.overlayStr[3] = s;
        final FontMetrics fontMetrics = this.getFontMetrics(this.overlayFont);
        final int height = fontMetrics.getHeight();
        if (this.overlayShow[3]) {
            this.overlayPos[3].x = this.imageXpos + this.imageWidth - fontMetrics.stringWidth(this.overlayStr[3]) - 10;
            this.overlayPos[3].y = this.imageYpos + height;
        }
    }
    
    public void disconnect(final int n) {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        this.repaint();
    }
    
    public Dimension minimumSize() {
        return this.size;
    }
    
    private void drawMessage(final String s, final Font font, final Color color, final int n) {
        if (s == null || this.backGC == null) {
            return;
        }
        this.backGC.setFont(font);
        final FontMetrics fontMetrics = this.backGC.getFontMetrics(font);
        final int height = fontMetrics.getHeight();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        int n2 = (this.height - stringTokenizer.countTokens() * height) / 2 + height + n;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int n3 = (this.width - fontMetrics.stringWidth(nextToken)) / 2;
            this.backGC.setColor(Color.black);
            this.backGC.drawString(nextToken, n3 + 2, n2 + 2);
            this.backGC.setColor(color);
            this.backGC.drawString(nextToken, n3, n2);
            n2 += height;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void videoConnected(final boolean videoConnected) {
        this.videoConnected = videoConnected;
        if (videoConnected) {
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
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.count % 5 == 0) {
                this.overlayStr[1] = Float.toString(5000.0f / (currentTimeMillis - this.prevTime));
                if (this.overlayStr[1].length() > 4) {
                    this.overlayStr[1] = this.overlayStr[1].substring(0, 4);
                }
                final String[] overlayStr = this.overlayStr;
                final int n = 1;
                overlayStr[n] += "(fps)";
                this.prevTime = currentTimeMillis;
            }
        }
    }
    
    static {
        overlayName = new String[] { "version", "fps", "counter", "hostname", "comment" };
    }
    
    private void drawImageOnBuffer(final Image image) {
        if (this.backGC == null || image == null) {
            return;
        }
        int n = 100;
        while (!this.backGC.drawImage(image, this.imageXpos, this.imageYpos, this.imageWidth, this.imageHeight, null)) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
            if (--n <= 0) {
                break;
            }
        }
        this.backGC.drawImage(image, this.imageXpos, this.imageYpos, this.imageWidth, this.imageHeight, null);
    }
    
    public void waitingCameraControl(final int n) {
        this.disable();
    }
    
    public void failedToGetCameraControl() {
        this.disable();
    }
    
    public Dimension preferredSize() {
        return this.size;
    }
    
    public WvLiveCanvas(final WvDispatcher wvDispatcher, final Applet applet, final int n, final int n2) {
        this.sunAwtImageAvailable = true;
        this.overlayShow = new boolean[WvLiveCanvas.overlayName.length];
        this.overlayStr = new String[WvLiveCanvas.overlayName.length];
        this.overlayPos = new Point[WvLiveCanvas.overlayName.length];
        this.wvDispatcher = wvDispatcher;
        this.applet = applet;
        this.imageWidth = Math.max(80, Math.min(768, n));
        this.imageHeight = Math.max(60, Math.min(576, n2));
        this.wvVideoState = wvDispatcher.getWvVideoState();
        boolean booleanValue = true;
        final Object object = wvDispatcher.getObject("controllable");
        if (object != null && object instanceof Boolean) {
            booleanValue = (boolean)object;
        }
        this.width = Math.max(booleanValue ? 240 : 80, n);
        this.height = Math.max(booleanValue ? 180 : 60, n2);
        this.imageXpos = (booleanValue ? ((this.width - n) / 2) : 0);
        this.imageYpos = (booleanValue ? ((this.height - n2) / 2) : 0);
        this.size = new Dimension(this.width, this.height);
        this.resize(this.width, this.height);
        int n3 = 0;
        do {
            this.overlayShow[n3] = false;
            this.overlayPos[n3] = new Point(0, 0);
            this.overlayStr[n3] = null;
        } while (++n3 < 5);
        this.overlayFont = new Font(wvDispatcher.getFontName(), 1, (n > 160) ? (n / 16) : (n / 12));
        final int integerObject = wvDispatcher.getIntegerObject("font_size");
        if (integerObject == Integer.MIN_VALUE) {
            this.messageFont = new Font(wvDispatcher.getFontName(), 1, this.width / 10);
        }
        else {
            this.messageFont = new Font(wvDispatcher.getFontName(), 1, integerObject);
        }
        this.fontArray = new Font[32];
        this.colorArray = new Color[32];
        int i = 0;
        final int n4 = this.width / 12 - 10;
        while (i < 32) {
            final int n5 = (int)(Math.sin(0.19634954084936207 * i) * 10.0) + this.width / 12;
            final int n6 = (n5 - n4) * 255 / 20;
            this.fontArray[i] = new Font(wvDispatcher.getFontName(), 1, n5);
            this.colorArray[i] = new Color(n6, n6, 0);
            ++i;
        }
        this.wvStarModel = new WvStarModel(this.width, this.height);
        this.wkbuffer = new byte[1179648];
    }
    
    public void run() {
        this.drawOpenImage = false;
        if (this.openImage != null) {
            final Object object = this.wvDispatcher.getObject("openImageTime");
            if (object != null && object instanceof Integer) {
                final int intValue = (int)object;
                this.drawOpenImage = true;
                this.repaint();
                try {
                    Thread.sleep(Math.max(0, intValue));
                }
                catch (Exception ex) {}
            }
        }
        this.drawOpenImage = false;
        int n = 1800;
        while (!this.videoConnected) {
            this.repaint();
            try {
                Thread.yield();
                Thread.sleep(100L);
            }
            catch (Exception ex2) {}
            if (n <= 0) {
                this.wvDispatcher.stopAll(10013);
                return;
            }
            --n;
        }
        URL url;
        try {
            url = new URL(this.wvDispatcher.getUrlBaseStr() + "GetLiveImage".trim() + "?" + this.idStr + "&serialize_requests=yes");
            WvDebug.println(url.toString());
        }
        catch (MalformedURLException ex3) {
            this.wvDispatcher.stopAll();
            return;
        }
        this.backGC.setColor(Color.black);
        this.backGC.fillRect(0, 0, this.width, this.height);
        this.prevTime = System.currentTimeMillis();
        final Object object2 = this.wvDispatcher.getObject("bPureJava");
        if (object2 != null && object2 instanceof String) {
            if (((String)object2).equalsIgnoreCase("on") || ((String)object2).equalsIgnoreCase("slow")) {
                this.sunAwtImageAvailable = false;
            }
            if (((String)object2).equalsIgnoreCase("fast")) {
                this.highSpeedMode = true;
            }
        }
        int n2 = 0;
        while (this.wvDispatcher.getRunnable() && n2 < 3) {
            if (this.getLiveImage(url)) {
                this.drawImageOnBuffer(this.liveImage);
                this.updatefps();
                this.repaint();
                n2 = 0;
            }
            else {
                ++n2;
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex4) {}
            }
            Thread.yield();
        }
        if (n2 >= 3) {
            this.wvDispatcher.stopAll();
        }
    }
    
    private void initOverlay() {
        for (int i = 0; i < WvLiveCanvas.overlayName.length; ++i) {
            final Object object = this.wvDispatcher.getObject("show_" + WvLiveCanvas.overlayName[i]);
            this.overlayShow[i] = false;
            if (object != null && object instanceof Boolean && (boolean)object) {
                this.overlayShow[i] = true;
            }
            final Object object2 = this.wvDispatcher.getObject(WvLiveCanvas.overlayName[i]);
            if (object2 != null && object2 instanceof String) {
                this.overlayStr[i] = (String)object2;
            }
        }
        this.overlayStr[1] = "3.00(fps)";
        this.overlayStr[2] = "1234";
        for (int j = 0; j < WvLiveCanvas.overlayName.length; ++j) {
            if (this.overlayShow[j] && this.overlayStr[j] == null) {
                this.overlayShow[j] = false;
            }
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.overlayFont);
        final int height = fontMetrics.getHeight();
        int k = 0;
        int x = this.imageXpos + 10;
        while (k < 3) {
            if (this.overlayShow[k]) {
                this.overlayPos[k].x = x;
                this.overlayPos[k].y = this.imageYpos + height;
                x += fontMetrics.stringWidth(this.overlayStr[k]) + 10;
            }
            ++k;
        }
        this.overlayStr[1] = null;
        this.overlayStr[2] = null;
        if (this.overlayShow[3]) {
            this.overlayPos[3].x = this.imageXpos + this.imageWidth - fontMetrics.stringWidth(this.overlayStr[3]) - 10;
            this.overlayPos[3].y = this.imageYpos + height;
        }
        if (this.overlayShow[4]) {
            this.overlayPos[4].x = this.imageXpos + (this.imageWidth - fontMetrics.stringWidth(this.overlayStr[4])) / 2;
            this.overlayPos[4].y = this.imageYpos + this.imageHeight - height;
        }
        final Object object3 = this.wvDispatcher.getObject("openImage");
        this.openImage = ((object3 != null && object3 instanceof Image) ? ((Image)object3) : null);
        final Object object4 = this.wvDispatcher.getObject("closeImage");
        this.closeImage = ((object4 != null && object4 instanceof Image) ? ((Image)object4) : null);
        final Object object5 = this.wvDispatcher.getObject("imposeImage");
        if (object5 != null && object5 instanceof Image) {
            this.imposeImage = (Image)object5;
            final Object object6 = this.wvDispatcher.getObject("imposePos");
            if (object6 != null && object6 instanceof Point) {
                this.imposePos = (Point)object6;
                return;
            }
            this.imposePos = new Point((this.width - this.imposeImage.getWidth(this)) / 2, (this.height - this.imposeImage.getHeight(this)) / 2);
        }
    }
    
    public void setCommentString(final String s) {
        this.overlayStr[4] = s;
        final FontMetrics fontMetrics = this.getFontMetrics(this.overlayFont);
        final int height = fontMetrics.getHeight();
        if (this.overlayShow[4]) {
            this.overlayPos[4].x = this.imageXpos + (this.imageWidth - fontMetrics.stringWidth(this.overlayStr[4])) / 2;
            this.overlayPos[4].y = this.imageYpos + this.imageHeight - height;
        }
    }
    
    public void connect(final String idStr) {
        this.idStr = idStr;
    }
}
