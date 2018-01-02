import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WrApp extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    Thread kicker;
    DirectColorModel dcm;
    MemoryImageSource mis;
    Image img;
    static final String strLoading = "Applet initializing.";
    static final String strProtocol = "file";
    String s;
    String strBrokenImageName;
    private long lngStart;
    private long lngEnd;
    static long lngFPS;
    private boolean running;
    boolean boolInitialized;
    boolean boolImageError;
    protected int intBackgroundColor;
    protected int intAppletWidth;
    protected int intAppletHeight;
    protected int intAppKey;
    private URL urlDurius;
    private URL urlThis;
    private String[] urlTarget;
    private StringTokenizer tokURL;
    private int intButtonCount;
    private int intRegcode;
    private int intOrientation;
    private String strFrame;
    protected Graphics gfx;
    
    public WrApp() {
        this.running = true;
        this.boolInitialized = false;
        this.boolImageError = false;
        this.strFrame = "";
    }
    
    public void init() {
        try {
            this.urlThis = this.getDocumentBase();
        }
        catch (Exception ex) {}
        try {
            this.urlDurius = new URL("http://www.durius.com/");
        }
        catch (Exception ex2) {}
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.s = this.getParameter("bg");
        if (this.s != null) {
            this.intBackgroundColor = Integer.valueOf(this.s, 16);
            this.setBackground(new Color(this.intBackgroundColor));
        }
        else {
            this.setBackground(new Color(0, 0, 0));
        }
        this.intRegcode = 1;
        this.intButtonCount = 0;
        this.intOrientation = 0;
        this.s = this.getParameter("width");
        if (this.s != null) {
            this.intAppletWidth = Integer.parseInt(this.s);
        }
        this.s = this.getParameter("height");
        if (this.s != null) {
            this.intAppletHeight = Integer.parseInt(this.s);
        }
        this.s = this.getParameter("orientation");
        if (this.s != null) {
            this.intOrientation = ((this.s.toLowerCase().compareTo("v") != 0) ? 1 : 0);
        }
        this.s = this.getParameter("url");
        if (this.s != null && this.s.length() > 1) {
            this.tokURL = new StringTokenizer(this.s);
            this.urlTarget = new String[this.tokURL.countTokens()];
        }
        this.s = this.getParameter("target");
        if (this.s != null) {
            this.strFrame = this.s;
        }
        if (this.tokURL != null) {
            this.setCursor(new Cursor(12));
            while (this.tokURL.hasMoreTokens()) {
                this.s = this.tokURL.nextToken();
                String s = this.s;
                this.s = this.s.toLowerCase();
                if (this.s.startsWith("/")) {
                    s = this.urlThis.getProtocol().toString() + this.urlThis.getHost().toString() + s;
                }
                else if (!this.s.startsWith("mailto:")) {
                    if (!this.s.startsWith("javascript:")) {
                        if (!this.s.startsWith("http://")) {
                            if (!this.s.startsWith("https://")) {
                                if (this.s.startsWith(".")) {
                                    s = this.urlThis.toString().substring(0, this.urlThis.toString().lastIndexOf("/") + 1) + s;
                                }
                                else if (!this.s.startsWith("/") && !this.s.startsWith("javascript:")) {
                                    s = this.urlThis.toString().substring(0, this.urlThis.toString().lastIndexOf("/") + 1) + s;
                                }
                            }
                        }
                    }
                }
                this.urlTarget[this.intButtonCount] = s;
                ++this.intButtonCount;
            }
        }
        this.gfx = this.getGraphics();
        (this.kicker = new Thread(this)).setPriority(10);
        this.kicker.start();
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.navigate(this.getURL(mouseEvent.getX(), mouseEvent.getY()));
    }
    
    public final void navigate(final String s) {
        if (s.startsWith("javascript:")) {
            this.executeJavascriptCommand(s);
            return;
        }
        try {
            if (this.strFrame != "") {
                this.getAppletContext().showDocument(new URL(s), this.strFrame);
            }
            else {
                this.getAppletContext().showDocument(new URL(s));
            }
        }
        catch (Exception ex) {}
    }
    
    private final void executeJavascriptCommand(final String s) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.intButtonCount != 0) {
            this.showStatus(this.getURL(mouseEvent.getX(), mouseEvent.getY()));
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.intButtonCount != 0) {
            this.showStatus(this.getURL(mouseEvent.getX(), mouseEvent.getY()));
        }
        if (this.boolImageError) {
            final String s = new String(this.urlThis.toString());
            this.showStatus("Image '" + s.substring(0, s.lastIndexOf("/")) + "/" + this.strBrokenImageName + "' not found!");
        }
    }
    
    public void render() {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void paint(final Graphics graphics) {
    }
    
    public final void update(final Graphics graphics) {
    }
    
    public final void run() {
        while (this.kicker != null && this.running) {
            if (this.boolInitialized) {
                this.lngStart = System.currentTimeMillis();
                this.render();
                this.lngEnd = System.currentTimeMillis();
                final long n = WrApp.lngFPS - (this.lngEnd - this.lngStart);
                try {
                    if (n > 1L) {
                        final Thread kicker = this.kicker;
                        Thread.sleep(n);
                    }
                    else {
                        final Thread kicker2 = this.kicker;
                        Thread.sleep(5L);
                    }
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    Thread.sleep(100L);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public final void start() {
    }
    
    public final void stop() {
        if (this.kicker != null) {
            this.running = false;
            try {
                this.kicker.join(1000L);
            }
            catch (Exception ex) {}
            this.kicker = null;
        }
    }
    
    public final String getAppletInfo() {
        return "(c) Durius http://www.durius.com/";
    }
    
    private final String getURL(final int n, final int n2) {
        try {
            if (this.intButtonCount < 1) {
                return "";
            }
            if (this.intOrientation == 0) {
                return this.urlTarget[Math.min(this.intButtonCount - 1, n2 / (this.intAppletHeight / this.intButtonCount))];
            }
            return this.urlTarget[Math.min(this.intButtonCount - 1, n / (this.intAppletWidth / this.intButtonCount))];
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    static {
        WrApp.lngFPS = 20L;
    }
}
