import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class spacefl extends Applet implements Runnable, MouseMotionListener, MouseListener, Serializable
{
    int Width;
    int Height;
    int Xmax;
    int Ymax;
    Thread me;
    boolean suspend;
    Image im;
    Graphics offscreen;
    Cursor fifi;
    int speed;
    int N_Stars;
    int mx;
    int my;
    int dx;
    int dy;
    int mxold;
    int myold;
    double crot;
    double srot;
    double rot;
    flybstar[] FStar;
    backstar[] BStar;
    
    public spacefl() {
        this.me = null;
        this.suspend = false;
    }
    
    public void Show(final String s, final String s2) {
        if (s2 == null) {
            System.out.println(String.valueOf(s) + " : null");
        }
        else {
            System.out.println(String.valueOf(s) + " : " + s2);
        }
    }
    
    public void Toggle() {
        if (this.me != null) {
            if (this.suspend) {
                this.me.resume();
            }
            else {
                this.me.suspend();
            }
            this.suspend ^= true;
        }
    }
    
    public Frame getFrame(Component parent) {
        while (parent != null && !(parent instanceof Frame)) {
            parent = parent.getParent();
        }
        return (Frame)parent;
    }
    
    public void init() {
        this.Width = this.getSize().width;
        this.Height = this.getSize().height;
        final String parameter = this.getParameter("Velocity");
        this.Show("speed", parameter);
        this.speed = ((parameter == null) ? 50 : Integer.valueOf(parameter));
        final String parameter2 = this.getParameter("Number_of_Stars");
        this.Show("Number_of_Stars", parameter2);
        this.N_Stars = ((parameter2 == null) ? 30 : Integer.valueOf(parameter2));
        try {
            this.im = this.createImage(this.Width, this.Height);
            this.offscreen = this.im.getGraphics();
        }
        catch (Exception ex) {
            this.offscreen = null;
        }
        this.BStar = new backstar[this.N_Stars];
        this.FStar = new flybstar[this.N_Stars];
        for (int i = 0; i < this.N_Stars; ++i) {
            this.BStar[i] = new backstar(this.Width, this.Height);
            this.FStar[i] = new flybstar(this.Width, this.Height);
        }
        this.mxold = 0;
        this.myold = 0;
        this.Xmax = this.Width / 2;
        this.Ymax = this.Height / 2;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mx = mouseEvent.getX() - this.Xmax;
        this.my = mouseEvent.getY() - this.Ymax;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen != null) {
            this.paintMe(this.offscreen);
            graphics.drawImage(this.im, 0, 0, this);
        }
        else {
            this.paintMe(graphics);
        }
    }
    
    public void paintMe(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.Width, this.Height);
        for (int i = 0; i < this.N_Stars; ++i) {
            this.BStar[i].BDraw(graphics, this.mx, this.my, this.dx, this.dy, this.crot, this.srot);
            this.FStar[i].Draw(graphics, this.mx, this.my, this.dx, this.dy, this.crot, this.srot);
        }
    }
    
    public void run() {
        while (this.me != null) {
            this.dx = this.mx - this.mxold;
            this.dy = this.my - this.myold;
            this.mxold = this.mx;
            this.myold = this.my;
            this.rot = 0.5233333333333333 * this.mx / this.Xmax * 0.0;
            this.crot = Math.cos(this.rot);
            this.srot = Math.sin(this.rot);
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void start() {
        this.getFrame(this).setCursor(1);
        if (this.me == null) {
            (this.me = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.me != null) {
            this.me.stop();
            this.me = null;
        }
        this.getFrame(this).setCursor(0);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
