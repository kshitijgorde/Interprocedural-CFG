import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Click extends Applet implements MouseMotionListener, MouseListener
{
    int size;
    double damp;
    int refresh;
    int count;
    int high;
    double gravity;
    int mouseX;
    int mouseY;
    Graphics bufferGraphics;
    Image offscreen;
    Dimension dim;
    Ball ball;
    Velocity velocity;
    boolean on;
    boolean start;
    Image s1;
    Image s2;
    Image s3;
    Image s4;
    Image s5;
    Image s6;
    int imagecount;
    Image title;
    URL base;
    MediaTracker mt;
    
    public Click() {
        this.size = 60;
        this.damp = 0.9;
        this.refresh = 15;
        this.count = 0;
        this.high = 0;
        this.gravity = 0.3;
        this.on = true;
        this.start = true;
        this.imagecount = 1;
    }
    
    public void init() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.requestFocus();
        final Color bg = new Color(22, 167, 26);
        this.setBackground(bg);
        this.setForeground(Color.black);
        this.dim = this.getSize();
        this.offscreen = this.createImage(this.dim.width, this.dim.height);
        this.bufferGraphics = this.offscreen.getGraphics();
        this.mt = new MediaTracker(this);
        try {
            this.base = this.getDocumentBase();
        }
        catch (Exception ex) {}
        this.s1 = this.getImage(this.base, "ball-1.gif");
        this.s2 = this.getImage(this.base, "ball-2.gif");
        this.s3 = this.getImage(this.base, "ball-3.gif");
        this.s4 = this.getImage(this.base, "ball-4.gif");
        this.s5 = this.getImage(this.base, "ball-5.gif");
        this.s6 = this.getImage(this.base, "ball-6.gif");
        this.title = this.getImage(this.base, "title.jpg");
        this.mt.addImage(this.s1, 1);
        this.mt.addImage(this.s2, 1);
        this.mt.addImage(this.s3, 1);
        this.mt.addImage(this.s4, 1);
        this.mt.addImage(this.s5, 1);
        this.mt.addImage(this.s6, 1);
        this.mt.addImage(this.title, 1);
        this.showStatus("Loading Images");
        try {
            this.mt.waitForAll();
        }
        catch (InterruptedException ex2) {}
        this.ball = new Ball(250.0, 10.0);
        this.velocity = new Velocity(this.ball, 0.0, this.gravity);
    }
    
    public void start() {
        new GameEngine(this);
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.offscreen, 0, 0, this);
        if (this.count > this.high) {
            this.high = this.count;
        }
        this.showStatus("Score " + this.count + "             High Score " + this.high);
    }
    
    public void mouseMoved(final MouseEvent me) {
        this.mouseX = me.getX();
        this.mouseY = me.getY();
    }
    
    public void mousePressed(final MouseEvent me) {
        if (!this.start) {
            if (this.mouseX >= (int)this.ball.getX() && this.mouseX < (int)this.ball.getX() + this.size / 3) {
                this.locY(-17);
            }
            else if (this.mouseX >= (int)this.ball.getX() + this.size / 3 && this.mouseX < (int)this.ball.getX() + this.size * 2 / 3) {
                this.locY(-10);
            }
            else if (this.mouseX >= (int)this.ball.getX() + this.size * 2 / 3 && this.mouseX <= (int)this.ball.getX() + this.size) {
                this.locY(-6);
            }
        }
        else {
            this.start = false;
        }
    }
    
    private void locY(final int y) {
        if (this.mouseY >= (int)this.ball.getY() && this.mouseY < (int)this.ball.getY() + this.size / 7) {
            this.velocity.setVelocity(8.0, y);
            ++this.count;
        }
        else if (this.mouseY >= (int)this.ball.getY() + this.size / 7 && this.mouseY < (int)this.ball.getY() + 2 * this.size / 7) {
            this.velocity.setVelocity(4.0, y);
            ++this.count;
        }
        else if (this.mouseY >= (int)this.ball.getY() + this.size * 2 / 7 && this.mouseY < (int)this.ball.getY() + this.size * 3 / 7) {
            this.velocity.setVelocity(2.0, y);
            ++this.count;
        }
        else if (this.mouseY >= (int)this.ball.getY() + this.size * 3 / 7 && this.mouseY < (int)this.ball.getY() + this.size * 4 / 7) {
            this.velocity.setVelocity(0.0, y);
            ++this.count;
        }
        else if (this.mouseY >= (int)this.ball.getY() + this.size * 4 / 7 && this.mouseY < (int)this.ball.getY() + this.size * 5 / 7) {
            this.velocity.setVelocity(-2.0, y);
            ++this.count;
        }
        else if (this.mouseY >= (int)this.ball.getY() + this.size * 5 / 7 && this.mouseY < (int)this.ball.getY() + this.size * 6 / 7) {
            this.velocity.setVelocity(-4.0, y);
            ++this.count;
        }
        else if (this.mouseY >= (int)this.ball.getY() + this.size * 6 / 7 && this.mouseY <= (int)this.ball.getY() + this.size) {
            this.velocity.setVelocity(-8.0, y);
            ++this.count;
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void stop() {
        this.on = false;
    }
    
    public void destroy() {
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent me) {
    }
    
    public void mouseExited(final MouseEvent me) {
    }
    
    public void mouseClicked(final MouseEvent me) {
    }
    
    public void mouseReleased(final MouseEvent me) {
    }
    
    class GameEngine implements Runnable
    {
        Thread et;
        Click click;
        
        GameEngine(final Click click) {
            this.click = click;
            (this.et = new Thread(this, "Engine")).setPriority(10);
            this.et.start();
        }
        
        public void run() {
            Click.this.bufferGraphics.clearRect(0, 0, Click.this.dim.width, Click.this.dim.width);
            do {
                Click.this.bufferGraphics.clearRect(0, 0, Click.this.dim.width, Click.this.dim.width);
                if (Click.this.start) {
                    Click.this.bufferGraphics.drawImage(Click.this.title, 0, 0, this.click);
                    Click.this.repaint();
                    this.pause(Click.this.refresh);
                }
                else {
                    Click.this.velocity.moveBall();
                    if (Click.this.imagecount <= 5) {
                        Click.this.bufferGraphics.drawImage(Click.this.s1, (int)Click.this.ball.getX(), (int)Click.this.ball.getY(), this.click);
                    }
                    if (Click.this.imagecount > 5 && Click.this.imagecount <= 10) {
                        Click.this.bufferGraphics.drawImage(Click.this.s2, (int)Click.this.ball.getX(), (int)Click.this.ball.getY(), this.click);
                    }
                    if (Click.this.imagecount > 10 && Click.this.imagecount <= 15) {
                        Click.this.bufferGraphics.drawImage(Click.this.s3, (int)Click.this.ball.getX(), (int)Click.this.ball.getY(), this.click);
                    }
                    if (Click.this.imagecount > 15 && Click.this.imagecount <= 20) {
                        Click.this.bufferGraphics.drawImage(Click.this.s4, (int)Click.this.ball.getX(), (int)Click.this.ball.getY(), this.click);
                    }
                    if (Click.this.imagecount > 20 && Click.this.imagecount <= 25) {
                        Click.this.bufferGraphics.drawImage(Click.this.s5, (int)Click.this.ball.getX(), (int)Click.this.ball.getY(), this.click);
                    }
                    if (Click.this.imagecount > 25) {
                        Click.this.bufferGraphics.drawImage(Click.this.s6, (int)Click.this.ball.getX(), (int)Click.this.ball.getY(), this.click);
                    }
                    Click.this.bufferGraphics.drawString("Score: " + Click.this.count + " High Score: " + Click.this.high, 10, 480);
                    if (Click.this.imagecount > 30) {
                        Click.this.imagecount = 0;
                    }
                    if (Click.this.imagecount < 0) {
                        Click.this.imagecount = 30;
                    }
                    if (Click.this.velocity.getVX() < 0.0 && Click.this.velocity.getVX() >= -2.0) {
                        final Click this$0 = Click.this;
                        ++this$0.imagecount;
                    }
                    if (Click.this.velocity.getVX() < -2.0 && Click.this.velocity.getVX() >= -4.0) {
                        Click.this.imagecount += 2;
                    }
                    if (Click.this.velocity.getVX() < -4.0) {
                        Click.this.imagecount += 3;
                    }
                    if (Click.this.velocity.getVX() > 0.0 && Click.this.velocity.getVX() <= 2.0) {
                        final Click this$2 = Click.this;
                        --this$2.imagecount;
                    }
                    if (Click.this.velocity.getVX() > 2.0 && Click.this.velocity.getVX() <= 4.0) {
                        Click.this.imagecount += 2;
                    }
                    if (Click.this.velocity.getVX() > 4.0) {
                        Click.this.imagecount += 3;
                    }
                    Click.this.repaint();
                    this.pause(Click.this.refresh);
                    if (Click.this.ball.getY() > 450.0 && Click.this.velocity.getVY() > 0.0) {
                        Click.this.velocity.setVelocity(Click.this.velocity.getVX(), -Click.this.velocity.getVY() * Click.this.damp);
                        Click.this.count = 0;
                    }
                    if (Click.this.ball.getY() <= 0.0 && Click.this.velocity.getVY() < 0.0) {
                        Click.this.velocity.setVelocity(Click.this.velocity.getVX(), -Click.this.velocity.getVY());
                    }
                    if (Click.this.ball.getX() > 0.0 && Click.this.ball.getX() < Click.this.dim.width - Click.this.size) {
                        continue;
                    }
                    Click.this.velocity.setVelocity(-Click.this.velocity.getVX(), Click.this.velocity.getVY());
                }
            } while (Click.this.on);
        }
        
        private void pause(final int pause) {
            final Thread t = Thread.currentThread();
            try {
                Thread.sleep(pause);
            }
            catch (InterruptedException ex) {}
        }
    }
}
