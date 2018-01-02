import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RunningRainbow extends Applet implements Runnable
{
    int delay;
    int shift;
    int offset;
    double ss;
    double ar;
    double ag;
    double ab;
    double sr;
    double sg;
    double sb;
    double ar1;
    String direction;
    String method;
    boolean isInvert;
    boolean isRunning;
    boolean isParams;
    Thread animatorThread;
    Image offImage;
    Graphics offGraphics;
    
    public void start() {
        if (this.isRunning) {
            if (this.animatorThread == null) {
                this.animatorThread = new Thread(this);
            }
            this.animatorThread.start();
        }
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "delay", "Integer", "delay of animation" }, { "shift", "Integer", "shift of animation" }, { "method", "Integer", "method of rainbow rendering: gaussian or trapezoid" }, { "ar", "Double", "mean value of red component" }, { "ag", "Double", "mean value of green component" }, { "ab", "Double", "mean value of blue component" }, { "sr", "Double", "dispersion of red component" }, { "sg", "Double", "dispersion of green component" }, { "sb", "Double", "dispersion of blue component" } };
    }
    
    public void stop() {
        this.animatorThread = null;
    }
    
    public Color cg(int i) {
        while (i >= this.size().width) {
            i -= this.size().width;
        }
        final double n = i / this.size().width;
        double n2;
        if (this.ar1 != 0.0 && n > 0.5) {
            n2 = n - this.ar1;
        }
        else {
            n2 = n - this.ar;
        }
        final double n3 = n - this.ag;
        final double n4 = n - this.ab;
        double exp;
        if (this.sr != 0.0) {
            exp = Math.exp(-n2 * n2 / this.sr);
        }
        else {
            exp = 0.0;
        }
        double exp2;
        if (this.sg != 0.0) {
            exp2 = Math.exp(-n3 * n3 / this.sg);
        }
        else {
            exp2 = 0.0;
        }
        double exp3;
        if (this.sb != 0.0) {
            exp3 = Math.exp(-n4 * n4 / this.sb);
        }
        else {
            exp3 = 0.0;
        }
        if (this.isInvert) {
            return new Color((float)(1.0 - exp), (float)(1.0 - exp2), (float)(1.0 - exp3));
        }
        return new Color((float)exp, (float)exp2, (float)exp3);
    }
    
    public Color c(int n) {
        if (n >= this.size().width) {
            n -= this.size().width;
        }
        final double n2 = n * 6.0 / this.size().width;
        double n3;
        double n4;
        double n5;
        if (n2 < 1.0) {
            n3 = 1.0;
            n4 = n2;
            n5 = 0.0;
        }
        else if (n2 < 2.0) {
            n3 = 2.0 - n2;
            n4 = 1.0;
            n5 = 0.0;
        }
        else if (n2 < 3.0) {
            n3 = 0.0;
            n4 = 1.0;
            n5 = n2 - 2.0;
        }
        else if (n2 < 4.0) {
            n3 = 0.0;
            n4 = 4.0 - n2;
            n5 = 1.0;
        }
        else if (n2 < 5.0) {
            n3 = n2 - 4.0;
            n4 = 0.0;
            n5 = 1.0;
        }
        else {
            n3 = 1.0;
            n4 = 0.0;
            n5 = 6.0 - n2;
        }
        return new Color((float)n3, (float)n4, (float)n5);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.isRunning) {
            this.isRunning = false;
            this.stop();
        }
        else {
            this.isRunning = true;
            this.start();
        }
        return true;
    }
    
    public double parseDouble(final String s) {
        return new Double(s);
    }
    
    public String getAppletInfo() {
        return "Running Rainbow applet. Copyright (c)1997, by Oleg V. Baranovsky.";
    }
    
    public void getParams() {
        this.isParams = true;
        final String parameter = this.getParameter("delay");
        final int n = (parameter != null) ? Integer.parseInt(parameter) : 100;
        this.delay = ((n > 10) ? n : 100);
        final String parameter2 = this.getParameter("shift");
        final int n2 = (parameter2 != null) ? Integer.parseInt(parameter2) : 2;
        this.shift = this.size().width * ((n2 >= 0 && n2 < 256) ? n2 : 2) / 256;
        this.direction = this.getParameter("direction");
        if (this.direction == null) {
            this.direction = new String("left");
        }
        this.isInvert = (this.getParameter("invert") != null);
        this.method = this.getParameter("method");
        if (this.method == null) {
            this.method = new String("none");
        }
        if (this.method.compareTo("gaussian") == 0) {
            this.ar = this.getDoubleParam("ar", 0.0);
            this.ag = this.getDoubleParam("ag", 0.5);
            this.ab = this.getDoubleParam("ab", 1.0);
            this.ar1 = this.getDoubleParam("ar1", 0.0);
            if (this.getParameter("ss") != null) {
                this.ss = this.getDoubleParam("ss", 0.5);
                final double ss = this.ss;
                this.sb = ss;
                this.sg = ss;
                this.sr = ss;
            }
            else {
                this.sr = this.getDoubleParam("sr", 0.5);
                this.sg = this.getDoubleParam("sg", 0.5);
                this.sb = this.getDoubleParam("sb", 0.5);
            }
            this.sr = this.sr * this.sr / 2.0;
            this.sg = this.sg * this.sg / 2.0;
            this.sb = this.sb * this.sb / 2.0;
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(3);
        if (this.direction.compareTo("none") == 0) {
            this.offset = this.shift;
            this.repaint();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        while (Thread.currentThread() == this.animatorThread) {
            this.repaint();
            try {
                currentTimeMillis += this.delay;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {
                return;
            }
            if (this.direction.compareTo("right") == 0) {
                this.offset -= this.shift;
                if (this.offset >= 0) {
                    continue;
                }
                this.offset = this.size().width;
            }
            else {
                if (this.direction.compareTo("left") != 0) {
                    continue;
                }
                this.offset += this.shift;
                if (this.offset < this.size().width) {
                    continue;
                }
                this.offset = 0;
            }
        }
    }
    
    public void createBuffer() {
        if (this.offImage == null) {
            this.offImage = this.createImage(this.size().width, this.size().height);
        }
        this.offGraphics = this.offImage.getGraphics();
        if (this.method.compareTo("gaussian") == 0) {
            for (int i = 0; i < this.size().width; ++i) {
                this.offGraphics.setColor(this.cg(i));
                this.offGraphics.drawLine(i, 0, i, this.size().height);
            }
            return;
        }
        for (int j = 0; j < this.size().width; ++j) {
            this.offGraphics.setColor(this.c(j));
            this.offGraphics.drawLine(j, 0, j, this.size().height);
        }
    }
    
    public void init() {
        this.isRunning = true;
        if (!this.isParams) {
            this.getParams();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.offImage != null) {
            graphics.drawImage(this.offImage, -this.offset, 0, this);
            graphics.drawImage(this.offImage, this.size().width - this.offset, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.offImage == null) {
            this.createBuffer();
        }
        graphics.drawImage(this.offImage, -this.offset, 0, this);
        graphics.drawImage(this.offImage, this.size().width - this.offset, 0, this);
    }
    
    public double getDoubleParam(final String s, final double n) {
        final String parameter = this.getParameter(s);
        double double1;
        if (parameter != null) {
            double1 = this.parseDouble(parameter);
        }
        else {
            double1 = n;
        }
        if (double1 < 0.0 || double1 > 1.0) {
            double1 = n;
        }
        return double1;
    }
}
