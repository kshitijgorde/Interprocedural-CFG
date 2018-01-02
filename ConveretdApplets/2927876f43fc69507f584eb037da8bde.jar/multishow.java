import java.awt.Cursor;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class multishow extends Applet implements Runnable
{
    Thread motor;
    Image art;
    int XSIZE;
    int YSIZE;
    int SIZE;
    String showtype;
    imagehold imhold;
    bgimage bg;
    linkme toplink;
    ion VERSION;
    final Font MFONT;
    int vCursorCurrent;
    boolean mouseover;
    boolean mouseup;
    boolean mousedn;
    boolean mousedrag;
    int mousex;
    int mousey;
    int mouseupx;
    int mouseupy;
    int mousednx;
    int mousedny;
    int mousednimg;
    
    public multishow() {
        this.showtype = "_blank";
        this.MFONT = new Font("Arial", 1, 11);
        this.vCursorCurrent = -1;
        this.mousednimg = -1;
    }
    
    void begin() {
        System.err.println("Host: [" + this.getDocumentBase().getHost() + "]");
        (this.VERSION = new ion()).compare(this.getDocumentBase().getHost(), this.getParameter("id_key"));
        final Graphics graphics = this.art.getGraphics();
        this.toplink = new linkme(graphics, this.XSIZE, "Applet by 6sense.com", "http://www.6sense.com/applets/", this.getParameter("maintext"), this.getParameter("mainlink"), this.VERSION.fullversion);
        this.bg = new bgimage(this, this.XSIZE, this.YSIZE);
        this.imhold = new imagehold(this, graphics, this.XSIZE, this.YSIZE);
        graphics.dispose();
        this.getTarget();
    }
    
    void checkover(final int n, final int n2) {
        int imageOver = 0;
        if (this.toplink.isOver(this.mousex, this.mousey)) {
            this.setCursorNow(12);
            this.showStatus("Link: " + this.toplink.getLink());
        }
        else if (this.imhold.imageDrag(this.mousedn, this.mousex, this.mousey, this.mousednx, this.mousedny)) {
            this.mousednx = this.mousex;
            this.mousedny = this.mousey;
            if (this.mousex < 0 && this.mousey < 0 && this.mousex >= this.XSIZE && this.mousey >= this.YSIZE) {
                this.mousedn = false;
            }
            imageOver = 1;
        }
        else {
            imageOver = this.imhold.imageOver(this, n, n2);
        }
        this.imhold.setNotOver(imageOver);
    }
    
    void checkpressed(final int n, final int n2) {
        if (this.mouseup) {
            if (this.toplink.isOver(this.mouseupx, this.mouseupy)) {
                URL url;
                try {
                    url = new URL(this.toplink.getLink());
                }
                catch (MalformedURLException ex) {
                    return;
                }
                this.getAppletContext().showDocument(url, this.showtype);
            }
            else {
                this.imhold.imageEnter(this, n, n2, this.showtype);
            }
            this.mouseup = false;
        }
    }
    
    void drawImageShow() {
        final Graphics graphics = this.art.getGraphics();
        this.bg.draw(graphics, this.XSIZE, this.YSIZE);
        this.imhold.draw(graphics);
        this.toplink.draw(graphics);
        graphics.dispose();
    }
    
    void getTarget() {
        this.showtype = "_blank";
        final String parameter = this.getParameter("target");
        if (parameter != null) {
            this.showtype = parameter;
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this) {
            switch (event.id) {
                case 504: {
                    this.toplink.linkowner = true;
                    break;
                }
                case 505: {
                    this.toplink.linkowner = false;
                    this.resetvar();
                    break;
                }
                case 503: {
                    this.mousex = event.x;
                    this.mousey = event.y;
                    this.mouseover = true;
                    break;
                }
                case 502: {
                    this.mouseupx = event.x;
                    this.mouseupy = event.y;
                    this.mouseup = true;
                    this.mousedn = false;
                    break;
                }
                case 506: {
                    this.mousex = event.x;
                    this.mousey = event.y;
                    this.mousedrag = true;
                    break;
                }
                case 501: {
                    this.mousednx = event.x;
                    this.mousedny = event.y;
                    this.mousedn = true;
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void init() {
        this.XSIZE = this.size().width;
        this.YSIZE = this.size().height;
        this.SIZE = this.XSIZE * this.YSIZE;
        System.err.println(String.valueOf(this.XSIZE) + " " + this.YSIZE + " " + this.SIZE);
        this.art = this.createImage(this.XSIZE, this.YSIZE);
        this.begin();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.art, 0, 0, null);
    }
    
    public void resetmouse() {
        final boolean b = false;
        this.mousedn = b;
        this.mouseup = b;
        this.mousedrag = b;
        this.mouseover = b;
        final int n = -1000;
        this.mousey = n;
        this.mousex = n;
        final int n2 = -1000;
        this.mouseupy = n2;
        this.mouseupx = n2;
        final int n3 = -1000;
        this.mousedny = n3;
        this.mousednx = n3;
        this.mousednimg = -1;
    }
    
    public void resetvar() {
        this.resetmouse();
        this.imhold.reset();
    }
    
    public void run() {
        System.err.println("Multi-Pic Slide Show Applet (C) 2001 I-Yuan Chen [6sense.com]");
        System.currentTimeMillis();
        while (true) {
            this.checkpressed(this.mouseupx, this.mouseupy);
            this.checkover(this.mousex, this.mousey);
            this.drawImageShow();
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void setCursorNow(final int vCursorCurrent) {
        if (this.vCursorCurrent != vCursorCurrent) {
            this.vCursorCurrent = vCursorCurrent;
            this.setCursor(Cursor.getPredefinedCursor(this.vCursorCurrent));
        }
    }
    
    public void start() {
        (this.motor = new Thread(this)).setPriority(1);
        this.motor.start();
    }
    
    public void stop() {
        this.motor.stop();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
