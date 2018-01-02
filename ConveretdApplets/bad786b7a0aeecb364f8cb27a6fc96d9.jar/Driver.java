import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Driver extends Applet implements Runnable, MouseMotionListener, MouseListener
{
    Particle[] p;
    Graphics memScreen;
    Image memImage;
    int NUM_BOIDS;
    int WIDTH;
    int HEIGHT;
    long fps;
    long frames;
    long firstFrame;
    int mx;
    int my;
    boolean mouseIn;
    
    public Driver() {
        this.NUM_BOIDS = 50;
        this.WIDTH = 640;
        this.HEIGHT = 480;
        this.mouseIn = false;
    }
    
    public float[] check_bounds(final int n) {
        final float[] array = new float[2];
        if (this.p[n].x < 0.0f) {
            array[0] = 1.0f;
        }
        else if (this.p[n].x > 640.0f) {
            array[0] = -1.0f;
        }
        if (this.p[n].y < 0.0f) {
            array[1] = 1.0f;
        }
        else if (this.p[n].y > 480.0f) {
            array[1] = -1.0f;
        }
        return array;
    }
    
    public float[] check_distance(final int n) {
        final float[] array = new float[2];
        for (int i = 0; i < this.NUM_BOIDS; ++i) {
            if (i != n) {
                if (Math.abs(this.p[n].x - this.p[i].x) < 0.8f) {
                    final float[] array2 = array;
                    final int n2 = 0;
                    array2[n2] -= this.p[n].x - this.p[i].x;
                }
                if (Math.abs(this.p[n].y - this.p[i].y) < 0.8f) {
                    final float[] array3 = array;
                    final int n3 = 1;
                    array3[n3] -= this.p[n].y - this.p[i].y;
                }
            }
        }
        return array;
    }
    
    public float[] follow_leader(final int n) {
        final float[] array = new float[2];
        if (!this.mouseIn && n != 0) {
            array[0] = (this.p[0].x - this.p[n].x) / 1000.0f;
            array[1] = (this.p[0].y - this.p[n].y) / 1000.0f;
        }
        else if (!this.mouseIn && n == 0) {
            array[0] = (this.p[n].x - this.p[1].x) / 1000.0f;
            array[1] = (this.p[n].y - this.p[1].y) / 1000.0f;
        }
        if (this.mouseIn) {
            array[0] = (this.mx - this.p[n].x) / 1000.0f;
            array[1] = (this.my - this.p[n].y) / 1000.0f;
        }
        return array;
    }
    
    public void init() {
        this.memImage = this.createImage(this.WIDTH, this.HEIGHT);
        this.memScreen = this.memImage.getGraphics();
        this.p = new Particle[this.NUM_BOIDS];
        for (int i = 0; i < this.NUM_BOIDS; ++i) {
            (this.p[i] = new Particle()).initialize(this.WIDTH / 2, this.HEIGHT / 2);
        }
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        new Thread(this).start();
    }
    
    public float[] match_velocity(final int n) {
        final float[] array = new float[2];
        for (int i = 0; i < this.NUM_BOIDS; ++i) {
            if (i != n) {
                final float[] array2 = array;
                final int n2 = 0;
                array2[n2] += this.p[i].velocityX;
                final float[] array3 = array;
                final int n3 = 1;
                array3[n3] += this.p[i].velocityY;
            }
        }
        array[0] = array[0] / (this.NUM_BOIDS - 1) / 700.0f;
        array[1] = array[1] / (this.NUM_BOIDS - 1) / 700.0f;
        return array;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.mouseIn = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mouseIn = false;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mx = mouseEvent.getX();
        this.my = mouseEvent.getY();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public float[] moveto_cent_of_mass(final int n) {
        final float[] array = new float[2];
        for (int i = 0; i < this.NUM_BOIDS; ++i) {
            if (i != n) {
                final float[] array2 = array;
                final int n2 = 0;
                array2[n2] += this.p[i].x;
                final float[] array3 = array;
                final int n3 = 1;
                array3[n3] += this.p[i].y;
            }
        }
        array[0] /= this.NUM_BOIDS - 1;
        array[1] /= this.NUM_BOIDS - 1;
        array[0] = (array[0] - this.p[n].x) / 700.0f;
        array[1] = (array[1] - this.p[n].y) / 700.0f;
        return array;
    }
    
    public void paint(final Graphics graphics) {
        this.memImage.flush();
        this.memScreen.setColor(Color.black);
        this.memScreen.fillRect(0, 0, this.WIDTH, this.HEIGHT);
        this.memScreen.setColor(Color.green);
        for (int i = 0; i < this.NUM_BOIDS; ++i) {
            this.memScreen.fillRect((int)this.p[i].x, (int)this.p[i].y, 3, 3);
            this.memScreen.setColor(Color.white);
        }
        ++this.frames;
        this.fps = this.frames * 10000L / (System.currentTimeMillis() - this.firstFrame);
        this.memScreen.setColor(Color.white);
        this.memScreen.drawString(String.valueOf(this.fps / 10L) + "." + this.fps % 10L + " fps", 2, this.HEIGHT - 2);
        graphics.drawImage(this.memImage, 0, 0, this);
    }
    
    public void run() {
        final float[] array = new float[2];
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        final float[] array4 = new float[2];
        final float[] array5 = new float[2];
        while (true) {
            try {
                Thread.sleep(20L);
                for (int i = 0; i < this.NUM_BOIDS; ++i) {
                    this.p[i].update(this.moveto_cent_of_mass(i), this.check_distance(i), this.match_velocity(i), this.check_bounds(i), this.follow_leader(i), i, this.mouseIn);
                }
            }
            catch (Exception ex) {}
            this.repaint();
        }
    }
    
    public void start() {
        this.firstFrame = System.currentTimeMillis();
        this.frames = 0L;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
