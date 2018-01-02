// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Event;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Canvas;

public class PanoramaCanvas extends Canvas implements SessionListener
{
    NuSiteProxyViewer applet;
    Image panoImage;
    private Rectangle focusRect;
    private Rectangle viewRect;
    private Rectangle hardRect;
    private Rectangle softRect;
    private WvScope hardScope;
    private WvScope softScope;
    private WvScope viewScope;
    private Rectangle rubberRect;
    private Image backBuffer;
    String debugStr;
    private Graphics backGC;
    private boolean createBackBuffer;
    private Dimension size;
    boolean isPanoramaEmpty;
    private Image bgImage;
    int action;
    ControlSession session;
    private static final int DEFAULTLARGEWIDTH = 480;
    private static final int DEFAULTSMALLWIDTH = 240;
    private static final int NONE = 0;
    private static final int INSIDE = 1;
    private static final int OUTSIDE = 2;
    private static final int RESIZE = 3;
    private WvMapper wvMapper;
    private Rectangle focusOuterRect;
    private Rectangle focusInnerRect;
    private boolean maxDebug;
    private int focusMinWidth;
    private int focusMaxWidth;
    private int cursorType;
    private int mouseDownX;
    private int mouseDownY;
    private int mouseDownCenterX;
    private int mouseDownCenterY;
    private int offsetToCenterX;
    private int offsetToCenterY;
    private boolean onceMoved;
    long debugCount;
    Frame frame;
    
    private PanoramaCanvas() {
        this.debugStr = "";
        this.isPanoramaEmpty = false;
        this.maxDebug = false;
        this.debugCount = 0L;
        this.frame = null;
    }
    
    public PanoramaCanvas(final NuSiteProxyViewer applet) {
        this.debugStr = "";
        this.isPanoramaEmpty = false;
        this.maxDebug = false;
        this.debugCount = 0L;
        this.frame = null;
        this.applet = applet;
        this.size = new Dimension();
        this.hardRect = new Rectangle();
        this.softRect = new Rectangle();
        this.viewRect = new Rectangle();
        this.focusRect = new Rectangle();
        this.focusOuterRect = new Rectangle();
        this.focusInnerRect = new Rectangle();
        this.rubberRect = new Rectangle();
        try {
            this.panoImage = applet.loadImage("/pano.img?doc=" + applet.getDoc());
            this.println("Pano Image:" + this.panoImage.getWidth(null) + "," + this.panoImage.getHeight(null));
            this.initPanoramaCanvas(this.panoImage);
        }
        catch (Exception e) {
            applet.report(e);
        }
    }
    
    public void println(final Object t) {
        if (this.maxDebug) {
            this.applet.println(t.toString());
        }
    }
    
    public void stateChanged(final int state, final int queuePos, final int queueLen) {
        switch (state) {
            case 3: {
                this.setEnabled(true);
                this.setCursor(1);
                break;
            }
            default: {
                this.setEnabled(false);
                this.setCursor(0);
                this.action = 0;
                break;
            }
        }
        this.repaint();
    }
    
    String ds(final double d) {
        final int t = (int)(d * 10000.0);
        return new StringBuffer().append(t / 100.0).toString();
    }
    
    void mapRect() {
        if (this.session != null) {
            this.mapRect(this.session.getPan(), this.session.getTilt(), this.session.getZoom());
        }
    }
    
    void mapRect(final int p, final int t, final int z) {
        int xrange = this.session.maxPan - this.session.minPan;
        int yrange = this.session.maxTilt - this.session.minTilt;
        final int zrange = this.session.maxZoom - this.session.minZoom;
        double xpercent = (p - this.session.minPan) / xrange;
        double ypercent = (t - this.session.minTilt) / yrange;
        final double zpercent = (z - this.session.minZoom) / zrange;
        xrange = this.viewScope.getDegRangeX();
        yrange = this.viewScope.getDegRangeY();
        xpercent = (p - this.viewScope.pMin) / xrange;
        ypercent = (t - this.viewScope.tMin) / yrange;
        this.println("xp = " + xpercent + "," + ypercent + "," + zpercent);
        this.focusRect.x = (int)Math.round(this.size.width * xpercent);
        this.focusRect.y = (int)Math.round(this.size.height * ypercent);
        this.focusRect.width = (int)Math.round((1.0 - zpercent) * 50.0) + 2;
        this.focusRect.height = (int)Math.round(this.focusRect.width * 0.75);
        final Rectangle focusRect = this.focusRect;
        focusRect.x -= this.focusRect.width / 2;
        final Rectangle focusRect2 = this.focusRect;
        focusRect2.y -= this.focusRect.height / 2;
        if (this.maxDebug) {
            this.debugStr = String.valueOf(this.ds(xpercent)) + "," + this.ds(ypercent) + "," + this.ds(zpercent) + " rect=" + this.focusRect.x + "," + this.focusRect.y + " [" + this.focusRect.width + "x" + this.focusRect.height + "] ";
        }
    }
    
