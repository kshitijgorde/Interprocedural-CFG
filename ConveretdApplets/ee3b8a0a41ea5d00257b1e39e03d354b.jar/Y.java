import java.io.IOException;
import java.io.PipedInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import java.io.PipedOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Y extends Z
{
    private volatile II D;
    private PipedOutputStream close;
    private byte[] notify;
    private int notifyAll;
    private volatile boolean player;
    private int round;
    private final int start = 4000;
    
    Y() {
        this.player = true;
    }
    
    protected final long B() {
        return (null == this.D) ? 0L : (125L * this.D.I);
    }
    
    final void D() {
        synchronized (this) {
            super.J = true;
            this.close = null;
            this.notifyAll = this.round;
            if (null != this.D) {
                AudioPlayer.player.stop(this.D);
            }
        }
    }
    
    final void F() {
        synchronized (this) {
            synchronized (super.C) {
                while (null == super.C.C) {
                    if (!super.C.P) {
                        this.C();
                        return;
                    }
                    super.C.wait();
                }
            }
            final int length = super.C.C[0].length;
            this.notify = new byte[length];
            for (int i = 0; i < length; ++i) {
                this.notify[i] = -1;
            }
            this.close();
            super.J = false;
            this.notifyAll();
        }
    }
    
    final void J() {
        synchronized (super.B.Y) {
            synchronized (this) {
                this.close();
                super.J = false;
                this.player = true;
                this.notifyAll();
            }
            super.B.Y.wait();
        }
    }
    
    private void close() {
        try {
            this.D = new II(4000, this.notifyAll);
            this.close = new PipedOutputStream(this.D);
        }
        catch (IOException ex) {}
        AudioPlayer.player.start(this.D);
    }
    
    public final void run() {
        try {
            while (!super.S) {
                if (super.J) {
                    synchronized (this) {
                        while (super.J && !super.S) {
                            this.wait();
                        }
                    }
                }
                synchronized (super.C.L[super.E]) {
                    if (0 < super.C.N[super.E]) {
                        if (this.player) {
                            synchronized (super.B.Y) {
                                super.B.Y.notifyAll();
                            }
                            this.player = false;
                        }
                        try {
                            if (super.Z) {
                                this.close.write(this.notify, 0, super.C.B[super.E]);
                            }
                            else {
                                for (int i = 0; i < super.C.B[super.E]; i += 2) {
                                    final int n = (int)Math.round((super.C.C[super.E][i + 1] << 8 | (0xFF & super.C.C[super.E][i])) * super.B.ZI);
                                    byte b;
                                    if (n < 0) {
                                        final int n2 = -n;
                                        b = I.I[(n2 > 8159) ? 8159 : n2];
                                    }
                                    else {
                                        b = I.Z[(n > 8159) ? 8159 : n];
                                    }
                                    this.close.write(b);
                                }
                            }
                        }
                        catch (NullPointerException ex) {
                            continue;
                        }
                        catch (IOException ex2) {
                            break;
                        }
                        this.round += super.C.B[super.E];
                        super.C.N[super.E] = -1;
                        super.C.L[super.E].notify();
                        if (super.C.J != ++super.E) {
                            continue;
                        }
                        super.E = 0;
                    }
                    else {
                        if (!super.C.P) {
                            break;
                        }
                        super.B.I();
                        super.C.L[super.E].wait();
                    }
                }
            }
        }
        catch (InterruptedException ex3) {}
        synchronized (this) {
            if (super.S) {
                super.S = false;
                this.notifyAll();
            }
            else {
                super.A = null;
                this.C();
            }
        }
    }
    
    final synchronized void C() {
        super.S = true;
        if (null != this.close) {
            try {
                if (this.close != null) {
                    this.close.close();
                }
            }
            catch (Exception ex) {}
            this.close = null;
        }
        if (null != this.D) {
            try {
                this.D.close();
            }
            catch (Exception ex2) {}
            this.D = null;
        }
        super.C();
    }
}
