import javax.sound.sampled.LineListener;
import javax.sound.sampled.Control;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import I.I;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

// 
// Decompiled by Procyon v0.5.30
// 

public final class M extends Z
{
    SourceDataLine I;
    private int D;
    private AudioFormat MUTE;
    private byte[] addLineListener;
    private final int arraycopy = 8000;
    static Class close;
    
    M() {
        this.addLineListener = new byte[1];
        this.MUTE = new AudioFormat(8000.0f, 16, 1, true, false);
        final Line line = AudioSystem.getLine(new DataLine.Info((M.close == null) ? (M.close = D(I.I.I(359))) : M.close, this.MUTE));
        if (line instanceof SourceDataLine) {
            this.I = (SourceDataLine)line;
            return;
        }
        throw new LineUnavailableException();
    }
    
    protected final long B() {
        return this.I.getMicrosecondPosition() + 135000L;
    }
    
    final void I() {
        super.I();
        ((BooleanControl)this.I.getControl(BooleanControl.Type.MUTE)).setValue(true);
    }
    
    final void Z() {
        super.I();
        ((BooleanControl)this.I.getControl(BooleanControl.Type.MUTE)).setValue(false);
    }
    
    final synchronized void D() {
        super.J = true;
        if (this.I.isRunning()) {
            synchronized (this.addLineListener) {
                this.I.stop();
                this.addLineListener.wait();
            }
        }
    }
    
    final synchronized void F() {
        try {
            synchronized (super.C) {
                while (null == super.C.C) {
                    if (!super.C.P) {
                        this.C();
                        return;
                    }
                    super.C.wait();
                }
            }
            this.I.open(this.MUTE, 8000);
            this.I.addLineListener(new L(this));
            this.I.start();
            if (super.Z) {
                this.I();
            }
            this.D = super.C.C[0].length;
        }
        catch (Exception ex) {}
        super.J = false;
        this.notifyAll();
    }
    
    final synchronized void J() {
        this.I.start();
        super.J = false;
        this.notifyAll();
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
                if (null == super.C.L) {
                    synchronized (this) {
                        this.wait(50L);
                    }
                }
                else {
                    synchronized (super.C.L[super.E]) {
                        if (0 < super.C.N[super.E]) {
                            for (int i = 0; i < super.C.B[super.E]; i += 2) {
                                final int n = (int)Math.round((super.C.C[super.E][i + 1] << 8 | (0xFF & super.C.C[super.E][i])) * super.B.ZI);
                                super.C.C[super.E][i] = (byte)n;
                                super.C.C[super.E][i + 1] = (byte)(n >>> 8);
                            }
                            final int write = this.I.write(super.C.C[super.E], 0, super.C.B[super.E]);
                            if (write != super.C.B[super.E]) {
                                final int[] b = super.C.B;
                                final int e = super.E;
                                b[e] -= write;
                                System.arraycopy(super.C.C[super.E], write, super.C.C[super.E], 0, super.C.B[super.E]);
                            }
                            else {
                                super.C.N[super.E] = -1;
                                super.C.L[super.E].notify();
                                if (super.C.J != ++super.E) {
                                    continue;
                                }
                                super.E = 0;
                            }
                        }
                        else {
                            if (!super.C.P) {
                                if (this.I.isActive()) {
                                    this.I.drain();
                                }
                                break;
                            }
                            super.B.I();
                            super.C.L[super.E].wait();
                        }
                    }
                }
            }
        }
        catch (InterruptedException ex) {}
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
        this.I.close();
        this.MUTE = null;
    }
    
    static final Z S() {
        return new M();
    }
    
    private static final Class D(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static final byte[] I(final M m) {
        return m.addLineListener;
    }
}
