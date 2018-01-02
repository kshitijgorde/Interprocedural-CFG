import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageMover implements Runnable
{
    private Graphics gr;
    private Image im;
    private int[] x;
    private int[] y;
    private int[] f;
    private int[] t;
    public long t0;
    public int tcur;
    public int xcur;
    public int ycur;
    private int icur;
    private int xold;
    private int yold;
    private int tsleep;
    public boolean posChanged;
    public boolean needsRedraw;
    public boolean isVisible;
    public boolean debug;
    public Thread th;
    
    public void print_array(final int[] array) {
        String s = "array[" + array.length + "] ";
        for (int i = 0; i < array.length; ++i) {
            s = s + array[i] + " ";
        }
        System.out.println(s);
    }
    
    ImageMover(final int[] x, final int[] y, final int[] t, final int[] f) {
        this.tsleep = 100;
        this.posChanged = false;
        this.needsRedraw = false;
        this.isVisible = false;
        this.debug = false;
        this.x = x;
        this.y = y;
        this.t = t;
        this.f = f;
        if (this.debug) {
            System.out.println("constructing ImageMover ");
            this.print_array(x);
            this.print_array(y);
            this.print_array(t);
            this.print_array(f);
        }
        this.xold = x[0];
        this.yold = y[0];
        this.t0 = System.currentTimeMillis();
        this.icur = 1;
        this.xcur = x[0];
        this.ycur = y[0];
        this.tcur = 0;
        if (f[0] == 1) {
            this.posChanged = true;
        }
        this.th = new Thread(this);
    }
    
    private int interpolate(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n3 != n2) {
            return n4 + (n5 - n4) * (n - n2) / (n3 - n2);
        }
        return n4;
    }
    
    public void start() {
        if (this.th != null && this.th.isAlive()) {
            if (this.debug) {
                System.out.println("ImageMover.start(): but already active() ??");
            }
            this.th.stop();
            this.th = null;
        }
        if (this.th == null) {
            this.th = new Thread(this);
        }
        this.icur = 1;
        this.t0 = System.currentTimeMillis();
        this.th.start();
        if (this.debug) {
            System.out.println("ImageMover.start() at time " + this.t0);
        }
    }
    
    public void stop() {
        if (this.th != null) {
            this.th = null;
        }
        if (this.debug) {
            System.out.println("ImageMover.stop() at time " + this.tcur);
        }
    }
    
    public void run() {
        if (this.debug) {
            System.out.println("ImageMover.run started...");
        }
        this.icur = 1;
        this.t0 = System.currentTimeMillis();
        this.tcur = 0;
        this.posChanged = true;
        if (this.f[this.icur] == 1) {
            this.isVisible = true;
        }
        else {
            this.isVisible = false;
        }
        if (this.f[this.icur] == 1) {
            this.needsRedraw = true;
        }
        else {
            this.needsRedraw = false;
        }
        while (this.tcur < this.t[this.t.length - 1]) {
            while (this.tcur > this.t[this.icur] && this.icur < this.x.length - 1) {
                ++this.icur;
            }
            this.xold = this.xcur;
            this.yold = this.ycur;
            this.xcur = this.interpolate(this.tcur, this.t[this.icur], this.t[this.icur - 1], this.x[this.icur], this.x[this.icur - 1]);
            this.ycur = this.interpolate(this.tcur, this.t[this.icur], this.t[this.icur - 1], this.y[this.icur], this.y[this.icur - 1]);
            if (this.debug) {
                System.out.println("...m1: " + this.tcur + " " + this.icur + " " + this.xcur);
            }
            if (this.xold != this.xcur || this.yold != this.ycur) {
                this.posChanged = true;
            }
            if (this.f[this.icur] == 1) {
                this.isVisible = true;
            }
            else {
                this.isVisible = false;
            }
            if (this.f[this.icur] == 1) {
                this.needsRedraw = true;
            }
            else {
                this.needsRedraw = false;
            }
            try {
                Thread.sleep(40L);
            }
            catch (InterruptedException ex) {
                break;
            }
            this.tcur = (int)(System.currentTimeMillis() - this.t0);
        }
        if (this.debug) {
            System.out.println("m1: stopping the thread");
        }
        this.th = null;
    }
    
    public boolean needsRedraw() {
        return this.needsRedraw;
    }
    
    public boolean posChanged() {
        return this.posChanged;
    }
}
