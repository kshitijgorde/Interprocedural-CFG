import java.awt.Frame;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.awt.Component;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fvr2 extends Applet implements Runnable
{
    static final boolean debug = false;
    boolean debugLoad;
    boolean debugEvents;
    boolean debugPaint;
    AMDProgressBarEmbed progbar;
    Image[][] frame;
    int[][] status;
    boolean fatal;
    boolean ready;
    boolean stopped;
    boolean dragged;
    boolean isMouseIn;
    Image dBuff;
    int direction;
    int upsideDown;
    Thread me;
    int loadFrameI;
    int loadFrameJ;
    int FrameI;
    int FrameJ;
    int oldx;
    int oldy;
    int zoneI;
    int zoneJ;
    int mouseX;
    int mouseY;
    int dragPixels;
    int progX;
    int progY;
    int marginX;
    int marginY;
    boolean circleN;
    boolean circleM;
    int N;
    int M;
    int initialI;
    int initialJ;
    String modelBase;
    String extension;
    String statusText;
    String currentStatus;
    static int myCursor;
    Hashtable plugins;
    Vector hotspots;
    fvr2Hotspot overHotspot;
    int recall_x;
    int recall_y;
    
    public void init() {
        this.fatal = false;
        this.ready = false;
        boolean b = false;
        final String parameter = this.getParameter("nFrames");
        if (parameter == null) {
            this.N = 1;
        }
        else {
            try {
                this.N = Integer.parseInt(parameter);
                b = true;
            }
            catch (NumberFormatException ex) {
                System.err.println("fvr2: nFrames not a number");
                this.fatal = true;
                this.repaint();
                return;
            }
        }
        final String parameter2 = this.getParameter("mFrames");
        if (parameter2 == null) {
            this.M = 1;
        }
        else {
            try {
                this.M = Integer.parseInt(parameter2);
                b = true;
            }
            catch (NumberFormatException ex2) {
                System.err.println("fvr2: mFrames not a number");
                this.fatal = true;
                this.repaint();
                return;
            }
        }
        if (!b) {
            System.err.println("fvr2: must specify nFrames or mFrames.");
            this.fatal = true;
            this.repaint();
            return;
        }
        this.progX = 20;
        final String parameter3 = this.getParameter("progX");
        if (parameter3 != null) {
            try {
                this.progX = Integer.parseInt(parameter3);
            }
            catch (NumberFormatException ex3) {
                System.err.println("fvr2: progX not a number");
                this.fatal = true;
                this.repaint();
                return;
            }
        }
        this.progY = 20;
        final String parameter4 = this.getParameter("progY");
        if (parameter4 != null) {
            try {
                this.progY = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex4) {
                System.err.println("fvr2: progY not a number");
                this.fatal = true;
                this.repaint();
                return;
            }
        }
        this.marginX = 0;
        this.marginY = 0;
        final String parameter5 = this.getParameter("marginX");
        if (parameter5 != null) {
            try {
                this.marginX = Integer.parseInt(parameter5);
            }
            catch (NumberFormatException ex5) {
                System.err.println("fvr2: marginX not a number");
                this.fatal = true;
                this.repaint();
                return;
            }
        }
        final String parameter6 = this.getParameter("marginY");
        if (parameter6 != null) {
            try {
                this.marginY = Integer.parseInt(parameter6);
            }
            catch (NumberFormatException ex6) {
                System.err.println("fvr2: marginY not a number");
                this.fatal = true;
                this.repaint();
                return;
            }
        }
        String parameter7 = this.getParameter("modelBase");
        if (parameter7 == null) {
            parameter7 = "model/";
        }
        this.modelBase = parameter7;
        this.extension = ".jpg";
        final String parameter8 = this.getParameter("imgExt");
        if (parameter8 != null) {
            this.extension = parameter8;
        }
        this.circleN = true;
        if (this.getParameter("noCircleN") != null) {
            this.circleN = false;
        }
        this.circleM = true;
        if (this.getParameter("noCircleM") != null) {
            this.circleM = false;
        }
        if (this.getParameter("ccw") != null) {
            this.direction = -1;
        }
        if (this.getParameter("upsideDown") != null) {
            this.upsideDown = -1;
        }
        this.initialI = 1;
        final String parameter9 = this.getParameter("initialI");
        if (parameter9 != null) {
            this.initialI = Integer.parseInt(parameter9);
        }
        this.initialJ = 1;
        final String parameter10 = this.getParameter("initialJ");
        if (parameter10 != null) {
            this.initialJ = Integer.parseInt(parameter10);
        }
        final Color colorParameter = this.getColorParameter("bgColor");
        if (colorParameter != null) {
            this.setBackground(colorParameter);
        }
        this.statusText = "Freedom VR ready";
        if (this.getParameter("statusText") != null) {
            this.statusText = this.getParameter("statusText");
        }
        this.plugins = new Hashtable();
        final String parameter11 = this.getParameter("plugins");
        if (parameter11 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter11);
            while (stringTokenizer.hasMoreElements()) {
                final String s = (String)stringTokenizer.nextElement();
                Class<?> forName;
                try {
                    forName = Class.forName(s);
                }
                catch (Exception ex7) {
                    this.fatal = true;
                    System.out.println("fvr2 fatal error:  couldn't load plugin " + s);
                    break;
                }
                this.plugins.put(s, forName);
            }
        }
        this.frame = new Image[this.M][this.N];
        this.status = new int[this.M][this.N];
        (this.progbar = new AMDProgressBarEmbed(this)).reshape(this.progX, this.progY, 150, 25);
        this.progbar.setText("Loading VR object");
        this.progbar.setPercent(0.0);
        this.setCursor(3);
        this.FrameI = this.initialI;
        this.FrameJ = this.initialJ;
        this.hotspots = new Vector();
        this.overHotspot = null;
        this.isMouseIn = false;
        System.out.println(this.getAppletInfo());
    }
    
    Color getColorParameter(final String s) {
        final String parameter = this.getParameter(s);
        int int1;
        try {
            int1 = Integer.parseInt(parameter, 16);
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return new Color(int1);
    }
    
    public String getAppletInfo() {
        return "Freedom VR 2.0 (http://www.honeylocust.com/vr/)";
    }
    
    public void start() {
        (this.me = new Thread(this)).start();
    }
    
    public void stop() {
        this.me.stop();
        this.me = null;
    }
    
    public void destroy() {
        System.out.println("Freedom VR destroying self.");
        for (int i = 0; i < this.M; ++i) {
            for (int j = 0; j < this.N; ++j) {
                if (this.frame[i][j] != null) {
                    this.frame[i][j].flush();
                    this.frame[i][j] = null;
                }
            }
        }
        System.gc();
    }
    
    public synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (!this.isActive()) {
            this.notify();
            return false;
        }
        if (image != this.frame[this.loadFrameI - 1][this.loadFrameJ - 1]) {
            System.err.println("fvr2: image update out of sequence.");
            this.fatal = true;
            this.notify();
            return false;
        }
        this.status[this.loadFrameI - 1][this.loadFrameJ - 1] = n;
        this.notify();
        return true;
    }
    
    String frameName(final int n, final int n2) {
        if (this.M == 1) {
            return this.modelBase + n2 + this.extension;
        }
        if (this.N == 1) {
            return this.modelBase + n + this.extension;
        }
        return this.modelBase + n + '_' + n2 + this.extension;
    }
    
    int frameNumber(final int n, final int n2) {
        int n3 = (n - 1) * this.N + n2;
        if (n3 < (this.initialI - 1) * this.N + this.initialJ) {
            ++n3;
        }
        return n3;
    }
    
    boolean loadFrame(final int loadFrameI, final int loadFrameJ) {
        if (this.status[loadFrameI - 1][loadFrameJ - 1] == 32) {
            return true;
        }
        try {
            this.frame[loadFrameI - 1][loadFrameJ - 1] = this.getImage(new URL(this.getDocumentBase(), this.frameName(loadFrameI, loadFrameJ)));
            this.status[loadFrameI - 1][loadFrameJ - 1] = 0;
        }
        catch (MalformedURLException ex) {
            this.fatal = true;
            System.err.println("fvr2: URL " + this.frameName(loadFrameI, loadFrameJ) + " ill-formed");
            this.repaint();
            return false;
        }
        this.loadFrameI = loadFrameI;
        this.loadFrameJ = loadFrameJ;
        if (!this.isActive()) {
            return false;
        }
        synchronized (this) {
            if (!this.prepareImage(this.frame[loadFrameI - 1][loadFrameJ - 1], this)) {
                do {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex2) {}
                    if (!this.isActive()) {
                        // monitorexit(this)
                        return false;
                    }
                    if ((this.status[this.loadFrameI - 1][this.loadFrameJ - 1] & 0x80) != 0x0) {
                        this.fatal = true;
                        this.repaint();
                        // monitorexit(this)
                        return false;
                    }
                    if ((this.status[this.loadFrameI - 1][this.loadFrameJ - 1] & 0x8) != 0x0 && loadFrameI == 0) {
                        this.synchronousRepaint();
                    }
                } while ((this.status[this.loadFrameI - 1][this.loadFrameJ - 1] & 0x20) == 0x0);
            }
            System.gc();
            this.status[loadFrameI - 1][loadFrameJ - 1] = 32;
        }
        return true;
    }
    
    public void run() {
        this.me.setPriority(3);
        System.gc();
        this.setDefaultStatus("Freedom VR: Loading poster frame");
        this.loadFrame(this.initialI, this.initialJ);
        for (int i = 1; i <= this.M; ++i) {
            for (int j = 1; j <= this.N; ++j) {
                this.setDefaultStatus("Freedom VR: Loading frame " + this.frameNumber(i, j) + " of " + this.N * this.M);
                if (!this.loadFrame(i, j)) {
                    return;
                }
                this.progbar.setPercent(this.frameNumber(i, j) / (this.N * this.M));
            }
        }
        this.ready = true;
        this.setDefaultStatus(this.statusText);
        this.setCursor(fvr2.myCursor);
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.overHotspot != null) {
            this.overHotspot.mouseDown();
        }
        this.mouseX = n;
        this.mouseY = n2;
        this.oldx = n;
        this.oldy = n2;
        this.zoneI = 0;
        this.zoneJ = 0;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (this.overHotspot != null) {
            return super.mouseDrag(event, mouseX, mouseY);
        }
        this.dragged = true;
        final int zoneI = (mouseY - this.oldy) / this.dragPixels;
        final int zoneJ = (mouseX - this.oldx) / this.dragPixels;
        if (zoneJ != this.zoneJ && this.N != 1) {
            this.panRight(zoneJ - this.zoneJ);
            this.zoneJ = zoneJ;
        }
        if (zoneI != this.zoneI && this.M != 1) {
            this.tiltDown(zoneI - this.zoneI);
            this.zoneI = zoneI;
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (this.overHotspot == null) {
            final fvr2Hotspot searchHotspots = this.searchHotspots(this.FrameI, this.FrameJ, mouseX, mouseY);
            if (searchHotspots == null) {
                return true;
            }
            (this.overHotspot = searchHotspots).mouseOver();
        }
        else {
            final fvr2Hotspot overHotspot = this.overHotspot;
            if (!overHotspot.isMouseOver(this.FrameI, this.FrameJ, mouseX, mouseY)) {
                this.overHotspot = null;
                overHotspot.mouseOut();
                this.setCursor(fvr2.myCursor);
                this.showStatus(this.statusText);
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (this.dragged) {
            this.dragged = false;
            this.paintHotspots(this.getGraphics());
            return true;
        }
        final fvr2Hotspot overHotspot = this.overHotspot;
        if (overHotspot != null) {
            overHotspot.mouseUp();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.isMouseIn = true;
        this.showStatus(this.currentStatus);
        this.requestFocus();
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.isMouseIn = false;
        if (this.overHotspot != null) {
            this.overHotspot.mouseOut();
            this.overHotspot = null;
        }
        this.repaint();
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1006) {
            this.panLeft(1);
            return true;
        }
        if (n == 1007) {
            this.panRight(1);
            return true;
        }
        if (n == 1005) {
            this.tiltDown(1);
            return true;
        }
        if (n == 1004) {
            this.tiltUp(1);
            return true;
        }
        if (n == 88 || n == 120) {
            this.recall_x = this.mouseX;
            this.recall_y = this.mouseY;
            System.out.println("Frame [" + this.FrameI + "," + this.FrameJ + "] + (x,y)=(" + this.recall_x + "," + this.recall_y + ")");
            return true;
        }
        if (n == 115 || n == 83) {
            System.out.println("(width,height)=(" + (this.mouseX - this.recall_x) + "," + (this.mouseY - this.recall_y) + ")");
            return true;
        }
        if (n == 10 && this.overHotspot != null) {
            this.overHotspot.mouseUp();
        }
        return true;
    }
    
    public boolean panRight(int n) {
        n *= this.direction;
        this.FrameJ += n;
        if (!this.circleN) {
            if (this.FrameJ < 1) {
                this.FrameJ = 1;
                this.repaint();
                return false;
            }
            if (this.FrameJ >= this.N) {
                this.FrameJ = this.N;
                this.repaint();
                return false;
            }
        }
        while (this.FrameJ < 1) {
            this.FrameJ += this.N;
        }
        this.FrameJ = (this.FrameJ - 1) % this.N + 1;
        this.repaint();
        return true;
    }
    
    public boolean panLeft(final int n) {
        return this.panRight(-n);
    }
    
    public boolean tiltDown(int n) {
        n *= this.upsideDown;
        this.FrameI += n;
        if (!this.circleM) {
            if (this.FrameI < 1) {
                this.FrameI = 1;
                this.repaint();
                return false;
            }
            if (this.FrameI >= this.M) {
                this.FrameI = this.M;
                this.repaint();
                return false;
            }
        }
        while (this.FrameI < 1) {
            this.FrameI += this.M;
        }
        this.FrameI = (this.FrameI - 1) % this.M + 1;
        this.repaint();
        return true;
    }
    
    public boolean tiltUp(final int n) {
        return this.tiltDown(-n);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.fatal) {
            graphics.setColor(Color.red);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            return;
        }
        if (this.frame == null) {
            return;
        }
        if (this.frame[this.FrameI - 1][this.FrameJ - 1] == null) {
            return;
        }
        if (this.status[this.FrameI - 1][this.FrameJ - 1] == 0) {
            return;
        }
        Graphics graphics2 = graphics;
        if (!this.ready) {
            if (this.dBuff == null) {
                this.dBuff = this.createImage(this.size().width, this.size().height);
            }
            graphics2 = this.dBuff.getGraphics();
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(0, 0, this.size().width, this.size().height);
        }
        else {
            this.dBuff = null;
            if (this.marginX != 0) {
                graphics2.clearRect(0, 0, this.marginX, this.size().height);
            }
            final int width = this.frame[this.FrameI - 1][this.FrameJ - 1].getWidth(null);
            if (this.marginX + width < this.size().width) {
                graphics2.clearRect(this.marginX + width, 0, this.size().width - this.marginX - width, this.size().height);
            }
            if (this.marginY != 0) {
                graphics2.clearRect(0, 0, this.size().width, this.marginY);
            }
            final int height = this.frame[this.FrameI - 1][this.FrameJ - 1].getHeight(null);
            if (this.marginY + height < this.size().width) {
                graphics2.clearRect(0, this.marginY + height, this.size().width, this.size().height - this.marginY - height);
            }
        }
        graphics2.drawImage(this.frame[this.FrameI - 1][this.FrameJ - 1], this.marginX, this.marginY, null);
        if (!this.ready) {
            this.progbar.paint(graphics2);
            graphics.drawImage(this.dBuff, 0, 0, null);
        }
        this.paintHotspots(graphics);
    }
    
    public void paintHotspots(final Graphics graphics) {
        if (this.ready && !this.dragged && this.isMouseIn) {
            final Enumeration<fvr2Hotspot> elements = this.hotspots.elements();
            while (elements.hasMoreElements()) {
                final fvr2Hotspot fvr2Hotspot = elements.nextElement();
                if (fvr2Hotspot.isInFrame(this.FrameI, this.FrameJ)) {
                    fvr2Hotspot.paint(graphics);
                }
            }
        }
    }
    
    public void synchronousRepaint() {
        this.paint(this.getGraphics());
    }
    
    void setCursor(final int cursor) {
        try {
            ((Frame)this.getParent()).setCursor(cursor);
        }
        catch (Exception ex) {}
    }
    
    public fvr2Hotspot newHotspot(final String s) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final Class<fvr2Hotspot> clazz = this.plugins.get(s);
        if (clazz == null) {
            this.fatal = true;
            System.out.println("Hotspot plugin " + s + " not loaded");
            this.repaint();
            return null;
        }
        final fvr2Hotspot fvr2Hotspot = clazz.newInstance();
        fvr2Hotspot.attachApplet(this);
        this.hotspots.addElement(fvr2Hotspot);
        return fvr2Hotspot;
    }
    
    void setDefaultStatus(final String currentStatus) {
        this.showStatus(this.currentStatus = currentStatus);
    }
    
    fvr2Hotspot searchHotspots(final int n, final int n2, final int n3, final int n4) {
        synchronized (this.hotspots) {
            for (int i = 0; i < this.hotspots.size(); ++i) {
                final fvr2Hotspot fvr2Hotspot = this.hotspots.elementAt(i);
                if (fvr2Hotspot.isMouseOver(this.FrameI, this.FrameJ, n3, n4)) {
                    // monitorexit(this.hotspots)
                    return fvr2Hotspot;
                }
            }
        }
        // monitorexit(this.hotspots)
        return null;
    }
    
    public fvr2() {
        this.debugLoad = false;
        this.debugEvents = false;
        this.debugPaint = false;
        this.direction = 1;
        this.upsideDown = 1;
        this.dragPixels = 10;
    }
    
    static {
        fvr2.myCursor = 13;
    }
}
