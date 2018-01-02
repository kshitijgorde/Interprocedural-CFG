import java.io.IOException;
import java.util.Vector;
import Z.J;
import Z.B;
import Z.L;
import Z.CI;
import Z.BI;
import Z.EI;
import Z.GI;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class I extends N implements Runnable
{
    static byte[] I;
    static byte[] Z;
    private static int[] addElement;
    private long arraycopy;
    private boolean close;
    private PipedInputStream elementAt;
    private PipedOutputStream isAlive;
    private volatile int join;
    private int max;
    byte[][] C;
    int[] B;
    private GI min;
    private EI notifyAll;
    private BI read;
    private CI removeElementAt;
    private L size;
    private B start;
    private J wait;
    private Z.I write;
    volatile int D;
    private Vector F;
    
    I(final Q q, final double n, final boolean close, final int n2) {
        super(q, n, I.I.I(431));
        this.close = close;
        this.D = -8 * n2;
        this.F = new Vector();
        this.min = new GI();
        this.notifyAll = new EI();
        this.read = new BI();
        this.removeElementAt = new CI();
        this.size = new L();
        this.start = new B();
        this.wait = new J();
        this.write = new Z.I(this.wait);
        final GI min = this.min;
        GI.I();
        this.isAlive = new PipedOutputStream(this.elementAt = new PipedInputStream());
        (super.E = new Thread(this, super.S)).start();
    }
    
    final void I() {
        try {
            while (!super.K) {
                if (0 < this.join) {
                    this.isAlive.write(super.O, 0, this.join);
                    this.join = 0;
                }
                else {
                    synchronized (super.Q.C) {
                        if (0 < super.Q.B) {
                            System.arraycopy(super.Q.C, 0, super.O, 0, super.Q.B);
                            this.join = super.Q.B;
                            super.Q.B = 0;
                            super.Q.C.notifyAll();
                        }
                        else {
                            if (super.Q.Z || super.Q.I) {
                                this.isAlive.close();
                                break;
                            }
                            super.Q.C.wait();
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
            final float[][][] array = { null };
            final int[] array2 = { 0 };
            final int read;
            this.arraycopy += (read = this.elementAt.read(this.min.I, this.min.I(4096), 4096));
            this.min.Z(read);
            this.min.I(this.read);
            this.notifyAll.I(this.read.F());
            this.size.I();
            this.start.I();
            if (0 > this.notifyAll.I(this.read) || 1 != this.notifyAll.I(this.removeElementAt) || 0 > this.size.I(this.start, this.removeElementAt)) {
                throw new InterruptedException();
            }
            int i = 0;
            while (i < 2 && !super.H) {
                while (i < 2 && !super.H) {
                    final int j = this.min.I(this.read);
                    if (j == 0) {
                        break;
                    }
                    if (j == 0) {
                        continue;
                    }
                    this.notifyAll.I(this.read);
                    while (i < 2) {
                        final int k = this.notifyAll.I(this.removeElementAt);
                        if (k == 0) {
                            break;
                        }
                        if (-1 == k) {
                            throw new InterruptedException();
                        }
                        this.size.I(this.start, this.removeElementAt);
                        ++i;
                    }
                }
                this.arraycopy += this.elementAt.read(this.min.I, this.min.I(4096), 4096);
                if (read == 0 && i < 2) {
                    throw new InterruptedException();
                }
                this.min.Z(read);
            }
            this.wait.I(this.size);
            this.write.I(this.wait);
            final int min = Math.min(this.size.J[0], this.size.J[1]);
            this.max = Math.max(this.size.J[0], this.size.J[1]);
            synchronized (this) {
                super.J = 1 + (int)(25600.0 / min);
                this.C = new byte[super.J][];
                super.L = new byte[super.J][];
                super.N = new int[super.J];
                this.B = new int[super.J];
                for (int l = super.J - 1; l >= 0; --l) {
                    this.C[l] = new byte[this.max];
                    super.L[l] = new byte[1];
                    super.N[l] = -1;
                }
                this.notifyAll();
            }
            int read2 = 0;
        Label_1322:
            for (boolean b = false; !b; b = (read2 == 0)) {
                while (!b) {
                    final int m = this.min.I(this.read);
                    if (m == 0) {
                        break;
                    }
                    if (-1 == m) {
                        continue;
                    }
                    this.notifyAll.I(this.read);
                    while (true) {
                        final int i2 = this.notifyAll.I(this.removeElementAt);
                        if (i2 == 0) {
                            b = (0 != this.read.B());
                            break;
                        }
                        if (-1 == i2) {
                            continue;
                        }
                        if (0 == this.write.I(this.removeElementAt)) {
                            this.wait.I(this.write);
                        }
                        int i3;
                        while (0 < (i3 = this.wait.I(array, array2))) {
                            float[] array3 = array[0][0];
                            int n = array2[0];
                            int d = (i3 < 4096) ? i3 : 4096;
                            if (0 < this.D) {
                                if (d > this.D) {
                                    d = this.D;
                                }
                                this.D -= d;
                                this.wait.I(d);
                            }
                            else {
                                if (0 > this.D) {
                                    d = -this.D;
                                    if (d > i3) {
                                        d = i3;
                                    }
                                    array3 = new float[d];
                                    n = 0;
                                }
                                if (super.H) {
                                    break Label_1322;
                                }
                                synchronized (super.L[super.U]) {
                                    if (-1 == super.N[super.U]) {
                                        final byte[] array4 = this.C[super.U];
                                        int n2 = 0;
                                        if (this.close) {
                                            for (int n3 = 0; n3 < d; ++n3) {
                                                final int n4 = (int)(32767.0f * array3[n + n3]) >> 2;
                                                array4[n2++] = (byte)n4;
                                                array4[n2++] = (byte)(n4 >>> 8);
                                            }
                                        }
                                        else {
                                            for (int n5 = 0; n5 < d; ++n5) {
                                                int n6 = (int)(32767.0f * array3[n + n5]);
                                                if (n6 > 32767) {
                                                    n6 = 32767;
                                                }
                                                if (n6 < -32768) {
                                                    n6 = -32768;
                                                }
                                                array4[n2++] = (byte)n6;
                                                array4[n2++] = (byte)(n6 >>> 8);
                                            }
                                        }
                                        if (0 > this.D) {
                                            this.D += d;
                                        }
                                        else {
                                            this.wait.I(d);
                                        }
                                        this.B[super.U] = n2;
                                        super.N[super.U] = ++super.T;
                                        super.L[super.U].notifyAll();
                                        if (super.J != ++super.U) {
                                            continue;
                                        }
                                        super.U = 0;
                                    }
                                    else {
                                        super.L[super.U].wait();
                                    }
                                }
                            }
                        }
                    }
                }
                if (!b) {
                    this.arraycopy += (read2 = this.elementAt.read(this.min.I, this.min.I(4096), 4096));
                    if (0 < this.F.size()) {
                        synchronized (this) {
                            final long[] array5 = this.F.elementAt(0);
                            if (this.arraycopy >= array5[0]) {
                                this.D = (int)array5[1];
                                this.F.removeElementAt(0);
                            }
                        }
                    }
                    this.min.Z(read2);
                }
            }
        }
        catch (Exception ex) {}
        return super.U;
    }
    
    final synchronized void I(final long n, final int n2) {
        this.F.addElement(new long[] { n, -8L * n2 });
    }
    
    final float C() {
        synchronized (super.Q.C) {
            return (super.Q.B + this.join) / super.R;
        }
    }
    
    final synchronized void B() {
        final boolean b = true;
        super.K = b;
        super.H = b;
        if (null != super.Q) {
            synchronized (super.Q.C) {
                super.Q.C.notifyAll();
            }
        }
        final int u = super.U;
        if (null != super.L) {
            synchronized (super.L[u]) {
                super.L[u].notifyAll();
            }
        }
        if (null != super.G && super.G.isAlive()) {
            if (null != this.elementAt) {
                try {
                    this.elementAt.close();
                }
                catch (IOException ex) {}
            }
            while (super.K) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex2) {}
            }
            try {
                super.G.join();
            }
            catch (InterruptedException ex3) {}
        }
        super.G = null;
        if (null != super.E && super.E.isAlive()) {
            while (super.H) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex4) {}
            }
            try {
                super.E.join();
            }
            catch (InterruptedException ex5) {}
        }
        super.Q = null;
        super.E = null;
        this.notifyAll();
    }
    
    static {
        I.addElement = new int[] { 63, 127, 255, 511, 1023, 2047, 4095, 8191, -1 };
        I.I = new byte[8160];
        I.Z = new byte[8160];
        int n = 0;
        for (int i = 0; i <= 8159; ++i) {
            final int n2 = i + 33;
            if (n < 8) {
                I.I[i] = (byte)((n << 4 | (n2 >> n + 1 & 0xF)) ^ 0x7F);
                I.Z[i] = (byte)((n << 4 | (n2 >> n + 1 & 0xF)) ^ 0xFF);
            }
            else {
                I.I[i] = 0;
                I.Z[i] = -128;
            }
            if (n2 == I.addElement[n]) {
                ++n;
            }
        }
    }
}
