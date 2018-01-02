import java.awt.Color;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Component;
import java.awt.Image;
import java.awt.Dimension;
import java.util.Vector;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BallDrop extends Applet implements Runnable
{
    Thread itsThread;
    MediaTracker itsTracker;
    AudioClip itsBoink;
    Vector itsBalls;
    Dimension offDimension;
    Dimension backDimension;
    Image offImage;
    Image backImage;
    static final int numrows = 8;
    static final int numcolumns = 20;
    static final int numballs = 10;
    static final int delay = 10;
    static final int topspace = 30;
    static final int sidespace = 20;
    Image pin;
    Image ball;
    double pinr;
    int ballw;
    int ballh;
    int pinw;
    int pinh;
    int numracks;
    int[] rackheight;
    int[] rackdel;
    
    public void init() {
        final Dimension size = this.size();
        this.itsTracker = new MediaTracker(this);
        this.ball = this.getImage(this.getDocumentBase(), "smallball.gif");
        this.pin = this.getImage(this.getDocumentBase(), "smallpin.gif");
        this.itsTracker.addImage(this.ball, 0);
        this.itsTracker.addImage(this.pin, 0);
        try {
            this.itsTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            return;
        }
        this.itsBalls = new Vector();
        for (int i = 0; i < 10; ++i) {
            final Ball ball = new Ball(size.width / 2, 0.0);
            ball.Accelerate((Math.random() - 0.5) * 0.5, 0.0);
            this.itsBalls.addElement(ball);
        }
    }
    
    public void start() {
        if (this.itsThread == null) {
            (this.itsThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.itsThread = null;
        this.offImage = null;
        this.backImage = null;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.itsThread == null) {
            this.start();
        }
        else {
            this.itsThread = null;
        }
        return false;
    }
    
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            this.itsTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            return;
        }
        this.pinw = this.pin.getWidth(this);
        this.pinh = this.pin.getHeight(this);
        this.pinr = this.pinw / 2.0;
        this.ballw = this.ball.getWidth(this);
        this.ballh = this.ball.getHeight(this);
        final Enumeration<Ball> elements = this.itsBalls.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().SetImage(this.ball);
        }
        this.numracks = this.size().width / this.ballw;
        this.rackheight = new int[this.numracks];
        this.rackdel = new int[this.numracks];
        for (int i = 0; i < this.numracks; ++i) {
            this.rackheight[i] = 0;
            this.rackdel[i] = 0;
        }
        while (Thread.currentThread() == this.itsThread) {
            this.UpdateBalls();
            this.repaint();
            try {
                currentTimeMillis += 10L;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.backImage == null || size.width != this.backDimension.width || size.height != this.backDimension.height) {
            this.backDimension = size;
            this.backImage = this.createImage(size.width, size.height);
            final Graphics graphics2 = this.backImage.getGraphics();
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(0, 0, size.width, size.height);
            graphics2.setColor(Color.black);
            this.paintBackground(graphics2);
        }
        else {
            this.updateRack(this.backImage.getGraphics());
        }
        if (this.offImage == null || size.width != this.offDimension.width || size.height != this.offDimension.height) {
            this.offDimension = size;
            this.offImage = this.createImage(size.width, size.height);
        }
        final Graphics graphics3 = this.offImage.getGraphics();
        graphics3.drawImage(this.backImage, 0, 0, this);
        final Enumeration<Ball> elements = this.itsBalls.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().Draw(graphics3);
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void paintBackground(final Graphics graphics) {
        final Dimension size = this.size();
        for (int i = 0; i < 8; ++i) {
            final int n = i * (size.height - 60) / 16 + 30;
            final double n2 = (size.width - 40) / 20.0;
            for (int j = 0; j < 20; ++j) {
                graphics.drawImage(this.pin, (int)((j + i % 2 / 2.0) * n2) + 20 - this.pinw / 2, n - this.pinh / 2, this);
            }
        }
        final double n3 = size.width / this.numracks;
        graphics.setColor(Color.black);
        double n4 = 0.0;
        for (int k = 0; k <= this.numracks; ++k) {
            final double n5 = this.numracks / 2;
            final double n6 = size.height * (1.0 - 0.5 * Math.exp(-(k - n5) * (k - n5) / 162.0));
            if (k > 0) {
                graphics.drawLine((int)((k - 1) * n3), (int)n4, (int)(k * n3), (int)n6);
            }
            n4 = n6;
        }
        for (int l = 0; l < this.numracks; ++l) {
            for (int n7 = 0; n7 < this.rackheight[l]; ++n7) {
                graphics.drawImage(this.ball, l * this.ballw, size.height - (n7 + 1) * this.ballh, this);
            }
        }
    }
    
    void updateRack(final Graphics graphics) {
        final Dimension size = this.size();
        for (int i = 0; i < this.numracks; ++i) {
            while (this.rackdel[i] > 0) {
                final int[] rackheight = this.rackheight;
                final int n = i;
                ++rackheight[n];
                graphics.drawImage(this.ball, i * this.ballw, size.height - this.rackheight[i] * this.ballh, this);
                final int[] rackdel = this.rackdel;
                final int n2 = i;
                --rackdel[n2];
            }
        }
    }
    
    void UpdateBalls() {
        final Dimension size = this.size();
        final int height = size.height;
        final Enumeration<Ball> elements = (Enumeration<Ball>)this.itsBalls.elements();
        while (elements.hasMoreElements()) {
            final Ball ball = elements.nextElement();
            ball.Move();
            if (ball.y > height - ball.r) {
                if (this.itsBoink != null) {
                    this.itsBoink.stop();
                    this.itsBoink.play();
                }
                if (ball.vlen > 0.25) {
                    ball.vx = 0.0;
                    ball.vy = -ball.vy * 0.25;
                    ball.y = height - ball.r;
                }
                else {
                    final int n = (int)(ball.x / this.ballw);
                    if (n >= 0 && n < this.numracks) {
                        final int[] rackdel = this.rackdel;
                        final int n2 = n;
                        ++rackdel[n2];
                    }
                    ball.x = size.width / 2;
                    ball.y = ball.r;
                    ball.vx = (Math.random() - 0.5) / 3.0;
                    ball.vy = 0.0;
                }
            }
            else {
                final int n3 = (int)((ball.y - 30.0) * 2.0 * 8.0 / (size.height - 60));
                final int n4 = (n3 <= 0) ? 0 : (n3 - 1);
                for (int n5 = (n3 >= 7) ? 7 : (n3 + 1), i = n4; i <= n5; ++i) {
                    final int n6 = i * (size.height - 60) / 16 + 30;
                    final double n7 = (size.width - 40) / 20.0;
                    final int n8 = (int)((ball.x - 20.0) / n7 - i % 2 / 2.0);
                    final int n9 = (n8 <= 0) ? 0 : (n8 - 1);
                    for (int n10 = (n8 >= 19) ? 19 : (n8 + 1), j = n9; j <= n10; ++j) {
                        ball.Boink((int)((j + i % 2 / 2.0) * n7 + 20.0), n6, this.pinr);
                    }
                }
            }
            ball.Accelerate(0.0, 0.075);
        }
    }
}
