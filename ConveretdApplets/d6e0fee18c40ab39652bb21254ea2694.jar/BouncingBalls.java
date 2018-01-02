import java.awt.Component;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BouncingBalls extends Applet implements Runnable
{
    Thread runner;
    Image Buffer;
    Graphics gBuffer;
    CollideBall[] ball;
    Obstacle o;
    static final int MAX = 10;
    boolean intro;
    boolean drag;
    boolean shiftW;
    boolean shiftN;
    boolean shiftE;
    boolean shiftS;
    boolean shiftNW;
    boolean shiftSW;
    boolean shiftNE;
    boolean shiftSE;
    int xtemp;
    int ytemp;
    int startx;
    int starty;
    int west;
    int north;
    int east;
    int south;
    
    private void handleCollision() {
        int i = 0;
        do {
            int j = 0;
            do {
                if (i != j && this.collide(this.ball[i], this.ball[j])) {
                    this.ball[i].hit(this.ball[j]);
                    this.ball[j].hit(this.ball[i]);
                }
            } while (++j < 10);
        } while (++i < 10);
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void paint(final Graphics g) {
        this.gBuffer.setColor(Color.lightGray);
        this.gBuffer.fillRect(0, 0, this.size().width, this.size().height);
        this.gBuffer.draw3DRect(5, 5, this.size().width - 10, this.size().height - 10, false);
        this.o.paint(this.gBuffer);
        int i = 0;
        do {
            this.ball[i].paint(this.gBuffer);
        } while (++i < 10);
        if (this.intro) {
            this.gBuffer.setColor(Color.red);
            this.gBuffer.setFont(new Font("Helvetica", 0, 12));
            this.gBuffer.drawString("You can move and resize the rectangle!", 20, 30);
            this.gBuffer.setFont(new Font("Helvetica", 0, 10));
            this.gBuffer.drawString("© 2000 Johannes Wallroth - www.programming.de", 155, 240);
        }
        g.drawImage(this.Buffer, 0, 0, this);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public BouncingBalls() {
        this.intro = true;
    }
    
    boolean collide(final CollideBall b1, final CollideBall b2) {
        final double wx = b1.getCenterX() - b2.getCenterX();
        final double wy = b1.getCenterY() - b2.getCenterY();
        final double distance = Math.sqrt(wx * wx + wy * wy);
        return distance < 20.0;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        final Rectangle r = new Rectangle(this.o.r.x + 2, this.o.r.y + 2, this.o.r.width - 4, this.o.r.height - 4);
        if (r.inside(x, y)) {
            this.drag = true;
            this.startx = x;
            this.starty = y;
            this.xtemp = this.o.r.x;
            this.ytemp = this.o.r.y;
        }
        else {
            this.drag = false;
        }
        this.changeCursor(x, y);
        return true;
    }
    
    public void run() {
        while (true) {
            Thread.currentThread().setPriority(10);
            try {
                Thread.sleep(15L);
            }
            catch (Exception ex) {}
            int i = 0;
            do {
                this.ball[i].move();
            } while (++i < 10);
            this.handleCollision();
            this.repaint();
        }
    }
    
    public void init() {
        this.Buffer = this.createImage(this.size().width, this.size().height);
        this.gBuffer = this.Buffer.getGraphics();
        this.ball = new CollideBall[10];
        final int w = this.size().width - 5;
        final int h = this.size().height - 5;
        this.ball[0] = new CollideBall(w, h, 50, 20, 1.5, 2.0, Color.orange);
        this.ball[1] = new CollideBall(w, h, 60, 210, 2.0, -3.0, Color.red);
        this.ball[2] = new CollideBall(w, h, 15, 70, -2.0, -2.5, Color.pink);
        this.ball[3] = new CollideBall(w, h, 150, 30, -2.7, -2.0, Color.cyan);
        this.ball[4] = new CollideBall(w, h, 210, 30, 2.2, -3.5, Color.magenta);
        this.ball[5] = new CollideBall(w, h, 360, 170, 2.2, -1.5, Color.yellow);
        this.ball[6] = new CollideBall(w, h, 210, 180, -1.2, -2.5, Color.blue);
        this.ball[7] = new CollideBall(w, h, 330, 30, -2.2, -1.8, Color.green);
        this.ball[8] = new CollideBall(w, h, 180, 220, -2.2, -1.8, Color.black);
        this.ball[9] = new CollideBall(w, h, 330, 130, -2.2, -1.8, Color.gray);
        this.o = new Obstacle(150, 80, 130, 90);
        this.west = this.o.r.x;
        this.north = this.o.r.y;
        this.east = this.o.r.x + this.o.r.width;
        this.south = this.o.r.y + this.o.r.height;
    }
    
    public boolean mouseDrag(final Event evt, int x, int y) {
        this.intro = false;
        final Rectangle bounds = new Rectangle(5, 5, this.size().width - 5, this.size().height - 5);
        final int endx = x - this.startx;
        final int endy = y - this.starty;
        if (x < 5) {
            x = 5;
        }
        if (y < 5) {
            y = 5;
        }
        if (x > bounds.width) {
            x = bounds.width;
        }
        if (y > bounds.height) {
            y = bounds.height;
        }
        if (this.drag) {
            int ox = endx + this.xtemp;
            int oy = endy + this.ytemp;
            if (ox < 5) {
                ox = 5;
            }
            if (oy < 5) {
                oy = 5;
            }
            if (ox > bounds.width - this.o.r.width) {
                ox = bounds.width - this.o.r.width;
            }
            if (oy > bounds.height - this.o.r.height) {
                oy = bounds.height - this.o.r.height;
            }
            this.o.r.move(ox, oy);
            this.west = this.o.r.x;
            this.north = this.o.r.y;
            this.east = this.o.r.x + this.o.r.width;
            this.south = this.o.r.y + this.o.r.height;
        }
        else {
            if (this.shiftNW) {
                this.west = x;
                this.north = y;
            }
            else if (this.shiftNE) {
                this.east = x;
                this.north = y;
            }
            else if (this.shiftSW) {
                this.west = x;
                this.south = y;
            }
            else if (this.shiftSE) {
                this.east = x;
                this.south = y;
            }
            else if (this.shiftW) {
                this.west = x;
            }
            else if (this.shiftE) {
                this.east = x;
            }
            else if (this.shiftN) {
                this.north = y;
            }
            else if (this.shiftS) {
                this.south = y;
            }
            final int MIN = 40;
            if (this.east - this.west < MIN) {
                if (this.shiftW || this.shiftNW || this.shiftSW) {
                    this.west = this.east - MIN;
                }
                if (this.shiftE || this.shiftNE || this.shiftSE) {
                    this.east = this.west + MIN;
                }
            }
            if (this.south - this.north < MIN) {
                if (this.shiftN || this.shiftNW || this.shiftNE) {
                    this.north = this.south - MIN;
                }
                if (this.shiftS || this.shiftSE || this.shiftSW) {
                    this.south = this.north + MIN;
                }
            }
        }
        int i = 0;
        do {
            this.ball[i].alterRect(this.o.r.x, this.o.r.y, this.o.r.width, this.o.r.height);
        } while (++i < 10);
        this.o.r.move(this.west, this.north);
        this.o.r.resize(this.east - this.west, this.south - this.north);
        this.changeCursor(x, y);
        return true;
    }
    
    void changeCursor(final int x, final int y) {
        final Rectangle r = new Rectangle(this.o.r.x + 1, this.o.r.y + 1, this.o.r.width - 1, this.o.r.height - 1);
        Component ParentComponent;
        for (ParentComponent = this.getParent(); ParentComponent != null && !(ParentComponent instanceof Frame); ParentComponent = ParentComponent.getParent()) {}
        final Frame BrowserFrame = (Frame)ParentComponent;
        if (this.shiftNW || this.shiftSE) {
            BrowserFrame.setCursor(5);
        }
        else if (this.shiftNE || this.shiftSW) {
            BrowserFrame.setCursor(4);
        }
        else if (this.shiftW) {
            BrowserFrame.setCursor(10);
        }
        else if (this.shiftN) {
            BrowserFrame.setCursor(8);
        }
        else if (this.shiftE) {
            BrowserFrame.setCursor(10);
        }
        else if (this.shiftS) {
            BrowserFrame.setCursor(8);
        }
        else if (r.inside(x, y)) {
            BrowserFrame.setCursor(13);
        }
        else {
            BrowserFrame.setCursor(0);
        }
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        final Rectangle nw = new Rectangle(this.o.r.x - 2, this.o.r.y - 2, 4, 4);
        if (nw.inside(x, y)) {
            this.shiftNW = true;
        }
        else {
            this.shiftNW = false;
        }
        final Rectangle sw = new Rectangle(this.o.r.x - 2, this.o.r.y + this.o.r.height - 2, 4, 4);
        if (sw.inside(x, y)) {
            this.shiftSW = true;
        }
        else {
            this.shiftSW = false;
        }
        final Rectangle ne = new Rectangle(this.o.r.x + this.o.r.width - 2, this.o.r.y - 2, 4, 4);
        if (ne.inside(x, y)) {
            this.shiftNE = true;
        }
        else {
            this.shiftNE = false;
        }
        final Rectangle se = new Rectangle(this.o.r.x + this.o.r.width - 2, this.o.r.y + this.o.r.height - 2, 4, 4);
        if (se.inside(x, y)) {
            this.shiftSE = true;
        }
        else {
            this.shiftSE = false;
        }
        if (x > this.o.r.x - 2 && x < this.o.r.x + 2 && y > this.o.r.y && y < this.o.r.y + this.o.r.height) {
            this.shiftW = true;
        }
        else {
            this.shiftW = false;
        }
        if (x > this.o.r.x + this.o.r.width - 2 && x < this.o.r.x + this.o.r.width + 2 && y > this.o.r.y && y < this.o.r.y + this.o.r.height) {
            this.shiftE = true;
        }
        else {
            this.shiftE = false;
        }
        if (y < this.o.r.y + 2 && y > this.o.r.y - 2 && x > this.o.r.x && x < this.o.r.x + this.o.r.width) {
            this.shiftN = true;
        }
        else {
            this.shiftN = false;
        }
        if (y > this.o.r.y + this.o.r.height - 2 && y < this.o.r.y + this.o.r.height + 2 && x < this.o.r.x + this.o.r.width) {
            this.shiftS = true;
        }
        else {
            this.shiftS = false;
        }
        this.changeCursor(x, y);
        return true;
    }
}
