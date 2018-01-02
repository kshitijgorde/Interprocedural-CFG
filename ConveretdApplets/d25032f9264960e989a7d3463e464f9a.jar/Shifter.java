import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Shifter extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Image offscreenImage2;
    Graphics osg;
    Graphics osg2;
    MediaTracker tracker;
    Image Bild;
    int AppletW;
    int AppletH;
    boolean busy;
    int mouseX;
    int mouseY;
    boolean mousePressed;
    int Zustand;
    boolean dragging;
    boolean dragHor;
    boolean dragStart;
    int dragStartX;
    int dragStartY;
    int dragISection;
    int dragTracerX;
    int dragTracerY;
    int nSections;
    Color FarbeLinien;
    
    public Shifter() {
        this.busy = true;
        this.Zustand = 0;
        this.dragging = false;
        this.dragHor = false;
        this.dragStartX = 0;
        this.dragStartY = 0;
        this.dragISection = 0;
        this.dragTracerX = 0;
        this.dragTracerY = 0;
        this.nSections = 4;
    }
    
    public void init() {
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.tracker = new MediaTracker(this);
        this.FarbeLinien = Color.decode("#" + this.getParameter("bordercolor"));
        this.offscreenImage = this.createImage(this.AppletW * 2, this.AppletH * 2);
        this.osg = this.offscreenImage.getGraphics();
        this.offscreenImage2 = this.createImage(this.AppletW, this.AppletH);
        this.osg2 = this.offscreenImage2.getGraphics();
        this.Zustand = 1;
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
    }
    
    public void paint(final Graphics graphics) {
        if (this.Zustand == 2) {
            this.osg2.drawImage(this.offscreenImage, 0, 0, this);
            this.osg2.setColor(this.FarbeLinien);
            this.osg2.drawRect(0, 0, this.AppletW - 1, this.AppletH - 1);
            if (this.mouseY > this.AppletH - 24 && this.mouseX < 120) {
                this.osg2.setColor(Color.black);
            }
            else {
                this.osg2.setColor(Color.gray);
            }
            this.osg2.drawString("http://www.eigelb.at", 10, this.AppletH - 10);
            graphics.drawImage(this.offscreenImage2, 0, 0, this);
        }
        else if (this.Zustand == 1) {
            this.osg.setColor(Color.white);
            this.osg.fillRect(0, 0, this.AppletW, this.AppletH);
            this.osg.setColor(Color.black);
            this.osg.drawString("loading image...", 10, 20);
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
    }
    
    public void run() {
        this.Bild = this.getImage(this.getDocumentBase(), this.getParameter("image"));
        this.tracker.addImage(this.Bild, 0);
        this.tracker.checkID(0, true);
        while (!this.tracker.checkAll()) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
        this.osg.drawImage(this.Bild, 0, 0, this);
        this.osg.setColor(this.FarbeLinien);
        for (int i = 0; i < this.nSections; ++i) {
            this.osg.drawLine(0, this.getY(i), this.AppletW - 1, this.getY(i));
            this.osg.drawLine(this.getX(i), 0, this.getX(i), this.AppletH - 1);
        }
        for (int j = 0; j < 5; ++j) {
            this.dragISection = (int)(Math.random() * this.nSections);
            if (Math.random() < 0.5) {
                this.dragHor = true;
            }
            else {
                this.dragHor = false;
            }
            if (Math.random() < 0.5) {
                this.shiftSection(-this.AppletW / this.nSections);
            }
            else {
                this.shiftSection(this.AppletW / this.nSections);
            }
        }
        this.Zustand = 2;
        while (this.busy) {
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public int getX(final int n) {
        return (int)(this.AppletW * (1.0 * n / this.nSections));
    }
    
    public int getY(final int n) {
        return (int)(this.AppletH * (1.0 * n / this.nSections));
    }
    
    public void shiftSection(final int n) {
        if (this.dragHor) {
            final int n2 = this.getY(this.dragISection) + 1;
            final int y = this.getY(this.dragISection + 1);
            if (n > 0) {
                this.osg.copyArea(0, n2, this.AppletW, y - n2, n, 0);
                this.osg.copyArea(this.AppletW, n2, n, y - n2, -this.AppletW, 0);
            }
            else {
                this.osg.copyArea(0, n2, -n, y - n2, this.AppletW, 0);
                this.osg.copyArea(-n, n2, this.AppletW, y - n2, n, 0);
            }
        }
        else {
            final int n3 = this.getX(this.dragISection) + 1;
            final int x = this.getX(this.dragISection + 1);
            if (n > 0) {
                this.osg.copyArea(n3, 0, x - n3, this.AppletH, 0, n);
                this.osg.copyArea(n3, this.AppletH, x - n3, n, 0, -this.AppletH);
            }
            else {
                this.osg.copyArea(n3, 0, x - n3, -n, 0, this.AppletH);
                this.osg.copyArea(n3, -n, x - n3, this.AppletH, 0, n);
            }
        }
    }
    
    public void start() {
        if (this.Faden == null) {
            (this.Faden = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.Faden != null) {
            this.Faden.stop();
            this.Faden = null;
            this.busy = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Shifter.this.mouseX = mouseEvent.getX();
            Shifter.this.mouseY = mouseEvent.getY();
            Shifter.this.mousePressed = true;
            if (Shifter.this.mouseY > Shifter.this.AppletH - 24 && Shifter.this.mouseX < 120) {
                Shifter.this.mousePressed = false;
                try {
                    Shifter.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
                Shifter.this.mousePressed = false;
            }
            else {
                Shifter.this.dragStartX = Shifter.this.mouseX;
                Shifter.this.dragStartY = Shifter.this.mouseY;
                Shifter.this.dragTracerX = 0;
                Shifter.this.dragTracerY = 0;
                Shifter.this.dragStart = true;
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (Shifter.this.dragging) {
                if (Shifter.this.dragHor) {
                    while (Shifter.this.dragTracerX < 0) {
                        final Shifter this$0 = Shifter.this;
                        this$0.dragTracerX += Shifter.this.AppletW;
                    }
                    int n = Shifter.this.dragTracerX % (Shifter.this.AppletW / Shifter.this.nSections);
                    if (n > Shifter.this.AppletW / Shifter.this.nSections / 2) {
                        n -= Shifter.this.AppletW / Shifter.this.nSections;
                    }
                    Shifter.this.shiftSection(-n);
                }
                else {
                    while (Shifter.this.dragTracerY < 0) {
                        final Shifter this$2 = Shifter.this;
                        this$2.dragTracerY += Shifter.this.AppletH;
                    }
                    int n2 = Shifter.this.dragTracerY % (Shifter.this.AppletH / Shifter.this.nSections);
                    if (n2 > Shifter.this.AppletH / Shifter.this.nSections / 2) {
                        n2 -= Shifter.this.AppletH / Shifter.this.nSections;
                    }
                    Shifter.this.shiftSection(-n2);
                }
                Shifter.this.dragging = false;
            }
            Shifter.this.mousePressed = false;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            Shifter.this.mouseX = mouseEvent.getX();
            Shifter.this.mouseY = mouseEvent.getY();
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Shifter.this.mouseX = mouseEvent.getX();
            Shifter.this.mouseY = mouseEvent.getY();
            final int n = Shifter.this.mouseX - Shifter.this.dragStartX;
            final int n2 = Shifter.this.mouseY - Shifter.this.dragStartY;
            if (Shifter.this.dragStart && (Math.abs(n) > 4 || Math.abs(n2) > 4)) {
                if (Math.abs(n) > Math.abs(n2)) {
                    Shifter.this.dragHor = true;
                }
                else {
                    Shifter.this.dragHor = false;
                }
                if (Shifter.this.dragHor) {
                    Shifter.this.dragISection = Shifter.this.mouseY / (Shifter.this.AppletH / Shifter.this.nSections);
                }
                else {
                    Shifter.this.dragISection = Shifter.this.mouseX / (Shifter.this.AppletW / Shifter.this.nSections);
                }
                Shifter.this.dragStart = false;
                Shifter.this.dragging = true;
            }
            if (Shifter.this.dragging) {
                Shifter.this.dragStartX = Shifter.this.mouseX;
                Shifter.this.dragStartY = Shifter.this.mouseY;
                if (Shifter.this.dragHor) {
                    Shifter.this.shiftSection(n);
                    final Shifter this$0 = Shifter.this;
                    this$0.dragTracerX += n;
                }
                else {
                    Shifter.this.shiftSection(n2);
                    final Shifter this$2 = Shifter.this;
                    this$2.dragTracerY += n2;
                }
            }
        }
    }
}