    public void cameraChanged(final int p, final int t, final int z, final int backlight) {
        this.println("camera Changed:" + p + "," + t + "," + z);
        this.mapRect(p, t, z);
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    void die(final String s) {
        new Exception(s).printStackTrace();
        Thread.yield();
    }
    
    String inspect(final Rectangle big) {
        return big.x + "," + big.y + "," + (big.x + big.width) + "," + (big.y + big.height) + " [" + big.width + "," + big.height + "]";
    }
    
    void assertInside(final Rectangle big, final Rectangle inside) {
        String err = null;
        if (!big.contains(inside.x, inside.y)) {
            err = "point not inside";
        }
        if (!big.contains(inside)) {
            err = "rect not inside";
        }
        if (err != null) {
            this.println(String.valueOf(err) + "\n" + this.inspect(big) + "\n" + this.inspect(inside));
            Thread.yield();
            if (inside.x < big.x) {
                inside.x = big.x;
            }
            if (inside.y < big.y) {
                inside.y = big.y;
            }
            final int maxx = big.x + big.width;
            if (inside.x + inside.width > maxx) {
                inside.width = maxx - (inside.x + inside.width);
            }
            final int maxy = big.y + big.height;
            if (inside.y + inside.height > maxy) {
                inside.height = maxy - (inside.y + inside.height);
            }
        }
    }
    
    public void setSession(final ControlSession session) {
        this.session = session;
        if (session == null) {
            return;
        }
        this.init(this.panoImage);
        session.addListener(this);
    }
    
    public int getWidth() {
        return this.getBounds().width;
    }
    
    public int getHeight() {
        return this.getBounds().height;
    }
    
    public boolean hasFloor() {
        return this.session != null && this.session.inControl();
    }
    
    private void debugpaint(final Graphics g) {
        final int x = 10;
        int y = 20;
        String what = this.debugStr;
        if (this.session != null) {
            what = String.valueOf(what) + this.session.toString();
        }
        what = String.valueOf(what) + " " + this.debugCount++;
        g.setColor(Color.RED);
        g.drawString(what, x, y);
        y += 20;
        if (this.session != null) {
            String q = this.session.querying;
            if (q == null) {
                q = "";
            }
            g.drawString(q, x, y);
            y += 20;
            g.drawString(this.session.cmds1, x, y);
            y += 20;
            g.drawString(this.session.cmds2, x, y);
            y += 20;
        }
    }
    
    public synchronized void paint(final Graphics g) {
        g.setColor(this.hasFloor() ? Color.yellow : Color.blue);
        int i = 0;
        do {
            g.drawRect(this.focusRect.x + i, this.focusRect.y + i, this.focusRect.width - i * 2, this.focusRect.height - i * 2);
        } while (++i < 2);
        i = this.focusRect.x + this.focusRect.width / 2;
        int j = this.focusRect.y + this.focusRect.height / 2;
        g.drawLine(i - 2, j, i + 2, j);
        g.drawLine(i, j - 2, i, j + 2);
        if (this.backBuffer == null || this.createBackBuffer) {
            if (this.size.width <= 0 || this.size.height <= 0) {
                return;
            }
            this.backBuffer = this.createImage(this.size.width, this.size.height);
            if (this.backBuffer == null) {
                return;
            }
            this.backGC = this.backBuffer.getGraphics();
            this.createBackBuffer = false;
        }
        this.backGC.setColor(Color.lightGray);
        this.backGC.fillRect(0, 0, this.size.width, this.size.height);
        if (this.isPanoramaEmpty) {
            g.drawImage(this.backBuffer, 0, 0, this.size.width, this.size.height, this);
            return;
        }
        if (this.bgImage != null) {
            this.backGC.drawImage(this.bgImage, 0, 0, this.size.width, this.size.height, this);
        }
        this.backGC.setColor(Color.gray);
        i = 0;
        do {
            this.backGC.draw3DRect(i, i, this.size.width - i * 2, this.size.height - i * 2, false);
        } while (++i < 2);
        this.backGC.setColor(Color.darkGray);
        if (this.softRect.isEmpty()) {
            if (this.softRect.width == 0) {
                this.backGC.drawLine(this.softRect.x, this.softRect.y, this.softRect.x, this.softRect.y + this.softRect.height);
            }
            if (this.softRect.height == 0) {
                this.backGC.drawLine(this.softRect.x, this.softRect.y, this.softRect.x + this.softRect.width, this.softRect.y);
            }
        }
        else {
            this.backGC.drawRect(this.softRect.x, this.softRect.y, this.softRect.width, this.softRect.height);
        }
        if (this.action == 2 && !this.rubberRect.isEmpty()) {
            this.backGC.setColor(Color.blue);
            this.backGC.drawRect(this.rubberRect.x, this.rubberRect.y, this.rubberRect.width, this.rubberRect.height);
        }
        this.backGC.setColor(this.hasFloor() ? Color.yellow : Color.blue);
        i = 0;
        do {
            this.backGC.drawRect(this.focusRect.x + i, this.focusRect.y + i, this.focusRect.width - i * 2, this.focusRect.height - i * 2);
        } while (++i < 2);
        i = this.focusRect.x + this.focusRect.width / 2;
        j = this.focusRect.y + this.focusRect.height / 2;
        this.backGC.drawLine(i - 2, j, i + 2, j);
        this.backGC.drawLine(i, j - 2, i, j + 2);
        g.drawImage(this.backBuffer, 0, 0, this.size.width, this.size.height, this);
        if (this.maxDebug) {
            this.debugpaint(g);
        }
    }
    
    private void init(Image image) {
        this.bgImage = image;
        this.hardScope = new WvScope(0, this.session);
        this.softScope = new WvScope(1, this.session);
        this.viewScope = new WvScope(2, this.session);
        this.debugScopes();
        int w = 0;
        int h = 0;
        if (image != null) {
            w = image.getWidth(this);
            h = image.getHeight(this);
        }
        this.isPanoramaEmpty = (this.hardScope.isEmpty() && this.softScope.isEmpty());
        if (w <= 0 || h <= 0) {
            image = null;
            this.size.width = ((this.hardScope.getDegRangeX() < 12000) ? 240 : 480);
            this.size.height = this.size.width * this.hardScope.getDegRangeY() / this.hardScope.getDegRangeX();
        }
        else {
            this.size.width = w;
            this.size.height = h;
        }
        if (this.size.height > 320) {
            final double d = 320.0 / this.size.height;
            this.size.width *= (int)d;
            this.size.height = 320;
        }
        (this.wvMapper = new WvMapper(this.hardScope, this.size)).mapRect(this.hardScope, this.hardRect);
        this.wvMapper.mapRect(this.softScope, this.softRect);
        this.wvMapper.mapRect(this.viewScope, this.viewRect);
        this.debugScopes();
        if (!this.hardRect.equals(this.viewRect)) {
            if (image != null) {
                final ImageProducer imageproducer = image.getSource();
                final CropImageFilter cropimagefilter = new CropImageFilter(this.viewRect.x, this.viewRect.y, this.viewRect.width, this.viewRect.height);
                final FilteredImageSource filteredimagesource = new FilteredImageSource(imageproducer, cropimagefilter);
                final Image image2 = Toolkit.getDefaultToolkit().createImage(filteredimagesource);
                try {
                    final MediaTracker mediatracker = new MediaTracker(this);
                    mediatracker.addImage(image2, 0);
                    mediatracker.waitForAll();
                }
                catch (Exception exception) {
                    System.out.println(this + " " + exception);
                }
                this.bgImage = image2;
                this.println("image0=" + image.getWidth(null) + "," + image.getHeight(null));
                this.println("image1=" + image2.getWidth(null) + "," + image2.getHeight(null));
            }
            final double d2 = this.size.width / this.viewScope.getDegRangeX();
            final double d3 = this.size.height / this.viewScope.getDegRangeY();
            if (d2 < d3) {
                this.size.height = (int)(this.viewScope.getDegRangeY() * d2);
            }
            else {
                this.size.width = (int)(this.viewScope.getDegRangeX() * d3);
            }
            final int k1 = this.wvMapper.getWidth(this.hardScope.getZoomMax());
            final int l1 = k1 * 3 / 4;
            if (this.viewRect.width < k1 && this.viewRect.height < l1) {
                this.bgImage = null;
            }
            (this.wvMapper = new WvMapper(this.viewScope, this.size)).mapRect(this.softScope, this.softRect);
        }
        if (this.frame != null) {
            this.resize(this.size.width, this.size.height);
            this.frame.pack();
            final String s1 = "Panorama Controller";
            this.frame.setTitle(s1);
        }
        else {
            Graphics g = this.getGraphics();
            g.clearRect(0, 0, this.size.width * 2, this.size.height * 2);
            final Container container = this.getParent();
            g = container.getGraphics();
            container.update(g);
            this.resize(this.size.width, this.size.height);
            this.invalidate();
            this.getParent().layout();
        }
        this.createBackBuffer = true;
        this.focusMaxWidth = this.wvMapper.getWidth(this.softScope.getZoomMax()) + 1;
        this.focusMinWidth = this.wvMapper.getWidth(this.softScope.getZoomMin()) - 1;
        this.println("focusMin=" + this.focusMinWidth + ", focusMax=" + this.focusMaxWidth);
        this.mapRect();
    }
    
    private String rectDebug(final Rectangle r) {
        return r.x + "," + r.y + " [" + r.width + "x" + r.height + "]";
    }
    
    private void debugScopes() {
        this.println("hard=" + this.hardScope.toString());
        this.println("soft=" + this.softScope.toString());
        this.println("view=" + this.viewScope.toString());
        this.println("hard=" + this.rectDebug(this.hardRect));
        this.println("soft=" + this.rectDebug(this.softRect));
        this.println("view=" + this.rectDebug(this.viewRect));
    }
    
    private void initPanoramaCanvas(final Image image) {
        this.bgImage = image;
        this.hardScope = new WvScope(0, this.session);
        this.softScope = new WvScope(1, this.session);
        this.viewScope = new WvScope(2, this.session);
        int width = 0;
        int height = 0;
        if (image != null) {
            width = image.getWidth(this);
            height = image.getHeight(this);
        }
        this.size.width = width;
        this.size.height = height;
        this.isPanoramaEmpty = false;
        if (this.size.height > 320) {
            final double d = 320.0 / this.size.height;
            this.size.width *= (int)d;
            this.size.height = 320;
        }
        this.debugScopes();
        this.setSize(this.size);
        if (this.frame != null) {
            this.resize(this.size.width, this.size.height);
            this.frame.pack();
            final String s1 = "Panorama Controller";
            this.frame.setTitle(s1);
        }
        else {
            Graphics g = this.getGraphics();
            if (g != null) {
                g.clearRect(0, 0, this.size.width * 2, this.size.height * 2);
            }
            final Container container = this.getParent();
            if (container != null) {
                g = container.getGraphics();
                container.update(g);
                this.resize(this.size.width, this.size.height);
                this.invalidate();
                this.getParent().layout();
            }
        }
        this.createBackBuffer = true;
    }
    
    public void disconnect(final int i) {
        this.setCursor(0);
        this.disable();
        this.repaint();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    private void setCursor(int i) {
        if (this.isPanoramaEmpty) {
            i = 0;
        }
        final Cursor c = Cursor.getPredefinedCursor(i);
        if (this.frame != null) {
            this.frame.setCursor(i);
        }
        else {
            this.setCursor(c);
        }
    }
    
    private void moveFocus(final int x, final int y) {
        final int xx = Math.min(Math.max(x, this.softRect.x), this.softRect.x + this.softRect.width);
        final int yy = Math.min(Math.max(y, this.softRect.y), this.softRect.y + this.softRect.height);
        final int posx = xx - this.focusRect.width / 2;
        final int posy = yy - this.focusRect.height / 2;
        this.focusRect.x = posx;
        this.focusRect.y = posy;
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    public void kickOff() {
        this.frame = null;
    }
    
    int getZoomValue(int x) {
        if (x < this.focusMinWidth) {
            x = this.focusMinWidth;
        }
        if (x > this.focusMaxWidth) {
            x = this.focusMaxWidth;
        }
        int range = this.focusMaxWidth - this.focusMinWidth;
        float f = (x - this.focusMinWidth) / range;
        f = 1.0f - f;
        this.println("f=" + f);
        range = this.session.maxZoom - this.session.minZoom;
        f *= range;
        f += this.session.minZoom;
        return Math.round(f);
    }
    
    public boolean mouseUp(final Event event, final int x, final int y) {
        this.println("mouse up: " + event);
        final int k = Math.abs(x - this.mouseDownX) + Math.abs(y - this.mouseDownY);
        switch (this.action) {
            case 2: {
                if (k < 4) {
                    this.moveFocus(x, y);
                    break;
                }
                this.focusRect.width = Math.max(this.rubberRect.width, this.focusMinWidth);
                this.focusRect.height = Math.max(this.rubberRect.height, this.focusMinWidth * 3 / 4);
                this.moveFocus(this.rubberRect.x + this.rubberRect.width / 2, this.rubberRect.y + this.rubberRect.height / 2);
                break;
            }
            case 1:
            case 3: {
                if (x == this.mouseDownX && y == this.mouseDownY && !this.onceMoved) {
                    this.moveFocus(x, y);
                    break;
                }
                break;
            }
        }
        if (this.wvMapper != null) {
            final int p = this.wvMapper.getPanValue(this.focusRect.x + this.focusRect.width / 2);
            final int t = this.wvMapper.getTiltValue(this.focusRect.y + this.focusRect.height / 2);
            int z;
            if (this.action != 2 || k >= 4) {
                z = this.getZoomValue(this.focusRect.width);
            }
            else {
                z = this.session.getZoom();
            }
            try {
                this.session.setPosition(p, t, z);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.rubberRect.setSize(0, 0);
        this.setCursor(this.isEnabled() ? 1 : 0);
        return false;
    }
    
    private int getActionType(final int x, final int y) {
        this.focusInnerRect.reshape(this.focusRect.x, this.focusRect.y, this.focusRect.width, this.focusRect.height);
        this.focusOuterRect.reshape(this.focusRect.x, this.focusRect.y, this.focusRect.width, this.focusRect.height);
        this.focusInnerRect.grow(-2, -2);
        this.focusOuterRect.grow(2, 2);
        int k = 20 - this.focusOuterRect.width;
        if (k > 0) {
            this.focusOuterRect.grow(k / 2, k * 3 / 8);
        }
        k = 12 - this.focusInnerRect.width;
        if (k > 0) {
            this.focusInnerRect.grow(k / 2, k * 3 / 8);
        }
        if (this.focusInnerRect.inside(x, y)) {
            return 1;
        }
        return this.focusOuterRect.inside(x, y) ? 3 : 2;
    }
    
    public Dimension minimumSize() {
        return this.size;
    }
    
    private void makeFocusInsideOfWindow(final Rectangle rectangle) {
        rectangle.x = Math.min(Math.max(rectangle.x, 1), this.size.width - rectangle.width - 1);
        rectangle.y = Math.min(Math.max(rectangle.y, 1), this.size.height - rectangle.height - 1);
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.println("mouseDown: " + event);
        this.action = this.getActionType(x, y);
        this.onceMoved = false;
        this.mouseDownX = x;
        this.mouseDownY = y;
        this.mouseDownCenterX = this.focusRect.x + this.focusRect.width / 2;
        this.mouseDownCenterY = this.focusRect.y + this.focusRect.height / 2;
        this.offsetToCenterX = this.mouseDownCenterX - x;
        this.offsetToCenterY = this.mouseDownCenterY - y;
        this.rubberRect.setSize(0, 0);
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int x, final int y) {
        this.onceMoved = true;
        if (!this.isEnabled()) {
            return false;
        }
        try {
            switch (this.action) {
                case 1: {
                    this.setCursor(13);
                    this.moveFocus(this.offsetToCenterX + x, this.offsetToCenterY + y);
                    break;
                }
                case 2: {
                    final int xx = Math.abs(this.mouseDownX - x);
                    final int yy = Math.abs(this.mouseDownY - y);
                    if (xx * 3 / 4 > yy) {
                        this.rubberRect.width = Math.min(xx, this.focusMaxWidth);
                        this.rubberRect.height = this.rubberRect.width * 3 / 4;
                    }
                    else {
                        this.rubberRect.height = Math.min(yy, this.focusMaxWidth * 3 / 4 + 1);
                        this.rubberRect.width = this.rubberRect.height * 4 / 3;
                    }
                    this.rubberRect.width = Math.max(1, this.rubberRect.width);
                    this.rubberRect.height = Math.max(1, this.rubberRect.height);
                    this.rubberRect.x = ((x <= this.mouseDownX) ? (this.mouseDownX - this.rubberRect.width) : this.mouseDownX);
                    this.rubberRect.y = ((y <= this.mouseDownY) ? (this.mouseDownY - this.rubberRect.height) : this.mouseDownY);
                    break;
                }
                case 3: {
                    final int xoffset = x - this.mouseDownCenterX;
                    final int yoffset = y - this.mouseDownCenterY;
                    final int ax = Math.abs(xoffset);
                    final int ay = Math.abs(yoffset);
                    this.println("mouseDrag: " + x + ", " + y + " " + xoffset + "," + yoffset);
                    this.focusRect.x = this.mouseDownCenterX - ax;
                    this.focusRect.y = this.mouseDownCenterY - ay;
                    this.focusRect.width = ax * 2;
                    this.focusRect.height = ay * 2;
                    if (ax * 3 / 4 > ay) {
                        this.focusRect.height = this.focusRect.width * 3 / 4;
                    }
                    else {
                        this.focusRect.width = this.focusRect.height * 4 / 3;
                    }
                    int cursor = 0;
                    if (xoffset < 0 && yoffset > 0) {
                        cursor = 4;
                    }
                    else if (xoffset > 0 && yoffset > 0) {
                        cursor = 5;
                    }
                    else if (xoffset > 0 && yoffset < 0) {
                        cursor = 7;
                    }
                    else if (xoffset < 0 && yoffset < 0) {
                        cursor = 6;
                    }
                    this.setCursor(cursor);
                    this.focusRect.width = Math.max(this.focusMinWidth, Math.min(this.focusMaxWidth, this.focusRect.width));
                    this.focusRect.height = this.focusRect.width * 3 / 4;
                    this.moveFocus(this.mouseDownCenterX, this.mouseDownCenterY);
                    break;
                }
            }
        }
        catch (Throwable t) {
            this.applet.report(t);
        }
        this.repaint();
        return false;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        this.action = this.getActionType(x, y);
        if (this.frame == null) {
            return false;
        }
        switch (this.action) {
            case 3: {
                this.cursorType = 0;
                final int k = x - (this.focusRect.x + this.focusRect.width / 2);
                final int l = y - (this.focusRect.y + this.focusRect.height / 2);
                if (k < 0 && l > 0) {
                    this.cursorType = 4;
                }
                else if (k > 0 && l > 0) {
                    this.cursorType = 5;
                }
                else if (k > 0 && l < 0) {
                    this.cursorType = 7;
                }
                else if (k < 0 && l < 0) {
                    this.cursorType = 6;
                }
                else if (k == 0) {
                    this.cursorType = ((l <= 0) ? 8 : 9);
                }
                else if (l == 0) {
                    this.cursorType = ((k <= 0) ? 10 : 11);
                }
                this.setCursor(this.cursorType);
                break;
            }
            default: {
                this.setCursor(this.isEnabled() ? 1 : 0);
                break;
            }
        }
        return false;
    }
    
    public void moveRequested(final int p, final int t, final int z, final int backlight) {
        this.mapRect(p, t, z);
        this.repaint();
    }
}
