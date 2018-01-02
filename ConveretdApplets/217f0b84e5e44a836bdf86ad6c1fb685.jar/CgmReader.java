import java.io.IOException;
import java.awt.Color;
import java.io.DataInput;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class CgmReader implements Runnable
{
    CgmContext cgm;
    DataInput in;
    Color[] ColorTable;
    Thread ReaderThread;
    static final boolean DebugOutput = false;
    Cgm ContextOrPicture;
    static final int COLOR_MODE_DIRECT = 0;
    static final int COLOR_MODE_DIRECT_ALPHA = 1;
    static final int COLOR_MODE_INDEXED = 2;
    int ColorMode;
    int colr1;
    int colg1;
    int colb1;
    double colr2;
    double colg2;
    double colb2;
    
    public CgmReader() {
        this.cgm = null;
        this.in = null;
        this.ColorTable = new Color[257];
        this.ReaderThread = null;
        this.ColorMode = 1;
        this.colr1 = 0;
        this.colg1 = 0;
        this.colb1 = 0;
        this.colr2 = 1.0;
        this.colg2 = 1.0;
        this.colb2 = 1.0;
    }
    
    final Color readColor() {
        return this.readColor(this.ColorMode);
    }
    
    final Color readColor(final int n) {
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 0.0;
        Label_0141: {
            try {
                switch (n) {
                    case 1: {
                        n2 = this.readColorComp();
                        n3 = this.readColorComp();
                        n4 = this.readColorComp();
                        this.readColorComp();
                        break Label_0141;
                    }
                    case 0: {
                        n2 = this.readColorComp();
                        n3 = this.readColorComp();
                        n4 = this.readColorComp();
                        break Label_0141;
                    }
                    case 2: {
                        final int int1 = this.readInt();
                        try {
                            final Color color = this.ColorTable[int1];
                            if (color != null) {
                                return color;
                            }
                            break Label_0141;
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {
                            return Color.red;
                        }
                        break;
                    }
                }
                System.out.println("Unknown Color Mode: " + n);
                System.exit(1);
            }
            catch (Exception ex2) {
                return null;
            }
        }
        if (this.ContextOrPicture.applet.inverseColor) {
            return new Color(255 - (int)((n2 - this.colr1) * this.colr2), 255 - (int)((n3 - this.colg1) * this.colg2), 255 - (int)((n4 - this.colb1) * this.colb2));
        }
        return new Color((int)((n2 - this.colr1) * this.colr2), (int)((n3 - this.colg1) * this.colg2), (int)((n4 - this.colb1) * this.colb2));
    }
    
    protected abstract double readColorComp() throws Exception;
    
    abstract void readElement() throws IOException;
    
    protected abstract int readInt() throws Exception;
    
    public final void run() {
        try {
            this.readElement();
        }
        catch (IOException ex) {
            System.out.println("Error reading CGM:" + ex);
        }
    }
    
    static final double toAngle(final double n, final double n2) {
        if (n2 == 0.0) {
            return (n < 0.0) ? 3.141592653589793 : 0.0;
        }
        if (n == 0.0) {
            return (n2 < 0.0) ? -1.5707963267948966 : 1.5707963267948966;
        }
        return Math.atan(n2 / n);
    }
    
    public final void waitFor() {
        while (this.ReaderThread.isAlive()) {
            try {
                if (!this.ReaderThread.isAlive()) {
                    continue;
                }
                this.ReaderThread.join(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
}
