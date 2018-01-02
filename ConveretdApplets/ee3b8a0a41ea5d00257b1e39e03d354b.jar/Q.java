import java.io.IOException;
import java.io.PipedInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Q implements Runnable
{
    private String arraycopy;
    private Thread available;
    private boolean isAlive;
    private PipedInputStream join;
    volatile boolean I;
    volatile boolean Z;
    private volatile String max;
    private byte[] notifyAll;
    volatile byte[] C;
    private volatile int read;
    volatile int B;
    private int start;
    
    Q(final PipedInputStream join, final int n, final int start, final String arraycopy) {
        this.join = join;
        this.arraycopy = arraycopy;
        this.start = start;
        this.notifyAll = new byte[this.start];
        this.read = Math.max(n, this.start << 1);
        this.C = new byte[this.read];
        (this.available = new Thread(this, this.arraycopy)).start();
    }
    
    public final void run() {
        try {
        Label_0162:
            while (!this.isAlive && !this.I) {
                int n = this.join.available();
                if (n == 0 || n > this.start) {
                    n = this.start;
                }
                final int read = this.join.read(this.notifyAll, 0, n);
                if (-1 == read) {
                    this.I = true;
                    break;
                }
                synchronized (this.C) {
                    while (!this.isAlive && this.read < this.B + read) {
                        this.C.wait();
                        if (this.isAlive) {
                            break Label_0162;
                        }
                    }
                    System.arraycopy(this.notifyAll, 0, this.C, this.B, read);
                    this.B += read;
                    this.C.notifyAll();
                }
            }
        }
        catch (InterruptedException ex2) {}
        catch (IOException ex) {
            this.max = ex.toString();
            this.Z = true;
        }
        synchronized (this) {
            if (this.isAlive) {
                this.isAlive = false;
                this.notifyAll();
            }
            else {
                this.available = null;
                this.I();
            }
        }
    }
    
    final synchronized void I() {
        this.isAlive = true;
        synchronized (this.C) {
            this.C.notifyAll();
        }
        if (null != this.available && this.available.isAlive()) {
            while (this.isAlive) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
            try {
                this.available.join();
            }
            catch (InterruptedException ex2) {}
        }
        this.available = null;
        this.join = null;
    }
}
