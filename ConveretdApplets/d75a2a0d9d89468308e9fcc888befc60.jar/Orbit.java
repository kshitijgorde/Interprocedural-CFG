import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Orbit extends Applet implements Runnable
{
    double increment;
    double t;
    double maxt;
    private Thread runner;
    Eye camera;
    Simulate sim;
    Color bgcolor;
    boolean should_move;
    Image buffered;
    Graphics bg;
    double oldmousex;
    double oldmousey;
    double mousex;
    double mousey;
    double rotatescale;
    boolean drag;
    boolean xymode;
    boolean trail;
    boolean energy;
    int work;
    int sleep;
    Moon follow;
    boolean noperspective;
    
    public void init() {
        this.t = -1.0;
        this.bgcolor = Point.s2c(this.getParameter("background"), Color.black);
        this.increment = Point.s2d(this.getParameter("increment"), 0.1);
        this.maxt = Point.s2d(this.getParameter("length"), 1.0E30) / this.increment;
        this.should_move = (this.getParameter("stop") == null);
        this.trail = (this.getParameter("trail") != null);
        this.noperspective = (this.getParameter("noperspective") != null);
        this.work = (int)Point.s2d(this.getParameter("work"), 5.0);
        this.sleep = (int)Point.s2d(this.getParameter("sleep"), 50.0);
        final String parameter = this.getParameter("moons");
        Moon[] array;
        if (parameter == null) {
            array = new Moon[] { new Moon("p(0.0,0.0,0.0) v(0.0,0.0,0.0) 1000.0 00ff00 15.0", 1) };
        }
        else {
            int i = 0;
            final Moon[] array2 = new Moon[Integer.parseInt(parameter)];
            int n = 0;
            while (i < array2.length) {
                final String parameter2 = this.getParameter("moon" + n);
                if (n > 2 * array2.length) {
                    break;
                }
                if (parameter2 != null) {
                    array2[i] = new Moon(parameter2, n);
                    ++i;
                }
                ++n;
            }
            final Moon[] array3 = new Moon[i];
            for (int j = 0; j < i; ++j) {
                array3[j] = array2[j];
            }
            array = array3;
        }
        if (Point.s2d(this.getParameter("follow"), -1.0) != -1.0) {
            final int n2 = (int)Point.s2d(this.getParameter("follow"), -1.0);
            for (int k = 0; k < array.length; ++k) {
                if (array[k].id == n2) {
                    this.follow = array[k];
                }
            }
        }
        this.energy = (this.getParameter("energy") != null);
        final double s2d = Point.s2d(this.getParameter("scalemass"), 1.0);
        for (int l = 0; l < array.length; ++l) {
            final Moon moon = array[l];
            moon.m *= s2d;
        }
        String parameter3 = this.getParameter("eye");
        if (parameter3 == null) {
            parameter3 = "10.0  0.0  0.0  0.0  20.0";
        }
        this.camera = new Eye(parameter3, this.size().width / 2, this.size().height / 2);
        this.drag = false;
        this.oldmousex = 0.0;
        this.oldmousey = 0.0;
        this.mousex = 0.0;
        this.mousey = 0.0;
        this.rotatescale = this.camera.magnification / Math.min(this.size().width, this.size().height);
        this.sim = new Simulate(array, this.increment, this.work, this.energy, this.noperspective);
    }
    
    void startstop() {
        if (this.should_move && this.runner == null) {
            if (this.t > this.maxt) {
                this.init();
            }
            (this.runner = new Thread(this)).start();
        }
        else if (!this.should_move && this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    void clear() {
        this.bg.setColor(this.bgcolor);
        this.bg.fillRect(0, 0, this.size().width, this.size().height);
        this.bg.setColor(Color.gray);
        this.bg.drawLine(0, 0, this.size().width, 0);
        this.bg.drawLine(0, 0, 0, this.size().height);
        this.bg.drawLine(this.size().width - 1, 0, this.size().width - 1, this.size().height - 1);
        this.bg.drawLine(0, this.size().height - 1, this.size().width - 1, this.size().height - 1);
    }
    
    public void start() {
        this.startstop();
        if (this.buffered == null) {
            this.buffered = this.createImage(this.size().width, this.size().height);
            this.bg = this.buffered.getGraphics();
        }
        this.repaint();
    }
    
    public void stop() {
        this.runner = null;
        if (this.bg != null) {
            this.bg.dispose();
            this.bg = null;
        }
        if (this.buffered != null) {
            this.buffered = null;
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.drag) {
            this.should_move = !this.should_move;
            this.startstop();
        }
        return this.drag = true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mousex = n - this.camera.centerx;
        this.mousey = n2 - this.camera.centery;
        this.oldmousex = this.mousex;
        this.oldmousey = this.mousey;
        this.xymode = (4.0 * (this.mousex * this.mousex + this.mousey * this.mousey) < Math.min(this.camera.centerx * this.camera.centerx, this.camera.centery * this.camera.centery));
        this.drag = false;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.mousex = n - this.camera.centerx;
        this.mousey = n2 - this.camera.centery;
        return true;
    }
    
    public void run() {
        while (this.runner != null) {
            this.repaint();
            try {
                Thread.sleep(this.sleep);
            }
            catch (InterruptedException ex) {}
            if (this.t > this.maxt) {
                this.should_move = false;
                this.runner = null;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.bg == null) {
            return;
        }
        if (this.t < 0.0) {
            this.clear();
        }
        if (!this.trail) {
            this.bg.setColor(this.bgcolor);
            this.clear();
        }
        final double mousex = this.mousex;
        final double mousey = this.mousey;
        final double n = mousex - this.oldmousex;
        final double n2 = mousey - this.oldmousey;
        if (n != 0.0 || n2 != 0.0) {
            this.drag = true;
            if (this.xymode) {
                final double atan2 = Math.atan2(n, n2);
                final double n3 = n * n + n2 * n2;
                this.camera.clockwise(-atan2);
                if (n3 <= 10.0) {
                    this.camera.up(Math.sqrt(n3) * (this.rotatescale / this.camera.magnification));
                }
                else {
                    this.camera.up(n3 * this.rotatescale / (10.0 * this.camera.magnification));
                }
                this.camera.clockwise(atan2);
            }
            else {
                final double n4 = this.oldmousex * this.oldmousex + this.oldmousey * this.oldmousey;
                if (n4 > 0.5) {
                    final double sqrt = Math.sqrt((mousex * mousex + mousey * mousey) / n4);
                    this.camera.zoom(sqrt);
                    this.camera.clockwise(Math.asin((this.oldmousey * (mousex / sqrt) - this.oldmousex * (mousey / sqrt)) / n4));
                }
            }
            this.oldmousex += (int)n;
            this.oldmousey += (int)n2;
            if (this.trail) {
                this.clear();
            }
        }
        if (this.follow != null) {
            this.camera.center(this.follow.p);
        }
        this.sim.draw(this.bg, this.camera);
        this.getGraphics().drawImage(this.buffered, 0, 0, null);
        this.sim.move();
        ++this.t;
    }
}
