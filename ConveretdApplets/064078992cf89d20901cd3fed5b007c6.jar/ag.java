import java.awt.image.ImageObserver;
import java.awt.image.DataBufferUShort;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.BufferedImage;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class ag extends r
{
    int x;
    short[] w;
    byte[] y;
    private static int A;
    private int z;
    
    static {
        ag.A = 0;
    }
    
    public ag() {
        this.x = 0;
        this.w = null;
        this.y = null;
        this.z = 0;
    }
    
    public void a() {
        super.a();
        this.w = null;
    }
    
    public void a(final Component p3, final boolean q, final ak s) throws Exception {
        final BufferedImage bufferedImage = (BufferedImage)p3.createImage(1, 1);
        ag.A = bufferedImage.getColorModel().getPixelSize();
        this.z = bufferedImage.getRaster().getDataBuffer().getDataType();
        if ((ag.A == 32 && this.z == 3) || (ag.A == 24 && this.z == 3)) {
            ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
        }
        else if (ag.A == 24 && this.z == 0) {
            l.a = false;
            ((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
        }
        else {
            if (ag.A != 16 || this.z != 1) {
                l.a = false;
                throw new Exception();
            }
            l.a = false;
            ((DataBufferUShort)bufferedImage.getRaster().getDataBuffer()).getData();
        }
        super.q = q;
        super.p = p3;
        super.s = s;
        if (s.r == null) {
            System.out.println("Render mode 1.");
        }
    }
    
    public void int() {
    }
    
    public void do() {
        try {
            if (!l.a) {
                try {
                    int n = 0;
                    if (ag.A == 16) {
                        while (true) {
                            final int n2;
                            this.w[n] = (short)(((n2 = super.c[n++]) >> 3 & 0x1F) | (n2 >> 5 & 0x7E0) | (n2 >> 8 & 0xF800));
                        }
                    }
                    else if (ag.A == 24) {
                        int n3 = 0;
                        while (true) {
                            this.y[n++] = (byte)(super.c[n3++] & 0xFF);
                            this.y[n++] = (byte)(super.c[n3] >> 8 & 0xFF);
                            this.y[n++] = (byte)(super.c[n3] >> 16 & 0xFF);
                        }
                    }
                }
                catch (Exception ex) {}
            }
            super.p.getGraphics().drawImage(super.r, 0, 0, super.p);
        }
        catch (Exception ex2) {}
    }
    
    public void if() {
        if (super.long == super.s.width && super.e == super.s.height) {
            return;
        }
        super.long = super.s.width;
        super.e = super.s.height;
        super.d = super.long;
        super.void = super.e;
        super.r = null;
        System.gc();
        if (l.a) {
            super.r = super.p.createImage(super.long, super.e);
            super.c = ((DataBufferInt)((BufferedImage)super.r).getRaster().getDataBuffer()).getData();
            for (int i = 0; i < super.long * super.e; ++i) {
                super.c[i] = super.s.p;
            }
        }
        else {
            super.c = new int[super.long * super.e];
            super.r = super.p.createImage(super.long, super.e);
            if (ag.A == 16) {
                this.w = ((DataBufferUShort)((BufferedImage)super.r).getRaster().getDataBuffer()).getData();
                final int p = super.s.p;
                final short n = (short)((p >> 3 & 0x1F) | (p >> 5 & 0x7E0) | (p >> 8 & 0xF800 & 0xFFFF));
                for (int j = 0; j < super.long * super.e; ++j) {
                    super.c[j] = super.s.p;
                    this.w[j] = n;
                }
            }
            else if (ag.A == 24) {
                this.y = ((DataBufferByte)((BufferedImage)super.r).getRaster().getDataBuffer()).getData();
                final int p2 = super.s.p;
                final byte b = (byte)(p2 >> 16 & 0xFF);
                final byte b2 = (byte)(p2 >> 8 & 0xFF);
                final byte b3 = (byte)(p2 & 0xFF);
                int n2 = 0;
                for (int k = 0; k < super.long * super.e; ++k) {
                    super.c[k] = super.s.p;
                    this.y[n2++] = b3;
                    this.y[n2++] = b2;
                    this.y[n2++] = b;
                }
            }
        }
    }
}
