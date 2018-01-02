import java.awt.image.ImageProducer;
import I.I;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import C.Z;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ZI extends N
{
    private int[][] I;
    private boolean Z;
    private ztmPlayer arraycopy;
    private Z createImage;
    private byte[] isAlive;
    private volatile int join;
    private volatile int notify;
    private int notifyAll;
    private int setAnimated;
    private int setFullBufferUpdates;
    private int start;
    volatile long C;
    Image[] B;
    MemoryImageSource[] D;
    private int wait;
    
    ZI(final ztmPlayer arraycopy, final double n, final long n2) {
        super(arraycopy.T, 0.5, I.I.I(407));
        this.arraycopy = arraycopy;
        this.notifyAll = this.arraycopy.N;
        this.setAnimated = this.arraycopy.O;
        this.setFullBufferUpdates = this.notifyAll + 15 >> 4 << 4;
        this.start = this.setAnimated + 15 >> 4 << 4;
        this.isAlive = new byte[1];
        this.createImage = new Z(this.isAlive);
        this.Z = true;
        this.C = (long)(1000000.0 / n);
        this.wait = (int)(n2 / this.C);
        this.I = new int[super.J][];
        super.L = new byte[super.J][];
        super.N = new int[super.J];
        super.M = new long[super.J];
        this.B = new Image[super.J];
        this.D = new MemoryImageSource[super.J];
        for (int i = 0; i < super.J; ++i) {
            super.N[i] = -1;
            super.L[i] = new byte[1];
            if (ztmPlayer.Z) {
                this.I[i] = new int[this.notifyAll * this.setAnimated];
                (this.D[i] = new MemoryImageSource(this.notifyAll, this.setAnimated, this.I[i], 0, this.setFullBufferUpdates)).setAnimated(true);
                this.D[i].setFullBufferUpdates(true);
                this.B[i] = arraycopy.createImage(this.D[i]);
            }
            else {
                this.B[i] = CI.I(this.notifyAll, this.setAnimated, this.arraycopy.L);
                this.I[i] = CI.Z(this.B[i]);
            }
        }
        super.N[super.J - 1] = 0;
        (super.E = new Thread(this, super.S)).start();
    }
    
    final void I() {
        try {
            synchronized (this.isAlive) {
                while (true) {
                    if (!super.K && !this.createImage.XB) {
                        this.isAlive.wait();
                    }
                    else {
                        final int notify = this.createImage.TB + this.createImage.VB - this.createImage.UB;
                        this.notify = notify;
                        final int n = notify;
                        int n2;
                        for (n2 = 4; 0 > this.createImage.UB - n2; --n2) {}
                        System.arraycopy(super.O, this.createImage.UB - n2, super.O, 0, n2 + n);
                        final Z createImage = this.createImage;
                        final Z createImage2 = this.createImage;
                        final int ub = 4;
                        this.join = ub;
                        createImage2.VB = ub;
                        createImage.UB = ub;
                        this.createImage.TB = this.notify;
                        if (super.K) {
                            break;
                        }
                        while (true) {
                            synchronized (super.Q.C) {
                                if (0 < super.Q.B) {
                                    System.arraycopy(super.Q.C, 0, super.O, 4 + this.notify, super.Q.B);
                                    this.notify += super.Q.B;
                                    this.createImage.TB = this.notify;
                                    super.Q.B = 0;
                                    super.Q.C.notifyAll();
                                    this.createImage.XB = false;
                                    this.isAlive.notify();
                                    break;
                                }
                                if (super.Q.Z || super.Q.I) {
                                    final Z createImage3 = this.createImage;
                                    final Z createImage4 = this.createImage;
                                    final boolean b = false;
                                    createImage4.XB = b;
                                    createImage3.WB = b;
                                    this.isAlive.notify();
                                    return;
                                }
                                super.Q.C.wait();
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    final int Z() {
        try {
            (super.G = new Thread(this, super.A)).start();
            int n = 0;
            while (!super.H && this.createImage.WB) {
                if (this.Z) {
                    if (this.createImage.I(super.O, this.join, this.notify)) {
                        this.createImage.I();
                    }
                    this.join += this.createImage.s();
                    this.notify -= this.createImage.s();
                    this.Z = false;
                }
                final int z = this.createImage.Z(super.O, this.join, this.notify);
                if (z != 0 && z != 5) {
                    break;
                }
                final int s = this.createImage.s();
                this.join += s;
                this.notify -= s;
                if (++n <= this.wait && n == 0) {
                    continue;
                }
                while (!super.H) {
                    synchronized (super.L[super.U]) {
                        if (-1 == super.N[super.U]) {
                            C.I.a(this.createImage.xZ.Y, this.createImage.xZ.V, this.createImage.xZ.U, this.I[super.U], this.setFullBufferUpdates, this.notifyAll, this.setAnimated, this.arraycopy.L);
                            super.N[super.U] = n;
                            super.M[super.U] = super.T++ * this.C;
                            super.L[super.U].notify();
                            if (super.J == ++super.U) {
                                super.U = 0;
                            }
                            break;
                        }
                        super.L[super.U].wait();
                    }
                }
            }
        }
        catch (Exception ex) {}
        return super.U;
    }
    
    final float C() {
        synchronized (super.Q.C) {
            return (super.Q.B + this.notify) / super.R;
        }
    }
    
    final void B() {
        synchronized (this) {
            final boolean b = true;
            super.K = b;
            super.H = b;
            synchronized (this.isAlive) {
                this.isAlive.notifyAll();
            }
            synchronized (super.Q.C) {
                super.Q.C.notifyAll();
            }
            final int u = super.U;
            synchronized (super.L[u]) {
                super.L[u].notifyAll();
            }
            if (null != super.G && super.G.isAlive()) {
                while (super.K) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                try {
                    super.G.join();
                }
                catch (InterruptedException ex2) {}
            }
            super.G = null;
            if (null != super.E && super.E.isAlive()) {
                while (super.H) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex3) {}
                }
                try {
                    super.E.join();
                }
                catch (InterruptedException ex4) {}
            }
            this.createImage = null;
            super.E = null;
            this.arraycopy = null;
            this.notifyAll();
        }
    }
}
