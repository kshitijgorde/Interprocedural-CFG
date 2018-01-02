import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fireworks extends Applet implements Runnable
{
    int nof_rockets;
    int delay;
    int nof_points;
    int trail;
    int dotsize;
    int life_len;
    double g;
    Thread kicker;
    Rocket[] rockets;
    int clear;
    boolean first;
    Color bg;
    
    Color parseCol(final String s) {
        if (s.length() >= 6) {
            final String substring = s.substring(0, 2);
            final String substring2 = s.substring(2, 4);
            final String substring3 = s.substring(4, 6);
            int int1;
            try {
                int1 = Integer.parseInt(substring, 16);
            }
            catch (Exception ex) {
                int1 = 0;
            }
            int int2;
            try {
                int2 = Integer.parseInt(substring2, 16);
            }
            catch (Exception ex2) {
                int2 = 0;
            }
            int int3;
            try {
                int3 = Integer.parseInt(substring3, 16);
            }
            catch (Exception ex3) {
                int3 = 0;
            }
            return new Color(int1, int2, int3);
        }
        return new Color(128, 128, 128);
    }
    
    public void init() {
        try {
            this.nof_rockets = Integer.parseInt(this.getParameter("ROCKETS"));
        }
        catch (Exception ex) {
            this.nof_rockets = 3;
        }
        try {
            this.delay = Integer.parseInt(this.getParameter("DELAY"));
        }
        catch (Exception ex2) {
            this.delay = 50;
        }
        try {
            this.trail = Integer.parseInt(this.getParameter("TRAIL"));
        }
        catch (Exception ex3) {
            this.trail = 10;
        }
        try {
            this.life_len = Integer.parseInt(this.getParameter("LIFELENGTH"));
        }
        catch (Exception ex4) {
            this.life_len = 100;
        }
        try {
            this.nof_points = Integer.parseInt(this.getParameter("POINTS"));
        }
        catch (Exception ex5) {
            this.nof_points = 3;
        }
        try {
            this.dotsize = Integer.parseInt(this.getParameter("POINTSIZE"));
        }
        catch (Exception ex6) {
            this.dotsize = 2;
        }
        try {
            this.g = Integer.parseInt(this.getParameter("GRAV")) / 1000.0;
        }
        catch (Exception ex7) {
            this.g = 0.009999999776482582;
        }
        final String parameter = this.getParameter("COLOR");
        if (parameter != null) {
            this.bg = this.parseCol(parameter);
        }
        this.rockets = new Rocket[this.nof_rockets];
        this.initrockets();
    }
    
    void initrockets() {
        for (int i = 0; i < this.nof_rockets; ++i) {
            final int n = 10 + (int)(Math.random() * (this.size().width - 20));
            final int n2 = this.size().height - (int)(Math.random() * 20.0);
            final double n3 = (Math.random() - 0.5) * 2.0;
            final double n4 = -(Math.random() * 6.0);
            final double random = Math.random();
            Color color;
            if (random < 0.33000001311302185) {
                color = new Color(155 + (int)(Math.random() * 100.0), 10, 10);
            }
            else if (random < 0.6600000262260437) {
                color = new Color(155 + (int)(Math.random() * 100.0), 155 + (int)(Math.random() * 100.0), 10);
            }
            else {
                color = new Color(10, 10, 155 + (int)(Math.random() * 100.0));
            }
            this.rockets[i] = new Rocket(this.nof_points, n, n2, n3, n4, this.g, this.dotsize, this.size().width, this.size().height, this.trail, this.life_len, color, this.bg);
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        graphics.setColor(this.bg);
        graphics.fillRect(0, 0, this.size().width - 1, this.size().height - 1);
        graphics.clipRect(2, 2, this.size().width - 4, this.size().height - 4);
        for (int i = 0; i < this.nof_rockets; ++i) {
            this.rockets[i].draw(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.clear == 0) {
            graphics.clipRect(2, 2, this.size().width - 4, this.size().height - 4);
            for (int i = 0; i < this.nof_rockets; ++i) {
                this.rockets[i].update(graphics);
            }
            return;
        }
        graphics.clearRect(0, 0, this.size().width, this.size().height);
        this.paint(graphics);
        this.clear = 0;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.clear = 1;
        this.initrockets();
        return true;
    }
    
    public void run() {
        while (this.kicker != null) {
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
    }
    
    public void start() {
        if (this.kicker == null) {
            (this.kicker = new Thread(this)).setPriority(1);
            this.kicker.start();
        }
    }
    
    public void stop() {
        this.kicker = null;
    }
    
    public fireworks() {
        this.nof_rockets = 3;
        this.delay = 50;
        this.nof_points = 20;
        this.trail = 10;
        this.dotsize = 2;
        this.life_len = 100;
        this.first = true;
        this.bg = Color.black;
    }
}
