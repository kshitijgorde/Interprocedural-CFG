import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Graphics;
import netscape.javascript.JSObject;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mapplet extends Applet implements MouseListener, MouseMotionListener
{
    String name;
    boolean busy;
    boolean box;
    boolean init;
    Image img;
    Image busyimg;
    double x1;
    double y1;
    double x2;
    double y2;
    int jitter;
    int cursorsize;
    int thickness;
    Color color;
    JSObject window;
    Image offScreenImage;
    Graphics offScreenGraphics;
    Dimension screenSize;
    
    public mapplet() {
        this.busy = false;
        this.box = true;
        this.init = true;
        this.busyimg = null;
        this.x1 = -1.0;
        this.y1 = -1.0;
        this.x2 = -1.0;
        this.y2 = -1.0;
        this.jitter = 5;
        this.cursorsize = 4;
        this.thickness = 1;
        this.color = Color.red;
    }
    
    public void init() {
        String s = null;
        final String t = null;
        URL url = null;
        this.screenSize = this.getSize();
        this.offScreenImage = this.createImage(this.screenSize.width, this.screenSize.height);
        this.offScreenGraphics = this.offScreenImage.getGraphics();
        s = this.getParameter("jitter");
        if (s != null) {
            this.jitter = Integer.parseInt(s);
        }
        s = this.getParameter("color");
        if (s != null) {
            this.color = this.getColorParameter(s);
        }
        s = this.getParameter("thickness");
        if (s != null) {
            this.thickness = Integer.parseInt(s);
        }
        s = this.getParameter("cursorsize");
        if (s != null) {
            this.cursorsize = Integer.parseInt(s);
        }
        s = this.getParameter("busyimage");
        if (s != null) {
            try {
                url = new URL(s);
            }
            catch (MalformedURLException e) {
                url = this.getDocumentBase();
                this.busyimg = this.getImage(url, s);
            }
        }
        s = this.getParameter("box");
        if (s != null && s.equalsIgnoreCase("off")) {
            this.box = false;
        }
        this.name = this.getParameter("name");
        s = this.getParameter("image");
        try {
            url = new URL(s);
        }
        catch (MalformedURLException e) {
            url = this.getDocumentBase();
            this.img = this.getImage(url, s);
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    private Color getColorParameter(final String s) {
        if (s.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (s.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (s.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (s.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (s.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (s.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (s.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (s.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        final StringTokenizer st = new StringTokenizer(s, ",");
        final int r = Integer.parseInt(st.nextToken());
        final int g = Integer.parseInt(st.nextToken());
        final int b = Integer.parseInt(st.nextToken());
        return new Color(r, g, b);
    }
    
    public void boxon() {
        this.box = true;
    }
    
    public void boxoff() {
        this.box = false;
        this.x2 = this.x1;
        this.y2 = this.y1;
        this.repaint();
        new evalThread(this.window, this.name, this.x1, this.y1, this.x2, this.y2, false).start();
    }
    
    public void swap(final String s) {
        URL url = null;
        final MediaTracker tracker = new MediaTracker(this);
        this.busy = true;
        final double n = (this.screenSize.width - 1) / 2.0;
        this.x2 = n;
        this.x1 = n;
        final double n2 = (this.screenSize.height - 1) / 2.0;
        this.y2 = n2;
        this.y1 = n2;
        if (this.busyimg != null) {
            this.repaint();
        }
        try {
            url = new URL(s);
        }
        catch (MalformedURLException e) {
            return;
        }
        tracker.addImage(this.img = this.getImage(url), 0);
        try {
            tracker.waitForID(0);
        }
        catch (InterruptedException e2) {
            return;
        }
        this.busy = false;
        this.repaint();
        new evalThread(this.window, this.name, this.x1, this.y1, this.x2, this.y2, false).start();
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
        final double n = e.getX();
        this.x2 = n;
        this.x1 = n;
        final double n2 = e.getY();
        this.y2 = n2;
        this.y1 = n2;
    }
    
    public void mouseDragged(final MouseEvent e) {
        this.x2 = e.getX();
        this.y2 = e.getY();
        if (!this.box) {
            this.x1 = this.x2;
            this.y1 = this.y2;
        }
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (this.box) {
            this.x2 = e.getX();
            this.y2 = e.getY();
            if (this.x2 > this.screenSize.width) {
                this.x2 = this.screenSize.width - 1;
            }
            if (this.x2 < 0.0) {
                this.x2 = 0.0;
            }
            if (this.y2 > this.screenSize.height) {
                this.y2 = this.screenSize.height - 1;
            }
            if (this.y2 < 0.0) {
                this.y2 = 0.0;
            }
            if (Math.abs(this.x1 - this.x2) <= this.jitter || Math.abs(this.y1 - this.y2) <= this.jitter) {
                this.x2 = this.x1;
                this.y2 = this.y1;
            }
        }
        else {
            this.x2 = this.x1;
            this.y2 = this.y1;
        }
        this.repaint();
        if (!this.busy) {
            new evalThread(this.window, this.name, this.x1, this.y1, this.x2, this.y2, true).start();
        }
    }
    
    public void paint(final Graphics g) {
        this.offScreenGraphics.drawImage(this.img, 0, 0, this);
        this.offScreenGraphics.setColor(this.color);
        if (this.x1 == this.x2 && this.y1 == this.y2) {
            if (this.cursorsize > 0) {
                this.offScreenGraphics.drawLine((int)(this.x2 - this.cursorsize), (int)this.y2, (int)(this.x2 + this.cursorsize), (int)this.y2);
                this.offScreenGraphics.drawLine((int)this.x2, (int)(this.y2 - this.cursorsize), (int)this.x2, (int)(this.y2 + this.cursorsize));
            }
        }
        else {
            final int x = (int)Math.min(this.x1, this.x2);
            final int y = (int)Math.min(this.y1, this.y2);
            final int w = (int)Math.abs(this.x1 - this.x2);
            final int h = (int)Math.abs(this.y1 - this.y2);
            for (int i = 0; i < this.thickness; ++i) {
                this.offScreenGraphics.drawRect(x + i, y + i, w - 2 * i, h - 2 * i);
            }
        }
        if (this.busyimg != null) {
            final int x = this.screenSize.width / 2 - this.busyimg.getWidth(this) / 2;
            final int y = this.screenSize.height / 2 - this.busyimg.getHeight(this) / 2;
            this.offScreenGraphics.drawImage(this.busyimg, x, y, this);
        }
        g.drawImage(this.offScreenImage, 0, 0, this);
    }
    
    public void destroy() {
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
